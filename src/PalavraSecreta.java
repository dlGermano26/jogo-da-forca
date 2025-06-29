public class PalavraSecreta {
    // Esta classe é como a "caixa secreta" onde guardamos a palavra que o jogador precisa adivinhar.
    // Ela também sabe mostrar a palavra com os espaços (sublinhados) para as letras que ainda não foram descobertas.

    private String palavraEscolhida; // Esta é a palavra "escondida" que o jogo escolheu.
    private LetrasEscolhidas letrasEscolhidas; // É o "caderninho" que anota as letras que o jogador já tentou.
    // A classe PalavraSecreta usa esse caderninho para saber quais letras já foram acertadas.

    /**
     * Isso é o que acontece quando uma nova "palavra secreta" é escolhida para o jogo.
     * @param tema O tema de onde a palavra será tirada (ex: Animais, Frutas).
     * @param letrasEscolhidas O "caderninho" que será usado para esta partida, para acompanhar as letras.
     */
    public PalavraSecreta(TemaPalavras tema, LetrasEscolhidas letrasEscolhidas){
        this.palavraEscolhida = tema.getAleatoria(); // Pega uma palavra aleatória do tema que foi escolhido.
        this.letrasEscolhidas = letrasEscolhidas; // Guarda o "caderninho" que será usado.
    }

    /**
     * Este método serve para mostrar a palavra secreta completa.
     * Normalmente, ele é usado só no final do jogo, para revelar a resposta.
     * @return A palavra completa que o jogador tinha que adivinhar.
     */
    public String getPalavraSecreta(){
        return palavraEscolhida; // Devolve a palavra secreta real.
    }

    /**
     * Este método serve para mostrar a palavra como ela aparece durante o jogo:
     * com as letras já acertadas visíveis e as letras que faltam como traços (sublinhados).
     * @return A palavra no formato "oculto" (ex: P _ S C _).
     */
    public String getPalavraOculta(){
        StringBuilder palavraOculta = new StringBuilder(); // Uma ferramenta para construir a palavra oculta pedacinho por pedacinho.
        // Percorre cada letra da palavra secreta:
        for(char letra : palavraEscolhida.toCharArray()){
            // Pergunta ao "caderninho" (letrasEscolhidas) se esta letra já foi adivinhada:
            if(letrasEscolhidas.jaEscolhida(letra)){
                // Se a letra já foi adivinhada, mostra a letra em maiúscula e um espaço.
                palavraOculta.append(Character.toUpperCase(letra)).append(" ");
            } else {
                // Se a letra ainda não foi adivinhada, mostra um traço e um espaço.
                palavraOculta.append("_ ");
            }
        }
        // Transforma tudo o que foi montado em um texto final e remove espaços extras no começo ou fim.
        return palavraOculta.toString().trim();
    }

    /**
     * Este método verifica se o jogador já adivinhou a palavra inteira.
     * Ele faz isso checando se *todas* as letras da palavra secreta já foram tentadas e acertadas.
     * @return Diz "sim" (verdadeiro) se todas as letras foram adivinhadas, ou "não" (falso) se ainda falta alguma.
     */
    public boolean foiAdivinhada() {
        // Percorre cada letra da palavra secreta novamente:
        for (char letra : palavraEscolhida.toCharArray()) {
            // Se encontrar *alguma* letra que ainda não foi adivinhada (não está no "caderninho")...
            if (!letrasEscolhidas.jaEscolhida(letra)) {
                return false; // ... então a palavra ainda NÃO foi adivinhada.
            }
        }
        // Se ele chegou até aqui, significa que percorreu todas as letras e todas já foram adivinhadas.
        return true; // A palavra FOI adivinhada!
    }
}