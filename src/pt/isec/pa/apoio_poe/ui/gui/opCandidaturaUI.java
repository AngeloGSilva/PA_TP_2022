package pt.isec.pa.apoio_poe.ui.gui;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.isec.pa.apoio_poe.model.ProgManager;


public class opCandidaturaUI extends BorderPane {
    ProgManager manager;
    Button btnVoltar;
    public opCandidaturaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnVoltar  = new Button("Voltar");
        btnVoltar.setMinWidth(100);
        this.setCenter(btnVoltar);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        btnVoltar.setOnAction(event ->{
            manager.voltar(false);
        });
    }

    private void update() {

    }
}
