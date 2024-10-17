/**
 * Example code for lecture 7.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class StringHash {
    /**
     * Simple test program to show hash code of string values.
     * @param args arguments
     */
    public static void main(String[] args) {
        String line = TextPrompter.promptLine("String>", null);
        while (line != null) {
            System.out.println(line.hashCode());
            line = TextPrompter.promptLine("String>", null);
        }
    }
}
