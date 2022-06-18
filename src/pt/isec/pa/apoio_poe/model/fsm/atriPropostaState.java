package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

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

    @Override
    public boolean AtribuirAutomaticamente(){
        if(dados.atribuiAutomaticamente() == false){
            dados.atribuiEstagiosSugeridos();
            dados.atribuirSemCandidatura();
            alteraState(new atriPropostaState(dados,contexto));
            return true;
        }else
            alteraState(new ConflitoState(dados,contexto));
        return false;
    }

    @Override
    public void AtribuirAutomaticoAutopropostosDocentesAluno() {
        dados.atribuiAutopropostos();
        dados.atribuiPropostasDocentesCompletas();

        alteraState(new atriPropostaState(dados,contexto));
        //return false;
    }

    @Override
    public boolean atribuirManualmenteAluno(long id_aluno, String proposta) {
        if(dados.atribuirManualmenteAluno(id_aluno,proposta)){
            alteraState(new atriPropostaState(dados,contexto));
            return true;
        }
        alteraState(new atriPropostaState(dados,contexto));
        return false;
    }

    @Override
    public Boolean removeAtribuicao(String nrAluno) {
            alteraState(new atriPropostaState(dados,contexto));
            return dados.removerAtribuicao(nrAluno);
    }

    @Override
    public int removerAllAtribuicao(){
        alteraState(new atriPropostaState(dados,contexto));
        return dados.removerAllAtribuicao();
    }


    @Override
    public PoeState getState() {
        return PoeState.ATRIBUIR_PROPOSTA;
    }
}
