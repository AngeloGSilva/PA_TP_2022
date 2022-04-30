package pt.isec.pa.apoio_poe.model.data;

public class T1 extends Proposta {
    public T1(String ramo,String tit, String cod_id,String empresa) {
        super(cod_id,tit,null,null,ramo,empresa);
    }

    public T1(String ramo,String tit, String cod_id,Long codaluno,String empresa){
        super(cod_id,tit,codaluno,null,ramo,empresa);
    }

}
