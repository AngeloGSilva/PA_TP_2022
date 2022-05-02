package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

public class T2 extends Proposta implements Serializable {

    public T2(String codid,String tit,String ramo,String docente) {
        super(codid,tit,null,docente,ramo,null);
    }
    //Aluno opcional
    public T2(String codid,String tit,String ramo,String docente,Long codaluno) {
        super(codid,tit,codaluno,docente,ramo,null);
    }

    @Override
    public String toString() {
        if(codigo_Aluno != null){
            return "T2" +
                    " Codigo Id : " + cod_ID +
                    " Titulo : " + titulo +
                    " Docente : " + email_Docente +
                    " Ramo: " + ramo +
                    " |Aluno associado| : " + codigo_Aluno +
                    "\n";
        }else{
            return "T2" +
                    " Codigo Id : " + cod_ID +
                    " Titulo : " + titulo +
                    " Docente : " + email_Docente +
                    " Ramo: " + ramo +
                    " |Sem aluno associado| " +
                    "\n";

        }
    }
}
