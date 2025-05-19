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

    public No<T> checarBalanceamento(No<T> noRaiz) {
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
        raiz = checarBalanceamento(raiz);

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
    
    /* 
    public No<T> removeMin(No<T> no){ // `Min` de mínimo, levando em consideração que os elementos à esquerda são menores
        // remove o sucessor substituindo pelo filho à direita
        if (no.getFilhoEsquerda() == null) { // já é o elemento mais à esquerda da subarvore
            return no.getFilhoDireita(); // então, retorna o seu elemento à direita
        }
        else {
            no.setFilhoEsquerda(removeMin(no.getFilhoEsquerda()));
            checarBalanceamento(no);
        }
    }

    public No<T> sucessorAux(No<T> noAux) { 
        if (noAux.getFilhoEsquerda() == null) { // já é o elemento mais à esquerda da subarvore
            return noAux.getFilhoDireita(); // então, retorna o seu elemento à direita
        }
        else {
            sucessorAux(noAux.getFilhoEsquerda());
        }
    }

    public No<T> removeRec(No<T> no, T valor) {
        No<T> novaRaizSub = no;

        if (this.comparador.compare(no.getValor(), valor) == 0) {
            if (no.getFilhoEsquerda == null) {
                novaRaizSub = no.getFilhoDireita();
            } else if (no.getFilhoDireita == null) {
                novaRaizSub = no.getFilhoEsquerda();
            } 
            else { // tem dois filhos, devemos checar o balanecamento 
                novaRaizSub = sucessorAux(no.getFilhoDireita());

                novaRaizSub.setFilhoDireita(removeMin(no.getFilhoDireita()));
                novaRaizSub.setFilhoEsquerda(no.getFilhoEsquerda());
            }

            if (this.raiz == no) {this.raiz = novaRaizSub;}
            no = null;
        } 
        else {
            if (this.comparador.compare(no.getValor(), valor) < 0) {
                no.setFilhoEsquerda(removeRec(no.getFilhoEsquerda(), valor));
            }
            else {
                no.setFilhoDireita(removeRec(no.getFilhoDireita(), valor));
            }
        }
        // para cada nó empilhado, checamos o balanceamento
        checarBalanceamento(novaRaizSub);
    }

    // TODO: debugar a remoção também
    @Override
    public T remover(T valor) {
        if (this.raiz == null) {
            return null;
        }

        No<T> auxNoTeste = this.raiz;
        removeRec(auxNoTeste, valor);
    }
    */

}
