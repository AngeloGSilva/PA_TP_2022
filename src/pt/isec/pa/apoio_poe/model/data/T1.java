package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

public class T1 extends Proposta implements Serializable {
    public T1(String ramo,String tit, String cod_id,String empresa) {
        super(cod_id,tit,null,null,ramo,empresa);
    }

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
