package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.Utils.PAInput;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;

public class PoeUI {
    ProContexto controladorDoPrograma;
    public PoeUI(ProContexto controladorDoPrograma) {
        this.controladorDoPrograma = controladorDoPrograma;
    }

    boolean acabou = false;

    public void start(){
        while(!acabou){
            switch (controladorDoPrograma.getState()){
                case CONFIGURACAO -> configuracaoUI();
                case GESTAO_ALUNO -> gestaoAlunosUI();
                case GESTAO_DOCENTE -> gestaoDocentesUI();
                case GESTAO_PROPOSTA -> gestaoPropostaUI();
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

    private void opCandidaturaUI() {

    }

    private void gestaoPropostaUI() {
        System.out.println("Gestão de Propostas:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Edição","Eliminação")) {
            //case 1 -> Inserção;
            //case 2 -> consulta;
            //case 3 -> edição;
            //case 4 -> eliminação;
            //default -> acabou = true;
        }
    }

    private void gestaoDocentesUI() {
        System.out.println("Gestão de Docentes:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Edição","Eliminação")) {
            //case 1 -> Inserção;
            //case 2 -> consulta;
            //case 3 -> edição;
            //case 4 -> eliminação;
            //default -> acabou = true;
        }
    }

    private void carregarFicheirosUI(){

    }


    private void gestaoAlunosUI() {
        System.out.println("Gestão de alunos:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Edição","Eliminação")) {
            //case 1 -> Inserção;
            //case 2 -> consulta;
            //case 3 -> edição;
            //case 4 -> eliminação;
            //default -> acabou = true;
        }
    }

    private void configuracaoUI() {
        System.out.println("---Inicial---\n");
        System.out.println("Bem Vindo\n");
        controladorDoPrograma.selecionar(PAInput.chooseOption("Gerir:", "Gestao de Alunos", "Gestao de Docentes", "Gestao de Projetos", "Sair"));
    }

    private void gestaoAlunos() {

    }

}
