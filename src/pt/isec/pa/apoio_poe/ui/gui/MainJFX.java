package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.ProgManager;

public class MainJFX extends Application {
    ProgManager manager;

    @Override
    public void init() throws Exception {
        super.init();
        manager = new ProgManager(); // here or in the constructor
    }

    @Override
    public void start(Stage stage) throws Exception {
        RootPane root = new RootPane(manager);
        Scene scene = new Scene(root,700,400);
        stage.setScene(scene);
        stage.setTitle("Estagios/Projetos");
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.show();
    }
}
