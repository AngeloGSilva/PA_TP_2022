package pt.isec.pa.apoio_poe.model.data;

public class T1 extends Proposta {
    public T1(String ramo,String tit, String cod_id) {
        super(cod_id,tit,null,null,ramo);
    }

    public T1(String ramo,String tit, String cod_id,Long codaluno){
        super(cod_id,tit,codaluno,null,ramo);
    }

}
