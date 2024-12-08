import java.util.ArrayList;

public class Pedido {
    private String cod; 
    private Aluno cliente;
    private Aluno entregador;
    private Sala s; 
    private ArrayList<Item> carrinho;
    private boolean entregue;
    
    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public String getCod() {
        return cod;
    }

    public Aluno getCliente() {
        return cliente;
    }

    public Aluno getEntregador() {
        return entregador;
    }

    public Sala getS() {
        return s;
    }

    public ArrayList<Item> getCarrinho() {
        return carrinho;
    }

    public boolean isEntregue() {
        return entregue;
    }

    @Override
    public String toString() {
        return "\nCódigo do Pedido: " + this.getCod();
    }

    public Pedido(String cod, Aluno cliente,  Sala s) {
        this.cod = cod;
        this.cliente = cliente;
        this.entregador = null;
        this.s = s;
        this.carrinho = new ArrayList<>();
        this.entregue = false;
    }

    public void atribuirEntregador(Aluno entregador) {
        this.entregador = entregador;
        this.marcarComoEntregue();
    }

    public boolean disponivel() {
        if (this.getEntregador() == null) {
            return true;
        }
        return false;
    }

    public String status() {

        if (this.disponivel()) {
            return "Em aberto";
        }
        else {
            return "Entregue";
        }
    }

    public Double valorTotal() {
        Double valor = 0.0; 

        for (Item i : this.getCarrinho()) {
            valor += i.valorTotal();
        }

        return valor;
    }

    public void marcarComoEntregue() {
        if (this.isEntregue() == false) {
            setEntregue(true);
        }
    }

    public void confirmar() {
        // saldo maior que a taxa (80% de 1 vai para o entregador)
        if (this.getCliente().retirarSaldo(this.valorTotal() + 1.0)) {
            for (Item in_p : this.getCarrinho()) {
                in_p.getProd().retirarDeEstoque(in_p.getQtd());
            }
        }
    }
}
