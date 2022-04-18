package pt.isec.pa.apoio_poe.model.data;

import java.util.Objects;

public class Docente {
    String nome_Docente;
    String email_Docente;
    boolean papel_Docente; //orientador - true, proponente de projeto - false

    public Docente(String nome_Docente, String email_Docente, boolean papel_Docente) {
        this.nome_Docente = nome_Docente;
        this.email_Docente = email_Docente;
        this.papel_Docente = papel_Docente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Docente docente = (Docente) o;
        return email_Docente.equals(docente.email_Docente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email_Docente);
    }

    @Override
    public String toString() {
        return "Docente{" +
                "Nome do Docente='" + nome_Docente + '\'' +
                ", Email do Docente='" + email_Docente + '\'' +
                ", Papel do Docente=" + papel_Docente +
                '}';
    }
}
