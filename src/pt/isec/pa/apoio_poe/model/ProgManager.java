package pt.isec.pa.apoio_poe.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.isec.pa.apoio_poe.model.data.Aluno;
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


    public void lerFicheiro(String fileName){
        controladorDoPrograma.lerFicheiro(fileName);
        System.out.println(controladorDoPrograma.getAlunos());
        //System.out.println(controladorDoPrograma.getDocentes());
        pcs.firePropertyChange(null,null,null);
    }

    public ObservableList<Aluno> getAlunos() {
        ObservableList<Aluno> ob = null;
        for (Aluno a : controladorDoPrograma.getAlunos()) {
            ob = FXCollections.observableArrayList(
                    new Aluno(a.getNr_Aluno(), a.getNome_Aluno(), a.getEmail_Aluno(), a.getRamo_Aluno(), a.getClassificacao_Aluno(), a.isAceder_a_Estagio(), a.getCurso())
            );
        }
        return ob;
    }

    public boolean voltar(boolean guardado) {
        return false;
    }

    public void resolverConflito(int selecionado){}

    public boolean avancar(boolean guardado) {
        return false;
    }


    public void AtribuirAutomaticamente(){};




    public boolean lerFicheiroDebug(String fileName){
        return false;
    }



    public boolean adicionarAluno(String nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio, String curso){
        return false;
    }


    public boolean removerAluno(long nr_aluno){
        return false;
    }

    public boolean removerDocente(String emaildoc){ return false;}


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
