import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Ranking {
    private static final String RANKING_FILE = "ranking.txt"; // Alterado para .txt
    private Map<String, Integer> scores;

    public Ranking() {
        scores = new HashMap<>();
        loadRanking();
    }

    private void loadRanking() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RANKING_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":"); // Supondo formato "NOME:PONTUACAO"
                if (parts.length == 2) {
                    try {
                        String name = parts[0].trim();
                        int score = Integer.parseInt(parts[1].trim());
                        scores.put(name, score);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao analisar pontuação no ranking: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de ranking não encontrado. Um novo será criado.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar o ranking: " + e.getMessage());
            scores = new HashMap<>(); // Reset scores if there's an error loading
        }
    }

    public void saveRanking() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RANKING_FILE))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue()); // Formato "NOME:PONTUACAO"
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar o ranking: " + e.getMessage());
        }
    }

    public void updateScore(String playerName, int score) {
        String nameUpperCase = playerName.toUpperCase();
        scores.put(nameUpperCase, scores.getOrDefault(nameUpperCase, 0) + score);
    }

    public void displayRanking() {
        System.out.println("\n--- RANKING ---");
        if (scores.isEmpty()) {
            System.out.println("Nenhum jogador no ranking ainda.");
            return;
        }

        // Ordena o ranking por pontuação em ordem decrescente
        Map<String, Integer> sortedScores = scores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        sortedScores.forEach((name, score) -> System.out.println(name + ": " + score + " pontos"));
        System.out.println("---------------");
    }
}