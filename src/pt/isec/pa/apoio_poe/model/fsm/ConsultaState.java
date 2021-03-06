package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class ConsultaState extends IStateAdapter {
    public ConsultaState(GestaoProj dados, ProContexto contexto) {
        super(dados, contexto);
    }

    @Override
    public PoeState getState() {
        return PoeState.CONSULTA;
    }
}
