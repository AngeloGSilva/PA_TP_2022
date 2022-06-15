package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.Utils.PAInput;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;

import java.util.Locale;

public class PoeUI {
    ProContexto controladorDoPrograma;
    //private String caminhoDefaultFicheiros = "\\\\Resources\\\\ficheiros\\\\";
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
                case CONFLITO -> conflitoUI();
                case ATRIBUIR_ORIENTADOR -> atrOrientadorUI();
                case CONSULTA -> consultaUI();
            }
        }
    }

    private void conflitoUI() {
        System.out.println(controladorDoPrograma.getConflitos());
        System.out.println(controladorDoPrograma.getPropostaConflito());
        System.out.println();
        controladorDoPrograma.resolverConflito(Integer.parseInt(PAInput.readString("Escolhe o aluno para a proposta de ID "+controladorDoPrograma.getPropostaConflito().getCod_ID()+": ", true)));
    }

    private void consultaUI() {
        System.out.println(controladorDoPrograma.getAlunosPropostaAtribuida());
        System.out.println(controladorDoPrograma.getAlunosSemPropostasComCadidaturaNaoAtribuida());
        System.out.println(controladorDoPrograma.getPropostasDisponiveis());
        System.out.println(controladorDoPrograma.getPropostasAtribuidas());
        System.out.println(controladorDoPrograma.getNumerodeOrientacoes());

        //controladorDoPrograma.voltar(controladorDoPrograma.getFase_Orientador());
        if(PAInput.readString("[Sair para sair]:",true).equals("sair"))
            System.exit(0);
        //PAInput.readNumber("poe numero qualquer .... so para parar o programa e dar debug");
    }

    private void atrOrientadorUI() {
        System.out.println("Atribuir Orientador:\n");
        switch (PAInput.chooseOption("Opções:","Atribuir Docentes Automaticos","Atribuir Docentes Manualmente","Consulta", "Load","Save","Avancar", "Voltar")) {
            case 1 -> {
                controladorDoPrograma.atribuirDocentesauto();
            }
            case 2 ->{
                System.out.println(controladorDoPrograma.getAtribuicoesPropostasSemDocente());
                System.out.println(controladorDoPrograma.getDocentes());
                int id_atribuicao = PAInput.readInt("Escolha uma Atribuicao com o docente disponivel para por um docente (Use o Id da atribuicao): ");
                String id_docente = PAInput.readString("Escolha um Docente para por numa atribuicao (Use o Email do docente): ",true);
                if (controladorDoPrograma.atribuirManualmenteDocente(id_docente.toLowerCase(Locale.ROOT),id_atribuicao)){
                    System.out.println(controladorDoPrograma.getAtribuicaoPorId(id_atribuicao));
                }else{
                    System.out.println("Algo correu mal!! Verificar ids e email");
                }
            }
            case 3 ->{
                switch (PAInput.chooseOption("Dados a consultar","Lista de estudantes com proposta atribuída e com orientador associado","Lista de estudantes com proposta atribuída mas sem orientador associado","Número de orientações por docente, em média, mínimo, máximo, e por docente especificado" , "Voltar")){
                    case 1->{
                        System.out.println(controladorDoPrograma.getAlunosComOrientador());
                    }
                    case 2->{
                        System.out.println(controladorDoPrograma.getAlunosSemOrientador());
                    }
                    case 3->{
                        System.out.println(controladorDoPrograma.getNumerodeOrientacoes());
                    }
                }
            }

            case 4-> {
                controladorDoPrograma.load();
            }
            case 5->{
                controladorDoPrograma.save();
            }
            case 6 -> controladorDoPrograma.avancar(true);
            case 7 -> controladorDoPrograma.voltar(true);
        }
    }

    private void atrPropostaUI() {
        System.out.println("Atribuicao de Propostas:\n");
        if (!controladorDoPrograma.getFase_Proposta() && controladorDoPrograma.getFase_Candidatura()) {
            if (controladorDoPrograma.isConflitoON()){
                controladorDoPrograma.AtribuirAutomaticamente();
            }else{
            switch (PAInput.chooseOption("Opções:", "Atribuir automatico Autopropostos e docentes com aluno", "Atribuir Automatico(Pode existir conflitos)", "Atribuir Alunos Manualmente", "Consulta", "Load", "Save", "avancar", "voltar")) {
                    //(PAInput.chooseOption("Opções:", "Atribuir automatico Autopropostos e docentes com aluno","Atribuir automatico Autopropostos e docentes com aluno", "Atribuir Automatico(Pode existir conflitos)","Atribuir Alunos Manualmente", "Consulta","Candidaturas","Load","Save" ,"avancar","voltar")

                    case 1 -> {
                        controladorDoPrograma.AtribuirAutomaticoAutopropostosDocentesAluno();
                        //controladorDoPrograma.atribuiAutopropostos();
                        //controladorDoPrograma.atribuiPropostasDocentes();
                        //controladorDoPrograma.AtribuirAutomaticamente();

                    }
                    case 2 -> {
                        controladorDoPrograma.AtribuirAutomaticamente();
                        /*int op = 0;
                        do {
                            ArrayList<String> conflito = controladorDoPrograma.atribuiAutomaticamente();
                            if (conflito != null) {
                                for (int i = 0; i < conflito.size() - 1; i++) {
                                    System.out.println(controladorDoPrograma.getAlunoPorNr(conflito.get(i)));
                                }
                                System.out.println(controladorDoPrograma.getPropostaPorID(conflito.get(conflito.size() - 1)));
                                controladorDoPrograma.atribuiAlunoAProposta(PAInput.readString("Escolhe o aluno para a proposta: ", true), conflito.get(conflito.size() - 1));
                            } else {
                                op = 1;
                            }
                        } while (op == 0);
                        controladorDoPrograma.atribuirSemCandidatura();*/
                    }
                    case 3 -> {
                        System.out.println(controladorDoPrograma.getAlunosSemAtribuicao());
                        System.out.println(controladorDoPrograma.getPropostasNaoAtribuidas());
                        String nr_Aluno = PAInput.readString("Escolha um aluno pelo numero: ", true);
                        String id_Proposta = PAInput.readString("Escolha uma proposta pelo id: ", true);
                        if (controladorDoPrograma.atribuirManualmenteAluno(Long.parseLong(nr_Aluno), id_Proposta.toUpperCase(Locale.ROOT))) {
                            System.out.println("Correu bem a Atribuicao"); //trocar pela ultima atribuicao feita para mostrar operacao realizada
                        } else
                            System.out.println("Algo Correu mal.. verifica se aluno pode acerder a estagios ou a projetos");
                    }
                    case 4 -> {
                        switch (PAInput.chooseOption("Dados a consultar", "Alunos", "Propostas", "Voltar")) {
                            case 1 -> {
                                switch (PAInput.chooseOption("Escolher Filtro para alunos", "Autoproposta Associada", "Candidatura Registada", "Proposta Atribuida", "Sem Prosposta Associada", "Voltar")) {
                                    case 1 -> {
                                        System.out.println(controladorDoPrograma.getAlunosAutopropostosString());
                                    }
                                    case 2 -> {
                                        System.out.println(controladorDoPrograma.getAlunosComCandidaturaString());
                                    }
                                    case 3 -> {
                                        System.out.println(controladorDoPrograma.getAlunosPropostaAtribuida());
                                    }
                                    case 4 -> {
                                        System.out.print(controladorDoPrograma.getAlunosSemProposta());
                                    }
                                    case 5 -> {
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2 -> {
                                switch (PAInput.chooseOption("Escolher Filtro para Propostas", "AutoPropostas de alunos", "Propostas de Docentes", "Propostas Disponiveis", "Propostas Atribuidas", "Voltar")) {
                                    case 1 -> {
                                        System.out.println(controladorDoPrograma.getAutopropostasAlunos());
                                    }
                                    case 2 -> {
                                        System.out.println(controladorDoPrograma.getPropostasDocentes());
                                    }
                                    case 3 -> {
                                        System.out.println(controladorDoPrograma.getPropostasDisponiveis());
                                    }
                                    case 4 -> {
                                        System.out.println(controladorDoPrograma.getPropostasAtribuidas());
                                    }
                                    case 5 -> {
                                        break;
                                    }
                                }

                            }
                        }
                    }
                    case 5 -> controladorDoPrograma.load();

                    case 6 -> controladorDoPrograma.save();

                    case 7 -> {
                        switch (PAInput.chooseOption("Pretende Fechar a fase?", "Sim", "Nao")) {
                            case 1 -> {
                                if (!controladorDoPrograma.avancar(true)){
                                    System.out.println("Todos os alunos com candidaturas submetidas tem de possuir projeto atribuído.\n");
                                }
                            }
                            case 2 -> controladorDoPrograma.avancar(false);
                        }

                    }

                    case 8 -> controladorDoPrograma.voltar(true);
                }}
            } else if (controladorDoPrograma.getFase_Proposta()){
                switch (PAInput.chooseOption("Consulta", "Consultar Todos Dados", "Avancar", "Voltar", "Load", "Save")) {
                    case 1 -> {
                        switch (PAInput.chooseOption("Dados a consultar", "Alunos", "Propostas", "Voltar")) {
                            case 1 -> {
                                switch (PAInput.chooseOption("Escolher Filtro para alunos", "Auroproposta Associada", "Candidatura Registada", "Proposta Atribuida", "Sem Prosposta Associada", "Voltar")) {
                                    case 1 -> {
                                        System.out.println(controladorDoPrograma.getAlunosAutopropostosString());
                                    }
                                    case 2 -> {
                                        System.out.println(controladorDoPrograma.getAlunosComCandidaturaString());
                                    }
                                    case 3 -> {
                                        System.out.println(controladorDoPrograma.getAlunosPropostaAtribuida());
                                    }
                                    case 4 -> {
                                        System.out.print(controladorDoPrograma.getAlunosSemProposta());
                                    }
                                    case 5 -> {
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2 -> {
                                switch (PAInput.chooseOption("Escolher Filtro para Propostas", "AutoPropostas de alunos", "Propostas de Docentes", "Propostas Disponiveis", "Propostas Atribuidas", "Voltar")) {
                                    case 1 -> {
                                        System.out.println(controladorDoPrograma.getAutopropostasAlunos());
                                    }
                                    case 2 -> {
                                        System.out.println(controladorDoPrograma.getPropostasDocentes());
                                    }
                                    case 3 -> {
                                        System.out.println(controladorDoPrograma.getPropostasDisponiveis());
                                    }
                                    case 4 -> {
                                        System.out.println(controladorDoPrograma.getPropostasAtribuidas());
                                    }
                                    case 5 -> {
                                        break;
                                    }
                                }

                            }
                        }
                    }
                    case 2 -> controladorDoPrograma.avancar(true);
                    case 3 -> controladorDoPrograma.voltar(true);
                    case 4 -> controladorDoPrograma.load();
                    case 5 -> controladorDoPrograma.save();
                }
            }else if (!controladorDoPrograma.getFase_Candidatura() && !controladorDoPrograma.getFase_Proposta()){
            switch (PAInput.chooseOption("Opções:", "Atribuir automatico Autopropostos e docentes com aluno" ,"Consulta", "Load", "Save", "avancar", "voltar")) {
                case 1 -> {
                    controladorDoPrograma.AtribuirAutomaticoAutopropostosDocentesAluno();
                }

                case 2 -> {
                    switch (PAInput.chooseOption("Dados a consultar", "Alunos", "Propostas", "Voltar")) {
                        case 1 -> {
                            switch (PAInput.chooseOption("Escolher Filtro para alunos", "Auroproposta Associada", "Candidatura Registada", "Proposta Atribuida", "Sem Prosposta Associada", "Voltar")) {
                                case 1 -> {
                                    System.out.println(controladorDoPrograma.getAlunosAutopropostosString());
                                }
                                case 2 -> {
                                    System.out.println(controladorDoPrograma.getAlunosComCandidaturaString());
                                }
                                case 3 -> {
                                    System.out.println(controladorDoPrograma.getAlunosPropostaAtribuida());
                                }
                                case 4 -> {
                                    System.out.print(controladorDoPrograma.getAlunosSemProposta());
                                }
                                case 5 -> {
                                    break;
                                }
                            }
                            break;
                        }
                        case 2 -> {
                            switch (PAInput.chooseOption("Escolher Filtro para Propostas", "AutoPropostas de alunos", "Propostas de Docentes", "Propostas Disponiveis", "Propostas Atribuidas", "Voltar")) {
                                case 1 -> {
                                    System.out.println(controladorDoPrograma.getAutopropostasAlunos());
                                }
                                case 2 -> {
                                    System.out.println(controladorDoPrograma.getPropostasDocentes());
                                }
                                case 3 -> {
                                    System.out.println(controladorDoPrograma.getPropostasDisponiveis());
                                }
                                case 4 -> {
                                    System.out.println(controladorDoPrograma.getPropostasAtribuidas());
                                }
                                case 5 -> {
                                    break;
                                }
                            }

                        }
                    }
                }
                case 3 -> controladorDoPrograma.load();

                case 4 -> controladorDoPrograma.save();

                case 5 -> {
                    switch (PAInput.chooseOption("Pretende Fechar a fase?", "Sim", "Nao")) {
                        case 1 -> {
                            if (!controladorDoPrograma.avancar(true)){
                                System.out.println("Todos os alunos com candidaturas submetidas tem de possuir projeto atribuído.\n");
                            }
                        }
                        case 2 -> controladorDoPrograma.avancar(false);
                    }
                }
                case 6 -> controladorDoPrograma.voltar(true);
            }
        }
    }

    private void opCandidaturaUI() {
        System.out.println("Gestão de Candidatura:\n");
        if (controladorDoPrograma.getFase_Candidatura()){
            switch (PAInput.chooseOption("Consulta","Consultar Todos Dados", "Load","Save","Avancar","Voltar")){
                case 1 -> {
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
                            switch (PAInput.chooseOption("Escolher Filtro para Propostas","AutoPropostas de alunos","Propostas de Docentes","Propostas com candidaturas", "Propostas sem candidaturas","Voltar")){
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
                }
                case 2 -> controladorDoPrograma.load();
                case 3 -> controladorDoPrograma.save();
                case 4 -> controladorDoPrograma.avancar(true);
                case 5 -> controladorDoPrograma.voltar(true);
            }
        }else {
            switch (PAInput.chooseOption("Opções:", "Exportar para um ficheiro", "Consulta", "Ler Ficheiro","Load","Save", "Avançar", "Voltar")) {
                case 1 -> {
                    controladorDoPrograma.exportarCandidaturas(PAInput.readString("Nome do Ficheiro csv ", true));
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

            }
                case 3 -> {
                    controladorDoPrograma.lerFicheiro(PAInput.readString("Nome do Ficheiro csv", true));

                    //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                    String errorDisplay = controladorDoPrograma.getErros().toString();
                    //Retirar os [] do print
                    errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                    System.out.println(errorDisplay);
                    //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                    controladorDoPrograma.limparErros();
                }
                case 4 -> {controladorDoPrograma.load();
                }
                case 5 -> controladorDoPrograma.save();
                case 6 -> {
                    switch (PAInput.chooseOption("Pretende Fechar a fase?", "Sim", "Nao")) {
                        case 1 -> {
                            if (!controladorDoPrograma.avancar(true)) {
                                System.out.println("Nao fechou a  fase anterior!");
                            }else{
                                System.out.println("Fase fechada!");
                            }

                        }
                        case 2 -> controladorDoPrograma.avancar(false);
                    }
                }
                case 7 -> {controladorDoPrograma.voltar(true);}
            }
        }
    }

    private void gestaoPropostaUI() {
        System.out.println("Gestão de Propostas:\n");
        switch (PAInput.chooseOption("Opções:", "Exportar para um ficheiro", "Consulta","Ler ficheiro","Adicionar","Editar","Eliminar","Avancar","Voltar")) {
            case 1 -> {
                controladorDoPrograma.exportarPropostas(PAInput.readString("Nome do ficheiro para exportar ",true));


            }
            case 2 -> System.out.println(controladorDoPrograma.getPropostas());
            case 3 -> {
                controladorDoPrograma.lerFicheiro(PAInput.readString("Nome do Ficheiro csv", true));
                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();
            }
            case 4 ->{
                //controladorDoPrograma.adicionarProposta(PAInput.readString("Tipo[T1][T2][T3]:",true),);
            }
            case 5 ->{

            }
            case 6 ->{
                controladorDoPrograma.remover(PAInput.readString("Id da proposta:",true));
            }
            case 7 -> {
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> {
                        if (!controladorDoPrograma.avancar(true)) {
                            System.out.println("Numero de propostas inferior ao numero de alunos,fase nao fechada\n");
                        }else
                            System.out.println("Fase Fechada\n");
                    }
                    case 2 -> controladorDoPrograma.avancar(false);
                }

            }
            case 8 ->   controladorDoPrograma.voltar(false);
        }
    }

    private void gestaoDocentesUI() {
        System.out.println("Gestão de Docentes:\n");
        switch (PAInput.chooseOption("Opções:", "Exportar para ficheiro", "Consulta","Ler ficheiro","Adicionar","Editar","Eliminar","Avancar", "Voltar")) {
            case 1 -> {
                controladorDoPrograma.exportarDocentes(PAInput.readString("Nome do Ficheiro csv ", true));

            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getDocentes());
            }
            case 3 -> {
                controladorDoPrograma.lerFicheiro(PAInput.readString("Nome do Ficheiro csv ", true));
                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();
            }
            case 4 ->{
                controladorDoPrograma.adicionarDocente(PAInput.readString("Nome:",false),PAInput.readString("Email:",true),false);
            }
            case 5 ->{
                //controladorDoPrograma.editar();
            }
            case 6 ->{
                controladorDoPrograma.remover(PAInput.readString("Email do Docente:",true));
            }
            case 7 -> {
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> {
                        if (!controladorDoPrograma.avancar(true)) {
                            System.out.println("Numero de propostas inferior ao numero de alunos,fase nao fechada\n");
                        }else
                            System.out.println("Fase Fechada\n");
                    }
                    case 2 -> controladorDoPrograma.avancar(false);
                }
            }
            case 8 ->{
                controladorDoPrograma.voltar(false);
            }
        }
    }

    private void gestaoAlunosUI() {
        System.out.println("Gestão de alunos:\n");
        switch (PAInput.chooseOption("Opções:", "Exportar para um ficheiro", "Consulta","Ler de ficheiro","Adicionar","Editar","Eliminar","Avancar","Voltar","[Nao usar][debug]")) {
            case 1 -> {
                controladorDoPrograma.exportarAlunos(PAInput.readString("Nome do ficheiro para exportar ",true));
            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getAlunos());
            }
            case 3 -> {
                //controladorDoPrograma.removerAluno(Long.parseLong(PAInput.readString("Numero:",true)));

                controladorDoPrograma.lerFicheiro(PAInput.readString("Nome do Ficheiro csv ", true));
                //Mostrar quais linhas nao foram lidas por alguma razao nos ficheiros
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();

                 
            }
            case 4 ->{
                controladorDoPrograma.adicionarAluno(PAInput.readString("Numero:",true),PAInput.readString("Nome Aluno(Primeiro e Ultimo Obrigatorios):",false),PAInput.readString("email:",true),PAInput.readString("Ramo:",true),PAInput.readNumber("classificacao: "),true,PAInput.readString("curso:",true));
            }
            case 5 ->{
                //controladorDoPrograma.editar();
            }
            case 6 ->{
                controladorDoPrograma.remover(PAInput.readString("Numero de aluno:",true));
            }
            case 7 -> {
                switch (PAInput.chooseOption("Pretende Fechar a fase?","Sim","Nao")){
                    case 1 -> {
                        if (!controladorDoPrograma.avancar(true)) {
                            System.out.println("Numero de propostas inferior ao numero de alunos,fase nao fechada\n");
                        }else
                            System.out.println("Fase Fechada\n");
                    }
                    case 2 -> controladorDoPrograma.avancar(false);
                }
            }
            case 8 ->{
                controladorDoPrograma.voltar(false);
            }
            case 9->{
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
           switch(PAInput.chooseOption("Gerir:", "Gestao de Alunos", "Gestao de Docentes", "Gestao de Projetos","Load","Save")){
               case 1->{
                   controladorDoPrograma.selecionar(PoeState.GESTAO_ALUNO);
               }
               case 2->{
                   controladorDoPrograma.selecionar(PoeState.GESTAO_DOCENTE);
               }
               case 3->{
                   controladorDoPrograma.selecionar(PoeState.GESTAO_PROPOSTA);
               }
               case 4->{
                   controladorDoPrograma.load();
               }
               case 5->{
                   controladorDoPrograma.save();
               }
           }

        } else
            switch (PAInput.chooseOption("Consulta", "Consulta","Load","Save","Avancar")) {
                case 1 -> {
                    switch(PAInput.chooseOption("Dados:", "Alunos", "Docentes", "Propostas", "voltar")){
                        case 1->{
                            System.out.println(controladorDoPrograma.getAlunos());
                        }
                        case 2->{
                            System.out.println(controladorDoPrograma.getDocentes());
                        }
                        case 3->{
                            System.out.println(controladorDoPrograma.getPropostas());
                        }
                        case 4->{
                            break;
                        }
                    }
                }
                case 2 -> controladorDoPrograma.load();
                case 3 ->  controladorDoPrograma.save();
                case 4 -> controladorDoPrograma.avancar(controladorDoPrograma.getFase_gestao());
            }
    }

}
