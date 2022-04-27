package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.Utils.PAInput;
import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.fsm.ConfiguracaoState;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;

import java.util.ArrayList;

public class PoeUI {
    ProContexto controladorDoPrograma;
    //colocar o caminhoDefaultFicheiros na classe LerFicheiro
    private String caminhoDefaultFicheiros = "PA_TP_2022\\\\Resources\\\\ficheiros\\\\";
    public PoeUI(ProContexto controladorDoPrograma) {
        this.controladorDoPrograma = controladorDoPrograma;
    }

    boolean acabou = false;

    public void start(){
        while(!acabou){
            switch (controladorDoPrograma.getState()){
                case CONFIGURACAO -> configuracaoUI();
                case GESTAO_ALUNO -> gestaoAlunosUI();
                case GESTAO_DOCENTE -> gestaoDocentesUI();
                case GESTAO_PROPOSTA -> gestaoPropostaUI();
                case OPCAO_CANDIDATURA -> opCandidaturaUI();
                case ATRIBUIR_PROPOSTA -> atrPropostaUI();
                case ATRIBUIR_ORIENTADOR -> atrOrientadorUI();
                case CONSULTA -> consultaUI();
            }
        }
    }

    private void consultaUI() {
        System.out.println(controladorDoPrograma.getAlunos());
        System.out.println(controladorDoPrograma.getPropostas());
        System.out.println(controladorDoPrograma.getDocentes());
        controladorDoPrograma.voltar(controladorDoPrograma.getFase_Orientador());
        PAInput.readNumber("poe numero qualquer .... so para parar o programa e dar debug");
    }

