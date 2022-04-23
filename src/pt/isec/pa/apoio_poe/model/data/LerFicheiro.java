package pt.isec.pa.apoio_poe.model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class LerFicheiro {

    private static String[] data;

    private LerFicheiro() {
    }

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

    public static boolean lerDoncentes(String fileName, GestaoProj gestaoProj) {
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null) {
                String[] data = linha.split(",");
                if (data[0].contains(" ") &&  //sem nome
                        data[1].contains("@isec.pt") && //email valido
                        !gestaoProj.getDocentes().contains(data[1]) && //email existe
                         data.length == 2 //2 parametros
                ) {
                    gestaoProj.adicinarDocentes(new Docente(data[0], data[1], false)); //nao pode estar false pq isto vai dar dor de cabecas ... precisamos de outra solucao para o papel do docente
                } else {
                    System.out.print("[ERRO] no seguinte docente: ");
                    for (String x : data) {
                        System.out.print(x + ", ");
                    }
                    System.out.println();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;// se correu bem false se correu mal
    }

    public static boolean lerAlunos(String fileName, GestaoProj gestaoProj) {
        try {
            String[] ramos = {"DA", "SI", "RAS"};
            List<String> Ramos = Arrays.asList(ramos); // para poder usar contains
            String[] curso = {"LEI", "LEI_PL"};
            List<String> Curso = Arrays.asList(curso);
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null) {
                String[] data = linha.split(",");
                if (data[0].length() == 10 &&
                        !gestaoProj.getAlunoPorNr(Long.parseLong(data[0])) &&
                        data[1].contains(" ") &&  //numero repetido
                        data[2].contains("@isec.pt") &&
                        !gestaoProj.getAlunos().contains(data[2]) && //email repetido
                        !data[3].isEmpty() &&
                        Ramos.contains(data[4]) && //ramo valido
                        Double.parseDouble(data[5]) < 1 &&
                        "true".toUpperCase(Locale.ROOT).equals(data[6].toUpperCase(Locale.ROOT)) ||
                        "false".toUpperCase(Locale.ROOT).equals(data[6].toUpperCase(Locale.ROOT))
                ) {
                    gestaoProj.adicinarAlunos(new Aluno(Long.parseLong(data[0]), data[1], data[2], data[4], Double.parseDouble(data[5]), Boolean.parseBoolean(data[6])));
                } else {
                    System.out.print("[ERRO] no seguinte aluno: ");
                    for (String x : data) {
                        System.out.print(x + ", ");
                    }
                    System.out.println();
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

        return true;// se correu bem false se correu mal

    }

    public static boolean lerPropostas(String fileName, GestaoProj gestaoProj) {
        try {
            String[] ramos = {"DA", "SI", "RAS"};
            List<String> Ramos = Arrays.asList(ramos);
            String[] tipos = {"T1", "T2", "T3"};
            List<String> Tipos = Arrays.asList(tipos);

            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            while ((linha = bufferedReader.readLine()) != null) {
                String[] data = linha.split(",");
                //data[2].split("|");
                if (Tipos.contains(data[0])){//data[1].contains("P[0-9][0-9][0-9]") tentativa .. pede para eu explicar que eu explico o q esta a fazer .. mas kinda da para perceber
                        switch (data[0]) {
                            case "T1" -> {
                                //  length = 5 quer dizer que nao tem aluno .... maior que 5 tem aluno
                                if (data.length == 5 && !gestaoProj.get_idProposta(data[1]) && ((data[2].length() > 3 && data[2].contains("|")) || (data[2].length() <= 3 && Ramos.contains(data[2])))) {
                                    gestaoProj.adicinarProsta(new T1(data[2], data[3], data[1]));
                                } else if(!gestaoProj.get_idProposta(data[1]) && (data[2].length() > 3 && data[2].contains("|")) || (data[2].length() <= 3 && Ramos.contains(data[2]))) { //ver o aluno
                                    gestaoProj.adicinarProsta(new T1(data[2], data[3], data[1] ,data[5]));
                                } else {
                                    System.out.print("[ERRO] na seguinte proposta: ");
                                    for (String x : data) {
                                        System.out.print(x + ", ");
                                    }
                                    System.out.println();
                                }
                            }
                            case "T2" -> {
                                if (data.length == 5 &&
                                        !gestaoProj.get_idProposta(data[1]) &&
                                        gestaoProj.getDocentePorEmail(data[4]) &&
                                        ((data[2].length() > 3 && data[2].contains("|"))  || (data[2].length() <= 3 && Ramos.contains(data[2])))) {
                                    gestaoProj.adicinarProsta(new T2(data[1], data[3], data[2], data[4]));
                                } else if (gestaoProj.getDocentePorEmail(data[4]) &&
                                        !gestaoProj.get_idProposta(data[1]) &&
                                        gestaoProj.getAlunoPorNr(Long.parseLong(data[5])) &&
                                        ((data[2].length() > 3 && data[2].contains("|"))  || (data[2].length() <= 3 && Ramos.contains(data[2])))) {
                                    gestaoProj.adicinarProsta(new T2(data[1], data[3], data[2], data[4], data[5]));
                                } else {
                                    System.out.print("[ERRO] na seguinte proposta: ");
                                    for (String x : data) {
                                        System.out.print(x + ", ");
                                    }
                                    System.out.println();
                                }
                            }
                            case "T3" -> {
                                if (gestaoProj.getAlunoPorNr(Long.parseLong(data[3])) && !gestaoProj.get_idProposta(data[1]) && !gestaoProj.get_codigoAluno(data[3])) {
                                    gestaoProj.adicinarProsta(new T3(data[1], data[2], data[3]));
                                } else {
                                    System.out.print("[ERRO] na seguinte proposta: ");
                                    for (String x : data) {
                                        System.out.print(x + ", ");
                                    }
                                    System.out.println();
                                }
                            }
                        }   //para alterar conforme as cenas fornecidas
                        // vai ser usar o length e chamar o construtor correspondente

               /*     for (int i = 0;i< data.length ; i++) {
                        System.out.println(data[i]);
                    }*/
                } else {
                    System.out.print("[ERRO] na seguinte proposta: ");
                    for (String x : data) {
                        System.out.print(x + ", ");
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;// se correu bem false se correu mal
    }
}