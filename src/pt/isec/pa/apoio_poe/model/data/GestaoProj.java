package pt.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;
import java.util.HashSet;

public class GestaoProj {
    ArrayList<String> erros = new ArrayList<>();

    private boolean fase_Fechada_Config = false;
    private boolean fase_Fechada_Candidatura = false;
    private boolean fase_Fechada_atriOrientador = false;
    private boolean fase_Fechada_atriProposta = false;

    public ArrayList<String> getErros() {
        return erros;
    }

    public void setErros(String erro) {
        erros.add(erro);
    }

    public boolean isFase_Fechada_Candidatura() {
        return fase_Fechada_Candidatura;
    }

    public void setFase_Fechada_Candidatura(boolean fase_Fechada_Candidatura) {
        this.fase_Fechada_Candidatura = fase_Fechada_Candidatura;
    }

    public boolean isFase_Fechada_atriOrientador() {
        return fase_Fechada_atriOrientador;
    }

    public void setFase_Fechada_atriOrientador(boolean fase_Fechada_atriOrientador) {
        this.fase_Fechada_atriOrientador = fase_Fechada_atriOrientador;
    }

    public boolean isFase_Fechada_atriProposta() {
        return fase_Fechada_atriProposta;
    }

    public void setFase_Fechada_atriProposta(boolean fase_Fechada_atriProposta) {
        this.fase_Fechada_atriProposta = fase_Fechada_atriProposta;
    }

    public boolean isFase_Fechada_Config() {
        return fase_Fechada_Config;
    }

    public void setFase_Fechada_Config(boolean fase_Fechada_Config) {
        this.fase_Fechada_Config = fase_Fechada_Config;
    }
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

    public boolean get_codigoAluno(String nrAluno){
        for (Proposta x: propostas) {
            if(x.getClass().getSimpleName().equals("T3")){
                if(x.getCodigo_Aluno().equals(nrAluno)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean get_idProposta(String IDPro){
        for (Proposta x: propostas) {
            if(x.getCod_ID().equals(IDPro)){
                return true;
            }
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