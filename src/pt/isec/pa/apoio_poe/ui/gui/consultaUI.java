package pt.isec.pa.apoio_poe.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.Locale;

public class consultaUI extends BorderPane {
    ProgManager manager;
    Button btnDelete,btnAdd;
    SplitMenuButton btnFilter;
    Tooltip tooltip;
    HBox hBox;
    VBox vBox;
    HBox hboxFilters,hboxFiltersConf;

    TableView<Aluno> tableAlunos;
    TableView<Docente> tableDocente;
    TableView<Proposta> tableProposta;

    TableView<Aluno> tableAlunosConf;
    TableView<Docente> tableDocenteConf;
    TableView<Proposta> tablePropostaConf;
    TableView<Candidatura> tableCandidatura;
    TableView<Atribuicao> tableAtribuicoesPro;
    TableView<Atribuicao> tableAtribuicoesOri;

    ToggleButton tbAuto,tbNotReg,tbReg,tbAluno,tbDocente,tbProposta;
    ToggleGroup tgFilter,tgFilterConf;

    MenuItem delete;

    public consultaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setMaxSize(750,450);
        vBox = new VBox();
        btnAdd = new Button();
        btnDelete = new Button();

        tooltip = new Tooltip("Adicionar");
        tooltip.setShowDelay(Duration.seconds(0.3));
        btnAdd.setTooltip(tooltip);
        tooltip = new Tooltip("Apagar");
        tooltip.setShowDelay(Duration.seconds(0.3));
        btnDelete.setTooltip(tooltip);

        ImageView imageView = new ImageView(ImageManager.getImage("addv1.png"));
        btnAdd.setGraphic(imageView);
        imageView = new ImageView(ImageManager.getImage("deletev1.png"));
        btnDelete.setGraphic(imageView);

        vBox.getChildren().addAll(btnAdd, btnDelete);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.setLeft(vBox);

        delete = new MenuItem("Delete");

        btnFilter = new SplitMenuButton();
        imageView = new ImageView(ImageManager.getImage("filter.png"));
        imageView.setFitHeight(17);
        imageView.setFitWidth(15);
        btnFilter.setGraphic(imageView);
        MenuItem choice1 = new MenuItem("Choice 1");
        MenuItem choice2 = new MenuItem("Choice 2");
        btnFilter.getItems().addAll(choice1,choice2);



