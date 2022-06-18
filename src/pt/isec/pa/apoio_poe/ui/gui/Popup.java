package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
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

    public static void addProposta(ProgManager manager){
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        RadioButton t1 = new RadioButton("Estagio(T1)");
        grid.getChildren().add(t1);
        t1.setSelected(true);

        RadioButton t2 = new RadioButton("Projeto(T2)");
        grid.getChildren().add(t2);

        RadioButton t3 = new RadioButton("Autoproposto(T3)");
        grid.getChildren().add(t3);

        ToggleGroup tipo_group = new ToggleGroup();
        t1.setToggleGroup(tipo_group);
        t2.setToggleGroup(tipo_group);
        t3.setToggleGroup(tipo_group);


        Label tipo_de_proposta = new Label("Tipo de Proposta");
        grid.getChildren().add(tipo_de_proposta);
        tipo_de_proposta.setAlignment(Pos.CENTER);

        Button btnSelectTipo = new Button("Selecionar");
        grid.getChildren().add(btnSelectTipo);


        Button addAluno= new Button("aluno");

        GridPane.setConstraints(tipo_de_proposta,0,0);
        GridPane.setConstraints(t1,0,1);
        GridPane.setConstraints(t2,1,1);
        GridPane.setConstraints(t3,2,1);
        GridPane.setConstraints(btnSelectTipo,1,2);

        btnSelectTipo.setOnAction(e-> {
            RadioButton rb = (RadioButton) tipo_group.getSelectedToggle();
            if (rb.getText().equals("Estagio(T1)")){
                t1.setVisible(false);
                t1.setManaged(false);
                t2.setVisible(false);
                t2.setManaged(false);
                t3.setVisible(false);
                t3.setManaged(false);
                btnSelectTipo.setVisible(false);
                btnSelectTipo.setManaged(false);

                grid.getChildren().add(addAluno);
                GridPane.setConstraints(addAluno,0,1);



            }else if (rb.getText().equals("Projeto(T2)")){

            } else if (rb.getText().equals("Autoproposto(T3)")) {

            }
        });

        addAluno.setOnAction(event->{
            TextField nome = new TextField("ola");
            grid.getChildren().add(nome);
            GridPane.setConstraints(addAluno,0,3);
        });


        Scene nscene = new Scene(grid, 200, 100);
        popupwindow.setTitle("Adicionar Aluno");
        popupwindow.setMinWidth(350);
        popupwindow.setMinHeight(300);

        popupwindow.setMaxWidth(nscene.getWidth());
        popupwindow.setMaxHeight(nscene.getHeight());

        popupwindow.setScene(nscene);
        popupwindow.showAndWait();
    }
    public static void addAluno(ProgManager manager){
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        TextField nome_Aluno = new TextField();
        nome_Aluno.setPromptText("Primeiro e Ultimo");
        nome_Aluno.setPrefColumnCount(15);
        grid.getChildren().add(nome_Aluno);

        TextField nr_Aluno = new TextField();
        nr_Aluno.setPromptText("Numero");
        nr_Aluno.setPrefColumnCount(15);
        grid.getChildren().add(nr_Aluno);

        TextField email_Aluno = new TextField();
        email_Aluno.setPromptText("Email");
        email_Aluno.setPrefColumnCount(15);
        grid.getChildren().add(email_Aluno);

        TextField ramo_Aluno = new TextField();
        ramo_Aluno.setPromptText("Ramo");
        ramo_Aluno.setPrefColumnCount(15);
        grid.getChildren().add(ramo_Aluno);

        TextField classificacao_Aluno = new TextField();
        classificacao_Aluno.setPromptText("Classificacao");
        classificacao_Aluno.setPrefColumnCount(15);
        grid.getChildren().add(classificacao_Aluno);

        TextField curso_Aluno = new TextField();
        curso_Aluno.setPromptText("Curso");
        curso_Aluno.setPrefColumnCount(15);
        grid.getChildren().add(curso_Aluno);

        Label estagio = new Label("Pode Acerder a Estagio?");
        grid.getChildren().add(estagio);
        estagio.setAlignment(Pos.CENTER);

        RadioButton acede = new RadioButton("Sim");
        grid.getChildren().add(acede);
        acede.setSelected(true);

        RadioButton naoAcede = new RadioButton("Nao");
        grid.getChildren().add(naoAcede);

        ToggleGroup acederEstagio = new ToggleGroup();
        acede.setToggleGroup(acederEstagio);
        naoAcede.setToggleGroup(acederEstagio);


        Button btnAdd = new Button("Adicionar");
        grid.getChildren().add(btnAdd);

        Button btnApagar = new Button("Apagar");
        grid.getChildren().add(btnApagar);

        GridPane.setConstraints(nome_Aluno,0,0);
        GridPane.setConstraints(nr_Aluno,0,1);
        GridPane.setConstraints(email_Aluno,0,2);
        GridPane.setConstraints(ramo_Aluno,0,3);
        GridPane.setConstraints(classificacao_Aluno,0,4);
        GridPane.setConstraints(curso_Aluno,0,5);
        GridPane.setConstraints(estagio,0,7);
        GridPane.setConstraints(acede,0,8);
        GridPane.setConstraints(naoAcede,1,8);
        GridPane.setConstraints(btnAdd,2,2);
        GridPane.setConstraints(btnApagar,2,3);

        btnAdd.setOnAction(e-> {
            RadioButton rb = (RadioButton) acederEstagio.getSelectedToggle();
            boolean resultado;
            if (rb.getText().equals("Sim"))
                resultado = true;
            else
                resultado = false;
            manager.adicionarAluno(nr_Aluno.getText(),nome_Aluno.getText(),email_Aluno.getText(),ramo_Aluno.getText(),Double.parseDouble(classificacao_Aluno.getText()),resultado, curso_Aluno.getText());
            popupwindow.close();
        });

        btnApagar.setOnAction(e-> {
           nr_Aluno.clear();
           nome_Aluno.clear();
           email_Aluno.clear();
           ramo_Aluno.clear();
           classificacao_Aluno.clear();
           curso_Aluno.clear();
        });

        Scene nscene = new Scene(grid, 200, 100);
        popupwindow.setTitle("Adicionar Aluno");
        popupwindow.setMinWidth(350);
        popupwindow.setMinHeight(300);

        popupwindow.setMaxWidth(nscene.getWidth());
        popupwindow.setMaxHeight(nscene.getHeight());

        popupwindow.setScene(nscene);
        popupwindow.showAndWait();
    }
    public static void addDocente(ProgManager manager){
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        TextField nome_Docente = new TextField();
        nome_Docente.setPromptText("Primeiro e Ultimo");
        nome_Docente.setPrefColumnCount(15);
        grid.getChildren().add(nome_Docente);


        TextField email_Docente = new TextField();
        email_Docente.setPromptText("Email");
        email_Docente.setPrefColumnCount(15);
        grid.getChildren().add(email_Docente);

        Label estagio = new Label("Pode Acerder a Estagio?");
        grid.getChildren().add(estagio);
        estagio.setAlignment(Pos.CENTER);

        RadioButton acede = new RadioButton("Sim");
        grid.getChildren().add(acede);
        acede.setSelected(true);

        RadioButton naoAcede = new RadioButton("Nao");
        grid.getChildren().add(naoAcede);

        ToggleGroup acederEstagio = new ToggleGroup();
        acede.setToggleGroup(acederEstagio);
        naoAcede.setToggleGroup(acederEstagio);


        Button btnAdd = new Button("Adicionar");
        grid.getChildren().add(btnAdd);

        Button btnApagar = new Button("Apagar");
        grid.getChildren().add(btnApagar);

        GridPane.setConstraints(nome_Docente,0,0);
        GridPane.setConstraints(email_Docente,0,2);
        GridPane.setConstraints(estagio,0,7);
        GridPane.setConstraints(acede,0,8);
        GridPane.setConstraints(naoAcede,1,8);
        GridPane.setConstraints(btnAdd,2,2);
        GridPane.setConstraints(btnApagar,2,3);

        btnAdd.setOnAction(e-> {
            RadioButton rb = (RadioButton) acederEstagio.getSelectedToggle();
            boolean resultado;
            if (rb.getText().equals("Sim"))
                resultado = true;
            else
                resultado = false;
            manager.adicionarDocente(nome_Docente.getText(),email_Docente.getText(),true);
            popupwindow.close();
        });

        btnApagar.setOnAction(e-> {
            nome_Docente.clear();
            email_Docente.clear();
        });

        Scene nscene = new Scene(grid, 200, 100);
        popupwindow.setTitle("Adicionar Aluno");
        popupwindow.setMinWidth(350);
        popupwindow.setMinHeight(300);

        popupwindow.setMaxWidth(nscene.getWidth());
        popupwindow.setMaxHeight(nscene.getHeight());

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
