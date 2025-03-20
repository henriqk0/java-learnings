public class Sala {
    private String bloco, sala, andar;

    public Sala(String bloco, String sala, String andar) {
        this.bloco = bloco;
        this.sala = sala;
        this.andar = andar;
    }

    public String getNome() {
        return this.bloco+this.sala+this.andar;
    }
    
}
