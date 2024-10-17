import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Example code for lecture 8.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class CountTestBuffered {

    /**
     * Simple test program to check running time of BufferedReader.
     * @param args arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("A program to count the number of characters in a given file.");
            System.out.println("Usage: java CountTestBuffered filename");
            return;
        }

        try {
            long startTime = System.currentTimeMillis();
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            long count = 0;

            int c = br.read();

            while (c != -1) {
                count = count + 1;
                c = br.read();
            }

            br.close();
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
