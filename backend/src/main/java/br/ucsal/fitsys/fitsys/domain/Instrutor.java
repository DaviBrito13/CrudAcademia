package br.ucsal.fitsys.fitsys.domain;

import br.ucsal.fitsys.fitsys.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Instrutor implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;

    private String telefone;

    private String urlImage = "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/profile-g21c1c5a43_1920.png?alt=media&token=0d5ca401-7e6a-4081-bbe0-22fb9efe9306";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Endereco endereco;

    private String email;

    @JsonIgnore
    private String senha;

    private final Integer perfil = Perfil.INSTRUTOR.getCod();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dataNascimento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT")
    private final LocalDateTime momentoInscricao = LocalDateTime.now();

    @JsonIgnore
    @OneToMany(mappedBy = "instrutor")
    private List<Aluno> alunos = new ArrayList<>();

    public Instrutor() {
    }

    public Instrutor(Integer id, String nome, String cpf, String telefone, String email, String senha, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
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

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
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

        Instrutor instrutor = (Instrutor) o;

        return getId() != null ? getId().equals(instrutor.getId()) : instrutor.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
