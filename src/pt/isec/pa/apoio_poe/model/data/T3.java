package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

public class T3 extends Proposta implements Serializable {

    public T3(String codID,String tit,Long codaluno,String ramo) {
        super(codID,tit,codaluno,null,ramo,null);
    }

    @Override
    public String toString() {
        return "T3" +
                " Codigo Id : " + getCod_ID() +
                " Titulo : " + getTitulo() +
                " |Aluno associado| : " + getCodigo_Aluno() +
                " Ramo: " + getRamo() +
                "\n";
    }
}
