package pt.isec.pa.apoio_poe.model.data;

import javax.print.Doc;
import java.io.Serializable;
import java.util.*;

/*
* nao comecar maiusculas
*  ex: Numero do Aluno : nr_Aluno Variaveis
* nome de metodos: verificaAlunoPeloNome
*
* */

/**
 * Class principal
 * @see Serializable
 */
public class GestaoProj implements Serializable {
    //array de erros para utilizar no UI
    ArrayList<String> erros = new ArrayList<>();
    HashSet<Aluno> conflitos = new HashSet<>();

    Proposta propostaConflito;
    boolean conflitoON = false;

    //Validar entradas
    //arrays para verificacoes que vao permitir usar o contains
    private  String[] ramos = {"DA", "SI", "RAS"};
    private  List<String> Ramos = Arrays.asList(ramos);
    private  String[] curso = {"LEI", "LEI-PL"};
    private  List<String> Curso = Arrays.asList(curso);
    private  String[] tipos = {"T1", "T2", "T3"};
    private  List<String> Tipos = Arrays.asList(tipos);

    public HashSet<Aluno> getConflitos() {
        return conflitos;
    }

    public Proposta getPropostaConflito() {
        return propostaConflito;
    }

    public boolean isConflitoON() {
        return conflitoON;
    }

    public Enum getStatekeep() {
        return statekeep;
    }

    public void setStatekeep(Enum statekeep) {
        this.statekeep = statekeep;
    }

    Enum statekeep;

    //para o fecho das fases
    private boolean fase_Fechada_Config = false;
    private boolean fase_Fechada_Candidatura = false;
    private boolean fase_Fechada_atriOrientador = false;
    private boolean fase_Fechada_atriProposta = false;

    /**
     * HashSet para guardar Alunos
     * @see Aluno
     * @see HashSet
     */
    private HashSet<Aluno> alunos;
    /**
     * Hashset para guardar Docentes
     * @see Docente
     * @see HashSet
     */
    private HashSet<Docente> docentes;
    /**
     * Hashet para guardar Propostas
     * @see Proposta
     * @see HashSet
     */
    private HashSet<Proposta> propostas;
    /**
     * Hashet para guardar Cadidaturas efetuadas pelos Alunos
     * @see Candidatura
     * @see Proposta
     * @see Aluno
     * @see HashSet
     */
    private HashSet<Candidatura> candidaturas;
    /**
     * HashSet para guardar Propostas Atribuidas a Alunos com o respetivo Orientador
     * @see Atribuicao
     * @see Proposta
     * @see Aluno
     * @see Docente
     * @see HashSet
     */
    private HashSet<Atribuicao> atribuicoes;

    /**
     * Construtor da Classe principal, onde é feita a inicialização dos HashSets dos Alunos, Docentes, Propostas, Candidaturas e Atribuições
     * @see GestaoProj
     * @see Aluno
     * @see Docente
     * @see Proposta
     * @see Candidatura
     * @see Atribuicao
     * @see HashSet
     */
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

    /**
     * Função de verificação do fecho da fase de Atribuir propostas
     * @return True se todos os alunos com candidaturas efetuadas possuem uma Atribuição
     */
    public boolean alunoCandidaturaFoiAtribuido(){
        int contador = 0;
        for (Candidatura candidatura: candidaturas) {
            for (Atribuicao atribuicao: atribuicoes) {
                if (candidatura.getAluno().getNr_Aluno() == atribuicao.getAluno().getNr_Aluno()){
                    contador++;
                }
            }
        }
        if (candidaturas.size() == contador && candidaturas.size() != 0){
            return true;
        }
        return false;
    }

    /**
     * Função de obtenção dos dados remetentes aos alunos
     * @return ArrayList de alunos
     */
    public ArrayList<Aluno> getAlunosTV(){
        return new ArrayList<Aluno>(alunos);
    }

    /**
     * Função de obtenção dos dados remetentes aos alunos em situação de conflito
     * @return ArrayList de alunos
     */
    public ArrayList<Aluno> getConflitosTV() {
        return new ArrayList<Aluno>(conflitos);
    }

    /**
     * Função de obtenção dos dados remetentes aos Docentes
     * @return ArrayList de Docentes
     */
    public ArrayList<Docente> getDocentesTV(){
        return new ArrayList<Docente>(docentes);
    }

    /**
     * Função de obtenção dos dados remetentes às propostas
     * @return ArrayList de propostas
     */
    public ArrayList<Proposta> getPropostaTV(){
        return new ArrayList<Proposta>(propostas);
    }

    /**
     * Função de obtenção dos dados remetentes às Candidaturas
     * @return ArrayList de Candidaturas
     */
    public ArrayList<Candidatura> getCandidaturaTV(){
        return new ArrayList<Candidatura>(candidaturas);
    }

    /**
     * Função de obtenção dos dados remetentes às Atribuições
     * @return ArrayList de Atribuições
     */
    public ArrayList<Atribuicao> getAtribuicoesTV(){
        return new ArrayList<Atribuicao>(atribuicoes);
    }

    /**
     * Função de obtenção dos dados remetentes às Candidaturas de alunos autopropostos
     * @return ArrayList de Candidaturas
     */
    public ArrayList<Candidatura> getCandidaturaTVAuto(){
        return getAutoPropostos();
    }

    /**
     * Função de obtenção dos dados remetentes aos alunos sem candidaturas registadas
     * @return ArrayList de Alunos
     */
    public ArrayList<Aluno> getCandidaturasTVNotReg(){
        return getAlunosNotReg();
    }


    /**
     * Procura Alunos nao registados
     * @return ArrayList de Alunos
     */
    public ArrayList<Aluno> getAlunosNotReg(){
        ArrayList<Aluno> alunosNotReg = new ArrayList<>();
        boolean encrontrou = false;
        for (Aluno aluno: alunos) {
            encrontrou = false;
            for (Candidatura candidatura: candidaturas) {
                if (candidatura.getNraluno().equals(aluno.getNr_Aluno())){
                    encrontrou = true;
                }
            }
            if (encrontrou == false)
                alunosNotReg.add(aluno);
        }
        return alunosNotReg;
    }

