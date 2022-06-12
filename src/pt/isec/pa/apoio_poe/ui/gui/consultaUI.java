package pt.isec.pa.apoio_poe.ui.gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.Aluno;

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
        TableColumn nome = new TableColumn("Nome");
        nome.setCellValueFactory(new PropertyValueFactory<Aluno,String>("nome_Aluno"));
        nome.setMinWidth(100);

        TableColumn nr_Aluno = new TableColumn("Numero");
        nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno,Long>("nr_Aluno"));
        nr_Aluno.setMinWidth(85);

        TableColumn email_Aluno = new TableColumn("Email");
        email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("email_Aluno"));
        email_Aluno.setMinWidth(140);

        TableColumn curso = new TableColumn("Curso");
        curso.setCellValueFactory(new PropertyValueFactory<Aluno,String>("curso"));
        curso.setMinWidth(50);

        TableColumn ramo_Aluno = new TableColumn("Ramo");
        ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("ramo_Aluno"));
        ramo_Aluno.setMinWidth(50);

        TableColumn classificacao_Aluno = new TableColumn("Classificacão");
        classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno,Double>("classificacao_Aluno"));
        classificacao_Aluno.setMinWidth(50);

        TableColumn aceder_a_Estagio = new TableColumn("Estagio");
        aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno,Boolean>("aceder_a_Estagio"));
        aceder_a_Estagio.setMinWidth(50);



        table.setMaxWidth(650);
        table.setMaxHeight(300);

        //table.setItems(manager.getAlunos());
        table.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);
        this.setCenter(table);
        //this.setRight(info);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        table.addEventHandler(ActionEvent.ANY, evt ->{
            table.setItems(manager.getAlunos());
        });
    }

    private void update() {
        table.getItems().clear();
        table.setItems(manager.getAlunos());
        //table.refresh();
    }
}
