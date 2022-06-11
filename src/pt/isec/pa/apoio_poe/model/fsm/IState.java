package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public interface IState {
    //redo e undo?
    boolean voltar(boolean guardado);
    boolean avancar(boolean guardado);
    boolean selecionar(PoeState escolha);
    boolean adicionarAluno(long nr_Aluno,String nome_Aluno,String email_Aluno,String ramo_Aluno,double classificacao_Aluno,boolean aceder_a_Estagio);
    boolean removerAluno(long nr_aluno);
    boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente);
    boolean adicionarProposta(Proposta proposta);
    void AtribuirAutomaticamente();
    void lerFicheiro(String fileName);
    boolean lerFicheiroDebug(String fileName);

    void resolverConflito(int selecionado);
    PoeState getState();

    void atribuirDocentesauto();

    boolean atribuirManualmenteDocente(String docente, int id_atribuicao);

    void AtribuirAutomaticoAutopropostosDocentesAluno();

    boolean atribuirManualmenteAluno(long id_aluno, String proposta);
}
