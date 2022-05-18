package Weak3;

/**
 * Autokey Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class AutokeyCipher {

    public static void main(String[] args) {

        // Initialize this object
        AutokeyCipher obj = new AutokeyCipher();

        // Test variables
        String p = "LOONATHEWORLD";
        String k = "roy";

        // Encrypt the plaintext
        String c = obj.encrypt(p, k);
        System.out.println("Encrypted text: " + c);

        // Decrypt the cipher text
        p = obj.decrypt(c, k);
        System.out.println("Decrypted text: " + p);
    }

    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String encrypt(String p, String k) {
        // Upper case the plain text and key
        p = p.toUpperCase();
        k = k.toUpperCase();

        // Repeat key if it is shorter than plain text
        String repeatedKey = k;
        while (repeatedKey.length() < p.length()) {
            for (int i = 0; i < p.length(); i++) {
                if (repeatedKey.length() == p.length())
                    break;
                repeatedKey += p.charAt(i);
            }
        }
        k = repeatedKey;

        // build the output text
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            int a = alphabet.indexOf(p.charAt(i));
            int b = alphabet.indexOf(k.charAt(i));
            if (a == -1 || b == -1)
                output.append(p.charAt(i));
            else
                output.append(alphabet.charAt((a + b) % alphabet.length()));

        }
        return output.toString();
    }

    private String decrypt(String c, String k) {
        // Upper case the plain text and key
        c = c.toUpperCase();
        k = k.toUpperCase();
        int k_len = k.length();

        // Build the decrypted text letter by letter
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < c.length(); i++) {
            int a;
            int b;
            if (k.length() > 0) {
                // use first letter of key and remove it from key
                // as its used
                a = alphabet.indexOf(c.charAt(i));
                b = alphabet.indexOf(k.charAt(0));
                k = k.substring(1);
            } else {
                // if key exhausted, use the decoded plain text
                // as key for further decryption
                a = alphabet.indexOf(c.charAt(i));
                b = alphabet.indexOf(output.charAt(i - k_len));
            }
            if (a == -1 || b == -1)
                output.append(c.charAt(i));
            else {
                int charPos = (a - b) % alphabet.length();
                if (charPos < 0)
                    charPos += alphabet.length();
                output.append(alphabet.charAt(charPos));
            }
        }
        return output.toString();
    }
}
