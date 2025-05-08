package com.app;

public class Processo {
    private int id;
    private String nome;
    private float usoCPU;
    private float usoMemoria;

    public Processo(int id, String nome, float usoCPU, float usoMemoria){
        this.id = id;
        this.nome = nome;
        this.usoCPU = usoCPU;
        this.usoMemoria = usoMemoria;
    }
}
