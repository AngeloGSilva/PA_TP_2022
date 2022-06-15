package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public class GestaoPropostaState extends IStateAdaptar {
    public GestaoPropostaState(GestaoProj dados, ProContexto contexto) {
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
    public boolean lerFicheiroDebug(String fileName){
        dados.lerficheiroProposta(fileName);
        dados.lerficheiroDocente(fileName);
        dados.lerficheiroAluno(fileName);
        return false;
    }

    @Override
    public int lerFicheiro(String fileName){
        int buf = dados.lerficheiroProposta(fileName);
        alteraState(new GestaoPropostaState(dados,contexto));
        return buf;
    }

    @Override
    public boolean adicionarProposta(String tipo,String cod_ID, String titulo, Long codigo_Aluno, String email_Docente, String ramo,String empresa){
        Proposta p = dados.validarProposta(tipo,cod_ID,  titulo,  codigo_Aluno,  email_Docente,  ramo, empresa);
        if(p!=null){
            dados.adicionarProposta(p);
            alteraState(new GestaoPropostaState(dados,contexto));
            return true;
        }
            alteraState(new GestaoPropostaState(dados,contexto));
            return false;
    }

    @Override
    public boolean remover(String cod_ID){
        alteraState(new GestaoPropostaState(dados,contexto));
        return dados.removerProposta(cod_ID);
    }

/*    @Override
    public boolean removerProposta(String cod_ID){
        alteraState(new GestaoPropostaState(dados,contexto));
        return dados.removerProposta(cod_ID);
    }*/


    @Override
    public PoeState getState() {
        return PoeState.GESTAO_PROPOSTA;
    }
}
