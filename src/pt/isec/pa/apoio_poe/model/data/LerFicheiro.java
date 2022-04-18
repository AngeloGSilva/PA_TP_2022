package pt.isec.pa.apoio_poe.model.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class LerFicheiro {

    private LerFicheiro(){}

    private static String fileName;
    private static String linha;
    private static BufferedReader bufferedReader = null;


    //Aluno aluno = new aluno();
    //Docente docente = new docente();
    //Proposta proposta = new proposta();


    //metodos para ler os varios ficheiros
    //lerDocentes()
    //lerAlunos()
    //lerProjetos()
    //lerCandidaturas()

    public static void lerDoncentes(String fileName){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null){
                System.out.println(linha);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //fileReader.close();
        //bufferedReader.close();
        //return da leitura dos ficheiros
        //return array de docentes
        //ou
        //return de 1 docente
    }

    public static void lerAlunos(String fileName){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null){
                System.out.println(linha);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //fileReader.close();
        //bufferedReader.close();
        //return da leitura dos ficheiros
        //return array de docentes
        //ou
        //return de 1 docente
    }

    public static void lerPropostas(String fileName){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null){
                System.out.println(linha);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //fileReader.close();
        //bufferedReader.close();
        //return da leitura dos ficheiros
        //return array de docentes
        //ou
        //return de 1 docente
    }

}

