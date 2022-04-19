import java.util.Scanner;

/**
 * DES algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class DES {

    public static void main(String[] args) {

        // Initialize this object
        DES obj = new DES();

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

    private String crypt(String m, String k, boolean isToDecrypt) {
        StringBuilder output = new StringBuilder();
        // =========== CODE HERE ========== //
        // P-Box (64bit => 64bit)
        // 16 feistel rounds (64bit => 64bit)
        // P-Box (64bit => 64bit)
        return output.toString();
    }

    private String encrypt(String p, String k) {
        return crypt(p, k, false);
    }

    private String decrypt(String c, String k) {
        return crypt(c, k, true);
    }
}