package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.fsm.PoeState;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class que representa uma Atribuicao onde tem a indicacao da Proposta atribuida o Aluno e o Docente encarregue
 * @see Aluno
 * @see Proposta
 * @see Docente
 * @see Serializable
 */
public class Atribuicao implements Serializable {
    private static int counter_globalAtribuicao = 0;

    private static int getNewID() {
        return ++counter_globalAtribuicao;
    }

    private final int id;
    Aluno aluno;
    Docente docente;
    Proposta proposta;

    /**
     * Construtor da Class Atribuicao
     * @param aluno Aluno a qual a proposta foi atribuida
     * @param docente Docente encarregue de orientar o Aluno
     * @param proposta Proposta associada ao Aluno
     * @see Aluno
     * @see Docente
     * @see Proposta
     */
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

    public String getNomeDocenteTV(){
        if (docente != null){
            return docente.getNome_Docente();
        }
        return null;
    }

    public String getEmailDocenteTV(){
        if (docente != null){
            return docente.getEmail_Docente();
        }
        return null;
    }

    public Proposta getProposta() {
        return proposta;
    }

    @Override
    public String toString() {
        if(docente==null){
            return "\nAtribuicao: " +
                    "Id: " + id + "\n"+
                    aluno +
                    " Proposta: " + proposta +
                    "\n";
        }else{
            return "\nAtribuicao: " +
                    "Id: " + id + "\n"+
                    aluno +
                    docente +
                    " Proposta: " + proposta +
                    "\n";
        }

    }
    //??? equals?
}
