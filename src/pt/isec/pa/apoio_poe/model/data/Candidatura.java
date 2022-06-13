package pt.isec.pa.apoio_poe.model.data;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Candidatura implements Serializable {
    private static int counter_globalCandidatura = 0;

    private static int getNewID() {
        return ++counter_globalCandidatura;
    }

    private final int id;
    private Aluno aluno;
    private ArrayList<Proposta> propostas;

    public Candidatura(Aluno aluno, ArrayList<Proposta> propostas) {
        this.aluno = aluno;
        this.propostas = new ArrayList<>(propostas);
        this.id = getNewID();
        //this.propostas = (ArrayList<Proposta>) clone(propostas);
    }

    public int getId() {
        return id;
    }

    public Candidatura(Aluno aluno, Proposta proposta){
        this.aluno = aluno;
        propostas = new ArrayList<>();
        this.propostas.add(proposta);
        this.id = getNewID();
    }

    public int getNrPropostas(){
        return this.propostas.size();
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

    public void removeProposta(String id_proposta) {
        for (Proposta proposta : propostas) {
            if (proposta.getCod_ID().equals(id_proposta)){
                propostas.remove(proposta);
            }
        }
    }

    public Long getNraluno() {
        return aluno.getNr_Aluno();
    }

    public String getNralunoString() {
        return String.valueOf(aluno.getNr_Aluno());
    }

    public String getNomeAluno() {
        return aluno.getNome_Aluno();
    }

    public ArrayList<String> getIdPropostas(){
        ArrayList<String> ids = new ArrayList<String>();
        for (Proposta propostas: propostas) {
            ids.add(propostas.getCod_ID());
        }
        return ids;
    }


    @Override
    public String toString() {
        return "\nCandidatura: " +
                " Id: " + id +
                " Aluno: " + aluno +
                " Propostas: " + propostas +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidatura that = (Candidatura) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
