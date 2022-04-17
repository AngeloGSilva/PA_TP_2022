package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;

public interface IState {
    //redo e undo?
    boolean voltar(boolean guardado);
    boolean avancar(boolean guardado,int op);
    boolean selecionar(int escolha);
    boolean adicionarAluno(Aluno aluno);
    boolean adicionarDocente(Docente docente);


    PoeState getState();


    boolean removerAluno(long nr_aluno);
}
