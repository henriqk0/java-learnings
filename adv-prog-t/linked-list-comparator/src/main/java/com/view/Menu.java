package com.view;

import java.io.IOException;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

import com.filegenerators.LeitorArquivos;
import com.linkedlists.LinkedListGenerics;
import com.student.Student;

public class Menu {
    public static void show() {
        System.out.println(
            "\n##################################" +
            "\nEscreva o numero de uma opcao:\n" +
            "1) Pesquisar na lista nao ordenada\n" +
            "2) Pesquisar na lista ordenada \n" +
            "3) Sair \n" +
            "->"
        );
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Instant start, end;
        long timeBetween;

        start = Instant.now();
        LinkedListGenerics<Student> noOrderedList = LeitorArquivos.populateList(false);
        end = Instant.now();
        timeBetween =  Duration.between(start, end).toNanos();
        System.out.printf("%d nanosegundos para inicializar a lista nao ordenada.\n", timeBetween );

        start = Instant.now();
        LinkedListGenerics<Student> orderedList = LeitorArquivos.populateList(true);
        end = Instant.now(); 
        timeBetween = Duration.between(start, end).toNanos();
        System.out.printf("%d nanosegundos para inicializar a lista ordenada.\n", timeBetween);

        Menu.show();        
        int mat;
        int opt = scanner.nextInt();

        while (opt != 3) {
            if (opt == 1 || opt == 2) {
                System.out.println("Digite a matricula a ser pesquisada:");

                mat = scanner.nextInt();
                Student existenceMatriculationTest = new Student(mat);
                Student studentWithMat = null;

                Menu.clearConsole();
                if ( opt == 1 ) { 
                    start = Instant.now();
                    studentWithMat = noOrderedList.pesquisar(existenceMatriculationTest);
                    end = Instant.now();
                    timeBetween =  Duration.between(start, end).toNanos();
                    System.out.printf("%d nanosegundos para pesquisar na lista nao ordenada.\n", timeBetween);
                }
                else { 
                    start = Instant.now();
                    studentWithMat = orderedList.pesquisar(existenceMatriculationTest);
                    end = Instant.now();
                    timeBetween =  Duration.between(start, end).toNanos();
                    System.out.printf("%d nanosegundos para pesquisar na lista ordenada.\n", timeBetween);
                }

                if (studentWithMat != null)  {
                    System.out.printf("ID: %d | Nome: %s | Nota: %.2f%n", studentWithMat.getReg(), studentWithMat.getName(), studentWithMat.getScore());
                } else {
                    System.out.println("Aluno nao encontrado!");
                }
            }
            else { System.out.println("Opcao invalida!"); }

            Menu.show();
            opt = scanner.nextInt();
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
