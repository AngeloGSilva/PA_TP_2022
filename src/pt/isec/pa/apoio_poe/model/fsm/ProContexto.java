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

    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente) {
        return state.adicionarDocente(nome_Docente, email_Docente, papel_Docente);
    }

    public boolean adicionarProposta(Proposta proposta) {
        return state.adicionarProposta(proposta);
    }

    public boolean adicionarAluno(long nr_Aluno,String nome_Aluno,String email_Aluno,String ramo_Aluno,double classificacao_Aluno,boolean aceder_a_Estagio){
        return state.adicionarAluno(nr_Aluno,nome_Aluno,email_Aluno,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);
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
