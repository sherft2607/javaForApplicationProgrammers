import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Example code for lecture 8.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class CountTest {

    /**
     * Simple test program to check running time of FileReader.
     * @param args arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("A program to count the number of characters in a given file.");
            System.out.println("Usage: java CountTest filename");
            return;
        }

        try {
            long startTime = System.currentTimeMillis();
            FileReader fr = new FileReader(args[0]);
            long count = 0;

            int c = fr.read();
            while (c != -1) {
                count = count + 1;
                c = fr.read();
            }

            fr.close();
            long endTime = System.currentTimeMillis();
            System.out.println(count + " characters counted in " + (endTime - startTime) + " milliseconds");
        } catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + " was not found");
        } catch (IOException e) {
            System.out.println();
            System.out.println("IOException!");
        }
    }

}
