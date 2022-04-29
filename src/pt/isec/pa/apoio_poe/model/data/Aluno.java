package pt.isec.pa.apoio_poe.model.data;

import java.util.Objects;

public class Aluno {
    private long nr_Aluno;
    private String nome_Aluno;
    private String email_Aluno;
    private String ramo_Aluno;
    private double classificacao_Aluno;
    private boolean aceder_a_Estagio;

    public Aluno(long nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio) {
        this.nr_Aluno = nr_Aluno;
        this.nome_Aluno = nome_Aluno;
        this.email_Aluno = email_Aluno;
        this.ramo_Aluno = ramo_Aluno;
        this.classificacao_Aluno = classificacao_Aluno;
        this.aceder_a_Estagio = aceder_a_Estagio;
    }

    public Aluno(long nr_Aluno){
        this.nr_Aluno=nr_Aluno;
        this.nome_Aluno = null;
        this.email_Aluno = null;
        this.ramo_Aluno = null;
    }

    public static Aluno getDummyAluno(long nr_Aluno){
        return new Aluno(nr_Aluno);
    }

    public long getNr_Aluno() {
        return nr_Aluno;
    }

    public String getNome_Aluno() {
        return nome_Aluno;
    }

    public String getEmail_Aluno() {
        return email_Aluno;
    }

    public String getRamo_Aluno() {
        return ramo_Aluno;
    }

    public double getClassificacao_Aluno() {
        return classificacao_Aluno;
    }

    public boolean isAceder_a_Estagio() {
        return aceder_a_Estagio;
    }

    @Override
    public String toString() {
        return "Numero de aluno:" + nr_Aluno +
                ", Nome do aluno:'" + nome_Aluno + '\'' +
                ", Email do aluno:'" + email_Aluno + '\'' +
                ", Ramo do aluno:'" + ramo_Aluno + '\'' +
                ", Classificação do aluno:" + classificacao_Aluno +
                ", Acesso a estagio:" + aceder_a_Estagio + "\n";
    }
}
