enum Forca {
    // Pense neste 'enum' como uma lista de "estados" ou "cenários" pré-definidos para o jogo da forca.
    // Cada nome grande (ESTAGIO0, ESTAGIO1, etc.) é um cenário diferente do desenho da forca,
    // que aparece conforme o jogador comete erros.

    ESTAGIO0("""
              _______
             |/      |
             |      
             |      
             |       
             |      
             |      
             |___
            """),
    // ESTAGIO0: Este é o desenho da forca vazia, quando o jogo começa e o jogador ainda não errou nenhuma letra.

    ESTAGIO1("""
              _______
             |/      |
             |       O
             |      
             |       
             |      
             |      
             |___
            """),
    // ESTAGIO1: O jogador errou a primeira vez. Aparece a cabeça do boneco.

    ESTAGIO2("""
              _______
             |/      |
             |       O
             |       |
             |       
             |      
             |      
             |___
            """),
    // ESTAGIO2: Mais um erro. O corpo do boneco é desenhado.

    ESTAGIO3("""
              _______
             |/      |
             |       O
             |     --|
             |       
             |      
             |      
             |___
            """),
    // ESTAGIO3: Outro erro. Um braço (o esquerdo, neste caso) aparece.

    ESTAGIO4("""
              _______
             |/      |
             |       O
             |     --|--
             |       
             |      
             |      
             |___
            """),
    // ESTAGIO4: Mais um erro. O segundo braço (o direito) é adicionado.

    ESTAGIO5("""
              _______
             |/      |
             |       O
             |     --|--
             |     _| 
             |      
             |      
             |___
            """),
    // ESTAGIO5: O jogador erra novamente. Uma perna (a esquerda) do boneco é desenhada.

    ESTAGIO6("""
              _______
             |/      |
             |       O   
             |     --|--
             |     _| |_
             |      
             |      
             |___
            """);
    // ESTAGIO6: O último erro! A segunda perna (a direita) é adicionada.
    // Neste ponto, o boneco está completo, e o jogador perdeu o jogo.

    private String desenho; // Esta variável guarda o "desenho" (o texto com a imagem) de cada estágio da forca.
    // É como se fosse uma etiqueta para cada cenário.

    /**
     * Isso é como cada cenário da forca (ESTAGIO0, ESTAGIO1, etc.) é "montado".
     * Quando você cria um ESTAGIO, você precisa dizer a ele qual é o desenho dele.
     * @param desenho O texto que forma a imagem da forca para aquele estágio.
     */
    Forca(String desenho){
        this.desenho = desenho; // Guarda o desenho que foi dado para este estágio específico.
    }

    /**
     * Este é um "botão" para você pegar o desenho de qualquer estágio da forca.
     * Por exemplo, se você quiser ver o desenho do ESTAGIO3, você chamaria este método.
     * @return O texto que mostra a imagem da forca para o estágio atual.
     */
    public String getDesenho(){
        return desenho; // Devolve o desenho que está guardado para este estágio.
    }
}