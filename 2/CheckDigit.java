/**
*Homework 2 solution to compute check digit.
*@author Shandon Herft (sherft@andrew.cmu.edu)
*/

public class CheckDigit {
    public static void main(String[] args) {
        String x = args[0];

        char a = x.charAt(0);
        int aInt = a - '0';

        char b = x.charAt(1);
        int bInt = b - '0';

        char c = x.charAt(2);
        int cInt = c - '0';

        char d = x.charAt(3);
        int dInt = d - '0';

        char e = x.charAt(4);
        int eInt = e - '0';

        char f = x.charAt(5);
        int fInt = f - '0';

        char g = x.charAt(6);
        int gInt = g - '0';

        char h = x.charAt(7);
        int hInt = h - '0';

        char i = x.charAt(8);
        int iInt = i - '0';

        char j = x.charAt(9);
        int jInt = j - '0';

        char k = x.charAt(10);
        int kInt = k - '0';

        int twelthDigit = ((10 - (3 * aInt + bInt + 3 * cInt + dInt + 3 * eInt + fInt + 3 * gInt + hInt + 3 * iInt + jInt + 3 * kInt) % 10) % 10);

        System.out.println(x);
        System.out.println(twelthDigit);
    }
}
