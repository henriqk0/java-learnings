public abstract class Usuario {
    protected String cpf, nome;
    private String senha;

    public Usuario(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }

    public boolean validarAcesso(String s) {
        return s.equals(this.senha);
    }

    public boolean alterarSenha(String atual, String nova) {
        if (validarAcesso(atual)) {
            this.senha = nova;
            System.out.println("Senha alterada com sucesso.");
            return true;
        }
        else {
            System.out.println("A senha não coincide com a atual.");
            return false;
        }
    }

    public String toString() {
        return this.nome + " - CPF: " + this.cpf;
    }

    public String getCPF() {
        return this.cpf;
    }
}
