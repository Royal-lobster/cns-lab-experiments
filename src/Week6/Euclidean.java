package Week6;

public class Euclidean {
    public static void main(String[] args) {
        System.out.println("GCD of 12, 15 is " + gcd(12, 15));
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
