package Weak7;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA {
    public static String hash(String msg) {
        try {
            // Initialize the SHA-1 hash generator
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Convert the message to bytes and digest it
            byte[] digest = md.digest(msg.getBytes());

            // Convert byte array into signum representation
            BigInteger DIGEST_NUM = new BigInteger(1, digest);
            String hash = DIGEST_NUM.toString(16);
            while (hash.length() < 32)
                hash = "0" + hash;

            // Return the hashed message after pre-pending 0's
            return hash;
        } catch (Exception ignored) {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(hash("Hello World"));
    }
}
