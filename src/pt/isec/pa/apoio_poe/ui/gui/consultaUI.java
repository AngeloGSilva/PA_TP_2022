package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.FontManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

public class consultaUI extends GridPane {

    ProgManager manager;
    Button btnGraficos, btnTabelas, btnback;
    ImageView imageView;

    public consultaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(6);
        this.setHgap(6);

        btnGraficos = new Button("Graficos");
        imageView = new ImageView(ImageManager.getImage("chart.png"));
        btnGraficos.setContentDisplay(ContentDisplay.LEFT);
        btnGraficos.setGraphic(imageView);
        this.getChildren().add(btnGraficos);

        btnTabelas = new Button("Tabelas");
        imageView = new ImageView(ImageManager.getImage("dataTables.png"));
        btnTabelas.setContentDisplay(ContentDisplay.LEFT);
        btnTabelas.setGraphic(imageView);
        this.getChildren().add(btnTabelas);

        btnback = new Button("Voltar");
        imageView = new ImageView(ImageManager.getImage("back.png"));
        btnback.setContentDisplay(ContentDisplay.RIGHT);
        btnback.setGraphic(imageView);
        this.getChildren().add(btnback);
        btnback.setVisible(false);
        btnback.setManaged(false);

        GridPane.setConstraints(btnGraficos,10,5);
        GridPane.setConstraints(btnGraficos,40,5);
        GridPane.setConstraints(btnTabelas,50,5);
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
        });

        btnTabelas.setOnAction(event -> {
            btnTabelas.setVisible(false);
            btnTabelas.setManaged(false);

            btnGraficos.setVisible(false);
            btnGraficos.setManaged(false);

            btnback.setVisible(true);
            btnback.setManaged(true);
        });

        btnback.setOnAction(event -> {
            btnGraficos.setVisible(true);
            btnGraficos.setManaged(true);

            btnTabelas.setVisible(true);
            btnTabelas.setManaged(true);

            btnback.setVisible(false);
            btnback.setManaged(false);
        });
    }

    private void update() {

    }
}
