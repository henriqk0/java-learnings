/*
 * To change this license header, choose License Headers in Project Properties.  * To change this template file, choose Tools | Templates * and open the template in the editor.
 */
package main.java.com.lib;

import main.java.com.lib.IArvoreBinaria;

import java.util.Stack;

import java.util.Comparator;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador;
  
    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
    }
    
    public void adicionar(T valor) {
        No<T> novo = new No<T>(valor);

        //Arvore vazia
        if (raiz == null) {
            raiz = novo;
            return;
        }

        No<T> atual = raiz;
        No<T> pai = null;

        while (atual != null) {
            pai = atual;
            int cmp = comparador.compare(valor, atual.getValor());

            if (cmp < 0) {
                atual = atual.getFilhoEsquerda();
            } else if (cmp > 0) {
                atual = atual.getFilhoDireita();
            } else {
                // Valor duplicado, não insere
                return;
            }
    }

        // Insere no lado correto do pai
        int cmpFinal = comparador.compare(valor, pai.getValor());
        if (cmpFinal < 0) {
            pai.setFilhoEsquerda(novo);
        } else {
            pai.setFilhoDireita(novo);
        }
    }

    public T pesquisar(T valor) {
        No<T> encontrado = pesquisarRecursivo(raiz, valor);
        return encontrado != null ? encontrado.getValor() : null;
    }

    private No<T> pesquisarRecursivo(No<T> atual, T valor) {
        if (atual == null) {
            return null;
        }

        int cmp = comparador.compare(valor, atual.getValor());

        if (cmp == 0) {
            return atual;
        } else if (cmp < 0) {
            return pesquisarRecursivo(atual.getFilhoEsquerda(), valor);
        } else {
            return pesquisarRecursivo(atual.getFilhoDireita(), valor);
        }
    }

    /**
     * Método para pesquisar usando um Comparator diferente (fazendo varredura completa).
     */
    public T pesquisar(T valor, Comparator<T> comparadorAlternativo) {
        No<T> encontrado = pesquisarComComparator(raiz, valor, comparadorAlternativo);
        return encontrado != null ? encontrado.getValor() : null;
    }

    private No<T> pesquisarComComparator(No<T> atual, T valor, Comparator<T> comparadorAlt) {
        if (atual == null) {
            return null;
        }

        if (comparadorAlt.compare(valor, atual.getValor()) == 0) {
            return atual;
        }

        No<T> encontradoEsq = pesquisarComComparator(atual.getFilhoEsquerda(), valor, comparadorAlt);
        if (encontradoEsq != null) {
            return encontradoEsq;
        }

        return pesquisarComComparator(atual.getFilhoDireita(), valor, comparadorAlt);
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

                        if (auxNoAnterior == auxNoTeste) {this.raiz = auxNoSucessorInOrd;} // removendo o nó da raiz da árvore
                        else { // removendo um nó de uma subarvore
                            if (auxNoAnterior.getFilhoDireita() == auxNoTeste) { // o no a ser removido estava à direita do no anterior
                                 auxNoAnterior.setFilhoDireita(auxNoSucessorInOrd); }
                            else { auxNoAnterior.setFilhoEsquerda(auxNoSucessorInOrd); } // '' '' '' '' '' à esquerda do no anterior
                        }
                    }

                    else {
                        if (auxNoAnterior != auxNoTeste) { // nó a ser removido com apenas um filho e não é o nó da raiz da árvore
                            if (auxNoAnterior.getFilhoDireita() == auxNoTeste) { // o no removido estava à direita do no anterior
                                auxNoAnterior.setFilhoDireita(auxNoTeste.getFilhoEsquerda());
                                auxNoTeste.setFilhoDireita(null);  // tira a referencia do ponteiro do no removido
                            }
                            else if (auxNoAnterior.getFilhoEsquerda() == auxNoTeste) {  // '' '' '' '' '' à esquerda do no anterior
                                auxNoAnterior.setFilhoEsquerda(auxNoTeste.getFilhoEsquerda());
                                auxNoTeste.setFilhoEsquerda(null); // tira a referencia do ponteiro do no removido
                            }
                        } 
                        else { // no a ser removido era a raiz da arvore e tinha apenas um filho
                            if (auxNoTeste.getFilhoEsquerda() != null) { // sendo um filho à esquerda
                                this.raiz = auxNoTeste.getFilhoEsquerda(); // obtem a nova raiz
                                auxNoTeste.setFilhoEsquerda(null); // tira a referencia do ponteiro do no removido
                            }
                            else { // sendo um filho à direita 
                                this.raiz = auxNoTeste.getFilhoDireita(); // obtem a nova raiz
                                auxNoTeste.setFilhoDireita(null);  // tira a referencia do ponteiro do no removido 
                            }
                        }
                    }
                }
                auxNoTeste = null; // "desaloca o no removido"
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

    /*
    //Balanceia a arvore de acordo com a quantidade de MB de memoria utilizadas por cada processo
    public void geraArvorePerfeitamenteBalanceada(double min, double max){
        //Se o valor da menor qtd. de memoria for menor ou igual ao maior valor é sinal que ainda preciso inserir elementos na árvore
        //Senão essa recursão acabou...
        if (min <= max){
            //Calculo o uso médio de memória  desta geração e insiro um processo com esse valor na árvore
            double media = (min+max)/2;
            double valor = media;
            this.adicionar(valor);
            //Chamo recursivamente para continuar inserindo os elementos com matrículas menores que a média
            geraArvorePerfeitamenteBalanceada(min,media-1);
            //Chamo recursivamente para continuar inserindo os elementos com matrículas maiores que a média
            geraArvorePerfeitamenteBalanceada(media+1,max);
        }
    }*/

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

            if (auxNo == null) { // nó nulo: não faz nada
                continue;
            }

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
