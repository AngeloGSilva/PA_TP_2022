package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class ProContexto {
    private GestaoProj dados;
    private IState state;

    public ProContexto() {
        dados = new GestaoProj();
        state = new ConfiguracaoState(dados,this);
    }

    public PoeState getState(){
        return state.getState();
    }

    public void alterarState(IState novoState){
        state = novoState;
    }


    //conjunto de metodos correspondentes as transiçoes de estados ... IState
    public boolean selecionar(int escolha){
        return state.selecionar(escolha);
    }

    public boolean voltar(boolean guardado) {
        return state.voltar(guardado);
    }

    public boolean avancar(boolean guardado, int op) {
        return state.avancar(guardado,op);
    }

    //metodos para aceder a dados..
    //public getNr_alunos_Inscritos(){return dados.getAlunos();}
}
