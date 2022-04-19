package pt.isec.pa.apoio_poe.model.data;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void lerAlunos(String fileName){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int i = ois.readInt();

            String today = (String) ois.readObject();
            Aluno date = (Aluno) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null){
                String[] data = linha.split(",");
                if(data[0].length() == 10){
                    //adicionaAluno(data[0]);
                    System.out.println("Tem DEZ");
                }else
                    System.out.println("nao tem");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

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

