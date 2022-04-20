package pt.isec.pa.apoio_poe.model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class LerFicheiro {

    private LerFicheiro(){}

    private static FileReader fileReader = null;
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

    public static boolean lerDoncentes(String fileName, GestaoProj gestaoProj){
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null){
                String[] data = linha.split(",");
                if(!gestaoProj.getDocentes().contains(data[1]) && data[0].contains(" ") &&  //numero repetido
                        data[1].contains("@isec.pt")
                ){
                    gestaoProj.adicinarDocentes(new Docente(data[0],data[1],false)); //nao pode estar false pq isto vai dar dor de cabecas ... precisamos de outra solucao para o papel do docente
                }else
                    System.out.println("nao tem");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;// se correu bem false se correu mal
    }

    public static boolean lerAlunos(String fileName, GestaoProj gestaoProj){

        FileReader fileReader = null;
        try {
            String[] ramos = {"DA","SI","RAS"};
            List<String> Ramos = Arrays.asList(ramos); // para poder usar contains
            String[] curso = {"LEI", "LEI_PL"};
            List<String> Curso = Arrays.asList(curso);
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null){
                String[] data = linha.split(",");
                if(data[0].length() == 10 &&
                        !gestaoProj.getAlunos().contains(data[0]) && data[1].contains(" ") &&  //numero repetido
                        data[2].contains("@isec.pt") &&
                        !gestaoProj.getAlunos().contains(data[2]) && //email repetido
                        !data[3].isEmpty() &&
                        Ramos.contains(data[4]) && //ramo valido
                        Double.parseDouble(data[5]) < 1 &&
                        "true".toUpperCase(Locale.ROOT).equals(data[6].toUpperCase(Locale.ROOT)) ||
                        "false".toUpperCase(Locale.ROOT).equals(data[6].toUpperCase(Locale.ROOT))
                ){
                        //FALTAM VERIFICACOES
                    for (int i = 0; i<data.length; i++) {
                        System.out.print(data[i] + " " );
                    }
                    gestaoProj.adicinarAlunos(new Aluno(Long.parseLong(data[0]),data[1],data[2],data[4],Double.parseDouble(data[5]),Boolean.parseBoolean(data[6])));
                    System.out.println();
                }else
                    System.out.println("nao tem");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    return true;// se correu bem false se correu mal

    }

    public static boolean lerPropostas(String fileName, GestaoProj gestaoProj){
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
        return true;
    }

}

