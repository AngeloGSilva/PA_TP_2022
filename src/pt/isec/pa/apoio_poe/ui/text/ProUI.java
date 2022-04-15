package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.Utils.PAInput;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;

public class ProUI {
    ProContexto controladorDoPrograma;
    public ProUI(ProContexto controladorDoPrograma) {
        this.controladorDoPrograma = controladorDoPrograma;
    }

    boolean acabou = false;

    public void start(){
        while(!acabou){
            switch (controladorDoPrograma.getState()){
                case CONFIGURACAO -> configuracaoUI();
                case GESTAO_ALUNO -> gestaoAlunosUI();
                case GESTAO_DOCENTE ->gestaoDocentesUI();
                case GESTAO_PROJETO -> gestaoProjetosUI();
                case OPCAO_CANDIDATURA -> opCandidaturaUI();
                case ATRIBUIR_PROPOSTA -> atrPropostaUI();
                case ATRIBUIR_ORIENTADOR -> atrOrientadorUI();
                case CONSULTA -> consultaUI();
            }
        }
    }

    private void consultaUI() {
        
    }

    private void atrOrientadorUI() {

    }

    private void atrPropostaUI() {

    }

    private void gestaoProjetosUI() {
    }

    private void opCandidaturaUI() {

    }

    private void gestaoDocentesUI() {

    }

    private void gestaoAlunosUI() {
    }

    private void configuracaoUI() {
        System.out.println("---Inicial---");
        System.out.println("Bem Vindo");
        switch (PAInput.chooseOption("Gerir:", "Gestao de Alunos", "Gestao de Docentes", "Gestao de Projetos", "sair")){
            case 1 -> System.out.println("Alunos");
            case 2 -> System.out.println("Docentes");
            case 3 -> System.out.println("Projetos");
            default -> acabou = true;
        }
    }

    private void gestaoAlunos(){

    }

}
