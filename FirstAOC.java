import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;  // Import List interface
import java.util.Map;

public class FirstAOC {
    public static void main(String[] args) {
        try {
            List<String> file = Files.readAllLines(Paths.get("input1"));

            HashMap<String, Integer> valueMap = new HashMap<>();

            valueMap.put("one", 1);
            valueMap.put("two", 2);
            valueMap.put("three", 3);
            valueMap.put("four", 4);
            valueMap.put("five", 5);
            valueMap.put("six", 6);
            valueMap.put("seven", 7);
            valueMap.put("eight", 8);
            valueMap.put("nine", 9);

            int partOne = 0;
            int partTwo = 0;
            for (String line: file) {
                String revLine = new String(new StringBuilder(line).reverse());
                partOne += GetValue(valueMap, line, true, false);
                partOne += GetValue(valueMap, revLine, true, true);
                partTwo += GetValue(valueMap, line, false, false);
                partTwo += GetValue(valueMap, revLine, false, true);
            }
            System.out.println("Part One: " + partOne + "\nPart Two: " + partTwo);
        } catch (Exception e) {
            System.out.println("File not found!!!");
        }

    }
    private static int GetValue(HashMap<String, Integer> valuesMap, String line, Boolean isPartOne, Boolean isReversed) {
        HashMap<Integer, Integer> indexValue = new HashMap<>();

        for (Map.Entry<String, Integer> arg : valuesMap.entrySet()) {
            String key = arg.getKey();
            String revKey = new String(new StringBuilder(key).reverse());
            int value = arg.getValue();
            char[] charLine = line.toCharArray();

            for(int i = 0; i < line.length(); i++) {
                if(Character.isDigit(charLine[i])) {
                    if (i<2 || isPartOne) { return isReversed? Character.getNumericValue(charLine[i]) : Character.getNumericValue(charLine[i]) * 10;}
                    indexValue.put(i, Character.getNumericValue(charLine[i]));
                }
            }

            if(line.contains(key)) {
                indexValue.put(line.indexOf(key), value);
            }

            if(line.contains(revKey)) {
                indexValue.put(line.indexOf(revKey), value);
            }
        }

        int initialIndex = 100;
        int VALUE = 0;

        for (Map.Entry<Integer, Integer> arg : indexValue.entrySet()) {

            int index = arg.getKey();
            int value = arg.getValue();

            if(index < initialIndex) {
                VALUE = value;
                initialIndex = index;
            }
        }
        return isReversed? VALUE : VALUE * 10;
    }
}
