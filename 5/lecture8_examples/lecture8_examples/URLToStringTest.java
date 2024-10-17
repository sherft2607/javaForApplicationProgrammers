import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Example code for lecture 8.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class URLToStringTest {
    /**
     * Maximum size constant.
     */
    private static final int MAX_SIZE = 4 * 1024 * 1024;

    /**
     * Reads the response from a url and returns its string representation.
     * @param urlString url to parse
     * @return String representation of the response from a url
     */
    private static String urlToString(String urlString) {
        try {
            URL url = new URL(urlString);
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder b = new StringBuilder();
            boolean eof = false;

            while (!eof && b.length() < MAX_SIZE) {
                String line = br.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    b.append(line).append('\n');
                }
            }

            br.close();
            return b.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Simple test program to read and print from a url.
     * @param args arguments
     */
    public static void main(String[] args) {
        System.out.println(urlToString(args[0]));
    }

}
