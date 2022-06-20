package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

/**
 * Class que deriva da Class Proposta, sendo esta representativa de uma tipo de Proposta neste caso o tipo T2
 * @see Serializable
 * @see Proposta
 */
public class T2 extends Proposta implements Serializable {

    /**
     * Construtor para o tipo T2 com o codigo Aluno a null
     * @param codid Identificador da Proposta (identificador unico)
     * @param tit   Titulo da Proposta
     * @param ramo  Ramo a que a Proposta se destina
     * @param docente   Email do Orientador desta Proposta
     * @see Proposta
     * @see Docente
     */
    public T2(String codid,String tit,String ramo,String docente) {
        super(codid,tit,null,docente,ramo,null);
    }
    //Aluno opcional

    /**
     * Construtor para o tipo T2 com o codigo Aluno
     * @param codid Identificador da Proposta (identificador unico)
     * @param tit   Titulo da Proposta
     * @param ramo  Ramo a que a Proposta se destina
     * @param docente   Email do Orientador desta Proposta
     * @param codaluno  Numero do Aluno associado
     * @see Proposta
     * @see Aluno
     * @see Docente
     */
    public T2(String codid,String tit,String ramo,String docente,Long codaluno) {
        super(codid,tit,codaluno,docente,ramo,null);
    }

    @Override
    public String toString() {
        if(getCodigo_Aluno() != null){
            return "T2" +
                    " Codigo Id : " + getCod_ID() +
                    " Titulo : " + getTitulo() +
                    " Docente : " + getEmail_Docente() +
                    " Ramo: " + getRamo() +
                    " |Aluno associado| : " + getCodigo_Aluno() +
                    "\n";
        }else{
            return "T2" +
                    " Codigo Id : " + getCod_ID() +
                    " Titulo : " + getTitulo() +
                    " Docente : " + getEmail_Docente() +
                    " Ramo: " + getRamo() +
                    " |Sem aluno associado| " +
                    "\n";

        }
    }
}
