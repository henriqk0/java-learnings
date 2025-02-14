import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Entrada {
    /**
     * Classe com as rotinas de entrada e saída do projeto
     * @author Hilario Seibel Junior e Henrique de Souza Lima
     */

    public Scanner input;

    /**
     * Construtor da classe InputOutput
     * Se houver um arquivo input.txt, define que o Scanner vai ler deste arquivo.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt na pasta corrente, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
            // NAO ALTERE A LOCALICAÇÃO DO ARQUIVO!!
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }

    private void lerSalvos(Sistema s) {
        try{
            FileReader f = new FileReader("dados.txt");
            BufferedReader br = new BufferedReader(f);
            String linha, cpf, nome, senha, email;

            // null ou FIM 
            while ((linha = br.readLine()) != null){
                if (linha.equals("ADM")) { 
                    cpf = br.readLine();
                    nome = br.readLine();
                    senha = br.readLine();
                    email = br.readLine();
                    Admin a = new Admin(cpf, nome, senha, email);
                    s.addAdmin(a);
                }
                if (linha.equals("ALU")) {
                    cpf = br.readLine();
                    nome = br.readLine();
                    senha = br.readLine();
                    Aluno a = new Aluno(cpf, nome, senha);
                    s.addAluno(a);
                }
            }
            f.close();
        } 
        catch(FileNotFoundException e) {
            System.out.println("Arquivo inexistente.");
        }
        catch(IOException e) {
            System.out.println("Não foi possível salvar o Aluno");
        }

    }

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    private String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = this.input.nextLine();

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    private int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um ponto flutuante
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um ponto flutuante e retorna este número
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }

    /**********************/
    /** MENUS DO SISTEMA **/
    /**********************/

    /**
     * Exibe o menu principal até que o usuário opte por sair do programa.
     * @param s: Objeto a classe Sistema.
    */
    public void menu(Sistema s) throws IOException {
        this.lerSalvos(s);

        if (s.sistemaVazio()) {
            System.out.println("** Inicializando o sistema **");
            this.cadAdmin(s);
        }

        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Login.\n" +
                "0) Sair.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) login(s);
            else System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Exibe o menu do administrador até que o usuário deslogue.
     * @param a: Objeto a classe Admin.
     * @param s: Objeto a classe Sistema.
    */
    public void menu(Admin a, Sistema s) throws IOException {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar novo administrador.\n" +
                "2) Cadastrar aluno.\n" +
                "3) Cadastrar produto.\n" +
                "4) Cadastrar sala.\n" +
                "0) Logout.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) { cadAdmin(s); }
            if (op == 2) { cadAluno(s); }
            if (op == 3) { cadProduto(s); }
            if (op == 4) { cadSala(s); }
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Exibe o menu do aluno até que o usuário deslogue.
     * @param a: Objeto a classe Aluno.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Aluno a, Sistema s) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Fazer pedido.\n" +
                "2) Fazer entrega.\n" +
                "3) Meus pedidos.\n" +
                "4) Inserir crédito.\n" +
                "0) Logout.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) { fazerPedido(a, s); }
            if (op == 2) { entregarPedido(a, s); }
            if (op == 3) { listarPedidos(a, s); }
            if (op == 4) { inserirCredito(a, s); }
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    public void login(Sistema s) throws IOException {
        System.out.println("\nBem vindo! Digite seus dados de login:");
        String cpf = this.lerLinha("CPF:");
        String senha = this.lerLinha("Senha:");

        Admin adm = s.getAdmin(cpf);
        if (adm != null) {
            if (adm.validarAcesso(senha)) {
                this.menu(adm, s);
            }
            else System.out.println("Senha inválida.");
        }
        else {
            Aluno a = s.getAluno(cpf);
            if (a != null) {
                if (a.validarAcesso(senha)) {
                    this.menu(a, s);
                }
                else System.out.println("Senha inválida");
            }
            else {
                System.out.println("Usuário inexistente");
            }
        }
    }

    /***************/
    /** CADASTROS **/
    /***************/

    /**
     * Lê os dados de um novo administrador e cadastra-o no sistema.
     * @param s: Um objeto da classe Sistema
    */
    public void cadAdmin(Sistema s) throws IOException {
        System.out.println("\n** Cadastrando um novo administrador **\n");
        String cpf = this.lerLinha("Digite o cpf: ");

        while (s.getAdmin(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        String email = this.lerLinha("Digite o email: ");

        Admin a = new Admin(cpf, nome, senha, email);
        s.addAdmin(a);

        FileWriter f = new FileWriter("salvos.txt", true);
        BufferedWriter br = new BufferedWriter(f);
        a.salvarArq(br);

        System.out.println("Usuário " + a + " criado com sucesso.");
    }

    /**
     * Lê os dados de um novo aluno e cadastra-o no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadAluno(Sistema s) throws IOException {
        System.out.println("\n** Cadastrando um novo aluno **\n");
        String cpf = this.lerLinha("Digite o cpf: ");

        while (s.getAluno(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        
        Aluno a = new Aluno(cpf, nome, senha);
        s.addAluno(a);

        FileWriter f = new FileWriter("salvos.txt", true);
        BufferedWriter br = new BufferedWriter(f);
        a.salvarArq(br);
        
        System.out.println("Usuário " + a + " criado com sucesso.");
    }

    /**
     * Lê os dados de um novo produto e cadastra-o no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadProduto(Sistema s) {
        System.out.println("\n** Cadastrando um novo produto **\n");
        String nome = this.lerLinha("Digite o nome do produto: ");

        // criar um loop infinito em nome ?

        while (s.getNomeProduto(nome) != null) {
            nome = this.lerLinha("Já existe um produto com este nome. Escolha outro nome: ");
        }

        // possíveis exceptions:
        Integer qtd = this.lerInteiro("Digite a quantidade em estoque: ");
        Double valor = this.lerDouble("Digite o valor unitário do produto: ");        

        Produto p = new Produto(nome, qtd, valor, s.gerarCodigoProduto());
        s.addProd(p);

        System.out.println("Produto " + p + " criado com sucesso.");
    }
 
    /**
     * Lê os dados de uma nova sala e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadSala(Sistema s) {
        System.out.println("\n** Cadastrando uma nova sala **\n");

        String bloco = this.lerLinha("Digite o bloco (ex: para 904T, digite 9): ");
        String sala = this.lerLinha("Digite o sala (ex: para 904T, digite 04): ");
        String andar = this.lerLinha("Digite o andar (ex: para 904T, digite T): ");
        
        while (s.getSala(bloco.concat(sala.concat(andar))) != null) {
            System.out.println("Sala já existente. Digite novamente.");
            bloco = this.lerLinha("Digite o bloco (ex: para 904T, digite 9): ");
            sala = this.lerLinha("Digite o sala (ex: para 904T, digite 04): ");
            andar = this.lerLinha("Digite o andar (ex: para 904T, digite T): ");
        }

        Sala nova_sala = new Sala(bloco, sala, andar);
        s.addSala(nova_sala);

        System.out.println("Sala " + nova_sala.getNome() + " criada com sucesso.");
    }

    /**
     * Faz a leitura de uma sala
     * @param s: Objeto da classe Sistema.
     * @return O objeto da classe Sala requerido pelo usuário.
     */
    private Sala lerSala(Sistema s) {
        System.out.println("\nSalas disponíveis:");

        s.listarSalas();

        String sala_nome = this.lerLinha("Digite a sala: ");
        while (s.getSala(sala_nome) == null) {
            sala_nome = this.lerLinha("Sala inexistente. Digite uma das salas listadas: ");
        }
    
        Sala sala = s.getSala(sala_nome);
        return sala;
    }

    /**
     * Faz a leitura de um item
     * @param s: Objeto da classe Sistema.
     * @return O objeto da classe Item requerido pelo usuário.
     */
    private Item lerItem(Sistema s) {
        System.out.println("\nProdutos disponíveis");

        s.listarProdutos();
        System.out.println("");

        String cod = this.lerLinha("Digite o código do produto: ");
        while (s.getProduto(cod) == null) {
            cod = this.lerLinha("Produto inexistente. Digite um dos códigos listados: ");
        }

        Integer quantd = this.lerInteiro("Digite a quantidade de " + s.getProduto(cod) + " no pedido: ");

        while ((s.getProduto(cod).getQtd()) < quantd) {
            quantd = this.lerInteiro("Só há " + s.getProduto(cod).getQtd() + " no estoque. Digite outra quantidade: ");
        }

        Item i = new Item((s.getProduto(cod)), quantd);

        return i;
    }

    /**
     * Realização de um Pedido de um Aluno e seu registro no Sistema
     * @param a: Objeto a classe Aluno.
     * @param s: Objeto a classe Sistema.
     */
    public void fazerPedido(Aluno a, Sistema s) {
        Sala entr_sala = this.lerSala(s);

        Pedido p = new Pedido(s.gerarCodigoPedido(), a, entr_sala);

        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Inserir produto no carrinho.\n" +
                "2) Fechar pedido.\n";

        int op = this.lerInteiro(msg);

        while (op != 2) {
            if (op == 1) { 
                Item it = this.lerItem(s);
                p.getCarrinho().add(it);
            }
            if (op < 1 || op > 2) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
        
        if (a.saldoSuficiente(p.valorTotal())) { 
            p.confirmar();  // supondo que ela não vá inserir o mesmo produto mais de uma vez
            s.addPedido(p);
        }
        else {
            System.out.println("Saldo insuficiente para o pedido. Ele não será registrado.");
        }
    }

    /**
     * Permite que um aluno entregue pedidos cadastrados no sistema.
     * @param a: Objeto a classe Aluno.
     * @param s: Objeto a classe Sistema.
     */
    public void entregarPedido(Aluno a, Sistema s) {
        System.out.println("\nPedidos disponíveis para entrega:");
        this.listarPedidos(s);

        String escolhido = this.lerLinha("Digite o código do pedido: ");
        while ((s.getPedido(escolhido) == null)) {
            escolhido = this.lerLinha("Pedido inexistente. Digite um dos códigos válido: ");
        }

        (s.getPedido(escolhido)).atribuirEntregador(a);
        
        a.inserirSaldo(0.8);
    }

    /**
     * Lista os pedidos disponíveis do sistema 
     * @param a: Objeto a classe Aluno.
     */
    public void listarPedidos(Sistema s) {
        for (Pedido p : s.filtrarPedidos(true)) {
            System.out.println(p);

            System.out.println("Produtos:");
            s.listarProdutos(p);

            System.out.println("Status: " + p.status());

            System.out.println("Valor total: R$" + String.format("%.2f", p.valorTotal()));
        }
    }

    /**
     * Lista os pedidos de um aluno 
     * @param a: Objeto a classe Aluno.
     * @param s: Objeto a classe Sistema.
     */
    public void listarPedidos(Aluno a, Sistema s) {
        System.out.println("\nPedidos de " + a);
        System.out.println("*");

        List<Pedido> ordP = s.filtrarPedidos(a);
        Collections.sort(ordP);

        for (Pedido p : ordP) {
            System.out.println(p);

            System.out.println("Produtos:");
            s.listarProdutos(p);

            System.out.println("Status: " + p.status());

            System.out.println("Valor total: R$" + String.format("%.2f", p.valorTotal()));
            System.out.println("*");
        }
    }

    /**
     * Insere o crédito ao saldo de um aluno do sistema
     * @param s: Objeto a classe Sistema.
     */
    public void inserirCredito(Aluno a, Sistema s) {
        Double valor = lerDouble("Digite um valor a ser inserido no saldo do aluno: ");
        a.inserirSaldo(valor);
    }
}