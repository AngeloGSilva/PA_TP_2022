package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class GestaoAlunoState extends IStateAdapter {
    public GestaoAlunoState(GestaoProj dados, ProContexto contexto) {
        super(dados,contexto);
        //inicio
    }

    @Override
    public int lerFicheiro(String fileName){
        int buf = dados.lerficheiroAluno(fileName);
        alteraState(new GestaoAlunoState(dados,contexto));
        return buf;
    }

    @Override
    public boolean lerFicheiroDebug(String fileName){
        if(fileName.equals("a")){

            dados.lerficheiroAluno("C:\\Users\\Angelo\\Desktop\\______\\ISEC\\PA\\PA_TP2022\\PA_TP_2022\\Resources\\ficheiros\\alunos.csv");
            dados.lerficheiroDocente("C:\\Users\\Angelo\\Desktop\\______\\ISEC\\PA\\PA_TP2022\\PA_TP_2022\\Resources\\ficheiros\\docentes.csv");
            dados.lerficheiroProposta("C:\\Users\\Angelo\\Desktop\\______\\ISEC\\PA\\PA_TP2022\\PA_TP_2022\\Resources\\ficheiros\\propostas.csv");
        }else{
            dados.lerficheiroAluno("C:\\\\Users\\\\Rodrigo\\\\Desktop\\\\Pa-tp\\\\PA_TP_2022\\\\Resources\\\\ficheiros\\\\alunos.csv");
            dados.lerficheiroDocente("C:\\Users\\Rodrigo\\Desktop\\Pa-tp\\PA_TP_2022\\Resources\\ficheiros\\docentes.csv");
            dados.lerficheiroProposta("C:\\\\Users\\\\Rodrigo\\\\Desktop\\\\Pa-tp\\\\PA_TP_2022\\\\Resources\\\\ficheiros\\\\propostas.csv");
        }
        alteraState(new GestaoAlunoState(dados,contexto));
        return false;
    }

   @Override
    public boolean adicionarAluno(String nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio, String curso){
        Aluno a = dados.validarAluno( nr_Aluno,nome_Aluno,email_Aluno,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio,curso);
        if(a!= null){
            dados.adicionarAlunos(a);
           alteraState(new GestaoAlunoState(dados,contexto));
           return true;
        }
        alteraState(new GestaoAlunoState(dados,contexto));
        return false;
    }

    @Override
    public boolean remover(String nr_aluno){
        alteraState(new GestaoAlunoState(dados,contexto));
        return dados.removerAlunos(Long.parseLong(nr_aluno));
    }

    @Override
    public boolean voltar(boolean guardado) {
        if(!dados.isFase_Fechada_Config()){
            alteraState(new ConfiguracaoState(dados,contexto));
        }else {
            //System.out.println("Fase fechada");
            alteraState(new ConfiguracaoState(dados,contexto));
        }
        return false;
    }

    @Override
    public boolean avancar(boolean guardado) {
        if(guardado && dados.condicaoAvancar()){
            dados.setFase_Fechada_Config(true);
            System.out.println("Fase fechada\n");
            alteraState(new opCandidaturaState(dados, contexto));
            return true;
        }else if(!guardado) {
            System.out.println("Nao fechou a fase\n");
            alteraState(new opCandidaturaState(dados, contexto));
        }else {
            //System.out.println("Numero de propostas inferior ao numero de alunos,fase nao fechada\n");
            alteraState(new opCandidaturaState(dados, contexto));
            return false;
        }
        return true;
    }

    @Override
    public PoeState getState() {
        return PoeState.GESTAO_ALUNO;
    }

    @Override
    public void editar(String identificador, String editado) {
//try catch de numero, crash se for uma letra
        if(dados.getAlunoPorNumero(Long.parseLong(identificador)) !=null) {
            dados.getAlunoPorNumero(Long.parseLong(identificador)).setNomeAluno(editado);
            alteraState(new GestaoAlunoState(dados, contexto));
        }else
            return;
    }
}
