package br.ucsal.fitsys.fitsys.config;

import br.ucsal.fitsys.fitsys.domain.*;
import br.ucsal.fitsys.fitsys.domain.enums.TipoTreino;
import br.ucsal.fitsys.fitsys.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Aluno aluno01 = new Aluno(null, "Davi Ramos", "782.328.725-00", 77.0, 1.76, "071991360435", "davi.lima@gmail.com", encoder.encode("123456"), sdf.parse("26/02/2000"));
        Aluno aluno02 = new Aluno(null, "Davi Brito", "055.304.415-05", 65.0, 1.85, "071981360435", "davi.brito@gmail.com", encoder.encode("456789"), sdf.parse("05/09/2000"));

        Instrutor instrutor01 = new Instrutor(null, "Manoel Silva", "545.744.980-72", "071991350432", "manoel.silva@fitsys.br", encoder.encode("0159"), sdf.parse("04/03/1975"));
        Instrutor instrutor02 = new Instrutor(null, "Jorge Meneguel", "738.941.200-37", "071992350832", "jorge.meneguel@fitsys.br", encoder.encode("9510"), sdf.parse("08/05/1972"));

        Endereco endereco01 = new Endereco(null, "Rua Magalhaes Peixoto", "449", null, "Nobrega", "Niterói", "RJ", "21036-000");
        Endereco endereco02 = new Endereco(null, "Rua Cidade de Deus", "55", "Próximo a igreja Adventista", "Paripe", "Salvador", "BA", "12345-000");
        Endereco endereco03 = new Endereco(null, "Rua Gaturamo", "125", "Próximo ao McDonald", "Gárcia", "Feira de Santana", "BA", "45678-000");
        Endereco endereco04 = new Endereco(null, "Rua Eleonor", "320", "Fim da rua", "Lobato", "Salvador", "BA", "48678-000");

        Presenca presenca = new Presenca(null, LocalDate.of(2021, 2, 26));

        Exercicio exercicio01 = new Exercicio(null, "Deadlift", "Pegue a barra e puxe", "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/exerciciobdcd6258-acfc-4c3d-9123-57c7a760d94f.gif?alt=media&token=4673e97c-ed14-46fd-8a4b-75dfdc86358b");
        Exercicio exercicio02 = new Exercicio(null, "Agachamento com barra", "Pegue a barra e faça leves agachamentos", "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/exerciciobdcd6258-acfc-4c3d-9123-57c7a760d94f.gif?alt=media&token=4673e97c-ed14-46fd-8a4b-75dfdc86358b");
        Exercicio exercicio03 = new Exercicio(null, "Apoio", "Pegue a barra e faça leves agachamentos", "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/exerciciobdcd6258-acfc-4c3d-9123-57c7a760d94f.gif?alt=media&token=4673e97c-ed14-46fd-8a4b-75dfdc86358b");
        Exercicio exercicio04 = new Exercicio(null, "Agachamento com barra invertida", "Pegue a barra e faça leves agachamentos", "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/exerciciobdcd6258-acfc-4c3d-9123-57c7a760d94f.gif?alt=media&token=4673e97c-ed14-46fd-8a4b-75dfdc86358b");


        Treino treino01 = new Treino(null,"Iniciante","Conjunto de exercicios físicos para iniciantes");
        Treino treino02 = new Treino(null, "Intermediário", "Conjunto de exercicios físicos para iniciantes");
        Treino treino03 = new Treino(null, "Avançado", "Conjunto de exercicios físicos para iniciantes");

        treino01.getExercicios().addAll(Arrays.asList(exercicio01,exercicio02));
        treino02.getExercicios().addAll(Arrays.asList(exercicio03, exercicio04));
        treinoRepository.saveAll(Arrays.asList(treino01,treino02,treino03));

        exercicio01.addTipoTreino(TipoTreino.BRACO);
        exercicio02.addTipoTreino(TipoTreino.GLUTEOS);

        exercicio01.getTreinos().add(treino01);
        exercicio02.getTreinos().add(treino01);
        exercicio03.getTreinos().add(treino02);
        exercicio04.getTreinos().add(treino02);
        exercicioRepository.saveAll(Arrays.asList(exercicio01, exercicio02, exercicio03, exercicio04));

        instrutor01.setEndereco(endereco03);
        instrutor02.setEndereco(endereco04);

        instrutor01.getAlunos().add(aluno01);
        instrutor02.getAlunos().add(aluno02);

        aluno01.setEndereco(endereco01);
        aluno02.setEndereco(endereco02);

        aluno01.setInstrutor(instrutor01);
        aluno02.setInstrutor(instrutor02);

        aluno01.getPresencas().add(presenca);
        presenca.setAluno(aluno01);

        aluno01.setTreinos(Arrays.asList(treino01,treino02));

        instrutorRepository.saveAll(Arrays.asList(instrutor01, instrutor02));
        alunoRepository.saveAll(Arrays.asList(aluno01, aluno02));
        presencaRepository.save(presenca);
        enderecoRepository.saveAll(Arrays.asList(endereco01, endereco02, endereco03, endereco04));

    }
}