    /**
     * Função de obtenção dos dados remetentes aos alunos sem candidaturas registadas
     * @return ArrayList de Alunos
     */
    public ArrayList<Candidatura> getAutoPropostos(){
        ArrayList<Candidatura> autopropostos = new ArrayList<>();
        for(Proposta p : propostas){
            if(p.getClass().getSimpleName().equals("T3")){
                for (Candidatura candidatura: candidaturas) {
                    if (p.getCodigo_Aluno().equals(candidatura.getNraluno())){
                        autopropostos.add(candidatura);
                    }
                }
            }
        }
        return autopropostos;
    }

    /**
     * Função que tem como objetivo auxiliar a verificação de avanço da fase de configuração
     * @return Numero de propostas por ramo
     */
    public int getNrPropostas(String ramo){
        int cont=0;
        for(Proposta x: propostas) {
            if(x.getRamo()!=null && x.getRamo().contains(ramo)){
                cont++;
            }
        }
        return cont;
    }

    /**
     * Função que tem como objetivo auxiliar a verificação de avanço da fase de configuração
     * @return Numero de alunos por ramo
     */
    public int getNrAlunos(String ramo){
        int cont=0;
        for(Aluno x: alunos) {
            if(x.getRamo_Aluno().equals(ramo)){
                cont++;
            }
        }
        return cont;
    }

    /**
     * Função que tem como objetivo auxiliar a atribuição de propostas a alunos
     * @return Hashset de Atribuições contendo as atribuiçoes sem docente
     */
    public HashSet<Atribuicao> getAtribuicoesSemDocente(){
        HashSet<Atribuicao> atribuicaoSemDocente= new HashSet<>();
        for(Atribuicao a : atribuicoes) {
            if (a.getDocente() == null){
                atribuicaoSemDocente.add(a);
            }
        }
        return atribuicaoSemDocente;
    }

