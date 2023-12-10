import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;


public class SecondAOC {
    public static void main(String[] args) {
        try {

            int gameIds = 1;
            int total = 0;
            int totalTwo = 0;

            Map<String, Integer> defaultMap = Map.of("blue", 14, "green", 13, "red", 12);

            Pattern pattern = Pattern.compile("(\\d+)\\s+(\\w+)");

            List<String> file = Files.readAllLines(Paths.get("input2p1"));

            for (String line : file) {
                boolean wonGame = true;

                HashMap<String, Integer> partTwo = new HashMap<>();

                partTwo.put("blue", 0);
                partTwo.put("green", 0);
                partTwo.put("red", 0);



                String[] sets = line.split("\\s*;\\s*|\n");

innerloop:for (String set: sets) {
              HashMap<String, Integer> setOne = new HashMap<>();

              setOne.put("blue", 0);
              setOne.put("green", 0);
              setOne.put("red", 0);
              Matcher matcher = pattern.matcher(set);

              while (matcher.find()) {

                  int count = Integer.parseInt(matcher.group(1));
                  String color = matcher.group(2);
                  if (wonGame) {
                      setOne.put(color, setOne.getOrDefault(color, 0) + count);

                      if ((setOne.getOrDefault("red", 0) > defaultMap.getOrDefault("red", 0)) || (setOne.getOrDefault("blue", 0) > defaultMap.getOrDefault("blue", 0)) || (setOne.getOrDefault("green", 0) > defaultMap.getOrDefault("green", 0))) {
                          wonGame = false;
                      }
                  }



                  if (count >= partTwo.getOrDefault(color, 0)) {
                      partTwo.put(color , count);
                  }
              }
}

int red = partTwo.getOrDefault("red", 0);
int green = partTwo.getOrDefault("green", 0);
int blue = partTwo.getOrDefault("blue", 0);

totalTwo += red * green * blue;
if(wonGame) {
    total += gameIds;
}

gameIds++;
            }

System.out.println("Total: " + total + "\nTotal Two: " + totalTwo);
} catch (Exception e) {
    System.out.println(e);
}
    }
}
