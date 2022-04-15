package pt.isec.pa.apoio_poe.model.data;

import java.io.BufferedReader;
import java.io.FileReader;

abstract class LerFicheiro {
    String fileName;
    String linha;
    BufferedReader bufferedReader = null;


    //Aluno aluno = new aluno();
    //Docente docente = new docente();
    //Proposta proposta = new proposta();


    //metodos para ler os varios ficheiros
    //lerDocentes()
    //lerAlunos()
    //lerProjetos()
    //lerCandidaturas()

    public void lerDoncentes(String fileName){
        FileReader fileReader = new FileReader(fileName);
        bufferedReader = new BufferedReader(fileReader);
        while ((linha = bufferedReader.readLine()) != null){
            System.out.println(linha);
        }
        fileReader.close();
        bufferedReader.close();
        //return da leitura dos ficheiros
        //return array de docentes
        //ou
        //return de 1 docente
    }


}
