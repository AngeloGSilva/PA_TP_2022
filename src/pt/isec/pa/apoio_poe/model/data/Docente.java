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
        return papel_Docente == docente.papel_Docente && Objects.equals(nome_Docente, docente.nome_Docente) && Objects.equals(email_Docente, docente.email_Docente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome_Docente, email_Docente, papel_Docente);
    }

    @Override
    public String toString() {
        return "Docente{" +
                "nome_Docente='" + nome_Docente + '\'' +
                ", email_Docente='" + email_Docente + '\'' +
                ", papel_Docente=" + papel_Docente +
                '}';
    }
}
