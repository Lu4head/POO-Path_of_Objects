package habilidade;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class ListaHabilidades {
    private static final List<Habilidade> habilidades_dano = new ArrayList<>();
    private static final List<Habilidade> habilidades_suporte = new ArrayList<>();
    private static final List<Habilidade> habilidades_buffs = new ArrayList<>();

    static{
        habilidades_dano.add(new Habilidade_Ofensiva("Ataque Sorrateiro", "Agora você me vê... agora não vê mais!", 0, 1, 10));
        habilidades_dano.add(new Habilidade_Ofensiva("Bola de fogo", "Mana condensada envolta em chamas", 10, 1, 20));
        habilidades_dano.add(new Habilidade_Ofensiva("Raio", "Um raio de energia pura", 20, 1, 30));
        habilidades_dano.add(new Habilidade_Ofensiva("Tempestade de gelo", "O mais puro frio", 30, 5, 70));

        habilidades_suporte.add(new Habilidade_Recuperacao("Cura Mágica", "Cura mágica leve", 10, 1, 10, 0));
        habilidades_suporte.add(new Habilidade_Recuperacao("Meditação", "Uma meditação profunda capaz de recuperar mana.", 0, 1,  0, 10));
        habilidades_suporte.add(new Habilidade_Recuperacao("Cura Divina", "A luz divina da recuperação", 20, 5, 100, 0));
        habilidades_suporte.add(new Habilidade_Recuperacao("Cura de Água", "Cura de água pura", 30, 3, 50, 20));

        habilidades_buffs.add(new Habilidade_Buff("Aura da ira", "Aumenta o dano do personagem", 1, 1, 1, 1, 1.1));
        habilidades_buffs.add(new Habilidade_Buff("Benção do anjo", "Aumenta a vida max do personagem", 1, 1, 1.3, 1, 1));
        habilidades_buffs.add(new Habilidade_Buff("Fonte de energia", "Aumenta a mana max do personagem", 1, 1, 1, 1.2, 1));
    }

    public static List<Habilidade> sortearHabilidades(){
        Random random = new Random();
        List<Habilidade> habilidades_sorteadas = new ArrayList<>();
        for( int i = 0; i < 3; i++){
            int lista = random.nextInt(3);
            if(lista == 0){
                int index = random.nextInt(habilidades_dano.size());
                habilidades_sorteadas.add(habilidades_dano.get(index));
            }
            else if(lista == 1){
                int index = random.nextInt(habilidades_suporte.size());
                habilidades_sorteadas.add(habilidades_suporte.get(index));
            }
            else if(lista == 2){
                int index = random.nextInt(habilidades_buffs.size());
                habilidades_sorteadas.add(habilidades_buffs.get(index));
            }
        }
        return habilidades_sorteadas;
    }
}
