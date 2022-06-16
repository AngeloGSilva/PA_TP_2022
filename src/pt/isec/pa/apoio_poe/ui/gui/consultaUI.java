package pt.isec.pa.apoio_poe.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidatura;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;

import java.util.ArrayList;
import java.util.Locale;

public class consultaUI extends BorderPane {
    ProgManager manager;
    Button btnDelete;
    HBox hBox;
    HBox hboxFilters,hboxFiltersConf;

    TableView<Aluno> tableAlunos;
    TableView<Docente> tableDocente;
    TableView<Proposta> tableProposta;

    TableView<Aluno> tableAlunosConf;
    TableView<Docente> tableDocenteConf;
    TableView<Proposta> tablePropostaConf;
    TableView<Candidatura> tableCandidatura;

    ToggleButton tbAuto,tbNotReg,tbReg,tbAluno,tbDocente,tbProposta;
    ToggleGroup tgFilter,tgFilterConf;

    public consultaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnDelete = new Button("eliminar");
        switch (manager.getState()){
            case CONFIGURACAO -> {
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

                tableAlunosConf.setMaxWidth(650);
                tableAlunosConf.setMaxHeight(300);

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

                tableDocenteConf.setMaxWidth(650);
                tableDocenteConf.setMaxHeight(300);

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

                tablePropostaConf.setMaxWidth(650);
                tablePropostaConf.setMaxHeight(300);

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

                tableAlunos.setMaxWidth(650);
                tableAlunos.setMaxHeight(300);

                //table.setItems(manager.getAlunos());
                btnDelete = new Button("Eliminar");

                this.setCenter(tableAlunos);
                hBox = new HBox();
                hBox.getChildren().add(btnDelete);
                hBox.setAlignment(Pos.CENTER);
                this.setBottom(hBox);
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

                tableDocente.setMaxWidth(650);
                tableDocente.setMaxHeight(300);

                btnDelete = new Button("Eliminar");
                //table.setItems(manager.getAlunos());
                this.setCenter(tableDocente);
                hBox = new HBox();
                hBox.getChildren().add(btnDelete);
                hBox.setAlignment(Pos.CENTER);
                this.setBottom(hBox);
                tableDocente.setItems(manager.getDocentes());
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

                tableProposta.setMaxWidth(650);
                tableProposta.setMaxHeight(300);

                btnDelete = new Button("Eliminar");
                this.setCenter(tableProposta);
                hBox = new HBox();
                hBox.getChildren().add(btnDelete);
                hBox.setAlignment(Pos.CENTER);
                this.setBottom(hBox);
                tableProposta.setItems(manager.getPropostas());
            }
            case OPCAO_CANDIDATURA -> {
                //btnDelete.setVisible(true);
/*                //if (manager.get())
                    btnDelete.setDisable(true);
                else
                    btnDelete.setDisable(false);*/
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

                tableAlunos.setMaxWidth(650);
                tableAlunos.setMaxHeight(300);

                tableAlunos.setItems(manager.getCandidaturasNotReg());
                tableAlunos.setVisible(false);

                tbReg = new ToggleButton("Registadas");
                tbAuto = new ToggleButton("Autopropostos");
                tbNotReg = new ToggleButton("Sem Registadas");

                tgFilter = new ToggleGroup();

                tbReg.setToggleGroup(tgFilter);
                tbAuto.setToggleGroup(tgFilter);
                tbNotReg.setToggleGroup(tgFilter);

                tbReg.setAlignment(Pos.TOP_LEFT);
                tbAuto.setAlignment(Pos.TOP_LEFT);
                tbNotReg.setAlignment(Pos.TOP_LEFT);

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

                tableCandidatura.setMaxWidth(650);
                tableCandidatura.setMaxHeight(300);

                hboxFilters = new HBox();
                hboxFilters.getChildren().addAll(tbAuto,tbReg,tbNotReg);
                tbReg.setSelected(true);
                hboxFilters.setAlignment(Pos.CENTER);
                this.setTop(hboxFilters);

                this.setCenter(tableCandidatura);
                hBox = new HBox();
                hBox.getChildren().add(btnDelete);
                btnDelete.setAlignment(Pos.CENTER);
                hBox.setAlignment(Pos.CENTER);
                this.setBottom(hBox);
                tableCandidatura.setItems(manager.getCandidaturas());
            }
        }
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
/*        table.addEventHandler(ActionEvent.ANY, evt ->{
            table.setItems(manager.getAlunos());
        });*/

        if (manager.getState().equals(PoeState.OPCAO_CANDIDATURA)) {
            tbReg.setOnAction(e -> {
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(true);
                this.setCenter(tableCandidatura);
                tableCandidatura.getItems().clear();
                tableCandidatura.setItems(manager.getCandidaturas());
                btnDelete.setDisable(false);
            });

            tbAuto.setOnAction(e -> {
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(true);
                this.setCenter(tableCandidatura);
                tableCandidatura.getItems().clear();
                tableCandidatura.setItems(manager.getCandidaturasAuto());
                btnDelete.setDisable(false);
            });

            tbNotReg.setOnAction(e -> {
                tableCandidatura.getItems().clear();
                tableCandidatura.setVisible(false);
                this.setCenter(tableAlunos);
                tableAlunos.setVisible(true);
                tableAlunos.setItems(manager.getCandidaturasNotReg());
                btnDelete.setDisable(true);
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
                                manager.removerCandidatura(selectedItem.getNralunoString(),td.getResult().toUpperCase(Locale.ROOT));
                            }else {
                                System.out.println("nao é nenhum deles");
                            }
                        }
                    }

                }
                //table.refresh();
            }

        });
    }

    private void update() {
        switch (manager.getState()) {
            case GESTAO_ALUNO -> {
                if (tableAlunos !=null) {
                    tableAlunos.getItems().clear();
                    tableAlunos.setItems(manager.getAlunos());
                }
            }
            case GESTAO_DOCENTE -> {
                if (tableDocente !=null) {
                    tableDocente.getItems().clear();
                    tableDocente.setItems(manager.getDocentes());
                }
            }
            case GESTAO_PROPOSTA -> {
                if (tableProposta !=null) {
                    tableProposta.getItems().clear();
                    tableProposta.setItems(manager.getPropostas());
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

