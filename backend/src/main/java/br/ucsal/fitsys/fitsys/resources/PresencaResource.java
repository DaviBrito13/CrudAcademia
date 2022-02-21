package br.ucsal.fitsys.fitsys.resources;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.domain.Presenca;
import br.ucsal.fitsys.fitsys.repositories.PresencaRepository;
import br.ucsal.fitsys.fitsys.security.UserSS;
import br.ucsal.fitsys.fitsys.services.AlunoService;
import br.ucsal.fitsys.fitsys.services.UserService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/presencas")
public class PresencaResource {

    @Autowired
    PresencaRepository repository;

    @Autowired
    AlunoService alunoService;

    @GetMapping("/buscaPresenca")
    @JsonSerialize(using = LocalDateSerializer.class)
    public Iterable<Presenca> presencas(@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate comeco, @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate termino) {
        UserSS userSS = UserService.authenticated();
        Aluno aluno = alunoService.findByEmail(userSS.getUsername());
        return repository.findBetween(comeco, termino, aluno.getId());
    }

    @PostMapping("/marcaPresenca")
    @JsonSerialize(using = LocalDateSerializer.class)
    @Transactional
    Presenca marcaPresenca(@RequestBody ParametrosNovaPresenca params) {
        Presenca p = new Presenca();
        p.setData(params.data);
        UserSS userSS = UserService.authenticated();
        Aluno aluno = alunoService.findByEmail(userSS.getUsername());
        List<Presenca> presencasAtuais = aluno.getPresencas();
        aluno.getPresencas().add(p);
        alunoService.update(aluno.getId(), aluno);
        p.setAluno(aluno);
        return repository.save(p);
    }

    public static class ParametrosNovaPresenca {
        public LocalDate data;
    }

}
