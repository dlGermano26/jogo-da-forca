import java.util.HashSet; // Uma "caixa" especial que guarda coisas sem repetir e em qualquer ordem
import java.util.Set;     // O tipo de "caixa" que o HashSet é, para guardar coisas únicas

public class LetrasEscolhidas {
    // Esta classe é como um "caderninho" onde o jogo anota todas as letras que o jogador já tentou.
    // Assim, o jogo sabe se uma letra já foi dita antes.

    private Set<Character> letras; // Esta é a nossa "caixa" (o caderninho) onde vamos guardar as letras que o jogador já falou.
    // 'Set' significa que cada letra só aparece uma vez, mesmo se o jogador digitar ela de novo.

    /**
     * Isso é o que acontece quando o "caderninho" é criado no início do jogo.
     * Ele começa vazio, sem nenhuma letra anotada.
     */
    public LetrasEscolhidas(){
        this.letras = new HashSet<>(); // Cria uma "caixa" nova e vazia para guardar as letras.
    }

    /**
     * Este método serve para perguntar ao "caderninho": "Você já tem esta letra anotada aqui?"
     * @param letra A letra que queremos verificar.
     * @return Diz "sim" (verdadeiro) se a letra já estiver anotada, ou "não" (falso) se ainda não estiver.
     */
    public boolean jaEscolhida(char letra){
        // Antes de verificar, ele transforma a letra para minúscula, só para ter certeza
        // que 'A' e 'a' são a mesma letra no caderninho.
        return letras.contains(Character.toLowerCase(letra)); // Pergunta à "caixa" se ela já tem essa letra.
    }

    /**
     * Este método serve para anotar uma nova letra no "caderninho".
     * @param letra A letra que o jogador acabou de tentar.
     * @return Diz "sim" (verdadeiro) se a letra foi adicionada (era nova), ou "não" (falso) se ela já estava lá.
     */
    public boolean adicionar(char letra) {
        // Também transforma a letra para minúscula antes de guardar, por organização.
        return letras.add(Character.toLowerCase(letra)); // Adiciona a letra na nossa "caixa" de letras já tentadas.
    }

    /**
     * Este método serve para pegar todas as letras que já foram tentadas pelo jogador,
     * para que o jogo possa mostrá-las na tela.
     * @return Uma cópia do "caderninho" com todas as letras que já foram anotadas.
     */
    public Set<Character> getLetrasJaTentadas() {
        // Devolve uma nova "caixa" com as mesmas letras, para que o jogo possa mostrar,
        // mas sem quebrar o nosso "caderninho" original.
        return new HashSet<>(letras);
    }
}