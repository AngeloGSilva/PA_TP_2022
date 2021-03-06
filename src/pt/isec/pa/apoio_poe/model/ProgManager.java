package pt.isec.pa.apoio_poe.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;
import pt.isec.pa.apoio_poe.model.fsm.atriPropostaState;
import pt.isec.pa.apoio_poe.model.fsm.opCandidaturaState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class que faz a ponte entre a interface grafica e o modelo de dados (UI - ProgManager - ProContexto - Dados)
 */
public class ProgManager {

    private ProContexto controladorDoPrograma;
    PropertyChangeSupport pcs;

    public ProgManager() {
        controladorDoPrograma = new ProContexto();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }


    public void selecionar(PoeState escolha) {
        controladorDoPrograma.selecionar(escolha);
        pcs.firePropertyChange(null,null,null);
    }


    public boolean getFase_gestao(){
        return controladorDoPrograma.getFase_gestao();
    }

    public boolean getFase_Candidatura(){
        return controladorDoPrograma.getFase_Candidatura();
    }

    public void load(String file_name){
        controladorDoPrograma.load(file_name);
    }

    public void save(String file_name){
        controladorDoPrograma.save(file_name);
    }

    public boolean getFase_Orientador(){
        return controladorDoPrograma.getFase_Orientador();
    }

    public boolean getFase_Proposta(){
        return controladorDoPrograma.getFase_Proposta();
    }


    public int lerFicheiro(String fileName){
        int buf = controladorDoPrograma.lerFicheiro(fileName);
        //System.out.println(controladorDoPrograma.getAlunos());
        //System.out.println(controladorDoPrograma.getDocentes());
        pcs.firePropertyChange(null,null,null);
        return buf;
    }

