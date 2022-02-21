package br.ucsal.fitsys.fitsys.domain;

import br.ucsal.fitsys.fitsys.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Aluno implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private Double peso;
    private Double altura;
    private String imc;
    private String telefone;
    private String urlImage = "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/blank-profile-picture-g0a210a10e_1280.png?alt=media&token=6160b86f-11e5-400f-a95e-7d2a64f660f8";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Endereco endereco;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String senha;

    private final Integer perfil = Perfil.ALUNO.getCod();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT")
    private Date dataNascimento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "GMT")
    private final LocalDateTime momentoInscricao = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    @JoinColumn()
    private Instrutor instrutor;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno")
    private List<Presenca> presencas = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    public List<Treino> treinos = new ArrayList<>();

    public Aluno() {
    }

    public Aluno(Integer id, String nome, String cpf, Double peso, Double altura, String telefone, String email, String senha, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.peso = peso;
        this.altura = altura;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.imc = calculaImc(peso, altura);
        this.dataNascimento = dataNascimento;
    }

    public String calculaImc(Double peso, Double altura) {
        if (peso == null || altura == null) {
            return "0";
        }
        Double valor = peso / (Math.pow(altura, 2));
        DecimalFormat formatado = new DecimalFormat("#.#");
        return formatado.format(valor);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
        this.imc = calculaImc(peso, this.altura);
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
        this.imc = calculaImc(altura, this.altura);
    }

    public String getImc() {
        return imc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getMomentoInscricao() {
        return momentoInscricao;
    }

    public Perfil getPerfil() {
        return Perfil.toEnum(perfil);
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public List<Presenca> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<Presenca> presencas) {
        this.presencas = presencas;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluno aluno = (Aluno) o;

        return getId() != null ? getId().equals(aluno.getId()) : aluno.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}