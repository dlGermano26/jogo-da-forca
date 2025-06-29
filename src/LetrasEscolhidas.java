import java.util.HashSet;
import java.util.Set;

public class LetrasEscolhidas {
    private Set<Character> letras;

    public LetrasEscolhidas(){
        this.letras = new HashSet<>();
    }

    public boolean jaEscolhida(char letra){
        return letras.contains(Character.toLowerCase(letra));
    }

    public boolean adicionar(char letra) {
        return letras.add(Character.toLowerCase(letra));
    }

    public Set<Character> getLetrasJaTentadas() {
        return new HashSet<>(letras);
    }
}