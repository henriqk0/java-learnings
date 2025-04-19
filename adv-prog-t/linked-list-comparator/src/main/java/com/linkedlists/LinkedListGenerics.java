package com.linkedlists;
import java.util.Comparator;

import com.student.Student;


public class LinkedListGenerics<T> {
    private NodeGenerics<T> first;
    private NodeGenerics<T> last;
    private int length;
    private final boolean ordered;
    private Comparator<T> comparator; 

    public LinkedListGenerics(boolean itsOrdered, Comparator<T> comparator){ 
        this.first = this.last = null;
        this.length = 0;
        this.ordered = itsOrdered;
        this.comparator = comparator;
    }

    public boolean isOrdered() {
        return ordered;
    }

    @Override
    public String toString() {
        NodeGenerics<T> aux = this.first;
        String s = "[";
        while (aux != null) {
            s += aux.getValue();
            if (aux != this.last) { s+= ","; }
            aux = aux.getNext();
        }
        return (s+"]");
    }

    public boolean hasElement(T element) {
        if (this.isOrdered() && this.last.getValue().equals(element)) return true;

        NodeGenerics<T> aux = this.first;
        while (aux!=null) {
            if (aux.getValue().equals(element)) return true;
            aux = aux.getNext();
        } 
        return false;
    }

    // searches element in ordered list
    public T pesquisarOrdenado(T element) {
        if (comparator.compare(this.last.getValue(), element) < 0) return null;

        NodeGenerics<T> aux = this.first;
        while (aux!=null && comparator.compare(aux.getValue(), element) <= 0) {
            if (comparator.compare(aux.getValue(), element) == 0) return aux.value;
            aux = aux.getNext();
        } 
        return null;
    }

    // searches element in unordered list
    public T pesquisarNaoOrdenado(T element) {
        NodeGenerics<T> aux = this.first;
        while (aux!=null) {
            if (comparator.compare(aux.getValue(), element) == 0) return aux.value;
            aux = aux.getNext();
        } 
        return null;
    }

    // get element if exists
    public T pesquisar(T element) {
        if (this.isOrdered()) return this.pesquisarOrdenado(element);
        return this.pesquisarNaoOrdenado(element);
    }

    public boolean excludeElement(T element) {
        NodeGenerics<T> aux = this.first;
        NodeGenerics<T> prev = null;

        while (aux != null) {
            if (aux.getValue().equals(element)) {
                if (aux == this.first) {
                    this.first = this.first.getNext();
                    if (aux == this.last) {
                        this.last = null;
                    }
                }
                else {
                    prev.setNext(aux.getNext());
                    if (aux == this.last) {
                        this.last = prev;
                    }
                }

                this.length--;
                return true;
            }
            else {
                prev = aux;
                aux = aux.getNext();
            }
        }
        return false; 
    }

    public void adicionarOrdenado(T element) { 
        NodeGenerics<T> newElement = new NodeGenerics<T>(element);

        NodeGenerics<T> actual, previous;  
        actual = this.first;
        previous = null;

        if (this.first == null) {
            this.first = this.last = newElement;
        }
        else {
            while (actual != null && comparator.compare(actual.getValue(), element) < 0){
                previous = actual;
                actual = actual.getNext();
            }
            if (actual == null) {
                this.last.setNext(newElement);
                this.last = newElement;
            }
            else if (previous == null) { 
                newElement.setNext(this.first);
                this.first = newElement;
            }
            else {
                previous.setNext(newElement);
                newElement.setNext(actual);
            }
        }
    }

    public void adicionarNaoOrdenado(T element) { 
        NodeGenerics<T> newElement = new NodeGenerics<T>(element);

        if (this.length == 0) { 
            this.first = newElement;
            this.last = newElement;
        }
        else {
            this.last.setNext(newElement);
            this.last = newElement;
        }
    }

    // insert element method
    public void adicionar(T element) { 
        if (this.isOrdered()) {
            adicionarOrdenado(element);
        } else {
            adicionarNaoOrdenado(element);
        }
        ++this.length;
    }
}
