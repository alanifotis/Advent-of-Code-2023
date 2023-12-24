import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.nio.file.*;

public class ThreeAOC {
    public static List<String> file;
    public static int LINES;

    public static void main(String[] args) {
        try {
            file = Files.readAllLines(Paths.get("input3"));
            LINES = file.size();
        } catch (Exception e) {
        }


        int total = 0;

        for (int i = 0; i < LINES; i++) {
            String [] numbers = LineSpliter(file.get(i));                
            total += NumberChecker(numbers, i, file.get(i));
        }
        System.out.println("Total: " + total);

    }

    public static String[] LineSpliter(String line) {
        return Arrays.stream(line.split("\\D+"))
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);       
    }

    public static int NumberChecker(String[] numbers, int lineIndex, String line) {
        int lineLength = file.get(lineIndex).length() - 1;
        int subTotal = 0;
        for (String number : numbers) {
            //System.out.println("Checking number: " + number);
            for (int i = 0; i < number.length(); i++) {

                int currentCharIndex = file.get(lineIndex).indexOf(number) + i;
                int nextCharIndex = currentCharIndex == lineLength ? -1 : currentCharIndex + 1;
                int previousCharIndex = currentCharIndex == 0 ? -1 : currentCharIndex - 1;


                int nextLine = lineIndex == (LINES - 1) ? -1 : lineIndex + 1;
                int previousLine = lineIndex == 0 ? -1 : lineIndex - 1;

                boolean leftSide = LeftOrRight(previousCharIndex, -1, -1, lineIndex);
                boolean rightSide = LeftOrRight(-1, nextCharIndex, -1, lineIndex);

                boolean previousLeftSide = LeftOrRight(previousCharIndex, -1, -1, previousLine);
                boolean previousRightSide = LeftOrRight(-1, nextCharIndex, -1, previousLine);

                boolean nextLeftSide = LeftOrRight(previousCharIndex, -1, -1, nextLine);
                boolean nextRightSide = LeftOrRight(-1, nextCharIndex, -1, nextLine);

                boolean currentPositionPreviousLine = previousLine < 0 ? false : LeftOrRight(-1, -1, currentCharIndex, previousLine);
                boolean currentPositionNextLine = nextLine < 0 ? false : LeftOrRight(-1, -1, currentCharIndex, nextLine);
                //System.out.println("DOING CURRENT POSITION");

                if (leftSide || rightSide || previousLeftSide || previousRightSide || currentPositionPreviousLine || nextLeftSide || nextRightSide || currentPositionNextLine) {
                    subTotal += Integer.parseInt(number);
                    //String newLine = line.substring(0, line.indexOf(number)) + ".".repeat(number.length()) + line.substring(line.indexOf(number) + number.length());
                    //String newLine = line.replace(number, );
                    //file.set(lineIndex, newLine);
                    System.out.println(number);
                    break;
                }                                
            }
        }
        return subTotal;
    }

    public static boolean LeftOrRight(int previousCharIndex, int nextCharIndex, int currentCharIndex, int lineIndex) {
        if (lineIndex < 0) {
            return false;            
        }

        char previousChar = previousCharIndex < 0 ? '.' : file.get(lineIndex).charAt(previousCharIndex);
        //System.out.println("Previous char: " + previousChar + "Previous Char Index: " + previousCharIndex);

        char nextChar = nextCharIndex < 0 ? '.' : file.get(lineIndex).charAt(nextCharIndex);
        //System.out.println("Next char: " + nextChar + "Next Char Index: " + nextCharIndex);


        char currentChar = currentCharIndex < 0 ? '.' : file.get(lineIndex).charAt(currentCharIndex);
        //System.out.println("Current char: " + currentChar + "Current Char Index: " + currentCharIndex);



        boolean leftSide = previousCharIndex < 0 ? false: previousChar != '.' && !Character.isDigit(previousChar);  

        boolean rightSide = nextCharIndex < 0 ? false : nextChar != '.' && !Character.isDigit(nextChar);

        boolean currentPosition = currentCharIndex < 0 ? false : currentChar != '.' && !Character.isDigit(currentChar);

        if (leftSide || rightSide || currentPosition) {
            return true;            
        }
        return false;    
    }
}
