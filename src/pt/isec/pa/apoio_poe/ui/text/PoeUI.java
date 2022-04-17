package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.Utils.PAInput;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.T1;
import pt.isec.pa.apoio_poe.model.fsm.ProContexto;

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
                case ATRIBUIR_ORIENTADOR -> atrOrientadorUI();
                case CONSULTA -> consultaUI();
            }
        }
    }

    private void consultaUI() {
        
    }

    private void atrOrientadorUI() {

    }

    private void atrPropostaUI() {

    }

    private void opCandidaturaUI() {

    }

    private void gestaoPropostaUI() {
        System.out.println("Gestão de Propostas:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Edição","Eliminação")) {
            case 1 -> {
                String tipo;
                while (!(tipo = PAInput.readString("tipo de proposta",true)).equals("T1")){
                    System.out.println("Tipo invalido");
                }
                if(tipo.equals("T1")) {
                    Proposta proposta = new T1("ramo", "ola", "1223", "ola");
                    controladorDoPrograma.adicionarProposta(proposta);
                }
            }
            case 2 -> System.out.println(controladorDoPrograma.getPropostas());
            //case 3 -> edição;
            //case 4 -> eliminação;
            //default -> acabou = true;
        }
    }

    private void gestaoDocentesUI() {
        System.out.println("Gestão de Docentes:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Edição","Eliminação")) {
            case 1 -> {
                String nome_Docente = PAInput.readString("Nome do aluno",false);
                String email_Docente = PAInput.readString("Email do aluno",false);
                boolean papel_Docente = true;
                switch (PAInput.chooseOption("Papel", "orientador","proponente")){
                    case 1: papel_Docente = true;
                    case 2: papel_Docente = false;
                }


                //isto nao pode estar aqui nem o do new aluno pq é logica tem de passar para o state correspondente de cada



                Docente docente = new Docente(nome_Docente,email_Docente,papel_Docente);
                controladorDoPrograma.adicionarDocente(docente);
            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getDocentes());
            }
            //case 3 -> edição;
            case 4 -> {
                //boolean apagou = controladorDoPrograma.removerDocente(PAInput.readInt("email do docente"));
               /* if(!apagou)
                    System.out.println("nao encontrado");
                else
                    System.out.println("apagou");*/
            }
            //default -> acabou = true;
        }
    }

    private void carregarFicheirosUI(){

    }

    private void gestaoAlunosUI() {
        System.out.println("Gestão de alunos:\n");
        switch (PAInput.chooseOption("Opções:", "Inserção", "Consulta","Edição","Eliminação")) {
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
                Aluno aluno = new Aluno(nr_Aluno,nome_Aluno,email_Aluno,ramo_Aluno,classificacao_Aluno,aceder_a_Estagio);
                if(controladorDoPrograma.adicionarAluno(aluno)){
                    System.out.println("Aluno adicionado\n");
                }else
                    System.out.println("Aluno ja existe\n");
            }
            case 2 -> {
                System.out.println(controladorDoPrograma.getAlunos());
            }
            //case 3 -> edição;
            case 4 -> {
                boolean apagou = controladorDoPrograma.removerAluno(PAInput.readInt("Classificao do aluno"));
                if(!apagou)
                    System.out.println("Não encontrado");
                else
                    System.out.println("Apagou ");
            }
            //default -> acabou = true;
        }
    }

    private void configuracaoUI() {
        System.out.println("---Inicial---\n");
        System.out.println("Bem Vindo\n");
        controladorDoPrograma.selecionar(PAInput.chooseOption("Gerir:", "Gestao de Alunos", "Gestao de Docentes", "Gestao de Projetos", "Sair"));
    }

}
