package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;


public class configuracaoUI extends BorderPane{
    ProgManager manager;
    Button btnAlunos,btnDocentes,btnProjetos,btnExit,btnAvancar;

    public configuracaoUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setPadding(new Insets(30));
        if (!manager.getFase_gestao()) {
            btnAlunos = new Button("Alunos");
            btnAlunos.setMinWidth(100);
            btnDocentes = new Button("Docentes");
            btnDocentes.setMinWidth(100);
            btnProjetos = new Button("Projetos");
            btnProjetos.setMinWidth(100);
            btnExit = new Button("Exit");
            btnExit.setMinWidth(100);
            btnAlunos.setId("allbtn");
            btnDocentes.setId("allbtn");
            btnProjetos.setId("allbtn");
            VBox hBox = new VBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
            //hBox.getChildren().addAll();
            hBox.getChildren().addAll(btnAlunos, btnDocentes, btnProjetos, btnExit);
            //hBox.getChildren().add(btnAlunos);
            //hBox.getChildren().add(btnDocentes);
            //hBox.getChildren().add(btnProjetos);
            //this.getChildren().addAll(hBox);
            this.setCenter(hBox);
        }else {
            this.setTop(new topButtonsUI(manager));
            this.setCenter(new tableViewsUI(manager));
        }
    }

    private void registerHandlers() {
        if (!manager.getFase_gestao()) {
            manager.addPropertyChangeListener(evt -> {
                update();
            });
            btnAlunos.setOnAction(event -> {
                manager.selecionar(PoeState.GESTAO_ALUNO);
            });

            btnDocentes.setOnAction(event -> {
                manager.selecionar(PoeState.GESTAO_DOCENTE);
            });

            btnProjetos.setOnAction(event -> {
                manager.selecionar(PoeState.GESTAO_PROPOSTA);
            });
        }
    }

    private void update() {
/*        if (manager.getState() != PoeState.CONFIGURACAO){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);*/
    }

}
