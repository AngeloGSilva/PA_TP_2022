package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class GestaoDocenteState extends IStateAdaptar {
    public GestaoDocenteState(GestaoProj dados, ProContexto contexto) {
        super(dados,contexto);
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
    public boolean lerFicheiroDebug(String fileName){
        dados.lerficheiroProposta(fileName);
        dados.lerficheiroDocente(fileName);
        dados.lerficheiroAluno(fileName);
        return false;
    }

    @Override
    public boolean avancar(boolean guardado) {
        if(guardado && dados.CondicaoAvancar()){
            dados.setFase_Fechada_Config(true);
            System.out.println("Fase fechada\n");
            alteraState(new opCandidaturaState(dados, contexto));
        }else if(!guardado) {
            System.out.println("Nao fechou a fase\n");
            alteraState(new opCandidaturaState(dados, contexto));
        }else {
            System.out.println("Numero de propostas inferior ao numero de alunos,fase nao fechada\n");
            alteraState(new GestaoDocenteState(dados, contexto));
        }
        return false;
    }

    @Override
    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente){
        Docente docente = new Docente(nome_Docente,email_Docente,papel_Docente);
        if(dados.adicinarDocentes(docente)){
            alteraState(new GestaoDocenteState(dados,contexto));
        }else{
            alteraState(new GestaoDocenteState(dados,contexto));
            return false;
        }
        return true;
    }
/*

    @Override
    public boolean removerDocente(long nr_aluno){
        return dados.removerAlunos(nr_aluno);
    }
*/
    @Override
    public void lerFicheiro(String fileName){
        dados.lerficheiroDocente(fileName);
        alteraState(new GestaoDocenteState(dados,contexto));
    }


    @Override
    public boolean selecionar(int escolha) {
        return false;
    }


    @Override
    public PoeState getState() {
        return PoeState.GESTAO_DOCENTE;
    }
}
