import java.io.IOException;

/**
 * Example code for lecture 7.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class IntPrompter {
    /**
     * Prompt and get the integer input from the std input.
     * @param prompt prompt string
     * @param defaultValue default value to show in the prompt
     * @return int value entered
     */
    public static int promptLine(String prompt, String defaultValue) {
        while (true) {
            System.out.print(prompt);
            if (defaultValue != null) {
                System.out.print(" [" + defaultValue + "]");
            }
            System.out.print(" ");

            String line = readLine().trim();
            if (line.length() == 0) {
                line = defaultValue;
            }

            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println(" * * * " + e);
            }
        }
    }

    /**
     * Read a line from the std input.
     * @return String value of the line
     */
    private static String readLine() {
        StringBuffer b = new StringBuffer();
        try {
            int c = System.in.read();
            while (true) {
                if (c == -1) {
                    System.err.println(" * * * End of input stream");
                    System.exit(1);
                }
                if (c == '\n') {
                    return b.toString();
                }
                if (c != '\r') {
                    b.append((char) c);
                }
                c = System.in.read();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(1);
            return null;
        }
    }
}
