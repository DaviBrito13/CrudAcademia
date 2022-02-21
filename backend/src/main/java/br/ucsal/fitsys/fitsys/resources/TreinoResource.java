package br.ucsal.fitsys.fitsys.resources;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.domain.DTO.TreinoDTO;
import br.ucsal.fitsys.fitsys.domain.Exercicio;
import br.ucsal.fitsys.fitsys.domain.Treino;
import br.ucsal.fitsys.fitsys.services.AlunoService;
import br.ucsal.fitsys.fitsys.services.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/treinos")
public class TreinoResource {

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping()
    public ResponseEntity<List<Treino>> findAll() {
        List<Treino> treinos = treinoService.findAll();
        return ResponseEntity.ok().body(treinos);
    }

    @GetMapping(value = "/{nome}")
    public ResponseEntity<Treino> findByNome(@PathVariable String nome) {
        Treino treino = treinoService.findByNome(nome);
        return ResponseEntity.ok().body(treino);
    }

    @GetMapping(value = "/aluno")
    public ResponseEntity<List<Treino>> getTreinos(){
        List<Treino> treinos = treinoService.findTreinoAluno();
        return ResponseEntity.ok().body(treinos);
    }

    @PostMapping(value = "/aluno/{id}")
    public ResponseEntity<Void> inserirTreinoAluno(@PathVariable Integer id){
        Treino treino = treinoService.findById(id);
        Aluno aluno = this.alunoService.retornaAlunoAutenticado();
        aluno.getTreinos().add(treino);

        System.out.println("ADICIONOU O TREINO COM SUCESSO NO ALUNO");
        this.alunoService.update(aluno.getId(), aluno);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/id/{id}")
    public ResponseEntity<List<Exercicio>> getExercicios(@PathVariable Integer id){
        Treino treino = treinoService.findById(id);
        List<Exercicio> exercicios= new ArrayList<>(treino.getExercicios());
        return ResponseEntity.ok().body(exercicios);
    }

    @PostMapping
    public ResponseEntity<Treino> insert(@RequestBody TreinoDTO treinoDTO) {
        Treino treino = treinoService.insert(treinoDTO);
        return ResponseEntity.ok().body(treino);
    }
}
