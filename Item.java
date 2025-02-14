public class Item implements Comparable<Item> {
    private Produto p;
    private Integer qtd;

    public Item(Produto p, Integer qtd){
        this.p = p;
        this.qtd = qtd;
    }

    public int compareTo(Item p){
        if (this.qtd > p.qtd) return -1; 
        if (this.qtd < p.qtd) return 1;

        return 0;
    }

    @Override
    public String toString() {
        return p.getCod() + ": " + p.getNome() + " (QTD: " + this.getQtd() + ")";
    }

    public Produto getProd() {
        return p;
    }

    public Integer getQtd() {
        return qtd;
    }

    public Double valorTotal() {
        Double valor;

        valor = p.getValor() * this.getQtd();
        return valor;
    }


}
