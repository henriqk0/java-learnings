package com.lib;

import java.util.Comparator;

public class ArvoreAVLExemplo <T> extends ArvoreBinaria<T>{

    public ArvoreAVLExemplo(Comparator<T> comparator) {
        super(comparator);
    }
    
    public int getFatorBalanceamento(No<T> noRaiz) {
        int he = getAlturaSubarvore(noRaiz.getFilhoEsquerda());
        int hd = getAlturaSubarvore(noRaiz.getFilhoDireita());
        return hd - he;
    }

    public void checarBalanceamento(No<T> noRaiz) {
        int fbRaiz = getFatorBalanceamento(raiz);
        int fbFilhoDireita = getFatorBalanceamento(raiz.getFilhoDireita());
        int fbFilhoEsquerda = getFatorBalanceamento(raiz.getFilhoEsquerda());

        if (fbRaiz == 2 && fbFilhoDireita > 0) {
            // rotação à esquerda
            rotacaoEsquerda(raiz);
        } else if (fbRaiz == 2 && fbFilhoDireita < 0) {
            // rotação direita esquerda
            rotacaoDireitaEsquerda(raiz);
        } else if (fbRaiz == -2 && fbFilhoEsquerda < 0) {
            // rotação à direita
            rotacaoDireita(raiz);
        } else if (fbRaiz == -2 && fbFilhoEsquerda > 0) { 
            // rotação esquerda direita
            rotacaoEsquerdaDireita(raiz);
        }
    }

    public No<T> adicionarRecursivo(No<T> raiz, No<T> novoNo){
        if (raiz == null) {
            return novoNo;
        }
        // TODO: Debugar (a árvore apontará para a nova raiz, caso mude ??)
        if (comparador.compare(novoNo.getValor(), raiz.getValor()) < 0) {
            raiz.setFilhoEsquerda(adicionarRecursivo(raiz.getFilhoEsquerda(), novoNo));
        } else {
            raiz.setFilhoDireita(adicionarRecursivo(raiz.getFilhoDireita(), novoNo));
        }
        checarBalanceamento(raiz);

        return raiz;
    }

    @Override
    public void adicionar(T valor) { 
        No<T> novoNo = new No<T>(valor);
        adicionarRecursivo(this.raiz, novoNo);       
    }
    
    public No<T> rotacaoDireita(No<T> rotac) {
        No<T> auxNo = rotac.getFilhoEsquerda();
        rotac.setFilhoEsquerda(auxNo.getFilhoDireita());
        auxNo.setFilhoDireita(rotac);
        return auxNo;
    }

    public No<T> rotacaoEsquerdaDireita(No<T> rotac) {
        rotac.setFilhoEsquerda(rotacaoEsquerda(rotac.getFilhoEsquerda()));
        return rotacaoDireita(rotac);
    }

    public No<T> rotacaoDireitaEsquerda(No<T> rotac) {
        rotac.setFilhoDireita(rotacaoDireita(rotac.getFilhoDireita()));
        return rotacaoEsquerda(rotac);
    }

    public No<T> rotacaoEsquerda(No<T> rotac) {
        No<T> auxNo = rotac.getFilhoDireita();
        rotac.setFilhoDireita(auxNo.getFilhoEsquerda());
        auxNo.setFilhoEsquerda(rotac);
        return auxNo;
    }

    public int getAlturaSubarvore(No<T> no) {
        if (no == null) {
            return 0;
        }

        ArvoreBinaria<T> subArvore = new ArvoreBinaria<>(comparador);
        subArvore.raiz = no;

        return subArvore.altura(); 
    }


}
