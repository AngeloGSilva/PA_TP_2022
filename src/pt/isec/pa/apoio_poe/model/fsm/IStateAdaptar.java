package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;

abstract class IStateAdaptar implements IState{
    protected GestaoProj dados;
    protected ProContexto contexto;

    protected IStateAdaptar(GestaoProj dados, ProContexto contexto) {
        this.dados = dados;
        this.contexto = contexto;
    }

    protected void alteraState(IState novoState){
        contexto.alterarState(novoState);
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
        return false;

    }
    @Override
    public boolean adicionarAluno(Aluno aluno) {
        return false;
    }

}
