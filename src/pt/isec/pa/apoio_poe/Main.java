package pt.isec.pa.apoio_poe;

import pt.isec.pa.apoio_poe.model.fsm.ProContexto;
import pt.isec.pa.apoio_poe.ui.text.PoeUI;

public class Main {

    public static void main(String[] args) {
        ProContexto controladorDoPrograma = new ProContexto();
        PoeUI ui = new PoeUI(controladorDoPrograma);
        ui.start();
	    System.out.println("Teste2");
    }
}
