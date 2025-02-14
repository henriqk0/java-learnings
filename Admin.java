import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends Usuario implements Salvavel{
    private String email;

    public Admin(String cpf, String nome, String senha, String email) {
        super(cpf, nome, senha);
        this.email = email;
    }

    public void salvarArq(BufferedWriter b){
        try {
            FileWriter f = new FileWriter("salvos.txt", true);
            b.write("ADM");
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

    public String toString() {
        return super.toString() + " (ADMIN)";
    }

}
