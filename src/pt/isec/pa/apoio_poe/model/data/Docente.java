package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

public class Docente implements Serializable {
    int contador = 0;
    String nome_Docente;
    String email_Docente;
    boolean papel_Docente; //orientador - true, proponente de projeto - false

    public Docente(String nome_Docente, String email_Docente, boolean papel_Docente) {
        this.nome_Docente = nome_Docente;
        this.email_Docente = email_Docente;
        this.papel_Docente = papel_Docente;
    }

    public String getNome_Docente() {
        return nome_Docente;
    }

    public String getEmail_Docente() {
        return email_Docente;
    }

    public boolean isPapel_Docente() {
        return papel_Docente;
    }

    public void setPapel_Docente(boolean papel){ //true para orientador
        this.papel_Docente = papel;
    }

    public int getContador() {
        return contador;
    }

    public void incContador() {
        contador++;
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
        if(papel_Docente) {
            return "Docente: " +
                    " Nome do Docente: " + nome_Docente +
                    " Email do Docente: " + email_Docente +
                    " Papel do Docente: " + papel_Docente + "[Proponente de 1 projeto]" +
                    "\n";
        }else{
        return  "Docente: " +
                " Nome do Docente: " + nome_Docente +
                " Email do Docente: " + email_Docente +
                " Papel do Docente: " + papel_Docente + "[Não é Proponente de nenhum projeto]" +
                "\n";
    }
    }
}
