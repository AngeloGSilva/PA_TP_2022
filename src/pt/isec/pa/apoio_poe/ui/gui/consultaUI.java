package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;

public class consultaUI extends BorderPane {
    ProgManager manager;
    Button btnExportar,btnConsulta,btnLerFich,btnAvancar,btnVoltar;
    Label info;

    TableView<Aluno> tableAlunos;
    TableView<Docente> tableDocente;
    TableView<Proposta> tableProposta;

    public consultaUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        switch (manager.getState()){
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

                tableDocente.setMaxWidth(650);
                tableDocente.setMaxHeight(300);

                //table.setItems(manager.getAlunos());
                this.setCenter(tableDocente);
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

                this.setCenter(tableProposta);
                tableProposta.setItems(manager.getPropostas());
            }
        }


        //this.setRight(info);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
/*        table.addEventHandler(ActionEvent.ANY, evt ->{
            table.setItems(manager.getAlunos());
        });*/
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
            //table.refresh();
        }
        }
    }

