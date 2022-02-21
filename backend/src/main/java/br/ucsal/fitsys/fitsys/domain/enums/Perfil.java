package br.ucsal.fitsys.fitsys.domain.enums;

public enum Perfil {

    INSTRUTOR(1, "ROLE_INSTRUTOR"),
    ALUNO(2, "ROLE_ALUNO");

    private final int cod;
    private final String descricao;

    Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) return null;

        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) return x;
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}