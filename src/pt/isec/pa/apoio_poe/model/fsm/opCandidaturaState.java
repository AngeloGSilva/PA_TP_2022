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
    public boolean avancar(boolean guardado) {
            if(guardado){
                dados.setFase_Fechada_Candidatura(guardado);
                alteraState(new atriPropostaState(dados, contexto));
            }else
                alteraState(new atriPropostaState(dados, contexto));
            return false;
    }

    @Override
    public void lerFicheiro(String fileName){
        dados.lerficheiroCandidaturas(fileName);
        alteraState(new opCandidaturaState(dados,contexto));
    }

    @Override
    public boolean selecionar(int escolha) {
        return false;
    }


    @Override
    public PoeState getState() {
        return PoeState.OPCAO_CANDIDATURA;
    }
}
