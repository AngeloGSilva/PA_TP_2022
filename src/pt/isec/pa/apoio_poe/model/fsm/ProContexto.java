package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ProContexto {
    private GestaoProj dados;
    private IState state;
    private Deque<memento> stateHistory;

    public ProContexto() {
        dados = new GestaoProj();
        state = new ConfiguracaoState(dados,this);
        stateHistory = new LinkedList<>();
    }

    public PoeState getState(){
        return state.getState();
    }

    public void alterarState(IState novoState){
        state = novoState;
    }

    //conjunto de metodos correspondentes as transiçoes de estados ... IState
    public boolean selecionar(PoeState escolha){
        return state.selecionar(escolha);
    }

    public void resolverConflito(int selecionado){state.resolverConflito(selecionado);}

    public boolean AtribuirAutomaticamente(){ return state.AtribuirAutomaticamente();}

    public boolean voltar(boolean guardado) {
        return state.voltar(guardado);
    }

    public boolean avancar(boolean guardado) {
        return state.avancar(guardado);
    }

    public int lerFicheiro(String fileName){
        return state.lerFicheiro(fileName);
    }

    public void editar(String identificador,String editado) {
        state.editar(identificador,editado);
    }

//State
    public void load() {
        StateFactory name = new StateFactory();
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("Loadsave.bin"))) {
            GestaoProj newapp = (GestaoProj) ois.readObject();
            dados = newapp;
            this.alterarState(name.createState(PoeState.valueOf(dados.getStatekeep().toString()),this,dados));
        }
        catch (Exception e){

        }
    }


    public void save() {
        dados.setStatekeep(state.getState());
        try (ObjectOutputStream ous = new ObjectOutputStream(
                new FileOutputStream("Loadsave.bin"))) {
            ous.writeObject(dados);
        }
        catch (Exception e){

        }
    }
    //Memento
    public memento takeSnapshot(){
        return new memento(this.takeSnapshot().state);
    }

    public void restore(){
    }


    public static class memento{
        private final PoeState state;

        private memento (PoeState newstate){
            state = newstate;
        }

        private PoeState getsavedstate(){
            return state;
        }
    }

    public void undo(){}

    public void redo(){}


    public boolean lerFicheiroDebug(String fileName){
        return state.lerFicheiroDebug(fileName);
    }

    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente) {
        return state.adicionarDocente(nome_Docente, email_Docente, papel_Docente);
    }

    public boolean adicionarProposta(String tipo,String cod_ID, String titulo, Long codigo_Aluno, String email_Docente, String ramo,String empresa){
        return state.adicionarProposta( tipo, cod_ID,  titulo,  codigo_Aluno,  email_Docente,  ramo, empresa);
    }

    public boolean adicionarAluno(String nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio, String curso){
        return state.adicionarAluno( nr_Aluno,  nome_Aluno,  email_Aluno,  ramo_Aluno,  classificacao_Aluno,  aceder_a_Estagio,  curso);
    }

    public boolean adicionarCandidatura(String nrAluno,String codId){
        return state.adicionarCandidatura(nrAluno,codId);
    }

    public boolean remover(String eliminar){
        return state.remover(eliminar);
    }

/*
    public boolean removerAluno(long nr_aluno){
        return state.removerAluno(nr_aluno);
    }

    public boolean removerDocente(String emaildoc){
        return state.removerDocente(emaildoc);
    }

    public boolean removerProposta(String codId){
        return state.removerProposta(codId);
    }
*/

    public void atribuirDocentesauto() {
        state.atribuirDocentesauto();
    }

    public boolean atribuirManualmenteDocente(String docente, int id_atribuicao){
        return state.atribuirManualmenteDocente(docente,id_atribuicao);
    }

    public void AtribuirAutomaticoAutopropostosDocentesAluno(){
        state.AtribuirAutomaticoAutopropostosDocentesAluno();
    }

    public boolean atribuirManualmenteAluno(long id_aluno, String proposta){
        return state.atribuirManualmenteAluno(id_aluno,proposta);
    }



    //metodos para aceder a dados... para o UI
    public List<String> getErros(){
        return dados.getErros();
    }

    public void limparErros(){
        dados.limparErros();
    }

    public ArrayList<Aluno> getAlunosTV(){
        return dados.getAlunosTV();
    }

    public String getAlunos(){
        return dados.toStringAlunos();
    }

    public ArrayList<Docente> getDocentesTV(){
        return dados.getDocentesTV();
    }

    public ArrayList<Proposta> getPropostaTV(){
        return dados.getPropostaTV();
    }

    public ArrayList<Candidatura> getCandidaturaTV(){
        return dados.getCandidaturaTV();
    }

    public ArrayList<Atribuicao> getAtribuicoesTV(){
        return dados.getAtribuicoesTV();
    }

    public String getAtribuicoes(){return dados.toStringAtribuicoes();}

    public ArrayList<Candidatura> getCandidaturaTVAuto(){
        return dados.getCandidaturaTVAuto();
    }

    public ArrayList<Aluno> getCandidaturasTVNotReg(){
        return dados.getCandidaturasTVNotReg();
    }

