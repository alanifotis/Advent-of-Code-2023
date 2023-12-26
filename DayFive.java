import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class DayFive {
    static List<String> file;
    static int fileLength;
    public static void main(String[] args) {
        try {
            file = Files.readAllLines(Path.of("input5"));
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        fileLength = file.size();
        long[] locations = Arrays.stream(file.get(0).split("\\D+"))
            .filter(s -> !s.isBlank())
            .map(Long::parseLong)
            .parallel()
            .mapToLong(seed -> FromSeedToLocation(seed))
            .toArray();

        Arrays.sort(locations);

        System.out.println("Part 1: " + locations[0]);

    }

    private static long FromSeedToLocation (long seed) {
        
        for (int i = 3 ; i < fileLength; i++) {

            String[] currentLine = file.get(i).split("\\D+");
            boolean reachedEOF = i >= fileLength - 1;

            while (currentLine.length > 1 && !reachedEOF) {            
                long destinationStart = Long.parseLong(currentLine[0]);
                long sourceStart = Long.parseLong(currentLine[1]);
                long range = Long.parseLong(currentLine[2]);
                final long seedCopy = seed;


                /* int[] destinationRange = IntStream.range(destinationStart, destinationStart + range)
                    .filter(num -> num == seedCopy)
                    .toArray(); */


                long[] sourceRange = LongStream.range(sourceStart, sourceStart + range)
                    .filter(num -> num == seedCopy)
                    .toArray();
                if (sourceRange.length > 0) {
                    boolean some = true;
                    seed = destinationStart + ( seed - sourceStart);
                    while (some) {
                        i++;
                        String[] nextLine = file.get(i).split("\\D+");
                        if (i >= fileLength - 1 || nextLine.length < 2) {
                            some = false;
                            break;
                        }
                    }
                    break;
                }

                if (i + 1 == fileLength) { reachedEOF = true; break;}

                i++;

                currentLine = file.get(i).split("\\D+");


                if (currentLine.length <= 1) {
                    
                    break;
                }
            } 
            if(reachedEOF) {
                return seed;
            } 
        }
        return seed;
    }

}
