package Week3;

/**
 * Affine Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class AffineCipher {

    public static void main(String[] args) {
        // Initialize this object
        AffineCipher obj = new AffineCipher();

        // Test variables
        String p = "LOONATHEWORLD";
        int a = 3, b = 5;

        // Encrypt the plaintext
        String c = obj.encrypt(p, a, b);
        System.out.println("Encrypted text: " + c);

        // Decrypt the cipher text
        p = obj.decrypt(c, a, b);
        System.out.println("Decrypted text: " + p);

    }

    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encrypt(String msg, int a, int b) {
        msg = msg.toUpperCase();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) != ' ') {
                int pos = alphabet.indexOf(msg.charAt(i));
                int cipherPos = (a * pos + b) % 26;
                char cipherChar = alphabet.charAt(cipherPos);
                output.append(cipherChar);
            } else
                output.append(" ");
        }
        return output.toString();
    }

    public String decrypt(String cipher, int a, int b) {
        // ---------------------------------
        // Find multiplicative inverse of a
        // ---------------------------------
        int a_inv = 0;
        for (int i = 0; i < 26; i++)
            if ((a * i) % 26 == 1)
                a_inv = i;

        // ---------------------------------
        // Decrypt the text with a_inv and b
        // ---------------------------------
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) != ' ') {
                int pos = alphabet.indexOf(cipher.charAt(i));
                int cipherPos = (a_inv * (pos - b)) % 26;
                // if cipherPos is negative, add 26 to it
                if (cipherPos < 0)
                    cipherPos += 26;
                char cipherChar = alphabet.charAt(cipherPos);
                output.append(cipherChar);
            } else
                output.append(" ");
        }
        return output.toString();
    }
}
