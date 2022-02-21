package br.ucsal.fitsys.fitsys.services;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.domain.DTO.AlunoNewDTO;
import br.ucsal.fitsys.fitsys.domain.Endereco;
import br.ucsal.fitsys.fitsys.domain.Instrutor;
import br.ucsal.fitsys.fitsys.domain.Treino;
import br.ucsal.fitsys.fitsys.domain.enums.Perfil;
import br.ucsal.fitsys.fitsys.repositories.AlunoRepository;
import br.ucsal.fitsys.fitsys.security.UserSS;
import br.ucsal.fitsys.fitsys.services.exceptions.AuthorizationException;
import br.ucsal.fitsys.fitsys.services.exceptions.DataIntegrityException;
import br.ucsal.fitsys.fitsys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private InstrutorService instrutorService;

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Aluno insert(Aluno obj) {
        return repository.save(obj);
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Aluno findById(Integer id) {

        // Restrição de conteúdo pelo id, um aluno apenas consulta ele mesmo
        UserSS user = UserService.authenticated();
        if (user == null || user.hasRole(Perfil.ALUNO) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Aluno> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado! Id: " + id + ", Tipo:" + Aluno.class.getName()));

    }

    public Aluno findByEmail(String email) {
        // Restrição de conteúdo pelo email, um aluno apenas consulta ele mesmo
        UserSS user = UserService.authenticated();
        if (user == null || user.hasRole(Perfil.ALUNO) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Aluno obj = repository.findByEmail(email);
        if (obj == null)
            throw new ObjectNotFoundException("Objeto não encontrado! Email: " + email + " , Tipo: " + Aluno.class.getName());
        return obj;
    }

    public boolean verificaAluno(String username){
        return repository.existsByEmail(username);
    }

    public void delete(String email) {
        try {
            if (repository.existsByEmail(email))
                repository.deleteAlunoByEmail(email);
            else
                throw new ObjectNotFoundException("Objeto não encontrado! Email: " + email + " , Tipo: " + Aluno.class.getName());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir este Aluno");
        }
    }

    public Aluno update(Integer id, Aluno obj) {
        Aluno aluno = findById(id);
        updateDate(aluno, obj);
        return repository.save(aluno);
    }

    private void updateDate(Aluno aluno, Aluno obj) {
        aluno.setAltura(obj.getAltura());
        aluno.setPeso(obj.getPeso());
        aluno.setCpf(obj.getCpf());
        aluno.setDataNascimento(obj.getDataNascimento());
        aluno.setEmail(obj.getEmail());
        aluno.setNome(obj.getNome());
        aluno.setTelefone(obj.getTelefone());
    }

    public void removeTreino(Integer id){
        Aluno aluno  = retornaAlunoAutenticado();
        Treino treino = treinoService.findById(id);
        aluno.getTreinos().remove(treino);
        update(aluno.getId(), aluno);
    }

    public void selectInstrutor(Integer id) {
        Instrutor instrutor = instrutorService.findById(id);
        Aluno aluno = this.retornaAlunoAutenticado();
        instrutor.getAlunos().add(aluno);
        aluno.setInstrutor(instrutor);
        this.update(aluno.getId(), aluno);
        instrutorService.update(instrutor.getId(), instrutor);
    }

    public Integer getInstrutorId(){
        Aluno aluno = this.retornaAlunoAutenticado();
        return aluno.getInstrutor().getId();
    }

    public Aluno fromDTO(AlunoNewDTO objDTO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = null;
        try {
            dataNascimento = sdf.parse(objDTO.getDataNascimento());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Aluno aluno = new Aluno(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getPeso(), objDTO.getAltura(), objDTO.getTelefone(), objDTO.getEmail(), encoder.encode(objDTO.getSenha()), dataNascimento);
        Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCidade(), objDTO.getEstado(), objDTO.getCep());
        aluno.setEndereco(endereco);
        return aluno;
    }

    public Aluno retornaAlunoAutenticado(){
        UserSS user = UserService.authenticated();
        Aluno aluno = findByEmail(user.getUsername());
        return aluno;
    }
}