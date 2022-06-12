package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class Popup extends VBox{
    public static void display(String s)
    {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL); //evita a aplicaçao continuar antes de fechar a janela
        popupwindow.setTitle("Janela de informação");
        Label lb = new Label("Popup LerFicheiro");
        Button btn = new Button("Ok");
        btn.setOnAction(e -> popupwindow.close());
        lb.setText(s);
        VBox layout = new VBox(10);

        layout.getChildren().addAll(lb, btn);
        layout.setAlignment(Pos.CENTER);
        Scene nscene= new Scene(layout, 300, 250);
        popupwindow.setMinHeight(600);
        popupwindow.setMinWidth(600);
        //popupwindow.initStyle(StageStyle.TRANSPARENT);
        popupwindow.setScene(nscene);
        popupwindow.showAndWait();

    }

}
