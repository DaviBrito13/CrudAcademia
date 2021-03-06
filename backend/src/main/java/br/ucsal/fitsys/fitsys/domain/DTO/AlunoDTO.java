package br.ucsal.fitsys.fitsys.domain.DTO;

import br.ucsal.fitsys.fitsys.domain.Aluno;

import java.io.Serializable;

public class AlunoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nome;

    private String email;

    private String cpf;

    public AlunoDTO() {
    }

    public AlunoDTO(Aluno obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.cpf = obj.getCpf();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
