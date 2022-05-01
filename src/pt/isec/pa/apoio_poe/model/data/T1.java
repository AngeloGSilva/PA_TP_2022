package pt.isec.pa.apoio_poe.model.data;

public class T1 extends Proposta {
    public T1(String ramo,String tit, String cod_id,String empresa) {
        super(cod_id,tit,null,null,ramo,empresa);
    }

    public T1(String ramo,String tit, String cod_id,Long codaluno,String empresa){
        super(cod_id,tit,codaluno,null,ramo,empresa);
    }

    @Override
    public String toString() {
        if(codigo_Aluno != null){
            return "T1" +
                    " Codigo Id : " + cod_ID +
                    " Titulo : " + titulo +
                    " Empresa : " + empresa +
                    " Ramo: " + ramo +
                    " |Aluno associado| : " + codigo_Aluno +
                    "\n";
        }else{
            return "T1" +
                    " Codigo Id : " + cod_ID +
                    " Titulo : " + titulo +
                    " Empresa : " + empresa +
                    " Ramo: " + ramo +
                    " |Sem aluno associado| " +
                    "\n";

        }
    }
}
