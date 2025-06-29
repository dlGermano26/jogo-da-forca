import java.io.*; // Ferramentas para ler e escrever em arquivos (como um caderno de anotações)
import java.util.Comparator; // Ferramenta para comparar e organizar coisas (para ordenar o placar)
import java.util.HashMap; // Um tipo de "tabela" para guardar informações (nome do jogador e pontos)
import java.util.LinkedHashMap; // Outro tipo de "tabela", que mantém a ordem quando as coisas são organizadas
import java.util.Map; // O conceito geral de uma "tabela" que associa uma coisa a outra
import java.util.stream.Collectors; // Ferramentas para juntar e organizar informações de forma mais avançada

public class Ranking {
    // Esta classe é como o "Placar Oficial" do jogo da forca.
    // Ela guarda os pontos de todos os jogadores, mesmo depois que o jogo é fechado,
    // e sabe como mostrar quem tem mais pontos.

    private static final String RANKING_FILE = "ranking.txt"; // O nome do arquivo onde o placar será salvo e lido.
    // Pense nisso como o "nome do caderno" onde anotamos os pontos.
    private Map<String, Integer> scores; // Esta é a nossa "tabela" principal onde guardamos o nome de cada jogador
    // (como um texto) e quantos pontos ele tem (como um número inteiro).

    /**
     * Isso é o que acontece quando o "Placar Oficial" é iniciado.
     * É como abrir o caderno de pontos.
     */
    public Ranking() {
        scores = new HashMap<>(); // Cria a nossa "tabela" de pontos, que começa vazia.
        loadRanking(); // Tenta ler os pontos que já estavam anotados no "caderno" (arquivo).
    }

    /**
     * Este método é como abrir o "caderno" e ler todos os pontos que foram anotados antes.
     * Ele tenta encontrar o arquivo 'ranking.txt' e, se achar, lê linha por linha.
     * Cada linha deve ter o nome do jogador e a pontuação, separados por dois pontos (ex: "JOAO:15").
     */
    private void loadRanking() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RANKING_FILE))) {
            // Abrimos o arquivo para leitura, como se estivéssemos pegando uma caneta para ler as anotações.
            String line;
            while ((line = reader.readLine()) != null) { // Lê uma linha de cada vez, até o final do arquivo.
                String[] parts = line.split(":"); // Separa a linha em duas partes: uma para o nome e outra para a pontuação.
                // Ele "corta" a linha onde encontra os dois pontos (:).
                if (parts.length == 2) { // Garante que a linha foi cortada corretamente em duas partes.
                    try {
                        String name = parts[0].trim(); // Pega a primeira parte (o nome) e tira espaços extras.
                        int score = Integer.parseInt(parts[1].trim()); // Pega a segunda parte (a pontuação) e transforma em um número.
                        scores.put(name, score); // Coloca o nome e a pontuação na nossa "tabela" de pontos.
                    } catch (NumberFormatException e) {
                        // Se a pontuação não for um número válido, mostra um aviso de erro.
                        System.err.println("Erro ao analisar pontuação no ranking: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Se o arquivo 'ranking.txt' ainda não existe (primeira vez jogando),
            // ele avisa que vai criar um novo quando for a hora de salvar.
            System.out.println("Arquivo de ranking não encontrado. Um novo será criado.");
        } catch (IOException e) {
            // Se acontecer algum problema ao ler o arquivo (ex: problema no computador),
            // ele mostra um aviso de erro e recomeça a "tabela" de pontos do zero para evitar problemas.
            System.err.println("Erro ao carregar o ranking: " + e.getMessage());
            scores = new HashMap<>(); // Zera o placar em caso de erro.
        }
    }

    /**
     * Este método é como anotar as pontuações atualizadas no "caderno" e salvá-lo.
     * Ele escreve cada nome e pontuação no arquivo 'ranking.txt', um por linha.
     */
    public void saveRanking() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RANKING_FILE))) {
            // Abrimos o arquivo para escrita, como se fôssemos escrever as anotações.
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                // Para cada jogador e sua pontuação na nossa "tabela":
                writer.write(entry.getKey() + ":" + entry.getValue()); // Escreve o nome e a pontuação, separados por dois pontos.
                writer.newLine(); // Pula para a próxima linha para o próximo jogador.
            }
        } catch (IOException e) {
            // Se acontecer algum problema ao salvar o arquivo, ele mostra um aviso de erro.
            System.err.println("Erro ao salvar o ranking: " + e.getMessage());
        }
    }

    /**
     * Este método serve para adicionar pontos a um jogador ou atualizar a pontuação dele.
     * Se o jogador for novo, ele é adicionado. Se já existir, os pontos são somados aos antigos.
     * @param playerName O nome do jogador que vai ter a pontuação atualizada.
     * @param score A quantidade de pontos que será adicionada (pode ser positivo ou negativo).
     */
    public void updateScore(String playerName, int score) {
        String nameUpperCase = playerName.toUpperCase(); // Converte o nome do jogador para MAIÚSCULAS para que "joao" e "Joao" sejam tratados como a mesma pessoa.
        // Adiciona os novos pontos à pontuação que o jogador já tinha (ou começa com 0 se for novo).
        scores.put(nameUpperCase, scores.getOrDefault(nameUpperCase, 0) + score);
    }

    /**
     * Este método mostra o placar atual de todos os jogadores na tela.
     * Ele organiza os jogadores do que tem mais pontos para o que tem menos.
     */
    public void displayRanking() {
        System.out.println("\n--- RANKING ---"); // Título para o placar.
        if (scores.isEmpty()) { // Se não houver nenhum jogador no placar ainda.
            System.out.println("Nenhum jogador no ranking ainda."); // Avisa que o placar está vazio.
            return; // Termina o método aqui.
        }

        // Esta parte é para organizar o placar:
        // Pega todos os jogadores e seus pontos, e os organiza do maior para o menor em pontuação.
        Map<String, Integer> sortedScores = scores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // Ordena pela pontuação, do maior para o menor.
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // Mostra cada jogador e sua pontuação na tela.
        sortedScores.forEach((name, score) -> System.out.println(name + ": " + score + " pontos"));
        System.out.println("---------------"); // Linha para finalizar o placar.
    }
}