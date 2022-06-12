package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class gestaoAlunosUI extends BorderPane {

    ProgManager manager;
    Button btnExportar,btnConsulta,btnLerFich,btnAvancar,btnVoltar;
    Label info;

    HBox hbox;


    public gestaoAlunosUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        info = new Label();
        btnExportar = new Button("Exportar");
        btnExportar.setMinWidth(100);
        /*btnConsulta = new Button("Consulta");
        btnConsulta.setMinWidth(100);*/
        btnLerFich = new Button("Ler Ficheiro");
        btnLerFich.setMinWidth(100);
        btnAvancar  = new Button("Avancar");
        btnAvancar.setMinWidth(100);
        btnVoltar  = new Button("Voltar");
        btnVoltar.setMinWidth(100);
        hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        //hBox.getChildren().addAll();
        hbox.getChildren().addAll(btnExportar,btnLerFich,btnAvancar,btnVoltar);
        //hBox.getChildren().add(btnAlunos);
        //hBox.getChildren().add(btnDocentes);
        //hBox.getChildren().add(btnProjetos);
        //this.getChildren().addAll(vbox);
        info.setVisible(false);
        this.setTop(hbox);
        this.setCenter(new consultaUI(manager));
        //this.setCenter(info);
        //this.setRight(info);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        btnLerFich.setOnAction(event ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File open...");
            fileChooser.setInitialDirectory(new File("..."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV (*.csv)","*.csv"),
                    new FileChooser.ExtensionFilter("All files","*.*")
            );
            File file = fileChooser.showOpenDialog(this.getScene().getWindow());
            System.out.println(file.getAbsolutePath());
            manager.lerFicheiro(file.getAbsolutePath());
            info.setVisible(true);
            //Popup.display(manager.getAlunos());
        });
    }

    private void update() {
        if (manager.getState() != PoeState.GESTAO_ALUNO){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
        //list.getItems().add(manager.getAlunos());

    }

}
