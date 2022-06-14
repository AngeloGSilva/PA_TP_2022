package pt.isec.pa.apoio_poe.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidatura;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

    public int lerFicheiro(String fileName){
        int buf = controladorDoPrograma.lerFicheiro(fileName);
        //System.out.println(controladorDoPrograma.getAlunos());
        //System.out.println(controladorDoPrograma.getDocentes());
        pcs.firePropertyChange(null,null,null);
        return buf;
    }

    public ObservableList<Aluno> getAlunos() {
        ObservableList<Aluno> ob = FXCollections.observableArrayList(controladorDoPrograma.getAlunosTV());
        System.out.println("Criar a lista de obs");
        System.out.println(ob);
        return ob;
    }

    public ObservableList<Docente> getDocentes() {
        ObservableList<Docente> ob = FXCollections.observableArrayList(controladorDoPrograma.getDocentesTV());
        System.out.println("Criar a lista de obs");
        System.out.println(ob);
        return ob;
    }

    public ObservableList<Proposta> getPropostas() {
        System.out.println(controladorDoPrograma.getPropostaTV());
        ObservableList<Proposta> ob = FXCollections.observableArrayList(controladorDoPrograma.getPropostaTV());
        System.out.println("Criar a lista de obs");
        System.out.println(ob);
        return ob;
    }

    public ObservableList<Candidatura> getCandidaturas() {
        System.out.println(controladorDoPrograma.getCandidaturaTV());
        ObservableList<Candidatura> ob = FXCollections.observableArrayList(controladorDoPrograma.getCandidaturaTV());
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

    public void resolverConflito(int selecionado){}

    public boolean avancar(boolean guardado) {
        boolean resultado = controladorDoPrograma.avancar(guardado);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }


    public void AtribuirAutomaticamente(){};




    public boolean lerFicheiroDebug(String fileName){
        return false;
    }



    public boolean adicionarAluno(String nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio, String curso){
        return false;
    }

    public boolean remover(String eliminar){
        boolean resultado = controladorDoPrograma.remover(eliminar);
        pcs.firePropertyChange(null,null,null);
        return resultado;
    }

    public boolean removerCandidatura(String nr_Aluno,String id_Proposta){
        boolean resultado = controladorDoPrograma.removerCandidatura(nr_Aluno,id_Proposta);
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


    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente){return false;}


    public boolean adicionarProposta(String tipo,String cod_ID, String titulo, Long codigo_Aluno, String email_Docente, String ramo,String empresa){return false;}


    public void atribuirDocentesauto() {}


    public boolean atribuirManualmenteDocente(String docente, int id_atribuicao){return false;}


    public void AtribuirAutomaticoAutopropostosDocentesAluno(){}


    public boolean atribuirManualmenteAluno(long id_aluno, String proposta){return false;}




    public PoeState getState(){
        return controladorDoPrograma.getState();
    }

}
