package com.app;
import java.util.Comparator;

public class ComparadorProcessoPorCPU implements Comparator<Processo> {
    @Override
    public int compare(Processo o1, Processo o2) {
        return Float.compare(o1.getUsoCPU(), o2.getUsoCPU());
    }
    
}
