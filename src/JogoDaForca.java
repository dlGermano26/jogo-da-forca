import java.util.Random; // Ferramenta para escolher coisas aleatoriamente (como um tema)
import java.util.Scanner; // Ferramenta para ler o que o jogador digita no teclado

public class JogoDaForca {
    public static void main(String[] args) {
        // Preparando as ferramentas que vamos usar no jogo:
        Scanner scanner = new Scanner(System.in); // Para o computador "ouvir" o que o jogador digita
        Random random = new Random(); // Para escolher palavras ou temas de surpresa
        Ranking ranking = new Ranking(); // Nosso placar de pontos que guarda quem tem mais pontos

        // Começo do jogo:
        System.out.println("Bem-vindo ao Jogo da Forca!"); // Mensagem de boas-vindas
        System.out.println("------------------------------------"); // Uma linha para deixar bonito

        System.out.print("Digite seu nome de jogador: "); // Pede o nome do jogador
        String playerName = scanner.nextLine(); // Guarda o nome que o jogador digitou

        System.out.println("Escolha um tema para a palavra secreta:"); // Pede para o jogador escolher um tema

        // Mostra as opções de temas:
        TemaPalavras[] temas = TemaPalavras.values(); // Pega todos os temas que existem (animais, frutas, etc.)
        for (int i = 0; i < temas.length; i++) {
            System.out.println((i + 1) + ". " + temas[i].name()); // Mostra cada tema com um número na frente
        }
        System.out.println("Digite o número do tema (ou '0' para aleatório):"); // Instrução para escolher

        TemaPalavras temaEscolhido = null; // Variável para guardar o tema que o jogador escolher
        int escolhaTema = -1; // Variável para guardar o número que o jogador digitar

        // Loop para garantir que o jogador escolha um tema válido:
        while (temaEscolhido == null) { // Continua perguntando até que um tema válido seja escolhido
            try {
                escolhaTema = Integer.parseInt(scanner.nextLine()); // Tenta transformar o que o jogador digitou em um número
                if (escolhaTema == 0) { // Se digitou '0', escolhe um tema qualquer
                    temaEscolhido = temas[random.nextInt(temas.length)]; // Escolhe um tema na sorte
                    System.out.println("Tema aleatório escolhido: " + temaEscolhido.name() + "!"); // Avisa qual tema foi sorteado
                } else if (escolhaTema > 0 && escolhaTema <= temas.length) { // Se digitou um número que está na lista de temas
                    temaEscolhido = temas[escolhaTema - 1]; // Pega o tema correspondente ao número
                    System.out.println("Tema escolhido: " + temaEscolhido.name() + "!"); // Avisa qual tema foi escolhido
                } else {
                    System.out.println("Opção inválida. Por favor, digite um número entre 0 e " + temas.length + "."); // Mensagem de erro para número fora da lista
                }
            } catch (NumberFormatException e) { // Se o jogador não digitar um número
                System.out.println("Entrada inválida. Por favor, digite um número."); // Mensagem de erro para entrada não numérica
            }
        }

        // Configurando o jogo em si:
        Player jogador = new Player(); // Cria o jogador para esta partida (com as tentativas e letras erradas)
        PalavraSecreta palavraJogo = new PalavraSecreta(temaEscolhido, jogador.getLetrasEscolhidas()); // Escolhe a palavra secreta com base no tema
        EscolherLetra escolherLetra = new EscolherLetra(jogador, palavraJogo); // Prepara a parte do jogo que pede as letras

        System.out.println("\nIniciando o jogo..."); // Mensagem de início de jogo
        System.out.println("A palavra tem " + palavraJogo.getPalavraSecreta().length() + " letras."); // Diz quantas letras a palavra secreta tem

        // A parte principal do jogo: continua enquanto o jogador não perdeu e a palavra não foi adivinhada
        while (!jogador.perdeu() && !palavraJogo.foiAdivinhada()) {
            escolherLetra.pedirESetarLetra(); // Pede uma letra ao jogador e verifica se ele acertou ou errou
        }

        // Final do jogo: Calcula a pontuação e mostra o resultado
        int score = 0; // Começa a pontuação desta rodada em zero
        if (jogador.perdeu()) { // Se o jogador esgotou as tentativas (perdeu a forca)
            System.out.println("\nVOCÊ PERDEU! A palavra secreta era: " + palavraJogo.getPalavraSecreta().toUpperCase()); // Avisa que perdeu e qual era a palavra
            score = -5; // Penaliza o jogador com 5 pontos negativos
        } else if (palavraJogo.foiAdivinhada()) { // Se o jogador acertou todas as letras (adivinhou a palavra)
            System.out.println("\nPARABÉNS! Você adivinhou a palavra!"); // Mensagem de parabéns
            System.out.println("A palavra era: " + palavraJogo.getPalavraSecreta().toUpperCase()); // Mostra a palavra adivinhada
            score = 10 + (jogador.getMaxTentativas() - jogador.getTentativasErradas()); // Dá 10 pontos mais um bônus por cada tentativa que sobrou
        }

        // Atualiza e mostra o placar geral:
        ranking.updateScore(playerName, score); // Adiciona ou atualiza a pontuação do jogador no placar geral
        ranking.saveRanking(); // Salva o placar em um arquivo para não perder as pontuações
        ranking.displayRanking(); // Mostra o placar atualizado para todos verem

        scanner.close(); // Fecha a ferramenta de leitura do teclado
        System.out.println("Obrigado por jogar!"); // Mensagem de despedida
    }
}