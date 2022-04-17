package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class GestaoAlunoState extends IStateAdaptar {
    public GestaoAlunoState(GestaoProj dados, ProContexto contexto) {
        super(dados,contexto);
        //inicio
    }

    @Override
    public boolean adicionarAluno(Aluno aluno){
        if(dados.adicinarAlunos(aluno)){
           alteraState(new GestaoAlunoState(dados,contexto));
        }else{
            alteraState(new GestaoAlunoState(dados,contexto));
            return false;
        }
        return true;
    }

    @Override
    public boolean removerAluno(long nr_aluno){
        return dados.removerAlunos(nr_aluno);
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
    public boolean selecionar(int escolha) {
        return false;
    }

    @Override
    public PoeState getState() {
        return PoeState.GESTAO_ALUNO;
    }
}
