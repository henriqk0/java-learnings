package com.linkedlists;
import java.util.Comparator;


public class LinkedListComparator<T> {
    private NodeGenerics<T> first;
    private NodeGenerics<T> last;
    private int length;
    private final boolean ordered;
    private Comparator<T> comparator; 

    public LinkedListComparator(boolean itsOrdered, Comparator<T> comparator){ 
        this.first = this.last = null;
        this.length = 0;
        this.ordered = itsOrdered;
        this.comparator = comparator;
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
        if (this.last.getValue().equals(element)) return true;

        NodeGenerics<T> aux = this.first;
        while (aux!=null) {
            if (aux.getValue().equals(element)) return true;
            aux = aux.getNext();
        } 
        return false;
    }

    // get element if exists
    public T pesquisar(T element) {
        if (this.last.getValue().equals(element)) return this.last.getValue();

        NodeGenerics<T> aux = this.first;
        while (aux!=null) {
            if (aux.getValue().equals(element)) return aux.value;
            aux = aux.getNext();
        } 
        return null;
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

    // insert element method
    public void adicionar(T element) { 
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
        ++this.length;
    }
}
