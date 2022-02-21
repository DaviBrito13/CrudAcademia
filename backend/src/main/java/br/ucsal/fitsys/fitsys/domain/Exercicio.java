package br.ucsal.fitsys.fitsys.domain;

import br.ucsal.fitsys.fitsys.domain.enums.TipoTreino;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Exercicio {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private String urlImage;

    @ManyToMany()
    @JsonIgnore
    Set<Treino> treinos = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tipoTreinos")
    Set<Integer> tipoTreinos = new HashSet<>();

    public Exercicio() {
    }

    public Exercicio(Integer id, String titulo, String descricao, String urlImage) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.urlImage = urlImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Set<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(Set<Treino> treinos) {
        this.treinos = treinos;
    }

    public Set<TipoTreino> getTipoTreinos() {
        return tipoTreinos.stream().map(TipoTreino::toEnum).collect(Collectors.toSet());
    }

    public void addTipoTreino(TipoTreino treino) {
        tipoTreinos.add(treino.getCod());
    }
}
