package main.java.com.appGerenciadorDeProcessos.Comparators;
import java.util.Comparator;

import main.java.com.appGerenciadorDeProcessos.Model.Processo;

public class ComparadorProcessoPorCPU implements Comparator<Processo> {
    @Override
    public int compare(Processo o1, Processo o2) {
        return Double.compare(o1.getUsoCPU(), o2.getUsoCPU());
    }
    
}
