package pt.isec.pa.apoio_poe.model.fsm;

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
    public boolean avancar(boolean guardado, int op) {
        return false;
    }


    @Override
    public boolean selecionar(int escolha) {
        switch (escolha) {
            case 1 -> alteraState(new GestaoAlunoState(dados, contexto));
            case 2 -> alteraState(new GestaoDocenteState(dados, contexto));
            case 3 -> alteraState(new GestaoPropostaState(dados, contexto));
        }
        return false;

    }

    @Override
    public PoeState getState() {
        return null;
    }
}
