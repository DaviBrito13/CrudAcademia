package br.ucsal.fitsys.fitsys.domain.DTO;

import java.io.Serializable;
import java.util.List;

public class ExercicioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String titulo;
    private String descricao;
    private String urlImage;
    private List<Integer> tiposTreinos;

    public ExercicioDTO() {
    }

    public ExercicioDTO(Integer id, String titulo, String descricao, String urlImage, List<Integer> tiposTreinos) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.urlImage = urlImage;
        this.tiposTreinos = tiposTreinos;
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

    public List<Integer> getTiposTreinos() {
        return tiposTreinos;
    }

    public void setTiposTreinos(List<Integer> tiposTreinos) {
        this.tiposTreinos = tiposTreinos;
    }
}
