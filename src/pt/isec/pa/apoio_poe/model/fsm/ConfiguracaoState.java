package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;


public class ConfiguracaoState extends IStateAdaptar {
    protected ConfiguracaoState(GestaoProj dados, ProContexto contexto) {
        super(dados ,contexto);
        //comecar
    }

    @Override
    public boolean voltar(boolean guardado) {
        return false;
    }

    @Override
    public boolean avancar(boolean guardado) {
        alteraState(new opCandidaturaState(dados, contexto));
        return false;
    }

    @Override
    public boolean selecionar(PoeState escolha) {
        switch (escolha) {
            case GESTAO_ALUNO -> alteraState(new GestaoAlunoState(dados, contexto));
            case GESTAO_DOCENTE -> alteraState(new GestaoDocenteState(dados, contexto));
            case GESTAO_PROPOSTA -> alteraState(new GestaoPropostaState(dados, contexto));
        }
        return false;
    }

    @Override
    public PoeState getState() {
        return PoeState.CONFIGURACAO;
    }
}
