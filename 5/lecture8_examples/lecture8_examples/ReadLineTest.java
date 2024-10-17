import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Example code for lecture 8.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class ReadLineTest {

    /**
     * Simple test program to print lines of a file using BufferedReader.
     * @param args arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Program to print out lines of a file, in typical fashion:");
            System.out.println("using BufferedReader");
            System.out.println("doing it one line at a time (with readLine())");
            System.out.println("not worrying about unprintable characters");
            System.out.println("put line numbers in front of each printed line");
            System.out.println("  Usage: java ReadLineTest filename");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            int lineNumber = 0;
            boolean eof = false;

            while (!eof) {
                String line = br.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    lineNumber = lineNumber + 1;
                    System.out.println("line " + lineNumber + ": " + line);
                }
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + " was not found");
            return;
        } catch (IOException e) {
            System.out.println();
            System.out.println(e);
            return;
        }
    }
}
