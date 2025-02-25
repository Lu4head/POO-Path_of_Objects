package personagem;

import java.util.List;
import java.util.Random;
import item.Item;
import item.Listaitens;

public class Monstro{
    private String nome;
    private double vida;
    private int nivel;
    private int experiencia;
    private List<Item> drops;
    private double dano;
    // drop de moedas

    // Construtor

    public Monstro(String nome,double vida, int experiencia, int nivel, double dano){
        this.experiencia = Math.max(0,experiencia);
        this.drops = Listaitens.getItensDrop();
        this.dano = Math.max(1,dano);
        this.nome = nome;
        this.vida = Math.max(0, vida);
        this.nivel = Math.max(1, nivel);
    }

    public Item dropsItem() {
        if (drops.isEmpty()){
            System.out.println("Nenhuma item a se dropar");
            return null;
        }
        Random random = new Random();
        int indice = random.nextInt(drops.size());
        return drops.get(indice);
    }

    public int darExperiencia(){
        if (experiencia == 0){
            System.out.println("Infelizmente sem experiencia para ser obtida");
            return 0;
        }

        Random random = new Random();
        int chance = random.nextInt(100);
        
        if (chance > 50){
            int extra = (int) (experiencia * (random.nextInt(50) / 100.0));
            return experiencia + extra;
        }
        return experiencia;
    }

    // Get

    public double getDano(){
        return dano;
    }

    public double getVida(){
        return vida;
    }

    public String getNome(){
        return nome;
    }

    public int getNivel(){
        return nivel;
    }
    

    // Set

    public void setVida(double vida){
        this.vida = vida;
    }


}
