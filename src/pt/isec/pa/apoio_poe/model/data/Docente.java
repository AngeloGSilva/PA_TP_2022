package pt.isec.pa.apoio_poe.model.data;

public class Docente {
    String nome_Docente;
    String email_Docente;
    boolean papel_Docente; //orientador - true, proponente de projeto - false

    public Docente(String nome_Docente, String email_Docente, boolean papel_Docente) {
        this.nome_Docente = nome_Docente;
        this.email_Docente = email_Docente;
        this.papel_Docente = papel_Docente;
    }
}
