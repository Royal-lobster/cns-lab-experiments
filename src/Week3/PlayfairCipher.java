package Week3;

import java.util.ArrayList;
import java.util.List;

/**
 * Playfair Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class PlayfairCipher {

    public static void main(String[] args) {

        // Initialize this object
        PlayfairCipher obj = new PlayfairCipher();

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

    public List<String> digraphs(String m) {
        List<String> digraphs = new ArrayList<String>();
        for (int i = 0; i < m.length(); i += 2)
            if (i + 1 < m.length() && m.charAt(i) != m.charAt(i + 1))
                digraphs.add(Character.toString(m.charAt(i)) + Character.toString(m.charAt(i + 1)));
            else
                digraphs.add(Character.toString(m.charAt(i)) + Character.toString('X'));

        return digraphs;
    }

    private List<Character> getKeyMatrix(String k) {
        // create a 2D list of characters
        List<Character> keyMatrix = new ArrayList<Character>();

        // append each character of k to keyMatrix
        for (int i = 0; i < k.length(); i++)
            // if character is not already in keyMatrix, append it
            if (!keyMatrix.contains(k.charAt(i)))
                keyMatrix.add(k.charAt(i));

        // if size of keyMatrix is less than 5, append rest of alphabet to it
        if (keyMatrix.size() < 25)
            for (int i = 0; i < alphabet.length(); i++)
                if (!keyMatrix.contains(alphabet.charAt(i)) && alphabet.charAt(i) != 'J') {
                    keyMatrix.add(alphabet.charAt(i));
                    if (keyMatrix.size() == 25)
                        break;
                }

        return keyMatrix;
    }

    public String crypt(String m, String k, Mode mode) {
        // convert p and k to upper case
        m = m.toUpperCase();
        k = k.toUpperCase();

        // convert string p to digraphs
        List<String> digraphs = digraphs(m);

        // get the key matrix
        List<Character> keyMatrix = getKeyMatrix(k);

        // compare each digraph with keyMatrix and build cipher text
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < digraphs.size(); i++) {
            String digraph = digraphs.get(i);
            int a = keyMatrix.indexOf(digraph.charAt(0));
            int b = keyMatrix.indexOf(digraph.charAt(1));
            char a1;
            char b1;

            // get row, col of a and b
            int row_a = a / 5, row_b = b / 5;
            int col_a = a % 5, col_b = b % 5;

            // check if two digraphs are in same row in keyMatrix
            if (row_a == row_b) {
                // get wrapped +1 element in row of keyMatrix
                if (mode == Mode.ENCRYPT) {
                    a1 = keyMatrix.get((row_a * 5 + (a + 1) % 5));
                    b1 = keyMatrix.get((row_a * 5 + (b + 1) % 5));
                } else {
                    a1 = keyMatrix.get((row_a * 5 + (a - 1) % 5));
                    b1 = keyMatrix.get((row_a * 5 + (b - 1) % 5));
                }
            }
            // check if two digraphs are in same column in keyMatrix
            else if (col_a == col_b) {
                // get wrapped +1 element in column of keyMatrix
                if (mode == Mode.ENCRYPT) {
                    a1 = keyMatrix.get((a + 5) % 25);
                    b1 = keyMatrix.get((b + 5) % 25);
                } else {
                    int a0 = (a - 5) % 25;
                    int b0 = (b - 5) % 25;
                    // if wrapped element is negative, add 25 to it
                    if (a0 < 0)
                        a0 += 25;
                    if (b0 < 0)
                        b0 += 25;

                    // get element in keyMatrix
                    a1 = keyMatrix.get(a0);
                    b1 = keyMatrix.get(b0);
                }
            }
            // check if two digraphs are in same diagonal in keyMatrix
            else {
                // get a1 and b1 from keyMatrix
                a1 = keyMatrix.get((row_a * 5 + col_b));
                b1 = keyMatrix.get((row_b * 5 + col_a));
            }

            // append a1 and b1 to output
            output.append(Character.toString(a1) + Character.toString(b1));
        }
        return output.toString();
    }
}