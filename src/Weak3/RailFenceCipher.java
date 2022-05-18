package Weak3;

/**
 * RailFence Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class RailFenceCipher {
    public static void main(String[] args) {
        // Initialize this object
        RailFenceCipher obj = new RailFenceCipher();

        // Test variables
        String p = "LOONATHEWORLD";
        int k = 4;

        // Encrypt the plaintext
        String c = obj.encrypt(p, k);
        System.out.println("Encrypted text: " + c);

        // Decrypt the cipher text
        p = obj.decrypt(c, k);
        System.out.println("Decrypted text: " + p);

    }

    private String encrypt(String p, int k) {
        StringBuilder output = new StringBuilder();

        // create an array of dimensions r x c
        char[][] arr = new char[k][p.length()];

        // iterate over arr and fill it with letters of p
        // fill in letters diagonally with alternating directions
        rail(p, k, arr, null, RailType.DEFAULT);

        // iterate over arr and append to output
        for (int i = 0; i < k; i++)
            for (int j = 0; j < p.length(); j++)
                output.append(arr[i][j]);

        return output.toString();
    }

    private String decrypt(String c, int k) {
        StringBuilder output = new StringBuilder();
        // create an array of dimensions r x c
        char[][] arr = new char[k][c.length() / k];

        // iterate over arr and fill it with *s diagonally
        // fill in letters diagonally with alternating directions
        rail(c, k, arr, null, RailType.STAR);

        // replace *s with letters
        int index = 0;
        for (int i = 0; i < k; i++)
            for (int j = 0; j < c.length() / k; j++)
                arr[i][j] = c.charAt(index++);

        // iterate over arr and append to output string diagonally
        rail(c, k, arr, output, RailType.APPEND_STRING);

        // fill the array with the cipher text letters
        return output.toString();
    }

    enum RailType {
        DEFAULT, STAR, APPEND_STRING
    }

    private void rail(String m, int k, char[][] arr, StringBuilder out, RailType method) {
        boolean isDown = true;
        int row = 0;
        int col = 0;
        int iterTimes = 0;
        if (method == RailType.DEFAULT)
            iterTimes = m.length();
        else
            iterTimes = m.length() / k;

        for (int i = 0; i < iterTimes; i++) {
            // determine direction of filling
            if (row == 0)
                isDown = true;
            if (row == k - 1)
                isDown = false;

            // fill in the array or append to output string
            if (method == RailType.STAR)
                arr[row][col] = '*';
            else if (method == RailType.APPEND_STRING)
                out.append(arr[row][col]);
            else
                arr[row][col] = m.charAt(i);

            // increment row and col
            if (isDown)
                row++;
            else
                row--;

            col++;
        }

    }
}
