package com.lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }
    
    public int getFatorBalanceamento(No<T> noRaiz) {
        if (noRaiz == null) {
            return 0;
        }

        int he = getAlturaSubarvore(noRaiz.getFilhoEsquerda());
        int hd = getAlturaSubarvore(noRaiz.getFilhoDireita());
        return hd - he;
    }

    public No<T> balancear(No<T> noRaiz) {
        int fbRaiz = getFatorBalanceamento(noRaiz);
        int fbFilhoDireita = 0;
        int fbFilhoEsquerda = 0;

        if (noRaiz.getFilhoDireita() != null){
            fbFilhoDireita = getFatorBalanceamento(noRaiz.getFilhoDireita());
        }
        if (noRaiz.getFilhoEsquerda() != null){
            fbFilhoEsquerda = getFatorBalanceamento(noRaiz.getFilhoEsquerda());
        }

        if (fbRaiz == 2 && fbFilhoDireita > 0) {
            // rotação à esquerda
            return rotacaoEsquerda(noRaiz);
        } else if (fbRaiz == 2 && fbFilhoDireita < 0) {
            // rotação direita esquerda
            return rotacaoDireitaEsquerda(noRaiz);
        } else if (fbRaiz == -2 && fbFilhoEsquerda < 0) {
            // rotação à direita
            return rotacaoDireita(noRaiz);
        } else if (fbRaiz == -2 && fbFilhoEsquerda > 0) { 
            // rotação esquerda direita
            return rotacaoEsquerdaDireita(noRaiz);
        }

        return noRaiz;
    }

    public No<T> adicionarRecursivo(No<T> raiz, No<T> novoNo){
        if (raiz == null) {
            return novoNo;
        }

        if (comparador.compare(novoNo.getValor(), raiz.getValor()) < 0) {
            raiz.setFilhoEsquerda(adicionarRecursivo(raiz.getFilhoEsquerda(), novoNo));
        } else {
            raiz.setFilhoDireita(adicionarRecursivo(raiz.getFilhoDireita(), novoNo));
        }
        raiz = balancear(raiz);

        return raiz;
    }

    @Override
    public void adicionar(T valor) { 
        No<T> novoNo = new No<T>(valor);
        raiz = adicionarRecursivo(this.raiz, novoNo);       
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

        return 1 + Math.max(getAlturaSubarvore(no.getFilhoEsquerda()), getAlturaSubarvore(no.getFilhoDireita()));
    }
    
    public T remover(T valor) {
        ResultadoRemocao<T> resultado = remover(raiz, valor);
        raiz = resultado.no;
        return resultado.removido;
    }

    // Classe auxiliar interna 
    private static class ResultadoRemocao<T> {
        No<T> no;
        T removido;

        ResultadoRemocao(No<T> no, T removido) {
            this.no = no;
            this.removido = removido;
        }
    }

    private ResultadoRemocao<T> remover(No<T> no, T valor) {
        if (no == null) {
            return new ResultadoRemocao<>(null, null); // Valor não encontrado
        }

        T removido = null;
        int cmp = comparador.compare(valor, no.getValor());

        if (cmp < 0) {
            ResultadoRemocao<T> resEsquerda = remover(no.getFilhoEsquerda(), valor);
            no.setFilhoEsquerda(resEsquerda.no);
            removido = resEsquerda.removido;
        } else if (cmp > 0) {
            ResultadoRemocao<T> resDireita = remover(no.getFilhoDireita(), valor);
            no.setFilhoDireita(resDireita.no);
            removido = resDireita.removido;
        } else {
            // Encontrado — remover
            removido = no.getValor();

            if (no.getFilhoEsquerda() == null && no.getFilhoDireita() == null) {
                return new ResultadoRemocao<>(null, removido);
            }

            if (no.getFilhoEsquerda() == null) {
                return new ResultadoRemocao<>(no.getFilhoDireita(), removido);
            }

            if (no.getFilhoDireita() == null) {
                return new ResultadoRemocao<>(no.getFilhoEsquerda(), removido);
            }

            // Dois filhos
            No<T> sucessor = encontrarMinimo(no.getFilhoDireita());
            no.setValor(sucessor.getValor());
            ResultadoRemocao<T> resSub = remover(no.getFilhoDireita(), sucessor.getValor());
            no.setFilhoDireita(resSub.no);
        }

        // Rebalancear e retornar resultado
        return new ResultadoRemocao<>(balancear(no), removido);
    }

    private No<T> encontrarMinimo(No<T> no) {
        while (no.getFilhoEsquerda() != null) {
            no = no.getFilhoEsquerda();
        }
        return no;
    }
    
}
