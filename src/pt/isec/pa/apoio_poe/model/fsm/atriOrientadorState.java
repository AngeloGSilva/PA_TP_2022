package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public class atriOrientadorState extends IStateAdaptar {
    public atriOrientadorState(GestaoProj dados, ProContexto contexto) {
        super(dados, contexto);
    }

    @Override
    public boolean voltar(boolean guardado) {
        alteraState(new atriPropostaState(dados,contexto));
        return false;
    }

    @Override
    public boolean avancar(boolean guardado, int op) {
        if(guardado){
            dados.setFase_Fechada_atriOrientador(guardado);
            alteraState(new ConsultaState(dados, contexto));
        }else
            alteraState(new ConsultaState(dados, contexto));
        return false;
    }

    @Override
    public boolean selecionar(int escolha) {
        return false;
    }


    @Override
    public PoeState getState() {
        return PoeState.ATRIBUIR_ORIENTADOR;
    }
}
