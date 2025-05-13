package com.lib;

import java.util.Comparator;

public class ArvoreAVLExemplo <T> extends ArvoreBinaria<T>{

    public ArvoreAVLExemplo(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void adicionar(No<T> novoValor) { 
       

    }
    
    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...
    public T rotacaoDireita(No<T> rotac) {
        No<T> auxNo = rotac.getFilhoEsquerda();
        auxNo.setFilhoDireita(rotac);
    }

    public int getAlturaSubarvore(No<T> no) {
        ArvoreBinaria<T> subArvore = new ArvoreBinaria<>();
        subArvore.raiz = no;

        return subArvore.altura(); 
    }


}
