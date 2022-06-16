package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

public class Proposta implements Serializable {
    private String cod_ID = null;
    private String titulo = null;
    private Long codigo_Aluno = null;
    private String email_Docente = null;
    private String empresa = null;
    private String ramo = null;

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

    public void setCodigo_Aluno(Long codigo_Aluno) {
        this.codigo_Aluno = codigo_Aluno;
    }

    public void setTitulo(String newtitulo){
        this.titulo=newtitulo;
    }

    public void setRamo(String newramo){
        this.ramo=newramo;
    }

    public void setEmail_Docente(String newemail){
        this.email_Docente=newemail;
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
        return "Proposta: " +
                " Codigo Id : " + cod_ID +
                " Titulo : " + titulo +
                " Codigo Aluno : " + codigo_Aluno +
                " Codigo Docente: " + email_Docente +
                " Ramo: " + ramo +
                "\n";
    }
}
