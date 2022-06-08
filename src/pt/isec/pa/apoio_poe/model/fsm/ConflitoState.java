package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class ConflitoState extends IStateAdaptar {
    public ConflitoState(GestaoProj dados, ProContexto contexto) {
        super(dados, contexto);
    }

    @Override
    public PoeState getState() {
        return PoeState.CONFLITO;
    }
}
