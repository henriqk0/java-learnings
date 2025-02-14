import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Sistema {
    private ArrayList<Aluno> alunos;
    private ArrayList<Admin> adms;
    private ArrayList<Produto> prods;
    private LinkedList<Pedido> pedidos;
    private ArrayList<Sala> salas;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.adms = new ArrayList<>();
        this.prods = new ArrayList<>();
        this.pedidos = new LinkedList<>();
        this.salas = new ArrayList<>();
    }

    public List<Pedido> getPedidos() {
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
            if (p.getQtd() > 0) { System.out.println(p + " (QTD: " + p.getQtd() + ")"); }
        }

    }

    public void listarProdutos(Pedido p) {
        // fazer o sort (mais caros primeiro?)
        Collections.sort(p.getCarrinho());
        for (Item it : p.getCarrinho()) {
            System.out.println(it);
        }

    }

    public void listarSalas() {
        for (Sala s : this.salas) {
            System.out.println(s.getNome());
        }

    }

    public List<Pedido> filtrarPedidos(boolean disponivel) {
        List<Pedido> disponiveis = new LinkedList<>();
        for (Pedido p: this.pedidos){
            if (p.disponivel() == disponivel) {
                disponiveis.add(p);
            }
        }

        return disponiveis;
    }

    public List<Pedido> filtrarPedidos(Aluno a) {
        List<Pedido> pedidos_do_aluno = new LinkedList<>();
        for (Pedido p: this.pedidos){
            if (p.getCliente() == a ) {
                pedidos_do_aluno.add(p);
            }
        }
        
        return pedidos_do_aluno;
    }

    public void salvarUsuarios() {
        List<Usuario> users = new LinkedList<>();
        users.addAll(this.alunos);
        users.addAll(this.adms);  

        for (Usuario u : users) {

        }
    }

}
