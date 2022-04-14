package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;


public class ConfiguracaoState extends IStateAdaptar {
    protected ConfiguracaoState(GestaoProj dados, ProContexto proContexto) {
        super(dados ,proContexto);
        //comecar
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
