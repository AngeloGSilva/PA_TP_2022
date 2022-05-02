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
