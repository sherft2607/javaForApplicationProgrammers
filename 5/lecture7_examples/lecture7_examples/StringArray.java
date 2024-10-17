/**
 * Example code for lecture 7.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class StringArray {
    /**
     * Simple test program to show array of String.
     * Compare this with StringList class!
     * @param args arguments
     */
    public static void main(String[] args) {
        String[] a = new String[0];

        String line = TextPrompter.promptLine("String>", null);

        while (line != null) {
            String[] b = new String[a.length + 1];
            for (int i = 0; i < a.length; i++) {
                b[i] = a[i];
            }
            b[b.length - 1] = line;
            a = b;
            line = TextPrompter.promptLine("String>", null);
        }

        for (String s : a) {
            System.out.println(s);
        }
    }
}