    public boolean removerProposta(String id_Proposta){
        boolean resultado = controladorDoPrograma.removerProposta(id_Proposta);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public ObservableList<Aluno> getAlunosPropostaAtribuida() {
        ObservableList<Aluno> ob = FXCollections.observableArrayList(controladorDoPrograma.getAlunosPropostaAtribuida());
        return ob;
    }

    public ObservableList<Aluno> getAlunosSemProposta() {
        ObservableList<Aluno> ob = FXCollections.observableArrayList(controladorDoPrograma.getAlunosSemProposta());
        return ob;
    }

    public ObservableList<Proposta> getAutopropostasAlunos() {
        ObservableList<Proposta> ob = FXCollections.observableArrayList(controladorDoPrograma.getAutopropostasAlunos());
        return ob;
    }

    public ObservableList<Aluno> toStringAutopropostasTV() {
        ObservableList<Aluno> ob = FXCollections.observableArrayList(controladorDoPrograma.getAlunosAutopropostos());
        return ob;
    }

    public ObservableList<Aluno> getAlunos() {
        ObservableList<Aluno> ob = FXCollections.observableArrayList(controladorDoPrograma.getAlunosTV());
        return ob;
    }

    public ObservableList<Docente> getDocentes() {
        ObservableList<Docente> ob = FXCollections.observableArrayList(controladorDoPrograma.getDocentesTV());
        return ob;
    }

    public ObservableList<Proposta> getPropostas() {
        ObservableList<Proposta> ob = FXCollections.observableArrayList(controladorDoPrograma.getPropostaTV());
        return ob;
    }

    public ObservableList<Proposta> getPropostasAuto() {
        ObservableList<Proposta> ob = FXCollections.observableArrayList(controladorDoPrograma.getAutopropostasAlunos());
        return ob;
    }

    public ObservableList<Proposta> getPropostasDoc() {
        ObservableList<Proposta> ob = FXCollections.observableArrayList(controladorDoPrograma.getPropostasDocentes());
        return ob;
    }

    public ObservableList<Proposta> getPropostasDisponiveis() {
        ObservableList<Proposta> ob = FXCollections.observableArrayList(controladorDoPrograma.getPropostasDisponiveis());
        return ob;
    }

    public ObservableList<Proposta> getPropostasComCandidatura() {
        ObservableList<Proposta> ob = FXCollections.observableArrayList(controladorDoPrograma.getPropostasComCandidaturas());
        return ob;
    }

    public ObservableList<Proposta> getPropostasSemCandidatura() {
        ObservableList<Proposta> ob = FXCollections.observableArrayList(controladorDoPrograma.getPropostasSemCandidaturas());
        return ob;
    }

    public ObservableList<Candidatura> getCandidaturas() {
        ObservableList<Candidatura> ob = FXCollections.observableArrayList(controladorDoPrograma.getCandidaturaTV());
        return ob;
    }

    public ObservableList<Atribuicao> getAtribuicoes() {
        ObservableList<Atribuicao> ob = FXCollections.observableArrayList(controladorDoPrograma.getAtribuicoesTV());
        return ob;
    }


    public ObservableList<Aluno> getCandidaturasNotReg() {
        ObservableList<Aluno> ob = FXCollections.observableArrayList(controladorDoPrograma.getCandidaturasTVNotReg());
        return ob;
        //return dados.toStringCandidaturas();
    }

    public ObservableList<Candidatura> getCandidaturasAuto() {
        System.out.println(controladorDoPrograma.getCandidaturaTVAuto());
        ObservableList<Candidatura> ob = FXCollections.observableArrayList(controladorDoPrograma.getCandidaturaTVAuto());
        System.out.println("Criar a lista de obs");
        System.out.println(ob);
        return ob;
        //return dados.toStringCandidaturas();
    }

    public ObservableList<Aluno> getConflitosTV() {
        System.out.println(controladorDoPrograma.getConflitosTV());
        ObservableList<Aluno> ob = FXCollections.observableArrayList(controladorDoPrograma.getConflitosTV());
        System.out.println("Criar a lista de obs");
        System.out.println(ob);
        return ob;
        //return dados.toStringCandidaturas();
    }


    public boolean voltar(boolean guardado) {
        controladorDoPrograma.voltar(guardado);
        pcs.firePropertyChange(null,null,null);
        return false;
    }

    public boolean avancar(boolean guardado) {
        boolean resultado = controladorDoPrograma.avancar(guardado);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }


    public boolean AtribuirAutomaticamente(){
        boolean resultado = controladorDoPrograma.AtribuirAutomaticamente();
        pcs.firePropertyChange(null,null,null);
        return resultado;
    };

    public void resolverConflito(int selecionado){
        controladorDoPrograma.resolverConflito(selecionado);
        pcs.firePropertyChange(null,null,null);
    }


    public boolean isConflitoON() {
        return controladorDoPrograma.isConflitoON();
    }


    public boolean lerFicheiroDebug(String fileName){
        return false;
    }



    public boolean adicionarAluno(String nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio, String curso){
        boolean resultado = controladorDoPrograma.adicionarAluno(nr_Aluno, nome_Aluno, email_Aluno, ramo_Aluno, classificacao_Aluno, aceder_a_Estagio, curso);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public boolean remover(String eliminar){
        boolean resultado = controladorDoPrograma.remover(eliminar);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public boolean removerPropostaDeCandidatura(String nr_Aluno, String id_Proposta){
        boolean resultado = controladorDoPrograma.removerPropostaDeCandidatura(nr_Aluno,id_Proposta);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

/*
    public boolean removerAluno(long nr_aluno){
        boolean resultado = controladorDoPrograma.removerAluno(nr_aluno);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public boolean removerDocente(String emaildoc){
        boolean resultado = controladorDoPrograma.removerDocente(emaildoc);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public boolean removerProposta(String codID){
        boolean resultado = controladorDoPrograma.removerProposta(codID);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }*/


    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente){
        boolean resultado = controladorDoPrograma.adicionarDocente(nome_Docente, email_Docente, papel_Docente);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }


    public boolean adicionarProposta(String tipo,String cod_ID, String titulo, Long codigo_Aluno, String email_Docente, String ramo,String empresa){
        boolean resultado = controladorDoPrograma.adicionarProposta( tipo, cod_ID,  titulo,  codigo_Aluno,  email_Docente,  ramo, empresa);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public boolean adicionarCandidatura(String nrAluno,String codId){
        boolean resultado = controladorDoPrograma.adicionarCandidatura(nrAluno,codId);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public boolean adicionarPropostaACandidatura(String nr_Aluno, String idProposta) {
        boolean resultado =  controladorDoPrograma.adicionarPropostaACandidatura(nr_Aluno, idProposta);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }


    public void exportarAlunos(String fileName){
        controladorDoPrograma.exportarAlunos(fileName);
    }

    public void exportarPropostas(String FileName){
        controladorDoPrograma.exportarPropostas(FileName);
    }

    public void exportarDocentes(String Filename){
        controladorDoPrograma.exportarDocentes(Filename);
    }

    public void exportarCandidaturas(String FileName){
        controladorDoPrograma.exportarCandidaturas(FileName);
    }

    public void exportarInfoFinal(String FileName){
        controladorDoPrograma.exportarInfoFinal(FileName);
    }

    public int getIdAtribuicao(String id_Proposta){
        return controladorDoPrograma.getIdAtribuicao(id_Proposta);
    }

    public void editar(String identificador, String editado) {
        controladorDoPrograma.editar(identificador,editado);
        pcs.firePropertyChange(null,null,null);
    }

    public boolean VerificaAlunoJaCandidato(long nr_Aluno){
        return controladorDoPrograma.VerificaAlunoJaCandidato(nr_Aluno);
    }


    public void atribuirDocentesauto() {
        controladorDoPrograma.atribuirDocentesauto();
        pcs.firePropertyChange(null,null,null);
    }

    public Atribuicao getAtribuicaoporId(int id){
        return controladorDoPrograma.getAtribuicaoporId(id);
    }

    public boolean atribuirManualmenteDocente(String docente, int id_atribuicao){
        boolean res = controladorDoPrograma.atribuirManualmenteDocente(docente,id_atribuicao);
        pcs.firePropertyChange(null,null,null);
        return res;
    }


    public void AtribuirAutomaticoAutopropostosDocentesAluno(){
        controladorDoPrograma.AtribuirAutomaticoAutopropostosDocentesAluno();
        pcs.firePropertyChange(null,null,null);
    }

    public Boolean removeAtribuicao(String nrAluno) {
        Boolean res =  controladorDoPrograma.removeAtribuicao(nrAluno);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean removeDocenteAtribuido(int IdProp) {
        boolean res =  controladorDoPrograma.removeDocenteAtribuido(IdProp);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean atribuirManualmenteAluno(long id_aluno, String proposta){
        boolean resultado = controladorDoPrograma.atribuirManualmenteAluno(id_aluno,proposta);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public Proposta getPropostaConflito() {
        return controladorDoPrograma.getPropostaConflito();
    }



    public PoeState getState(){
        return controladorDoPrograma.getState();
    }

    public int contaPropostaRAS(){return controladorDoPrograma.contaPropostaRAS();}
    public int contaPropostaDA(){return controladorDoPrograma.contaPropostaDA();}
    public int contaPropostaSI(){return controladorDoPrograma.contaPropostaSI();}

    public int contaPropostasAtribuidas(){return controladorDoPrograma.contaPropostasAtribuidas();}
    public int contaPropostasNaoAtribuidas(){return controladorDoPrograma.contaPropostasNaoAtribuidas();}

    public HashMap contaEstagios() {
        return controladorDoPrograma.contaEstagios();
    }

    public HashMap contaOrientacoes() {
        return controladorDoPrograma.contaOrientacoes();
    }
}
