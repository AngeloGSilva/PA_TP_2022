package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class GestaoAlunoState extends IStateAdaptar {
    public GestaoAlunoState(GestaoProj dados, ProContexto contexto) {
        super(dados,contexto);
        //inicio
    }

    @Override
    public boolean voltar(boolean guardado) {
        return false;
    }

    @Override
    public boolean avancar(boolean guardado, int op) {
        return false;
    }

    @Override
    public boolean selecionar() {
        return false;
    }

    @Override
    public ProState getState() {
        return null;
    }
}
