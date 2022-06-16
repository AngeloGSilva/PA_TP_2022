package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class opCandidaturaState extends IStateAdaptar {
    public opCandidaturaState(GestaoProj dados, ProContexto contexto) {
        super(dados, contexto);
    }

    @Override
    public boolean voltar(boolean guardado) {
        alteraState(new ConfiguracaoState(dados,contexto));
        return false;
    }

    @Override
    public boolean removerPropostaDeCandidatura(String nr_Aluno, String id_Candidatura){
        alteraState(new opCandidaturaState(dados,contexto));
        return dados.removerPropostaDeCandidatura(nr_Aluno,id_Candidatura);
    }

    @Override
    public boolean avancar(boolean guardado) {
            if(guardado && dados.isFase_Fechada_Config()){
                dados.setFase_Fechada_Candidatura(guardado);
                alteraState(new atriPropostaState(dados, contexto));
                return true;
            }else if(!guardado)
                alteraState(new atriPropostaState(dados, contexto));
            else{
                alteraState(new atriPropostaState(dados, contexto));
                return false;
            }
            return true;
    }

    @Override
    public int lerFicheiro(String fileName){
        int buf= dados.lerficheiroCandidaturas(fileName);
        alteraState(new opCandidaturaState(dados,contexto));
        return buf;
    }

    @Override
    public PoeState getState() {
        return PoeState.OPCAO_CANDIDATURA;
    }


}
