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


    @Override
    public String toString() {
        return "Alunos: " + alunos +
                ", Docente: " + docentes +
                ", Propostas: " + propostas;
    }

    public String toStringAlunos() {
        return "Alunos: " + alunos;
    }

    public String toStringDocentes() {
        return "Docentes: " + docentes;
    }

    public String toStringPropostas(){
        return "Propostas: " + propostas;
    }

    public HashSet<Aluno> getAlunos() {
        return alunos;
    }

    public boolean getDocentePorEmail(String email) {
        for (Docente d: docentes
        ) {
            if (d.getEmail_Docente().equals(email))
                return true;
        }
        return false;
    }
    public boolean getAlunoPorNr(long nr) {
        for (Aluno d: alunos
        ) {
            if (d.getNr_Aluno() == nr)
                return true;
        }
        return false;
    }


    public HashSet<Docente> getDocentes() {
        return docentes;
    }

    public HashSet<Proposta> getPropostas() {
        return propostas;
    }

    public boolean adicinarAlunos(Aluno aluno){
        return alunos.add(aluno);
    }

    public boolean adicinarDocentes(Docente docente) {
        return docentes.add(docente);
    }

    public boolean adicinarProsta(Proposta proposta){
        return propostas.add(proposta);
    }

    public boolean removerAlunos(long nr_aluno){
        return alunos.remove(Aluno.getDummyAluno(nr_aluno));
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

    public boolean lerficheiroAluno(String fileName) {
        return LerFicheiro.lerAlunos(fileName,this);
    }

    public boolean lerficheiroDocente(String fileName) {
        return LerFicheiro.lerDoncentes(fileName,this);
    }

    public boolean lerficheiroProposta(String fileName) {
        return LerFicheiro.lerPropostas(fileName,this);
    }
}