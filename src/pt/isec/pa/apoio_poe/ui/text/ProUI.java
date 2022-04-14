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
            }
        }
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

}
