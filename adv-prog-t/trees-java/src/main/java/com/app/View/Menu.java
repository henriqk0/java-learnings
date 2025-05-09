package com.app.View;

import com.app.Comparators.ComparadorProcessoPorCPU;
import com.app.Comparators.ComparadorProcessoPorMemoria;
import com.app.Model.Processo;
import com.lib.ArvoreBinaria;

public class Menu {

    public static void exibir(){
        //Instanciando os comparadores
        ComparadorProcessoPorMemoria comparador_memoria = new ComparadorProcessoPorMemoria();
        ComparadorProcessoPorCPU comparador_cpu = new ComparadorProcessoPorCPU();

        //Criando 2 árvores: uma que organiza por uso de memória e outra por uso de CPU.
        ArvoreBinaria<Processo> arvore_processos_memoria = new ArvoreBinaria<Processo>(comparador_memoria);
        ArvoreBinaria<Processo> arvore_processos_cpu = new ArvoreBinaria<Processo>(comparador_cpu);

        //Rodar os casos de teste aqui
    }
}
