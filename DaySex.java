import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;

public class DaySex {
    static List<String> file;
    static int fileLength;
    public static void main(String[] args) {
        try {
            file = Files.readAllLines(Paths.get("input6"));
            fileLength = file.size();
        } catch (Exception e) {
            System.out.println(e);
        }

        int[] availableTime = Arrays.stream(file.get(0).split("\\D+"))
            .filter(s -> !s.isBlank())
            .mapToInt(Integer::parseInt)
            .toArray();

        int[] recordDistance = Arrays.stream(file.get(1).split("\\D+"))
            .filter(s -> !s.isBlank())
            .mapToInt(Integer::parseInt)
            .toArray();


        System.out.println("Part 1: " + PartOne(availableTime, recordDistance));
        System.out.println("Part 2: " + PartTwo());

    }

    private static int PartOne(int[] availableTime, int[] recordDistance) {
        int total = 0;
        for (int i = 0; i < recordDistance.length; i++) {
            int wins = 0;
            for (int j = 0; j <= availableTime[i]; j++) {
                int speed = j * 1;
                int remainingTime = availableTime[i] - j;
                int lapRecord = remainingTime * speed;
                int currentLapRecord = recordDistance[i];
                wins = lapRecord > currentLapRecord ? wins + 1 : wins; 
            }

            if (total == 0) { total += wins; }
            else if (total > 0) {total = wins == 0 ? total + wins : total * wins;}
        }
        return total;
    }

    private static long PartTwo() {
        long availableTime = Long.parseLong(file.get(0).replaceAll("\\D", ""));
        long record = Long.parseLong(file.get(1).replaceAll("\\D", ""));
        System.out.println("av Time: " + availableTime + "\nrec: " + record);

        long wins = 0;

        for (long i = 0; i <= availableTime; i++) {
            long speed = i * 1;
            long remainingTime = availableTime - i;
            long lapRecord = remainingTime * speed;
            wins = lapRecord > record ? wins + 1 : wins + 0; 
        }
        return wins;
    }
}
