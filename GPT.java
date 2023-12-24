import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GPT {

    public static void main(String[] args) {
        try {
            // Read data from the input file
            Scanner scanner = new Scanner(new File("input4"));

            List<Set<Integer>> cards = new ArrayList<>();

            // Process the cards
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Skip any lines that do not start with "Card"
                if (!line.startsWith("Card")) {
                    continue;
                }

                // Extract the numbers from the line
                String[] parts = line.split(":");
                String[] numbers = parts[1].trim().split("\\s*\\|\\s*|\\s+");

                Set<Integer> currentCard = new HashSet<>();
                for (String number : numbers) {
                    currentCard.add(Integer.parseInt(number));
                }

                cards.add(currentCard);
            }

            Map<Integer, Integer> cardCounts = new HashMap<>();

            // Process each card
            for (int i = 0; i < cards.size(); i++) {
                Set<Integer> winningNumbers = cards.get(i);
                processCard(i, cards, winningNumbers, cardCounts);
            }

            // Calculate total number of scratch cards
            int totalScratchcards = 0;
            for (int count : cardCounts.values()) {
                totalScratchcards += count;
            }

            System.out.println("Total number of scratch cards: " + totalScratchcards);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void processCard(int currentCardIndex, List<Set<Integer>> cards, Set<Integer> winningNumbers, Map<Integer, Integer> cardCounts) {
        int matchingNumbers = countMatchingNumbers(winningNumbers, cards.get(currentCardIndex));

        // Update the count for the current card
        int instances = cardCounts.getOrDefault(currentCardIndex, 0);
        cardCounts.put(currentCardIndex, instances + 1);

        // If there are matching numbers, recursively process the other card
        if (matchingNumbers > 0 && currentCardIndex + matchingNumbers < cards.size()) {
            processCard(currentCardIndex + matchingNumbers, cards, winningNumbers, cardCounts);
        }
    }

    private static int countMatchingNumbers(Set<Integer> winningNumbers, Set<Integer> card) {
        int count = 0;
        for (int num : winningNumbers) {
            if (card.contains(num)) {
                count++;
            }
        }
        return count;
    }
}

