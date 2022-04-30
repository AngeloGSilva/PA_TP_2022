package pt.isec.pa.apoio_poe.model.data;

import java.util.Objects;

public class Proposta {
    String cod_ID = null;
    String titulo = null;
    Long codigo_Aluno = null;
    String email_Docente = null;
    String empresa = null;
    String ramo = null;

    public Long getCodigo_Aluno() {
        return codigo_Aluno;
    }

    public String getCod_ID() {
        return cod_ID;
    }

    //geral
    public Proposta(String cod_ID, String titulo, Long codigo_Aluno, String email_Docente, String ramo,String empresa) {
        this.cod_ID = cod_ID;
        this.titulo = titulo;
        this.codigo_Aluno = codigo_Aluno;
        this.email_Docente = email_Docente;
        this.ramo = ramo;
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmail_Docente() {
        return email_Docente;
    }

    public String getRamo() {
        return ramo;
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
        return "\nProposta: " +
                " Codigo Id : " + cod_ID +
                " Titulo : " + titulo +
                " Codigo Aluno : " + codigo_Aluno +
                " Codigo Docente: " + email_Docente +
                " Ramo: " + ramo +
                "\n";
    }
}
