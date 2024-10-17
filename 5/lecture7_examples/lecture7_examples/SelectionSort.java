import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Example code for lecture 7.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class SelectionSort {
    /**
     * Decimal format variable to use in formatting values.
     */
    private static DecimalFormat df = new DecimalFormat("#,###");

    /**
     * Simple test program using ArrayList of string and sorting performance.
     * @param args arguments
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        if (args.length == 0) {
            System.out.println("java SelectionSort <size>");
            return;
        }

        int size = Integer.parseInt(args[0]);

        long constructionStartTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(AlphaNumber.alphabetize(i));
        }
        long constructionEndTime = System.currentTimeMillis();
        System.out.println("Building ArrayList with " + df.format(size) + " elements takes "
                + df.format(constructionEndTime - constructionStartTime) + " ms");

        long sortStartTime = System.currentTimeMillis();

        int n = list.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    String temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        long sortEndTime = System.currentTimeMillis();
        System.out.println("Sorting ArrayList with " + df.format(size) + " elements takes "
                + df.format(sortEndTime - sortStartTime) + " ms");

        if (args.length > 1) {
            for (String s : list) {
                System.out.println(s);
            }
        }

    }
}
