import java.util.Scanner;

public class EscolherLetra {
    private Player player;
    private PalavraSecreta palavraJogo;
    private Scanner input;

    public EscolherLetra(Player player, PalavraSecreta palavraJogo){
        this.player = player;
        this.palavraJogo = palavraJogo;
        this.input = new Scanner(System.in);
    }

    public void pedirESetarLetra(){
        System.out.println("------------------------------------");
        System.out.println("Palavra: " + palavraJogo.getPalavraOculta());
        System.out.println("Letras já escolhidas: " + player.getLetrasEscolhidas().getLetrasJaTentadas());
        System.out.println("Tentativas restantes: " + (player.getMaxTentativas() - player.getTentativasErradas()));

        System.out.println(player.getEstagioAtualForca().getDesenho());

        System.out.println("Digite uma Letra: ");
        String entrada = input.nextLine();

        if(entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))){
            System.out.println("Entrada inválida! Por favor, digite apenas UMA letra.");
            return;
        }

        char letra = Character.toLowerCase(entrada.charAt(0));

        if(player.getLetrasEscolhidas().jaEscolhida(letra)){
            System.out.println("A letra '" + letra + "' já foi escolhida! Tente outra.");
        } else {
            player.getLetrasEscolhidas().adicionar(letra);

            if (palavraJogo.getPalavraSecreta().contains(String.valueOf(letra))) {
                System.out.println("A letra '" + letra + "' está na palavra!");
                player.pontuacao+=2;
            } else {
                System.out.println("Que pena! A letra '" + letra + "' não está na palavra.");
                player.registrarTentativaErrada();
                player.pontuacao-=1;

                if (player.perdeu()) {
                    System.out.println(player.getEstagioAtualForca().getDesenho());
                    System.out.println("Fim de jogo! Você perdeu.");
                    player.pontuacao-=2;
                }
            }
        }
    }
}