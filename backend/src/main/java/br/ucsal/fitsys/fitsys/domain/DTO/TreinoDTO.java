package br.ucsal.fitsys.fitsys.domain.DTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class TreinoDTO {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private List<Integer> exercicios;

    public TreinoDTO() { }

    public TreinoDTO(Integer id, String nome, String descricao, List<Integer> exercicios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.exercicios = exercicios;
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

    public List<Integer> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Integer> exercicios) {
        this.exercicios = exercicios;
    }
}
