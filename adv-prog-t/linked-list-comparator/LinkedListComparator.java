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
        }
        return (s+"]");

    }

    public void insertElemOrdered(T element) { 
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
                newElement.setProximo(this.first);
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
