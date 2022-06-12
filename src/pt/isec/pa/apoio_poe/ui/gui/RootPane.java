package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

public class RootPane extends BorderPane {
    ProgManager manager;

    public RootPane(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        StackPane stackPane;
        stackPane = new StackPane(
                new configuracaoUI(manager));
        this.setCenter(stackPane);
    }

    private void registerHandlers() {
    }

    private void update() {
        /*if (estado == a waitBet)
             new WaitBetUI(gameBWManager) para nao ser tao pesado para nao carregar td lg no inicio
         */
    }
}