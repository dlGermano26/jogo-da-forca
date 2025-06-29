import java.util.Scanner; // Ferramenta para ler o que o jogador digita (embora não seja mais usada diretamente aqui para o nome)

public class Player {
    // Esta classe é como a "ficha" do jogador no jogo.
    // Ela guarda todas as informações importantes sobre o jogador durante a partida.

    private LetrasEscolhidas letrasEscolhidas; // O "caderninho" do jogador, que anota as letras que ele já tentou.
    private int tentativasErradas; // Quantas vezes o jogador errou uma letra até agora.
    // O número máximo de erros que o jogador pode cometer antes de perder.
    // É calculado com base nos estágios do desenho da forca.
    private final int maximoTentativas = Forca.values().length - 1;
    private String nome; // O nome do jogador (adicionado para o ranking).
    public int pontuacao; // A pontuação do jogador nesta partida (pode ser pública para fácil acesso).
    private Scanner input; // Uma ferramenta para "escutar" o teclado (embora o nome do jogador seja lido em JogoDaForca).

    /**
     * Isso é o que acontece quando um novo jogador entra no jogo.
     * Prepara a "ficha" do jogador para o início da partida.
     */
    public Player() {
        this.letrasEscolhidas = new LetrasEscolhidas(); // Cria um "caderninho" novo e vazio para as letras tentadas.
        this.tentativasErradas = 0; // Começa sem nenhum erro.
        this.input=new Scanner(System.in); // Prepara a ferramenta de leitura (mesmo que o nome seja lido fora daqui).
        this.pontuacao=0; // Começa com zero pontos.
    }

    /**
     * Pede para ver o "caderninho" de letras que o jogador já tentou.
     * @return O "caderninho" com as letras já escolhidas.
     */
    public LetrasEscolhidas getLetrasEscolhidas() {
        return letrasEscolhidas;
    }

    /**
     * Descobre qual estágio do desenho da forca deve ser mostrado.
     * Isso depende de quantas tentativas erradas o jogador já tem.
     * @return O desenho da forca correspondente ao número de erros.
     */
    public Forca getEstagioAtualForca() {
        // Se o número de erros for maior ou igual ao total de estágios da forca,
        // mostra sempre o último estágio (a forca completa).
        if (tentativasErradas >= Forca.values().length) {
            return Forca.values()[Forca.values().length - 1];
        }
        // Caso contrário, mostra o desenho da forca que corresponde ao número de erros.
        return Forca.values()[tentativasErradas];
    }

    /**
     * Anota que o jogador cometeu um erro.
     * Se o jogador ainda não atingiu o limite máximo de erros, ele adiciona mais um.
     */
    public void registrarTentativaErrada() {
        if (tentativasErradas < maximoTentativas) { // Verifica se ainda pode errar mais
            tentativasErradas++; // Aumenta a contagem de erros.
        }
    }

    /**
     * Verifica se o jogador já perdeu o jogo.
     * Isso acontece quando o número de erros atinge o máximo permitido.
     * @return Diz "sim" (verdadeiro) se o jogador perdeu, ou "não" (falso) se ainda pode jogar.
     */
    public boolean perdeu() {
        return tentativasErradas >= maximoTentativas; // Compara os erros atuais com o máximo permitido.
    }

    /**
     * Descobre quantas vezes o jogador já errou.
     * @return O número de tentativas erradas do jogador.
     */
    public int getTentativasErradas() {
        return tentativasErradas;
    }

    /**
     * Descobre qual é o número máximo de erros que o jogador pode cometer.
     * @return O número máximo de tentativas permitidas.
     */
    public int getMaxTentativas() {
        return maximoTentativas;
    }

    /**
     * Pega a pontuação atual do jogador para esta partida.
     * @return A pontuação do jogador.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Pega o nome do jogador.
     * @return O nome do jogador.
     */
    public String getNome(){
        return nome;
    }

    /**
     * Define o nome do jogador.
     * @param nome O nome a ser atribuído ao jogador.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}