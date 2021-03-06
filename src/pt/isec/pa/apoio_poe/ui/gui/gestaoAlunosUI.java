package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ProgManager;

public class gestaoAlunosUI extends BorderPane {

    ProgManager manager;
    Button btnExportar, btnDelete,btnLerFich,btnAvancar,btnVoltar,btnAdd;
    Label info;

    ImageView imageView;
    HBox hbox;
    VBox vBox;
    BorderPane plane;

    Tooltip tooltip;

    //Variavel de auxilio mostrar info
    int aux;


    public gestaoAlunosUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setPadding(new Insets(30));
        this.setTop(new topButtonsUI(manager));
        /*tooltip = new Tooltip();

        btnLerFich = new Button();
        imageView = new ImageView(ImageManager.getImage("download.png"));
        btnLerFich.setGraphic(imageView);
        btnLerFich.setText("Importar");

        btnExportar = new Button();
        imageView = new ImageView(ImageManager.getImage("upload.png"));
        btnExportar.setGraphic(imageView);
        btnExportar.setText("Exportar");

        btnAvancar = new Button();
        imageView = new ImageView(ImageManager.getImage("avance.png"));
        btnAvancar.setContentDisplay(ContentDisplay.RIGHT);
        btnAvancar.setGraphic(imageView);
        btnAvancar.setText("Avancar");


        btnVoltar = new Button();
        imageView = new ImageView(ImageManager.getImage("back.png"));
        btnVoltar.setGraphic(imageView);
        btnVoltar.setText("Voltar");




        info = new Label();

        hbox = new HBox();
        hbox.setSpacing(10);
        //btnVoltar.setAlignment(Pos.TOP_RIGHT);
        hbox.getChildren().addAll(btnVoltar,btnLerFich,btnExportar,btnAvancar);

        hbox.setAlignment(Pos.CENTER);
        info.setVisible(false);
        this.setTop(hbox);*/
        this.setCenter(new tableViewsUI(manager));
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        /*btnExportar.setOnAction(event ->{
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
*//*        btnConsulta.setOnAction(event ->{
            System.out.println(textField.getText());
            manager.removerAluno(Long.parseLong(textField.getText()));
            System.out.println(manager.getAlunos());
        });*//*
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
    }

}
