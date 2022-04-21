package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public class GestaoAlunoState extends IStateAdaptar {
    public GestaoAlunoState(GestaoProj dados, ProContexto contexto) {
        super(dados,contexto);
        //inicio
    }

    @Override
    public boolean lerFicheiro(String fileName){
        if(dados.lerficheiroAluno(fileName)){
            alteraState(new GestaoAlunoState(dados,contexto));
            return true;
        }else {
            alteraState(new GestaoAlunoState(dados, contexto));
            return false;
        }
    }

    @Override
    public boolean adicionarAluno(long nr_Aluno,String nome_Aluno,String email_Aluno,String ramo_Aluno,double classificacao_Aluno,boolean aceder_a_Estagio){
        Aluno aluno = new Aluno(nr_Aluno,nome_Aluno,email_Aluno,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);
        if(dados.adicinarAlunos(aluno)){
           alteraState(new GestaoAlunoState(dados,contexto));
        }else{
            alteraState(new GestaoAlunoState(dados,contexto));
            return false;
        }
        return true;
    }

    @Override
    public boolean removerAluno(long nr_aluno){
        return dados.removerAlunos(nr_aluno);
    }

    @Override
    public boolean voltar(boolean guardado) {
        alteraState(new ConfiguracaoState(dados,contexto));
        return false;
    }

    @Override
    public boolean avancar(boolean guardado, int op) {
        alteraState(new opCandidaturaState(dados, contexto));
        return false;
    }

    @Override
    public boolean selecionar(int escolha) {
        return false;
    }

    @Override
    public PoeState getState() {
        return PoeState.GESTAO_ALUNO;
    }
}
