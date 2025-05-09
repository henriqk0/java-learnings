package com.app;

public class Processo {
    private int id;
    private String nome;
    // Uso de CPU em porcentagem
    private float usoCPU;
    // Uso de memória variando entre 0 e 16 GB
    private float usoMemoria;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getUsoCPU() {
        return usoCPU;
    }

    public float getUsoMemoria() {
        return usoMemoria;
    }

    public Processo(int id, String nome, float usoCPU, float usoMemoria){
        this.id = id;
        this.nome = nome;
        this.usoCPU = usoCPU;
        this.usoMemoria = usoMemoria;
    }
}
