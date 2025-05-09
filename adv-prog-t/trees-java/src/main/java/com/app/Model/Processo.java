package com.app.Model;


public class Processo {
    private int id;
    private String nome;
    // Uso de CPU em porcentagem
    private float usoCPU;
    // Uso de memória variando entre 0MB e 16000MB (para permitir o encaixe de mais nós de processo)
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
        Memoria m = Memoria.getInstance();
        Cpu c = Cpu.getInstance();
        try {
            m.setAvailableSize(usoMemoria);
            c.setAvailableAmount(usoMemoria);
            this.id = id;
            this.nome = nome;
            this.usoCPU = usoCPU;
            this.usoMemoria = usoMemoria;
        } catch(Exception e){
            System.out.println(e.getMessage());//Printa mensagem de erro
        }
    }

}
