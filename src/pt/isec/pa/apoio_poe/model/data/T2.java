package pt.isec.pa.apoio_poe.model.data;

public class T2 extends Proposta {

    public T2(String codid,String tit,String ramo,String docente) {
        super(null,tit,null,docente,null);
    }
    //Aluno opcional
    public T2(String codid,String tit,String ramo,String docente,String codaluno) {
        super(codid,tit,codaluno,docente,ramo);
    }
}
