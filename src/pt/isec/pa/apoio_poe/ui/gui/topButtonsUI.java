package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.io.File;

public class topButtonsUI extends VBox{

        ProgManager manager;
        Button btnExportar, btnLerFich,btnAvancar,btnVoltar,btnAutoDoc,btnAtriAuto,btnManual,btnAtriDoc,btnExit,btnGraficos,btnTabelas;
        Label info;

        ImageView imageView;
        HBox hbox;


        Tooltip tooltip;

        //Variavel de auxilio mostrar info
        int aux;


        public topButtonsUI(ProgManager manager) {
            this.manager = manager;

            createViews();
            registerHandlers();
            update();
        }

        private void createViews() {
            info = new Label();
            switch (manager.getState()){
                case GESTAO_ALUNO -> info.setText("Alunos");
                case ATRIBUIR_ORIENTADOR -> info.setText("Atribuir Orientadores");
                case ATRIBUIR_PROPOSTA ->  info.setText("Atribuir Propostas");
                case CONFIGURACAO -> info.setText("Configuracao");
                case OPCAO_CANDIDATURA -> info.setText("Candidaturas");
                case GESTAO_DOCENTE -> info.setText("Docentes");
                case GESTAO_PROPOSTA -> info.setText("Propostas");
            }
            hbox = new HBox();
            info.setFont(new Font(30));
            info.setPadding(new Insets(10));
            info.setAlignment(Pos.TOP_CENTER);
            //this.setPadding(new Insets(30));
            btnAvancar = new Button();
            imageView = new ImageView(ImageManager.getImage("avance.png"));
            btnAvancar.setContentDisplay(ContentDisplay.RIGHT);
            btnAvancar.setGraphic(imageView);
            btnAvancar.setText("Avancar");

            btnVoltar = new Button();
            imageView = new ImageView(ImageManager.getImage("back.png"));
            btnVoltar.setGraphic(imageView);
            btnVoltar.setText("Voltar");

            btnExportar = new Button();
            imageView = new ImageView(ImageManager.getImage("upload.png"));
            btnExportar.setGraphic(imageView);
            btnExportar.setText("Exportar");

            switch (manager.getState()){
                case ATRIBUIR_PROPOSTA -> {
                    btnAutoDoc = new Button();
                    imageView = new ImageView(ImageManager.getImage("assign.png"));
                    btnAutoDoc.setGraphic(imageView);
                    btnAutoDoc.setText("Autopropostos");

                    btnAtriAuto = new Button();
                    imageView = new ImageView(ImageManager.getImage("assign.png"));
                    btnAtriAuto.setGraphic(imageView);
                    btnAtriAuto.setText("Automaticamente");


                    btnAutoDoc.setVisible(true);
                    btnAutoDoc.setManaged(true);
                    btnAtriAuto.setManaged(true);
                    btnAtriAuto.setVisible(true);

                    hbox.getChildren().addAll(btnVoltar, btnAutoDoc, btnAtriAuto,btnExportar, btnAvancar);
                    hbox.setAlignment(Pos.CENTER);
                    hbox.setSpacing(10);


                    if (manager.getFase_Proposta()){
                        btnAutoDoc.setVisible(false);
                        btnAutoDoc.setManaged(false);
                        btnAtriAuto.setManaged(false);
                        btnAtriAuto.setVisible(false);
                    }
                }
                case ATRIBUIR_ORIENTADOR -> {
                    btnAtriDoc = new Button();
                    imageView = new ImageView(ImageManager.getImage("assign.png"));
                    btnAtriDoc.setContentDisplay(ContentDisplay.RIGHT);
                    btnAtriDoc.setGraphic(imageView);
                    btnAtriDoc.setText("Automaticamente");

                    hbox.getChildren().addAll(btnVoltar, btnAtriDoc,btnExportar, btnAvancar);
                    hbox.setAlignment(Pos.CENTER);
                    hbox.setSpacing(10);

                }case CONFIGURACAO -> {
                    btnExit = new Button();
                    imageView = new ImageView(ImageManager.getImage("sair.png"));
                    btnExit.setContentDisplay(ContentDisplay.LEFT);
                    btnExit.setGraphic(imageView);
                    btnExit.setText("Sair");

                    hbox.getChildren().addAll(btnExit,btnAvancar);
                    hbox.setAlignment(Pos.CENTER);
                    hbox.setSpacing(10);
                }
                default -> {
                    btnLerFich = new Button();
                    imageView = new ImageView(ImageManager.getImage("download.png"));
                    btnLerFich.setGraphic(imageView);
                    btnLerFich.setText("Importar");

                    hbox.getChildren().addAll(btnVoltar, btnLerFich, btnExportar, btnAvancar);
                    hbox.setAlignment(Pos.TOP_CENTER);
                    hbox.setSpacing(10);
                    btnLerFich.setManaged(true);
                    btnLerFich.setVisible(true);
                    if (manager.getFase_Candidatura()){
                        btnLerFich.setManaged(false);
                        btnLerFich.setVisible(false);
                    }
                }
            }

            topBar topBar = new topBar(manager);

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().add(topBar);

            AnchorPane.setTopAnchor(anchorPane,0.0);
            AnchorPane.setLeftAnchor(anchorPane,0.0);
            AnchorPane.setRightAnchor(anchorPane,0.0);
            AnchorPane.setBottomAnchor(anchorPane,0.0);

            this.setAlignment(Pos.TOP_CENTER);
            this.getChildren().add(anchorPane);
            this.getChildren().add(info);
            this.getChildren().add(hbox);

        }

