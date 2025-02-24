package item;

public class Pocao extends Item {
    private double cura;
    private double regeneracao;

    // Construtor
    public Pocao(String nome, String descricao, int nivel, double custo, double pocao, double mana){
        super(nome, descricao, nivel, custo);
        this.cura = Math.max(0, cura);
        this.regeneracao = Math.max(0, regeneracao);
    }

    // Get

    public double getCura(){
        return cura;
    }

    public double getRegeneracao(){
        return regeneracao;
    }


}
