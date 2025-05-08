/*
 * To change this license header, choose License Headers in Project Properties.  * To change this template file, choose Tools | Templates * and open the template in the editor.
 */
package com.lib;

import java.util.Stack;
import java.util.Comparator;

/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
  
    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
    }
    
    @Override
    public void adicionar(T novoValor) {
        No<T> atual = this.raiz;

        while (atual != null){
            //Se o valor for igual, também vai para a esquerda
            if (comparador.compare(novoValor, atual.getValor()) <= 0) {
                atual = atual.getFilhoEsquerda();
            }
            else{
                atual = atual.getFilhoDireita();
            }
        }

        atual = new No<T>(novoValor);
    }

    @Override
    public T pesquisar(T valor) {
        return pesquisarRecursivo(this.raiz, valor);
    }

    private T pesquisarRecursivo(No<T> raiz, T valor){
        if (raiz == null || (comparador.compare(raiz.getValor(), valor) == 0)){
            return raiz.getValor();
        }

        if (comparador.compare(valor, raiz.getValor()) <= 0) return pesquisarRecursivo(raiz.getFilhoEsquerda(), valor);
        return pesquisarRecursivo(raiz.getFilhoDireita(), valor);
    }

    @Override
    public T pesquisar(T valor, Comparator comparador) {
        return pesquisarRecursivo(this.raiz, valor, comparador);
    }

    private T pesquisarRecursivo(No<T> raiz, T valor, Comparator comparador){
        if (raiz == null) {
            return null;
        }
        else if (comparador.compare(raiz.getValor(), valor) == 0){
            return raiz.getValor();
        }

        T returnEsq = pesquisarRecursivo(raiz.getFilhoEsquerda(), valor, comparador);
        if (returnEsq != null){
            return returnEsq;
        }
        return pesquisarRecursivo(raiz.getFilhoDireita(), valor, comparador);
    }

    @Override
    public T remover(T valor) {
        if (raiz == null) {
            return null;
        }

        No<T> auxNoTeste = this.raiz;
        No<T> auxNoAnterior = auxNoTeste;

        while (auxNoTeste != null) { 
            if (this.comparador.compare(auxNoTeste.getValor(), valor) == 0)  {
                
                if (auxNoTeste.getFilhoDireita() != null || auxNoTeste.getFilhoEsquerda() != null) {

                    // nó a ser removido possui 2 filhos
                    if (auxNoTeste.getFilhoDireita() != null && auxNoTeste.getFilhoEsquerda() != null) { 
                        No<T> auxNoSucessorInOrd = auxNoTeste.getFilhoEsquerda();

                        while (auxNoSucessorInOrd.getFilhoDireita() != null) {
                            auxNoSucessorInOrd = auxNoSucessorInOrd.getFilhoDireita();
                        }
                        auxNoSucessorInOrd.setFilhoDireita(auxNoTeste.getFilhoDireita());
                    }

                    else {
                        if (auxNoAnterior != auxNoTeste) { // nó a ser removido com apenas um filho e não é o nó da raiz 
                            if (auxNoTeste.getFilhoEsquerda() != null) {
                                if (auxNoAnterior.getFilhoDireita() == auxNoTeste) {
                                    auxNoAnterior.setFilhoDireita(auxNoTeste.getFilhoEsquerda());
                                }
                                else if (auxNoAnterior.getFilhoEsquerda() == auxNoTeste) {
                                    auxNoAnterior.setFilhoEsquerda(auxNoTeste.getFilhoEsquerda());
                                }
                                auxNoTeste.setFilhoEsquerda(null);
                            }
                            else {
                                if (auxNoAnterior.getFilhoDireita() == auxNoTeste) {
                                    auxNoAnterior.setFilhoDireita(auxNoTeste.getFilhoDireita());
                                }
                                else if (auxNoAnterior.getFilhoDireita() == auxNoTeste) {
                                    auxNoAnterior.setFilhoEsquerda(auxNoTeste.getFilhoDireita());
                                }
                                auxNoTeste.setFilhoDireita(null);
                            }
                        } 
                    }
                }
                auxNoTeste = null;
                return valor;
            }

            else {
                if (this.comparador.compare(auxNoTeste.getValor(), valor) < 0) {
                    auxNoAnterior = auxNoTeste;
                    auxNoTeste = auxNoTeste.getFilhoEsquerda();
                }
                else {
                    auxNoAnterior = auxNoTeste;
                    auxNoTeste = auxNoTeste.getFilhoDireita();
                }
            }
        }
        return null;
    }

    @Override
    public int altura() {
        int alturaFolhaMax = 0;

        Stack<TuplaNoAltura<T>> stack = new Stack<>();
        stack.push(new TuplaNoAltura<>(this.raiz, 0));

        while (stack.isEmpty() == false) {
            TuplaNoAltura<T> auxTuplaNoAltura = stack.peek();
            stack.pop();

            int alturaNo = auxTuplaNoAltura.getAltura(); 
            No<T> auxNo = auxTuplaNoAltura.getNo();

            if (auxNo.getFilhoDireita() == null && auxNo.getFilhoEsquerda() == null) { // folha encontrada: tira o maximo entre a altura até este no e a ultima maior altura de folha
                alturaFolhaMax = Math.max(alturaFolhaMax, alturaNo);
            }
            else { // empilha uma nova tupla com a altura incrementada relativa ao nó filho
                if (auxNo.getFilhoDireita() != null) {
                    stack.push(new TuplaNoAltura<>(auxNo.getFilhoDireita(), alturaNo + 1)); 
                }
                if (auxNo.getFilhoEsquerda() != null) {
                    stack.push(new TuplaNoAltura<>(auxNo.getFilhoEsquerda(), alturaNo + 1));
                }
            }
        }
        return alturaFolhaMax;
        
    }
       
    
    @Override
    public int quantidadeNos() {
        int quantidade = 0;
        if (this.raiz == null) {
            return quantidade;
        }

        Stack<No<T>> stack = new Stack<>();
        stack.push(this.raiz);

        while (stack.isEmpty() == false) {
            No<T> auxNo = stack.peek();
            stack.pop();
            quantidade += 1;

            if (auxNo.getFilhoDireita() != null) {
                stack.push(auxNo.getFilhoDireita());
            }
            if (auxNo.getFilhoEsquerda() != null) {
                stack.push(auxNo.getFilhoEsquerda());
            }
        }
        return quantidade;
    }

    @Override
    public String caminharEmNivel() { // ou por prefixo
        String valoresFinais = "[";
        if (this.raiz == null) {
            return valoresFinais += "]";
        }

        Stack<No<T>> stack = new Stack<>();
        stack.push(this.raiz);

        while (stack.isEmpty() == false) {
            No<T> auxNo = stack.peek();
            stack.pop();
            valoresFinais += auxNo.getValor();
            valoresFinais += " \n ";

            if (auxNo.getFilhoDireita() != null) {
                stack.push(auxNo.getFilhoDireita());
            }
            if (auxNo.getFilhoEsquerda() != null) {
                stack.push(auxNo.getFilhoEsquerda());
            }
        }

        valoresFinais += "]";
        return valoresFinais;
    }

    @Override
    public String caminharEmOrdem() {
        String valoresFinais = "[";
        Stack<No<T>> stack = new Stack<>();

        No<T> auxNo = this.raiz;

        while (auxNo != null || stack.empty() == false) {
            while (auxNo != null) {
                stack.push(auxNo);
                auxNo = auxNo.getFilhoEsquerda();
            }
            auxNo = stack.peek();
            stack.pop();

            valoresFinais = valoresFinais + auxNo.getValor() + " \n ";

            auxNo = auxNo.getFilhoDireita();
        }
        return valoresFinais += "]";
    }
        
}
