import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Example code for lecture 7.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class StringSort {
    /**
     * Simple test program to show ArrayList of String and Collections sort.
     * @param args arguments
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (String s : args) {
            list.add(s);
        }

        System.out.println("Before sort:");
        for (String s : list) {
            System.out.println("    " + s);
        }

        Collections.sort(list);

        System.out.println("After sort:");
        for (String s : list) {
            System.out.println("    " + s);
        }
    }
}
