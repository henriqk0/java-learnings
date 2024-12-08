public class Item {
    private Produto p;
    private Integer qtd;

    public Item(Produto p, Integer qtd) {
        this.p = p;
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
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
