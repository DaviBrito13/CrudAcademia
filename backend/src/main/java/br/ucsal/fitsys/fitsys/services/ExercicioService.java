package br.ucsal.fitsys.fitsys.services;

import br.ucsal.fitsys.fitsys.domain.DTO.ExercicioDTO;
import br.ucsal.fitsys.fitsys.domain.Exercicio;
import br.ucsal.fitsys.fitsys.domain.enums.TipoTreino;
import br.ucsal.fitsys.fitsys.repositories.ExercicioRepository;
import br.ucsal.fitsys.fitsys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {

    @Autowired
    ExercicioRepository exercicioRepository;

    public Exercicio findById(Integer id) {
        return exercicioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado! Id: " + id + ", Tipo:" + Exercicio.class.getName()));
    }

    public Exercicio insert(ExercicioDTO exercicioDTO) {
        Exercicio exercicio = new Exercicio(exercicioDTO.getId(), exercicioDTO.getTitulo(), exercicioDTO.getDescricao(), exercicioDTO.getUrlImage());
        exercicioDTO.getTiposTreinos().forEach(x -> exercicio.addTipoTreino(TipoTreino.toEnum(x)));
        return exercicioRepository.save(exercicio);
    }

    public List<Exercicio> findAll() {
        return exercicioRepository.findAll();
    }

    public Exercicio findByTitulo(String titulo) {
        return exercicioRepository.findByTituloIgnoreCase(titulo);
    }

    public void deleteById(Integer id) {
        if (exercicioRepository.existsById(id))
            exercicioRepository.deleteById(id);
        else {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Exercicio.class.getName());
        }
    }

//    TODO Criar método de update

    public Exercicio update(Integer id, Exercicio obj){
        Exercicio exercicio = findById(id);
        updateData(exercicio, obj);
        return exercicioRepository.save(exercicio);
    }

    private void updateData(Exercicio exercicio, Exercicio obj) {
        exercicio.setTitulo(obj.getTitulo());
        exercicio.setDescricao(obj.getDescricao());
        exercicio.setUrlImage(obj.getUrlImage());
        exercicio.setTreinos(obj.getTreinos());
    }
}
