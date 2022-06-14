package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;

import java.awt.*;

import static pt.isec.pa.apoio_poe.model.fsm.PoeState.GESTAO_ALUNO;

public class RootPane extends BorderPane {
    ProgManager manager;

    public RootPane(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
/*        StackPane stackPane;
        stackPane = new StackPane(
                new configuracaoUI(manager)
        );*/
        //stackPane.setBackground(Color.BLACK);
        //this.setCenter(stackPane);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
    }

    private void update() {
        switch (manager.getState()) {
            case CONFIGURACAO -> this.setCenter(new configuracaoUI(manager));
            case GESTAO_ALUNO -> this.setCenter(new gestaoAlunosUI(manager));
            case GESTAO_DOCENTE -> this.setCenter(new gestaoDocenteUI(manager));
            case GESTAO_PROPOSTA -> this.setCenter(new gestaoPropostaUI(manager));
            case OPCAO_CANDIDATURA -> this.setCenter(new opCandidaturaUI(manager));
            case ATRIBUIR_PROPOSTA -> this.setCenter(new atriPropostaUI(manager));

        /*if (estado == a waitBet)
             new WaitBetUI(gameBWManager) para nao ser tao pesado para nao carregar td lg no inicio
         */
        }
    }
}