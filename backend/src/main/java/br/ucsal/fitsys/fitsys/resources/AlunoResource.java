package br.ucsal.fitsys.fitsys.resources;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.domain.DTO.AlunoNewDTO;
import br.ucsal.fitsys.fitsys.domain.Treino;
import br.ucsal.fitsys.fitsys.security.UserSS;
import br.ucsal.fitsys.fitsys.services.AlunoService;
import br.ucsal.fitsys.fitsys.services.FirebaseService;
import br.ucsal.fitsys.fitsys.services.TreinoService;
import br.ucsal.fitsys.fitsys.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService service;

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public ResponseEntity<Aluno> insert(@RequestBody AlunoNewDTO alunoDTO) {
        Aluno aluno = service.fromDTO(alunoDTO);
        Treino treino = treinoService.findById(alunoDTO.getObjetivo());
        aluno.setTreinos(List.of(treino));
        aluno = service.insert(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(aluno);
    }

    @PostMapping(value = "/profilePic")
    public ResponseEntity<Aluno> insertProfilePic(@RequestParam(value = "imagem") MultipartFile imagem) {
        String urlImagem = firebaseService.upload(imagem, "profileAluno");
        UserSS user = UserService.authenticated();
        Aluno aluno = this.service.findByEmail(user.getUsername());
        aluno.setUrlImage(urlImagem);
        this.service.update(aluno.getId(), aluno);
        return ResponseEntity.ok().body(aluno);
    }

    @GetMapping(value = "/verificaAluno/{email}")
    public ResponseEntity<Integer> verificaAluno(@PathVariable String email) {
        boolean resultado = service.verificaAluno(email);
        int valor = 1;
        if (resultado)
            valor = 0;
        return ResponseEntity.ok().body(valor);
    }

    // PreAuthorize = Bloqueia rota para determinado perfil
    @PreAuthorize("hasAnyRole('INSTRUTOR')")
    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
        Aluno aluno = service.findById(id);
        return ResponseEntity.ok().body(aluno);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<Aluno> findByEmail(@RequestParam(value = "value") String email) {
        Aluno obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/instrutorId")
    public ResponseEntity<Integer> getInstrutorId() {
        Integer id = service.getInstrutorId();
        return ResponseEntity.ok().body(id);
    }
    
    @GetMapping(value = "/removeTreino/{id}")
    public ResponseEntity<Void> removeTreino(@PathVariable() Integer id){
        service.removeTreino(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Void> selectInstrutor(@PathVariable Integer id) {
        service.selectInstrutor(id);
        return ResponseEntity.noContent().build();
    }

    // PreAuthorize = Bloqueia rota para determinado perfil
    @PreAuthorize("hasAnyRole('INSTRUTOR')")
    @DeleteMapping(value = "/email")
    public ResponseEntity<Void> delete(@RequestParam(value = "value") String email) {
        service.delete(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody Aluno aluno) {
        aluno = service.update(id, aluno);
        return ResponseEntity.ok().body(aluno);
    }
}
