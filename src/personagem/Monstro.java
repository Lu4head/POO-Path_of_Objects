package personagem;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import item.Item;

public class Monstro extends Personagem{
    private int experiencia;
    private List<Item> drops = new ArrayList<>();
    private double dano;

    // Construtor

    public Monstro(String nome, String classe, double vida, double mana, int experiencia, int nivel,List<Item> drops, double dano){
        super(nome, classe, vida, mana);
        super.setNivel(nivel);
        this.experiencia = Math.max(0,experiencia);
        this.drops = drops;
        this.dano = Math.max(1,dano);
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

    public double getDano(){
        return dano;
    }


}
