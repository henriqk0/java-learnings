package com.app;

import java.util.Random;

public class GerenciadorDeProcessos {
    private ArvoreBinaria arvoreProcessos;
    final Random rand = new Random();

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
    private Processo gerarProcesso(int identifier){
        String nome = gerarNomeProcessoAleatorio();
        float usoCPU = rand.nextFloat() * 100;
        usoCPU = Math.round(usoCPU * 100.0f) / 100.0f;
        float usoMemoria = rand.nextFloat() * 16;
        usoMemoria = Math.round(usoMemoria * 100.0f) / 100.0f;

        return new Processo(identifier, nome, usoCPU, usoMemoria);
    }

    public static void main(String[] args) {
        int numProcessos;
        
    }
}
