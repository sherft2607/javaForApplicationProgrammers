import java.util.ArrayList;
import java.util.List;

/**
 * Example code for lecture 7.
 * Bad style
 * @author Jeffrey Eppinger & Terry Lee
 */
public class StringListBad {
    /**
     * Simple test program of ArrayList used before Java 5.
     * On Eclipse, this will compile but there are warnings.
     * @param args arguments
     */
    public static void main(String[] args) {
        List list = new ArrayList();

        String line = TextPrompter.promptLine("String>", null);

        while (line != null) {
            // you can insert any object
            list.add(line);
            line = TextPrompter.promptLine("String>", null);
        }

        // the type is object while iterating
        for (Object o : list) {
            // notice that we have to cast since we know we added strings
            String s = (String) o;
            System.out.println(s);
        }
    }
}
