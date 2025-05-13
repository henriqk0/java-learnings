package main.java.com.lib;

import java.util.Comparator;
import main.java.com.lib.ArvoreBinaria;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void adicionar(T valor){

    }

    @Override
    public T remover(T valor){
        return valor;
    }

    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...

}
