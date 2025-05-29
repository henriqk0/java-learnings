package com.appGerenciadorDeProcessos.View;

import java.io.IOException;
import java.util.Scanner;

import com.appGerenciadorDeProcessos.Comparators.*;
import com.appGerenciadorDeProcessos.Control.GerenciadorDeProcessos;
import com.appGerenciadorDeProcessos.Model.Processo;

public class Menu {

    private static void exibir(){
         System.out.print("""
                          
                          ##################################
                          Escreva o numero de uma opcao:
                          1) Adicionar um novo processo
                          2) Remover um processo 
                          3) Pesquisar por um processo 
                          4) Visualizar processos 
                          5) Sair
                          """);
                          System.out.print("--> ");
    }

    private static void exibirPesquisar(){
        System.out.print("""
                         
                          ##################################
                          Escreva o numero de uma opcao:
                          1) Pesquisar por Nome
                          2) Pesquisar por ID
                          3) Pesquisar por Uso de CPU
                          """);
                          System.out.print("--> ");

    }

    private static void exibirRemover(){
        System.out.print("""
                         
                          ##################################
                          Escreva o numero de uma opcao:
                          1) Remover por ID
                          2) Remover por Nome
                          3) Remover por Uso de CPU
                          """);
                          System.out.print("--> ");

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

        while (opcao != 5) {
            switch (opcao) {
                case 1 -> {
                    System.out.println("Adicionando um novo processo...");
                    System.out.print("Digite o nome do processo: ");
                    String nomeProcesso = scanner.next();
                    Processo novoProcesso = gerenciador.gerarProcesso(nomeProcesso);
                    gerenciador.getArvoreProcessos().adicionar(novoProcesso);
                    System.out.println("Processo adicionado com sucesso! " + novoProcesso);

                }

                case 2 -> {
                    System.out.println("Removendo um processo...");
                    exibirRemover();
                    int opcaoRemover = scanner.nextInt();
                    switch (opcaoRemover) {
                        case 1 -> {
                            System.out.print("Digite o ID do processo: ");
                            int idRemover = scanner.nextInt();
                            Processo processoRemover = gerenciador.getArvoreProcessos().removerPorComparador(new Processo(idRemover), comparador_id);
                            if (processoRemover != null) {
                                System.out.println("Processo removido! " + processoRemover);
                            } else {
                                System.out.println("Processo não encontrado.");
                            }
                        }
                        case 2 -> {
                            System.out.print("Digite o nome do processo: ");
                            String nomeRemover = scanner.next();
                            Processo processoRemover = gerenciador.getArvoreProcessos().removerPorComparador(new Processo(nomeRemover), comparador_nome);
                            if (processoRemover != null) {
                                System.out.println("Processo removido! " + processoRemover);
                            } else {
                                System.out.println("Processo não encontrado.");
                            }
                        }
                        case 3 -> {
                            System.out.print("Digite o uso de CPU do processo (sim, isso nao faz o menor sentido): ");
                            double usoCPURemover = scanner.nextDouble();
                            Processo processoRemover = gerenciador.getArvoreProcessos().remover(new Processo(usoCPURemover));
                            if (processoRemover != null) {
                                System.out.println("Processo removido! " + processoRemover);
                            } else {
                                System.out.println("Processo não encontrado.");
                            }
                        }
                        default -> System.out.println("Opcao invalida.");
                    }
                }

                case 3 -> {
                    System.out.println("Pesquisando por um processo...");
                    exibirPesquisar();
                    int opcaoPesquisar = scanner.nextInt();

                switch (opcaoPesquisar) {
                    case 1 -> {
                            System.out.print("Digite o nome do processo: ");
                            String nomePesquisar = scanner.next();
                            Processo processoEncontrado = gerenciador.getArvoreProcessos().
                                    pesquisar(new Processo(nomePesquisar), comparador_nome);
                            if (processoEncontrado != null) {
                                System.out.println("Processo encontrado: " + processoEncontrado);
                            } else {
                                System.out.println("Processo nao encontrado.");
                            }                          
                    }
                    case 2 -> {
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
                    case 3 -> {
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
                    default -> System.out.println("Opcao invalida.");
                    
                }
                }
                case 4 -> {
                    System.out.println("Visualizando processos...");
                    System.out.println(gerenciador.getArvoreProcessos().caminharEmOrdem());
                }
                default -> System.out.println("Opcao invalida.");
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
