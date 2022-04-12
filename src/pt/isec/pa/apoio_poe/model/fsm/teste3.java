package pt.isec.pa.apoio_poe.model.fsm;

public interface IState {
    boolean voltar(boolean guardado);
    boolean avancar(boolean guardado,int op);
    boolean selecionar();
}
