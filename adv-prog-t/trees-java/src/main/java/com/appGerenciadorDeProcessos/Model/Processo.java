package main.java.com.appGerenciadorDeProcessos.Model;


public class Processo {
    private int id;
    private String nome;
    // Uso de CPU em porcentagem
    private double usoCPU;
    // Uso de memória variando entre 0MB e 16000MB (para permitir o encaixe de mais nós de processo)
    private double usoMemoria;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getUsoCPU() {
        return usoCPU;
    }

    public double getUsoMemoria() {
        return usoMemoria;
    }

    public Processo(int id, String nome, double usoCPU, double usoMemoria){
        Memoria m = Memoria.getInstance();
        Cpu c = Cpu.getInstance();
        try {
            m.setAvailableSize(usoMemoria);
            c.setAvailableAmount(usoCPU);
            this.id = id;
            this.nome = nome;
            this.usoCPU = usoCPU;
            this.usoMemoria = usoMemoria;
        } catch(Exception e){
            System.out.println(e.getMessage());//Printa mensagem de erro
        }
    }

    public Processo(int id){
        this.id = id;
    }

     public Processo(String nome){
        this.nome = nome;
    }

    public Processo(double usoCPU){
        this.usoCPU = usoCPU;
    }
    
    @Override
    public String toString() {
        return "Processo {" +
                " Id: " + id +
                ", Nome: " + nome +
                ", UsoCPU: " + usoCPU +
                ", UsoMemoria: " + usoMemoria + " MB" +
                '}';
    }
    
}
