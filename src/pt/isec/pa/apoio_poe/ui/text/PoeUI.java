package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.Utils.PAInput;
import pt.isec.pa.apoio_poe.model.fsm.PoeState;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;

import java.util.Locale;

/**
 * Class onde é feita toda a interface de texto
 */
public class PoeUI {
    ProContexto controladorDoPrograma;
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
        System.out.println("A criar um ficheiro de resumo...");
        controladorDoPrograma.exportarInfoFinal(PAInput.readString("Nome do Ficheiro [.csv] ", true));
        System.out.println("--Alunos com proposta atribuida--");
        System.out.println(controladorDoPrograma.getAlunosPropostaAtribuida());
        System.out.println("--Alunos sem proposta atribuida--");
        System.out.println(controladorDoPrograma.getAlunosSemPropostasComCadidaturaNaoAtribuida());
        System.out.println("--Propostas Disponiveis--");
        System.out.println(controladorDoPrograma.getPropostasDisponiveis());
        System.out.println("--Propostas Atribuidas--");
        System.out.println(controladorDoPrograma.getPropostasAtribuidas());
        System.out.println("--Info Docentes--");
        System.out.println(controladorDoPrograma.getNumerodeOrientacoes());

        if(PAInput.readString("[Sair para sair]:",true).equals("sair"))
            System.exit(0);
    }

    private void atrOrientadorUI() {
        System.out.println("Atribuir Orientador:\n");
        switch (PAInput.chooseOption("Opções:","Atribuir Docentes Automaticos","Atribuir Docentes Manualmente","Consulta","Eliminar Atribuição de docente","Undo","Redo", "Load","Save","Avancar", "Voltar")) {
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
                switch (PAInput.chooseOption("Dados a consultar","Lista de estudantes com proposta atribuída e com orientador associado","Lista de estudantes com proposta atribuída mas sem orientador associado","Número de orientações por docente, em média, mínimo, máximo, e por docente especificado","Todos" , "Voltar")){
                    case 1->{
                        System.out.println(controladorDoPrograma.getAlunosComOrientador());
                    }
                    case 2->{
                        System.out.println(controladorDoPrograma.getAlunosSemOrientador());
                    }
                    case 3->{
                        System.out.println(controladorDoPrograma.getNumerodeOrientacoes());
                    }
                    case 4->{
                        System.out.println(controladorDoPrograma.getAtribuicoesPrintEasy());
                    }
                }
            }case 4->{
                if(controladorDoPrograma.removeDocenteAtribuido((int) PAInput.readNumber("Id da proposta a remover o docente:"))){
                    System.out.println("Removi com sucesso!\n");
                }else
                    System.out.println("Remover falhou!\n");
            }
            case 5->{
                /*TODO*/

            }case 6->{
                /*TODO*/
            }

            case 7-> {
                controladorDoPrograma.load();
            }
            case 8->{
                controladorDoPrograma.save();
            }
            case 9 -> controladorDoPrograma.avancar(true);
            case 10 -> controladorDoPrograma.voltar(true);
        }
    }

    private void atrPropostaUI() {
        System.out.println("Atribuicao de Propostas:\n");
        if (!controladorDoPrograma.getFase_Proposta() && controladorDoPrograma.getFase_Candidatura()) {
            if (controladorDoPrograma.isConflitoON()){
                //para nao parar num conflito
                controladorDoPrograma.AtribuirAutomaticamente();
            }else{
            switch (PAInput.chooseOption("Opções:", "Atribuir automatico Autopropostos e docentes com aluno", "Atribuir Automatico(Pode existir conflitos)", "Atribuir Alunos Manualmente", "Consulta","Eliminar Atribuições","Undo","Redo", "Load", "Save", "avancar", "voltar")) {

                    case 1 -> {
                        controladorDoPrograma.AtribuirAutomaticoAutopropostosDocentesAluno();
                    }
                    case 2 -> {
                        controladorDoPrograma.AtribuirAutomaticamente();
                    }
                    case 3 -> {
                        System.out.println(controladorDoPrograma.getAlunosSemAtribuicao());
                        System.out.println(controladorDoPrograma.getPropostasNaoAtribuidas());
                        //reformular
                        String nr_Aluno = PAInput.readString("Escolha um aluno pelo numero: ", true);
                        String id_Proposta = PAInput.readString("Escolha uma proposta pelo id: ", true);
                        if (controladorDoPrograma.atribuirManualmenteAluno(Long.parseLong(nr_Aluno), id_Proposta.toUpperCase(Locale.ROOT))) {

                            System.out.println("Correu bem a Atribuicao"); //trocar pela ultima atribuicao feita para mostrar operacao realizada
                        } else
                            System.out.println("Algo Correu mal!\n");
                    }
                    case 4 -> {
                        switch (PAInput.chooseOption("Dados a consultar", "Alunos", "Propostas","Todos", "Voltar")) {
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

                            }case 3->{
                                System.out.println(controladorDoPrograma.getAtribuicoesPrintEasy());

                            }case 4->{break;}
                        }
                    }
                    case 5 ->{//So vai eliminar Atribuições de T1's basicamente, ja que se nao elimina T3 nem T2 com aluno, so resta T1 com aluno.
                            System.out.print(controladorDoPrograma.getAtribuicoes());
                            switch(PAInput.chooseOption("Eliminar:","Atribuição","Todas Atribuições")){
                                case 1->{
                                    if(controladorDoPrograma.removeAtribuicao(PAInput.readString("Numero de aluno da atribuição a eliminar:",true))){
                                        System.out.println("Removido com sucesso!\n");
                                    }else
                                        System.out.println("Erro ao eliminar Atribuição!\n");
                                }
                                case 2->{
                                    int aux_int = controladorDoPrograma.removerAllAtribuicao();
                                    if(aux_int >0){
                                        System.out.println("Eliminei" + aux_int + "Atribuições");
                                    }else
                                        System.out.println("Erro");
                                }
                            }
                    }
                    case 6->{//undo
                        /*TODO*/
                    }
                    case 7 ->{//redo
                        /*TODO*/
                    }
                    case 8 -> controladorDoPrograma.load();

                    case 9 -> controladorDoPrograma.save();

                    case 10 -> {
                        switch (PAInput.chooseOption("Pretende Fechar a fase?", "Sim", "Nao")) {
                            case 1 -> {
                                if (!controladorDoPrograma.avancar(true)){
                                    System.out.println("Todos os alunos com candidaturas submetidas tem de possuir projeto atribuído.\n");
                                }
                            }
                            case 2 -> controladorDoPrograma.avancar(false);
                        }

                    }

                    case 11 -> controladorDoPrograma.voltar(true);
                }
            }
            //So consulta(Fase fechada)
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
            switch (PAInput.chooseOption("Opções:", "Exportar para um ficheiro", "Consulta", "Ler Ficheiro","Adicionar","Editar","Eliminar","Load","Save", "Avançar", "Voltar")) {
                case 1 -> {
                    controladorDoPrograma.exportarCandidaturas(PAInput.readString("Nome do Ficheiro csv ", true));
                }
                case 2 -> {

                switch (PAInput.chooseOption("Dados a consultar","Alunos","Propostas","Todos", "Voltar")){
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
                    case 3->{
                            System.out.println(controladorDoPrograma.getCandidaturasPrintEasy().toString());
                    }case 4->{
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
                case 4-> {//100%
                    if(controladorDoPrograma.adicionarCandidatura(PAInput.readString("Numero de aluno:",true),PAInput.readString("Codigo da proposta:",true))){
                        System.out.println("Candidatura criada com sucesso!\n");
                    }else
                        System.out.println("Erro ao criar!\n");

                }
                case 5-> {//Editar
                    switch(PAInput.chooseOption("Adicionar ou remover proposta:","Adicionar","Remover")){
                        case 1->{ // Adicionar com 1 proposta é sempre com 1 proposta, aqui podem se adicioanr mais propostas
                            if(controladorDoPrograma.adicionarPropostaACandidatura(PAInput.readString("Numero de aluno:",true),PAInput.readString("Codigo da proposta:",true))){
                                System.out.println("Adicionado Com Sucesso!\n");
                            }else
                                System.out.println("Erro ao adicionar!\n");
                        }
                        case 2->{
                            if(controladorDoPrograma.removerPropostaDeCandidatura(PAInput.readString("Numero de aluno:",true),PAInput.readString("Codigo da proposta:",true))){
                                System.out.println("Removida Com Sucesso!\n");
                            }else
                                System.out.println("Eliminar falhou!\n");
                        }
                        default -> {
                            break;
                        }
                    }

                }
                case 6-> {//100%
                    if(controladorDoPrograma.remover(PAInput.readString("Numero do aluno da Candidatura a eliminar:",true)))
                        System.out.println("Eliminado Com Sucesso!\n");
                    else
                        System.out.println("Eliminar falhou,não é permitido eliminar [T2][T3]!\n");
                }
                case 7 -> controladorDoPrograma.load();
                case 8 -> controladorDoPrograma.save();
                case 9 -> {
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
                case 10 -> {controladorDoPrograma.voltar(true);}
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
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                //limpar o array dos erros para nao mostrar informacoes de outros ficheiros na proxima leitura
                controladorDoPrograma.limparErros();
            }
            case 4 ->{
                String tipoaux;
                switch(tipoaux = PAInput.readString("Tipo:\n[T1 - Estágio]\n[T2 - Projeto]\n[T3 - Estágio/projeto autoproposto]\n Opção:",true)){
                 case "T1" ->{ //T1 com aluno associado
                     if(PAInput.readString("Aluno Associado?[s/n]",true).equals("s")){
                        if(controladorDoPrograma.adicionarProposta(tipoaux,PAInput.readString("Codigo Id:",true),PAInput.readString("Titulo:",true),Long.parseLong(PAInput.readString("Numero do aluno associado:",true)),null,PAInput.readString("Ramo:",true),PAInput.readString("Empresa:",false)))
                            System.out.println("Adicionado com sucesso proposta do tipo T1 com aluno associado");
                        else
                            System.out.println("Erro em algum parametro[T1 com aluno associado]");
                     }else{ //T1 sem aluno associado
                         if(controladorDoPrograma.adicionarProposta(tipoaux,PAInput.readString("Codigo Id:",true),PAInput.readString("Titulo:",true),null,null,PAInput.readString("Ramo:",true),PAInput.readString("Empresa:",false)))
                             System.out.println("Adicionado com sucesso proposta do tipo T1 sem aluno associado");
                         else
                             System.out.println("Erro em algum parametro[T1 sem aluno associado]");
                     }
                 }
                 case "T2" ->{//definir docente como preponente
                     if(PAInput.readString("Aluno Associado?[s/n]",true).equals("s")){
                         if(controladorDoPrograma.adicionarProposta(tipoaux,PAInput.readString("Codigo Id:",true),PAInput.readString("Titulo:",true),Long.parseLong(PAInput.readString("Numero do aluno associado:",true)),PAInput.readString("Email docente responsável:",true),PAInput.readString("Ramo:",true),null))
                             System.out.println("Adicionado com sucesso proposta do tipo T2 com aluno associado");
                         else
                             System.out.println("Erro em algum parametro[T2 com aluno associado]");
                     }else{ //T2 sem aluno associado
                         if(controladorDoPrograma.adicionarProposta(tipoaux,PAInput.readString("Codigo Id:",true),PAInput.readString("Titulo:",true),null,PAInput.readString("Email docente responsável:",true),PAInput.readString("Ramo:",true),null))
                             System.out.println("Adicionado com sucesso proposta do tipo T2 sem aluno associado");
                         else
                             System.out.println("Erro em algum parametro[T2 sem aluno associado]");
                     }

                 }
                 case "T3" ->{
                     if(controladorDoPrograma.adicionarProposta(tipoaux,PAInput.readString("Codigo Id:",true),PAInput.readString("Titulo:",true),Long.parseLong(PAInput.readString("Numero do aluno associado:",true)),null,null,null))
                         System.out.println("Adicionado com sucesso proposta do tipo T3 ");
                     else
                         System.out.println("Erro em algum parametro [T3]");
                 }
                }
            }
            case 5 ->{
                controladorDoPrograma.editar(PAInput.readString("Id da proposta:",true),PAInput.readString("Edição [titulo]:",false));

            }
            case 6 ->{
                if(controladorDoPrograma.remover(PAInput.readString("Id da proposta:",true))){
                    System.out.println("Proposta removida com sucesso\n");
                }else
                    System.out.println("Proposta não removida\n");
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
                String errorDisplay = controladorDoPrograma.getErros().toString();
                //Retirar os [] do print
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                controladorDoPrograma.limparErros();
            }
            case 4 ->{
                if(controladorDoPrograma.adicionarDocente(PAInput.readString("Nome:",false),PAInput.readString("Email:",true),false)){
                    System.out.println("Docente adicionado com sucesso!\n");
                }else
                    System.out.println("Docente nao adicionado!\n");
            }
            case 5 ->{
                //A realizar
                controladorDoPrograma.editar(PAInput.readString("Email do docente:",true),PAInput.readString("Edição [nome]:",false));
            }
            case 6 ->{
                if(controladorDoPrograma.remover(PAInput.readString("Email do Docente:",true))){
                    System.out.println("Docente removido com sucesso!\n");
                }else
                    System.out.println("Docente nao removido!\n");
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
        switch (PAInput.chooseOption("Opções:", "Exportar para um ficheiro", "Consulta","Ler de ficheiro","Adicionar","Editar","Eliminar","Avancar","Voltar")) {
            case 1 -> {
                controladorDoPrograma.exportarAlunos(PAInput.readString("Nome do ficheiro para exportar ",true));
            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getAlunos());
            }
            case 3 -> {

                controladorDoPrograma.lerFicheiro(PAInput.readString("Nome do Ficheiro csv ", true));
                String errorDisplay = controladorDoPrograma.getErros().toString();
                errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
                System.out.println(errorDisplay);
                controladorDoPrograma.limparErros();

            }
            case 4 ->{
                if(controladorDoPrograma.adicionarAluno(PAInput.readString("Numero:",true),PAInput.readString("Nome Aluno(Primeiro e Ultimo Obrigatorios):",false),PAInput.readString("email:",true),PAInput.readString("Ramo:",true),PAInput.readNumber("classificacao: "),true,PAInput.readString("curso:",true))){
                    System.out.println("Aluno adicionado com sucesso");
                }else
                    System.out.println("Aluno adicionado com sucesso");
            }
            case 5 ->{
                controladorDoPrograma.editar(PAInput.readString("Numero do aluno a editar:",true),PAInput.readString("Edição [nome]:",false));
            }
            case 6 ->{
                if(controladorDoPrograma.remover(PAInput.readString("Numero de aluno:",true))){
                System.out.println("Aluno removido com sucesso");
            }else
                System.out.println("Aluno removido com sucesso");
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
