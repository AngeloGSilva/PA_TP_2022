package pt.isec.pa.apoio_poe.model.data;

public class Atribuicao {
    Aluno aluno;
    Docente docente;
    Proposta proposta;

    public Atribuicao(Aluno aluno, Docente docente, Proposta proposta) {
        this.aluno = aluno;
        this.docente = docente;
        this.proposta = proposta;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Docente getDocente() {
        return docente;
    }

    public Proposta getProposta() {
        return proposta;
    }

    @Override
    public String toString() {
        return "Atribuicao{" +
                "aluno=" + aluno +
                ", docente=" + docente +
                ", proposta=" + proposta +
                "}\n";
    }
}
