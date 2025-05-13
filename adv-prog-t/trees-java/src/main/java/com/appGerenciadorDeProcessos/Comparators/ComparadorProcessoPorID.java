package main.java.com.appGerenciadorDeProcessos.Comparators;
import java.util.Comparator;

import main.java.com.appGerenciadorDeProcessos.Model.Processo;

public class ComparadorProcessoPorID implements Comparator<Processo> {
    @Override
    public int compare(Processo o1, Processo o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
    
}
