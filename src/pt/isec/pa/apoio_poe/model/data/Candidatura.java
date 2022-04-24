package pt.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;
import java.util.Objects;

public class Candidatura {
    private Long nraluno;
    private ArrayList<String> idspropostas = new ArrayList<>();

    public Candidatura(Long nraluno, ArrayList<String> idspropostas) {
        this.idspropostas = idspropostas;
        this.nraluno = nraluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidatura that = (Candidatura) o;
        return nraluno.equals(that.nraluno) && idspropostas.equals(that.idspropostas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nraluno, idspropostas);
    }

    @Override
    public String toString() {
        return "Candidatura{" +
                "nraluno=" + nraluno +
                ", idspropostas=" + idspropostas +
                '}';
    }
}
