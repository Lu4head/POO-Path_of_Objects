package habilidade;

import personagem.Personagem;

public class Habilidade_Recuperacao extends Habilidade{
    private double cura;
    private double mana;

    public Habilidade_Recuperacao(String nome, String descricao, double mana, int nivel, double cura, double mana_recuperada){
        super(nome, descricao, mana, nivel);
        this.cura = cura;
        this.mana = mana_recuperada;
    }

    public double getCura(){
        return cura;
    }

    public double getMana_Recuperada(){
        return mana;
    }

    public double usarHabilidade(Personagem jogador){
        if(jogador.getVida_Atual() + cura > jogador.getVida()){
            jogador.setVida_Atual(cura);
        } 
        else{
            jogador.setVida_Atual(jogador.getVida_Atual() + cura);
        }
        if(jogador.getMana_Atual() + mana > jogador.getMana()){
            jogador.setMana_Atual(mana);
        }
        else{
            jogador.setMana_Atual(jogador.getMana_Atual() + mana);
        }
        return 0;
    }
}