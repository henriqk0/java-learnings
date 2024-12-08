public class Admin extends Usuario{
    private String email;

    public Admin(String cpf, String nome, String senha, String email) {
        super(cpf, nome, senha);
        this.email = email;
    }

    public String toString() {
        return super.toString() + " (ADMIN)";
    }

}
