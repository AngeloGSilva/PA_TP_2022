package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;
import pt.isec.pa.apoio_poe.model.data.Proposta;

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
    public boolean lerFicheiro(String fileName){
        return false;
    }

    @Override
    public boolean lerFicheiroDebug(String fileName){
        return false;
    }


    @Override
    public boolean adicionarAluno(long nr_Aluno,String nome_Aluno,String email_Aluno,String ramo_Aluno,double classificacao_Aluno,boolean aceder_a_Estagio) {
        return false;
    }

    @Override
    public boolean removerAluno(long nr_aluno){
        return false;
    }

    @Override
    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente){return false;}

    @Override
    public boolean adicionarProposta(Proposta proposta){return false;}

}
