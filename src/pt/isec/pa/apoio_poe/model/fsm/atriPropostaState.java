package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.Utils.PAInput;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.GestaoProj;

import java.util.ArrayList;

public class atriPropostaState extends IStateAdaptar {
    public atriPropostaState(GestaoProj dados, ProContexto contexto) {
        super(dados, contexto);
    }

    @Override
    public boolean voltar(boolean guardado) {
        alteraState(new opCandidaturaState(dados,contexto));
        return false;
    }

    @Override
    public boolean avancar(boolean guardado) {
        if (dados.AlunoCandidaturaFoiAtribuido()){
            if(guardado){
                dados.setFase_Fechada_atriProposta(guardado);
                alteraState(new atriOrientadorState(dados, contexto));
                return true;
            }else
                alteraState(new atriOrientadorState(dados, contexto));
            return true;
        }
        alteraState(new atriOrientadorState(dados,contexto));
        return false;
    }

    public boolean AtribuirAutomaticamente(){
        dados.atribuiAutomaticamente();
        dados.atribuirSemCandidatura();
        return false;
    }

    @Override
    public boolean selecionar(int escolha) {
        return false;
    }

    @Override
    public PoeState getState() {
        return PoeState.ATRIBUIR_PROPOSTA;
    }
}
