import java.util.LinkedList;
import java.util.List;

/**
 * Example code for lecture 7.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class StringList {
    /**
     * Simple test program to show LinkedList of String.
     * @param args arguments
     */
    public static void main(String[] args) {
        List<String> list = new LinkedList<String>();

        String line = TextPrompter.promptLine("String>", null);

        while (line != null) {
            list.add(line);
            line = TextPrompter.promptLine("String>", null);
        }

        for (String s : list) {
            System.out.println(s);
        }
    }
}
