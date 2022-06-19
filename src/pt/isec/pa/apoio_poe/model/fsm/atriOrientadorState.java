package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

public class atriOrientadorState extends IStateAdapter {
    public atriOrientadorState(GestaoProj dados, ProContexto contexto) {
        super(dados, contexto);
    }

    @Override
    public boolean voltar(boolean guardado) {
        alteraState(new atriPropostaState(dados,contexto));
        return false;
    }

    @Override
    public boolean avancar(boolean guardado) {
        if(guardado){
            dados.setFase_Fechada_atriOrientador(guardado);
            alteraState(new ConsultaState(dados, contexto));
        }else
            alteraState(new ConsultaState(dados, contexto));
        return false;
    }


    @Override
    public void atribuirDocentesauto() {
        dados.atribuirDocentesauto();
        alteraState(new atriOrientadorState(dados, contexto));
    }

    @Override
    public boolean atribuirManualmenteDocente(String docente, int id_atribuicao){
        if (dados.atribuirManualmenteDocente(docente,id_atribuicao)){
            alteraState(new atriOrientadorState(dados, contexto));
            return true;
        }
        alteraState(new atriOrientadorState(dados, contexto));
        return false;
    }

    @Override
    public void removeDocenteAtribuido(int idProp) {
        dados.removerDocenteAtribuido(idProp);
        alteraState(new atriOrientadorState(dados, contexto));
    }


    @Override
    public boolean selecionar(PoeState escolha) {
        return false;
    }


    @Override
    public PoeState getState() {
        return PoeState.ATRIBUIR_ORIENTADOR;
    }


}
