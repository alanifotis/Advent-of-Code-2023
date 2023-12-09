import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BetterAOC1 {
    public static void main(String[] args) {
        try {
            List<String> file = Files.readAllLines(Paths.get("input1"));

            Map<String, Integer> valueMap = Map.of(
                    "one", 1, "two", 2, "three", 3, "four", 4, "five", 5,
                    "six", 6, "seven", 7, "eight", 8, "nine", 9
            );

            int partOne = 0;
            int partTwo = 0;

            for (String line : file) {
                partOne += calculateValue(valueMap, line, false);
                partOne += calculateValue(valueMap, new StringBuilder(line).reverse().toString(), true);
                partTwo += calculateValue(valueMap, line, true);
                partTwo += calculateValue(valueMap, new StringBuilder(line).reverse().toString(), true);
            }

            System.out.println("Part One: " + partOne + "\nPart Two: " + partTwo);

        } catch (IOException e) {
            System.out.println("File not found!!!");
        }
    }

    private static int calculateValue(Map<String, Integer> valuesMap, String line, boolean isReversed) {
        Map<Integer, Integer> indexValue = new HashMap<>();

        valuesMap.forEach((key, value) -> {
            String revKey = new StringBuilder(key).reverse().toString();

            line.chars().filter(Character::isDigit)
                    .forEach(i -> indexValue.put(i, (i < 2 || isReversed) ? i : i * 10));

            indexValue.putIfAbsent(line.indexOf(key), value);
            indexValue.putIfAbsent(line.indexOf(revKey), value);
        });

        return isReversed ? indexValue.values().stream().min(Integer::compare).orElse(0) :
                indexValue.values().stream().mapToInt(Integer::intValue).sum();
    }
}
