package Weak6;

public class ExtendedEuclidean {
    public static void main(String[] args) {
        System.out.println("Inverse of 26 mod 3 is " + egcd(26, 3, 0, 1));
    }

    private static int egcd(int a, int b, int t1, int t2) {
        if (b == 0)
            return t1;
        else
            return egcd(b, a % b, t2, t1 - t2 * (a / b));

    }
}