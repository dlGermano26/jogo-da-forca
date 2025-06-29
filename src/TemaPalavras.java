import java.util.Random; // Ferramenta para escolher coisas aleatoriamente (como uma palavra dentro de um tema)

public enum TemaPalavras {
    // Pense nisso como uma "lista de temas" pré-definidos para o jogo da forca.
    // Cada nome grande aqui (ANIMAIS, FRUTAS, etc.) é um tema que o jogador pode escolher.
    // E cada tema já vem com sua própria lista de palavras.

    ANIMAIS(new String[]{"gato", "cachorro", "elefante", "leao", "tigre", "lobo", "urso", "cobra", "aguia", "pinguim"}),
    // O tema ANIMAIS tem esta lista de palavras.
    FRUTAS(new String[]{"banana", "uva", "laranja", "morango", "abacaxi", "melancia", "kiwi", "coco", "pessego"}),
    // O tema FRUTAS tem esta lista.
    PAISES(new String[]{"brasil", "canada", "japao", "franca", "alemanha", "mexico", "argentina", "australia", "egito", "russia"}),
    // E assim por diante para PAISES,
    CORES(new String[]{"vermelho", "azul", "verde", "amarelo", "roxo", "rosa", "marrom", "preto", "branco", "cinza"}),
    // CORES,
    ESPORTES(new String[]{"futebol", "basquete", "volei", "tenis", "natacao", "corrida", "ciclismo", "boxe", "ginastica", "esgrima"});
    // E ESPORTES.

    private final String[] palavras; // Esta variável guarda a lista de palavras para CADA tema.
    // Por exemplo, para ANIMAIS, guarda {"gato", "cachorro", ...}.
    private static final Random random = new Random(); // Esta é a ferramenta de "sorteio" que o jogo usa.
    // É como ter um dado para escolher uma palavra.

    /**
     * Isso é como cada tema é "criado" e recebe sua lista de palavras.
     * Quando escrevemos ANIMAIS(...), estamos usando este construtor para dar a lista de palavras aos ANIMAIS.
     * @param palavras A lista de palavras que pertence a este tema específico.
     */
    TemaPalavras(String[] palavras) {
        this.palavras = palavras; // Guarda a lista de palavras que foi dada para este tema.
    }

    /**
     * Este é um "botão" que, quando apertado para um tema (ex: TemaPalavras.ANIMAIS.getAleatoria()),
     * escolhe uma palavra SURPRESA da lista daquele tema.
     * @return Uma palavra escolhida ao acaso da lista de palavras do tema.
     */
    public String getAleatoria() {
        // Escolhe um número aleatório que corresponde a uma posição na lista de palavras
        // e devolve a palavra que está nessa posição.
        return palavras[random.nextInt(palavras.length)];
    }
}