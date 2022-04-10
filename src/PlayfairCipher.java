import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Playfair Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class PlayfairCipher {
    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public int findPos(char a) {
        for (int i = 0; i < alphabet.length(); i++)
            if (alphabet.charAt(i) == a)
                return i;
        return -1;
    }

    public List<String> digraphs(String m) {
        // convert string m to List of characters
        List<Character> mList = new ArrayList<Character>();
        for (int i = 0; i < m.length(); i++) {
            mList.add(m.charAt(i));
        }

        // create a 2D array of characters
        List<String> digraphs = new ArrayList<String>();

        // append each digraph to digraphs array
        while (mList.size() != 1 && mList.size() != 0) {
            char a = mList.get(0);
            char b = mList.get(1);
            if (a != b) {
                // remove 0 and 1 indices from mList
                mList.remove(0);
                mList.remove(0);

                // append digraph to digraphs array
                digraphs.add(Character.toString(a) + Character.toString(b));
            } else {
                // remove 0 index from mList
                mList.remove(0);

                // append digraph to digraphs array
                digraphs.add(Character.toString(a) + Character.toString('X'));
            }
        }

        // if there is a last character, append it to digraphs array
        if (mList.size() == 1) {
            digraphs.add(Character.toString(mList.get(0)) + Character.toString('X'));
        }
        return digraphs;
    }

    private List<Character> getKeyMatrix(String k) {
        // create a 2D list of characters
        List<Character> keyMatrix = new ArrayList<Character>();

        // append each character of k to keyMatrix
        for (int i = 0; i < k.length(); i++) {
            // if character is not already in keyMatrix, append it
            if (!keyMatrix.contains(k.charAt(i))) {
                keyMatrix.add(k.charAt(i));
            }
        }

        // if size of keyMatrix is less than 5, append rest of alphabet to it
        if (keyMatrix.size() < 25) {
            for (int i = 0; i < alphabet.length(); i++) {
                if (!keyMatrix.contains(alphabet.charAt(i)) && alphabet.charAt(i) != 'J') {
                    keyMatrix.add(alphabet.charAt(i));
                    // if size of keyMatrix is 5, break
                    if (keyMatrix.size() == 25) {
                        break;
                    }
                }
            }
        }

        return keyMatrix;
    }

    public String crypt(String m, String k, Boolean isToDecrypt) {
        // convert p and k to upper case
        m = m.toUpperCase();
        k = k.toUpperCase();

        // convert string p to digraphs
        List<String> digraphs = digraphs(m);
        System.out.println("digraphs: " + digraphs);

        // get the key matrix
        List<Character> keyMatrix = getKeyMatrix(k);
        System.out.println("keyMatrix: ");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(keyMatrix.get(i * 5 + j) + " ");
            }
            System.out.println();
        }

        // compare each digraph with keyMatrix and build cipher text
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < digraphs.size(); i++) {
            String digraph = digraphs.get(i);
            int a = keyMatrix.indexOf(digraph.charAt(0));
            int b = keyMatrix.indexOf(digraph.charAt(1));
            char a1;
            char b1;
            System.out.println("\ndigraph: " + digraph + " => a: " + a + " b: " + b);

            // check if two digraphs are in same row in keyMatrix
            if (a / 5 == b / 5) {
                System.out.println("Row Same");
                // get which row the digraphs are in
                int r = a / 5;

                // get wrapped +1 element in row of keyMatrix
                if (!isToDecrypt) {
                    a1 = keyMatrix.get((r * 5 + (a + 1) % 5));
                    b1 = keyMatrix.get((r * 5 + (b + 1) % 5));
                } else {
                    a1 = keyMatrix.get((r * 5 + (a - 1) % 5));
                    b1 = keyMatrix.get((r * 5 + (b - 1) % 5));
                }

            }
            // check if two digraphs are in same column in keyMatrix
            else if (a % 5 == b % 5) {
                System.out.println("Column same");
                // get wrapped +1 element in column of keyMatrix
                if (!isToDecrypt) {
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
                System.out.println("Diagonal same");
                // get row, col of a
                int row_a = a / 5;
                int col_a = a % 5;
                // get row, col of b
                int row_b = b / 5;
                int col_b = b % 5;
                // get a1 and b1 from keyMatrix
                a1 = keyMatrix.get((row_a * 5 + col_b));
                b1 = keyMatrix.get((row_b * 5 + col_a));
            }

            // append a1 and b1 to output
            output.append(Character.toString(a1) + Character.toString(b1));
            System.out.println("a1: " + a1 + " b1: " + b1);
        }
        return output.toString();
    }

    private String decrypt(String c, String k) {
        return crypt(c, k, true);
    }

    private String encrypt(String p, String k) {
        return crypt(p, k, false);
    }

    public static void main(String[] args) {

        // Initialize this object
        PlayfairCipher obj = new PlayfairCipher();

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
        System.out.println("Plain text " + p);

        // Close input
        input.close();
    }
}