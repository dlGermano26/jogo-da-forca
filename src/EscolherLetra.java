import java.util.Scanner; // Importa a classe Scanner para permitir a entrada de dados do usuário

public class EscolherLetra {
    private Player player; // Declara uma variável de instância 'player' do tipo Player. Representa o jogador atual.
    private PalavraSecreta palavraJogo; // Declara uma variável de instância 'palavraJogo' do tipo PalavraSecreta. Representa a palavra a ser adivinhada.
    private Scanner input; // Declara uma variável de instância 'input' do tipo Scanner para ler a entrada do teclado.

    /**
     * Construtor da classe EscolherLetra.
     * @param player O objeto Player que representa o jogador atual.
     * @param palavraJogo O objeto PalavraSecreta que contém a palavra a ser adivinhada.
     */
    public EscolherLetra(Player player, PalavraSecreta palavraJogo){
        this.player = player; // Atribui o objeto Player passado como argumento à variável de instância 'player'.
        this.palavraJogo = palavraJogo; // Atribui o objeto PalavraSecreta passado como argumento à variável de instância 'palavraJogo'.
        this.input = new Scanner(System.in); // Inicializa o Scanner para ler a entrada do sistema (teclado).
    }

    /**
     * Este método é responsável por pedir uma letra ao jogador, validar a entrada,
     * verificar se a letra já foi escolhida, atualizar o estado do jogo (pontuação,
     * tentativas erradas) e exibir mensagens para o usuário.
     */
    public void pedirESetarLetra(){
        System.out.println("------------------------------------"); // Imprime uma linha separadora para melhor visualização.
        System.out.println("Palavra: " + palavraJogo.getPalavraOculta()); // Exibe a palavra com as letras adivinhadas e as não adivinhadas como '_'.
        System.out.println("Letras já escolhidas: " + player.getLetrasEscolhidas().getLetrasJaTentadas()); // Mostra as letras que o jogador já tentou.
        System.out.println("Tentativas restantes: " + (player.getMaxTentativas() - player.getTentativasErradas())); // Calcula e exibe o número de tentativas restantes.

        System.out.println(player.getEstagioAtualForca().getDesenho()); // Exibe o desenho da forca correspondente ao número de tentativas erradas.

        System.out.println("Digite uma Letra: "); // Pede ao usuário para digitar uma letra.
        String entrada = input.nextLine(); // Lê a linha digitada pelo usuário.

        // Validação da entrada: verifica se a entrada tem exatamente um caractere e se é uma letra.
        if(entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))){
            System.out.println("Entrada inválida! Por favor, digite apenas UMA letra."); // Mensagem de erro para entrada inválida.
            return; // Sai do método se a entrada for inválida, sem processar a letra.
        }

        char letra = Character.toLowerCase(entrada.charAt(0)); // Converte a letra digitada para minúscula para padronização.

        // Verifica se a letra já foi escolhida anteriormente.
        if(player.getLetrasEscolhidas().jaEscolhida(letra)){
            System.out.println("A letra '" + letra + "' já foi escolhida! Tente outra."); // Mensagem para letra repetida.
        } else {
            player.getLetrasEscolhidas().adicionar(letra); // Adiciona a letra à lista de letras já escolhidas pelo jogador.

            // Verifica se a letra escolhida está na palavra secreta.
            if (palavraJogo.getPalavraSecreta().contains(String.valueOf(letra))) {
                System.out.println("A letra '" + letra + "' está na palavra!"); // Mensagem de acerto.
                // Note: 'player.pontuacao' parece ser um campo diretamente acessível.
                // É mais comum ter um método setPontuacao ou adicionarPontos em Player.
                player.pontuacao += 2; // Adiciona 2 pontos à pontuação do jogador por acertar a letra.
            } else {
                System.out.println("Que pena! A letra '" + letra + "' não está na palavra."); // Mensagem de erro.
                player.registrarTentativaErrada(); // Registra uma tentativa errada para o jogador.
                player.pontuacao -= 1; // Subtrai 1 ponto da pontuação do jogador por errar a letra.

                // Verifica se o jogador perdeu o jogo após esta tentativa errada.
                if (player.perdeu()) {
                    System.out.println(player.getEstagioAtualForca().getDesenho()); // Exibe o desenho final da forca (completa).
                    System.out.println("Fim de jogo! Você perdeu."); // Mensagem de fim de jogo por derrota.
                    player.pontuacao -= 2; // Subtrai mais 2 pontos por perder o jogo (penalidade adicional).
                }
            }
        }
    }
}