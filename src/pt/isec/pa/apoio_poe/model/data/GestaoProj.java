package pt.isec.pa.apoio_poe.model.data;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GestaoProj {
    //array de erros para utilizar no UI
    ArrayList<String> erros = new ArrayList<>();

    //para o fecho das fases
    private boolean fase_Fechada_Config = false;
    private boolean fase_Fechada_Candidatura = false;
    private boolean fase_Fechada_atriOrientador = false;
    private boolean fase_Fechada_atriProposta = false;

    private HashSet<Aluno> alunos;
    private HashSet<Docente> docentes;
    private HashSet<Proposta> propostas;
    private HashSet<Candidatura> candidaturas;
    private HashSet<Atribuicao> atribuicoes;

    public GestaoProj() {
        alunos = new HashSet<>();
        docentes = new HashSet<>();
        propostas = new HashSet<>();
        candidaturas = new HashSet<>();
        atribuicoes = new HashSet<>();
    }

    //gets e sets para as fecho das fases
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


    //toStrings dos varios arrays.... (nao sei se é necessario)
    public String toStringAlunos() {
        return "Alunos: " + alunos;
    }

    public String toStringDocentes() {
        return "Docentes: " + docentes;
    }

    public String toStringPropostas(){
        return "Propostas: " + propostas;
    }

    public String toStringCandidaturas() {
        return "Candidaturas" + candidaturas;
    }

    public String toStringAtribuicoes() {
        return "Atribuicoes" + atribuicoes;
    }

    //tamanho dos arrays
    public int getNrPropostas(String ramo){
        int cont=0;
        for(Proposta x: propostas) {
                if(x.getRamo()!=null && x.getRamo().contains(ramo)){
                   cont++;
                }
            }
        return cont;
        }

    public int getNrAlunos(String ramo){
        int cont=0;
        for(Aluno x: alunos) {
            if(x.getRamo_Aluno().equals(ramo)){
                cont++;
            }
        }
        return cont;
    }

    public boolean CondicaoAvancar(){
        boolean verificado=true;
        String[] ramos = {"DA", "SI", "RAS"};
        for(int i=0;i<ramos.length;i++) {
            if (getNrPropostas(ramos[i]) < getNrAlunos(ramos[i]) && verificado == true || (getNrPropostas(ramos[i])==0 && getNrAlunos(ramos[i])==0 && verificado == true)){
                  verificado=false;
            }
        }
            return verificado;
    }

    //verifica se o numero passado ja esta vincolado a alguma candidatura
    public boolean getNrAlunoCandidatura(long nr_Aluno){
        for (Candidatura x : candidaturas) {
            if(x.getNraluno() == nr_Aluno){
                return true;
            }
        }
        return false;
    }

    //verifica se o email pertence a algum docente e retorna o docente
    public Docente getDocentePorEmailObjeto(String email) {
        for (Docente d: docentes
        ) {
            if (d.getEmail_Docente().equals(email))
                return d;
        }
        return null;
    }

    //verifica se o email pertence a algum docente
    public boolean getDocentePorEmail(String email) {
        for (Docente d: docentes
        ) {
            if (d.getEmail_Docente().equals(email))
                return true;
        }
        return false;
    }

    //verifica se o numero pertence a algum aluno
    public boolean VerificaAlunoExiste(long nr) {
        for (Aluno d: alunos
        ) {
            if (d.getNr_Aluno() == nr)
                return true;
        }
        return false;
    }

    public boolean getAlunoPorEmail(String email) {
        for (Aluno d : alunos) {
            if (d.getEmail_Aluno().equals(email)) {
                return true;
            }
        }
        return false;
    }

    //verifica se o aluno passado esta associado a alguma proposta T3
    public boolean get_codigoAluno(Long nrAluno){
        for (Proposta x: propostas) {
            if(x.getClass().getSimpleName().equals("T3")){
                if(x.getCodigo_Aluno().equals(nrAluno)){
                    return true;
                }
            }
        }
        return false;
    }

    //verifica se o Id da proposta ja pertence a alguma proposta
    public boolean get_idProposta(String IDPro){
        for (Proposta x: propostas) {
            if(x.getCod_ID().equals(IDPro)){
                return true;
            }
        }
        return false;
    }


    public HashSet<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public HashSet<Aluno> getAlunos() {
        return alunos;
    }

    public HashSet<Docente> getDocentes() {
        return docentes;
    }

    public HashSet<Proposta> getPropostas() {
        return propostas;
    }


    //adicionar aos arrays
    public boolean adicinarAlunos(Aluno aluno){
        return alunos.add(aluno);
    }

    public boolean adicinarDocentes(Docente docente) {
        return docentes.add(docente);
    }

    public boolean adicionarProposta(Proposta proposta){
        return propostas.add(proposta);
    }

    public boolean adicionarCandidatura(Candidatura candidatura) {
        return candidaturas.add(candidatura);
    }


    //remover dos arrays
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


    //funcoes que recebem o nome do ficheiro do state e chama o metodo da class estatica correspondente
    public boolean lerficheiroAluno(String fileName) {
        return LerFicheiro.lerAlunos(fileName,this);
    }

    public boolean lerficheiroDocente(String fileName) {
        return LerFicheiro.lerDoncentes(fileName,this);
    }

    public boolean lerficheiroProposta(String fileName) {
        return LerFicheiro.lerPropostas(fileName,this);
    }

    public boolean lerficheiroCandidaturas(String filename){
        return LerFicheiro.lercandidaturas(filename,this);
    }

    //verifica se a proposta passada tem algum aluno
    public boolean verificaPropostaAssociado(String id_Proposta){
        for (Proposta x : propostas) {
            if (x.getCod_ID().equals(id_Proposta)){
                if(x.getCodigo_Aluno() == null){
                    return true;
                }
            }
        }
        return false;
    }

    //verifica se o aluno passado esta associado a alguma proposta
    public boolean VerificaNumeroAssociado(String codaluno){
        for (Proposta x : propostas) {
            if (codaluno.equals(x.getCodigo_Aluno())){
                return true;
            }
        }
        return false;
    }

    //metodos para mostrar o erro no UI etc
    public void limparErros(){
        erros.clear();
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(String erro) {
        erros.add(erro);
    }

    @Override
    public String toString() {
        return "GestaoProj{" +
                "alunos=" + alunos +
                ", docentes=" + docentes +
                ", propostas=" + propostas +
                ", candidaturas=" + candidaturas +
                ", atribuicoes=" + atribuicoes +
                '}';
    }

    public ArrayList<Aluno> getAlunosAutopropostos() {
        ArrayList<Aluno> list;
        list = new ArrayList<>();
        for (Proposta x : propostas) {
            if(x.getClass().getSimpleName().equals("T3")){
                list.add(getAlunoPorNumero(x.getCodigo_Aluno()));
            }
        }
        return list;
    }

    public Aluno getAlunoPorNumero(long nr) {
        for (Aluno d: alunos
        ) {
            if (d.getNr_Aluno() == nr)
                return d;
        }
        return null;
    }

    public Proposta getPropostaPorId(String id){
        for (Proposta p: propostas) {
         if (p.getCod_ID().equals(id))
                return p;
    }
        return null;
    }

    public ArrayList<Proposta> getPropostasDocentes() {
        ArrayList<Proposta> list;
        list = new ArrayList<>();
        for (Proposta x : propostas) {
            if(x.getClass().getSimpleName().equals("T2")){
                list.add(x);
            }
        }
        return list;
    }
/*
    public ArrayList<Proposta> getPropostasCandidatos() {
        ArrayList<Candidatura> list;
        list = new ArrayList<>();
        for (Candidatura x : candidaturas) {
            if(){
                list.add(x);
            }
        }
        return list;
    }*/

    public ArrayList<Proposta> getPropostasSemCandidatos() {
        ArrayList<Proposta> list;
        list = new ArrayList<>();
        for (Proposta x : propostas) {
            if(!verificaPropostaAssociado(x.getCod_ID())){
                list.add(x);
            }
        }
        return list;
    }

    public void atribuiAutopropostos(){
        for (Candidatura c: candidaturas) {
            for (Proposta p: c.getPropostas()) {
                if(p.getClass().getSimpleName().equals("T3")){
                    atribuicoes.add(new Atribuicao(c.getAluno(),null,p));
                }
            }
        }
    }

    public void atribuiPropostasDocentes(){
        for (Candidatura c: candidaturas) {
            for (Proposta p: c.getPropostas()) {
                if(p.getClass().getSimpleName().equals("T2") && p.getCodigo_Aluno() != null && p.getNomeDocente() != null){
                    atribuicoes.add(new Atribuicao(c.getAluno(),getDocentePorEmailObjeto(p.getNomeDocente()),p));
                }
            }
        }
    }

    public boolean verificaCandidaturaAtribuida(Aluno aluno){
        for (Atribuicao a: atribuicoes) {
            if (a.getAluno().equals(aluno)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> atribuiAutomaticamente() {
        //base nas classificacoes...
        //opcoes de candidatura
        //true or false do estagio

        ArrayList<String> conflito = new ArrayList<>();
        for (Candidatura c1: candidaturas) {
            if(!verificaCandidaturaAtribuida(c1.getAluno())) {
                for (Proposta p1 : c1.getPropostas()) {
                    for (Candidatura c2 : candidaturas) {
                        if (!c1.equals(c2)) {
                            if (p1.equals(c2.getPropostas().get(0))) {
                                conflito.add(String.valueOf(c1.getAluno().getNr_Aluno()));
                                conflito.add(String.valueOf(c2.getAluno().getNr_Aluno()));
                                conflito.add(p1.getCod_ID());
                                return conflito;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}