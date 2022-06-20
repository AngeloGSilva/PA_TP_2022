package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.FontManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

public class consultaUI extends BorderPane {

    ProgManager manager;
    Button btnGraficos, btnTabelas, btnback;
    ImageView imageView;

    HBox hBox;

    public consultaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setTop(new graficosUI(manager));

        this.setPadding(new Insets(10, 10, 10, 10));

        hBox = new HBox();

        btnGraficos = new Button("Graficos");
        imageView = new ImageView(ImageManager.getImage("chart.png"));
        btnGraficos.setContentDisplay(ContentDisplay.LEFT);
        btnGraficos.setGraphic(imageView);
        hBox.getChildren().add(btnGraficos);

        btnTabelas = new Button("Tabelas");
        imageView = new ImageView(ImageManager.getImage("dataTables.png"));
        btnTabelas.setContentDisplay(ContentDisplay.LEFT);
        btnTabelas.setGraphic(imageView);
        hBox.getChildren().add(btnTabelas);

        btnback = new Button("Voltar");
        imageView = new ImageView(ImageManager.getImage("back.png"));
        btnback.setContentDisplay(ContentDisplay.LEFT);
        btnback.setGraphic(imageView);
        hBox.getChildren().add(btnback);
        btnback.setVisible(false);
        btnback.setManaged(false);

        this.setTop(hBox);
        hBox.setAlignment(Pos.TOP_CENTER);

    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });

        btnGraficos.setOnAction(event -> {
            btnGraficos.setVisible(false);
            btnGraficos.setManaged(false);

            btnTabelas.setVisible(false);
            btnTabelas.setManaged(false);

            btnback.setVisible(true);
            btnback.setManaged(true);

            hBox.setAlignment(Pos.TOP_LEFT);
            this.setCenter(new graficosUI(manager));
        });

        btnTabelas.setOnAction(event -> {
            btnTabelas.setVisible(false);
            btnTabelas.setManaged(false);

            btnGraficos.setVisible(false);
            btnGraficos.setManaged(false);

            btnback.setVisible(true);
            btnback.setManaged(true);

            hBox.setAlignment(Pos.TOP_LEFT);
            this.setCenter(new tableViewsUI(manager));
        });



        btnback.setOnAction(event -> {
            btnGraficos.setVisible(true);
            btnGraficos.setManaged(true);

            btnTabelas.setVisible(true);
            btnTabelas.setManaged(true);

            btnback.setVisible(false);
            btnback.setManaged(false);
            hBox.setAlignment(Pos.TOP_CENTER);
            this.setCenter(null);
        });
    }

    private void update() {

    }
}
