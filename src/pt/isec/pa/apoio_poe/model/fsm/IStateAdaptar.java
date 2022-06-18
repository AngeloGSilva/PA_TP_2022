package pt.isec.pa.apoio_poe.model.fsm;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;

abstract class IStateAdaptar implements IState{
    protected GestaoProj dados;
    protected ProContexto contexto;

    protected IStateAdaptar(GestaoProj dados, ProContexto contexto) {
        this.dados = dados;
        this.contexto = contexto;
    }

    protected void alteraState(IState novoState){
        contexto.alterarState(novoState);
    }

    @Override
    public boolean voltar(boolean guardado) {
        return false;
    }

    @Override
    public void resolverConflito(int selecionado){}
    @Override
    public boolean avancar(boolean guardado) {
        return false;
    }

    @Override
    public boolean selecionar(PoeState escolha) {
        return false;
    }

    @Override
    public boolean AtribuirAutomaticamente(){return false;};

    @Override
    public int lerFicheiro(String fileName){
        return 0;
    }



    @Override
    public boolean lerFicheiroDebug(String fileName){
        return false;
    }


    @Override
    public boolean adicionarAluno(String nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio, String curso){
        return false;
    }

    @Override
    public boolean removerPropostaDeCandidatura(String nr_Aluno, String eliminar){
        return false;
    }

    @Override
    public boolean adicionarPropostaACandidatura(String nr_Aluno, String eliminar){return false;}

    @Override
    public boolean remover(String eliminar){
        return false;
    }

    public void editar(String identificador,String edicao){};
/*
    @Override
    public boolean removerAluno(long nr_aluno){
        return false;
    }
    @Override
    public boolean removerDocente(String emaildoc){ return false;}
    @Override
    public boolean removerProposta(String cod_ID){return false;}*/

    @Override
    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente){return false;}

    @Override
    public boolean adicionarProposta(String tipo,String cod_ID, String titulo, Long codigo_Aluno, String email_Docente, String ramo,String empresa){return false;}

    @Override
    public boolean adicionarCandidatura(String nrAluno,String codId){return false;}

    @Override
    public void atribuirDocentesauto() {}

    @Override
    public boolean atribuirManualmenteDocente(String docente, int id_atribuicao){return false;}

    @Override
    public void AtribuirAutomaticoAutopropostosDocentesAluno(){}

    @Override
    public boolean atribuirManualmenteAluno(long id_aluno, String proposta){return false;}

}
