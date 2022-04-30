package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Atribuicao;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;
import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.ArrayList;
import java.util.List;

public class ProContexto {
    private GestaoProj dados;
    private IState state;

    public ProContexto() {
        dados = new GestaoProj();
        state = new ConfiguracaoState(dados,this);
    }

    public PoeState getState(){
        return state.getState();
    }

    public void alterarState(IState novoState){
        state = novoState;
    }

    //conjunto de metodos correspondentes as transiçoes de estados ... IState
    public boolean selecionar(int escolha){
        return state.selecionar(escolha);
    }

    public boolean voltar(boolean guardado) {
        return state.voltar(guardado);
    }

    public boolean avancar(boolean guardado) {
        return state.avancar(guardado);
    }

    public void lerFicheiro(String fileName){
        state.lerFicheiro(fileName);
    }

    public boolean lerFicheiroDebug(String fileName){
        return state.lerFicheiroDebug(fileName);
    }

    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente) {
        return state.adicionarDocente(nome_Docente, email_Docente, papel_Docente);
    }

    public boolean adicionarProposta(Proposta proposta) {
        return state.adicionarProposta(proposta);
    }

    public boolean adicionarAluno(long nr_Aluno,String nome_Aluno,String email_Aluno,String ramo_Aluno,double classificacao_Aluno,boolean aceder_a_Estagio){
        return state.adicionarAluno(nr_Aluno,nome_Aluno,email_Aluno,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);
    }

    public boolean removerAluno(long nr_aluno){
        return state.removerAluno(nr_aluno);
    }
    

    //metodos para aceder a dados... para o UI
    public List<String> getErros(){
        return dados.getErros();
    }

    public void limparErros(){
        dados.limparErros();
    }

    public String getAlunos(){
        return dados.toStringAlunos();
    }

    public String getDocentes(){
        return dados.toStringDocentes();
    }

    public String getPropostas() {
        return dados.toStringPropostas();
    }

    public String getCandidaturas() {
        return dados.toStringCandidaturas();
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

    public ArrayList<Proposta> getPropostasSemCandidatos() {
        return dados.getPropostasSemCandidatos();
    }

    public void atribuiAutopropostos() {
        dados.atribuiAutopropostos();
    }

    public void atribuiPropostasDocentes() {
        dados.atribuiPropostasDocentes();
    }

    public String getAtribuicoesPropostas() {
        return dados.toStringAtribuicoes();
    }

    public ArrayList<String> atribuiAutomaticamente() {
        return dados.atribuiAutomaticamente();
    }

    public String getAlunoPorNr(String nr) {
        return dados.getAlunoPorNumero(Long.parseLong(nr)).toString();
    }

    public String getCandidaturaPorNRAluno(String nraluno){
        return dados.getCandidaturaPorNrAluno(Long.parseLong(nraluno));
    }

    public void atribuiAlunoAProposta(String escolhido, String Id_Proposta) {
        dados.atribuiPropostaAluno(escolhido,Id_Proposta);
    }

    public void atribuirSemCandidatura(){
        dados.atribuirSemCandidatura();
    }

    public void atribuirDocentesauto() {
        dados.atribuirDocentesauto();
    }

    public boolean atribuirManualmenteDocente(String docente, int id_atribuicao){
        return dados.atribuirManualmenteDocente(docente,id_atribuicao);
    }

    public boolean atribuirManualmenteAluno(long id_aluno, String proposta){
        return dados.atribuirManualmenteAluno(id_aluno,proposta);
    }

    public String getAtribuicoesPropostasSemDocente() {
        return dados.toStringAtribuicoesSemDocente();
    }

    public String getAtribuicaoPorId(int id){
        return dados.getAtribuicaoPorId(id);
    }

    public String getAlunosSemAtribuicao(){
        return dados.toStringAlunosSemAtribuicao();
    }

    public String getPropostasNaoAtribuidas() {
        return dados.toStringPropostasSemAtribuicao();
    }

    public String getAlunosAutopropostosString(){
        return String.valueOf(dados.AlunosComAutoproposta());
    }
    public String getAlunosComCandidaturaString(){
        return String.valueOf(dados.AlunosCandidaturaRegistada());
    }

    public String getAlunosSemCandidaturaString(){
        return String.valueOf(dados.AlunosSemCandidaturaRegistada());
    }
}
