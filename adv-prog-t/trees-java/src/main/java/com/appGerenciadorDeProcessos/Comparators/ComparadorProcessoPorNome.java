package main.java.com.appGerenciadorDeProcessos.Comparators;
import java.util.Comparator;

import main.java.com.appGerenciadorDeProcessos.Model.Processo;

public class ComparadorProcessoPorNome implements Comparator<Processo> {
    @Override
    public int compare(Processo o1, Processo o2) {
        return o1.getNome().compareTo(o2.getNome());
    }
}
