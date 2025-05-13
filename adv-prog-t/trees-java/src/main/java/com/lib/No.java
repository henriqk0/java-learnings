/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.lib;

public class No<T> {
    
    private T valor;
    private No<T> filhoDireita;
    private No<T> filhoEsquerda;

    
    public No(T valor){
        this.valor = valor;
        this.filhoDireita = null;
        this.filhoEsquerda = null;
    }
    
    /**
     * @return the valor
     */
    public T getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * @return the filhoDireita
     */
    public No<T> getFilhoDireita() {
        return filhoDireita;
    }

    /**
     * @param filhoDireita the filhoDireita to set
     */
    public void setFilhoDireita(No<T> filhoDireita) {
        this.filhoDireita = filhoDireita;
    }

    /**
     * @return the filhoEsquerda
     */
    public No<T> getFilhoEsquerda() {
        return filhoEsquerda;
    }

    /**
     * @param filhoEsquerda the filhoEsquerda to set
     */
    public void setFilhoEsquerda(No<T> filhoEsquerda) {
        this.filhoEsquerda = filhoEsquerda;
    }

    private No<T> rotacaoDireita(No<T> raiz){
        No<T> filho = raiz.getFilhoEsquerda();

        raiz.setFilhoEsquerda(filho.getFilhoDireita());
        filho.setFilhoDireita(raiz);

        return filho;
    }

    private No<T> rotacaoEsquerda(No<T> raiz){
        No<T> filho = raiz.getFilhoDireita();
        raiz.setFilhoDireita(filho.getFilhoEsquerda());
        filho.setFilhoEsquerda(raiz);

        return filho;
    }
    private No<T> rotacaoEsquerdaDireita(No<T> raiz){
        raiz.setFilhoEsquerda(this.rotacaoEsquerdaDireita(raiz.getFilhoEsquerda()));

        return this.rotacaoDireita(raiz);
    }
    private No<T> rotacaoDireitaEsquerda(No<T> raiz){
        raiz.setFilhoDireita(this.rotacaoDireitaEsquerda(raiz.getFilhoDireita()));

        return this.rotacaoEsquerda(raiz);
    }

    public int obterAltura(){ return obterAltura(this);}

    private int obterAltura(No<T> raiz){
        if(raiz == null){
            return -1;
        }
        else{
            int hd = this.obterAltura(raiz.getFilhoDireita());
            int he = this.obterAltura(raiz.getFilhoEsquerda());
            if(hd>he){
                return hd+1;
            }
            else{
                return he+1;
            }
        }
    }

    public int fatorBalanceamento(){
        return this.obterAltura(this.filhoDireita) - this.obterAltura(this.filhoEsquerda);
    }
}
