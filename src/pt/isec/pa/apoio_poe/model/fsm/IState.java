package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public interface IState {
    //redo e undo?
    boolean voltar(boolean guardado);
    boolean avancar(boolean guardado,int op);
    boolean selecionar(int escolha);
    boolean adicionarAluno(Aluno aluno);
    boolean adicionarDocente(Docente docente);
    boolean adicionarProposta(Proposta proposta);

    PoeState getState();


    boolean removerAluno(long nr_aluno);

}
