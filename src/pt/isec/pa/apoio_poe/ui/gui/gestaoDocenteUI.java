package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.apoio_poe.model.ProgManager;

public class gestaoDocenteUI extends BorderPane{

    ProgManager manager;
    Button btnExportar,btnConsulta,btnLerFich,btnAvancar,btnVoltar;
    Label info;

    HBox hbox;
    BorderPane plane;

    //Variavel de auxilio mostrar info
    int aux;

    TextField textField;
    HBox textotry;


    public gestaoDocenteUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setPadding(new Insets(30));
        this.setTop(new topButtonsUI(manager));

        /*this.setPadding(new Insets(30));
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
        plane = new BorderPane(new tableViewsUI(manager));
        this.setCenter(plane);
*//*        this.setBottom(textotry);
        textField.setPromptText("Numero do Aluno");
        textotry.getChildren().addAll(textField,btnConsulta);
        textotry.setAlignment(Pos.BOTTOM_CENTER);*//*
        //HBox text = new HBox();
        //text.getChildren().add(tfield);
        //plane.setVisible(false);
        //this.setCenter(info);
        //this.setRight(info);*/

        this.setCenter(new tableViewsUI(manager));

    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });

/*        btnExportar.setOnAction(event ->{
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
        btnConsulta.setOnAction(event ->{
            System.out.println(textField.getText());
            manager.removerAluno(Long.parseLong(textField.getText()));
            System.out.println(manager.getAlunos());
        });
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
        });*/
    }

    private void update() {
/*        if (manager.getState() != PoeState.GESTAO_ALUNO){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);*/
        //this.setCenter(plane);
        //this.setCenter(new tableViewsUI(manager));
        //list.getItems().add(manager.getAlunos());

    }
}