        switch (manager.getState()){

            case CONFIGURACAO -> {
                this.setLeft(null);
                tableAlunosConf = new TableView<Aluno>();
                TableColumn nome = new TableColumn("Nome");
                nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
                nome.setMinWidth(100);

                TableColumn nr_Aluno = new TableColumn("Numero");
                nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("nr_Aluno"));
                nr_Aluno.setMinWidth(85);

                TableColumn email_Aluno = new TableColumn("Email");
                email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email_Aluno"));
                email_Aluno.setMinWidth(140);

                TableColumn curso = new TableColumn("Curso");
                curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
                curso.setMinWidth(50);

                TableColumn ramo_Aluno = new TableColumn("Ramo");
                ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ramo_Aluno"));
                ramo_Aluno.setMinWidth(50);

                TableColumn classificacao_Aluno = new TableColumn("Classificacão");
                classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao_Aluno"));
                classificacao_Aluno.setMinWidth(50);

                TableColumn aceder_a_Estagio = new TableColumn("Estagio");
                aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("aceder_a_Estagio"));
                aceder_a_Estagio.setMinWidth(50);
                tableAlunosConf.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);

                tableAlunosConf.setMaxWidth(750);
                tableAlunosConf.setMaxHeight(450);

                this.setCenter(tableAlunosConf);
                tableAlunosConf.setItems(manager.getAlunos());


                tableDocenteConf = new TableView<Docente>();
                TableColumn nomeDocente = new TableColumn("Nome");
                nomeDocente.setCellValueFactory(new PropertyValueFactory<Docente, String>("nome_Docente"));
                nomeDocente.setMinWidth(100);

                TableColumn papel_Docente = new TableColumn("Papel");
                papel_Docente.setCellValueFactory(new PropertyValueFactory<Docente, Boolean>("papel_Docente"));
                papel_Docente.setMinWidth(85);

                TableColumn email_Docente = new TableColumn("Email");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(140);

                tableDocenteConf.getColumns().addAll(nomeDocente,papel_Docente,email_Docente);

                tableDocenteConf.setMaxWidth(750);
                tableDocenteConf.setMaxHeight(450);

                tableDocenteConf.setItems(manager.getDocentes());

                tablePropostaConf = new TableView<Proposta>();
                TableColumn cod_ID = new TableColumn("Nome");
                cod_ID.setCellValueFactory(new PropertyValueFactory<Docente, String>("cod_ID"));
                cod_ID.setMinWidth(100);

                TableColumn titulo = new TableColumn("Titulo");
                titulo.setCellValueFactory(new PropertyValueFactory<Docente, String>("titulo"));
                titulo.setMinWidth(100);

                TableColumn codigo_Aluno = new TableColumn("Codigo Aluno");
                codigo_Aluno.setCellValueFactory(new PropertyValueFactory<Docente, String>("codigo_Aluno"));
                codigo_Aluno.setMinWidth(100);

                TableColumn email_DocenteProposta = new TableColumn("Email Docente");
                email_DocenteProposta.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_DocenteProposta.setMinWidth(100);

                TableColumn empresa = new TableColumn("Empresa");
                empresa.setCellValueFactory(new PropertyValueFactory<Docente, String>("empresa"));
                empresa.setMinWidth(100);

                TableColumn ramo = new TableColumn("Ramo");
                ramo.setCellValueFactory(new PropertyValueFactory<Docente, String>("ramo"));
                ramo.setMinWidth(100);

                tablePropostaConf.getColumns().addAll(cod_ID,titulo,codigo_Aluno,email_Docente,empresa,ramo);

                tablePropostaConf.setMaxWidth(750);
                tablePropostaConf.setMaxHeight(450);

                tablePropostaConf.setItems(manager.getPropostas());

                tbAluno = new ToggleButton("Alunos");
                tbDocente = new ToggleButton("Docentes");
                tbProposta = new ToggleButton("Propostas");

                tgFilterConf = new ToggleGroup();

                tbAluno.setToggleGroup(tgFilterConf);
                tbDocente.setToggleGroup(tgFilterConf);
                tbProposta.setToggleGroup(tgFilterConf);

                tbAluno.setAlignment(Pos.TOP_LEFT);
                tbDocente.setAlignment(Pos.TOP_LEFT);
                tbProposta.setAlignment(Pos.TOP_LEFT);

                hboxFiltersConf = new HBox();
                hboxFiltersConf.getChildren().addAll(tbAluno,tbDocente,tbProposta);
                tbAluno.setSelected(true);
                hboxFiltersConf.setAlignment(Pos.CENTER);
                this.setTop(hboxFiltersConf);


            }
            case GESTAO_ALUNO -> {
                tableAlunos = new TableView<Aluno>();
                TableColumn nome = new TableColumn("Nome");
                nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
                nome.setMinWidth(100);

                TableColumn nr_Aluno = new TableColumn("Numero");
                nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("nr_Aluno"));
                nr_Aluno.setMinWidth(85);

                TableColumn email_Aluno = new TableColumn("Email");
                email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email_Aluno"));
                email_Aluno.setMinWidth(140);

                TableColumn curso = new TableColumn("Curso");
                curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
                curso.setMinWidth(50);

                TableColumn ramo_Aluno = new TableColumn("Ramo");
                ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ramo_Aluno"));
                ramo_Aluno.setMinWidth(50);

                TableColumn classificacao_Aluno = new TableColumn("Classificacão");
                classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao_Aluno"));
                classificacao_Aluno.setMinWidth(50);

                TableColumn aceder_a_Estagio = new TableColumn("Estagio");
                aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("aceder_a_Estagio"));
                aceder_a_Estagio.setMinWidth(50);
                tableAlunos.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);

                tableAlunos.setMaxWidth(750);
                tableAlunos.setMaxHeight(450);

                this.setCenter(tableAlunos);
                tableAlunos.setItems(manager.getAlunos());

            }
            case GESTAO_DOCENTE -> {
                tableDocente = new TableView<Docente>();
                TableColumn nomeDocente = new TableColumn("Nome");
                nomeDocente.setCellValueFactory(new PropertyValueFactory<Docente, String>("nome_Docente"));
                nomeDocente.setMinWidth(100);

                TableColumn papel_Docente = new TableColumn("Papel");
                papel_Docente.setCellValueFactory(new PropertyValueFactory<Docente, Boolean>("papel_Docente"));
                papel_Docente.setMinWidth(85);

                TableColumn email_Docente = new TableColumn("Email");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(140);

                tableDocente.getColumns().addAll(nomeDocente,papel_Docente,email_Docente);

                tableDocente.setMaxWidth(750);
                tableDocente.setMaxHeight(450);

                this.setCenter(tableDocente);
                tableDocente.setItems(manager.getDocentes());
                tableDocente.setUserData(tableDocente);



            }
            case GESTAO_PROPOSTA -> {
                tableProposta = new TableView<Proposta>();
                TableColumn cod_ID = new TableColumn("Nome");
                cod_ID.setCellValueFactory(new PropertyValueFactory<Docente, String>("cod_ID"));
                cod_ID.setMinWidth(100);

                TableColumn titulo = new TableColumn("Titulo");
                titulo.setCellValueFactory(new PropertyValueFactory<Docente, String>("titulo"));
                titulo.setMinWidth(100);

                TableColumn codigo_Aluno = new TableColumn("Codigo Aluno");
                codigo_Aluno.setCellValueFactory(new PropertyValueFactory<Docente, String>("codigo_Aluno"));
                codigo_Aluno.setMinWidth(100);

                TableColumn email_Docente = new TableColumn("Email Docente");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(100);

                TableColumn empresa = new TableColumn("Empresa");
                empresa.setCellValueFactory(new PropertyValueFactory<Docente, String>("empresa"));
                empresa.setMinWidth(100);

                TableColumn ramo = new TableColumn("Ramo");
                ramo.setCellValueFactory(new PropertyValueFactory<Docente, String>("ramo"));
                ramo.setMinWidth(100);

                tableProposta.getColumns().addAll(cod_ID,titulo,codigo_Aluno,email_Docente,empresa,ramo);

                tableProposta.setMaxWidth(750);
                tableProposta.setMaxHeight(450);

                this.setCenter(tableProposta);
                tableProposta.setItems(manager.getPropostas());


            }
            case OPCAO_CANDIDATURA -> {

                btnDelete.setDisable(false);

                tableAlunos = new TableView<Aluno>();
                TableColumn nome = new TableColumn("Nome");
                nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
                nome.setMinWidth(100);

                TableColumn nr_Aluno = new TableColumn("Numero");
                nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("nr_Aluno"));
                nr_Aluno.setMinWidth(85);

                TableColumn email_Aluno = new TableColumn("Email");
                email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email_Aluno"));
                email_Aluno.setMinWidth(140);

                TableColumn curso = new TableColumn("Curso");
                curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
                curso.setMinWidth(50);

                TableColumn ramo_Aluno = new TableColumn("Ramo");
                ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ramo_Aluno"));
                ramo_Aluno.setMinWidth(50);

                TableColumn classificacao_Aluno = new TableColumn("Classificacão");
                classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao_Aluno"));
                classificacao_Aluno.setMinWidth(50);

                TableColumn aceder_a_Estagio = new TableColumn("Estagio");
                aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("aceder_a_Estagio"));
                aceder_a_Estagio.setMinWidth(50);
                tableAlunos.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);

                tableAlunos.setMaxWidth(750);
                tableAlunos.setMaxHeight(450);

                tableAlunos.setItems(manager.getCandidaturasNotReg());
                tableAlunos.setVisible(false);

                tbReg = new ToggleButton("Registadas");
                tbAuto = new ToggleButton("Autopropostos");
                tbNotReg = new ToggleButton("Sem Registadas");

                tgFilter = new ToggleGroup();

                tbReg.setToggleGroup(tgFilter);
                tbAuto.setToggleGroup(tgFilter);
                tbNotReg.setToggleGroup(tgFilter);

                tbReg.setAlignment(Pos.CENTER);
                tbAuto.setAlignment(Pos.CENTER);
                tbNotReg.setAlignment(Pos.CENTER);

                tableCandidatura = new TableView<Candidatura>();
                TableColumn<Candidatura, String> numeroAluno = new TableColumn<Candidatura, String>("Numero");
                numeroAluno.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getNralunoString()));
                numeroAluno.setMinWidth(100);

                TableColumn<Candidatura, String> nomeAluno = new TableColumn<Candidatura, String>("Nome");
                nomeAluno.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getNomeAluno()));
                nomeAluno.setMinWidth(100);

                TableColumn<Candidatura, String> cod_ID = new TableColumn<Candidatura, String>("Codigo da Proposta");
                cod_ID.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getIdPropostas().toString()));
                cod_ID.setMinWidth(200);

                tableCandidatura.getColumns().addAll(numeroAluno,nomeAluno,cod_ID);

                tableCandidatura.setMaxWidth(750);
                tableCandidatura.setMaxHeight(450);
                hboxFilters = new HBox();
                hboxFilters.getChildren().addAll(tbAuto,tbReg,tbNotReg);
                tbReg.setSelected(true);
                hboxFilters.setAlignment(Pos.BOTTOM_CENTER);
                //hboxFilters.setPadding(new Insets(bottom));
                //this.topProperty(hboxFilters);
                this.setTop(hboxFilters);
                //HBox.setHgrow(hboxFilters, Priority.ALWAYS);


                this.setCenter(tableCandidatura);
                tableCandidatura.setItems(manager.getCandidaturas());


                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getChildren().add(btnFilter);
                AnchorPane.setLeftAnchor(btnFilter,10.0);
                AnchorPane.setTopAnchor(btnFilter,25.0);
                this.getChildren().add(anchorPane);

            }
            case ATRIBUIR_PROPOSTA -> {
                tableAtribuicoesPro = new TableView<Atribuicao>();

                TableColumn<Atribuicao, String> nome_Aluno = new TableColumn<Atribuicao, String>("Nome");
                nome_Aluno.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getAluno().getNome_Aluno()));
                nome_Aluno.setMinWidth(100);

                TableColumn<Atribuicao, String> numero_Aluno = new TableColumn<Atribuicao, String>("Numero");
                numero_Aluno.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getAluno().getNr_AlunoString()));
                numero_Aluno.setMinWidth(85);

                TableColumn<Atribuicao, String> nome_Docente = new TableColumn<Atribuicao, String>("Docente");
                nome_Docente.setCellValueFactory(cellData ->
                            new SimpleStringProperty(cellData.getValue().getNomeDocenteTV())
                        );
                nome_Docente.setMinWidth(140);

                TableColumn<Atribuicao, String> id_Proposta = new TableColumn<Atribuicao, String>("ID");
                id_Proposta.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getProposta().getCod_ID()));
                id_Proposta.setMinWidth(140);


                tableAtribuicoesPro.getColumns().addAll(nome_Aluno,numero_Aluno,nome_Docente,id_Proposta);

                tableAtribuicoesPro.setMaxWidth(750);
                tableAtribuicoesPro.setMaxHeight(450);

                this.setCenter(tableAtribuicoesPro);
                tableAtribuicoesPro.getItems().clear();
                tableAtribuicoesPro.setItems(manager.getAtribuicoes());
            }
            case ATRIBUIR_ORIENTADOR -> {
                tableAtribuicoesOri = new TableView<Atribuicao>();
                TableColumn <Atribuicao,String> nome_Docente = new TableColumn("Nome Docente");
                nome_Docente.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getDocente().getNome_Docente()));
                nome_Docente.setMinWidth(100);

                TableColumn <Atribuicao,String> email_Docente = new TableColumn("Email Docente");
                email_Docente.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getDocente().getEmail_Docente()));
                email_Docente.setMinWidth(140);

                TableColumn <Atribuicao,String> id_Proposta = new TableColumn("Proposta");
                id_Proposta.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getProposta().getCod_ID()));
                id_Proposta.setMinWidth(140);

                TableColumn <Atribuicao,String> nr_Aluno = new TableColumn("Numero Aluno");
                nr_Aluno.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getAluno().getNr_AlunoString()));
                nr_Aluno.setMinWidth(140);

                tableAtribuicoesOri.getColumns().addAll(nome_Docente,email_Docente,nr_Aluno,id_Proposta);

                tableAtribuicoesOri.setMaxWidth(750);
                tableAtribuicoesOri.setMaxHeight(450);

                this.setCenter(tableAtribuicoesOri);
                tableAtribuicoesOri.setItems(manager.getAtribuicoes());

            }
        }
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });

