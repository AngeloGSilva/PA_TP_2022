package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.Utils.PAInput;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;

import java.util.ArrayList;

public class PoeUI {
    ProContexto controladorDoPrograma;
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
        System.out.println("Atribuir Orientador:\n");
        switch (PAInput.chooseOption("Opções:", "Avancar(Apos avancar nao sera possivel voltar atras!!!!)", "Voltar","Atribuir docentes automaticos","Consulta", "Atribuir docentes Manualmente", "Load","Save")) {
            case 1 -> {
                System.out.println("Apos avancar nao sera possivel voltar atras!!!!");
                controladorDoPrograma.avancar(true);
            }
            case 2 ->{
                controladorDoPrograma.voltar(true);
            }
            case 3 ->{
                controladorDoPrograma.atribuirDocentesauto();
            }
            case 4->{
                System.out.println(controladorDoPrograma.getAtribuicoesPropostas());
            }
            case 5->{
                System.out.println(controladorDoPrograma.getAtribuicoesPropostasSemDocente());
                System.out.println(controladorDoPrograma.getDocentes());
                int id_atribuicao = PAInput.readInt("Escolha uma Atribuicao com o docente disponivel para por um docente (Use o Id da atribuicao):");
                String id_docente = PAInput.readString("Escolha um Docente para por numa atribuicao (Use o Email do docente):",true);
                if (controladorDoPrograma.atribuirManualmenteDocente(id_docente,id_atribuicao)){
                    System.out.println(controladorDoPrograma.getAtribuicaoPorId(id_atribuicao));
                }else{
                    System.out.println("Algo correu mal");
                }
            }
            case 6 -> controladorDoPrograma.load();
            case 7 -> controladorDoPrograma.save();
        }
    }

    private void atrPropostaUI() {
        System.out.println("Atribuicao de Propostas:\n");
        if (!controladorDoPrograma.getFase_Proposta()) {
            switch (PAInput.chooseOption("Opções:", "Avancar", "Voltar", "Atribuir automatico Autopropostos e docentes com aluno", "atribuir automatico", "consulta", "atribuir alunos manualmente","Candidaturas", "Load", "Save")) {
                case 1 -> {
                    switch (PAInput.chooseOption("Pretende Fechar a fase?", "Sim", "Nao")) {
                        case 1 -> controladorDoPrograma.avancar(true);
                        case 2 -> controladorDoPrograma.avancar(false);
                    }
                }
                case 2 -> {

                    switch (PAInput.chooseOption("Dados a consultar","Alunos","Propostas", "Voltar")){
                        case 1-> {
                            switch (PAInput.chooseOption("Escolher Filtro para alunos","Auroproposta Associada","Candidatura Registada", "Proposta Atribuida","Sem Prosposta Associada","Voltar")){
                                case 1 ->{
                                    System.out.println(controladorDoPrograma.getAlunosAutopropostosString());
                                }
                                case 2 ->{
                                    System.out.println(controladorDoPrograma.getAlunosComCandidaturaString());
                                }
                                case 3 ->{
                                    System.out.println(controladorDoPrograma.getAlunosPropostaAtribuida());
                                }
                                case 4 ->{
                                    //System.out.print(controladorDoPrograma.getAlunosSemProposta);
                                }case 5 ->{
                                    break;
                                }
                            }
                            break;
                        }
                        case 2 ->{
                            switch (PAInput.chooseOption("Escolher Filtro para Propostas    ","AutoPropostas de alunos","Propostas de Docentes","Propostas com candidaturas", "Propostas sem candidaturas","Voltar")){
                                case 1 ->{
                                    System.out.println(controladorDoPrograma.getAutopropostasAlunos());
                                    break;
                                }
                                case 2 ->{
                                    System.out.println(controladorDoPrograma.getPropostasDocentes());
                                }
                                case 3 ->{
                                    System.out.println(controladorDoPrograma.getPropostasComCandidaturas());
                                }
                                case 4 ->{
                                    System.out.println(controladorDoPrograma.getPropostasSemCandidaturas());
                                }
                                case 5 ->{
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }


                case 3 -> {
                    controladorDoPrograma.atribuiAutopropostos();
                    controladorDoPrograma.atribuiPropostasDocentes();
                }
                case 4 -> {
                    int op = 0;
                    do {
                        ArrayList<String> conflito = controladorDoPrograma.atribuiAutomaticamente();
                        if (conflito != null) {
                            for (int i = 0; i < conflito.size() - 1; i++) {
                                System.out.println(controladorDoPrograma.getAlunoPorNr(conflito.get(i)));
                            }
                            System.out.println(controladorDoPrograma.getPropostaPorID(conflito.get(conflito.size() - 1)));
                            controladorDoPrograma.atribuiAlunoAProposta(PAInput.readString("Escolhe o aluno para a proposta", true), conflito.get(conflito.size() - 1));
                        }else{
                            op = 1;
                        }
                    }while (op==0);
                    controladorDoPrograma.atribuirSemCandidatura();
                }
                case 5 -> {
                    System.out.println(controladorDoPrograma.getAtribuicoesPropostas());
                }
                case 6 -> {
                    System.out.println(controladorDoPrograma.getAlunosSemAtribuicao());
                    System.out.println(controladorDoPrograma.getPropostasNaoAtribuidas());
                    String nr_Aluno = PAInput.readString("Escolha um aluno pelo numero", true);
                    String id_Proposta = PAInput.readString("Escolha uma proposta pelo id", true);
                    if (controladorDoPrograma.atribuirManualmenteAluno(Long.parseLong(nr_Aluno), id_Proposta)) {
                        System.out.println("Funcionou!!!!!!!!!!!!!!"); //trocar pela ultima atribuicao feita para mostrar operacao realizada
                    }else
                        System.out.println("Algo Correu mal.. verifica se aluno pode acerder a estagios ou a projetos");
                }
                case 7 ->{
                    System.out.println("Candidaturas");
                    System.out.println(controladorDoPrograma.getCandidaturas());
                }
                case 8 -> controladorDoPrograma.load();
                case 9 -> controladorDoPrograma.save();
            }
        }else{
            switch (PAInput.chooseOption("Consulta","Todos","avancar","voltar", "Load", "Save")){
                case 1 -> {
                    System.out.println(controladorDoPrograma.getAlunos());
                    System.out.println(controladorDoPrograma.getPropostas());
                    System.out.println(controladorDoPrograma.getDocentes());
                }
                case 2 -> controladorDoPrograma.avancar(controladorDoPrograma.getFase_gestao());
                case 3 -> controladorDoPrograma.voltar(true);
                case 4 -> controladorDoPrograma.load();
                case 5 -> controladorDoPrograma.save();
            }
        }
    }

    private void opCandidaturaUI() {
        System.out.println("Gestão de Candidatura:\n");
        if (controladorDoPrograma.getFase_Candidatura()){
            switch (PAInput.chooseOption("Consulta","Consultar Todos Dados","Avancar","Voltar", "Load","Save")){
                case 1 -> {
                    System.out.println(controladorDoPrograma.getAlunos());
                    System.out.println(controladorDoPrograma.getPropostas());
                    System.out.println(controladorDoPrograma.getDocentes());
                }
                case 2 -> controladorDoPrograma.avancar(controladorDoPrograma.getFase_gestao());
                case 3 -> controladorDoPrograma.voltar(true);
                case 4 -> controladorDoPrograma.load();
                case 5 -> controladorDoPrograma.save();
            }
        }else {
            switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta", "Ler Ficheiro", "Avançar","Load","Save", "Voltar")) {
                case 1 -> {
                    controladorDoPrograma.exportarCandidaturas(PAInput.readString("Nome do Ficheiro csv", true));

                }
                case 2 -> {

                switch (PAInput.chooseOption("Dados a consultar","Alunos","Propostas", "Voltar")){
                    case 1-> {
                        switch (PAInput.chooseOption("Escolher Filtro para alunos","Com autoproposta","Candidatura Registada", "Sem candidatura Registada","Voltar")){
                        case 1 ->{
                            System.out.println(controladorDoPrograma.getAlunosAutopropostosString());
                        }
                        case 2 ->{
                            System.out.println(controladorDoPrograma.getAlunosComCandidaturaString());
                        }
                        case 3 ->{
                            System.out.println(controladorDoPrograma.getAlunosSemCandidaturaString());
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
                                System.out.println(controladorDoPrograma.getAutopropostasAlunos());
                                break;
                            }
                            case 2 ->{
                                System.out.println(controladorDoPrograma.getPropostasDocentes());
                            }
                            case 3 ->{
                                System.out.println(controladorDoPrograma.getPropostasComCandidaturas());
                            }
                            case 4 ->{
                                System.out.println(controladorDoPrograma.getPropostasSemCandidaturas());
                            }
                            case 5 ->{
                                break;
                            }
                        }
                        break;
                    }
                }
            break;
            }
                case 3 -> {
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
                    switch (PAInput.chooseOption("Pretende Fechar a fase?", "Sim", "Nao")) {
                        case 1 -> controladorDoPrograma.avancar(true);
                        case 2 -> controladorDoPrograma.avancar(false);
                    }
                }
                case 5 -> controladorDoPrograma.load();
                case 6 -> controladorDoPrograma.save();
                case 7 -> {
                    controladorDoPrograma.voltar(true);
                }
                //default -> acabou = true;
            }
        }
    }

    private void gestaoPropostaUI() {
        System.out.println("Gestão de Propostas:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Ler ficheiro","voltar","Avancar","debug")) {
            case 1 -> {
                controladorDoPrograma.exportarPropostas(PAInput.readString("Nome do ficheiro para exportar",true));
                /*
                String tipo;
                while (!(tipo = PAInput.readString("tipo de proposta",true)).equals("T1") &&
                        !(tipo = PAInput.readString("tipo de proposta",true)).equals("T2") &&
                        !(tipo = PAInput.readString("tipo de proposta",true)).equals("T3")){
                    System.out.println("Tipo invalido T[1-3]");
                }
                */

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
                controladorDoPrograma.lerFicheiro(caminhoDefaultFicheiros + PAInput.readString("Nome do Ficheiro csv", true));


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
                    case 1 -> controladorDoPrograma.avancar(true);
                    case 2 -> controladorDoPrograma.avancar(false);
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
                controladorDoPrograma.avancar(true);
            }
        }
    }

    private void gestaoDocentesUI() {
        System.out.println("Gestão de Docentes:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Ler ficheiro","voltar", "Avancar","debug")) {
            case 1 -> {
                controladorDoPrograma.exportarDocentes(PAInput.readString("Nome do Ficheiro csv", true));
                /*
                String nome_Docente = PAInput.readString("Nome do aluno",false);
                String email_Docente = PAInput.readString("Email do aluno",false);
                boolean papel_Docente = true;
                switch (PAInput.chooseOption("Papel", "orientador","proponente")){
                    case 1: papel_Docente = true;
                    case 2: papel_Docente = false;
                }
                controladorDoPrograma.adicionarDocente(nome_Docente,email_Docente,papel_Docente);
                */
            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getDocentes());
            }
            case 3 -> {
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
                controladorDoPrograma.voltar(false);
            }
            case 5 ->{
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> controladorDoPrograma.avancar(true);
                    case 2 -> controladorDoPrograma.avancar(false);
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
                controladorDoPrograma.avancar(true);
            }
        }
    }

    private void gestaoAlunosUI() {
        System.out.println("Gestão de alunos:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Ler de ficheiro","voltar","Avancar","debug")) {
            case 1 -> {
                controladorDoPrograma.exportarAlunos(PAInput.readString("Nome do ficheiro para exportar",true));
/*                String nome_Aluno = PAInput.readString("Nome do aluno",false);
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
                    System.out.println("Aluno ja existe\n");*/
            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getAlunos());
            }
            case 3 -> {
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
                controladorDoPrograma.voltar(false);
            }
            case 5 ->{
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> controladorDoPrograma.avancar(true);
                    case 2 -> controladorDoPrograma.avancar(false);
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
                    controladorDoPrograma.avancar(true);

            }
        }
    }

    private void configuracaoUI() {
        System.out.println("---Inicial---\n");
        System.out.println("Bem Vindo\n");
        if (!controladorDoPrograma.getFase_gestao()) {
           switch(PAInput.chooseOption("Gerir:", "Gestao de Alunos", "Gestao de Docentes", "Gestao de Projetos","Load","Save", "Sair")){
               case 1->{
                   controladorDoPrograma.selecionar(1);
               }
               case 2->{
                   controladorDoPrograma.selecionar(2);
               }
               case 3->{
                   controladorDoPrograma.selecionar(3);
               }
               case 4->{
                   controladorDoPrograma.load();
               }
               case 5->{
                   controladorDoPrograma.save();
               }
           }
        } else
            switch (PAInput.chooseOption("Consulta", "Todos", "avancar","Load","Save")) {
                case 1 -> {
                    System.out.println(controladorDoPrograma.getAlunos());
                    System.out.println(controladorDoPrograma.getPropostas());
                    System.out.println(controladorDoPrograma.getDocentes());
                }
                case 2 -> controladorDoPrograma.avancar(controladorDoPrograma.getFase_gestao());
                case 3 -> controladorDoPrograma.load();
                case 4 -> controladorDoPrograma.save();
            }
    }

}
