package view;
import java.util.Scanner;

import linkedlists.LinkedListComparator;
import linkedlists.LinkedListNoOrdered;
import student.Student;
import student.StudentNameComparator;


public class Menu {

    public static void exibir(){
        Scanner sc = new Scanner(System.in);
        LinkedListNoOrdered<Student> no_ordered_list = new LinkedListNoOrdered<Student>();
        LinkedListComparator<Student> ordered_list = new LinkedListComparator<Student>(true, new StudentNameComparator());

        String msg = "Escreva o numero de uma opcao:\n" +
                "1) Inserir Aluno Lista Ordenada\n"
                + "2) Buscar Aluno Lista Ordenada\n" +
                "3) Inserir Aluno Lista Não Ordenada\n" +
                "4) Buscar Aluno Lista Não Ordenada\n" +
                "->";

        System.out.println(msg);
        int opt = sc.nextInt();

        while(opt != 0){
            if(opt == 1){
                Student student = new Student();
                ordered_list.insertElemOrdered(student);
            }else if(opt == 2){
                Student student = new Student();
                ordered_list.insertElemOrdered(student);
            } else if (opt == 3) {
                Student student = new Student();
                no_ordered_list.insertElem(student);
            } else if (opt == 4) {
                Student student = new Student();
                no_ordered_list.insertElem(student);
            }else{
                System.out.println("Opcao invalida!");
            }
            System.out.println(msg);
            opt = sc.nextInt();
        }
    }
}
