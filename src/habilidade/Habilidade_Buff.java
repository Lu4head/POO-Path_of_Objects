package habilidade;

import personagem.Personagem;

public class Habilidade_Buff extends Habilidade{
    private double buff_vida;
    private double buff_mana;
    private double buff_dano;

    public Habilidade_Buff(String nome, String descricao, double mana, int nivel, double buff_vida, double buff_mana, double buff_dano){
        super(nome, descricao, mana, nivel);
        this.buff_vida = buff_vida;
        this.buff_mana = buff_mana;
        this.buff_dano = buff_dano;
    }

    public double getBuff_vida(){
        return buff_vida;
    }

    public double getBuff_mana(){
        return buff_mana;
    }

    public double getBuff_dano(){
        return buff_dano;
    }

    public void ApplyBuff(Personagem jogador){
        jogador.setVida(jogador.getVida() * buff_vida * jogador.getNivel());
        jogador.setMana(jogador.getMana() * buff_mana * jogador.getNivel());
        jogador.setBuff_dano(jogador.getBuff_dano() * buff_dano * jogador.getNivel());
    }

    @Override
    public double usarHabilidade(Personagem jogador){
        System.out.println("Habilidades Passivas não podem ser usadas.");
        return 0;
    }
    
}
