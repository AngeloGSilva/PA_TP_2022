package pt.isec.pa.apoio_poe.ui.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.*;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.Aluno;


public class Popup extends VBox{
    public static void conflito(ProgManager manager) {
        Button btnSelected = new Button("Atribuir");




        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);



        Label lb = new Label();
        TableView<Aluno> tabelaAlunos = new TableView<>();
        TableColumn nome_Aluno = new TableColumn<>("Nome");
        TableColumn numero_Aluno = new TableColumn<>("Numero");
        TableColumn classificacao_Aluno = new TableColumn<>("Classificacao");
        TableColumn curso_Aluno = new TableColumn<>("Curso");

        nome_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
        numero_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nr_Aluno"));
        classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("classificacao_Aluno"));
        curso_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));

        tabelaAlunos.getColumns().addAll(numero_Aluno,nome_Aluno,curso_Aluno,classificacao_Aluno);


        btnSelected.setOnAction(e->{
            popupwindow.close();
            Aluno selectedItem = tabelaAlunos.getSelectionModel().getSelectedItem();
            manager.resolverConflito(Integer.parseInt(String.valueOf(selectedItem.getNr_Aluno())));
            tabelaAlunos.getItems().clear();
        });

        VBox vBox = new VBox();
        lb.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(lb,tabelaAlunos,btnSelected);
        lb.setText(manager.getPropostaConflito().getCod_ID());
        tabelaAlunos.setItems(manager.getConflitosTV());




        Scene nscene = new Scene(vBox, 200, 100);
        popupwindow.setMinWidth(350);
        popupwindow.setMinHeight(350);

        popupwindow.setMaxWidth(nscene.getWidth());
        popupwindow.setMaxHeight(nscene.getHeight());

        //popupwindow.initStyle(StageStyle.TRANSPARENT);
        popupwindow.setScene(nscene);
        popupwindow.showAndWait();

    }


    public static void add(ProgManager manager){
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        VBox vBox = new VBox();

        TextField nome_Aluno = new TextField("Primeiro e Ultimo");
        TextField nr_Aluno = new TextField("Numero");
        TextField email_Aluno = new TextField("Email");
        TextField ramo_Aluno = new TextField("Ramo");
        TextField classificacao_Aluno = new TextField("Classificacao");
        TextField curso_Aluno = new TextField("Curso");
        Button btnAdd = new Button("Adicionar");
        vBox.getChildren().addAll(nome_Aluno,nr_Aluno,email_Aluno,btnAdd);

        btnAdd.setOnAction(e-> {
            manager.adicionarAluno(nr_Aluno.getText(),nome_Aluno.getText(),email_Aluno.getText(),ramo_Aluno.getText(),Long.parseLong(classificacao_Aluno.getText()),true, curso_Aluno.getText());
            popupwindow.close();
        });

        Scene nscene = new Scene(vBox, 200, 100);
        popupwindow.setMinWidth(350);
        popupwindow.setMinHeight(350);

        popupwindow.setMaxWidth(nscene.getWidth());
        popupwindow.setMaxHeight(nscene.getHeight());

        //popupwindow.initStyle(StageStyle.TRANSPARENT);
        popupwindow.setScene(nscene);
        popupwindow.showAndWait();
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
