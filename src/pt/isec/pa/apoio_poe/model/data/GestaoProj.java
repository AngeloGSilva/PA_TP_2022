package pt.isec.pa.apoio_poe.model.data;

import java.util.HashSet;

public class GestaoProj {

   /* //arrays para guardar objetos das classes ALUNOS , DOCENTES e PROJETOS
    Aluno[] alunos;
    Docente[] docentes;
    Proposta[] propostas;
    int nr_Alunos;
    int nr_Docentes;
    int nr_Projetos;
    //alterar


    public void adicionarAluno(long nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio){
        alunos[nr_Alunos] = new Aluno(nr_Aluno,nome_Aluno,email_Aluno,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);
        nr_Alunos++;
    }*/

    private HashSet<Aluno> alunos;
    private HashSet<Docente> docentes;
    private HashSet<Proposta> propostas;


    public GestaoProj() {
        alunos = new HashSet<>();
        docentes = new HashSet<>();
        propostas = new HashSet<>();
    }

    //arranjar state para ler ficheiros utilizar funcao assim
    //LerFicheiro.lerDoncentes("ola");


    public String toStringAlunos() {
        return "Alunos:" + alunos;
    }

    public String toStringDocentes() {
        return "Docentes:" + docentes;
    }

    public boolean removerAlunos(long nr_aluno){
        return alunos.remove(Aluno.getDummyAluno(nr_aluno));
    }

    public boolean adicinarAlunos(Aluno aluno){
        return alunos.add(aluno);
    }

    public String toStringPropostas(){
        return "Propostas:" + propostas;
    }

    public boolean adicinarProsta(Proposta proposta){
        return propostas.add(proposta);
    }

    public void removeAluno(Aluno aluno){
        alunos.remove(aluno);
    }

    public void removeDocente(Docente docente){
        docentes.remove(docente);
    }

    public void removeProposta(Proposta proposta){
        propostas.remove(proposta);
    }

    public HashSet<Aluno> getAlunos() {
        return alunos;
    }

    public boolean adicinarDocentes(Docente docente) {
        return docentes.add(docente);
    }

    public boolean lerficheiro(String fileName) {
        return LerFicheiro.lerAlunos(fileName,this);
    }
}