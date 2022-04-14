package pt.isec.pa.apoio_poe.model.data;

public class Proposta {
    String cod_ID = null;
    String titulo = null;
    String codigo_Aluno = null;;
    String codigo_Docente = null;;
    String ramo = null;;

    //geral
    public Proposta(String cod_ID, String titulo, String codigo_Aluno, String codigo_Docente, String ramo) {
        this.cod_ID = cod_ID;
        this.titulo = titulo;
        this.codigo_Aluno = codigo_Aluno;
        this.codigo_Docente = codigo_Docente;
        this.ramo = ramo;
    }
}
