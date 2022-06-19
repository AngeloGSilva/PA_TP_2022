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

import java.util.Locale;


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


        Button btnaddAluno= new Button("Associar Aluno");
        grid.getChildren().add(btnaddAluno);
        btnaddAluno.setManaged(false);
        btnaddAluno.setVisible(false);

        Button addDocente= new Button("Docente");

        Button btnAdd = new Button("Adicionar");
        grid.getChildren().add(btnAdd);

        Button btnApagar = new Button("Apagar");
        grid.getChildren().add(btnApagar);


        btnAdd.setVisible(false);
        btnAdd.setManaged(false);
        btnApagar.setManaged(false);
        btnApagar.setVisible(false);

        GridPane.setConstraints(tipo_de_proposta,0,0);
        GridPane.setConstraints(t1,0,1);
        GridPane.setConstraints(t2,1,1);
        GridPane.setConstraints(t3,2,1);
        GridPane.setConstraints(btnSelectTipo,1,2);
        GridPane.setConstraints(btnAdd,2,2);
        GridPane.setConstraints(btnApagar,2,3);


        TextField id_proposta = new TextField();
        id_proposta.setPromptText("Id Proposta");
        id_proposta.setPrefColumnCount(15);
        grid.getChildren().add(id_proposta);
        id_proposta.setManaged(false);
        id_proposta.setVisible(false);

        TextField titulo_Proposta = new TextField();
        titulo_Proposta.setPromptText("Titulo");
        titulo_Proposta.setPrefColumnCount(15);
        grid.getChildren().add(titulo_Proposta);
        titulo_Proposta.setManaged(false);
        titulo_Proposta.setVisible(false);

        TextField nr_Aluno = new TextField();
        nr_Aluno.setPromptText("Numero de Aluno");
        nr_Aluno.setPrefColumnCount(15);
        grid.getChildren().add(nr_Aluno);
        nr_Aluno.setManaged(false);
        nr_Aluno.setVisible(false);

        TextField email_Docente = new TextField();
        email_Docente.setPromptText("Email");
        email_Docente.setPrefColumnCount(15);
        grid.getChildren().add(email_Docente);
        email_Docente.setManaged(false);
        email_Docente.setVisible(false);

        TextField ramo_Proposta = new TextField();
        ramo_Proposta.setPromptText("Ramo da Proposta");
        ramo_Proposta.setPrefColumnCount(15);
        grid.getChildren().add(ramo_Proposta);
        ramo_Proposta.setManaged(false);
        ramo_Proposta.setVisible(false);

        TextField empresa_Proposta = new TextField();
        empresa_Proposta.setPromptText("Empresa");
        empresa_Proposta.setPrefColumnCount(15);
        grid.getChildren().add(empresa_Proposta);
        empresa_Proposta.setManaged(false);
        empresa_Proposta.setVisible(false);


        btnSelectTipo.setOnAction(e-> {
            RadioButton rb = (RadioButton) tipo_group.getSelectedToggle();
            if (rb.getText().equals("Estagio(T1)")){
                tipo_de_proposta.setVisible(false);
                t1.setVisible(false);
                t2.setVisible(false);
                t3.setVisible(false);

                tipo_de_proposta.setManaged(false);
                t1.setManaged(false);
                t2.setManaged(false);
                t3.setManaged(false);

                btnSelectTipo.setVisible(false);
                btnSelectTipo.setManaged(false);

                btnAdd.setVisible(true);
                btnAdd.setManaged(true);
                btnApagar.setManaged(true);
                btnApagar.setVisible(true);




                id_proposta.setVisible(true);
                id_proposta.setManaged(true);
                GridPane.setConstraints(id_proposta,0,0);

                ramo_Proposta.setVisible(true);
                ramo_Proposta.setManaged(true);
                GridPane.setConstraints(ramo_Proposta,0,1);

                empresa_Proposta.setVisible(true);
                empresa_Proposta.setManaged(true);
                GridPane.setConstraints(empresa_Proposta,0,2);

                titulo_Proposta.setVisible(true);
                titulo_Proposta.setManaged(true);
                GridPane.setConstraints(titulo_Proposta,0,3);

                btnaddAluno.setManaged(true);
                btnaddAluno.setVisible(true);
                GridPane.setConstraints(btnaddAluno,0,4);


                btnaddAluno.setOnAction(event->{
                    btnaddAluno.setManaged(false);
                    btnaddAluno.setVisible(false);
                    nr_Aluno.setManaged(true);
                    nr_Aluno.setVisible(true);
                    GridPane.setConstraints(nr_Aluno,0,4);
                });

                btnAdd.setOnAction( event ->{
                    if (nr_Aluno.getText() != ""){
                        manager.adicionarProposta("T1", id_proposta.getText(), titulo_Proposta.getText(), Long.parseLong(nr_Aluno.getText()),null, ramo_Proposta.getText(), empresa_Proposta.getText());
                    }else {
                        manager.adicionarProposta("T1", id_proposta.getText(), titulo_Proposta.getText(), null,null, ramo_Proposta.getText(), empresa_Proposta.getText());
                    }
                    popupwindow.close();
                });

            }else if (rb.getText().equals("Projeto(T2)")){
                tipo_de_proposta.setVisible(false);
                t1.setVisible(false);
                t2.setVisible(false);
                t3.setVisible(false);

                tipo_de_proposta.setManaged(false);
                t1.setManaged(false);
                t2.setManaged(false);
                t3.setManaged(false);

                btnSelectTipo.setVisible(false);
                btnSelectTipo.setManaged(false);

                btnAdd.setVisible(true);
                btnAdd.setManaged(true);
                btnApagar.setManaged(true);
                btnApagar.setVisible(true);




                id_proposta.setVisible(true);
                id_proposta.setManaged(true);
                GridPane.setConstraints(id_proposta,0,0);

                email_Docente.setVisible(true);
                email_Docente.setManaged(true);
                GridPane.setConstraints(email_Docente,0,1);

                ramo_Proposta.setVisible(true);
                ramo_Proposta.setManaged(true);
                GridPane.setConstraints(ramo_Proposta,0,2);

                titulo_Proposta.setVisible(true);
                titulo_Proposta.setManaged(true);
                GridPane.setConstraints(titulo_Proposta,0,3);

                btnaddAluno.setManaged(true);
                btnaddAluno.setVisible(true);
                GridPane.setConstraints(btnaddAluno,0,4);


                btnaddAluno.setOnAction(event->{
                    btnaddAluno.setManaged(false);
                    btnaddAluno.setVisible(false);
                    nr_Aluno.setManaged(true);
                    nr_Aluno.setVisible(true);
                    GridPane.setConstraints(nr_Aluno,0,4);
                });

                btnAdd.setOnAction( event ->{
                    if (nr_Aluno.getText() != ""){
                        manager.adicionarProposta("T2", id_proposta.getText(), titulo_Proposta.getText(), Long.parseLong(nr_Aluno.getText()),email_Docente.getText(), ramo_Proposta.getText(), null);
                    }else {
                        manager.adicionarProposta("T2", id_proposta.getText(), titulo_Proposta.getText(), null,email_Docente.getText(), ramo_Proposta.getText(), null);
                    }
                    popupwindow.close();
                });

            } else if (rb.getText().equals("Autoproposto(T3)")) {
                tipo_de_proposta.setVisible(false);
                t1.setVisible(false);
                t2.setVisible(false);
                t3.setVisible(false);

                tipo_de_proposta.setManaged(false);
                t1.setManaged(false);
                t2.setManaged(false);
                t3.setManaged(false);

                btnSelectTipo.setVisible(false);
                btnSelectTipo.setManaged(false);

                btnAdd.setVisible(true);
                btnAdd.setManaged(true);
                btnApagar.setManaged(true);
                btnApagar.setVisible(true);




                id_proposta.setVisible(true);
                id_proposta.setManaged(true);
                GridPane.setConstraints(id_proposta,0,0);

                ramo_Proposta.setVisible(true);
                ramo_Proposta.setManaged(true);
                GridPane.setConstraints(ramo_Proposta,0,2);

                titulo_Proposta.setVisible(true);
                titulo_Proposta.setManaged(true);
                GridPane.setConstraints(titulo_Proposta,0,3);

                nr_Aluno.setManaged(true);
                nr_Aluno.setVisible(true);
                GridPane.setConstraints(nr_Aluno,0,4);


                btnAdd.setOnAction( event ->{
                    manager.adicionarProposta("T3", id_proposta.getText(), titulo_Proposta.getText(), Long.parseLong(nr_Aluno.getText()),null, ramo_Proposta.getText(), null);
                    popupwindow.close();
                });
            }


            //manager.adicionarProposta();


        });


        btnApagar.setOnAction(e-> {
            empresa_Proposta.clear();
            ramo_Proposta.clear();
            email_Docente.clear();
            nr_Aluno.clear();
            titulo_Proposta.clear();
            id_proposta.clear();
        });


        Scene nscene = new Scene(grid, 200, 100);
        popupwindow.setTitle("Adicionar Proposta");
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
            manager.adicionarAluno(nr_Aluno.getText(),nome_Aluno.getText(),email_Aluno.getText(),ramo_Aluno.getText().toUpperCase(Locale.ROOT),Double.parseDouble(classificacao_Aluno.getText()),resultado, curso_Aluno.getText().toUpperCase());
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
    public static void addCandidatura(ProgManager manager){
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        TextField nr_Aluno = new TextField();
        nr_Aluno.setPromptText("Numero do Aluno");
        nr_Aluno.setPrefColumnCount(15);
        grid.getChildren().add(nr_Aluno);


        TextField id_Proposta = new TextField();
        id_Proposta.setPromptText("ID Proposta");
        id_Proposta.setPrefColumnCount(15);
        grid.getChildren().add(id_Proposta);


        Button btnAdd = new Button("Adicionar");
        grid.getChildren().add(btnAdd);

        Button btnApagar = new Button("Apagar");
        grid.getChildren().add(btnApagar);

        GridPane.setConstraints(nr_Aluno,0,0);
        GridPane.setConstraints(id_Proposta,0,1);
        GridPane.setConstraints(btnAdd,2,0);
        GridPane.setConstraints(btnApagar,2,1);

        btnAdd.setOnAction(e-> {
            if (!manager.VerificaAlunoJaCandidato((Long.parseLong(nr_Aluno.getText())))){
                manager.adicionarCandidatura(nr_Aluno.getText(), id_Proposta.getText().toUpperCase());
            }else{
                manager.adicionarPropostaACandidatura(nr_Aluno.getText(),id_Proposta.getText().toUpperCase());
            }
            popupwindow.close();
        });

        btnApagar.setOnAction(e-> {
            nr_Aluno.clear();
            id_Proposta.clear();
        });

        Scene nscene = new Scene(grid, 200, 100);
        popupwindow.setTitle("Adicionar Candidatura");
        popupwindow.setMinWidth(300);
        popupwindow.setMinHeight(125);

        popupwindow.setMaxWidth(nscene.getWidth());
        popupwindow.setMaxHeight(nscene.getHeight());

        popupwindow.setScene(nscene);
        popupwindow.showAndWait();
    }
    public static void display(PopupSupport s,int aux) {
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
    public static void exportar(ProgManager manager){
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(3);
        grid.setHgap(3);

        TextField nome_Ficheiro = new TextField();
        nome_Ficheiro.setPromptText("Nome Ficheiro");
        nome_Ficheiro.setPrefColumnCount(10);
        grid.getChildren().add(nome_Ficheiro);

        Label lbFicheiro = new Label("Nome Ficheiro?");
        grid.getChildren().add(lbFicheiro);
        lbFicheiro.setAlignment(Pos.CENTER);


        Button btnAdd = new Button("Adicionar");
        grid.getChildren().add(btnAdd);

        Button btnApagar = new Button("Apagar");
        grid.getChildren().add(btnApagar);

        GridPane.setConstraints(lbFicheiro,0,0);
        GridPane.setConstraints(nome_Ficheiro,0,1);
        GridPane.setConstraints(btnAdd,0,2);
        GridPane.setConstraints(btnApagar,1,2);

        btnAdd.setOnAction(e-> {
            //adicionar if para ver se tem dados nos hashsets
            switch (manager.getState()){
                case GESTAO_ALUNO -> {
                    manager.exportarAlunos(nome_Ficheiro.getText());
                }
                case GESTAO_PROPOSTA -> {
                    manager.exportarPropostas(nome_Ficheiro.getText());
                }
                case GESTAO_DOCENTE -> {
                    manager.exportarDocentes(nome_Ficheiro.getText());
                }
            }
            popupwindow.close();
        });

        btnApagar.setOnAction(e-> {
            nome_Ficheiro.clear();
        });

        Scene nscene = new Scene(grid, 200, 100);
        popupwindow.setTitle("Exportar Lista");
        popupwindow.setMinWidth(250);
        popupwindow.setMinHeight(150);

        popupwindow.setMaxWidth(nscene.getWidth());
        popupwindow.setMaxHeight(nscene.getHeight());

        popupwindow.setScene(nscene);
        popupwindow.showAndWait();
    }

    public static void avancarFase(ProgManager manager){
        //as vezes da erro se estiver aqui e nao sei pq
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fechar Fase");
        alert.setHeaderText("Ao Fechar a fase nao tera mais a possibilidade de alterar as informacoes");
        alert.setContentText("Pertende Fechar a fase?");
        ButtonType okButton = new ButtonType("Sim", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Nao", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                manager.avancar(true);
            } else if (type == noButton) {
                manager.avancar(false);
            }
        });
    }

}
