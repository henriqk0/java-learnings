import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Aluno extends Usuario implements Salvavel{
    private double saldo;

    public Aluno(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
        this.saldo = 0;
    }

    public String toString() {
        return super.toString() + " (Saldo: R$" + String.format("%.2f", this.saldo) + ")";
    }

    public void salvarArq(BufferedWriter b){
        try {
            FileWriter f = new FileWriter("salvos.txt", true);
            b.write("ALU");
            b.newLine();
            b.write(this.cpf);
            b.newLine();
            b.write(this.nome);
            b.newLine();
            b.write(this.getSenha());
            b.newLine();
            f.close();
        } catch(IOException e) {
            System.out.println("Não foi possível salvar o Aluno");
        }
    }

    public void inserirSaldo(Double valor) {
        this.saldo += valor;
        System.out.println("Seu novo saldo é R$" + String.format("%.2f", this.saldo) + ".");
    }

    public boolean saldoSuficiente(Double valor) {
        return (this.saldo >= valor);
    }
    
    public boolean retirarSaldo(Double valor) {
        if (saldoSuficiente(valor)) {
            this.saldo -= valor;
            System.out.println("Seu novo saldo é R$" + String.format("%.2f", this.saldo) + ".");
            return true;
        }
        else {
            Double diff = valor - saldo;
            System.out.println("O valor excede o saldo disponível em R$" +
            String.format("%.2f", diff)  + "." );
            return false;
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
