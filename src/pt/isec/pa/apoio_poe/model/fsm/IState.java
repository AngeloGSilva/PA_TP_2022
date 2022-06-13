package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Proposta;

public interface IState {
    //redo e undo?
    boolean voltar(boolean guardado);
    boolean avancar(boolean guardado);
    boolean selecionar(PoeState escolha);
    boolean adicionarAluno(String nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio, String curso);
    boolean removerAluno(long nr_aluno);
    boolean removerDocente(String emaildoc);
    public boolean removerProposta(String cod_ID);
    boolean adicionarDocente(String nome_Docente, String email_Docente, boolean papel_Docente);
    boolean adicionarProposta(String tipo,String cod_ID, String titulo, Long codigo_Aluno, String email_Docente, String ramo,String empresa);
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
