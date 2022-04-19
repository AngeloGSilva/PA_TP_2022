package pt.isec.pa.apoio_poe.model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
        //experiemtentar com o inputStream e Outptscream com o readObj


        //https://simplesolution.dev/java-read-and-parse-csv-file-using-apache-commons-csv/

        // pecorrer a linha td ate ao email e verificar se este tem em algum sitio o @
        //e um ".com"
        //ALUNOS
        //contar os digitos dos numeros
        //email igual ao docente
        //e por ai em diante
        // a maneira a baixo nao esta a funcionar


        // email ids written to myOutputFile.txt file
        PrintWriter p = null;
        try {
            p = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
// Regular expression for email id
        Pattern pat=Pattern.compile( "[a-zA-Z0-9]" + "[a-zA-Z0-9_.]" + "*@[a-zA-Z0-9]" + "+([.][a-zA-Z]+)+");
        BufferedReader b = null;
        try {
            b = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//reading myInputFile.txt file
        String l = null;
        try {
            l = b.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (l != null) {
            Matcher mat = pat.matcher(l);
            while (mat.find()) {
                p.println(mat.group());
            }
            try {
                l = b.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        p.flush();




        /*FileReader fileReader = null;
        Pattern pat= Pattern.compile( "[a-zA-Z0-9]" + "[a-zA-Z0-9_.]" + "*@[a-zA-Z0-9]" + "+([.][a-zA-Z]+)+");
        try {
            PrintWriter p = new PrintWriter(fileName);
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null){
                Matcher mat = pat.matcher(linha);
                while (mat.find()) {
                    p.println(mat.group());
                }
                linha = bufferedReader.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
*/
        //fileReader.close();
        //bufferedReader.close();
        //return da leitura dos ficheiros
        //return array de docentes
        //ou
        //return de 1 docente
    }

    public static boolean lerAlunos(String fileName, GestaoProj gestaoProj){

        FileReader fileReader = null;
        try {
            String[] ramos = {"SI", "DA", "RAS"};
            String[] curso = {"LEI", "LEI_PL"};

            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null){
                String[] data = linha.split(",");
                if(data[0].length() == 10 && data[1].contains(" ") &&
                        data[2].contains("@isec.pt") &&
                        !data[3].isEmpty() &&
                        //data[4].equalsIgnoreCase(String.valueOf(ramos)) &&
                        Double.parseDouble(data[5]) < 1 &&
                        "true".toUpperCase(Locale.ROOT).equals(data[6].toUpperCase(Locale.ROOT)) ||
                        "false".toUpperCase(Locale.ROOT).equals(data[6].toUpperCase(Locale.ROOT))
                ){
                        //FALTAM VERIFICACOES
                    for (int i = 0; i<data.length; i++) {
                        System.out.print(data[i] + " " );
                    }
                    System.out.println();
                }else
                    System.out.println("nao tem");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    return true;// se correu bem false se correu mal

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

