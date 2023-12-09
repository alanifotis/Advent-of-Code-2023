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
            
            Map<String, Integer> defaultMap = Map.of("blue", 14, "green", 13, "red", 12);            
            Pattern pattern = Pattern.compile("(\\d+)\\s+(\\w+)");
                  
            List<String> file = Files.readAllLines(Paths.get("input2p1"));

            for (String line : file) {
                boolean wonGame = true;
                              
                String[] sets = line.split("\\s*;\\s*|\n");

innerloop:for (String set: sets) {
                    Map<String, Integer> setOne = new HashMap<>();

                    setOne.put("blue", 0);
                    setOne.put("green", 0);
                    setOne.put("red", 0);
                    Matcher matcher = pattern.matcher(set);

                    while (matcher.find() && wonGame) {

                        int count = Integer.parseInt(matcher.group(1));
                        String color = matcher.group(2);

                        setOne.put(color, setOne.getOrDefault(color, 0) + count);

                        if ((setOne.getOrDefault("red", 0) > defaultMap.getOrDefault("red", 0)) || (setOne.getOrDefault("blue", 0) > defaultMap.getOrDefault("blue", 0)) || (setOne.getOrDefault("green", 0) > defaultMap.getOrDefault("green", 0))) {
                            wonGame = false;
                            break innerloop;
                        }
                    }
                }

                
                if(wonGame) {
                    total += gameIds;
                }

                gameIds++;
            }

            System.out.println("Total: " + total);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
