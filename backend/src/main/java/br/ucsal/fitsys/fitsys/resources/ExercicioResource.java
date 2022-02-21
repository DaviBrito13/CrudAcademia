package br.ucsal.fitsys.fitsys.resources;

import br.ucsal.fitsys.fitsys.domain.DTO.ExercicioDTO;
import br.ucsal.fitsys.fitsys.domain.Exercicio;
import br.ucsal.fitsys.fitsys.services.ExercicioService;
import br.ucsal.fitsys.fitsys.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/exercicios")
public class ExercicioResource {

    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping()
    public ResponseEntity<List<Exercicio>> findAll() {
        List<Exercicio> exercicios = exercicioService.findAll();
        return ResponseEntity.ok().body(exercicios);
    }

    @GetMapping(value = "/{titulo}")
    public ResponseEntity<Exercicio> findByTitulo(@PathVariable String titulo) {
        Exercicio exercicio = exercicioService.findByTitulo(titulo);
        return ResponseEntity.ok().body(exercicio);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        exercicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping()
//    public ResponseEntity<Exercicio> insert(@RequestParam(value = "imagem") MultipartFile imagem, @RequestParam(value = "titulo") String titulo, @RequestParam(value = "descricao") String descricao, @RequestParam(value = "tipo") List<Integer> tipoTreino) {
//        String urlImagem = firebaseService.upload(imagem, "exercicio");
//        Exercicio exercicio = exercicioService.insert(titulo, descricao, urlImagem, tipoTreino);
//        return ResponseEntity.ok().body(exercicio);
//    }

    @PostMapping()
    public ResponseEntity<Exercicio> insert(@RequestBody ExercicioDTO exercicio) {
        Exercicio response = exercicioService.insert(exercicio);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Exercicio> update(@PathVariable Integer id, @RequestBody Exercicio exercicio){
        exercicio = exercicioService.update(id,exercicio);
        return ResponseEntity.ok().body(exercicio);
    }


}