/*        delete.setOnAction(event -> {
                Aluno selectedItem = tableAlunos.getSelectionModel().getSelectedItem();
                tableAlunos.getItems().remove(selectedItem);
                manager.remover(selectedItem.getNr_AlunoString());
        });*/

        if (manager.getState().equals(PoeState.OPCAO_CANDIDATURA)) {
            btnFilter.setOnAction(e ->{

            });
            tbReg.setOnAction(e -> {
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(true);
                this.setCenter(tableCandidatura);
                tableCandidatura.getItems().clear();
                tableCandidatura.setItems(manager.getCandidaturas());
                btnDelete.setDisable(false);
                btnAdd.setDisable(false);
            });

            tbAuto.setOnAction(e -> {
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(true);
                this.setCenter(tableCandidatura);
                tableCandidatura.getItems().clear();
                tableCandidatura.setItems(manager.getCandidaturasAuto());
                btnDelete.setDisable(false);
                btnAdd.setDisable(false);
            });

            tbNotReg.setOnAction(e -> {
                tableCandidatura.getItems().clear();
                tableCandidatura.setVisible(false);
                this.setCenter(tableAlunos);
                tableAlunos.setVisible(true);
                tableAlunos.setItems(manager.getCandidaturasNotReg());
                btnDelete.setDisable(true);
                btnAdd.setDisable(true);
            });
        }

        if (manager.getState().equals(PoeState.CONFIGURACAO) && manager.getFase_gestao()) {
            tbAluno.setOnAction(e -> {
                tableAlunosConf.setVisible(true);
                tableDocenteConf.setVisible(false);
                tablePropostaConf.setVisible(false);
                this.setCenter(tableAlunosConf);
                tableAlunosConf.getItems().clear();
                tableAlunosConf.setItems(manager.getAlunos());
            });

            tbDocente.setOnAction(e -> {
                tableAlunosConf.setVisible(false);
                tableDocenteConf.setVisible(true);
                tablePropostaConf.setVisible(false);
                this.setCenter(tableDocenteConf);
                tableDocenteConf.getItems().clear();
                tableDocenteConf.setItems(manager.getDocentes());
            });

            tbProposta.setOnAction(e -> {
                tableAlunosConf.setVisible(false);
                tableDocenteConf.setVisible(false);
                tablePropostaConf.setVisible(true);
                this.setCenter(tablePropostaConf);
                tablePropostaConf.getItems().clear();
                tablePropostaConf.setItems(manager.getPropostas());
            });
        }

        btnAdd.setOnAction(e ->{
            switch (manager.getState()) {
            case GESTAO_ALUNO -> {
                if (tableAlunos !=null) {
                    Popup.addAluno(manager);
                }
            }
            case GESTAO_DOCENTE -> {
                if (tableDocente !=null) {
                    Popup.addDocente(manager);
                }
            }
            case GESTAO_PROPOSTA -> {
                if (tableProposta !=null) {
                    Popup.addProposta(manager);
                }
            }
            case OPCAO_CANDIDATURA -> {
                if (tableCandidatura !=null) {
                    Candidatura selectedItem = tableCandidatura.getSelectionModel().getSelectedItem();
                    TextInputDialog td = new TextInputDialog("Codigo Proposta");
                    td.setHeaderText("Qual Proposta do Aluno " + selectedItem.getNomeAluno() + " deseja apagar?\n" + selectedItem.getIdPropostas());
                    td.showAndWait();
                    //tableCandidatura.getItems().remove(selectedItem);
                    System.out.println(td.getResult().toUpperCase(Locale.ROOT));
                    for (String s: selectedItem.getIdPropostas()) {
                        if (td.getResult().toUpperCase(Locale.ROOT).equals(s)){
                            System.out.println("é um camelo");
                            System.out.println(selectedItem.getNralunoString());
                            manager.removerPropostaDeCandidatura(selectedItem.getNralunoString(),td.getResult().toUpperCase(Locale.ROOT));
                        }else {
                            System.out.println("nao é nenhum deles");
                        }
                    }
                }

            }
        }
        });

        btnDelete.setOnAction(e -> {
            switch (manager.getState()) {
                case GESTAO_ALUNO -> {
                    if (tableAlunos !=null) {
                        Aluno selectedItem = tableAlunos.getSelectionModel().getSelectedItem();
                        tableAlunos.getItems().remove(selectedItem);
                        manager.remover(selectedItem.getNr_AlunoString());
                        System.out.println(selectedItem.getNr_Aluno());

                    }
                }
                case GESTAO_DOCENTE -> {
                    if (tableDocente !=null) {
                        Docente selectedItem = tableDocente.getSelectionModel().getSelectedItem();
                        tableDocente.getItems().remove(selectedItem);
                        manager.remover(selectedItem.getEmail_Docente());
                        System.out.println(selectedItem.getEmail_Docente());
                    }
                }
                case GESTAO_PROPOSTA -> {
                    if (tableProposta !=null) {
                        Proposta selectedItem = tableProposta.getSelectionModel().getSelectedItem();
                        tableProposta.getItems().remove(selectedItem);
                        manager.remover(selectedItem.getCod_ID());
                        System.out.println(selectedItem.getCod_ID());
                    }
                }
                case OPCAO_CANDIDATURA -> {
                    if (tableCandidatura !=null) {
                        Candidatura selectedItem = tableCandidatura.getSelectionModel().getSelectedItem();
                        TextInputDialog td = new TextInputDialog("Codigo Proposta");
                        td.setHeaderText("Qual Proposta do Aluno " + selectedItem.getNomeAluno() + " deseja apagar?\n" + selectedItem.getIdPropostas());
                        td.showAndWait();
                        //tableCandidatura.getItems().remove(selectedItem);
                        System.out.println(td.getResult().toUpperCase(Locale.ROOT));
                        for (String s: selectedItem.getIdPropostas()) {
                            if (td.getResult().toUpperCase(Locale.ROOT).equals(s)){
                                System.out.println("é um camelo");
                                System.out.println(selectedItem.getNralunoString());
                                manager.removerPropostaDeCandidatura(selectedItem.getNralunoString(),td.getResult().toUpperCase(Locale.ROOT));
                            }else {
                                System.out.println("nao é nenhum deles");
                            }
                        }
                    }
                }
            }
        });
    }

    private void update() {
        switch (manager.getState()) {
            case GESTAO_ALUNO -> {
                if (tableAlunos !=null) {
                    tableAlunos.getItems().clear();
                    tableAlunos.setItems(manager.getAlunos());
                    tableAlunos.setContextMenu(new ContextMenu(delete));
                }
            }
            case GESTAO_DOCENTE -> {
                if (tableDocente !=null) {
                    tableDocente.getItems().clear();
                    tableDocente.setItems(manager.getDocentes());
                    tableDocente.setContextMenu(new ContextMenu(delete));
                }
            }
            case GESTAO_PROPOSTA -> {
                if (tableProposta !=null) {
                    tableProposta.getItems().clear();
                    tableProposta.setItems(manager.getPropostas());
                    tableProposta.setContextMenu(new ContextMenu(delete));
                }
            }

            case ATRIBUIR_PROPOSTA -> {
                if (tableAtribuicoesPro != null){
                    tableAtribuicoesPro.getItems().clear();
                    tableAtribuicoesPro.setItems(manager.getAtribuicoes());
                    tableAtribuicoesPro.setContextMenu(new ContextMenu(delete));
                }
            }
            case ATRIBUIR_ORIENTADOR -> {
                if (tableAtribuicoesOri != null){
                    tableAtribuicoesOri.getItems().clear();
                    tableAtribuicoesOri.setItems(manager.getAtribuicoes());
                    tableAtribuicoesOri.setContextMenu(new ContextMenu(delete));
                }
            }
            default -> {
/*                tableProposta.refresh();
                tableAlunos.refresh();
                tableDocente.refresh();*/
            }
        }
        }
    }

