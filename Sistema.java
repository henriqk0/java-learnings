import java.util.ArrayList;

public class Sistema {
    private ArrayList<Aluno> alunos;
    private ArrayList<Admin> adms;
    private ArrayList<Produto> prods;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Sala> salas;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.adms = new ArrayList<>();
        this.prods = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public ArrayList<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void addAdmin(Admin a) {
        this.adms.add(a);
    }

    public void addAluno(Aluno a) {
        this.alunos.add(a);
    }

    public void addProd(Produto p) {
        this.prods.add(p);
    }

    public void addPedido(Pedido p) {
        this.pedidos.add(p);
    }

    public void addSala(Sala s) {
        this.salas.add(s);
    }

    public Aluno getAluno(String cpf) {
        for(Aluno a : this.alunos) {
            if (cpf.equals(a.getCPF())) return a;
        }

        return null;
    }

    public Admin getAdmin(String cpf) {
        for(Admin a : this.adms) {
            if (cpf.equals(a.getCPF())) return a;
        }

        return null;
    }    

    public Produto getProduto(String cod) {
        for (Produto p : this.prods) {
            if (cod.equals(p.getCod())) return p;
        }

        return null;
    }

    public Produto getNomeProduto(String nome) {
        for (Produto p : this.prods) {
            if (  
                ( nome.toUpperCase() ).equals( (p.getNome()).toUpperCase())  
            ) { return p; } 
        }

        return null;
    }

    public Pedido getPedido(String cod) {
        for (Pedido p : this.filtrarPedidos(true)) {
            if (cod.equals(p.getCod())) return p;
        }

        return null;
    }

    public Sala getSala(String nome) {
        for (Sala s : this.salas) {
            if (nome.equals(s.getNome())) return s;
        }

        return null;
    }

    public boolean sistemaVazio() {
        return this.adms.size() == 0;
    }

    public String gerarCodigoProduto() {
        return "PROD-" + (this.prods.size() + 1);
    }

    public String gerarCodigoPedido() {
        return "PEDIDO-" + (this.pedidos.size() + 1);
    }

    public void listarProdutos() {
        for (Produto p : this.prods) {
            System.out.println(p + " (QTD: " + p.getQtd() + ")");
        }

    }

    public void listarProdutos(Pedido p) {
        for (Item it : p.getCarrinho()) {
            System.out.println(it);
        }

    }

    public void listarSalas() {
        for (Sala s : this.salas) {
            System.out.println(s.getNome());
        }

    }

    public ArrayList<Pedido> filtrarPedidos(boolean disponivel) {
        ArrayList<Pedido> disponiveis = new ArrayList<>();
        for (Pedido p: this.pedidos){
            if (p.disponivel() == disponivel) {
                disponiveis.add(p);
            }
        }

        return disponiveis;
    }

    public ArrayList<Pedido> filtrarPedidos(Aluno a) {
        ArrayList<Pedido> pedidos_do_aluno = new ArrayList<>();
        for (Pedido p: this.pedidos){
            if (p.getCliente() == a ) {
                pedidos_do_aluno.add(p);
            }
        }
        
        return pedidos_do_aluno;
    }

}
