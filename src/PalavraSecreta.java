public class PalavraSecreta {
    private String palavraEscolhida;
    private LetrasEscolhidas letrasEscolhidas;

    public PalavraSecreta(TemaPalavras tema, LetrasEscolhidas letrasEscolhidas){
        this.palavraEscolhida = tema.getAleatoria();
        this.letrasEscolhidas = letrasEscolhidas;
    }

    public String getPalavraSecreta(){
        return palavraEscolhida;
    }

    public String getPalavraOculta(){
        StringBuilder palavraOculta = new StringBuilder();
        for(char letra : palavraEscolhida.toCharArray()){
            if(letrasEscolhidas.jaEscolhida(letra)){
                palavraOculta.append(Character.toUpperCase(letra)).append(" ");
            } else {
                palavraOculta.append("_ ");
            }
        }
        return palavraOculta.toString().trim();
    }

    public boolean foiAdivinhada() {
        for (char letra : palavraEscolhida.toCharArray()) {
            if (!letrasEscolhidas.jaEscolhida(letra)) {
                return false;
            }
        }
        return true;
    }
}