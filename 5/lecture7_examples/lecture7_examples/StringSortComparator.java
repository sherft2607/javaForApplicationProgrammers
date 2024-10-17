import java.util.Comparator;

/**
 * Comparator interface implementation example.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class StringSortComparator implements Comparator<String> {
    /**
     * Implementation of compare method.
     * @param s1 String to compare
     * @param s2 Another String to compare
     * @return negative, zero, or positive values
     */
    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}
