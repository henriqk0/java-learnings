public class NodeGenerics<T> {
    public T value;
    public NodeGenerics<T> next;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodeGenerics<T> getNext() {
        return next;
    }

    public void setNext(NodeGenerics<T> next) {
        this.next = next;
    }

    public NodeGenerics(T value){
        this.value = value;
        this.next = null;
    }

}
