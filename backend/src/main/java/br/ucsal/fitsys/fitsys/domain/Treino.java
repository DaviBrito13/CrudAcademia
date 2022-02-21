package br.ucsal.fitsys.fitsys.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Treino {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;

    @ManyToMany(mappedBy = "treinos")
    public Set<Exercicio> exercicios = new HashSet<>();

    public Treino() {
    }

    public Treino(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(Set<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }
}
