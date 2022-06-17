package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Candidatura;
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
    public boolean adicionarCandidatura(String nrAluno,String codId){
        Candidatura c = dados.validarCandidatura(nrAluno,codId);
        if(c==null){
            dados.adicionarCandidatura(c);
            alteraState(new opCandidaturaState(dados,contexto));
            return true;
        }else
        alteraState(new opCandidaturaState(dados,contexto));
        return false;
    }

    @Override
    public boolean removerPropostaDeCandidatura(String nr_Aluno, String IdProposta){
        alteraState(new opCandidaturaState(dados,contexto));
        return dados.removerPropostaDeCandidatura(nr_Aluno,IdProposta);
    }

    @Override
    public boolean adicionarPropostaACandidatura(String nr_Aluno, String idProposta) {
        alteraState(new opCandidaturaState(dados,contexto));
        return dados.adicionarPropostaACandidatura(nr_Aluno, idProposta);
    }

    @Override
    public boolean remover(String nrAluno){
        alteraState(new opCandidaturaState(dados,contexto));
        return dados.removerCandidatura(Long.parseLong(nrAluno));
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
