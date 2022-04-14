package pt.isec.pa.apoio_poe.model.data;

public class T2 extends Propostas {
    String docente;

    public T2(String codid,String tit,String ramo,String docente) {
        this.ramo = ramo;
        this.cod_ID = codid;
        this.titulo = tit;
        this.docente = docente;
    }
    //Aluno opcional
    public T2(String codid,String tit,String ramo,String docente,String codaluno) {
        super(codid,tit,ramo,docente,codaluno);
    }
}
