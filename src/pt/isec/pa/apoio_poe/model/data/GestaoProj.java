package pt.isec.pa.apoio_poe.model.data;

import java.util.HashSet;

public class GestaoProj {

   /* //arrays para guardar objetos das classes ALUNOS , DOCENTES e PROJETOS
    Aluno[] alunos;
    Docente[] docentes;
    Proposta[] propostas;
    int nr_Alunos;
    int nr_Docentes;
    int nr_Projetos;
    //alterar


    public void adicionarAluno(long nr_Aluno, String nome_Aluno, String email_Aluno, String ramo_Aluno, double classificacao_Aluno, boolean aceder_a_Estagio){
        alunos[nr_Alunos] = new Aluno(nr_Aluno,nome_Aluno,email_Aluno,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);
        nr_Alunos++;
    }*/

    private HashSet<Aluno> alunos;


    public GestaoProj() {
        alunos = new HashSet<>();
    }


    public void adicinarAluno(Aluno aluno){
        alunos.add(aluno);
    }

}