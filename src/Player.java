import java.util.Scanner;

public class Player {
    private LetrasEscolhidas letrasEscolhidas;
    private int tentativasErradas;
    private final int maximoTentativas = Forca.values().length - 1;
    private String nome;
    public int pontuacao;
    private Scanner input;

    public Player() {
        this.letrasEscolhidas = new LetrasEscolhidas();
        this.tentativasErradas = 0;
        this.input=new Scanner(System.in);
        this.pontuacao=0;
    }

    public LetrasEscolhidas getLetrasEscolhidas() {
        return letrasEscolhidas;
    }

    public Forca getEstagioAtualForca() {
        if (tentativasErradas >= Forca.values().length) {
            return Forca.values()[Forca.values().length - 1];
        }
        return Forca.values()[tentativasErradas];
    }

    public void registrarTentativaErrada() {
        if (tentativasErradas < maximoTentativas) {
            tentativasErradas++;
        }
    }

    public boolean perdeu() {
        return tentativasErradas >= maximoTentativas;
    }

    public int getTentativasErradas() {
        return tentativasErradas;
    }

    public int getMaxTentativas() {
        return maximoTentativas;
    }

    public int getPontuacao() {
        return pontuacao;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
