import java.util.List;
import java.util.HashMap;
import java.nio.file.*;

public class ThreeAOC {
    public static void main(String[] args) {
        try {
            int total = 0;
            List<String> file = Files.readAllLines(Paths.get("input3"));
            int lineLength = file.size();

            HashMap<String, Integer> symbolPosition = new HashMap<>();
            symbolPosition.put("currentX", 0);
            symbolPosition.put("currentY", 0);
            symbolPosition.put("previousX", 0);
            symbolPosition.put("nextX", 0);
            symbolPosition.put("previousY", 0);
            symbolPosition.put("nextY", 0);


            for (int j = 0; j < lineLength; j++) {
                String[] lineArray =  file.split("\\.");
                System.out.println(line);
                int repetitions = lineArray.length;
                int[] possibleNumbers = new int[repetitions];

                                

                for (int i = 0; i < repetitions; i++) {
                    int currentX = file.indexOf(lineArray[i]);
                    switch (lineArray[i].length()) {
                        case 1:
                            symbolPosition.put("currentX", currentX);
                            symbolPosition.put("currentY", i);

                            if(i > 0) {




                            break;

                        default:
                            break;
                    }                    }                    
                }
            } 
            System.out.println("Total: " + total);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
