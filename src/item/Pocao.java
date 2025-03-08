package item;

public class Pocao extends Item {
    private double cura;
    private double regeneracao;

    // Construtor
    public Pocao(String nome, String descricao, double custo, double pocao, double mana){
        super(nome, descricao, custo);
        this.cura = Math.max(0, pocao);
        this.regeneracao = Math.max(0, mana);
    }

    // Get

    public double getCura(){
        return cura;
    }

    public double getRegeneracao(){
        return regeneracao;
    }


}
