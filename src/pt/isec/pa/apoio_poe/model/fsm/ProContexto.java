package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;
import pt.isec.pa.apoio_poe.model.data.Proposta;

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


    public boolean lerFicheiro(String fileName){
        return state.lerFicheiro(fileName);
    }

    public boolean adicionarDocente(Docente docente) {
        return state.adicionarDocente(docente);
    }

    public boolean adicionarProposta(Proposta proposta) {
        return state.adicionarProposta(proposta);
    }

    public boolean adicionarAluno(Aluno aluno){
        return state.adicionarAluno(aluno);
    }

    public boolean removerAluno(long nr_aluno){
        return state.removerAluno(nr_aluno);
    }
    


    //metodos para aceder a dados..
    //public getNr_alunos_Inscritos(){return dados.getAlunos();}

    public String getAlunos(){
        return dados.toStringAlunos();
    }

    public String getDocentes(){
        return dados.toStringDocentes();
    }



    public String getPropostas() {
        return dados.toStringPropostas();
    }
}
