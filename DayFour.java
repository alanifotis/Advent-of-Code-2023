import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import jdk.jshell.spi.ExecutionControl.ExecutionControlException;

public class DayFour {
    public static int totalTwo;
    public static int totalOne;
    public static int fileLength;
    public static List<String> file;
    public static HashMap<Integer, Integer> cardIndexCardContent = new HashMap<>();

    public static void main(String[] args) {
        try {
            file = Files.readAllLines(Paths.get("input4")); 
            fileLength = file.size() - 1;

            for (int i = 0; i <= fileLength; i++) {
                totalTwo++; 
                String currentLine = file.get(i);
                int nextLine = i + 1 > fileLength ? fileLength : i + 1;
                int winningNumbers = PartOne(currentLine);
                totalOne += (int) Math.pow(2, winningNumbers - 1);

                for (int j = 0; j < winningNumbers; j++) {
                    cardIndexCardContent.put(nextLine + j, cardIndexCardContent.getOrDefault(nextLine + j, 0) + 1);
                }
            }
            
            processWinningCard();
            
            System.out.println("Points: " + totalOne);
            System.out.println("Part Two: " + totalTwo);
        } catch (IOException e) {
            System.out.println(e);;
        }
    }

    public static int PartOne(String line) {
        String[] parts = line.split("\\|");

        if (parts.length < 2) {
            // Handle the case where there are not enough parts
            return 0;
        }

        int[] leftArray = Arrays.stream(parts[0].split("\\s+"))
            .skip(2) // skip "Card 1:"
            .mapToInt(Integer::parseInt)
            .toArray();

        int[] rightArray = Arrays.stream(parts[1].trim().split("\\s+"))
            .mapToInt(Integer::parseInt)
            .toArray();

        Set<Integer> leftSet = new HashSet<>();
        Set<Integer> commonSet = new HashSet<>();

        // Populate leftSet with elements from the left array
        Arrays.stream(leftArray).forEach(leftSet::add);

        // Check for common elements and populate commonSet
        Arrays.stream(rightArray)
             .filter(leftSet::contains)
             .forEach(commonSet::add);

        return commonSet.size();
    }

    private static void processWinningCard() {
        for (Map.Entry<Integer, Integer> card : cardIndexCardContent.entrySet()) {
                int cardCopies = card.getValue();
                int currentCardIndex = card.getKey();
                int winsCopies = PartOne(file.get(currentCardIndex));  
                int nextLine = currentCardIndex + 1;
                for (int i = 0; i < winsCopies; i++) {
                    cardIndexCardContent.put(nextLine + i, cardIndexCardContent.getOrDefault(nextLine + i, 0) + cardCopies);                   
                }
                totalTwo += cardCopies;
        }
    }
}
