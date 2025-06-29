enum Forca {
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
    private String desenho;

    Forca(String desenho){
        this.desenho = desenho;
    }

    public String getDesenho(){
        return desenho;
    }
}