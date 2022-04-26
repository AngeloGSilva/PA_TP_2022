package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;

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
    public boolean lerFicheiroDebug(String fileName){
        dados.lerficheiroProposta(fileName);
        dados.lerficheiroDocente(fileName);
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
        if(!dados.isFase_Fechada_Config()){
            alteraState(new ConfiguracaoState(dados,contexto));
        }else {
            System.out.println("Fase fechada");
            alteraState(new ConfiguracaoState(dados,contexto));
        }
        return false;
    }

    @Override
    public boolean avancar(boolean guardado, int op) {
        if(guardado && dados.getNrPropostas() >= dados.getNrAlunos()){
            dados.setFase_Fechada_Config(true);
            alteraState(new opCandidaturaState(dados, contexto));
        }else
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
