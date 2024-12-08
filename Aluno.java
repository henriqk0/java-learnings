public class Aluno extends Usuario{
    private double saldo;

    public Aluno(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
        this.saldo = 0;
    }

    public String toString() {
        return super.toString() + " (Saldo: R$" + String.format("%.2f", this.saldo) + ")";
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
