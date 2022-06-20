package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

/**
 * Class que deriva da Class Proposta, sendo esta representativa de uma tipo de Proposta neste caso o tipo T3
 * @see Serializable
 * @see Proposta
 */
public class T3 extends Proposta implements Serializable {

    /**
     * Construtor para o tipo T3 com o Email do Docente a null
     * @param codID Identificador da Proposta (Identificador unico)
     * @param tit   Titulo da Proposta
     * @param codaluno  Numero do Aluno associado a Proposta
     * @param ramo  Ramo a que a Proposta se destina
     * @see Proposta
     * @see Aluno
     */
    public T3(String codID,String tit,Long codaluno,String ramo) {
        super(codID,tit,codaluno,null,ramo,null);
    }

    @Override
    public String toString() {
        return "T3" +
                " Codigo Id : " + getCod_ID() +
                " Titulo : " + getTitulo() +
                " Ramo: " + getRamo() +
                " |Aluno associado| : " + getCodigo_Aluno() +
                "\n";
    }
}
