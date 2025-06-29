import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Ranking ranking = new Ranking(); // Inicializa o sistema de Ranking

        System.out.println("Bem-vindo ao Jogo da Forca!");
        System.out.println("------------------------------------");

        System.out.print("Digite seu nome de jogador: ");
        String playerName = scanner.nextLine(); // Solicita o nome do jogador

        System.out.println("Escolha um tema para a palavra secreta:");

        TemaPalavras[] temas = TemaPalavras.values();
        for (int i = 0; i < temas.length; i++) {
            System.out.println((i + 1) + ". " + temas[i].name());
        }
        System.out.println("Digite o número do tema (ou '0' para aleatório):");

        TemaPalavras temaEscolhido = null;
        int escolhaTema = -1;

        while (temaEscolhido == null) {
            try {
                escolhaTema = Integer.parseInt(scanner.nextLine());
                if (escolhaTema == 0) {
                    temaEscolhido = temas[random.nextInt(temas.length)];
                    System.out.println("Tema aleatório escolhido: " + temaEscolhido.name() + "!");
                } else if (escolhaTema > 0 && escolhaTema <= temas.length) {
                    temaEscolhido = temas[escolhaTema - 1];
                    System.out.println("Tema escolhido: " + temaEscolhido.name() + "!");
                } else {
                    System.out.println("Opção inválida. Por favor, digite um número entre 0 e " + temas.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }

        Player jogador = new Player();
        PalavraSecreta palavraJogo = new PalavraSecreta(temaEscolhido, jogador.getLetrasEscolhidas());
        EscolherLetra escolherLetra = new EscolherLetra(jogador, palavraJogo);

        System.out.println("\nIniciando o jogo...");
        System.out.println("A palavra tem " + palavraJogo.getPalavraSecreta().length() + " letras.");

        while (!jogador.perdeu() && !palavraJogo.foiAdivinhada()) {
            escolherLetra.pedirESetarLetra();
        }

        int score = 0;
        if (jogador.perdeu()) {
            System.out.println("\nVOCÊ PERDEU! A palavra secreta era: " + palavraJogo.getPalavraSecreta().toUpperCase());
            score = -5; // Exemplo: perde 5 pontos por perder
        } else if (palavraJogo.foiAdivinhada()) {
            System.out.println("\nPARABÉNS! Você adivinhou a palavra!");
            System.out.println("A palavra era: " + palavraJogo.getPalavraSecreta().toUpperCase());
            score = 10 + (jogador.getMaxTentativas() - jogador.getTentativasErradas()); // Exemplo: 10 pontos + bônus para tentativas restantes
        }

        ranking.updateScore(playerName, score); // Atualiza a pontuação do jogador no ranking
        ranking.saveRanking(); // Salva o ranking após cada jogo
        ranking.displayRanking(); // Exibe o ranking após o jogo

        scanner.close();
        System.out.println("Obrigado por jogar!");
    }
}