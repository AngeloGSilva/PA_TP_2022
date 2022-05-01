package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.Utils.PAInput;

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

    //private HashSet<Aluno> alunosDisponiveis;
    //private HashSet<Proposta> propostaDisponiveis;

    public GestaoProj() {
        alunos = new HashSet<>();
        docentes = new HashSet<>();
        propostas = new HashSet<>();
        candidaturas = new HashSet<>();
        atribuicoes = new HashSet<>();
        //propostaDisponiveis = new HashSet<>();
        //alunosDisponiveis =  new HashSet<>();
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

    public boolean AlunoCandidaturaFoiAtribuido(){
        int contador = 0;
        for (Candidatura candidatura: candidaturas) {
            for (Atribuicao atribuicao: atribuicoes) {
                if (candidatura.getAluno().getNr_Aluno() == atribuicao.getAluno().getNr_Aluno()){
                    contador++;
                }
            }
        }
        if (candidaturas.size() == contador){
            return true;
        }
        return false;
    }




    //toStrings dos varios arrays.... (nao sei se é necessario)
    public String toStringAlunos() {
        return "Alunos:\n " + alunos;
    }

    public String toStringDocentes() {
        return "Docentes:\n " + docentes;
    }

    public String toStringPropostas(){
        return "Propostas:\n " + propostas;
    }

    public String toStringAutopropostas(){
        ArrayList<String> Autopropostos = new ArrayList<>();
        String buffer;
        Autopropostos.add("\n");
        for(Proposta p : propostas){
            if(p.getClass().getSimpleName().equals("T3")){
                buffer = ("Proposta [" + p.getCod_ID() + "] apresentada pelo aluno " + p.getCodigo_Aluno() +"\n");
                Autopropostos.add(buffer);
            }
        }
        return String.valueOf(Autopropostos);
    }

    public String toStringCandidaturas() {
        return "Candidaturas:\n" + candidaturas;
    }

    public String toStringAtribuicoes() {
        return "Atribuicoes:\n" + atribuicoes;
    }

    public String toStringAtribuicoesSemDocente() {
        return PreencheAtribuicoesSemDocente().toString();
    }

    public String toStringAlunosSemAtribuicao() {
        return PreencheAlunosDisponiveis().toString();
    }

    public String toStringPropostasSemAtribuicao() {
        return PreenchePropostasDisponiveis().toString();
    }

    //Devolver um to.string()
    //Alunos com autoproposta e Alunos com proposta de docente associada
    public HashSet<Aluno> AlunosComAutoproposta(){
        HashSet<Aluno> alunosCA= new HashSet<>();
        for(Aluno a:alunos) {
            if(get_codigoAluno(a.getNr_Aluno())){
                alunosCA.add(a);
            }
        }
        return alunosCA;
    }


    //Alunos com candidatura registada
    public HashSet<Aluno> AlunosCandidaturaRegistada(){
        HashSet<Aluno> alunosD= new HashSet<>();
        for(Aluno a:alunos) {
            for (Candidatura ca : candidaturas)
                if (a.getNr_Aluno() == ca.getAluno().getNr_Aluno())
                    alunosD.add(a);
        }
        return alunosD;
    }

    //Alunos sem candidatura registada
    public HashSet<Aluno> AlunosSemCandidaturaRegistada(){
        boolean existe = true;
        HashSet<Aluno> alunosSR= new HashSet<>();
        for(Aluno a:alunos) {
            existe = true;
            for (Candidatura ca : candidaturas) {
                if (a.getNr_Aluno() == ca.getAluno().getNr_Aluno()){
                    existe=false;
                }
            }
            if(existe) {
                alunosSR.add(a);
            }
        }
        return alunosSR;
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

    //Facilita a utilização dos Atribuicoes sem docentes
    public HashSet<Atribuicao> PreencheAtribuicoesSemDocente(){
        HashSet<Atribuicao> atribuicaoSemDocente= new HashSet<>();
        for(Atribuicao a : atribuicoes) {
            if (a.getDocente() == null){
                atribuicaoSemDocente.add(a);
            }
        }
        return atribuicaoSemDocente;
    }

    //isto nao esta a fazer muito sentido ... e nao esta a funcionar
    //Facilita a utilização dos alunos e das propostas ao associar
    public HashSet<Aluno> PreencheAlunosDisponiveis(){
        HashSet<Aluno> alunosD= new HashSet<>();
        boolean existe = false;
        for(Aluno a:alunos) {
            existe = false;
            for (Atribuicao at : atribuicoes) {
                if (a.getNr_Aluno() == at.getAluno().getNr_Aluno())
                    existe = true;
                }
                 if (!existe) {
                    alunosD.add(a);
            }
        }
        return alunosD;
    }

    //nao é != em vez de ==
    public HashSet<Proposta> PreenchePropostasDisponiveis(){
        HashSet<Proposta> PropostasD= new HashSet<>();
        boolean existe =  false;
        for(Proposta p:propostas) {
            existe = false;
            for (Atribuicao at : atribuicoes) {
                if (p.getCod_ID() == at.getProposta().getCod_ID())
                    existe=true;
                }
            if(!existe)
                PropostasD.add(p);
        }
        return PropostasD;
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
    public boolean verificaEmailDocente(String email) {
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
    public boolean VerificaIdProposta(String IDPro){
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
        return Ficheiro.lerAlunos(fileName,this);
    }

    public boolean lerficheiroDocente(String fileName) {
        return Ficheiro.lerDoncentes(fileName,this);
    }

    public boolean lerficheiroProposta(String fileName) {
        return Ficheiro.lerPropostas(fileName,this);
    }

    public boolean lerficheiroCandidaturas(String filename){
        return Ficheiro.lercandidaturas(filename,this);
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
    public boolean VerificaNumeroAssociadoAProposta(String codaluno){
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

    public void atribuiPropostasDocentesCompletas(){
        for (Candidatura c: candidaturas) {
            for (Proposta p: c.getPropostas()) {
                if(p.getClass().getSimpleName().equals("T2") &&
                        p.getCodigo_Aluno() != null &&
                        VerificaAlunoAcederProposta(p.getCodigo_Aluno(),p.getCod_ID()) &&
                        p.getEmail_Docente() != null){
                    atribuicoes.add(new Atribuicao(c.getAluno(),getDocentePorEmailObjeto(p.getEmail_Docente()),p));
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

    public boolean verificaPropostaAtribuida(Proposta proposta){
        for (Atribuicao a: atribuicoes) {
            if (a.getProposta().equals(proposta)){
                return true;
            }
        }
        return false;
    }

    public void atribuirSemCandidatura(){
        boolean encontrou = false;
        boolean propostaTaken = false;
        for (Aluno aluno: alunos) {
            encontrou = false;
            for (Atribuicao atribuicao: atribuicoes) {
                if(aluno.getNr_Aluno() == atribuicao.getAluno().getNr_Aluno()){
                    encontrou = true;
                }
            }
            if (!encontrou){
                for (Proposta proposta: propostas) {
                    propostaTaken = false;
                    if (proposta.getRamo().contains(aluno.getRamo_Aluno())){
                        for (Atribuicao atribuicao : atribuicoes) {
                            if (proposta.getCod_ID().equals(atribuicao.getProposta().getCod_ID())) {
                                propostaTaken = true;
                            }
                        }
                    if (!propostaTaken) {
                        atribuicoes.add(new Atribuicao(aluno,getDocentePorEmailObjeto(proposta.getEmail_Docente()),proposta));
                        System.out.println(aluno);
                        System.out.println(proposta);
                        System.out.println(atribuicoes.toString());
                        break;
                    }
                }
                }
            }
        }
    }

    public boolean ComparaClassificacoes(ArrayList<String> alunosconflito, Proposta proposta){
        double maiorClassificacao = 0;
        long nrAluno=0;
        int contador=0;
        for (String aluno: alunosconflito) {
            if (maiorClassificacao <= getAlunoPorNumero(Long.parseLong(aluno)).getClassificacao_Aluno()){
                maiorClassificacao = getAlunoPorNumero(Long.parseLong(aluno)).getClassificacao_Aluno();
                nrAluno = Long.parseLong(aluno);
            }
        }
        for (String aluno: alunosconflito) {
            if (maiorClassificacao == getAlunoPorNumero(Long.parseLong(aluno)).getClassificacao_Aluno()){
                contador++;
            }
        }
        if (contador>1){
            for (String aluno : alunosconflito) {
                if (getAlunoPorNumero(Long.parseLong(aluno)).getClassificacao_Aluno() == maiorClassificacao){
                    System.out.println(getAlunoPorNumero(Long.parseLong(aluno)).toString());
                }
                System.out.println("Proposta: " + proposta.getCod_ID());
            }
            String alunoEscolhido = PAInput.readString("Aluno:", true);
            atribuiPropostaAluno(alunoEscolhido,proposta.getCod_ID());
            //perguntar ao gajo
            System.out.println("Repetidos seu crlh");
        }else{
            atribuicoes.add(new Atribuicao(getAlunoPorNumero(nrAluno),getDocentePorEmailObjeto(proposta.getEmail_Docente()),proposta));
            System.out.println("Atribuiu a este cabecudo"+ nrAluno);
        }
        return false;
    }

    public ArrayList<String> atribuiAutomaticamente() {
        ArrayList<String> conflitos = new ArrayList<>();
        boolean repetido = false;
        for (Candidatura c: candidaturas) {
            if (!verificaCandidaturaAtribuida(c.getAluno())) {
                for (Proposta p : c.getPropostas()) {
                    conflitos.add(String.valueOf(c.getAluno().getNr_Aluno()));
                    if (!verificaPropostaAtribuida(p) && !verificaCandidaturaAtribuida(c.getAluno())) {
                        for (Candidatura c2 : candidaturas) { //comparar com a segunda dos outros se perder
                            if (!verificaCandidaturaAtribuida(c2.getAluno()) &&
                                    !(c.getAluno().getNr_Aluno() == c2.getAluno().getNr_Aluno()) &&
                                    p.equals(c2.getPropostas().get(0)) &&
                                    c2.getAluno().getClassificacao_Aluno() >= c.getAluno().getClassificacao_Aluno()) {
                                repetido = true;
                                conflitos.add(String.valueOf(c2.getAluno().getNr_Aluno()));
                            }
                        }
                        if (!repetido) {
                            atribuicoes.add(new Atribuicao(c.getAluno(), getDocentePorEmailObjeto(p.getEmail_Docente()), p));
                            System.out.println("Atribuiu a este cabecudo "+ c.getAluno().getNr_Aluno());
                            conflitos.clear();
                        } else {
                            ComparaClassificacoes(conflitos, p);
                            conflitos.clear();
                            repetido = false;
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getCandidaturaPorNrAluno(Long nraluno) {
        for(Candidatura x : candidaturas){
            if(x.getAluno().getNr_Aluno() == nraluno)
                return x.toString();
        }
        return null;
    }

    public void atribuiPropostaAluno(String escolhido, String id_proposta) {
        atribuicoes.add(new Atribuicao(getAlunoPorNumero(Long.parseLong(escolhido)),getDocentePorEmailObjeto(getPropostaPorId(id_proposta).getEmail_Docente()),getPropostaPorId(id_proposta)));
    }

    public Docente getDocenteContadorMenor(){
        Docente docenteMinimo = null;
        int docenteMenor = 0;
        for (Docente docente: docentes) {
            if (docente.getContador() < 5) {
                if (docenteMenor <= docente.getContador()) {
                    docenteMenor = docente.getContador();
                    docenteMinimo = docente;
                }
            }
        }
        return docenteMinimo;
    }

    public void atribuirDocentesauto() {
        Docente docente = null;
            for (Atribuicao atribuicao : atribuicoes) {
                docente = getDocenteContadorMenor();
                if (atribuicao.getDocente() == null) {
                    atribuicao.setDocente(docente);
                    docente.setContador();
                }
            }
    }

    public boolean atribuirManualmenteDocente(String docente, int id_atribuicao){
        if (verificaEmailDocente(docente)) {
            for (Atribuicao atribuicao : atribuicoes) {
                if (atribuicao.getId() == id_atribuicao) {
                    if (atribuicao.getDocente() == null) {
                        atribuicao.setDocente(getDocentePorEmailObjeto(docente));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean atribuirManualmenteAluno(long id_aluno, String proposta){
        if (VerificaAlunoExiste(id_aluno) &&
                VerificaIdProposta(proposta) &&
                VerificaAlunoAcederProposta(id_aluno,proposta) &&
                !verificaPropostaAtribuida(getPropostaPorId(proposta)) &&
                !verificaCandidaturaAtribuida(getAlunoPorNumero(id_aluno))){
            atribuicoes.add(new Atribuicao(getAlunoPorNumero(id_aluno),getDocentePorEmailObjeto(getPropostaPorId(proposta).getEmail_Docente()),getPropostaPorId(proposta)));
            return true;
        }
        return false;
    }

    public String getAtribuicaoPorId(int id) {
        for (Atribuicao a:atribuicoes) {
            if (a.getId() == id){
                return a.toString();
            }
        }
        return null;
    }

    public boolean VerificaAlunoAcederProposta(long nr_aluno, String id_proposta) {
        if (getAlunoPorNumero(nr_aluno).isAceder_a_Estagio()) {
            return true;
        }else if (getPropostaPorId(id_proposta).getClass().getSimpleName().equals("T2")){
            return true;
        }
        return false;
    }

    public boolean VerificaAlunoAcederPropostaLeitura(long nr_aluno, String id_proposta) {
        if (getAlunoPorNumero(nr_aluno).isAceder_a_Estagio()) {
            return true;
        }else if (id_proposta.equals("T2")){
            return true;
        }
        return false;
    }

    public void exportarAlunos(String fileName){
        Ficheiro.ExportarAlunos(fileName,this);
    }

    public void exportarPropostas(String fileName){
        Ficheiro.ExportarPropostas(fileName,this);
    }

    public void exportarDocentes(String fileName){
        Ficheiro.ExportarDocentes(fileName,this);
    }

    public void exportarCandidaturas(String filename){
        Ficheiro.ExportarCandidaturas(filename,this);
    }

    //Dados Nas opcoes de Candidatura

}