    private void atrOrientadorUI() {
        System.out.println("Gestão de candidatura:\n");
        switch (PAInput.chooseOption("Opções:", "Avancar", "Voltar")) {
            case 1 -> {
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> controladorDoPrograma.avancar(true, 0);
                    case 2 -> controladorDoPrograma.avancar(false, 0);
                }
            }
            case 2 -> {
                controladorDoPrograma.voltar(controladorDoPrograma.getFase_Orientador());
            }
        }
    }

    private void atrPropostaUI() {
        System.out.println("Gestão de candidatura:\n");
        switch (PAInput.chooseOption("Opções:", "Avancar", "Voltar","Atribuir automatico Autopropostos e docentes com aluno","atribuir automatico","consulta")) {
            case 1 -> {
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> controladorDoPrograma.avancar(true, 0);
                    case 2 -> controladorDoPrograma.avancar(false, 0);
                }
            }
            case 2 -> {
                controladorDoPrograma.voltar(controladorDoPrograma.getFase_Proposta());
            }
            case 3->{
                controladorDoPrograma.atribuiAutopropostos();
                controladorDoPrograma.atribuiPropostasDocentes();
            }
            case 4->{
                ArrayList<String> conflito = new ArrayList<>();
                conflito = controladorDoPrograma.atribuiAutomaticamente();
                System.out.println(conflito);
                switch (PAInput.chooseOption("Deseja ver informacoes de qual aluno",conflito.get(0),conflito.get(1))){
                    case 1->{
                        System.out.println(controladorDoPrograma.getAlunoPorNr(conflito.get(0)));
                    }
                    case 2->{
                        System.out.println(controladorDoPrograma.getAlunoPorNr(conflito.get(1)));
                    }
                }

            }
            case 5->{
                System.out.println(controladorDoPrograma.getAtribuicoesPropostas());
            }
        }
    }

    private void opCandidaturaUI() {
        System.out.println("Gestão de candidatura:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta", "Ler Ficheiro" , "Eliminação","avançar","Voltar")) {
            case 1 ->{

            }
            case 2 ->{
                System.out.println(controladorDoPrograma.getCandidaturas());
                /*
                switch (PAInput.chooseOption("Dados a consultar","Alunos","Propostas", "Voltar")){
                    case 1-> {
                        switch (PAInput.chooseOption("Escolher Filtro para alunos","Com autoproposto","Candidatura Registada", "Sem candidatura Registada","Voltar")){
                        case 1 ->{
                            System.out.println(controladorDoPrograma.getAlunosAutopropostos());
                            break;
                        }
                        case 2 ->{

                        }
                        case 3 ->{

                        }
                        case 4 ->{
                            break;
                        }
                    }
                        break;
                    }
                    case 2 ->{
                        switch (PAInput.chooseOption("Escolher Filtro para Propostas    ","AutoPropostas de alunos","Propostas de Docentes","Propostas com candidaturas", "Propostas sem candidaturas","Voltar")){
                            case 1 ->{
                                System.out.println(controladorDoPrograma.getAlunosAutopropostos());
                                break;
                            }
                            case 2 ->{
                                System.out.println(controladorDoPrograma.getPropostasDocentes());
                            }
                            case 3 ->{
                                //System.out.println(controladorDoPrograma.getPropostasCandidadatos());
                            }
                            case 4 ->{
                                System.out.println(controladorDoPrograma.getPropostasSemCandidatos());
                            }
                            case 5 ->{
                                break;
                            }
                        }
                        break;
                    }
                }
            break;
            }*/
            }
            case 3 ->{
                controladorDoPrograma.lerFicheiro(caminhoDefaultFicheiros + PAInput.readString("Nome do Ficheiro csv", true));

                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();
            }
            case 4 -> {
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> controladorDoPrograma.avancar(true, 0);
                    case 2 -> controladorDoPrograma.avancar(false, 0);
                }
            }
            case 5 -> {
                controladorDoPrograma.avancar(true,0);
            }
            case 6->{
                controladorDoPrograma.voltar(true);
            }
            //default -> acabou = true;
        }
    }

    private void gestaoPropostaUI() {
        System.out.println("Gestão de Propostas:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Ler ficheiro","voltar","Avancar","debug")) {
            case 1 -> {
                String tipo;
                while (!(tipo = PAInput.readString("tipo de proposta",true)).equals("T1") &&
                        !(tipo = PAInput.readString("tipo de proposta",true)).equals("T2") &&
                        !(tipo = PAInput.readString("tipo de proposta",true)).equals("T3")){
                    System.out.println("Tipo invalido T[1-3]");
                }
                /*
                if(tipo.equals("T1")) {
                    Proposta proposta = new T1("ramo", "ola", "1223", "ola");
                    controladorDoPrograma.adicionarProposta(proposta);
                }else if(tipo.equals("T2")){
                    Proposta proposta = new T2("ramo", "ola", "1223", "ola");
                    controladorDoPrograma.adicionarProposta(proposta);
                }else if(tipo.equals("T3")){
                    Proposta proposta = new T3("cod_id","titulo","nr_aluno");
                    controladorDoPrograma.adicionarProposta(proposta);
                }
                */

            }
            case 2 -> System.out.println(controladorDoPrograma.getPropostas());
            case 3 -> {
                if(controladorDoPrograma.lerFicheiro(caminhoDefaultFicheiros + PAInput.readString("Nome do Ficheiro csv", true))){
                    System.out.println("Leu tudo bem");
                }else
                    System.out.println("Nao leu bem");

                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();
            }
            case 4 -> controladorDoPrograma.voltar(false);
            case 5 ->{
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> controladorDoPrograma.avancar(true, 0);
                    case 2 -> controladorDoPrograma.avancar(false, 0);
                }
            }
            case 6->{
                controladorDoPrograma.lerFicheiroDebug("a");
                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();

                System.out.println("A passar de fase");
                controladorDoPrograma.avancar(true,0);
            }
        }
    }

    private void gestaoDocentesUI() {
        System.out.println("Gestão de Docentes:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Ler ficheiro","voltar", "Avancar","debug")) {
            case 1 -> {
                String nome_Docente = PAInput.readString("Nome do aluno",false);
                String email_Docente = PAInput.readString("Email do aluno",false);
                boolean papel_Docente = true;
                switch (PAInput.chooseOption("Papel", "orientador","proponente")){
                    case 1: papel_Docente = true;
                    case 2: papel_Docente = false;
                }
                controladorDoPrograma.adicionarDocente(nome_Docente,email_Docente,papel_Docente);
            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getDocentes());
            }
            case 3 -> {
                if(controladorDoPrograma.lerFicheiro(caminhoDefaultFicheiros + PAInput.readString("Nome do Ficheiro csv", true))){
                    System.out.println("Leu tudo bem");
                }else
                    System.out.println("Nao leu td");

                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();
            }
            case 4 -> {
                controladorDoPrograma.voltar(false);
            }
            case 5 ->{
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> controladorDoPrograma.avancar(true, 0);
                    case 2 -> controladorDoPrograma.avancar(false, 0);
                }
            }
            case 6->{
                controladorDoPrograma.lerFicheiroDebug("a");
                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();

                System.out.println("A passar de fase");
                controladorDoPrograma.avancar(true,0);
            }
        }
    }

    private void gestaoAlunosUI() {
        System.out.println("Gestão de alunos:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","ler de ficheiro","voltar","Avancar","debug")) {
            case 1 -> {
                String nome_Aluno = PAInput.readString("Nome do aluno",false);
                long nr_Aluno = PAInput.readInt("Numero do aluno");
                String email_Aluno = PAInput.readString("Email do aluno",false);
                String ramo_Aluno = PAInput.readString("Ramo do aluno",true);
                double classificacao_Aluno = PAInput.readInt("Classificao do aluno");
                boolean aceder_a_Estagio = true;
                switch (PAInput.chooseOption("Aceder ao Estagio", "PODE","NAO PODE")){
                    case 1: aceder_a_Estagio = true;
                    case 2: aceder_a_Estagio = false;
                }
                if(controladorDoPrograma.adicionarAluno(nr_Aluno,nome_Aluno,email_Aluno,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio)){
                    System.out.println("Aluno adicionado\n");
                }else
                    System.out.println("Aluno ja existe\n");
            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getAlunos());
            }
            case 3 -> {
                if(controladorDoPrograma.lerFicheiro(caminhoDefaultFicheiros + PAInput.readString("Nome do Ficheiro csv", true))){
                    System.out.println("Leu tudo bem");
                }else
                    System.out.println("Nao leu td");

                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();
            }
            case 4 -> {
                controladorDoPrograma.voltar(false);
            }
            case 5 ->{
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> controladorDoPrograma.avancar(true, 0);
                    case 2 -> controladorDoPrograma.avancar(false, 0);
                }
            }
            case 6->{
                switch(PAInput.chooseOption("Quem Utiliza?","Angelo","Rodrigo")) {
                    case 1 ->{
                        controladorDoPrograma.lerFicheiroDebug("a");
                    }
                    case 2 ->{
                        controladorDoPrograma.lerFicheiroDebug("r");
                    }
                }
                    //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                    String errorDisplay = controladorDoPrograma.getErros().toString();
                    //Retirar os [] do print
                    errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                    System.out.println(errorDisplay);
                    //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                    controladorDoPrograma.limparErros();

                    System.out.println("A passar de fase");
                    controladorDoPrograma.avancar(true, 0);

            }
        }
    }

    private void configuracaoUI() {
        System.out.println("---Inicial---\n");
        System.out.println("Bem Vindo\n");
        if(!controladorDoPrograma.getFase_gestao()) {
            controladorDoPrograma.selecionar(PAInput.chooseOption("Gerir:", "Gestao de Alunos", "Gestao de Docentes", "Gestao de Projetos", "Sair"));
        }else
            switch (PAInput.chooseOption("Consulta","Todos","avancar")){
                case 1 -> {
                    System.out.println(controladorDoPrograma.getAlunos());
                    System.out.println(controladorDoPrograma.getPropostas());
                    System.out.println(controladorDoPrograma.getDocentes());
                }
                case 2 -> controladorDoPrograma.avancar(controladorDoPrograma.getFase_gestao(),0);
            }
    }

}
