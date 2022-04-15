package pt.isec.pa.apoio_poe;

import pt.isec.pa.apoio_poe.model.fsm.ProContexto;
import pt.isec.pa.apoio_poe.ui.text.ProUI;

public class Main {

    public static void main(String[] args) {
        ProContexto ControladorDoPrograma = new ProContexto();
        ProUI ui = new ProUI(ControladorDoPrograma);
        ui.start();
	    System.out.println("Teste2");
    }
}
