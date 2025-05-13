package com.lib;

import java.util.Comparator;

public class ArvoreAVLExemplo <T> extends ArvoreBinaria<T>{

    public ArvoreAVLExemplo(Comparator<T> comparator) {
        super(comparator);
    }

    public No<T> adicionarRecursivo(No<T> raiz, No<T> novoNo){
        if (raiz == NULL) {
            return no;
        }

        int heRaiz = getAlturaSubarvore(raiz.getFilhoEsquerda());
        int hdRaiz = getAlturaSubarvore(raiz.getFilhoDireita());
        int fbRaiz = hdRaiz - heRaiz;
        
        int heFilhoDireita = getAlturaSubarvore(raiz.getFilhoDireita().getFilhoEsquerda());
        int hdFilhoDireita = getAlturaSubarvore(raiz.getFilhoDireita().getFilhoDireita());
        int fbFilhoDireita = hdFilhoDireita - heFilhoDireita;

        int heFilhoEsquerda = getAlturaSubarvore(raiz.getFilhoEsquerda().getFilhoEsquerda());
        int hdFilhoEsquerda = getAlturaSubarvore(raiz.getFilhoEsquerda().getFilhoDireita());
        int fbFilhoEsquerda = hdFilhoEsquerda - heFilhoEsquerda;

        // falta verificações dos fb's e ver se os metodos estao funcionando
        if (comparator.compare(novoNo.getValor(), raiz.getValor()) < 0) {
            raiz.setFilhoEsquerda(adicionarRecursivo(raiz.getFilhoEsquerda(), novoNo));
        } else {
            raiz.setFilhoEsquerda(adicionarRecursivo(raiz.getFilhoEsquerda(), novoNo));
        }
    }

    @Override
    public void adicionar(T valor) { 
        No<T> novoNo = new No<T>(valor)
        ArvoreAVLExemplo.adicionarRecursivo(this.raiz, novoNo);       
    }
    
    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...
    public T rotacaoDireita(No<T> rotac) {
        No<T> auxNo = rotac.getFilhoEsquerda();
        auxNo.setFilhoDireita(rotac);
    }

    public int getAlturaSubarvore(No<T> no) {
        if (no == null) {
            return 0;
        }

        ArvoreBinaria<T> subArvore = new ArvoreBinaria<>();
        subArvore.raiz = no;

        return subArvore.altura(); 
    }


}
