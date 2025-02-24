import personagem.Guerreiro;
import eventos.Eventos;

public class Main {
    public static void main(String[] args) {
        Guerreiro jogador = new Guerreiro("teste");
        Eventos eventos = new Eventos(jogador);
        eventos.iniciar();
    }
}