    /**
     * Função que tem como objetivo auxiliar a atribuição de propostas a alunos
     * @return Hashset de Alunos contendo os alunos sem atribuição
     */
    public HashSet<Aluno> getAlunosDisponiveis(){
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

    /**
     * Obter as autopropostas e o aluno que as propos
     * @return String com todas as autopropostas
     */
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


    public ArrayList<Proposta> toStringAutopropostasTV(){
        ArrayList<Proposta> Autopropostos = new ArrayList<>();
        String buffer;
        for(Proposta p : propostas){
            if(p.getClass().getSimpleName().equals("T3")){
                Autopropostos.add(p);
            }
        }
        return Autopropostos;
    }

    /**
     * Retornar o docente a qual pertence o email
     * @param email email do docente
     * @return Docente a qual pertence o email
     */
    public Docente getDocentePorEmailObjeto(String email) {
        for (Docente d: docentes
        ) {
            if (d.getEmail_Docente().equals(email))
                return d;
        }
        return null;
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

    public String toStringCandidaturas() {
        return "Candidaturas:\n" + candidaturas;
    }

    public String toStringAtribuicoes() {
        return "Atribuicoes:\n" + atribuicoes;
    }

    public String toStringAtribuicoesSemDocente() {
        return getAtribuicoesSemDocente().toString();
    }

    public String toStringAlunosSemAtribuicao() {
        return getAlunosDisponiveis().toString();
    }

    public String toStringPropostasSemAtribuicao() {
        return retornaPropostasDisponiveis().toString();
    }

    /**
     * Função de obtenção dos dados remetentes aos alunos com candidaturas registadas
     * @return ArrayList de Alunos
     */
    public HashSet<Aluno> alunosCandidaturaRegistada(){
        HashSet<Aluno> alunosD= new HashSet<>();
        for(Aluno a:alunos) {
            for (Candidatura ca : candidaturas)
                if (a.getNr_Aluno() == ca.getAluno().getNr_Aluno())
                    alunosD.add(a);
        }
        return alunosD;
    }

    /**
     * Função de obtenção dos dados remetentes aos alunos sem candidaturas registadas
     * @return ArrayList de Alunos
     */
    public HashSet<Aluno> alunosSemCandidaturaRegistada(){
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



    /**
     * Função que tem como objetivo auxiliar a atribuição de propostas a alunos
     * @return Hashset de Propostas contento as propostas sem Atribuição
     */
    public HashSet<Proposta> retornaPropostasDisponiveis(){
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

    /**
     * Função que tem como objetivo auxiliar o avanço da fase de configuração
     * @return Boolean true->fase pode fechar, false->fase nao pode fechar
     */
    public boolean condicaoAvancar(){
        boolean verificado=true;
        String[] ramos = {"DA", "SI", "RAS"};
        for(int i=0;i<ramos.length;i++) {
            if (getNrPropostas(ramos[i]) < getNrAlunos(ramos[i]) && verificado == true || (getNrPropostas(ramos[i])==0 && getNrAlunos(ramos[i])==0 && verificado == true)){
                  verificado=false;
            }
        }
            return verificado;
    }

    /**
     * Verificar se o numero esta associado a uma candidatura
     * @param nr_Aluno numero do aluno a verificar
     * @return true se aluno possuir candidatura,false se não
     */
    public boolean verificaAlunoJaCandidato(long nr_Aluno){
        for (Candidatura x : candidaturas) {
            if(x.getNraluno() == nr_Aluno){
                return true;
            }
        }
        return false;
    }


    /**
     * Verificar se o email pertence a algum docente
     * @param email email do docente
     * @return true se existe, false se não
     */
    public boolean verificaEmailDocente(String email) {
        for (Docente d: docentes
        ) {
            if (d.getEmail_Docente().equals(email))
                return true;
        }
        return false;
    }

    /**
     * Verifica se um aluno existe
     * @param nr numero do aluno a verificar
     * @return true se aluno existe, false se não
     */
    public boolean verificaAlunoExiste(long nr) {
        if(alunos.size() == 0)
            return false;
        for (Aluno d: alunos) {
            if (d.getNr_Aluno() == nr)
                return true;
        }
        return false;
    }

    /**
     * Retornar o aluno a qual pertence o email
     * @param email email do aluno
     * @return false caso email ja exista true caso nao exista
     */
    public boolean getAlunoPorEmail(String email) {
        for (Aluno d : alunos) {
            if (d.getEmail_Aluno().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verificar se aluno ja esta presente em alguma proposta
     * @param nrAluno numero do aluno
     * @return true se aluno estiver associado a proposta, false se não
     */
    public boolean verificaNumeroProposta(Long nrAluno){
        for (Proposta x: propostas) {
            if(x.getClass().getSimpleName().equals("T3") || x.getClass().getSimpleName().equals("T2") || x.getClass().getSimpleName().equals("T1")){
                if(nrAluno.equals(x.getCodigo_Aluno())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verificar se um id ja existe nas propostas
     * @param IDPro id a analisar
     * @return true se ja existir, false se não
     */
    public boolean verificaIdProposta(String IDPro){
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

    /**
     * Adicionar alunos ao array de alunos
     * @param aluno aluno a adicionar
     * @return true se adicionou, false se não
     */
    public boolean adicionarAlunos(Aluno aluno){
        return alunos.add(aluno);
    }

    /**
     * Adicionar Docentes ao array de docentes
     * @param docente Docente a adicionar
     * @return true se adicionou, false se não
     */
    public boolean adicionarDocentes(Docente docente) {
        return docentes.add(docente);
    }

    /**
     * Adicionar alunos ao array de Propostas
     * @param proposta Proposta a adicionar
     * @return true se adicionou, false se não
     */
    public boolean adicionarProposta(Proposta proposta){
        return propostas.add(proposta);
    }

    /**
     * Adicionar alunos ao array de Candidaturas
     * @param candidatura Candidatura a adicionar
     * @return true se adicionou, false se não
     */
    public boolean adicionarCandidatura(Candidatura candidatura) {
        return candidaturas.add(candidatura);
    }

    /**
     * Remover aluno do array de Alunos
     * @param nr_aluno numero do aluno a remover
     * @return true se removeu, false se nao
     */
    public boolean removerAlunos(long nr_aluno){
        ArrayList<String> Eliminadas = new ArrayList<>();
        for (Candidatura candidatura: candidaturas){
            if (candidatura.getNraluno().equals(nr_aluno)){
                candidaturas.remove(candidatura);
                break;
            }
        }
        for (Proposta proposta:propostas) {
            if (proposta.getCodigo_Aluno() != null && proposta.getCodigo_Aluno().equals(nr_aluno))
                if(proposta.getClass().getSimpleName().equals("T3")) {
                    Eliminadas.add(proposta.getCod_ID());
                }else if (proposta.getClass().getSimpleName().equals("T2") || proposta.getClass().getSimpleName().equals("T1"))
                    proposta.setCodigo_Aluno(null);
        }
        for(String s:Eliminadas){
            removerProposta(s);
        }
        return alunos.remove(getAlunoPorNumero(nr_aluno));
    }

    /**
     * Remover docente do array de docentes
     * @param email email do docente a remover
     * @return true se removeu, false se nao
     */
    public boolean removerDocente(String email){
        ArrayList<String> Eliminadas = new ArrayList<>();
        //Eliminadas.clear();
        for(Proposta p:propostas){
            if (email.equals(p.getEmail_Docente())){
                if(p.getClass().getSimpleName().equals("T2")) {
                    Eliminadas.add(p.getCod_ID());
                }else if(p.getClass().getSimpleName().equals("T1") || p.getClass().getSimpleName().equals("T3"))
                    p.setEmail_Docente(null);
            }
        }

            for(String s:Eliminadas){
                    removerProposta(s);
            }

        //nao funciona direito ... pq ao apagar a proposta altera o size das propostas e o ciclo for para de correr lg nao chega a remover o docente

        return docentes.remove(getDocentePorEmailObjeto(email));
    }

    /**
     * Remover Proposta do array de propostas
     * @param cod_ID id da proposta
     * @return true se removeu, false se não
     */
    public boolean removerProposta(String cod_ID){
        String tipo = getPropostaPorId(cod_ID).getClass().getSimpleName();
        Proposta aux = getPropostaPorId(cod_ID);
        switch (tipo){
            case "T1"->{
                if (aux.getCodigo_Aluno() != null){
                    for (Candidatura c : candidaturas){
                        if (c.getNraluno().equals(aux.getCodigo_Aluno())){
                            c.getPropostas().remove(aux);
                        }
                    }
                }
                propostas.remove(aux);
                return true;
            }
            case "T2"->{
                getDocentePorEmailObjeto(aux.getEmail_Docente()).setPapel_Docente(false);
                getDocentePorEmailObjeto(aux.getEmail_Docente()).decContador();
                if (aux.getCodigo_Aluno() !=null){
                    for (Candidatura c : candidaturas){
                        if (c.getNraluno().equals(aux.getCodigo_Aluno())){
                            c.getPropostas().remove(aux);
                        }
                    }
                }
                propostas.remove(aux);
                return true;
            }
            case "T3"->{
                for (Candidatura c : candidaturas){
                    if (c.getNraluno().equals(aux.getCodigo_Aluno())){
                        c.getPropostas().remove(aux);
                    }
                }
                propostas.remove(aux);
                return true;
            }
        }
        return false;
    }

    /**
     * Chama o metodo de lerAlunos da classe Ficheiro
     * @param fileName nome do ficheiro a ler
     * @return numero de elementos lidos
     */
    public int lerficheiroAluno(String fileName) {
        return Ficheiro.lerAlunos(fileName,this);
    }

    /**
     * Chama o metodo de lerDocentes da classe Ficheiro
     * @param fileName nome do ficheiro a ler
     * @return numero de elementos lidos
     */
    public int lerficheiroDocente(String fileName) {
        return Ficheiro.lerDoncentes(fileName,this);
    }

    /**
     * Chama o metodo de lerPropostas da classe Ficheiro
     * @param fileName nome do ficheiro a ler
     * @return numero de elementos lidos
     */
    public int lerficheiroProposta(String fileName) {
        return Ficheiro.lerPropostas(fileName,this);
    }

    /**
     * Chama o metodo de lerCandidaturas da classe Ficheiro
     * @param fileName nome do ficheiro a ler
     * @return numero de elementos lidos
     */
    public int lerficheiroCandidaturas(String fileName){
        return Ficheiro.lercandidaturas(fileName,this);
    }

    /**
     * Verifica se uma propsota contem aluno associado
     * @param id_Proposta id da proposta a verificar
     * @return true se ao tiver aluno, false se não
     */
    public boolean verificaPropostaComAluno(String id_Proposta){
        for (Proposta x : propostas) {
            if (x.getCod_ID().equals(id_Proposta)){
                if(x.getCodigo_Aluno() == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se um aluno esta associado a uma proposta
     * @param codaluno codigo do aluno a verificar
     * @return true se estiver associado, false se não
     */
    public boolean verificaNumeroAssociadoAProposta(String codaluno){
        for (Proposta x : propostas) {
            if (codaluno.equals(x.getCodigo_Aluno())){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para limpar array de erros
     */
    public void limparErros(){
        erros.clear();
    }

    /**
     * Metodo para retornar Lista de erros
     * @return Lista dos erros
     */
    public List<String> getErros() {
        return erros;
    }

    /**
     * metodo para adicionar um erro à lista de erros
     * @param erro string a adicionar
     */
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

    /**
     * Função para obter os alunos autopropostos
     * @return Arraylist de alunos autopropostos
     */
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

    /**
     * Funcao para obter um aluno dado o seu numero
     * @param nr numero do aluno
     * @return aluno a retornar, null se nao existir
     */
    public Aluno getAlunoPorNumero(long nr) {
        for (Aluno d: alunos
        ) {
            if (d.getNr_Aluno() == nr)
                return d;
        }
        return null;
    }

    /**
     * Funcao de obter uma proposta dado o seu id
     * @param id id da proposta
     * @return proposta , null se nao existir
     */
    public Proposta getPropostaPorId(String id){
        for (Proposta p: propostas) {
         if (p.getCod_ID().equals(id))
                return p;
    }
        return null;
    }

    /**
     * Funcao de obter propostas com docentes
     * @return Arraylist de propostas com docentes
     */
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

    /**
     *Funcao de obter propostas sem candidaturas
     * @return Arraylist de propostas sem candidaturas
     */
    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        ArrayList<Proposta> list = new ArrayList<>();
        boolean existe = false;
        for (Proposta x : propostas) {
            existe = false;
            for (Candidatura c : candidaturas) {
                for (int i = 0; i < c.getNrPropostas(); i++) {
                    if (x.getCod_ID().equals(c.getPropostas().get(i).getCod_ID())) {
                        existe = true;
                    }
                }
            }
            if(!existe)
                list.add(x);
        }
        return list;
    }

    /**
     *Funcao de obter propostas com candidaturas
     * @return Arraylist de propostas com candidaturas
     */
    public ArrayList<Proposta> getPropostasComCandidaturas(){
        ArrayList<Proposta> list = new ArrayList<>();
        boolean existelist=false;
        for (Proposta x : propostas) {
            for(Candidatura c:candidaturas){
                for(int i=0;i<c.getNrPropostas();i++){
                    if(x.getCod_ID().equals(c.getPropostas().get(i).getCod_ID())){
                        existelist=false;
                        for(int j=0;j<list.size();j++) {
                            if(list.get(j).getCod_ID() == x.getCod_ID()){
                                existelist=true;
                            }
                        }
                        if(!existelist)
                            list.add(x);
                    }
                }
            }
        }
        return list;
    }

    public void atribuiAutopropostos(){
        for (Candidatura c: candidaturas) {
            if (!verificaCandidaturaAtribuida(c.getAluno())) {
                for (Proposta p : c.getPropostas()) {
                    if (!verificaPropostaAtribuida(p)){
                        if (p.getClass().getSimpleName().equals("T3")) {
                            atribuicoes.add(new Atribuicao(c.getAluno(), null, p));
                        }
                    }
                }
            }
        }
    }

    public void atribuiEstagiosSugeridos(){
        for (Candidatura c: candidaturas) {
            if (!verificaCandidaturaAtribuida(c.getAluno())) {
                for (Proposta p : c.getPropostas()) {
                    if (!verificaPropostaAtribuida(p)){
                        if (p.getClass().getSimpleName().equals("T1") && p.getCodigo_Aluno() != null) {
                            p.setCodigo_Aluno(c.getNraluno());
                            atribuicoes.add(new Atribuicao(c.getAluno(), null, p));
                        }
                    }
                }
            }
        }
    }


    public void atribuiPropostasDocentesCompletas(){
        for (Candidatura c: candidaturas) {
            for (Proposta p: c.getPropostas()) {
                if(p.getClass().getSimpleName().equals("T2") &&
                        p.getCodigo_Aluno() !=null &&
                        c.getAluno().getNr_Aluno() == p.getCodigo_Aluno() &&
                        verificaAlunoAcederProposta(p.getCodigo_Aluno(),p.getCod_ID()) &&
                        !verificaCandidaturaAtribuida(c.getAluno()) &&
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
        for (Aluno aluno: alunos) {
            for (Proposta proposta:propostas) {
                if (!verificaPropostaAtribuida(proposta) &&
                        verificaRamoAlunoProposta(aluno.getNr_Aluno(),proposta.getRamo()) &&
                        !verificaCandidaturaAtribuida(aluno)){
                        proposta.setCodigo_Aluno(aluno.getNr_Aluno());
                        atribuicoes.add(new Atribuicao(aluno,getDocentePorEmailObjeto(proposta.getEmail_Docente()),proposta));
                }
            }
        }
    }

    public boolean atribuiAutomaticamente() {
        conflitos.clear();
        boolean repetido = false;
        for (Candidatura c: candidaturas) {
            if (!verificaCandidaturaAtribuida(c.getAluno())) {
                for (Proposta p : c.getPropostas()) {
                    if (!verificaPropostaAtribuida(p) && !verificaCandidaturaAtribuida(c.getAluno())) {
                        conflitos.add(c.getAluno());
                        for (Candidatura c2 : candidaturas) { //comparar com a segunda dos outros se perder
                            if (!verificaCandidaturaAtribuida(c2.getAluno()) &&
                                    !(c.getAluno().getNr_Aluno() == c2.getAluno().getNr_Aluno()) &&
                                    p.equals(c2.getPropostas().get(0)) &&
                                    c2.getAluno().getClassificacao_Aluno() >= c.getAluno().getClassificacao_Aluno()) {
                                repetido = true;
                                conflitos.add(c2.getAluno());
                            }
                        }
                        if (!repetido) {
                            atribuicoes.add(new Atribuicao(c.getAluno(), getDocentePorEmailObjeto(p.getEmail_Docente()), p));
                            p.setCodigo_Aluno(c.getAluno().getNr_Aluno());
                            //System.out.println("Atribuiu a este cabecudo "+ c.getAluno().getNr_Aluno());
                            conflitos.clear();
                        } else {
                            propostaConflito = p;
                            //ArrayList<String> resultado = ComparaClassificacoes(conflitos, p);
                            if (conflitos != null){
                                conflitoON = true;
                                return true;
                            }
                            propostaConflito = null;
                            conflitos.clear();
                            repetido = false;
                        }
                    }
                }
            }
        }
        conflitos.clear();
        propostaConflito = null;
        conflitoON = false;
        return false;
    }

    public String getCandidaturaPorNrAluno(Long nraluno) {
        for(Candidatura x : candidaturas){
            if(x.getAluno().getNr_Aluno() == nraluno)
                return x.toString();
        }
        return null;
    }

    public Docente getDocenteContadorMenor(){
        Docente docenteMinimo = null;
        int out=0;
        for(Docente docente:docentes) {
            docenteMinimo = docente;
            for (Docente docente2 : docentes) {
                out++;
                if (docente.getContador() > docente2.getContador()) {
                    docenteMinimo = docente2;
                    return docenteMinimo;
                }
                if (out == docentes.size())
                    return docenteMinimo;
            }
            }
        return null;
        }

    public void atribuirDocentesauto() {
        Docente docente = null;
            for (Atribuicao atribuicao : atribuicoes) {
                docente = getDocenteContadorMenor();
                if (docente != null) {
                    if (atribuicao.getDocente() == null) {
                        //Set do email do docente na proposta
                        for (Proposta p : propostas) {
                            if (p.getCod_ID().equals(atribuicao.getProposta().getCod_ID()))
                                p.setEmail_Docente(docente.getEmail_Docente());
                        }
                        atribuicao.setDocente(docente);
                        docente.incContador();
                    }
                }
            }
    }

    public boolean atribuirManualmenteDocente(String docente, int id_atribuicao){
        if (verificaEmailDocente(docente)) {
            for (Atribuicao atribuicao : atribuicoes) {
                if (atribuicao.getId() == id_atribuicao) {
                    if (atribuicao.getDocente() == null) {
                        for(Proposta p:propostas){
                            if(p.getCod_ID().equals(atribuicao.getProposta().getCod_ID())) {
                                p.setEmail_Docente(getDocentePorEmailObjeto(docente).getEmail_Docente());
                                for(Docente d:docentes){
                                    if(d.getEmail_Docente().equals(p.getEmail_Docente())){
                                        d.incContador();
                                    }
                                }

                            }
                        }
                        atribuicao.setDocente(getDocentePorEmailObjeto(docente));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean atribuirManualmenteAluno(long id_aluno, String proposta){
        if (verificaAlunoExiste(id_aluno) &&
                verificaIdProposta(proposta) &&
                verificaAlunoAcederProposta(id_aluno,proposta) &&
                !verificaPropostaAtribuida(getPropostaPorId(proposta)) &&
                !verificaCandidaturaAtribuida(getAlunoPorNumero(id_aluno))){
                getPropostaPorId(proposta).setCodigo_Aluno(id_aluno);
                atribuicoes.add(new Atribuicao(getAlunoPorNumero(id_aluno),getDocentePorEmailObjeto(getPropostaPorId(proposta).getEmail_Docente()),getPropostaPorId(proposta)));
            return true;
        }
        return false;
    }

    public String getAtribuicaoPorIdString(int id) {
        for (Atribuicao a:atribuicoes) {
            if (a.getId() == id){
                return a.toString();
            }
        }
        return null;
    }

    public boolean verificaAlunoAcederProposta(long nr_aluno, String id_proposta) {
        if (getAlunoPorNumero(nr_aluno).isAceder_a_Estagio()) {
            return true;
        }else if (getPropostaPorId(id_proposta).getClass().getSimpleName().equals("T2")){
            return true;
        }
        return false;
    }

    public boolean verificaAlunoAcederPropostaLeitura(long nr_aluno, String id_proposta) {
        if (getAlunoPorNumero(nr_aluno).isAceder_a_Estagio()) {
            return true;
        }else if (id_proposta.equals("T2")){
            return true;
        }
        return false;
    }

    public void exportarAlunos(String fileName){
        Ficheiro.exportarAlunos(fileName,this);
    }

    public void exportarPropostas(String fileName){
        Ficheiro.exportarPropostas(fileName,this);
    }

    public void exportarDocentes(String fileName){
        Ficheiro.exportarDocentes(fileName,this);
    }

    public void exportarCandidaturas(String filename){
        Ficheiro.exportarCandidaturas(filename,this);
    }

    public void exportarInfoFinal(String filename) {
        Ficheiro.exportarInfoFinal(filename, this);
    }

    public ArrayList<Proposta> getPropostasAutopropostos() {
        ArrayList<Proposta> props = new ArrayList<>();
        for(Proposta p:propostas){
                if(p.getClass().getSimpleName().equals("T3"))
                    props.add(p);
            }
        return props;
    }

    public ArrayList<Aluno> getAlunosComPropostas() {
        ArrayList<Aluno> alunosProposta = new ArrayList<>();
            for(Aluno a:alunos){
                for(Atribuicao at:atribuicoes){
                    if(a.getNr_Aluno() == at.getAluno().getNr_Aluno()){
                            alunosProposta.add(a);
                            }
                        }
                    }
            return alunosProposta;
    }

    public ArrayList<Aluno> getAlunosSemPropostas() {
        ArrayList<Aluno> alunosSemProposta = new ArrayList<>();
        boolean existe=false;
        for (Aluno a:alunos){
            existe=false;
            for(Atribuicao at:atribuicoes){
                if(a.getNr_Aluno() == at.getAluno().getNr_Aluno()){
                    existe=true;
                }
            }
            if(!existe){
                alunosSemProposta.add(a);
            }
        }
        return alunosSemProposta;
    }

    public ArrayList<Proposta> getPropostasDisponiveis() {
        ArrayList<Proposta> PropostasDisponiveis = new ArrayList<>();
        boolean existe=false;
        PropostasDisponiveis.clear();
        for(Proposta p:propostas){
            existe=false;
            for(Atribuicao at:atribuicoes){
                if(p.getCod_ID() == at.getProposta().getCod_ID()){
                    existe=true;
                }
            }
            if(!existe)
                PropostasDisponiveis.add(p);
        }
        return PropostasDisponiveis;
    }

    public ArrayList<Proposta> getPropostasAtribuidas() {
        ArrayList<Proposta> PropostasAtribuidas = new ArrayList<>();
        for(Proposta p:propostas){
            for(Atribuicao at:atribuicoes){
                if(p.getCod_ID() == at.getProposta().getCod_ID())
                    PropostasAtribuidas.add(p);
            }
        }
        return PropostasAtribuidas;
    }

    public ArrayList<Atribuicao> getAlunosComOrientador() {
        ArrayList<Atribuicao> ComOrientador = new ArrayList<>();
            for(Atribuicao at:atribuicoes){
                if(at.getDocente() != null){
                    ComOrientador.add(at);
                }
            }
        return ComOrientador;
        }

    public ArrayList<Atribuicao> getAlunosSemOrientador() {
        ArrayList<Atribuicao> SemOrientador = new ArrayList<>();
        for(Atribuicao at:atribuicoes){
            if(at.getDocente() == null){
                SemOrientador.add(at);
            }
        }
        return SemOrientador;
    }

    public String getNumeroDeOrientacoes() {
        ArrayList<String> Orientadores = new ArrayList<>();
        String buffer;
        int flag=0;
        int minimo=0,maximo=0,media=0;
            for(Docente doc:docentes){
                media=media+doc.getContador();
                if(flag==0){
                    minimo=doc.getContador();
                    maximo:doc.getContador();
                    flag++;
                }

                 if(doc.getContador() <= minimo)
                     minimo=doc.getContador();
                 if(doc.getContador() >= maximo)
                     maximo=doc.getContador();
                 buffer ="Docente " + doc.getNome_Docente() + " rege [" + doc.getContador() + "] Projetos/Estagios" + "\n";
                Orientadores.add(buffer);
            }
            if (docentes.size() != 0)
                media = (media/docentes.size());
            buffer = "Minimo de atribuiçoes [" + minimo + "]\n" + "Maximo de atribuiçoes [" + maximo + "]\n" + "Media de atribuições [" + media + "]";
            Orientadores.add(buffer);
        return String.valueOf(Orientadores);
    }

    public Aluno validarAluno(String nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio, String curso){
        if (nr_Aluno.length() == 10 && //se o numero tem 10 digitos
                !verificaAlunoExiste(Long.parseLong(nr_Aluno)) && //se o numero ja nao se encontra noutro aluno
                nome_Aluno.contains(" ") &&  //se tem um espaco entre os dois nomes (lg tem 2 nomes)
                //email deve ter a + numero de aluno, seguido de @isec.pt
                email_Aluno.contains("@isec.pt") && //email valido
                !getAlunoPorEmail(email_Aluno) && //Verifica se email existe
                !curso.isEmpty() && //se nao esta vazio
                (curso.toUpperCase(Locale.ROOT).equals("LEI-PL") || curso.toUpperCase(Locale.ROOT).equals("LEI")) && //ver se o Lei esta correto
                classificacao_Aluno <= 1 && //classificao menor q 1
                (aceder_a_Estagio == true ||
                aceder_a_Estagio == false) &&
                (ramo_Aluno.toUpperCase(Locale.ROOT).equals("DA") || ramo_Aluno.toUpperCase(Locale.ROOT).equals("SI") || ramo_Aluno.toUpperCase(Locale.ROOT).equals("RAS"))//ramo valido
        ) {
            return new Aluno(Long.parseLong(nr_Aluno), nome_Aluno, email_Aluno, ramo_Aluno,classificacao_Aluno,aceder_a_Estagio,curso);
    }
        return null;
    }

    public Docente validarDocente(String nome_Docente, String email_Docente, boolean papel_Docente){
        if (nome_Docente.contains(" ") &&  //sem nome
                email_Docente.contains("@isec.pt") && //email valido
                !getDocentes().contains(email_Docente)//email existe
        ) {
            return new Docente(nome_Docente,email_Docente,papel_Docente);
        }
        return null;
    }

    public Proposta validarProposta(String tipo,String cod_ID, String titulo, Long codigo_Aluno, String email_Docente, String ramo,String empresa){
        switch (tipo) {
            case "T1" -> {
                if (codigo_Aluno == null && //nao tem aluno
                        !verificaIdProposta(cod_ID) && //id da proposta repetido
                        ((ramo.length() > 3 && ramo.contains("|")) || (ramo.length() <= 3 && Ramos.contains(ramo)))) //ver se tem mais q um ramo associado
                {
                    return new T1(ramo,titulo,cod_ID,empresa);
                } else if(codigo_Aluno != null &&
                        verificaAlunoExiste(codigo_Aluno) &&
                        !verificaIdProposta(cod_ID) && //id da proposta repetido
                        verificaRamoAlunoProposta(codigo_Aluno,ramo) &&
                        verificaAlunoAcederPropostaLeitura(codigo_Aluno,cod_ID) && //Verifica proposta durante a leitura
                        ((ramo.length() > 3 && ramo.contains("|")) || (ramo.length() <= 3 && Ramos.contains(ramo)))) //ver se tem mais q um ramo associado
                {
                    return new T1(ramo, titulo, cod_ID ,codigo_Aluno,empresa);
                } else {
                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                    //gestaoProj.setErros("[Erro] no seguinte Proposta" + Arrays.toString(data) + "\n");
                }
            }
            case "T2" -> {
                if (codigo_Aluno == null &&
                        !verificaIdProposta(cod_ID) && //id da proposta repetido
                        verificaEmailDocente(email_Docente) && //email de um docente valido
                        ((ramo.length() > 3 && ramo.contains("|"))  || (ramo.length() <= 3 && Ramos.contains(ramo)))) //ramos associados
                {
                    //Definir o docente como proponente do projeto
                    getDocentePorEmailObjeto(email_Docente).setPapel_Docente(true);
                    //Subir contador
                    getDocentePorEmailObjeto(email_Docente).incContador();
                    return new T2(cod_ID, titulo, ramo, email_Docente);
                } else if (codigo_Aluno != null &&
                        verificaEmailDocente(email_Docente) && //email de um docente valido
                        !verificaIdProposta(cod_ID) && //id da proposta repetido
                        verificaRamoAlunoProposta(codigo_Aluno,ramo) &&
                        verificaAlunoExiste(codigo_Aluno) && //numero de aluno valido
                        ((ramo.length() > 3 && ramo.contains("|"))  || (ramo.length() <= 3 && Ramos.contains(ramo)))) //ramos associado
                {
                    //Definir o docente como proponente do projeto
                    getDocentePorEmailObjeto(email_Docente).setPapel_Docente(true);
                    //Subir contador
                    getDocentePorEmailObjeto(email_Docente).incContador();
                    //Adicionar durante  leitura das propostas
                    adicionarCandidatura(new Candidatura(getAlunoPorNumero(codigo_Aluno),getPropostaPorId(cod_ID)));
                    return new T2(cod_ID,titulo,ramo,email_Docente,codigo_Aluno);
                } else {
                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                    //gestaoProj.setErros("[Erro] no seguinte Proposta" + Arrays.toString(data) + "\n");
                }
            }
            case "T3" -> {
                if (verificaAlunoExiste(codigo_Aluno) && //numero de aluno valido
                        !verificaIdProposta(cod_ID) && //id da proposta repetido
                        !verificaNumeroProposta(codigo_Aluno)) //se aluno ja nao esta associado a um T3
                {
                    adicionarCandidatura(new Candidatura(getAlunoPorNumero(codigo_Aluno),getPropostaPorId(cod_ID)));
                    return new T3(cod_ID, titulo, codigo_Aluno,getAlunoPorNumero(codigo_Aluno).getRamo_Aluno());
                } else {
                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                    //gestaoProj.setErros("[Erro] no seguinte Proposta" + Arrays.toString(data) + "\n");
                }
            }
        }
        return null;
    }

    public boolean verificaPropostaEmCandidatura(String nrAluno,String codId){
        for (Candidatura candidatura: candidaturas) {
            if (nrAluno.equals(candidatura.getNralunoString())){
                for (Proposta proposta:candidatura.getPropostas()) {
                    if (codId.equals(proposta.getCod_ID())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Candidatura validarCandidatura(String nrAluno,String codId){
        //falta validar
        //Validar aluno
        if(verificaAlunoExiste(Long.parseLong(nrAluno)) && //Aluno existe
                !verificaCandidaturaAtribuida(getAlunoPorNumero(Long.parseLong(nrAluno)))&&//Verifica se ja existe nas candidaturas
                !verificaPropostaEmCandidatura(nrAluno,codId) && //repetida
                !verificaNumeroAssociadoAProposta(nrAluno)){ //se ja esta associada
            //Validar proposta
            if(verificaIdProposta(codId) &&
            verificaRamoAlunoProposta(Long.parseLong(nrAluno),getPropostaPorId(codId).getRamo()) &&
            verificaAlunoAcederProposta(Long.parseLong(nrAluno),codId) &&
            verificaPropostaComAluno(codId)){
                return new Candidatura(getAlunoPorNumero(Long.parseLong(nrAluno)), getPropostaPorId(codId));
            }
            return null;
        }
        return null;
    }

    public boolean removerPropostaDeCandidatura(String nr_Aluno, String id_proposta){
        //So nao remove Proposta de T3 e de T2 com proposta com aluno associado!
        for (Candidatura candidatura: candidaturas){
            if (candidatura.getNralunoString().equals(nr_Aluno)){
                if(candidatura.getNrPropostas() == 1 && (candidatura.getPropostas().get(0).getClass().getSimpleName().equals("T3") || (candidatura.getPropostas().get(0).getClass().getSimpleName().equals("T2")) && nr_Aluno.equals(candidatura.getPropostas().get(0).getCodigo_Aluno())))
                    return false;
                candidatura.removeProposta(id_proposta);
                if(candidatura.getPropostas().size() == 0){
                    candidaturas.remove(candidatura);
                }
                return true;
            }
        }
        return false;
    }

    public boolean verificaRamoAlunoProposta(long nraluno, String ramo) {
        String[] nova;
        if(ramo.contains("|")) {
            nova = ramo.split("\\|");
            for(Aluno a :alunos){
                if(a.getNr_Aluno() == nraluno) {
                    for(int i=0;i<nova.length;i++){
                        if(nova[i].equals(a.getRamo_Aluno()))
                            return true;
                    }
                }

            }
        }else {
            for (Aluno a : alunos) {
                if (a.getNr_Aluno() == nraluno) {
                    if (a.getRamo_Aluno().equals(ramo)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removerCandidatura(Long nrAluno) {
        for(Candidatura c:candidaturas){
            if(c.getAluno().getNr_Aluno() == nrAluno) {
                if (c.getNrPropostas() <= 1) //Elimina  a possibilidade de remover T2 com aluno e T3 com aluno
                {
                        if (c.getPropostas().get(0).getClass().getSimpleName().equals("T3") || (c.getPropostas().get(0).getClass().getSimpleName().equals("T2") && nrAluno.equals(c.getPropostas().get(0).getCodigo_Aluno()))) {
                            return false;
                        } else {
                            c.getPropostas().get(0).setCodigo_Aluno(null); // se a proposta for do Tipo T1,fica disponivel para mais alunos
                            candidaturas.remove(c);
                            return true;
                        }
                } else {
                    candidaturas.remove(c);
                    return true;
                }
            }
        }
        return false;

    }

    public StringBuilder getCandidaturasPrintEasy() {
        //Exploração de varios tipos de string
        ArrayList view = new ArrayList();
        view.add("\nCandidaturas:\n");
        for(Candidatura c :candidaturas){
            view.add("\nAluno: "+ c.getAluno().getNr_Aluno() + " Propostas:" + c.getIdPropostas());
        }
        view.add("\n");
        String aux = String.valueOf(view);
        aux = aux.replaceAll(",","");
        StringBuilder aux2 = new StringBuilder(aux);
        aux2.deleteCharAt(0);
        aux2.deleteCharAt(aux2.length()-1);
        return aux2;
    }
    public StringBuilder getAtribuicoesPrintEasy(){
        ArrayList view = new ArrayList();
        view.add("\nAtribuições:\n");
        for(Atribuicao a :atribuicoes){
            view.add("ID:{" + a.getId() +"}:");
            view.add("\nAluno: "+ a.getAluno().getNome_Aluno() + " Nrº-" +a.getAluno().getNr_Aluno());
            view.add("\n");
            if(a.getDocente()!=null) {
                view.add("Docente:" + a.getDocente().getNome_Docente() + ", Email:" + a.getDocente().getEmail_Docente());
                view.add("\n");
            }
            view.add("Proposta Atribuida:["+ a.getProposta().getClass().getSimpleName() + "]" + a.getProposta().getCod_ID());
            view.add("\n");
        }
        String aux = String.valueOf(view);
        aux = aux.replaceAll(",","");
        StringBuilder aux2 = new StringBuilder(aux);
        aux2.deleteCharAt(0);
        aux2.deleteCharAt(aux2.length()-1);
        return aux2;
    }

    public boolean adicionarPropostaACandidatura(String nr_aluno, String idProp) {
        for(Candidatura c :candidaturas){
            if(c.getNralunoString().equals(nr_aluno)) {
                if (c.getPropostas().get(0).getClass().getSimpleName().equals("T3") || (c.getPropostas().get(0).getClass().getSimpleName().equals("T2") && nr_aluno.equals(c.getPropostas().get(0).getCodigo_Aluno())))
                    return false;
                    if (!verificaPropostaEmCandidatura(nr_aluno,idProp) &&
                            verificaIdProposta(idProp) &&
                            verificaPropostaComAluno(idProp) &&
                            verificaRamoAlunoProposta(Long.parseLong(nr_aluno),getPropostaPorId(idProp).getRamo())) {
                            c.getPropostas().add(getPropostaPorId(idProp));
                        return true;
                    }
                }
        }
        return false;
    }

    public boolean removerAtribuicao(String nrAluno) {
        for(Atribuicao a :atribuicoes){
            if(a.getAluno().getNr_AlunoString().equals(nrAluno) && a.getProposta().getClass().getSimpleName().equals("T1")){
                a.getProposta().setCodigo_Aluno(null);
                atribuicoes.remove(a);
                return true;
            }
        }
        return false;
    }

    public Atribuicao getAtribuicaoporId(int id){
        for(Atribuicao a :atribuicoes){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    public int removerAllAtribuicao(){
        int elim=0;
        ArrayList<Integer> s = new ArrayList<>();
        for(Atribuicao a :atribuicoes) {
            if (a.getProposta().getClass().getSimpleName().equals("T1")) {
                a.getProposta().setCodigo_Aluno(null);
                s.add(a.getId());
                elim++;
            }
        }
        if(elim>0){
            for(int i=0;i<s.size();i++)
                atribuicoes.remove(getAtribuicaoporId(s.get(i)));
        }
        return elim;
    }

    public boolean removerDocenteAtribuido(int idProp) {
        for(Atribuicao a:atribuicoes){
            if(a.getId() == idProp) {
                if (a.getDocente() != null) {
                    for (Proposta p : propostas) {
                        if (p.getCod_ID().equals(a.getProposta().getCod_ID())) {
                            for(Docente d:docentes){
                                if(d.getEmail_Docente().equals(p.getEmail_Docente()))
                                    d.decContador();
                            }
                            p.setEmail_Docente(null);
                        }
                    }
                    a.setDocente(null);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<String> getPropostasChart() {
        ArrayList<String> entidades = new ArrayList<String>();
        for (Proposta proposta: propostas) {
            if(proposta.getClass().getSimpleName().equals("T1"))
                entidades.add(proposta.getEmpresa());
        }
        return new ArrayList<String>(entidades);
    }

    public ArrayList<String> getDocentesChart() {
        ArrayList<String> entidades = new ArrayList<String>();
        for(Docente d:docentes) {
            for (Atribuicao a : atribuicoes) {
                if (a.getDocente().getEmail_Docente().equals(d.getEmail_Docente())){
                    entidades.add(d.getEmail_Docente());
                }
            }
        }
        return new ArrayList<String>(entidades);
    }

    public int contaPropostaRAS(){
        int contador = 0;
        if (propostas == null || propostas.size() == 0)
            return 0;
        for (Proposta proposta : propostas) {
            if (proposta.getRamo().contains("RAS"))
                contador++;
        }
        return contador;
    }

    public int contaPropostaDA(){
        int contador = 0;
        if (propostas == null || propostas.size() == 0)
            return 0;
        for (Proposta proposta : propostas) {
            if (proposta.getRamo().contains("DA"))
                contador++;
        }
        return contador;
    }

    public int contaPropostaSI(){
        int contador = 0;
        if (propostas == null || propostas.size() == 0)
            return 0;
        for (Proposta proposta : propostas) {
            if (proposta.getRamo().contains("SI"))
                contador++;
        }
        return contador;
    }

    public int contaPropostasAtribuidas(){
        int contador=0;
        for(Atribuicao a:atribuicoes){
            for(Proposta p:propostas){
                if(a.getProposta().getCod_ID().equals(p.getCod_ID()))
                    contador++;
            }
        }
        return contador;
    }

    public int contaPropostasNaoAtribuidas(){
        int contador=0;
        boolean existe=false;
        for(Atribuicao a:atribuicoes){
            existe=false;
            for(Proposta p:propostas){
                if(a.getProposta().getCod_ID().equals(p.getCod_ID()))
                    existe=true;
            }
            if(!existe)
                contador++;
        }
        return contador;
    }

    public HashMap<String, Integer> contaEstagios() {
        HashMap<String, Integer> pares = new HashMap<>();
        int frequencia = 0;
        List<String> teste = getPropostasChart();
        //int maior = 0;
        if (propostas==null)
            return null;
        for (Proposta proposta : propostas) {
            if(proposta.getClass().getSimpleName().equals("T1")) {
                frequencia = Collections.frequency(teste, (proposta.getEmpresa()));
                pares.put(proposta.getEmpresa(), frequencia);
            }
        }
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(pares.entrySet());
        Collections.sort(list, Map.Entry.comparingByValue());
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public HashMap<String, Integer> contaOrientacoes() {
        HashMap<String, Integer> pares = new HashMap<>();
        int frequencia = 0;
        List<String> teste = getDocentesChart();
        if (docentes == null)
            return null;
        for (Docente d : docentes) {
            for (Atribuicao a : atribuicoes) {
                if (a.getDocente().getEmail_Docente().equals(d.getEmail_Docente())) {
                    frequencia = Collections.frequency(teste, d.getEmail_Docente());
                    pares.put(d.getEmail_Docente(), frequencia);
                }
            }
        }
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(pares.entrySet());
        Collections.sort(list, Map.Entry.comparingByValue());
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}