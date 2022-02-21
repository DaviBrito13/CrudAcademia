package br.ucsal.fitsys.fitsys.resources;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.domain.DTO.InstrutorDTO;
import br.ucsal.fitsys.fitsys.domain.Instrutor;
import br.ucsal.fitsys.fitsys.security.UserSS;
import br.ucsal.fitsys.fitsys.services.FirebaseService;
import br.ucsal.fitsys.fitsys.services.InstrutorService;
import br.ucsal.fitsys.fitsys.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/instrutores")
public class InstrutorResource {

    @Autowired
    private InstrutorService service;

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping
    public ResponseEntity<List<Instrutor>> findAll() {
        List<Instrutor> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Instrutor> findById(@PathVariable Integer id) {
        Instrutor instrutor = service.findById(id);
        return ResponseEntity.ok().body(instrutor);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<Instrutor> findByEmail(@RequestParam(value = "value") String email){
        Instrutor obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/alunos")
    public ResponseEntity<List<Aluno>> getAlunos(){
        UserSS user = UserService.authenticated();
        List<Aluno> alunos = service.getAlunos(user.getUsername());
        return ResponseEntity.ok().body(alunos);
    }

    @PostMapping
    public ResponseEntity<Instrutor> insert(@RequestBody InstrutorDTO instrutorDTO) {
        Instrutor instrutor = service.fromDTO(instrutorDTO);
        service.insert(instrutor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(instrutor.getId()).toUri();
        return ResponseEntity.created(uri).body(instrutor);
    }

    @PostMapping(value = "/profilePic")
    public ResponseEntity<Instrutor> insertProfilePic(@RequestParam(value = "imagem") MultipartFile imagem){
        String urlImagem = firebaseService.upload(imagem, "profileInstrutor");
        UserSS user = UserService.authenticated();
        Instrutor instrutor = this.service.findByEmail(user.getUsername());
        instrutor.setUrlImage(urlImagem);
        this.service.update(instrutor.getId(), instrutor);
        return ResponseEntity.ok().body(instrutor);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/removeAluno/{id}")
    public ResponseEntity<Void> removeAluno(@PathVariable Integer id){
        service.removeAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Instrutor> update(@PathVariable Integer id, @RequestBody Instrutor instrutor){
        instrutor = service.update(id, instrutor);
        return ResponseEntity.ok().body(instrutor);
    }
}
