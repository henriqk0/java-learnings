package main.java.com.appGerenciadorDeProcessos.View;

//Classes utilitárias
import java.io.IOException;
import java.util.Scanner;

//Comparadores
import main.java.com.appGerenciadorDeProcessos.Comparators.ComparadorProcessoPorID;
import main.java.com.appGerenciadorDeProcessos.Comparators.ComparadorProcessoPorNome;

//Controllers
import main.java.com.appGerenciadorDeProcessos.Control.GerenciadorDeProcessos;

//Models
import main.java.com.appGerenciadorDeProcessos.Model.Processo;

public class Menu {

    public static void exibir(){
         System.out.print(
            "\n##################################" +
            "\nEscreva o numero de uma opcao:\n" +
            "1) Adicionar um novo processo\n" +
            "2) Remover um processo \n" +
            "3) Pesquisar por um processo \n" +
            "4) Visualizar processos \n" +
            "5) Ver altura \n" +
            "6) Ver quantidade de nós \n" +
            "7) Sair \n" +
            "--> "
        );
    }

    public static void exibirPesquisar(){
        System.out.print(
            "\n##################################" +
            "\nEscreva o numero de uma opcao:\n" +
            "1) Pesquisar por Nome\n" +
            "2) Pesquisar por ID\n" +
            "3) Pesquisar por Uso de CPU\n" +
            "--> "
        );
    }

    public static void run() {
        clearConsole();
        //Instanciando o scanner
        Scanner scanner = new Scanner(System.in);

        //Instanciando os comparadores
        ComparadorProcessoPorNome comparador_nome = new ComparadorProcessoPorNome();
        ComparadorProcessoPorID comparador_id = new ComparadorProcessoPorID();

        //Instanciando o gerenciador de processos
        GerenciadorDeProcessos gerenciador = new GerenciadorDeProcessos();

        System.out.println("Bem-vindo. Quantos processos deseja gerar?");
        System.out.print("--> ");
        int quantidade_processos = scanner.nextInt();
        scanner.nextLine(); // Consumindo a quebra de linha

        //Gerando processos aleatórios
        gerenciador.popularGerenciador(quantidade_processos);

        Menu.exibir();
        int opcao = scanner.nextInt();

        while (opcao != 7) {
            switch (opcao) {
                case 1:
                    System.out.println("Adicionando um novo processo...");
                    System.out.print("Digite o nome do processo: ");
                    String nomeProcesso = scanner.next();
                    Processo novoProcesso = gerenciador.gerarProcesso(nomeProcesso);
                    gerenciador.getArvoreProcessos().adicionar(novoProcesso);
                    break;

                case 2:
                    System.out.println("Removendo um processo...");
                    System.out.print("Digite o uso de CPU do processo (sim, isso nao faz sentido): ");
                    double usoCPU = scanner.nextDouble();
                    gerenciador.getArvoreProcessos().remover(new Processo(usoCPU));
                    break;

                case 3:
                    System.out.println("Pesquisando por um processo...");
                    exibirPesquisar();
                    int opcaoPesquisar = scanner.nextInt();

                    if (opcaoPesquisar == 1) {
                        System.out.print("Digite o nome do processo: ");
                        String nomePesquisar = scanner.next();
                        Processo processoEncontrado = gerenciador.getArvoreProcessos().
                        pesquisar(new Processo(nomePesquisar), comparador_nome);

                        if (processoEncontrado != null) {
                            System.out.println("Processo encontrado: " + processoEncontrado);
                        } else {
                            System.out.println("Processo nao encontrado.");
                        }

                    } else if (opcaoPesquisar == 2) {
                        System.out.print("Digite o ID do processo: ");
                        int idPesquisar = scanner.nextInt();
                        Processo processoEncontrado = gerenciador.getArvoreProcessos().
                        pesquisar(new Processo(idPesquisar), comparador_id);

                        if (processoEncontrado != null) {
                            System.out.println("Processo encontrado: " + processoEncontrado);
                        } else {
                            System.out.println("Processo não encontrado.");
                        }
                    }

                    else if (opcaoPesquisar == 3) {
                        System.out.print("Digite o uso de CPU do processo (sim, isso nao faz o menor sentido): ");
                        double usoCPUPesquisar = scanner.nextDouble();
                        Processo processoEncontrado = gerenciador.getArvoreProcessos().
                        pesquisar(new Processo(usoCPUPesquisar));

                        if (processoEncontrado != null) {
                            System.out.println("Processo encontrado: " + processoEncontrado);
                        } else {
                            System.out.println("Processo não encontrado.");
                        }
                    }
                    else {
                        System.out.println("Opcao invalida.");
                    }
                    break;
                case 4:
                    System.out.println("Visualizando processos...");
                    System.out.println(gerenciador.getArvoreProcessos().caminharEmOrdem());
                    break;

                case 5:
                    System.out.println("Altura da arvore: " + gerenciador.getArvoreProcessos().altura());
                    break;

                case 6:
                    System.out.println("Quantidade de nos: " + gerenciador.getArvoreProcessos().quantidadeNos());
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
            Menu.exibir();
            opcao = scanner.nextInt();
        }

        scanner.close();
    }
    

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else { System.out.print("\033\143"); }
        } catch (IOException | InterruptedException ex) {}
    }
        
}
