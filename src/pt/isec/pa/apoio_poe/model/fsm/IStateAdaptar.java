package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

abstract class IStateAdaptar implements IState{
    protected GestaoProj dados;
    protected ProContexto contexto;

    public IStateAdaptar(GestaoProj dados, ProContexto contexto) {
        this.dados = dados;
        this.contexto = contexto;
    }
}
