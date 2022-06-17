package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.Aluno;


public class Popup extends VBox{
    public static void conflito(ProgManager manager) {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        Label lb = new Label();
        TableView<Aluno> tabelaAlunos = new TableView<>();
        //TableColumn nome_Aluno = new TableColumn<>(Nome);
    }


    public static void display(PopupSupport s,int aux)
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
                if(aux>0) { //Leu pelo menos 1
                    lb.setText("[Sucesso]Li " + aux + " novos dados!");
                }else{
                    lb.setText("[Erro]Sem dados para leitura!");
                }
            }
        }
        btn.setOnAction(e -> popupwindow.close());
        VBox layout = new VBox(10);

        layout.getChildren().addAll(lb, btn);
        layout.setAlignment(Pos.CENTER);
        Scene nscene = new Scene(layout, 200, 100);
        popupwindow.setMinWidth(250);
        popupwindow.setMinHeight(150);

        popupwindow.setMaxWidth(nscene.getWidth());
        popupwindow.setMaxHeight(nscene.getHeight());

        //popupwindow.initStyle(StageStyle.TRANSPARENT);
        popupwindow.setScene(nscene);
        popupwindow.showAndWait();


    }

}
