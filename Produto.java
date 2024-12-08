public class Produto {
    private String nome;
    private Integer qtd;
    private Double valor;
    private String cod;


    public Integer getQtd() {
        return qtd;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public String getCod() {
        return cod;
    }

    public Produto(String nome, Integer qtd, Double valor, String cod) {
        this.nome = nome;
        this.qtd = qtd;
        this.valor = valor;
        this.cod = cod;
    }

    @Override
    public String toString() {
        return this.getCod() + ": " + this.getNome();
    }

    public void retirarDeEstoque(Integer qtd_ret) {
        this.qtd -= qtd_ret;
    }
}
