package pt.isec.pa.apoio_poe.model.data;

import java.util.Objects;

public class Proposta {
    String cod_ID = null;
    String titulo = null;
    String codigo_Aluno = null;
    String codigo_Docente = null;
    String ramo = null;

    //geral
    public Proposta(String cod_ID, String titulo, String codigo_Aluno, String codigo_Docente, String ramo) {
        this.cod_ID = cod_ID;
        this.titulo = titulo;
        this.codigo_Aluno = codigo_Aluno;
        this.codigo_Docente = codigo_Docente;
        this.ramo = ramo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return Objects.equals(cod_ID, proposta.cod_ID) && Objects.equals(titulo, proposta.titulo) && Objects.equals(codigo_Aluno, proposta.codigo_Aluno) && Objects.equals(codigo_Docente, proposta.codigo_Docente) && Objects.equals(ramo, proposta.ramo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod_ID, titulo, codigo_Aluno, codigo_Docente, ramo);
    }
}
