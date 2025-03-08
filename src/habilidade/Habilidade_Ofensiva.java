package habilidade;

public class Habilidade_Ofensiva extends Habilidade{
    private double dano;

    public Habilidade_Ofensiva(String nome, String descricao, double mana, int nivel, double dano){
        super(nome, descricao, mana, nivel);
        this.dano = dano;
    }

    public double getDano(){
        return dano;
    }

    public void setDano(double dano){
        this.dano = dano;
    }

}
