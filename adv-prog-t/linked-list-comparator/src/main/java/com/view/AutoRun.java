package main.java.com.view;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import main.java.com.filegenerators.LeitorArquivos;
import com.filegenerators.GeradorArquivosOrdenados;
import main.java.com.linkedlists.LinkedListGenerics;
import com.student.Student;


public class AutoRun {
    static Instant start;
    static Instant end;
    static long timeBetween;
    static int listLenght[] = {10000, 25000, 50000, 100000, 200000};

    public static int[] getRandomInputs(int lengthStudentList) {
        // obtaining the first n/2 registrations obtained from a list with 
        // all the assumed registration values ​​that was randomized.
        List<Integer> possibleMatriculationsRandom = new ArrayList<>();
        for (int i = 1; i <= lengthStudentList; i++) {
            possibleMatriculationsRandom.add(i);
        }
        Collections.shuffle(possibleMatriculationsRandom);

        int[] matriculationTests = new int[lengthStudentList/2];
        for (int i = 0; i < lengthStudentList/2; i++)  {
            matriculationTests[i] = possibleMatriculationsRandom.get(i);
        }
        return matriculationTests;
    }

    public static void averangeCaseTest(LinkedListGenerics<Student> studentList, int[] matriculationTests) {
        Student existenceMatriculationTest = new Student(0);

        start = Instant.now();
        for (int mat_input_value = 0; mat_input_value < matriculationTests.length; mat_input_value++) {
            existenceMatriculationTest = new Student(matriculationTests[mat_input_value]);
            studentList.pesquisar(existenceMatriculationTest);
        }
        end = Instant.now();
        timeBetween =  Duration.between(start, end).toNanos() / matriculationTests.length;

        if (studentList.isOrdered()) { System.out.printf("\nTestes da lista ordenada:\n%d nanosegundos em media para pesquisar na lista ordenada.\n", timeBetween); }
        else { System.out.printf("\nTestes da lista nao ordenada:\n%d nanosegundos em media para pesquisar na lista nao ordenada.\n", timeBetween); }
    }

    public static void worstCaseTest(LinkedListGenerics<Student> studentList, int lengthStudentList, int worstCaseValue) {
        Student existenceMatriculationTest = new Student(0);

        start = Instant.now();
        for (int num_test = 0; num_test < lengthStudentList/2; num_test++) {
            existenceMatriculationTest = new Student(worstCaseValue);
            studentList.pesquisar(existenceMatriculationTest);
        }
        end = Instant.now();
        timeBetween =  Duration.between(start, end).toNanos() / (lengthStudentList/2);

        if (studentList.isOrdered()) { System.out.printf("\n%d nanosegundos (pior caso) para pesquisar na lista ordenada\n",timeBetween); }
        else { System.out.printf("\n%d nanosegundos (pior caso) para pesquisar na lista nao ordenada\n",timeBetween);  }
    }

    public static void runSearchTest(int lengthStudentList, LinkedListGenerics<Student> studentList) { 
        averangeCaseTest(studentList, getRandomInputs(lengthStudentList));

        if (studentList.isOrdered()) { worstCaseTest(studentList, lengthStudentList, lengthStudentList-1); }
        else { worstCaseTest(studentList, lengthStudentList, lengthStudentList+1); }
    }

    public static void runTests() {
       
        for (int i : listLenght) {
            System.out.println("\n\nIniciando busca em uma lista de " + i + " alunos...");

            GeradorArquivosOrdenados.gerarArquivo(i);

            start = Instant.now();
            LinkedListGenerics<Student> orderedList = LeitorArquivos.populateList(true);
            end = Instant.now();
            timeBetween =  Duration.between(start, end).toNanos();
            System.out.printf("%d nanosegundos para inicializar a lista ordenada.\n", timeBetween );

            start = Instant.now();
            LinkedListGenerics<Student> noOrderedList = LeitorArquivos.populateList(false);
            end = Instant.now();
            timeBetween =  Duration.between(start, end).toNanos();
            System.out.printf("%d nanosegundos para inicializar a lista nao ordenada.\n", timeBetween );

            // running tests to ordered and not ordered lists
            runSearchTest(i, orderedList);
            runSearchTest(i, noOrderedList);
        } 
    }
}
