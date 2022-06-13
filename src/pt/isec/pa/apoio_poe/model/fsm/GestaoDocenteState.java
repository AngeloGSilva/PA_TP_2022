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
            //System.out.println("Fase fechada\n");
            alteraState(new opCandidaturaState(dados, contexto));
            return true;
        }else if(!guardado) {
            //System.out.println("Nao fechou a fase\n");
            alteraState(new opCandidaturaState(dados, contexto));
        }else {
            //System.out.println("Numero de propostas inferior ao numero de alunos,fase nao fechada\n");
            alteraState(new opCandidaturaState(dados, contexto));
            return false;
        }
        return true;
    }

    @Override
    public boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente){
        if(dados.adicinarDocentes(dados.validarDocente(nome_Docente,email_Docente,papel_Docente))){
            alteraState(new GestaoDocenteState(dados,contexto));
            return true;
        }
        alteraState(new GestaoDocenteState(dados,contexto));
        return false;
    }
    @Override
    public boolean remover(String email_docente){
        alteraState(new GestaoDocenteState(dados,contexto));
        return dados.removerDocente(email_docente);
    }

/*    @Override
    public boolean removerDocente(String emaildoc){
        alteraState(new GestaoDocenteState(dados,contexto));
        return dados.removerDocente(emaildoc);
    }*/
    @Override
    public void lerFicheiro(String fileName){
        dados.lerficheiroDocente(fileName);
        alteraState(new GestaoDocenteState(dados,contexto));
    }



    @Override
    public PoeState getState() {
        return PoeState.GESTAO_DOCENTE;
    }
}
