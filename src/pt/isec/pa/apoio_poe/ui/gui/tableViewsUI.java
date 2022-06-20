package pt.isec.pa.apoio_poe.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.util.Locale;

public class tableViewsUI extends BorderPane {
    ProgManager manager;
    Button btnDelete,btnAdd;

    SplitMenuButton btnMenuAluno,btnMenuProposta;
    MenuItem itemAuto, itemNotReg, itemReg;
    MenuItem itemAutoPropostas, itemProDocentes, itemProCandidaturas, itemProSemCandidaturas;
    MenuItem itemAutoAssociadoProposta, itemPropostasAtri, itemPropostaNaoAssociada;
    MenuItem itemAutoAssociadoAluno, itemPropostasDisp, itemPropostasAtribuidas;

    //ToggleButton tbAuto,tbNotReg,tbReg;
    ToggleButton tbAluno,tbDocente,tbProposta,tbCandidatura,tbAtribuicoes;

    ToggleGroup tgFilter,tgFilterConf,tgConsultaUI;
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

    //tabelas para o UI final
    TableView<Aluno> tableAlunosFinal;
    TableView<Docente> tableDocenteFinal;
    TableView<Proposta> tablePropostaFinal;
    TableView<Candidatura> tableCandidaturaFinal;
    TableView<Atribuicao> tableAtribuicoesFinal;

    TableColumn nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio;
    TableColumn nomeDocente,papel_Docente,email_Docente;
    TableColumn cod_ID,id_Proposta,titulo,codigo_Aluno,email_DocenteProposta,empresa,ramo;



    MenuItem delete;

    public tableViewsUI(ProgManager manager) {
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

        btnMenuAluno = new SplitMenuButton();
        btnMenuAluno.setText("Alunos");
        imageView = new ImageView(ImageManager.getImage("estudante.png"));
        imageView.setFitHeight(17);
        imageView.setFitWidth(15);
        btnMenuAluno.setGraphic(imageView);

        btnMenuProposta = new SplitMenuButton();
        btnMenuProposta.setText("propostas");
        imageView = new ImageView(ImageManager.getImage("propostas.png"));
        imageView.setFitHeight(17);
        imageView.setFitWidth(15);
        btnMenuProposta.setGraphic(imageView);

        switch (manager.getState()){

            case CONFIGURACAO -> {
                this.setLeft(null);
                tableAlunosConf = new TableView<Aluno>();
                nome = new TableColumn("Nome");
                nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
                nome.setMinWidth(100);

                nr_Aluno = new TableColumn("Numero");
                nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("nr_Aluno"));
                nr_Aluno.setMinWidth(85);

                email_Aluno = new TableColumn("Email");
                email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email_Aluno"));
                email_Aluno.setMinWidth(140);

                curso = new TableColumn("Curso");
                curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
                curso.setMinWidth(50);

                ramo_Aluno = new TableColumn("Ramo");
                ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ramo_Aluno"));
                ramo_Aluno.setMinWidth(50);

                classificacao_Aluno = new TableColumn("Classificacão");
                classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao_Aluno"));
                classificacao_Aluno.setMinWidth(50);

                aceder_a_Estagio = new TableColumn("Estagio");
                aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("aceder_a_Estagio"));
                aceder_a_Estagio.setMinWidth(50);
                tableAlunosConf.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);

                tableAlunosConf.setMaxWidth(750);
                tableAlunosConf.setMaxHeight(450);

                this.setCenter(tableAlunosConf);
                tableAlunosConf.setItems(manager.getAlunos());


                tableDocenteConf = new TableView<Docente>();
                nomeDocente = new TableColumn("Nome");
                nomeDocente.setCellValueFactory(new PropertyValueFactory<Docente, String>("nome_Docente"));
                nomeDocente.setMinWidth(100);

                papel_Docente = new TableColumn("Papel");
                papel_Docente.setCellValueFactory(new PropertyValueFactory<Docente, Boolean>("papel_Docente"));
                papel_Docente.setMinWidth(85);

                email_Docente = new TableColumn("Email");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(140);

                tableDocenteConf.getColumns().addAll(nomeDocente,papel_Docente,email_Docente);

                tableDocenteConf.setMaxWidth(750);
                tableDocenteConf.setMaxHeight(450);

