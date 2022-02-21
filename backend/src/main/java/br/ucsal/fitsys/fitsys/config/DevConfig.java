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
@Profile("dev")
public class DevConfig implements CommandLineRunner {

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
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//        Aluno aluno01 = new Aluno(null, "Davi Ramos", "782.328.725-00", 77.0, 1.76, "071991360435", "davi.lima@gmail.com", encoder.encode("123456"), sdf.parse("26/02/2000"));
//        Aluno aluno02 = new Aluno(null, "Davi Brito", "055.304.415-05", 65.0, 1.85, "0719885320431", "davi.brito@gmail.com", encoder.encode("123456"), sdf.parse("05/09/2000"));
//        Aluno aluno03 = new Aluno(null, "Bruno Antas", "114.744.070-05", 60.0, 1.90, "071992043879", "bruno.antas@gmail.com", encoder.encode("123456"), sdf.parse("15/01/1997"));
//        Aluno aluno04 = new Aluno(null, "Luana Santos", "471.751.560-90", 55.5, 1.60, "071988156450", "luana.santos@gmail.com", encoder.encode("123456"), sdf.parse("27/09/1975"));
//        Aluno aluno05 = new Aluno(null, "Sandra Lima", "335.118.890-06", 80.9, 1.73, "071995340689", "sandra.lima@gmail.com", encoder.encode("123456"), sdf.parse("07/06/1983"));
//
//        Instrutor instrutor01 = new Instrutor(null, "Manoel Silva", "545.744.980-72", "(71) 991350432", "manoel.silva@fitsys.br", encoder.encode("456789"), sdf.parse("04/03/1975"));
//        Instrutor instrutor02 = new Instrutor(null, "Jorge Meneguel", "738.941.200-37", "(71) 992350832", "jorge.meneguel@fitsys.br", encoder.encode("456789"), sdf.parse("08/05/1972"));
//        Instrutor instrutor03 = new Instrutor(null, "Lara Mendes", "468.938.710-90", "(71) 992350832", "lara.mendes@fitsys.br", encoder.encode("456789"), sdf.parse("10/01/1970"));
//        Instrutor instrutor04 = new Instrutor(null, "Robert Matos", "964.215.130-85", "(82) 3602-2506", "robert.matos@fitsys.br", encoder.encode("456789"), sdf.parse("02/04/1961"));
//        Instrutor instrutor05 = new Instrutor(null, "Bruna Oliveira", "638.344.380-14", "(63) 96703-3702", "bruna.oliveira@fitsys.br", encoder.encode("456789"), sdf.parse("02/05/1998"));
//        Instrutor instrutor06 = new Instrutor(null, "Vicente Borges", "138.758.080-96", "(28) 98081-5769", "vicente.borges@fitsys.br", encoder.encode("456789"), sdf.parse("15/02/1990"));
//        Instrutor instrutor07 = new Instrutor(null, "Riselda Paula", "168.975.550-45", "(22) 99748-5935", "riselda.paula@fitsys.br", encoder.encode("456789"), sdf.parse("04/03/1975"));
//        Instrutor instrutor08 = new Instrutor(null, "Angela Goulart", "276.294.830-40", "(61) 98501-6445", "angela.goulart@fitsys.br", encoder.encode("456789"), sdf.parse("21/01/1974"));
//        Instrutor instrutor09 = new Instrutor(null, "Rosangela Guedes", "357.803.450-90", "(75) 98258-0674", "rosangela.guedes@fitsys.br", encoder.encode("456789"), sdf.parse("31/08/1981"));
//        Instrutor instrutor10 = new Instrutor(null, "Rosana Rosa", "024.946.900-64", "(82) 99628-3713", "rosana.rosa@fitsys.br", encoder.encode("456789"), sdf.parse("13/10/2019"));
//
//        Endereco endereco01 = new Endereco(null, "Rua Magalhaes Peixoto", "449", null, "Nobrega", "Niterói", "RJ", "21036-000");
//        Endereco endereco02 = new Endereco(null, "Rua Cidade de Deus", "55", "Próximo a igreja Adventista", "Paripe", "Salvador", "BA", "12345-000");
//        Endereco endereco03 = new Endereco(null, "Rua Gaturamo", "125", "Próximo ao McDonald", "Gárcia", "Feira de Santana", "BA", "45678-000");
//        Endereco endereco04 = new Endereco(null, "Rua Eleonor", "320", "Fim da rua", "Lobato", "Salvador", "BA", "48678-000");
//        Endereco endereco05 = new Endereco(null, "Rua Pastor Tavares", "172", "AP 524", "Tabuleiro do Martins", "Maceió", "AL", "57081-007");
//        Endereco endereco06 = new Endereco(null, "Avenida Rio Amazonas", "159", "", "Fazendinha", "Macapá", "AP", "68911-040");
//        Endereco endereco07 = new Endereco(null, "Rua Viviane Siqueira do Nascimento", "89", "", "Senhor dos Passos", "Belo Horizonte", "MG", "31210-325");
//        Endereco endereco08 = new Endereco(null, "Alameda Colibrir", "54", "", "Boné Azul", "Macapá", "AP", "68909-608");
//        Endereco endereco09 = new Endereco(null, "Rua Olavo Brasil Filho", "78", "", "Jardim Floresta", "Boa Vista", "RR", "69312-133");
//        Endereco endereco10 = new Endereco(null, "Rua Pêra", "56", "", "Cohab", "Porto Velho", "RO", "76807-710");
//        Endereco endereco11 = new Endereco(null, "Rua Angico", "159", "", "Chico Mendes", "Rio Branco", "AC", "69902-687");
//        Endereco endereco12 = new Endereco(null, "Rua 6", "165", "", "Mondubim", "Fortaleza", "CE", "60767-660");
//        Endereco endereco13 = new Endereco(null, "Rua Mutum", "256", "", "Jardim Mathilde", "Campo Grande", "MS", "79113-325");
//        Endereco endereco14 = new Endereco(null, "1ª Travessa São Vicente", "606", "", "Lagoa Nova", "Natal", "RN", "59054-611");
//        Endereco endereco15 = new Endereco(null, "Avenida Quarta", "701", "", "Cabralzinho", "Macapá", "AP", "68906-855");
//
//        Presenca presenca01 = new Presenca(null, LocalDate.of(2021, 11, 13));
//        Presenca presenca02 = new Presenca(null, LocalDate.of(2021, 11, 11));
//        Presenca presenca03 = new Presenca(null, LocalDate.of(2021, 11, 1));
//        Presenca presenca04 = new Presenca(null, LocalDate.of(2021, 11, 3));
//        Presenca presenca05 = new Presenca(null, LocalDate.of(2021, 11, 15));
//        Presenca presenca06 = new Presenca(null, LocalDate.of(2021, 11, 18));
//        Presenca presenca07 = new Presenca(null, LocalDate.of(2021, 11, 21));
//        Presenca presenca08 = new Presenca(null, LocalDate.of(2021, 11, 25));
//        Presenca presenca09 = new Presenca(null, LocalDate.of(2021, 11, 28));
//        Presenca presenca10 = new Presenca(null, LocalDate.of(2021, 11, 30));
//
//        Exercicio exercicio01 = new Exercicio(null, "Agachamento com barra", "Conjuntos / repetição: 3 x 10\n" +
//                "Grupo muscular: Pernas, Nádegas\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/agachamentoBarra.gif?alt=media&token=6bae0a42-4ba4-4623-b62e-853f4291d64d");
//
//        Exercicio exercicio02 = new Exercicio(null, "Extensão de perna", "Conjuntos / repetição: 3 x 10\n" +
//                "Grupo muscular: Pernas\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/extensaoPerna.gif?alt=media&token=b72f109c-8453-473b-9e1b-016163143b6d");
//
//        Exercicio exercicio03 = new Exercicio(null, "Elevação da panturrilha em pé", "Conjuntos / reps: 3 x 10\n" +
//                "Grupo muscular: Peito\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/elevacaoPanturrilha.gif?alt=media&token=9ea88b0b-2396-444f-b285-5c136c942b8a");
//
//        Exercicio exercicio04 = new Exercicio(null, "Supino com Barra", "Conjuntos / repetição: 3 x 10\n" +
//                "Grupo muscular: Pernas\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/supinoBarra.gif?alt=media&token=f5320208-131c-47c7-9538-71a71c85c024");
//
//        Exercicio exercicio05 = new Exercicio(null, "Haltere único braço remada", "Séries / repetições: 3 x 10\n" +
//                "Grupo muscular: Costas\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/haltereBracoRemada.gif?alt=media&token=4469124e-07ea-43c5-8f38-f8340b0b230d");
//
//        Exercicio exercicio06 = new Exercicio(null, "Cabo final puxado para baixo", "Séries / repetições: 3 x 10\n" +
//                "Grupo muscular: Costas\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/caboFinalBaixo.gif?alt=media&token=915387ca-85a6-406a-9711-864de79d89d9");
//
//        Exercicio exercicio07 = new Exercicio(null, "Curvatura bíceps com barra", "Séries / repetição: 3 x 10\n" +
//                "Grupo muscular: Braços\n" +
//                "Duração: aprox. 4 min.",
//                "");
//
//        Exercicio exercicio08 = new Exercicio(null, "Curva de concentração com halteres", "Séries / repetição: 3 x 10\n" +
//                "Grupo muscular: Braços\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/curvaturaBiceps.gif?alt=media&token=8e48e53a-8a5f-40e7-994f-2ef139ea7302");
//
//        Exercicio exercicio09 = new Exercicio(null, "Extensão de tríceps", "Série / repetição: 3 x 10\n" +
//                "Grupo muscular: braços, ombros\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/extensaoTriceps.gif?alt=media&token=6694a35c-9eda-485b-90ad-5ace1c020fc2");
//        Exercicio exercicio10 = new Exercicio(null, "Haltere no ombro", "Conjuntos / reps: 3 x 10\n" +
//                "Grupo muscular: Ombros\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/haltereOmbro.gif?alt=media&token=7298fbf0-3b36-4b3b-a2a0-60fe59ed38dc");
//        Exercicio exercicio11 = new Exercicio(null, "Flexão", "Conjuntos / rep: 3 x 30 seg.\n" +
//                "Grupo muscular: Braços, Peito, Ombros\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/flexao.gif?alt=media&token=cd319a6b-f22a-4a26-9a0b-5a34a8882982");
//        Exercicio exercicio12 = new Exercicio(null, "Abdominal", "Conjuntos / rep: 3 x 30 seg.\n" +
//                "Grupo muscular: Abdômen\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/abdominal.gif?alt=media&token=f424030f-a9e7-45da-8706-bae2763b21c0");
//
//
////        ------------------ INICIO INTERMEDIÁRIO ---------------
//        Exercicio exercicioIntermediario01 = new Exercicio(null, "Levantamento terra com barra", "Séries / reps: 3 x 10\n" +
//                "Grupo muscular: Pernas, Nádegas\n" +
//                "Duração: aprox. 5 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/intermediario%2FlevantamentoTerraBarra.gif?alt=media&token=ac68b7a5-f15f-4ed2-8bf8-4a40b5ac3ad6");
//
////        AGACHAMENTO COM BARRA
//
//        Exercicio exercicioIntermediario02 = new Exercicio(null, "Barra de ombros", "Séries / reps: 3 x 10\n" +
//                "Grupo muscular: Ombros\n" +
//                "Duração: aprox. 5 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/intermediario%2FbarraOmbro.gif?alt=media&token=0b151360-691a-492b-a629-d2c2cdb85442");
//
////        SUPINO BARRA
//
//        Exercicio exercicioIntermediario03 = new Exercicio(null, "Supino torácico com halteres", "Séries / repetições: 3 x 10\n" +
//                "Grupo muscular: Peito\n" +
//                "Duração: aprox. 5 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/intermediario%2FsupinoToracicoHaltere.gif?alt=media&token=51734004-6851-4b9f-bfc4-f37a5d6c4e82");
//
////        CURVATURA BÍCEPS COM BARRA
////        EXTENSÃO TRICEPS
//
//        Exercicio exercicioIntermediario04 = new Exercicio(null, "Deadlift", "Séries / repetições: 3 x 10\n" +
//                "Grupo muscular: Costas\n" +
//                "Duração: aprox. 5 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/intermediario%2Fdeadlift.gif?alt=media&token=95434aa8-ea21-48f5-a056-4e8bd5f94663");
//
//        Exercicio exercicioIntermediario05 = new Exercicio(null, "Haltere traseiro mosca", "Séries / repetições: 3 x 10\n" +
//                "Grupo muscular: Costas\n" +
//                "Duração: aprox. 5 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/intermediario%2FhaltereTraseiroMosca.gif?alt=media&token=7e794694-55af-40d3-88c2-e677201e124e");
//
////        HALTERE OMBRO
//
//        Exercicio exercicioIntermediario06 = new Exercicio(null, "Barra", "Série / repetição: 3 x 10\n" +
//                "Grupo muscular: braços, costas, ombros\n" +
//                "Duração: aprox. 5 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/intermediario%2Fbarra.gif?alt=media&token=9d459169-36eb-40e8-9519-3eeb1d6578b6");
//
//        Exercicio exercicioIntermediario07 = new Exercicio(null, "Prancha", "Séries / repetições: 3 x 1 min.\n" +
//                "Grupo muscular: Abdômen\n" +
//                "Duração: aprox. 5 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/intermediario%2Fprancha.gif?alt=media&token=0952fcd0-f5a3-49ff-8b72-4e0516be9504");
//
//
////        ------------------ INICIO AVANÇADO ---------------
//        Exercicio exercicioAvancado01 = new Exercicio(null, "Agachamento com halteres", "Séries / reps: 3 x 10\n" +
//                "Grupo muscular: Pernas, Nádegas\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/avancado%2FagachamentoHaltere.gif?alt=media&token=3cce2c45-2d7d-4362-9216-679aef303fce");
//
////       ELEVAÇÃO PANTURRILHA
////       FLEXÃO
////       CONCENTRAÇÃO HALTERES
////       HALTERE OMBRO
////       EXTENSÃO DE TRÍCEPS COM HALTERES
//
//        Exercicio exercicioAvancado02 = new Exercicio(null, "Haltere tríceps retrocesso", "Séries / repetição: 3 x 10\n" +
//                "Grupo muscular: Braços\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/avancado%2FhaltereTricepsRetro.gif?alt=media&token=449bdd9c-1c56-4e53-be5a-709fb592e4ae");
//
////       HALTERE TRASEIRO MOSCA
//
//        Exercicio exercicioAvancado03 = new Exercicio(null, "Ponte", "Conjuntos / rep: 3 x 30 seg.\n" +
//                "Grupo muscular: Nádegas, Abdômen, Costas\n" +
//                "Duração: aprox. 4 min.",
//                "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/avancado%2Fponte.gif?alt=media&token=439e0de1-e0e7-4bf1-a6e3-c373048f3e7b");
//
////       ABDOMINAL
////       PRANCHA
//
//
//        Treino treino01 = new Treino(null, "Iniciante", "É um programa eficaz com doze exercícios compostos. Isso permite que você atinja um grande número de músculos pequenos e grandes durante um treino.");
//        Treino treino02 = new Treino(null, "Intermediário", "Você pode realizar essa programação de corpo inteiro, com uma abordagem eficiente em 1 hora e 20 minutos (incluindo um aquecimento de 10 minutos).");
//        Treino treino03 = new Treino(null, "Avançado", "A programação contém os melhores exercícios para o desenvolvimento muscular e você pode executá-los sem equipamento de fitness.");
//
//        treino01.getExercicios().addAll(Arrays.asList(exercicio01, exercicio02, exercicio03, exercicio04, exercicio05, exercicio06, exercicio07, exercicio08, exercicio09, exercicio10, exercicio11, exercicio12));
//        treino02.getExercicios().addAll(Arrays.asList(exercicioIntermediario01, exercicio01, exercicioIntermediario02, exercicio04, exercicioIntermediario03, exercicio07, exercicio09, exercicioIntermediario04, exercicioIntermediario05, exercicio10, exercicioIntermediario06, exercicioIntermediario07));
//        treino03.getExercicios().addAll(Arrays.asList(exercicioAvancado01, exercicio03, exercicio11, exercicio08, exercicio10, exercicio09, exercicioAvancado02, exercicioIntermediario05, exercicioAvancado03, exercicio12, exercicioIntermediario07));
//        treinoRepository.saveAll(Arrays.asList(treino01, treino02, treino03));
//
//        exercicio01.addTipoTreino(TipoTreino.PERNAS);
//        exercicio01.addTipoTreino(TipoTreino.GLUTEOS);
//        exercicio02.addTipoTreino(TipoTreino.PERNAS);
//        exercicio03.addTipoTreino(TipoTreino.PEITO);
//        exercicio04.addTipoTreino(TipoTreino.PERNAS);
//        exercicio05.addTipoTreino(TipoTreino.COSTAS);
//        exercicio06.addTipoTreino(TipoTreino.COSTAS);
//        exercicio07.addTipoTreino(TipoTreino.BRACO);
//        exercicio08.addTipoTreino(TipoTreino.BRACO);
//        exercicio09.addTipoTreino(TipoTreino.BRACO);
//        exercicio09.addTipoTreino(TipoTreino.OMBROS);
//        exercicio10.addTipoTreino(TipoTreino.OMBROS);
//        exercicio11.addTipoTreino(TipoTreino.OMBROS);
//        exercicio11.addTipoTreino(TipoTreino.PEITO);
//        exercicio11.addTipoTreino(TipoTreino.BRACO);
//        exercicio12.addTipoTreino(TipoTreino.PEITO);
//
//        exercicioIntermediario01.addTipoTreino(TipoTreino.PERNAS);
//        exercicioIntermediario01.addTipoTreino(TipoTreino.GLUTEOS);
//        exercicioIntermediario02.addTipoTreino(TipoTreino.OMBROS);
//        exercicioIntermediario03.addTipoTreino(TipoTreino.PEITO);
//        exercicioIntermediario04.addTipoTreino(TipoTreino.COSTAS);
//        exercicioIntermediario05.addTipoTreino(TipoTreino.COSTAS);
//        exercicioIntermediario06.addTipoTreino(TipoTreino.COSTAS);
//        exercicioIntermediario06.addTipoTreino(TipoTreino.OMBROS);
//        exercicioIntermediario06.addTipoTreino(TipoTreino.BRACO);
//        exercicioIntermediario07.addTipoTreino(TipoTreino.PEITO);
//
//        exercicioAvancado01.addTipoTreino(TipoTreino.PERNAS);
//        exercicioAvancado01.addTipoTreino(TipoTreino.GLUTEOS);
//        exercicioAvancado02.addTipoTreino(TipoTreino.BRACO);
//        exercicioAvancado03.addTipoTreino(TipoTreino.COSTAS);
//        exercicioAvancado03.addTipoTreino(TipoTreino.ABDOMEN);
//        exercicioAvancado03.addTipoTreino(TipoTreino.GLUTEOS);
//
//
//        exercicio01.getTreinos().addAll(Arrays.asList(treino01, treino02));
//        exercicio02.getTreinos().add(treino01);
//        exercicio03.getTreinos().addAll(Arrays.asList(treino01, treino03));
//        exercicio04.getTreinos().addAll(Arrays.asList(treino01, treino02));
//        exercicio05.getTreinos().add(treino01);
//        exercicio06.getTreinos().add(treino01);
//        exercicio07.getTreinos().add(treino01);
//        exercicio08.getTreinos().addAll(Arrays.asList(treino01, treino03));
//        exercicio09.getTreinos().addAll(Arrays.asList(treino01, treino02, treino03));
//        exercicio10.getTreinos().addAll(Arrays.asList(treino01, treino03));
//        exercicio11.getTreinos().addAll(Arrays.asList(treino01, treino03));
//        exercicio12.getTreinos().addAll(Arrays.asList(treino01, treino03));
//        exercicioIntermediario01.getTreinos().add(treino02);
//        exercicioIntermediario02.getTreinos().add(treino02);
//        exercicioIntermediario03.getTreinos().add(treino02);
//        exercicioIntermediario04.getTreinos().add(treino02);
//        exercicioIntermediario05.getTreinos().addAll(Arrays.asList(treino02, treino03));
//        exercicioIntermediario06.getTreinos().add(treino02);
//        exercicioIntermediario07.getTreinos().addAll(Arrays.asList(treino02, treino03));
//        exercicioAvancado01.getTreinos().add(treino03);
//        exercicioAvancado02.getTreinos().add(treino03);
//        exercicioAvancado03.getTreinos().add(treino03);
//
//        exercicioRepository.saveAll(Arrays.asList(exercicio01, exercicio02, exercicio03, exercicio04, exercicio05, exercicio06, exercicio07, exercicio08, exercicio09, exercicio10, exercicio11, exercicio12,
//                exercicioIntermediario01, exercicioIntermediario02, exercicioIntermediario03, exercicioIntermediario04, exercicioIntermediario05, exercicioIntermediario06, exercicioIntermediario07, exercicioAvancado01,
//                exercicioAvancado02, exercicioAvancado03));
//
//        instrutor01.setEndereco(endereco01);
//        instrutor02.setEndereco(endereco02);
//        instrutor03.setEndereco(endereco03);
//        instrutor04.setEndereco(endereco04);
//        instrutor05.setEndereco(endereco05);
//        instrutor06.setEndereco(endereco06);
//        instrutor07.setEndereco(endereco07);
//        instrutor08.setEndereco(endereco08);
//        instrutor09.setEndereco(endereco09);
//        instrutor10.setEndereco(endereco10);
//
//        aluno01.setEndereco(endereco11);
//        aluno02.setEndereco(endereco12);
//        aluno03.setEndereco(endereco13);
//        aluno04.setEndereco(endereco14);
//        aluno05.setEndereco(endereco15);
//
//        aluno01.getPresencas().add(presenca01);
//        presenca01.setAluno(aluno01);
//        aluno01.getPresencas().add(presenca02);
//        presenca02.setAluno(aluno01);
//
//        aluno02.getPresencas().add(presenca03);
//        presenca03.setAluno(aluno02);
//        aluno02.getPresencas().add(presenca04);
//        presenca03.setAluno(aluno02);
//
//        aluno03.getPresencas().add(presenca05);
//        presenca05.setAluno(aluno03);
//        aluno03.getPresencas().add(presenca06);
//        presenca06.setAluno(aluno03);
//
//        aluno04.getPresencas().add(presenca07);
//        presenca07.setAluno(aluno04);
//        aluno04.getPresencas().add(presenca08);
//        presenca08.setAluno(aluno04);
//
//        aluno05.getPresencas().add(presenca09);
//        presenca09.setAluno(aluno05);
//        aluno05.getPresencas().add(presenca10);
//        presenca10.setAluno(aluno05);
//
//        instrutorRepository.saveAll(Arrays.asList(instrutor01, instrutor02, instrutor03, instrutor04, instrutor05, instrutor06, instrutor07, instrutor08, instrutor09, instrutor10));
//        alunoRepository.saveAll(Arrays.asList(aluno01, aluno02, aluno03, aluno04, aluno05));
//        presencaRepository.saveAll(Arrays.asList(presenca01,presenca02,presenca03,presenca04,presenca05,presenca06,presenca07,presenca08,presenca09,presenca10));
//        enderecoRepository.saveAll(Arrays.asList(endereco01, endereco02, endereco03, endereco04, endereco05, endereco06, endereco07, endereco08, endereco09, endereco10, endereco11, endereco12, endereco13, endereco14, endereco15));

    }
}
