package pt.isec.pa.apoio_poe.model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Ficheiro {

    private static String caminho = "C:\\Users\\Rodrigo\\Desktop\\Pa-tp\\PA_TP_2022\\Resources\\ficheiros\\";
    //private static String CaminhoFile;
    //para leitura dos ficheiros
    private static String[] data;
    private static File f = null;
    private static FileReader fileReader = null;
    private static BufferedReader bufferedReader = null;
    private static String linha;

    private static FileWriter fw;
    private static BufferedWriter bw;
    private static PrintWriter pw;

    //arrays para verificacoes que vao permitir usar o contains
    private static String[] ramos = {"DA", "SI", "RAS"};
    private static List<String> Ramos = Arrays.asList(ramos);
    private static String[] curso = {"LEI", "LEI-PL"};
    private static List<String> Curso = Arrays.asList(curso);
    private static String[] tipos = {"T1", "T2", "T3"};
    private static List<String> Tipos = Arrays.asList(tipos);


    private Ficheiro() {
    }

    public static int lerDoncentes(String fileName, GestaoProj gestaoProj) {
        int count=0;
        try {
            //CaminhoFile = caminho + fileName;
            //fileName = "C:\\Users\\Rodrigo\\Desktop\\Pa-tp\\PA_TP_2022\\Resources\\ficheiros\\docentes.csv";
            f = new File(fileName);
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null) {
                data = linha.split(",");
                if (data[0].contains(" ") &&  //sem nome
                        data[1].contains("@isec.pt") && //email valido
                        !gestaoProj.getDocentes().contains(data[1]) && //email existe
                         data.length == 2 //2 parametros
                ) {
                    gestaoProj.adicionarDocentes(new Docente(data[0], data[1], false));
                } else {
                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                    gestaoProj.setErros("[Erro] no seguinte Docente" + Arrays.toString(data) + "\n");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao existe!");
        }catch (IOException e) {
            e.printStackTrace();
        }
        return count;// se correu bem false se correu mal
    }

    public static int lerAlunos(String fileName, GestaoProj gestaoProj) {
        int count=0;
        try {
            //fileName = "C:\\Users\\Rodrigo\\Desktop\\Pa-tp\\PA_TP_2022\\Resources\\ficheiros\\ alunosteste.csv";
            //CaminhoFile = caminho + fileName;
            f = new File(fileName);
            //if(!f.exists())
               // System.out.println();
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null) {
                data = linha.split(",");
                if (data[0].length() == 10 && //se o numero tem 10 digitos
                        !gestaoProj.VerificaAlunoExiste(Long.parseLong(data[0])) && //se o numero ja nao se encontra noutro aluno
                        data[1].contains(" ") &&  //se tem um espaco entre os dois nomes (lg tem 2 nomes)
                        //email deve ter a + numero de aluno, seguido de @isec.pt
                        data[2].contains("@isec.pt") && //email valido
                        !gestaoProj.getAlunoPorEmail(data[2]) && //Verifica se email existe
                        !data[3].isEmpty() && //se nao esta vazio
                        Curso.contains(data[3]) && //ver se o Lei esta correto
                        Ramos.contains(data[4]) && //ramo valido
                        Double.parseDouble(data[5]) <= 1 && //classificao menor q 1
                        ("true".toUpperCase(Locale.ROOT).equals(data[6].toUpperCase(Locale.ROOT)) ||
                        "false".toUpperCase(Locale.ROOT).equals(data[6].toUpperCase(Locale.ROOT)))
                ) {
                    count++;
                    gestaoProj.adicionarAlunos(new Aluno(Long.parseLong(data[0]), data[1], data[2], data[4], Double.parseDouble(data[5]), Boolean.parseBoolean(data[6]),data[3]));
                } else {
                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                    gestaoProj.setErros("[Erro] no seguinte Aluno" + Arrays.toString(data) + "\n");
                }
            }

        }catch (FileNotFoundException e) {
            gestaoProj.setErros("[Erro] Ficheiro nao existe!!" + fileName +"\n");
            //System.out.println("Ficheiro nao existe!");
        }catch (IOException e) {
            e.printStackTrace();
        }

        return count;// se correu bem false se correu mal

    }

    public static int lerPropostas(String fileName, GestaoProj gestaoProj) {
        int count=0;
        try {
            //CaminhoFile = caminho + fileName;
            //fileName = "C:\\Users\\Rodrigo\\Desktop\\Pa-tp\\PA_TP_2022\\Resources\\ficheiros\\propostas.csv";
            f = new File(fileName);
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);

            while ((linha = bufferedReader.readLine()) != null) {
                data = linha.split(",");
                //data[2].split("|");
                if (Tipos.contains(data[0])){ //se é um dos 3 tipos de proposta
                        switch (data[0]) {
                            case "T1" -> {
                                if (data.length == 5 && //proposta tem 5 campos
                                        !gestaoProj.VerificaIdProposta(data[1]) && //id da proposta repetido
                                        ((data[2].length() > 3 && data[2].contains("|")) || (data[2].length() <= 3 && Ramos.contains(data[2])))) //ver se tem mais q um ramo associado
                                {
                                    gestaoProj.adicionarProposta(new T1(data[2], data[3], data[1],data[4]));
                                } else if(data.length == 6 &&
                                        gestaoProj.VerificaAlunoExiste(Long.parseLong(data[5])) &&
                                        !gestaoProj.VerificaIdProposta(data[1]) && //id da proposta repetido
                                        gestaoProj.VerificaAlunoAcederPropostaLeitura(Long.parseLong(data[5]),data[0]) && //Verifica proposta durante a leitura
                                        ((data[2].length() > 3 && data[2].contains("|")) || (data[2].length() <= 3 && Ramos.contains(data[2])))) //ver se tem mais q um ramo associado
                                {
                                    gestaoProj.adicionarProposta(new T1(data[2], data[3], data[1] ,Long.parseLong(data[5]),data[4]));
                                    gestaoProj.adicionarCandidatura(new Candidatura(gestaoProj.getAlunoPorNumero(Long.parseLong(data[5])),gestaoProj.getPropostaPorId(data[1])));
                                } else {
                                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                                    gestaoProj.setErros("[Erro] no seguinte Proposta" + Arrays.toString(data) + "\n");
                                }
                            }
                            case "T2" -> {
                                if (data.length == 5 &&
                                        !gestaoProj.VerificaIdProposta(data[1]) && //id da proposta repetido
                                        gestaoProj.verificaEmailDocente(data[4]) && //email de um docente valido
                                        ((data[2].length() > 3 && data[2].contains("|"))  || (data[2].length() <= 3 && Ramos.contains(data[2])))) //ramos associados
                                {
                                    gestaoProj.adicionarProposta(new T2(data[1], data[3], data[2], data[4]));
                                    //Definir o docente como proponente do projeto
                                    gestaoProj.getDocentePorEmailObjeto(data[4]).setPapel_Docente(true);
                                    //Subir contador
                                    gestaoProj.getDocentePorEmailObjeto(data[4]).incContador();
                                } else if (gestaoProj.verificaEmailDocente(data[4]) && //email de um docente valido
                                        !gestaoProj.VerificaIdProposta(data[1]) && //id da proposta repetido
                                        gestaoProj.VerificaAlunoExiste(Long.parseLong(data[5])) && //numero de aluno valido
                                        ((data[2].length() > 3 && data[2].contains("|"))  || (data[2].length() <= 3 && Ramos.contains(data[2])))) //ramos associado
                                {
                                    gestaoProj.adicionarProposta(new T2(data[1], data[3], data[2], data[4], Long.parseLong(data[5])));
                                    //Definir o docente como proponente do projeto
                                    gestaoProj.getDocentePorEmailObjeto(data[4]).setPapel_Docente(true);
                                    //Subir contador
                                    gestaoProj.getDocentePorEmailObjeto(data[4]).incContador();
                                    //Adicionar durante  leitura das propostas
                                    gestaoProj.adicionarCandidatura(new Candidatura(gestaoProj.getAlunoPorNumero(Long.parseLong(data[5])),gestaoProj.getPropostaPorId(data[1])));
                                } else {
                                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                                    gestaoProj.setErros("[Erro] no seguinte Proposta" + Arrays.toString(data) + "\n");
                                }
                            }
                            case "T3" -> {
                                if (gestaoProj.VerificaAlunoExiste(Long.parseLong(data[3])) && //numero de aluno valido
                                        !gestaoProj.VerificaIdProposta(data[1]) && //id da proposta repetido
                                        !gestaoProj.get_codigoAluno(Long.parseLong(data[3]))) //se aluno ja nao esta associado a um T3
                                {
                                    gestaoProj.adicionarProposta(new T3(data[1], data[2], Long.parseLong(data[3]),gestaoProj.getAlunoPorNumero(Long.parseLong(data[3])).getRamo_Aluno()));
                                    gestaoProj.adicionarCandidatura(new Candidatura(gestaoProj.getAlunoPorNumero(Long.parseLong(data[3])),gestaoProj.getPropostaPorId(data[1])));
                                } else {
                                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                                    gestaoProj.setErros("[Erro] no seguinte Proposta" + Arrays.toString(data) + "\n");
                                }
                            }
                        }
                } else {
                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                    gestaoProj.setErros("[Erro] no seguinte Proposta" + Arrays.toString(data) + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            gestaoProj.setErros("[Erro] Ficheiro nao existe!!" + fileName +"\n");
            //System.out.println("Ficheiro nao existe!");
        }catch (IOException e) {
            e.printStackTrace();
        }
        return count;// se correu bem false se correu mal
    }

    public static int lercandidaturas(String fileName, GestaoProj gestaoProj){
/*        switch(PAInput.chooseOption("Path dos ficheiros","Angelo","Rodrigo")){
            case 1 ->{
                fileName = "C:\\Users\\Angelo\\Desktop\\______\\ISEC\\PA\\PA_TP2022\\PA_TP_2022\\Resources\\ficheiros\\candidaturas.csv";
            }case 2 ->{
                fileName = "C:\\Users\\Rodrigo\\Desktop\\Pa-tp\\PA_TP_2022\\Resources\\ficheiros\\candidaturas.csv";
            }
        }*/
        ArrayList<Proposta> propostasPorAluno = new ArrayList<>();
        int count=0;
        try {
            //CaminhoFile = caminho + fileName;
            f = new File(fileName);
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
            while ((linha = bufferedReader.readLine()) != null) {
                data = linha.split(",");
                if(gestaoProj.VerificaAlunoExiste(Long.parseLong(data[0])) &&
                        !gestaoProj.getNrAlunoCandidatura(Long.parseLong(data[0])) &&
                        !gestaoProj.VerificaNumeroAssociadoAProposta(data[0]) &&
                        data.length > 1)
                { //data[1].contains("P[0-9]{3}")
                    for(int i = 1;i<data.length;i++){
                        if(gestaoProj.VerificaIdProposta(data[i]) &&
                                gestaoProj.VerificaAlunoAcederProposta(Long.parseLong(data[0]),data[i]) &&
                                gestaoProj.VerificaPropostaComAluno(data[i])){ //
                            propostasPorAluno.add(gestaoProj.getPropostaPorId(data[i]));
                        }else {
                            gestaoProj.setErros("[Erro] no seguinte Proposta"+ data[i] +"do aluno" + data[0] +  "\n");
                        }
                    }//se tem pelo menos 1 proposta
                    if(!propostasPorAluno.isEmpty()) {
                        gestaoProj.adicionarCandidatura(new Candidatura(gestaoProj.getAlunoPorNumero(Long.parseLong(data[0])), propostasPorAluno));
                        propostasPorAluno.clear();
                    }
                }else{
                    //metodo para gravar o erro e enviar para UI e informar o utilizador
                    //separar erro de aluno que nao existe / ou se é aluno com candidatura efetuada
                    if(gestaoProj.get_codigoAluno(Long.parseLong(data[0]))) {
                        gestaoProj.setErros("[Erro] Aluno ja tem candidatura efetuada" + data[0] + "\n");
                    }else if(gestaoProj.getNrAlunoCandidatura(Long.parseLong(data[0]))){
                        gestaoProj.setErros("[Erro] Aluno ja proposto anteriormente!" + data[0] +"\n");
                    }else{
                        gestaoProj.setErros("[Erro] Aluno nao existe" + data[0] +"\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            gestaoProj.setErros("[Erro] Ficheiro nao existe!!" + fileName +"\n");
        }catch (IOException e){
            e.printStackTrace();
        }
        return count;
    }

    public static void ExportarAlunos(String fileName,GestaoProj gestaoProj){
        //f = new File("C:\\Users\\Angelo\\Desktop\\______\\ISEC\\PA\\PA_TP2022\\PA_TP_2022\\Resources\\ficheiros\\" +fileName+".csv");
        f = new File(fileName);
        try {
            fw = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);
        for (Aluno aluno:gestaoProj.getAlunos()) {
            pw.print(aluno.getNr_Aluno());
            pw.print(',');
            pw.print(aluno.getNome_Aluno());
            pw.print(',');
            pw.print(aluno.getEmail_Aluno());
            pw.print(',');
            pw.print(aluno.getCurso());
            pw.print(',');
            pw.print(aluno.getRamo_Aluno());
            pw.print(',');
            pw.print(aluno.getClassificacao_Aluno());
            pw.print(',');
            pw.print(aluno.isAceder_a_Estagio());
            pw.println();
        }
        pw.flush();
        pw.close();
    }

    public static void ExportarPropostas(String fileName,GestaoProj gestaoProj){
        //f = new File("C:\\Users\\Angelo\\Desktop\\______\\ISEC\\PA\\PA_TP2022\\PA_TP_2022\\Resources\\ficheiros\\" +fileName+".csv");
        f = new File(fileName);
        try {
            fw = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);
        for (Proposta p : gestaoProj.getPropostas()) {
            switch(p.getClass().getSimpleName()){
                case "T1" ->{
                    pw.print("T1");
                    pw.print(',');
                    pw.print(p.getCod_ID());
                    pw.print(',');
                    pw.print(p.getRamo());
                    pw.print(',');
                    pw.print(p.getTitulo());
                    pw.print(',');
                    pw.print(p.getEmpresa());
                    if(p.getCodigo_Aluno() != null) {
                        pw.print(',');
                        pw.print(p.getCodigo_Aluno());
                    }
                }
                case "T2" ->{
                    pw.print("T2");
                    pw.print(',');
                    pw.print(p.getCod_ID());
                    pw.print(',');
                    pw.print(p.getRamo());
                    pw.print(',');
                    pw.print(p.getTitulo());
                    pw.print(',');
                    pw.print(p.getEmail_Docente());

                    if(p.getCodigo_Aluno() != null) {
                        pw.print(',');
                        pw.print(p.getCodigo_Aluno());
                    }
                }
                case "T3" ->{
                    pw.print("T3");
                    pw.print(',');
                    pw.print(p.getCod_ID());
                    pw.print(',');
                    pw.print(p.getTitulo());
                    pw.print(',');
                    pw.print(p.getCodigo_Aluno());
                }
            }
            pw.println();
        }
        pw.flush();
        pw.close();
    }

    public static void ExportarDocentes(String fileName,GestaoProj gestaoProj){
        //f = new File("C:\\Users\\Angelo\\Desktop\\______\\ISEC\\PA\\PA_TP2022\\PA_TP_2022\\Resources\\ficheiros\\" +fileName+".csv");
        f = new File(fileName);
        try {
            fw = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);

        for (Docente docente:gestaoProj.getDocentes()) {
            pw.print(docente.getNome_Docente());
            pw.print(',');
            pw.print(docente.getEmail_Docente());
            pw.println();
        }
        pw.flush();
        pw.close();
    }

    public static void ExportarCandidaturas(String filename,GestaoProj gestaoProj){
        f = new File(filename);

        try {
            fw = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);

        for (Candidatura candidatura:gestaoProj.getCandidaturas()) {
            pw.print(candidatura.getAluno().getNr_Aluno());
            for(Proposta p:candidatura.getPropostas()) {
                pw.print(',');
                pw.print(p.getCod_ID());
            }
            pw.println();
        }
        pw.flush();
        pw.close();
    }

}