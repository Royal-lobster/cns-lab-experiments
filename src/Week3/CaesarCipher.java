package Weak3;

/**
 * Caesar Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class CaesarCipher {
    public static void main(String[] args) {

        // Initalize this object
        CaesarCipher obj = new CaesarCipher();

        // Test variables
        String p = "LOONATHEWORLD";
        int a = 3;

        // Encrypt the plaintext
        String c = obj.encrypt(p, a);
        System.out.println("Encrypted text: " + c);

        // Decrypt the cipher text
        p = obj.decrypt(c, a);
        System.out.println("Decrypted text: " + p);
    }

    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String crypt(String m, int k, int method) {
        StringBuilder output = new StringBuilder();
        m = m.toUpperCase();

        // loop over the plain text and add the cipher letter to sb
        for (int i = 0; i < m.length(); i++) {

            // find the pos of current charecter
            int correctPos = alphabet.indexOf(m.charAt(i));

            // find cipher pos of the charecter
            // (left shift for decrypt and right shift for encrypt)
            int cipherPos;
            if (method == 0)
                cipherPos = (correctPos + k) % 26;
            else {
                cipherPos = (correctPos - k) % 26;
                // if cipherPos is negative, add 26 to it
                if (cipherPos < 0)
                    cipherPos += 26;
            }

            // append char of alphabet at cipherPos
            char cipherChar;
            if (correctPos == -1)
                cipherChar = m.charAt(i);
            else
                cipherChar = alphabet.charAt(cipherPos);

            output.append(cipherChar);

        }
        return output.toString();
    }

    public String encrypt(String p, int k) {
        return crypt(p, k, 0);
    }

    public String decrypt(String c, int k) {
        return crypt(c, k, 1);
    }

}