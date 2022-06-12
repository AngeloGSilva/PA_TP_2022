package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

public class consultaUI extends BorderPane {
    ProgManager manager;
    Button btnExportar,btnConsulta,btnLerFich,btnAvancar,btnVoltar;
    Label info;

    public consultaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        info = new Label();
        info.setVisible(false);
        //this.setRight(info);
    }

    private void registerHandlers() {
    }

    private void update() {
        this.getChildren().clear();
        this.setCenter(info);
        if (manager.getAlunos().length()>=1){
            info.setVisible(true);
            String str = String.format(manager.getAlunos());
            info.setText(str);
        }
        /*if (estado == a waitBet)
             new WaitBetUI(gameBWManager) para nao ser tao pesado para nao carregar td lg no inicio
         */
    }
}
