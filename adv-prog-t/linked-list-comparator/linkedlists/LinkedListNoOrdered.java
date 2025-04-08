package linkedlists;


public class LinkedListNoOrdered<T> {
    private NodeGenerics<T> first;
    private NodeGenerics<T> last;
    private int length;

    public LinkedListNoOrdered(){ 
        this.first = this.last = null;
        this.length = 0;
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
        NodeGenerics<T> aux = this.first;

        while (aux!=null) {
            if (aux.getValue().equals(element)) return true;
            aux = aux.getNext();
        } 
        return false;
    }

    public T getElementIfExists(T element) {
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

    public void insertElem(T element) { 
        // insert like a queue
        NodeGenerics<T> newElement = new NodeGenerics<T>(element);
        this.last.setNext(newElement);
        this.last = newElement;
        ++this.length;
    }
}

   
