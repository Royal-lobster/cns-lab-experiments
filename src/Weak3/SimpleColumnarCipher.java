package Weak3;

/**
 * SimpleColumnar Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class SimpleColumnarCipher {
    public static void main(String[] args) {
        // Initialize this object
        SimpleColumnarCipher obj = new SimpleColumnarCipher();

        // Test variables
        String p = "LOONATHEWORLD";
        String k = "1432";
        int row = 4, col = 4;

        // Encrypt the plaintext
        String c = obj.encrypt(p, row, col, k);
        System.out.println("Encrypted text: " + c);

        // Decrypt the cipher text
        p = obj.decrypt(c, row, col, k);
        System.out.println("Decrypted text: " + p);
    }

    private String encrypt(String p, int row, int col, String k) {
        StringBuilder output = new StringBuilder();
        // create a 2D array of characters of size row x col`
        char[][] rect = new char[row][col];

        // fill the 2D array with characters from plain text
        int i = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (i < p.length()) {
                    rect[r][c] = p.charAt(i);
                    i++;
                } else {
                    rect[r][c] = '@';
                }
            }
        }

        // encrypt the 2D array
        // each digit in key is used to find which column to append first to output
        for (int kIndex = 1; kIndex <= k.length(); kIndex++) {
            int key = k.indexOf(String.valueOf(kIndex));
            for (int r = 0; r < row; r++) {
                output.append(rect[r][key]);
            }
        }

        return output.toString();
    }

    private String decrypt(String c, int row, int col, String k) {
        StringBuilder output = new StringBuilder();

        // create a 2D array of characters of size row x col
        char[][] rect = new char[row][col];

        // split the cipher text into n substrings of length row
        String[] cipherCols = new String[col];
        for (int i = 0; i < col; i++) {
            int start = i * row;
            int end = start + row;
            cipherCols[i] = c.substring(start, end);
        }

        // fill the 2D array with characters from cipher text
        // with each digit in key used to find which column to append first to output
        // use substring to get the blocks of characters from cipher text
        for (int i = 0; i < k.length(); i++) {
            int key = Integer.parseInt(k.substring(i, i + 1)) - 1;
            for (int r = 0; r < row; r++) {
                rect[r][i] = cipherCols[key].charAt(r);
            }
        }

        // append the characters from 2D array to output row by row
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                output.append(rect[i][j]);
            }
        }

        return output.toString();
    }
}
