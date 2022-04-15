package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class GestaoDocenteState extends IStateAdaptar {
    public GestaoDocenteState(GestaoProj dados, ProContexto contexto) {
        super(dados,contexto);
    }

    @Override
    public boolean voltar(boolean guardado) {
        return false;
    }

    @Override
    public boolean avancar(boolean guardado, int op) {
        alterarState(new opCandidatura(dados, contexto));
        return false;
    }

    @Override
    public boolean selecionar(int escolha) {
        return false;
    }

    @Override
    public ProState getState() {
        return null;
    }
}
