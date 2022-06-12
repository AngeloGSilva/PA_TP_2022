package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;


public class configuracaoUI extends BorderPane{
    ProgManager manager;
    Button btnAlunos,btnDocentes,btnProjetos,btnExit;

    public configuracaoUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnAlunos = new Button("Alunos");
        btnAlunos.setMinWidth(100);
        btnDocentes = new Button("Docentes");
        btnDocentes.setMinWidth(100);
        btnProjetos = new Button("Projetos");
        btnProjetos.setMinWidth(100);
        btnExit  = new Button("Exit");
        btnExit.setMinWidth(100);
        btnAlunos.setId("allbtn");
        btnDocentes.setId("allbtn");
        btnProjetos.setId("allbtn");
        VBox hBox = new VBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        //hBox.getChildren().addAll();
        hBox.getChildren().addAll(btnAlunos,btnDocentes,btnProjetos,btnExit);
        //hBox.getChildren().add(btnAlunos);
        //hBox.getChildren().add(btnDocentes);
        //hBox.getChildren().add(btnProjetos);
        //this.getChildren().addAll(hBox);
        this.setCenter(hBox);

        //hBox.getChildren().addAll(btnAlunos,btnDocentes,btnProjetos,btnExit);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        btnAlunos.setOnAction(event -> {
            manager.selecionar(PoeState.GESTAO_ALUNO);
        });

        btnDocentes.setOnAction(event -> {
            manager.selecionar(PoeState.GESTAO_DOCENTE);
        });

        btnProjetos.setOnAction(event -> {
            manager.selecionar(PoeState.GESTAO_PROPOSTA);
        });

        btnExit.setOnAction( event -> {
            Platform.exit();
        });
    }

    private void update() {
        if (manager.getState() != PoeState.CONFIGURACAO){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

}
