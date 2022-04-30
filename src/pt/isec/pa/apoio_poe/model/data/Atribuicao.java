package pt.isec.pa.apoio_poe.model.data;

import java.util.Objects;

public class Atribuicao {
    private static int counter_globalAtribuicao = 0;

    private static int getNewID() {
        return ++counter_globalAtribuicao;
    }

    private final int id;
    Aluno aluno;
    Docente docente;
    Proposta proposta;

    public Atribuicao(Aluno aluno, Docente docente, Proposta proposta) {
        this.aluno = aluno;
        this.docente = docente;
        this.proposta = proposta;
        this.id = getNewID();
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atribuicao that = (Atribuicao) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
        return "id: " + id +
                ", aluno: " + aluno +
                ", docente: " + docente +
                ", proposta: " + proposta +
                "\n";
    }
}
