import java.util.Random;

public enum TemaPalavras {
    ANIMAIS(new String[]{"gato", "cachorro", "elefante", "leao", "tigre", "lobo", "urso", "cobra", "aguia", "pinguim"}),
    FRUTAS(new String[]{"banana", "uva", "laranja", "morango", "abacaxi", "melancia", "kiwi", "coco", "pessego"}),
    PAISES(new String[]{"brasil", "canada", "japao", "franca", "alemanha", "mexico", "argentina", "australia", "egito", "russia"}),
    CORES(new String[]{"vermelho", "azul", "verde", "amarelo", "roxo", "rosa", "marrom", "preto", "branco", "cinza"}),
    ESPORTES(new String[]{"futebol", "basquete", "volei", "tenis", "natacao", "corrida", "ciclismo", "boxe", "ginastica", "esgrima"});

    private final String[] palavras;
    private static final Random random = new Random();

    TemaPalavras(String[] palavras) {
        this.palavras = palavras;
    }

    public String getAleatoria() {
        return palavras[random.nextInt(palavras.length)];
    }
}