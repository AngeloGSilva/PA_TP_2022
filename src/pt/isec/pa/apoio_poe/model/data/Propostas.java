package pt.isec.pa.apoio_poe.model.data;

public class Propostas {
    //T1 - estagio , T2 - projeto , T3 - Estagio/projeto autoproposto
     private String cod_ID;
     private String titulo;
     private String codAluno; //Opcional
     private String codDocente = null; //Obrigatorio
     private String ramo;

 public Propostas(String cod_ID, String titulo, String codAluno, String codDocente, String ramo) {
  this.cod_ID = cod_ID;
  this.titulo = titulo;
  this.codAluno = codAluno;
  this.codDocente = codDocente;
  this.ramo = ramo;
 }


}

