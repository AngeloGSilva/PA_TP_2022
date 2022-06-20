package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

/**
 * Class que deriva da Class Proposta, sendo esta representativa de uma tipo de Proposta neste caso o tipo T1
 * @see Serializable
 * @see Proposta
 */
public class T1 extends Proposta implements Serializable {
    /**
     * Construtor para o tipo T1 com o codigo Aluno a null
     * @param ramo Ramo a que se destina a Proposta
     * @param tit   Titulo da Proposta
     * @param cod_id    Codigo identificador da Proposta (identificador unico)
     * @param empresa   Empresa que propos a Proposta
     * @see Proposta
     */
    public T1(String ramo,String tit, String cod_id,String empresa) {
        super(cod_id,tit,null,null,ramo,empresa);
    }

    /**
     * Construtor para o tipo T1 com o Aluno associado, ou seja, com o campo codaluno diferente de null
     * @param ramo Ramo a que se destina a Proposta
     * @param tit   Titulo da Proposta
     * @param codaluno Numero do Aluno associado a Proposta
     * @param cod_id    Codigo identificador da Proposta (identificador unico)
     * @param empresa   Empresa que propos a Proposta
     * @see Proposta
     * @see Aluno
     */
    public T1(String ramo,String tit, String cod_id,Long codaluno,String empresa){
        super(cod_id,tit,codaluno,null,ramo,empresa);
    }

    @Override
    public String toString() {
        if(getCodigo_Aluno() != null){
            return "T1" +
                    " Codigo Id : " + getCod_ID() +
                    " Titulo : " + getTitulo() +
                    " Empresa : " + getEmpresa() +
                    " Ramo: " + getRamo() +
                    " |Aluno associado| : " + getCodigo_Aluno() +
                    "\n";
        }else{
            return "T1" +
                    " Codigo Id : " + getCod_ID() +
                    " Titulo : " + getTitulo() +
                    " Empresa : " + getEmpresa() +
                    " Ramo: " + getRamo() +
                    " |Sem aluno associado| " +
                    "\n";

        }
    }
}
