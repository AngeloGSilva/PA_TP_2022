package pt.isec.pa.apoio_poe.model.data;

public class T1 extends Propostas {

    public T1(String ramo,String tit, String cod_id) {
        this.titulo = tit;
        this.cod_ID = cod_id;
        this.ramo = ramo;
    }
    //Aluno opcional
    public T1(String ramo,String tit, String cod_id,String codaluno) {
        this.titulo = tit;
        this.cod_ID = cod_id;
        this.ramo = ramo;
        this.codAluno =  codaluno;
    }
}
