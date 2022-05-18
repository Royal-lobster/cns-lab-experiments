package Weak3;

import java.util.Scanner;

/**
 * Affine Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class AffineCipher {

    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public int findPos(char a) {
        for (int i = 0; i < alphabet.length(); i++)
            if (alphabet.charAt(i) == a)
                return i;
        return -1;
    }

    public String encrypt(String msg, int a, int b) {
        msg = msg.toUpperCase();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) != ' ') {
                int pos = findPos(msg.charAt(i));
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
        // Find mulitplicative inverse of a
        // ---------------------------------
        int a_inv = 0;
        int flag = 0;
        for (int i = 0; i < 26; i++) {
            flag = (a * i) % 26;

            // Check if (a*i)%26 == 1,
            // then i will be the multiplicative inverse of a
            if (flag == 1) {
                a_inv = i;
            }
        }

        // ---------------------------------
        // Decrypt the text with a_inv and b
        // ---------------------------------
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) != ' ') {
                int pos = findPos(cipher.charAt(i));
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

    // Driver code
    public static void main(String[] args) {
        // Initalize this object
        AffineCipher obj = new AffineCipher();

        // ------------------
        // ENCRYPTION PHASE
        // ------------------

        // Ask user to enter plain text
        Scanner input = new Scanner(System.in);
        System.out.print("Enter plain text to encrypt: ");
        String p = input.nextLine();

        // Ask user to enter key
        // a should be in [1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25] only
        System.out.print("Enter first key for encryption: ");
        int a = input.nextInt();
        input.nextLine();
        System.out.print("Enter second key for encryption: ");
        int b = input.nextInt();
        input.nextLine();

        // Encrypt the plain text with key and display the cipher text
        String c = obj.encrypt(p, a, b);
        System.out.println("Cipher text: " + c);

        // ------------------
        // DECRYPTION PHASE
        // ------------------

        // Ask user to enter cipher text
        System.out.print("Enter cipher text to decrypt: ");
        c = input.nextLine();

        // Ask user to enter key
        System.out.print("Enter first key for decryption: ");
        a = input.nextInt();
        input.nextLine();
        System.out.print("Enter second key for decryption: ");
        b = input.nextInt();
        input.nextLine();

        // Decrypt the cipher text with key and display the plain text
        p = obj.decrypt(c, a, b);
        System.out.println("Plain text " + p);

        // Close input
        input.close();
    }
}
