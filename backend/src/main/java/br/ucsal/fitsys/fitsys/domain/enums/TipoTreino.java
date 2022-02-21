package br.ucsal.fitsys.fitsys.domain.enums;

public enum TipoTreino {

    CARDIO(1, "CARDIO"),
    BRACO(2, "BRAÇO"),
    ABDOMEN(3, "ABDOMEN"),
    PERNAS(4, "PERNAS"),
    GLUTEOS(5, "GLUTEOS"),
    PEITO(6, "PEITO"),
    COSTAS(7, "COSTAS"),
    OMBROS(8, "OMBROS");

    private final int cod;
    private final String nome;

    TipoTreino(int cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public static TipoTreino toEnum(Integer cod) {
        if (cod == null) return null;

        for (TipoTreino x : TipoTreino.values()) {
            if (cod.equals(x.getCod())) return x;
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
