/*
 * To change this license header, choose License Headers in Project Properties.  * To change this template file, choose Tools | Templates * and open the template in the editor.
 */
package com.lib;

import java.util.Stack;
import java.util.ArrayList;
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
        else if (this.comparador.compare(raiz.getValor(), valor) == 0) {
            
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private T removerRecursivo(No<T> raiz, T valor) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String caminharEmNivel() {
        String valoresFinais = "";
        if (this.raiz == null) {
            return valoresFinais;
        }

        Stack<No<T>> stack = new Stack<>();
        stack.push(this.raiz);

        while (stack.isEmpty() == false) {
            No<T> auxNo = stack.peek();
            stack.pop();
            valoresFinais += auxNo.getValor();

            if (auxNo.getFilhoDireita() != null) {
                stack.push(auxNo.getFilhoDireita());
            }
            if (auxNo.getFilhoEsquerda() != null) {
                stack.push(auxNo.getFilhoEsquerda());
            }
        }
        return valoresFinais;

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }

    @Override
    public String caminharEmOrdem() {
        String valoresFinais = "";
        Stack<No<T>> stack = new Stack<>();

        No<T> auxNo = this.raiz;

        while (auxNo != null || stack.empty() == false) {
            while (auxNo != null) {
                stack.push(auxNo);
                auxNo = auxNo.getFilhoEsquerda();
            }
            auxNo = stack.peek();
            stack.pop();

            valoresFinais = valoresFinais + auxNo.getValor();

            auxNo = auxNo.getFilhoDireita();
        }
        return valoresFinais;
    }
        
}