/*   public ArrayList<Candidatura> getCandidaturasTVNotReg(){
        return dados.getCandidaturasTVNotReg();
    }*/

    public String getDocentes(){
        return dados.toStringDocentes();
    }

    public String getPropostas() {
        return dados.toStringPropostas();
    }

    public String getCandidaturas() {
        return dados.toStringCandidaturas();
    }

    public String getPropostaPorID(String id){
        return dados.getPropostaPorId(id).toString();
    }

    public boolean getFase_gestao(){
        return dados.isFase_Fechada_Config();
    }

    public boolean getFase_Candidatura(){
        return dados.isFase_Fechada_Candidatura();
    }

    public boolean getFase_Orientador(){
        return dados.isFase_Fechada_atriOrientador();
    }

    public boolean getFase_Proposta(){
        return dados.isFase_Fechada_atriProposta();
    }

    public ArrayList<Aluno> getAlunosAutopropostos(){
        return dados.getAlunosAutopropostos();
    }

    public ArrayList<Proposta> getPropostasDocentes() {
        return dados.getPropostasDocentes();
    }

    //public ArrayList<Proposta> getPropostasCandidadatos() {
        //return dados.getPropostasCandidatos();
    //}

    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        return dados.getPropostasSemCandidaturas();
    }

    public ArrayList<Proposta> getPropostasComCandidaturas() {
        return dados.getPropostasComCandidaturas();
    }

    public String getAtribuicoesPropostas() {
        return dados.toStringAtribuicoes();
    }

    public String getAlunoPorNr(String nr) {
        return dados.getAlunoPorNumero(Long.parseLong(nr)).toString();
    }

    public String getCandidaturaPorNRAluno(String nraluno){
        return dados.getCandidaturaPorNrAluno(Long.parseLong(nraluno));
    }

    public String getAtribuicoesPropostasSemDocente() {
        return dados.toStringAtribuicoesSemDocente();
    }

    public String getAtribuicaoPorId(int id){
        return dados.getAtribuicaoPorIdString(id);
    }

    public String getAlunosSemAtribuicao(){
        return dados.toStringAlunosSemAtribuicao();
    }

    public String getPropostasNaoAtribuidas() {
        return dados.toStringPropostasSemAtribuicao();
    }

    //public String getAlunosAutopropostosString(){
        //return String.valueOf(dados.AlunosComAutoproposta());
    //}

    public String getAlunosAutopropostosString(){
        return dados.toStringAutopropostas();
    }

    public String getAlunosComCandidaturaString(){
        return String.valueOf(dados.AlunosCandidaturaRegistada());
    }

    public String getAlunosSemCandidaturaString(){
        return String.valueOf(dados.AlunosSemCandidaturaRegistada());
    }


    public void exportarAlunos(String fileName){
        dados.exportarAlunos(fileName);
    }

    public void exportarPropostas(String FileName){
        dados.exportarPropostas(FileName);
    }

    public void exportarDocentes(String Filename){
        dados.exportarDocentes(Filename);
    }

    public void exportarCandidaturas(String FileName){
        dados.exportarCandidaturas(FileName);
    }


    public ArrayList<Proposta> getAutopropostasAlunos() {
        return dados.getPropostasAutopropostos();
    }

    public ArrayList<Aluno> getAlunosPropostaAtribuida() {
        return dados.getAlunosComPropostas();
    }

    public ArrayList<Aluno> getAlunosSemProposta() {
        return dados.getAlunosSemPropostas();
    }

    public ArrayList<Proposta> getPropostasDisponiveis() {
        return dados.getPropostasDisponiveis();
    }

    public ArrayList<Proposta> getPropostasAtribuidas() {
        return dados.getPropostasAtribuidas();
    }

    public ArrayList<Atribuicao> getAlunosComOrientador() {
        return dados.getAlunosComOrientador();
    }

    public ArrayList<Atribuicao> getAlunosSemOrientador() {
        return dados.getAlunosSemOrientador();
    }

    public String getNumerodeOrientacoes() {
        return dados.getNumeroDeOrientacoes();
    }


    public ArrayList<Aluno> getAlunosSemPropostasComCadidaturaNaoAtribuida() {
        return dados.getAlunosSemPropostas();
    }


    public HashSet<Aluno> getConflitos() {
        return dados.getConflitos();
    }

    public ArrayList<Aluno> getConflitosTV() {
        return dados.getConflitosTV();
    }

    public Proposta getPropostaConflito() {
        return dados.getPropostaConflito();
    }

    public boolean isConflitoON() {
        return dados.isConflitoON();
    }

    public StringBuilder getCandidaturasPrintEasy() {
        return dados.getCandidaturasPrintEasy();
    }

    public boolean adicionarPropostaACandidatura(String nrAluno,String codID) {
        return state.adicionarPropostaACandidatura(nrAluno,codID);
    }

    public boolean removerPropostaDeCandidatura(String nr_Aluno, String eliminar){
        return state.removerPropostaDeCandidatura(nr_Aluno,eliminar);
    }

    public Boolean removeAtribuicao(String nrAluno) {
        return state.removeAtribuicao(nrAluno);
    }

    public int removerAllAtribuicao() {
        return state.removerAllAtribuicao();
    }

    public void removeDocenteAtribuido(int IdProp) {
        state.removeDocenteAtribuido(IdProp);
    }

    public boolean VerificaAlunoJaCandidato(long nr_Aluno){
        return dados.VerificaAlunoJaCandidato(nr_Aluno);
    }
}
