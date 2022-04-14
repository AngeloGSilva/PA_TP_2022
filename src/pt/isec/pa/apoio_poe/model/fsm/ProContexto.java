package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class ProContexto {
    private IState state;
    private GestaoProj dados;

    public ProContexto() {
        dados = new GestaoProj();
        state = new ConfiguracaoState(dados,this);
    }

    public ProState getState(){
        //return state.getState();
        return null;
    }


    //conjunto de metodos correspondentes as transiçoes de estados ... IState


    //metodos para aceder a dados..
    //public getNr_alunos_Inscritos(){return dados.getAlunos();}
}
