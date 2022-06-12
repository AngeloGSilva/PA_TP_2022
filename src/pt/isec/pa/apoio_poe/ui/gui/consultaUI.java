package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

public class consultaUI extends BorderPane {
    ProgManager manager;
    Button btnExportar,btnConsulta,btnLerFich,btnAvancar,btnVoltar;
    Label info;

    TableView<Aluno> table;

    public consultaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        table = new TableView<Aluno>();
        TableColumn nome = new TableColumn("nome_Aluno");
        nome.setCellFactory(new PropertyValueFactory<Aluno,String>("nome_Aluno"));
        table.setItems(manager.getAlunos());
        table.getColumns().addAll(nome);
        this.setCenter(table);
        //this.setRight(info);
    }

    private void registerHandlers() {
    }

    private void update() {

        /*if (estado == a waitBet)
             new WaitBetUI(gameBWManager) para nao ser tao pesado para nao carregar td lg no inicio
         */
    }
}
