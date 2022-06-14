package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

public class atriPropostaUI extends BorderPane {

    ProgManager manager;

    public atriPropostaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
    }

    private void update() {

    }

}
