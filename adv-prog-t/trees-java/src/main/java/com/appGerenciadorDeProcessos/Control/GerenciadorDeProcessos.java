package com.appGerenciadorDeProcessos.Control;

import java.util.Random;

import com.appGerenciadorDeProcessos.Comparators.ComparadorProcessoPorCPU;
import com.appGerenciadorDeProcessos.Model.Cpu;
import com.appGerenciadorDeProcessos.Model.Memoria;
import com.appGerenciadorDeProcessos.Model.Processo;
import com.lib.ArvoreBinaria;

public class GerenciadorDeProcessos {
    final Random rand = new Random();
    // Árvore de processos ordenada a princípio por uso de CPU

    public ArvoreBinaria<Processo> getArvoreProcessos() {
        return arvoreProcessos;
    }

    private ArvoreBinaria<Processo> arvoreProcessos;
    private int quantidadeProcessos;

    public GerenciadorDeProcessos() {
        this.arvoreProcessos = new ArvoreBinaria<>(new ComparadorProcessoPorCPU());
    }

    private static final String[] PREFIXOS = {
            "proc", "sys", "task", "service", "daemon", "worker", "thread", "bg",
            "svchost", "chrome", "firefox", "explorer", "nginx", "apache", "mysql",
            "postgres", "systemd", "kworker", "powershell", "cmd", "bash", "zsh",
            "java", "python", "node", "dotnet", "teams", "zoom", "slack"
    };

    private static final String[] SUFIXOS = {
            "manager", "handler", "exec", "runner", "ctrl", "core", "engine", "job",
            "main", "thread", "svc", "loop", "client", "server", "host", "mod",
            "bridge", "util", "watcher", "cleaner", "logger", "monitor", "session"
    };

    public static String gerarNomeProcessoAleatorio() {
        Random rand = new Random();
        String prefixo = PREFIXOS[rand.nextInt(PREFIXOS.length)];
        String sufixo = SUFIXOS[rand.nextInt(SUFIXOS.length)];
        return prefixo + "_" + sufixo;
    }

    public Processo gerarProcesso(){
        // Variáveis que armazenam a quantidade de CPU e memória disponíveis
        Memoria m = Memoria.getInstance();
        Cpu c = Cpu.getInstance();

        String nome = gerarNomeProcessoAleatorio();

        // Uso de CPU e memória aleatórios, variando entre 0 e 10% da quantidade disponível
        // a fim de evitar que o uso total ultrapasse 100% dos recursos
        double usoCPU = (rand.nextDouble() * c.getAvailableAmount() * 0.1);
        usoCPU = Math.round(usoCPU * 10000.0) / 10000.0; // Arredondando para 4 casas decimais
        double usoMemoria = (rand.nextDouble() * m.getAvailableSize() * 0.1);
        usoMemoria = Math.round(usoMemoria * 10000.0) / 10000.0; // Arredondando para 4 casas decimais
        
        return new Processo(this.quantidadeProcessos + 1, nome, usoCPU, usoMemoria);
    }

    public Processo gerarProcesso(String nomeProcesso){
        // Variáveis que armazenam a quantidade de CPU e memória disponíveis
        Memoria m = Memoria.getInstance();
        Cpu c = Cpu.getInstance();

        String nome = nomeProcesso;

        // Uso de CPU e memória aleatórios, variando entre 0 e 10% da quantidade disponível
        // a fim de evitar que o uso total ultrapasse 100% dos recursos
        double usoCPU = (rand.nextDouble() * c.getAvailableAmount() * 0.1);
        usoCPU = Math.round(usoCPU * 100.0) / 10000.0; // Arredondando para 4 casas decimais
        double usoMemoria = (rand.nextDouble() * m.getAvailableSize() * 0.1);
        usoMemoria = Math.round(usoMemoria * 10000.0) / 10000.0; // Arredondando para 4 casas decimais

        this.quantidadeProcessos++;
        
        return new Processo(this.quantidadeProcessos, nome, usoCPU, usoMemoria);
    }

    // numProcessos é o número de processos que deseja-se gerar
    // quantidadeProcessos é o número de processos que já existem na árvore
    public void popularGerenciador (int numProcessos) {
        for (int i = 0; i < numProcessos; i++) {
            Processo processo = this.gerarProcesso();
            /* 
            System.out.printf("Processo ID: %d, Nome: %s, Uso CPU: %f %%, Uso Memória: %.4f MB\n", 
                    processo.getId(), processo.getNome(), processo.getUsoCPU(), processo.getUsoMemoria());
            */
            System.out.println("Processo ID: " + processo.getId() + ", Nome: " + processo.getNome() + 
            ", Uso CPU: " + processo.getUsoCPU() + ", Uso Memória: " + processo.getUsoMemoria() + " MB");
            this.arvoreProcessos.adicionar(processo);
            this.quantidadeProcessos++;
        }
    }
}
