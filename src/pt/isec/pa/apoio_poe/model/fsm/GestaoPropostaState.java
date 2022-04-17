package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public class GestaoPropostaState extends IStateAdaptar {
    public GestaoPropostaState(GestaoProj dados, ProContexto contexto) {
        super(dados,contexto);
    }

    @Override
    public boolean voltar(boolean guardado) {
        return false;
    }

    @Override
    public boolean avancar(boolean guardado, int op) {
        alteraState(new opCandidaturaState(dados, contexto));
        return false;
    }

    @Override
    public boolean adicionarProposta(Proposta proposta){
        if(dados.adicinarProsta(proposta)){
            alteraState(new GestaoPropostaState(dados,contexto));
        }else{
            alteraState(new GestaoPropostaState(dados,contexto));
            return false;
        }
        return true;
    }

    @Override
    public boolean selecionar(int escolha) {
        return false;
    }



    @Override
    public PoeState getState() {
        return PoeState.GESTAO_PROPOSTA;
    }
}
