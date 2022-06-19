package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.ProgManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.FontManager;

public class RootPane extends BorderPane {
    ProgManager manager;

    public RootPane(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        FontManager.loadFont("opensans.ttf",24);
        CSSManager.applyCSS(this,"teste.css");
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
            case ATRIBUIR_ORIENTADOR -> this.setCenter(new atriOrientadorUI(manager));
            case CONSULTA -> this.setCenter(new consultaUI(manager));
        }
    }
}