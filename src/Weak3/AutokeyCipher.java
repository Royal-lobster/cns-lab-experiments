package Weak3;

import java.util.Scanner;

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

        // ------------------
        // ENCRYPTION PHASE
        // ------------------

        // Ask user to enter plain text
        Scanner input = new Scanner(System.in);
        System.out.print("Enter plain text to encrypt: ");
        String p = input.nextLine();

        // Ask user to enter key
        System.out.print("Enter key to encryption: ");
        String k = input.nextLine();

        // Encrypt the plain text with key and display the cipher text
        String c = obj.encrypt(p, k);
        System.out.println("Cipher text: " + c);

        // ------------------
        // DECRYPTION PHASE
        // ------------------

        // Ask user to enter cipher text
        System.out.print("Enter cipher text to decrypt: ");
        c = input.nextLine();

        // Ask user to enter key
        System.out.print("Enter key for decryption: ");
        k = input.nextLine();

        // Decrypt the cipher text with key and display the plain text
        p = obj.decrypt(c, k);
        System.out.println("Plain text: " + p);

        // Close input
        input.close();
    }

    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public int findPos(char a) {
        for (int i = 0; i < alphabet.length(); i++)
            if (alphabet.charAt(i) == a)
                return i;
        return -1;
    }

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
            int a = findPos(p.charAt(i));
            int b = findPos(k.charAt(i));
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
                a = findPos(c.charAt(i));
                b = findPos(k.charAt(0));
                k = k.substring(1);
            } else {
                // if key exhausted, use the decoded plain text
                // as key for further decryption
                a = findPos(c.charAt(i));
                b = findPos(output.charAt(i - k_len));
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
