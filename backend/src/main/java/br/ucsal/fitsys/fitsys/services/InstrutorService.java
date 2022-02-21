package br.ucsal.fitsys.fitsys.services;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.domain.DTO.InstrutorDTO;
import br.ucsal.fitsys.fitsys.domain.Endereco;
import br.ucsal.fitsys.fitsys.domain.Instrutor;
import br.ucsal.fitsys.fitsys.repositories.InstrutorRepository;
import br.ucsal.fitsys.fitsys.security.UserSS;
import br.ucsal.fitsys.fitsys.services.exceptions.DataIntegrityException;
import br.ucsal.fitsys.fitsys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InstrutorService {

    @Autowired
    private InstrutorRepository repository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Instrutor insert(Instrutor obj) {
        return repository.save(obj);
    }

    public List<Instrutor> findAll() {
        return repository.findAll();
    }

    public Instrutor findById(Integer id) {
        Optional<Instrutor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Instrutor não encontrado! Id: " + id + ", Tipo:" + Instrutor.class.getName()));
    }

    public Instrutor findByEmail(String email) {
        Instrutor obj = repository.findByEmail(email);
        if (obj == null)
            throw new ObjectNotFoundException("Objeto não encontrado! Email: " + email + " , Tipo: " + Instrutor.class.getName());
        return obj;
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir este Instrutor");
        }
    }

    public Instrutor update(Integer id, Instrutor obj) {
        Instrutor instrutor = findById(obj.getId());
        updateDate(instrutor, obj);
        return repository.save(instrutor);
    }

    private void updateDate(Instrutor instrutor, Instrutor obj) {
        instrutor.setCpf(obj.getCpf());
        instrutor.setDataNascimento(obj.getDataNascimento());
        instrutor.setEmail(obj.getEmail());
        instrutor.setNome(obj.getNome());
        instrutor.setTelefone(obj.getTelefone());
    }

    public List<Aluno> getAlunos(String email){
        Instrutor instrutor = findByEmail(email);
        return instrutor.getAlunos();
    }

    public void removeAluno(Integer id){
        Aluno aluno = alunoService.findById(id);
        Instrutor instrutor = retornaInstrutorAutenticado();
        instrutor.getAlunos().remove(aluno);
        aluno.setInstrutor(null);
        update(instrutor.getId(), instrutor);
        alunoService.update(aluno.getId(), aluno);
    }

    public Instrutor fromDTO(InstrutorDTO objDTO){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = null;
        try {
            dataNascimento = sdf.parse(objDTO.getDataNascimento());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Instrutor instrutor = new Instrutor(null, objDTO.getNome(),objDTO.getCpf(),objDTO.getTelefone(), objDTO.getEmail(), encoder.encode(objDTO.getSenha()), dataNascimento);
        Endereco endereco = new Endereco(null, objDTO.getLogradouro(),objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCidade(), objDTO.getEstado(), objDTO.getCep());
        instrutor.setEndereco(endereco);
        return instrutor;
    }

    public Instrutor retornaInstrutorAutenticado(){
        UserSS user = UserService.authenticated();
        Instrutor instrutor = findByEmail(user.getUsername());
        return instrutor;
    }

}
