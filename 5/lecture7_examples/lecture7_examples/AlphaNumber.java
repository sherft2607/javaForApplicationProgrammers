/**
 * Example code for lecture 7.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class AlphaNumber {
    /**
     * Simple test program.
     * @param args arguments
     */
    public static void main(String[] args) {
        long x = Long.parseLong(args[0]);
        System.out.println(alphabetize(x));
    }

    /**
     * Returns alphabetized string representation of a number.
     * @param x a number to transform
     * @return String representation of a number
     */
    public static String alphabetize(long x) {
        if (x >= 0) {
            return nineteenDigit(x);
        }
        return "minus " + nineteenDigit(-x);
    }

    /**
     * Return string representation of one digit.
     * @param x one digit integer
     * @return string representation of one digit
     */
    private static String oneDigit(int x) {
        switch (x) {
        case 0:
            return "zero";
        case 1:
            return "one";
        case 2:
            return "two";
        case 3:
            return "three";
        case 4:
            return "four";
        case 5:
            return "five";
        case 6:
            return "six";
        case 7:
            return "seven";
        case 8:
            return "eight";
        case 9:
            return "nine";
        default:
            throw new IllegalArgumentException(String.valueOf(x));
        }
    }

    /**
     * Returns string representation of a teen number.
     * @param x teen integer
     * @return string representation of a teen
     */
    private static String teen(int x) {
        if (x < 10) {
            return oneDigit(x);
        }

        switch (x) {
        case 10:
            return "ten";
        case 11:
            return "eleven";
        case 12:
            return "twelve";
        case 13:
            return "thirteen";
        case 14:
            return "fourteen";
        case 15:
            return "fifteen";
        case 16:
            return "sixteen";
        case 17:
            return "seventeen";
        case 18:
            return "eighteen";
        case 19:
            return "nineteen";
        default:
            throw new IllegalArgumentException(String.valueOf(x));
        }
    }

    /**
     * Returns string representation of two digits.
     * @param x two digit integer
     * @return String representation of two digit integer
     */
    private static String twoDigit(int x) {
        if (x < 0 || x >= 100) {
            throw new IllegalArgumentException(String.valueOf(x));
        }

        if (x < 10) {
            return oneDigit(x);
        }
        if (x < 20) {
            return teen(x);
        }

        int tens = x / 10;
        int remainder = x % 10;

        String suffix;
        if (remainder == 0) {
            suffix = "";
        } else {
            suffix = "-" + oneDigit(remainder);
        }

        switch (tens) {
        case 2:
            return "twenty" + suffix;
        case 3:
            return "thirty" + suffix;
        case 4:
            return "forty" + suffix;
        case 5:
            return "fifty" + suffix;
        case 6:
            return "sixty" + suffix;
        case 7:
            return "seventy" + suffix;
        case 8:
            return "eighty" + suffix;
        case 9:
            return "ninety" + suffix;
        default:
            throw new AssertionError("Can't get here: " + x);
        }

    }

    /**
     * Returns String representation of a three digit number.
     * @param x three digit integer
     * @return String representation of three digit integer
     */
    private static String threeDigit(int x) {
        if (x < 0 || x >= 1000) {
            throw new IllegalArgumentException(String.valueOf(x));
        }

        if (x < 100) {
            return twoDigit(x);
        }

        int hundreds = x / 100;
        int remainder = x % 100;

        String suffix;
        if (remainder == 0) {
            suffix = "";
        } else {
            suffix = " " + twoDigit(remainder);
        }

        return oneDigit(hundreds) + " hundred" + suffix;
    }

    /**
     * Constant for Million.
     */
    private static final int MILLION = 1000 * 1000;

    /**
     * Returns String representation of a six digit number.
     * @param x six digit integer
     * @return String representation of six digit integer
     */
    private static String sixDigit(int x) {
        if (x < 0 || x >= MILLION) {
            throw new IllegalArgumentException(String.valueOf(x));
        }

        if (x < 1000) {
            return threeDigit(x);
        }

        int thousands = x / 1000;
        int remainder = x % 1000;

        String suffix;
        if (remainder == 0) {
            suffix = "";
        } else {
            suffix = " " + threeDigit(remainder);
        }

        return threeDigit(thousands) + " thousand" + suffix;
    }

    /**
     * Constant for Billion.
     */
    private static final int BILLION = MILLION * 1000;

    /**
     * Returns String representation of a nine digit number.
     * @param x nine digit integer
     * @return String representation of nine digit integer
     */
    private static String nineDigit(int x) {
        if (x < 0 || x >= BILLION) {
            throw new IllegalArgumentException(String.valueOf(x));
        }

        if (x < MILLION) {
            return sixDigit(x);
        }

        int millions = x / MILLION;
        int remainder = x % MILLION;

        String suffix;
        if (remainder == 0) {
            suffix = "";
        } else {
            suffix = " " + sixDigit(remainder);
        }

        return threeDigit(millions) + " million" + suffix;
    }

    /**
     * Constant for Trillion.
     */
    private static final long TRILLION = BILLION * 1000L;

    /**
     * Returns String representation of a twelve digit number.
     * @param x twelve digit long
     * @return String representation of a twelve digit long
     */
    private static String twelveDigit(long x) {
        if (x < 0 || x >= TRILLION) {
            throw new IllegalArgumentException(String.valueOf(x));
        }

        if (x < BILLION) {
            return nineDigit((int) x);
        }

        int billions = (int) (x / BILLION);
        int remainder = (int) (x % BILLION);

        String suffix;
        if (remainder == 0) {
            suffix = "";
        } else {
            suffix = " " + nineDigit(remainder);
        }

        return threeDigit(billions) + " billion" + suffix;
    }

    /**
     * Constant for Quadrillion.
     */
    private static final long QUADRILLION = TRILLION * 1000L;

    /**
     * Returns String representation of a fifteen digit number.
     * @param x fifteen digit long
     * @return String representation of fifteen digit long
     */
    private static String fifteenDigit(long x) {
        if (x < 0 || x >= QUADRILLION) {
            throw new IllegalArgumentException(String.valueOf(x));
        }

        if (x < TRILLION) {
            return twelveDigit(x);
        }

        int trillions = (int) (x / TRILLION);
        long remainder = x % TRILLION;

        String suffix;
        if (remainder == 0) {
            suffix = "";
        } else {
            suffix = " " + twelveDigit(remainder);
        }

        return threeDigit(trillions) + " trillion" + suffix;
    }

    /**
     * Constant for Quintillion.
     */
    private static final long QUINTILLION = QUADRILLION * 1000L;

    /**
     * Returns String representation of an eighteen digit number.
     * @param x eighteen digit long
     * @return String representation of eighteen digit long
     */
    private static String eighteenDigit(long x) {
        if (x < 0 || x >= QUINTILLION) {
            throw new IllegalArgumentException(String.valueOf(x));
        }

        if (x < QUADRILLION) {
            return fifteenDigit(x);
        }

        int quadrillions = (int) (x / QUADRILLION);
        long remainder = x % QUADRILLION;

        String suffix;
        if (remainder == 0) {
            suffix = "";
        } else {
            suffix = " " + fifteenDigit(remainder);
        }

        return threeDigit(quadrillions) + " quadrillion" + suffix;
    }

    /**
     * Returns string representation of a nineteen digit number.
     * @param x nineteen digit long
     * @return String representation of nineteen digit long
     */
    private static String nineteenDigit(long x) {
        if (x < QUINTILLION) {
            return eighteenDigit(x);
        }

        int quintillions = (int) (x / QUINTILLION);
        long remainder = x % QUINTILLION;

        String suffix;
        if (remainder == 0) {
            suffix = "";
        } else {
            suffix = " " + eighteenDigit(remainder);
        }

        return threeDigit(quintillions) + " quintillion" + suffix;
    }
}
