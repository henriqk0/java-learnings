package com.filegenerators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.linkedlists.LinkedListGenerics;
import com.student.Student;
import com.student.StudentRegistrationComparator;


public class LeitorArquivos {
    private static final String NOME_ARQUIVO = "alunosOrdenados.txt";
    
    public static LinkedListGenerics<Student> populateList(boolean itsOrdered) {
        LinkedListGenerics<Student> studentList = new LinkedListGenerics<>(itsOrdered, null);
        if (itsOrdered) { studentList = new LinkedListGenerics<>(itsOrdered, new StudentRegistrationComparator()); }

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            int numRegistros = Integer.parseInt(reader.readLine().trim());
            System.out.println("Número de registros: " + numRegistros);
            
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                float nota = Float.parseFloat(partes[2]);
                
                Student s = new Student(id, nome, nota);
                studentList.adicionar(s);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao processar um dos valores numéricos: " + e.getMessage());
        }
        return studentList;
    }

    public static void main(String[] args) {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            int numRegistros = Integer.parseInt(reader.readLine().trim());
            System.out.println("Número de registros: " + numRegistros);
            
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                float nota = Float.parseFloat(partes[2]);
                
                System.out.printf("ID: %d | Nome: %s | Nota: %.2f%n", id, nome, nota);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao processar um dos valores numéricos: " + e.getMessage());
        }
    }
}