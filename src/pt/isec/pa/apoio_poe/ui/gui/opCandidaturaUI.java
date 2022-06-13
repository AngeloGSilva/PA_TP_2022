package pt.isec.pa.apoio_poe.ui.gui;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.ProgManager;


public class opCandidaturaUI extends BorderPane {
    ProgManager manager;
    Button btnExportar,btnConsulta,btnLerFich,btnAvancar,btnVoltar;
    Label info;

    HBox hbox;
    BorderPane plane;

    TextField textField;
    HBox textotry;
    public opCandidaturaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        //textField = new TextField();
        //textotry = new HBox();
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
        //hBox.getChildren().addAll();
        //hbox.setPadding(new Insets(100));
        hbox.getChildren().addAll(btnExportar,btnLerFich,btnAvancar,btnVoltar);
        //hBox.getChildren().add(btnAlunos);
        //hBox.getChildren().add(btnDocentes);
        //hBox.getChildren().add(btnProjetos);
        //this.getChildren().addAll(vbox);
        info.setVisible(false);
        this.setTop(hbox);
        plane = new BorderPane(new consultaUI(manager));
        this.setCenter(plane);
/*        this.setBottom(textotry);
        textField.setPromptText("Numero do Aluno");
        textotry.getChildren().addAll(textField,btnConsulta);
        textotry.setAlignment(Pos.BOTTOM_CENTER);*/
        //HBox text = new HBox();
        //text.getChildren().add(tfield);
        //plane.setVisible(false);
        //this.setCenter(info);
        //this.setRight(info);
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
