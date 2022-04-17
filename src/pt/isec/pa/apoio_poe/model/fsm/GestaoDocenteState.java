package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
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
        alteraState(new opCandidaturaState(dados, contexto));
        return false;
    }

    @Override
    public boolean adicionarDocente(Docente docente){
        if(dados.adicinarDocentes(docente)){
            alteraState(new GestaoDocenteState(dados,contexto));
        }else{
            alteraState(new GestaoDocenteState(dados,contexto));
            return false;
        }
        return true;
    }
/*

    @Override
    public boolean removerDocente(long nr_aluno){
        return dados.removerAlunos(nr_aluno);
    }
*/


    @Override
    public boolean selecionar(int escolha) {
        return false;
    }


    @Override
    public PoeState getState() {
        return PoeState.GESTAO_DOCENTE;
    }
}
