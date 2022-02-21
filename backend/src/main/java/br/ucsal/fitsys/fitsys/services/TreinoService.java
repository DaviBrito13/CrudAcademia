package br.ucsal.fitsys.fitsys.services;

import br.ucsal.fitsys.fitsys.domain.DTO.TreinoDTO;
import br.ucsal.fitsys.fitsys.domain.Exercicio;
import br.ucsal.fitsys.fitsys.domain.Treino;
import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.security.UserSS;
import br.ucsal.fitsys.fitsys.repositories.TreinoRepository;
import br.ucsal.fitsys.fitsys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ExercicioService exercicioService;

    public Treino findById(Integer id) {
        return treinoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Treino não encontrado! Id: " + id + ", Tipo:" + Treino.class.getName()));
    }

    public List<Treino> findAll() {
        return treinoRepository.findAll();
    }

    public Treino findByNome(String nome) {
        return treinoRepository.findByNomeIgnoreCase(nome).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Nome: " + nome + " , Tipo: " + Treino.class.getName()));
    }

    public List<Treino> findTreinoAluno() {
        UserSS user = UserService.authenticated();
        Aluno aluno = alunoService.findByEmail(user.getUsername());
        return aluno.getTreinos();
    }

    public Treino insert(TreinoDTO treinoDTO) {
        Treino treino = new Treino(treinoDTO.getId(), treinoDTO.getNome(), treinoDTO.getDescricao());
        Set<Exercicio> exerciciosSet = new HashSet<>();
        for (Integer x : treinoDTO.getExercicios()) {
            Exercicio exercicio = exercicioService.findById(x);
            exerciciosSet.add(exercicio);
        }
        treino.setExercicios(exerciciosSet);
        Treino response = treinoRepository.save(treino);
        for (Integer x : treinoDTO.getExercicios()) {
            Exercicio exercicio = exercicioService.findById(x);
            exercicio.getTreinos().add(treino);
            exercicioService.update(exercicio.getId(), exercicio);
        }
        return response;
    }

    public void delete(Integer id) {
        findById(id);
        treinoRepository.deleteById(id);
    }

//  TODO Fazer o método update
}
