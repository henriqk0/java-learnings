package main.java.com.appGerenciadorDeProcessos.View.AVL;

/*Importando classes de Arvore<T> e Gerenciador de Processos*/

import java.util.Scanner;

public class MenuAVL{

    public static void exibir(){
        String message = """
                Escolha uma das opcoes:
                1)
                2)
                3)
                4)
                0)
                """;
    }

    public static void run(){
        Scanner sc = new Scanner(System.in);

        exibir();
        int opt = sc.nextInt();

        while(opt != 0){
            switch(opt){
                case 1:
                    System.out.println();
                case 2:
                    System.out.println();
                case 3:
                    System.out.println();
                case 4:
                    System.out.println();
                default:
                    System.out.println();
            }
            exibir();
            opt = sc.nextInt();
        }
    }
}
