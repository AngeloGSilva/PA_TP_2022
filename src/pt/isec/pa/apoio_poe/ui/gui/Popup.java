package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class Popup extends VBox{
    public static void display(PopupSupport s)
    {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL); //evita a aplicaçao continuar antes de fechar a janela
        Button btn = new Button("Ok");
        Label lb = new Label();
        switch(s){
            case POPUP_EXPORT->{
                popupwindow.setTitle("Janela de informação");
                lb.setText("Popup Export");

            }
            case POPUP_LERFICH->{
                popupwindow.setTitle("Janela de informação");
                lb.setText("Popup LerFicheiro");
            }
        }
        btn.setOnAction(e -> popupwindow.close());
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
