package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.FontManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

public class sideButtonsUI extends VBox {

    ProgManager manager;
    Button btnAdd ;
    Button btnDelete;

    public sideButtonsUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnAdd = new Button();
        btnDelete = new Button();
        btnAdd = new Button();
        btnDelete = new Button();



        ImageView imageView = new ImageView(ImageManager.getImage("addv1.png"));
        btnAdd.setGraphic(imageView);
        imageView = new ImageView(ImageManager.getImage("deletev1.png"));
        btnDelete.setGraphic(imageView);

        this.getChildren().addAll(btnAdd, btnDelete);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);



    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        btnDelete.setOnAction(e->{
            Scene scene = this.getScene();
            Aluno selected = (Aluno) scene.getUserData();
            System.out.println(selected);
        });

    }

    private void update() {

    }
}
