package pt.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;
import java.util.Objects;

public class Candidatura {
    private Aluno aluno;
    private ArrayList<Proposta> propostas = new ArrayList<>();

    public Candidatura(Aluno aluno, ArrayList<Proposta> propostas) {
        this.aluno = aluno;
        this.propostas = propostas;
    }

    public Candidatura(Aluno aluno,Proposta proposta){
        this.aluno = aluno;
        this.propostas.add(proposta);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public ArrayList<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(Proposta proposta) {
        this.propostas.add(proposta);
    }

    public Long getNraluno() {
        return aluno.getNr_Aluno();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidatura that = (Candidatura) o;
        return aluno.equals(that.aluno) && propostas.equals(that.propostas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aluno, propostas);
    }

    @Override
    public String toString() {
        return "Candidatura{" +
                "aluno=" + aluno +
                ", propostas=" + propostas +
                '}' + "\n";
    }
}
