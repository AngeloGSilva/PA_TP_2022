package pt.isec.pa.apoio_poe.model.data;

import java.util.Objects;

public class Proposta {
    String cod_ID = null;
    String titulo = null;
    String codigo_Aluno = null;
    String nomeDocente = null;
    String ramo = null;

    public String getCodigo_Aluno() {
        return codigo_Aluno;
    }

    public String getCod_ID() {
        return cod_ID;
    }

    //geral
    public Proposta(String cod_ID, String titulo, String codigo_Aluno, String nomeDocente, String ramo) {
        this.cod_ID = cod_ID;
        this.titulo = titulo;
        this.codigo_Aluno = codigo_Aluno;
        this.nomeDocente = nomeDocente;
        this.ramo = ramo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return Objects.equals(cod_ID, proposta.cod_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod_ID);
    }

    @Override
    public String toString() {
        return "Codigo Id ='" + cod_ID + '\'' +
                ", Titulo ='" + titulo + '\'' +
                ", Codigo Aluno ='" + codigo_Aluno + '\'' +
                ", Codigo Docente ='" + nomeDocente + '\'' +
                ", Ramo ='" + ramo + '\'' +
                "\n";
    }
}
