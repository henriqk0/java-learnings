import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends Usuario implements Salvavel{
    private String email;

    public Admin(String cpf, String nome, String senha, String email) {
        super(cpf, nome, senha);
        this.email = email;
    }

    public void salvarArq(BufferedWriter b) throws IOException{
        try {
            b.write("ADM");
            b.newLine();
            b.write(this.cpf);
            b.newLine();
            b.write(this.nome);
            b.newLine();
            b.write(this.getSenha());
            b.newLine();
            b.close();
            throw new Exception();
        } catch(Exception e) {
            System.out.println("Não foi possível salvar o Administrador");
        }
    }

    public String toString() {

        return this.nome + " - CPF: " + this.cpf + " (ADMIN)";
    }

}
