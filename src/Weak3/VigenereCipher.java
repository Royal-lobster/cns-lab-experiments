package Weak3;

import java.util.Scanner;

/**
 * Vigenere Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class VigenereCipher {

    public static void main(String[] args) {

        // Initialize this object
        VigenereCipher obj = new VigenereCipher();

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

    private String crypt(String m, String k, boolean isToDecrypt) {
        // Upper case the plain text and key
        m = m.toUpperCase();
        k = k.toUpperCase();

        // Repeat key if it is shorter than plain text
        StringBuilder repeatedKey = new StringBuilder();
        while (repeatedKey.length() < m.length()) {
            for (int i = 0; i < k.length(); i++) {
                if (repeatedKey.length() == m.length())
                    break;
                repeatedKey.append(k.charAt(i));
            }
        }
        k = repeatedKey.toString();

        // build the output text
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            int a = findPos(m.charAt(i));
            int b = findPos(k.charAt(i));
            if (a == -1 || b == -1) {
                output.append(m.charAt(i));
            } else {
                if (!isToDecrypt)
                    output.append(alphabet.charAt((a + b) % alphabet.length()));
                else {
                    int charPos = (a - b) % alphabet.length();
                    if (charPos < 0)
                        charPos += alphabet.length();
                    output.append(alphabet.charAt(charPos));
                }
            }
        }
        return output.toString();
    }

    private String encrypt(String p, String k) {
        return crypt(p, k, false);
    }

    private String decrypt(String c, String k) {
        return crypt(c, k, true);
    }
}