                tableDocenteConf.setItems(manager.getDocentes());

                tablePropostaConf = new TableView<Proposta>();
                cod_ID = new TableColumn("Nome");
                cod_ID.setCellValueFactory(new PropertyValueFactory<Docente, String>("cod_ID"));
                cod_ID.setMinWidth(100);

                titulo = new TableColumn("Titulo");
                titulo.setCellValueFactory(new PropertyValueFactory<Docente, String>("titulo"));
                titulo.setMinWidth(100);

                codigo_Aluno = new TableColumn("Codigo Aluno");
                codigo_Aluno.setCellValueFactory(new PropertyValueFactory<Docente, String>("codigo_Aluno"));
                codigo_Aluno.setMinWidth(100);

                email_DocenteProposta = new TableColumn("Email Docente");
                email_DocenteProposta.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_DocenteProposta.setMinWidth(100);

                empresa = new TableColumn("Empresa");
                empresa.setCellValueFactory(new PropertyValueFactory<Docente, String>("empresa"));
                empresa.setMinWidth(100);

                ramo = new TableColumn("Ramo");
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
                nome = new TableColumn("Nome");
                nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
                nome.setCellFactory(TextFieldTableCell.forTableColumn());
                nome.setMinWidth(100);

                nr_Aluno = new TableColumn("Numero");
                nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("nr_Aluno"));
                nr_Aluno.setMinWidth(85);

                email_Aluno = new TableColumn("Email");
                email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email_Aluno"));
                email_Aluno.setMinWidth(140);

                curso = new TableColumn("Curso");
                curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
                curso.setMinWidth(50);

                ramo_Aluno = new TableColumn("Ramo");
                ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ramo_Aluno"));
                ramo_Aluno.setMinWidth(50);

                classificacao_Aluno = new TableColumn("Classificacão");
                classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao_Aluno"));
                classificacao_Aluno.setMinWidth(50);

                aceder_a_Estagio = new TableColumn("Estagio");
                aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("aceder_a_Estagio"));
                aceder_a_Estagio.setMinWidth(50);
                tableAlunos.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);

                tableAlunos.setMaxWidth(750);
                tableAlunos.setMaxHeight(450);

                this.setCenter(tableAlunos);
                tableAlunos.setItems(manager.getAlunos());

                tableAlunos.setEditable(true);


            }
            case GESTAO_DOCENTE -> {
                tableDocente = new TableView<Docente>();
                nomeDocente = new TableColumn("Nome");
                nomeDocente.setCellValueFactory(new PropertyValueFactory<Docente, String>("nome_Docente"));
                nomeDocente.setCellFactory(TextFieldTableCell.forTableColumn());
                nomeDocente.setMinWidth(100);

                papel_Docente = new TableColumn("Papel");
                papel_Docente.setCellValueFactory(new PropertyValueFactory<Docente, Boolean>("papel_Docente"));
                papel_Docente.setMinWidth(85);

                email_Docente = new TableColumn("Email");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(140);

                tableDocente.getColumns().addAll(nomeDocente,papel_Docente,email_Docente);

                tableDocente.setMaxWidth(750);
                tableDocente.setMaxHeight(450);

                this.setCenter(tableDocente);
                tableDocente.setItems(manager.getDocentes());
                tableDocente.setUserData(tableDocente);

                tableDocente.setEditable(true);

            }
            case GESTAO_PROPOSTA -> {
                tableProposta = new TableView<Proposta>();
                cod_ID = new TableColumn("Codigo");
                cod_ID.setCellValueFactory(new PropertyValueFactory<Proposta, String>("cod_ID"));
                cod_ID.setMinWidth(100);

                titulo = new TableColumn("Titulo");
                titulo.setCellValueFactory(new PropertyValueFactory<Docente, String>("titulo"));
                titulo.setMinWidth(100);

                codigo_Aluno = new TableColumn("Codigo Aluno");
                codigo_Aluno.setCellValueFactory(new PropertyValueFactory<Docente, String>("codigo_Aluno"));
                codigo_Aluno.setMinWidth(100);

                email_Docente = new TableColumn("Email Docente");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(100);

                empresa = new TableColumn("Empresa");
                empresa.setCellValueFactory(new PropertyValueFactory<Docente, String>("empresa"));
                empresa.setMinWidth(100);

                ramo = new TableColumn("Ramo");
                ramo.setCellValueFactory(new PropertyValueFactory<Docente, String>("ramo"));
                ramo.setMinWidth(100);

                tableProposta.getColumns().addAll(cod_ID,titulo,codigo_Aluno,email_Docente,empresa,ramo);

                tableProposta.setMaxWidth(750);
                tableProposta.setMaxHeight(450);

                this.setCenter(tableProposta);
                tableProposta.setItems(manager.getPropostas());


            }
            case OPCAO_CANDIDATURA -> {

                if (manager.getFase_Candidatura()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }
                btnDelete.setDisable(false);

                tableAlunos = new TableView<Aluno>();
                nome = new TableColumn("Nome");
                nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
                nome.setMinWidth(100);

                nr_Aluno = new TableColumn("Numero");
                nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("nr_Aluno"));
                nr_Aluno.setMinWidth(85);

                email_Aluno = new TableColumn("Email");
                email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email_Aluno"));
                email_Aluno.setMinWidth(140);

                curso = new TableColumn("Curso");
                curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
                curso.setMinWidth(50);

                ramo_Aluno = new TableColumn("Ramo");
                ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ramo_Aluno"));
                ramo_Aluno.setMinWidth(50);

                classificacao_Aluno = new TableColumn("Classificacão");
                classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao_Aluno"));
                classificacao_Aluno.setMinWidth(50);

                aceder_a_Estagio = new TableColumn("Estagio");
                aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("aceder_a_Estagio"));
                aceder_a_Estagio.setMinWidth(50);
                tableAlunos.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);

                tableAlunos.setMaxWidth(750);
                tableAlunos.setMaxHeight(450);

                tableAlunos.setItems(manager.getCandidaturasNotReg());
                tableAlunos.setVisible(false);

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

                tableProposta = new TableView<Proposta>();
                id_Proposta = new TableColumn("Codigo");
                id_Proposta.setCellValueFactory(new PropertyValueFactory<Proposta, String>("cod_ID"));
                id_Proposta.setMinWidth(100);

                titulo = new TableColumn("Titulo");
                titulo.setCellValueFactory(new PropertyValueFactory<Docente, String>("titulo"));
                titulo.setMinWidth(100);

                codigo_Aluno = new TableColumn("Codigo Aluno");
                codigo_Aluno.setCellValueFactory(new PropertyValueFactory<Docente, String>("codigo_Aluno"));
                codigo_Aluno.setMinWidth(100);

                email_Docente = new TableColumn("Email Docente");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(100);

                empresa = new TableColumn("Empresa");
                empresa.setCellValueFactory(new PropertyValueFactory<Docente, String>("empresa"));
                empresa.setMinWidth(100);

                ramo = new TableColumn("Ramo");
                ramo.setCellValueFactory(new PropertyValueFactory<Docente, String>("ramo"));
                ramo.setMinWidth(100);

                tableProposta.getColumns().addAll(id_Proposta,titulo,codigo_Aluno,email_Docente,empresa,ramo);

                tableProposta.setMaxWidth(750);
                tableProposta.setMaxHeight(450);

                tableProposta.setVisible(false);

                hboxFilters = new HBox();
                hboxFilters.getChildren().addAll(btnMenuAluno,btnMenuProposta);
                hboxFilters.setAlignment(Pos.BOTTOM_CENTER);
                this.setTop(hboxFilters);

                this.setCenter(tableCandidatura);
                tableCandidatura.setItems(manager.getCandidaturas());

                itemReg = new MenuItem("Registadas");
                itemAuto = new MenuItem("Autopropostos");
                itemNotReg = new MenuItem("Sem Registadas");

                btnMenuAluno.getItems().addAll(itemReg,itemAuto,itemNotReg);

                itemProCandidaturas = new MenuItem("Com Candidaturas");
                itemAutoPropostas = new MenuItem("Autopropostos");
                itemProDocentes = new MenuItem("Propostas de Docentes");
                itemProSemCandidaturas = new MenuItem("Propostas Sem Candidaturas");

                btnMenuProposta.getItems().addAll(itemAutoPropostas,itemProCandidaturas,itemProDocentes,itemProSemCandidaturas);
            }
            case ATRIBUIR_PROPOSTA -> {
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }

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

                tableCandidatura.setVisible(false);

                tableAlunos = new TableView<Aluno>();
                nome = new TableColumn("Nome");
                nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
                nome.setMinWidth(100);

                nr_Aluno = new TableColumn("Numero");
                nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("nr_Aluno"));
                nr_Aluno.setMinWidth(85);

                email_Aluno = new TableColumn("Email");
                email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email_Aluno"));
                email_Aluno.setMinWidth(140);

                curso = new TableColumn("Curso");
                curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
                curso.setMinWidth(50);

                ramo_Aluno = new TableColumn("Ramo");
                ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ramo_Aluno"));
                ramo_Aluno.setMinWidth(50);

                classificacao_Aluno = new TableColumn("Classificacão");
                classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao_Aluno"));
                classificacao_Aluno.setMinWidth(50);

                aceder_a_Estagio = new TableColumn("Estagio");
                aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("aceder_a_Estagio"));
                aceder_a_Estagio.setMinWidth(50);
                tableAlunos.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);

                tableAlunos.setMaxWidth(750);
                tableAlunos.setMaxHeight(450);

                tableAlunos.setItems(manager.getCandidaturasNotReg());
                tableAlunos.setVisible(false);

                tableProposta = new TableView<Proposta>();
                id_Proposta = new TableColumn("Codigo");
                id_Proposta.setCellValueFactory(new PropertyValueFactory<Proposta, String>("cod_ID"));
                id_Proposta.setMinWidth(100);

                titulo = new TableColumn("Titulo");
                titulo.setCellValueFactory(new PropertyValueFactory<Docente, String>("titulo"));
                titulo.setMinWidth(100);

                codigo_Aluno = new TableColumn("Codigo Aluno");
                codigo_Aluno.setCellValueFactory(new PropertyValueFactory<Docente, String>("codigo_Aluno"));
                codigo_Aluno.setMinWidth(100);

                email_Docente = new TableColumn("Email Docente");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(100);

                empresa = new TableColumn("Empresa");
                empresa.setCellValueFactory(new PropertyValueFactory<Docente, String>("empresa"));
                empresa.setMinWidth(100);

                ramo = new TableColumn("Ramo");
                ramo.setCellValueFactory(new PropertyValueFactory<Docente, String>("ramo"));
                ramo.setMinWidth(100);

                tableProposta.getColumns().addAll(id_Proposta,titulo,codigo_Aluno,email_Docente,empresa,ramo);

                tableProposta.setMaxWidth(750);
                tableProposta.setMaxHeight(450);

                tableProposta.setVisible(false);

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

                hboxFilters = new HBox();
                hboxFilters.getChildren().addAll(btnMenuAluno,btnMenuProposta);
                hboxFilters.setAlignment(Pos.BOTTOM_CENTER);
                this.setTop(hboxFilters);

                itemAutoAssociadoProposta = new MenuItem("Auroproposta Associada");
                itemReg = new MenuItem("Candidatura Registada");
                itemPropostasAtri = new MenuItem("Proposta Atribuida");
                itemNotReg = new MenuItem("Sem Prosposta Associada");

                btnMenuAluno.getItems().addAll(itemAutoAssociadoProposta,itemReg,itemPropostasAtri,itemNotReg);

                itemAutoAssociadoAluno = new MenuItem("AutoPropostas de alunos");
                itemProDocentes = new MenuItem("Propostas de Docentes");
                itemPropostasDisp = new MenuItem("Propostas Disponiveis");
                itemPropostasAtribuidas = new MenuItem("Propostas Atribuidas");

                btnMenuProposta.getItems().addAll(itemAutoAssociadoAluno,itemProDocentes,itemPropostasDisp,itemPropostasAtribuidas);

                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }else {
                    btnDelete.setManaged(true);
                    btnAdd.setManaged(true);
                    btnDelete.setVisible(true);
                    btnAdd.setVisible(true);
                    btnDelete.setDisable(false);
                    btnAdd.setDisable(false);
                }
            }
            case ATRIBUIR_ORIENTADOR -> {
                tableAtribuicoesOri = new TableView<Atribuicao>();
                TableColumn<Atribuicao, String> nome_Docente = new TableColumn<Atribuicao, String>("Nome Docente");
                nome_Docente.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getNomeDocenteTV())
                );
                nome_Docente.setMinWidth(140);

                TableColumn <Atribuicao,String> email_Docente = new TableColumn<Atribuicao, String>("Email Docente");
                email_Docente.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getEmailDocenteTV()));
                email_Docente.setMinWidth(140);

                TableColumn <Atribuicao,String> id_Proposta = new TableColumn<Atribuicao, String>("Proposta");
                id_Proposta.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getProposta().getCod_ID()));
                id_Proposta.setMinWidth(140);

                TableColumn <Atribuicao,String> nr_Aluno = new TableColumn<Atribuicao, String>("Numero Aluno");
                nr_Aluno.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getAluno().getNr_AlunoString()));
                nr_Aluno.setMinWidth(140);

                tableAtribuicoesOri.getColumns().addAll(nome_Docente,email_Docente,nr_Aluno,id_Proposta);

                tableAtribuicoesOri.setMaxWidth(750);
                tableAtribuicoesOri.setMaxHeight(450);

                this.setCenter(tableAtribuicoesOri);
                tableAtribuicoesOri.setItems(manager.getAtribuicoes());

            }
            case CONSULTA -> {
                btnDelete.setManaged(false);
                btnDelete.setVisible(false);
                btnAdd.setManaged(false);
                btnAdd.setVisible(false);

                //tabela Alunos
                tableAlunosFinal = new TableView<Aluno>();
                nome = new TableColumn("Nome");
                nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome_Aluno"));
                nome.setCellFactory(TextFieldTableCell.forTableColumn());
                nome.setMinWidth(100);

                nr_Aluno = new TableColumn("Numero");
                nr_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("nr_Aluno"));
                nr_Aluno.setMinWidth(85);

                email_Aluno = new TableColumn("Email");
                email_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email_Aluno"));
                email_Aluno.setMinWidth(140);

                curso = new TableColumn("Curso");
                curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
                curso.setMinWidth(50);

                ramo_Aluno = new TableColumn("Ramo");
                ramo_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ramo_Aluno"));
                ramo_Aluno.setMinWidth(50);

                classificacao_Aluno = new TableColumn("Classificacão");
                classificacao_Aluno.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("classificacao_Aluno"));
                classificacao_Aluno.setMinWidth(50);

                aceder_a_Estagio = new TableColumn("Estagio");
                aceder_a_Estagio.setCellValueFactory(new PropertyValueFactory<Aluno, Boolean>("aceder_a_Estagio"));
                aceder_a_Estagio.setMinWidth(50);
                tableAlunosFinal.getColumns().addAll(nome,nr_Aluno,email_Aluno,curso,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);

                tableAlunosFinal.setMaxWidth(750);
                tableAlunosFinal.setMaxHeight(450);

                tableAlunosFinal.setItems(manager.getAlunos());
                this.setCenter(tableAlunosFinal);

                //tabela Docentes
                tableDocenteFinal = new TableView<Docente>();
                nomeDocente = new TableColumn("Nome");
                nomeDocente.setCellValueFactory(new PropertyValueFactory<Docente, String>("nome_Docente"));
                nomeDocente.setCellFactory(TextFieldTableCell.forTableColumn());
                nomeDocente.setMinWidth(100);

                papel_Docente = new TableColumn("Papel");
                papel_Docente.setCellValueFactory(new PropertyValueFactory<Docente, Boolean>("papel_Docente"));
                papel_Docente.setMinWidth(85);

                email_Docente = new TableColumn("Email");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(140);

                tableDocenteFinal.getColumns().addAll(nomeDocente,papel_Docente,email_Docente);

                tableDocenteFinal.setMaxWidth(750);
                tableDocenteFinal.setMaxHeight(450);

                tableDocenteFinal.setVisible(false);
                tableDocenteFinal.setManaged(false);

                //tabela Propostas
                tablePropostaFinal = new TableView<Proposta>();
                cod_ID = new TableColumn("Nome");
                cod_ID.setCellValueFactory(new PropertyValueFactory<Docente, String>("cod_ID"));
                cod_ID.setMinWidth(100);

                titulo = new TableColumn("Titulo");
                titulo.setCellValueFactory(new PropertyValueFactory<Docente, String>("titulo"));
                titulo.setMinWidth(100);

                codigo_Aluno = new TableColumn("Codigo Aluno");
                codigo_Aluno.setCellValueFactory(new PropertyValueFactory<Docente, String>("codigo_Aluno"));
                codigo_Aluno.setMinWidth(100);

                email_Docente = new TableColumn("Email Docente");
                email_Docente.setCellValueFactory(new PropertyValueFactory<Docente, String>("email_Docente"));
                email_Docente.setMinWidth(100);

                empresa = new TableColumn("Empresa");
                empresa.setCellValueFactory(new PropertyValueFactory<Docente, String>("empresa"));
                empresa.setMinWidth(100);

                ramo = new TableColumn("Ramo");
                ramo.setCellValueFactory(new PropertyValueFactory<Docente, String>("ramo"));
                ramo.setMinWidth(100);

                tablePropostaFinal.getColumns().addAll(cod_ID,titulo,codigo_Aluno,email_Docente,empresa,ramo);

                tablePropostaFinal.setMaxWidth(750);
                tablePropostaFinal.setMaxHeight(450);

                tablePropostaFinal.setVisible(false);
                tablePropostaFinal.setManaged(false);

                //tabela Candidaturas
                tableCandidaturaFinal = new TableView<Candidatura>();
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

                tableCandidaturaFinal.getColumns().addAll(numeroAluno,nomeAluno,cod_ID);

                tableCandidaturaFinal.setMaxWidth(750);
                tableCandidaturaFinal.setMaxHeight(450);

                tableCandidaturaFinal.setVisible(false);
                tableCandidaturaFinal.setManaged(false);

                //tabela Atribuicoes de Estagios/Projetos
                tableAtribuicoesFinal = new TableView<Atribuicao>();
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


                tableAtribuicoesFinal.getColumns().addAll(nome_Aluno,numero_Aluno,nome_Docente,id_Proposta);

                tableAtribuicoesFinal.setMaxWidth(750);
                tableAtribuicoesFinal.setMaxHeight(450);

                tableAtribuicoesFinal.setManaged(false);
                tableAtribuicoesFinal.setVisible(false);

                tbAluno = new ToggleButton("Alunos");
                tbDocente = new ToggleButton("Docentes");
                tbProposta = new ToggleButton("Propostas");
                tbCandidatura = new ToggleButton("Candidaturas");
                tbAtribuicoes = new ToggleButton("Atribuicoes");

                tgConsultaUI = new ToggleGroup();

                tbAluno.setToggleGroup(tgConsultaUI);
                tbDocente.setToggleGroup(tgConsultaUI);
                tbProposta.setToggleGroup(tgConsultaUI);
                tbCandidatura.setToggleGroup(tgConsultaUI);
                tbAtribuicoes.setToggleGroup(tgConsultaUI);

                hboxFilters = new HBox();
                hboxFilters.getChildren().addAll(tbAluno,tbDocente,tbProposta,tbCandidatura,tbAtribuicoes);
                tbAluno.setSelected(true);
                hboxFilters.setAlignment(Pos.BOTTOM_CENTER);
                this.setTop(hboxFilters);
            }
        }
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });

        if (manager.getState().equals(PoeState.OPCAO_CANDIDATURA)) {
            itemAutoPropostas.setOnAction(event -> {
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(false);
                tableProposta.setItems(manager.getPropostasAuto());
                tableProposta.setVisible(true);
                this.setCenter(tableProposta);
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(true);
                if (!manager.getFase_Candidatura()){
                    btnDelete.setManaged(true);
                    btnAdd.setManaged(true);
                    btnDelete.setDisable(true);
                    btnAdd.setDisable(true);
                }
            });

            itemProDocentes.setOnAction(event -> {
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(false);
                btnDelete.setDisable(false);
                btnAdd.setDisable(false);
                this.setCenter(tableProposta);
                tableProposta.setVisible(true);
                tableProposta.setItems(manager.getPropostasDoc());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(true);
                if (!manager.getFase_Candidatura()){
                    btnDelete.setManaged(true);
                    btnAdd.setManaged(true);
                    btnDelete.setDisable(true);
                    btnAdd.setDisable(true);
                }
            });

            itemProCandidaturas.setOnAction(event -> {
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(false);
                btnDelete.setDisable(false);
                btnAdd.setDisable(false);
                this.setCenter(tableProposta);
                tableProposta.setVisible(true);
                tableProposta.setItems(manager.getPropostasComCandidatura());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(true);
                if (!manager.getFase_Candidatura()){
                    btnDelete.setManaged(true);
                    btnAdd.setManaged(true);
                    btnDelete.setDisable(true);
                    btnAdd.setDisable(true);
                }
            });

            itemProSemCandidaturas.setOnAction(event -> {
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(false);
                btnDelete.setDisable(false);
                btnAdd.setDisable(false);
                this.setCenter(tableProposta);
                tableProposta.setVisible(true);
                tableProposta.setItems(manager.getPropostasSemCandidatura());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(true);
                if (!manager.getFase_Candidatura()){
                    btnDelete.setManaged(true);
                    btnAdd.setManaged(true);
                    btnDelete.setDisable(true);
                    btnAdd.setDisable(true);
                }
            });

            itemReg.setOnAction(e -> {
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableProposta.setVisible(false);
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(true);
                this.setCenter(tableCandidatura);
                tableCandidatura.getItems().clear();
                tableCandidatura.setItems(manager.getCandidaturas());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(true);
                tableProposta.setManaged(false);
                if (!manager.getFase_Candidatura()){
                    btnDelete.setManaged(true);
                    btnAdd.setManaged(true);
                    btnDelete.setDisable(false);
                    btnAdd.setDisable(false);
                }
            });

            itemAuto.setOnAction(e -> {
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableProposta.setVisible(false);
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(true);
                this.setCenter(tableCandidatura);
                tableCandidatura.setItems(manager.getCandidaturasAuto());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(true);
                tableProposta.setManaged(false);
                if (!manager.getFase_Candidatura()){
                    btnDelete.setManaged(true);
                    btnAdd.setManaged(true);
                    btnDelete.setDisable(true);
                    btnAdd.setDisable(true);
                }
            });

            itemNotReg.setOnAction(e -> {
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableProposta.setVisible(false);
                tableCandidatura.setVisible(false);
                this.setCenter(tableAlunos);
                tableAlunos.setVisible(true);
                tableAlunos.setItems(manager.getCandidaturasNotReg());
                tableAlunos.setManaged(true);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(false);
                if (!manager.getFase_Candidatura()){
                    btnDelete.setManaged(true);
                    btnAdd.setManaged(true);
                    btnDelete.setDisable(true);
                    btnAdd.setDisable(true);
                }
            });
        }

        if (manager.getState().equals(PoeState.ATRIBUIR_PROPOSTA)){
            itemAutoAssociadoProposta.setOnAction(event -> {
                tableAtribuicoesPro.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAlunos.setVisible(true);
                tableCandidatura.setVisible(false);
                tableAtribuicoesPro.setVisible(false);
                tableAlunos.setItems(manager.toStringAutopropostasTV());
                tableProposta.setVisible(false);
                this.setCenter(tableAlunos);
                tableAlunos.setManaged(true);
                tableCandidatura.setManaged(false);
                tableAtribuicoesPro.setManaged(false);
                tableProposta.setManaged(false);
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }});
            itemReg.setOnAction(event -> {
                tableAtribuicoesPro.getItems().clear();
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAtribuicoesPro.setVisible(false);
                tableProposta.setVisible(false);
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(true);
                this.setCenter(tableCandidatura);
                tableCandidatura.setItems(manager.getCandidaturas());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(true);
                tableProposta.setManaged(false);
                tableAtribuicoesPro.setVisible(false);
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }
            });
            itemPropostasAtri.setOnAction(event -> {
                tableAtribuicoesPro.getItems().clear();
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAtribuicoesPro.setVisible(false);
                tableProposta.setVisible(false);
                tableAlunos.setVisible(true);
                tableCandidatura.setVisible(false);
                this.setCenter(tableAlunos);
                tableAlunos.setItems(manager.getAlunosPropostaAtribuida());
                tableAlunos.setManaged(true);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(false);
                tableAtribuicoesPro.setVisible(false);
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }
            });

            itemNotReg.setOnAction(event -> {
                tableAtribuicoesPro.getItems().clear();
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAtribuicoesPro.setVisible(false);
                tableProposta.setVisible(false);
                tableAlunos.setVisible(true);
                tableCandidatura.setVisible(false);
                this.setCenter(tableAlunos);
                tableAlunos.setItems(manager.getAlunosSemProposta());
                tableAlunos.setManaged(true);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(false);
                tableAtribuicoesPro.setVisible(false);
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }
            });

            itemAutoAssociadoAluno.setOnAction(event -> {
                tableAtribuicoesPro.getItems().clear();
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAtribuicoesPro.setVisible(false);
                tableProposta.setVisible(true);
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(false);
                this.setCenter(tableProposta);
                tableProposta.setItems(manager.getAutopropostasAlunos());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(true);
                tableAtribuicoesPro.setVisible(false);
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }
            });

            itemProDocentes.setOnAction(event -> {
                tableAtribuicoesPro.getItems().clear();
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAtribuicoesPro.setVisible(false);
                tableProposta.setVisible(true);
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(false);
                this.setCenter(tableProposta);
                tableProposta.setItems(manager.getPropostasDoc());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(true);
                tableAtribuicoesPro.setVisible(false);
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }
            });
            itemPropostasDisp.setOnAction(event -> {
                tableAtribuicoesPro.getItems().clear();
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAtribuicoesPro.setVisible(false);
                tableProposta.setVisible(true);
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(false);
                this.setCenter(tableProposta);
                tableProposta.setItems(manager.getPropostasDisponiveis());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(true);
                tableAtribuicoesPro.setVisible(false);
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }
            });

            itemPropostasAtribuidas.setOnAction(event -> {
                tableAtribuicoesPro.getItems().clear();
                tableCandidatura.getItems().clear();
                tableProposta.getItems().clear();
                tableAlunos.getItems().clear();

                tableAtribuicoesPro.setVisible(true);
                tableProposta.setVisible(false);
                tableAlunos.setVisible(false);
                tableCandidatura.setVisible(false);
                this.setCenter(tableAtribuicoesPro);
                tableAtribuicoesPro.setItems(manager.getAtribuicoes());
                tableAlunos.setManaged(false);
                tableCandidatura.setManaged(false);
                tableProposta.setManaged(false);
                tableAtribuicoesPro.setVisible(true);
                if (manager.getFase_Proposta()){
                    btnDelete.setManaged(false);
                    btnAdd.setManaged(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(false);
                }
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
                    Popup.addCandidatura(manager);
                }
            }
            case ATRIBUIR_PROPOSTA -> {
                Popup.addAtribuicaoPro(manager);
            }
            case ATRIBUIR_ORIENTADOR -> {
                Popup.addAtribuicaoDoc(manager);
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
                    if (tableCandidatura !=null && tableCandidatura.isVisible() == true) {
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

        if (manager.getState().equals(PoeState.CONSULTA)){

            tbAluno.setOnAction(event -> {
                tableAlunosFinal.setVisible(true);
                tableAlunosFinal.setManaged(true);
                tableAlunosFinal.setItems(manager.getAlunos());
                this.setCenter(tableAlunosFinal);
            });

            tbDocente.setOnAction(event -> {
                tableDocenteFinal.setVisible(true);
                tableDocenteFinal.setManaged(true);
                tableDocenteFinal.setItems(manager.getDocentes());
                this.setCenter(tableDocenteFinal);
            });

            tbProposta.setOnAction(event -> {
                tablePropostaFinal.setVisible(true);
                tablePropostaFinal.setManaged(true);
                tablePropostaFinal.setItems(manager.getPropostas());
                this.setCenter(tablePropostaFinal);
            });

            tbCandidatura.setOnAction(event -> {
                tableCandidaturaFinal.setVisible(true);
                tableCandidaturaFinal.setManaged(true);
                tableCandidaturaFinal.setItems(manager.getCandidaturas());
                this.setCenter(tableCandidaturaFinal);
            });

            tbAtribuicoes.setOnAction(event -> {
                tableAtribuicoesFinal.setVisible(true);
                tableAtribuicoesFinal.setManaged(true);
                tableAtribuicoesFinal.setItems(manager.getAtribuicoes());
                this.setCenter(tableAtribuicoesFinal);
            });

        }

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
        }
    }
}

