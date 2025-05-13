package main.java.com.lib;

public class TuplaNoAltura<T> {
    private No<T> no;
    private int altura;

    public TuplaNoAltura(No<T> no, int altura) {
        this.no = no;
        this.altura = altura;
    }

    public No<T> getNo() {
        return no;
    }
    
    public int getAltura() {
        return altura;
    }
}
