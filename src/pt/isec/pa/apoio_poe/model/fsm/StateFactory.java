package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoProj;

class StateFactory {
    static IState createState(PoeState tipo,
                                      ProContexto context, GestaoProj dados) {

        return switch (tipo) {
            case CONFIGURACAO -> new ConfiguracaoState(dados,context);
            case GESTAO_ALUNO -> new GestaoAlunoState(dados,context);
            case GESTAO_DOCENTE -> new GestaoDocenteState(dados,context);
            case GESTAO_PROPOSTA -> new GestaoPropostaState(dados,context);
            case OPCAO_CANDIDATURA->new opCandidaturaState(dados,context);
            case ATRIBUIR_PROPOSTA -> new atriPropostaState(dados,context);
            case CONFLITO -> new ConflitoState(dados,context);
            case ATRIBUIR_ORIENTADOR -> new atriOrientadorState(dados,context);
            case CONSULTA->new ConsultaState(dados,context);
            //default -> null;
        };
    }
}
