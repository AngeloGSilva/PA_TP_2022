package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;
import pt.isec.pa.apoio_poe.ui.gui.MainJFX;
import pt.isec.pa.apoio_poe.ui.text.PoeUI;

public class App {

    public static void main(String[] args) {
        ProContexto controladorDoPrograma = new ProContexto();
        PoeUI ui = new PoeUI(controladorDoPrograma);
        ui.start();
       Application.launch(MainJFX.class,args);
    }
}
