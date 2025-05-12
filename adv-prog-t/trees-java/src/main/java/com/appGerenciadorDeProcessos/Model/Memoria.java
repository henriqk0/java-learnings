package com.appGerenciadorDeProcessos.Model;

import java.util.Scanner;

public class Memoria {
    //Obs.: Tamanho medido em MBs(MegaBytes)
    private static double size = 0;
    private static double available_size = size;
    private static Memoria instance = null;

    private Memoria(double s){
        size = s;
        available_size = size;
    }

    public static Memoria getInstance(){
        if(instance == null){
            Scanner sc = new Scanner(System.in);
            System.out.print("Tamanho da memoria (MB): ");
            instance = new Memoria(sc.nextFloat());
            //Por algum motivo, fechar esse scanner estava dando problema lá no menu
            //sc.close();
        }
        
        return instance;
    }

    public static Memoria getInstance(double memory_size) {
        if (instance == null) {
            instance = new Memoria(memory_size);
        }
        return instance;
    }

    public double getSize() {return size;}

    public double getAvailableSize() {return available_size;}

    public void setAvailableSize(double process_size) throws Exception {
        if(available_size < process_size){
            throw new Exception("Memoria insuficiente");
        }else{
            available_size -= process_size;
        }
    }
}
