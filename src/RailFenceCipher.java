import java.util.Scanner;

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

        // ------------------
        // ENCRYPTION PHASE
        // ------------------

        // Ask user to enter plain text
        Scanner input = new Scanner(System.in);
        System.out.print("Enter plain text to encrypt: ");
        String p = input.nextLine();

        // Ask user to enter key
        System.out.print("Enter Depth of encryption: ");
        int k = input.nextInt();
        input.nextLine();

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
        System.out.print("Enter Depth of decryption: ");
        k = input.nextInt();
        input.nextLine();

        // Decrypt the cipher text with key and display the plain text
        p = obj.decrypt(c, k);
        System.out.println("Plain text: " + p);

        // Close input
        input.close();
    }

    private String encrypt(String p, int k) {
        StringBuilder output = new StringBuilder();

        // create an array of dimensions r x c
        char[][] arr = new char[k][p.length()];

        // iterate over arr and fill it with letters of p
        // fill in letters diagonally with alternating directions
        rail(p, k, arr, null, false, false);

        // print the array
        printTable(arr, k, p.length());

        // iterate over arr and append to output
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < p.length(); j++) {
                output.append(arr[i][j]);
            }
        }
        return output.toString();
    }

    private String decrypt(String c, int k) {
        StringBuilder output = new StringBuilder();
        // create an array of dimensions r x c
        char[][] arr = new char[k][c.length()];

        // iterate over arr and fill it with *s diagonally
        // fill in letters diagonally with alternating directions
        rail(c, k, arr, null, true, false);

        // print the array
        printTable(arr, k, c.length());

        // now replace *s with letters from c
        int index = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < c.length(); j++) {
                if (arr[i][j] == '*') {
                    arr[i][j] = c.charAt(index);
                    index++;
                }
            }
        }

        // print the array
        printTable(arr, k, c.length());

        // iterate over arr and append to output string diagonally
        rail(c, k, arr, output, false, true);

        // fill the array with the cipher text letters
        return output.toString();
    }

    private void rail(String m, int k, char[][] arr, StringBuilder out, boolean isToStar, boolean isToStringBuild) {
        boolean isDown = true;
        int row = 0;
        int col = 0;
        for (int i = 0; i < m.length(); i++) {
            // determine direction of filling
            if (row == 0)
                isDown = true;
            if (row == k - 1)
                isDown = false;

            // fill in the array or append to output string
            if (isToStar) {
                arr[row][col] = '*';
            } else if (isToStringBuild) {
                out.append(arr[row][col]);
            } else {
                arr[row][col] = m.charAt(i);
            }

            // increment row and col
            if (isDown) {
                row++;
            } else {
                row--;
            }
            col++;
        }
    }

    private void printTable(char[][] arr, int rows, int cols) {
        // print array in table
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == '\u0000') {
                    System.out.print(" ");
                } else {
                    System.out.print(arr[i][j]);
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
