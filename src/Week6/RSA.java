package Weak6;

import java.math.*;

public class RSA {

    public static void main(String args[]) {

        // ==============
        // Key Generation
        // ==============

        // Initialize algorithm variables
        int p = 3, q = 11, n = p * q;
        int phi = (p - 1) * (q - 1);
        int d = 0, e;

        // Calculate exponent e (Part of Public key)
        for (e = 2; e < phi; e++)
            if (gcd(e, phi) == 1)
                break;

        // Calculate d (Part of Private key)
        for (int i = 0; i <= 9; i++) {
            int x = 1 + (i * phi);
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }

        // Print Public and Private keys
        System.out.println("Public key: [" + n + ", " + e + "]");
        System.out.println("Private key: [" + n + ", " + d + "]");

        // =======================
        // Encryption / Decryption
        // =======================

        // Initialize message variables
        int pMsg = 12;
        double cMsg;
        BigInteger dMsg;

        cMsg = (Math.pow(pMsg, e)) % n;
        System.out.println("Encrypted message is : " + cMsg);

        // converting int/double values to BigInteger
        BigInteger N = BigInteger.valueOf(n);
        BigInteger C_MSG = BigDecimal.valueOf(cMsg).toBigInteger();

        dMsg = (C_MSG.pow(d)).mod(N);
        System.out.println("Decrypted message is : " + dMsg);
    }

    static int gcd(int e, int z) {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }
}
