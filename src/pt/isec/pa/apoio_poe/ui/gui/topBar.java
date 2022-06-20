package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.ProgManager;

import java.io.File;

/**
 * class topBar class onde esta presente o save e load
 * @see MenuBar
 */
public class topBar extends MenuBar {
    private  ProgManager manager;
    private MenuItem mnSave, mnLoad, mnExit;

    public topBar(ProgManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Menu app = new Menu("Application");
        mnSave = new MenuItem("_Save");
        mnLoad = new MenuItem("_Load");
        mnExit = new MenuItem("_Exit");
        app.getItems().addAll(mnSave, mnLoad, new SeparatorMenuItem(), mnExit);

        this.getMenus().add(app);
    }

    private void registerHandlers() {
        mnSave.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("(*.dat)", "*.dat"),
                    new FileChooser.ExtensionFilter("All", "*.*")
            );
            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());
            if (hFile != null){
                manager.save();
            }
        });

        mnLoad.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File open...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("(*.dat)", "*.dat"),
                    new FileChooser.ExtensionFilter("All", "*.*")
            );
            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (hFile != null){
                manager.load();
            }
        });

        mnExit.setOnAction(actionEvent -> Platform.exit());
    }

    private void update() {
    }
}

