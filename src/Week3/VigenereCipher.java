package Week3;

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

        // Test variables
        String p = "LOONATHEWORLD";
        String k = "roy";

        // Encrypt the plaintext
        String c = obj.crypt(p, k, Mode.ENCRYPT);
        System.out.println("Encrypted text: " + c);

        // Decrypt the cipher text
        p = obj.crypt(c, k, Mode.DECRYPT);
        System.out.println("Decrypted text: " + p);
    }

    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    enum Mode {
        ENCRYPT, DECRYPT
    }

    private String crypt(String m, String k, Mode mode) {
        // Upper case the plain text and key
        m = m.toUpperCase();
        k = k.toUpperCase();

        // build the output text
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            int a = alphabet.indexOf(m.charAt(i));
            int b = alphabet.indexOf(k.charAt(i % k.length()));
            if (mode == Mode.ENCRYPT)
                output.append(alphabet.charAt((a + b) % alphabet.length()));
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