        private void registerHandlers() {
            manager.addPropertyChangeListener(evt -> { update(); });

            if (manager.getState() == PoeState.ATRIBUIR_PROPOSTA){
                btnAutoDoc.setOnAction(event ->{
                    manager.AtribuirAutomaticoAutopropostosDocentesAluno();
                });

                btnAtriAuto.setOnAction(event ->{
                    if(!manager.AtribuirAutomaticamente()){
                        Popup.conflito(manager);
                    }
                });
            }else if (manager.getState() == PoeState.ATRIBUIR_ORIENTADOR){
                btnAtriDoc.setOnAction(event -> {
                    manager.atribuirDocentesauto();
                });
            }else if(manager.getState() == PoeState.CONFIGURACAO){

            }else {
                btnExportar.setOnAction(event ->{
                    Popup.exportar(manager);
                });

                btnLerFich.setOnAction(event ->{
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("File open...");
                    fileChooser.setInitialDirectory(new File("..."));
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("CSV (*.csv)","*.csv"),
                            new FileChooser.ExtensionFilter("All files","*.*")
                    );
                    File file = fileChooser.showOpenDialog(this.getScene().getWindow());
                    System.out.println(file.getAbsolutePath());
                    aux = manager.lerFicheiro(file.getAbsolutePath());
                    Popup.display(PopupSupport.POPUP_LERFICH,aux);
                });
            }

            btnAvancar.setOnAction(event ->{
                switch (manager.getState()) {
                    case CONFIGURACAO -> {
                        if (!manager.getFase_gestao())
                            Popup.avancarFase(manager);
                        else
                            manager.avancar(manager.getFase_gestao());
                    }case OPCAO_CANDIDATURA -> {
                        if (!manager.getFase_Candidatura())
                            Popup.avancarFase(manager);
                        else
                            manager.avancar(manager.getFase_Candidatura());
                    }case ATRIBUIR_PROPOSTA -> {
                        if (!manager.getFase_Proposta())
                            Popup.avancarFase(manager);
                        else
                            manager.avancar(manager.getFase_Proposta());
                    }case ATRIBUIR_ORIENTADOR -> {
                        manager.avancar(manager.getFase_Orientador());
                    }
                    default -> Popup.avancarFase(manager);

                }
            });

            btnVoltar.setOnAction(event ->{
                manager.voltar(false);
            });
        }

        private void update() {
        }



}
