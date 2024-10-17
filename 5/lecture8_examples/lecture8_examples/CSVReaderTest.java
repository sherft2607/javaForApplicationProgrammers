import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Example code for lecture 8.
 * @author Jeffrey Eppinger & Terry Lee & Hammad Ahmad
 */
public class CSVReaderTest {

    /**
     * Simple test program to read CSV files.
     * @param args arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (args.length != 1) {
            System.out.println("Usage: java CSVReaderTest <filename>");
            System.exit(0);
        }

        FileReader fr = new FileReader(args[0]);
        CSVReader c = new CSVReader(fr);

        int lineNum = 0;
        boolean eof = false;

        while (!eof) {
            String[] values = c.readCSVLine();
            if (values == null) {
                eof = true;
            } else {
                lineNum = lineNum + 1;

                System.out.print("Line " + lineNum + "  " + values.length + " components:");

                for (String s : values) {
                    System.out.printf(" \"%s\"", s);
                }

                System.out.println();
            }
        }

        c.close();
    }

}
