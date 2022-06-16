package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;

import java.io.File;
import java.util.ArrayList;

public class gestaoAlunosUI extends BorderPane {

    ProgManager manager;
    Button btnExportar,btnConsulta,btnLerFich,btnAvancar,btnVoltar;
    Label info;

    HBox hbox;
    BorderPane plane;

    //Variavel de auxilio mostrar info
    int aux;
    TextField textField;
    HBox textotry;


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
        btnConsulta = new Button("Eliminar");
        btnConsulta.setMinWidth(100);
        btnLerFich = new Button("Ler Ficheiro");
        btnLerFich.setMinWidth(100);
        btnAvancar  = new Button("Avancar");
        btnAvancar.setMinWidth(100);
        btnVoltar  = new Button("Voltar");
        btnVoltar.setMinWidth(100);
        hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(btnExportar,btnLerFich,btnAvancar,btnVoltar);
        info.setVisible(false);
        this.setTop(hbox);
        plane = new BorderPane(new consultaUI(manager));
        this.setCenter(plane);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        btnExportar.setOnAction(event ->{
            Popup.display(PopupSupport.POPUP_EXPORT,0);
        });
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
            aux = manager.lerFicheiro(file.getAbsolutePath());
            Popup.display(PopupSupport.POPUP_LERFICH,aux);
        });
/*        btnConsulta.setOnAction(event ->{
            System.out.println(textField.getText());
            manager.removerAluno(Long.parseLong(textField.getText()));
            System.out.println(manager.getAlunos());
        });*/
        btnAvancar.setOnAction(event ->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fechar Fase");
            alert.setHeaderText("Ao Fechar a fase nao tera mais a possibilidade de alterar as informacoes");
            alert.setContentText("Fechar?");
            ButtonType okButton = new ButtonType("Sim", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("Nao", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    manager.avancar(true);
                } else if (type == noButton) {
                    manager.avancar(false);
                }
            });
        });
        btnVoltar.setOnAction(event ->{
            manager.voltar(false);
        });
    }

    private void update() {


    }

}
