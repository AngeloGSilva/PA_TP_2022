package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import pt.isec.pa.apoio_poe.model.ProgManager;

public class atriOrientadorUI extends BorderPane {
    ProgManager manager;

    public atriOrientadorUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setPadding(new Insets(30));
        this.setTop(new topButtonsUI(manager));
        this.setCenter(new consultaUI(manager));
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
    }

    private void update() {

    }
}
