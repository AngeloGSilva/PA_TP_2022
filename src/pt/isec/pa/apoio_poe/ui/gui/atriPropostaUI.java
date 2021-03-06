package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.ProgManager;

public class atriPropostaUI extends BorderPane {

    ProgManager manager;
    Button btnAutoDoc, btnAtriAuto,btnManual,btnVoltar,btnAvancar;
    Label info;

    ImageView imageView;
    HBox hbox;
    VBox vBox;
    BorderPane plane;

    Tooltip tooltip;


    public atriPropostaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setPadding(new Insets(30));
        this.setTop(new topButtonsUI(manager));
/*        btnAutoDoc = new Button();
        imageView = new ImageView(ImageManager.getImage("download.png"));
        btnAutoDoc.setGraphic(imageView);
        btnAutoDoc.setText("Autopropostos");

        btnAtriAuto = new Button();
        imageView = new ImageView(ImageManager.getImage("upload.png"));
        btnAtriAuto.setGraphic(imageView);
        btnAtriAuto.setText("Automaticamente");

        btnManual = new Button();
        imageView = new ImageView(ImageManager.getImage("avance.png"));
        btnManual.setContentDisplay(ContentDisplay.RIGHT);
        btnManual.setGraphic(imageView);
        btnManual.setText("Manualmente");

        btnVoltar = new Button();
        imageView = new ImageView(ImageManager.getImage("back.png"));
        btnVoltar.setGraphic(imageView);
        btnVoltar.setText("Voltar");

        btnAvancar = new Button();
        imageView = new ImageView(ImageManager.getImage("back.png"));
        btnAvancar.setGraphic(imageView);
        btnAvancar.setText("Avancar");

        hbox = new HBox();
        hbox.setSpacing(10);
        //btnVoltar.setAlignment(Pos.TOP_RIGHT);
        hbox.getChildren().addAll(btnVoltar,btnAutoDoc,btnAtriAuto,btnManual,btnAvancar);

        hbox.setAlignment(Pos.CENTER);
        this.setTop(hbox);
        plane = new BorderPane(new tableViewsUI(manager));
        this.setCenter(plane);*/
        this.setCenter(new tableViewsUI(manager));
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
    }

    private void update() {


    }

}
