/**
 * Example code for lecture 7.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class IntHash {
    /**
     * Returns hash code of an integer entered from std input.
     * @param args arguments
     */
    public static void main(String[] args) {
        int i = IntPrompter.promptLine("int>", "-1");
        while (i != -1) {
            System.out.println(Integer.valueOf(i).hashCode());
            i = IntPrompter.promptLine("int>", "-1");
        }
    }
}
