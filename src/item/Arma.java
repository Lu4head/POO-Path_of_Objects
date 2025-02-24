package item;

public class Arma extends Item{
    private double dano;
    private double mana;

    // Construtor
    public Arma(String nome, String descricao, int nivel, double custo, double dano, double mana){
        super(nome, descricao, nivel, custo); // necessidade de chamar o metodo super (pq estamos criando um novo construtor) para invocar o construtor da classe pai
        this.dano = Math.max(0,dano);
        this.mana = Math.max(0,mana);
    }

    // get

    public double getDano(){
        return dano;
    }

    public double getMana(){
        return mana;
    }


}
