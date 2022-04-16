package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;

public interface IState {
    //redo e undo?
    boolean voltar(boolean guardado);
    boolean avancar(boolean guardado,int op);
    boolean selecionar(int escolha);
    void adicionarAluno(Aluno aluno);


    PoeState getState();


}
