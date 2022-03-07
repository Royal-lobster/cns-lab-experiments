/**
 * Hill Cipher algorithm that encrypts plaintext "PAY" and given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class PAY_HillCipher {
    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    double[][] matrixMultiply(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    String matrixToString(double[][] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                sb.append(alphabet.charAt((int) a[i][j] % 26));
            }
        }
        return sb.toString();
    }

    double[][] inverseMatrix(double[][] a) {
        double det = 0;
        double inv[][] = new double[3][3];

        // Find determinant of matrix a[][]
        for (int i = 0; i < 3; i++)
            det = det + (a[0][i]
                    * (a[1][(i + 1) % 3] * a[2][(i + 2) % 3] - a[1][(i + 2) % 3] * a[2][(i + 1) % 3]));

        // Find inverse using adjoint method
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                inv[i][j] = ((a[(j + 1) % 3][(i + 1) % 3] * a[(j + 2) % 3][(i + 2) % 3])
                        - (a[(j + 1) % 3][(i + 2) % 3] * a[(j + 2) % 3][(i + 1) % 3])) / det;
        }
        return inv;
    }

    // main function of program
    public static void main(String[] args) {
        // create a new PAY_HillCipher object
        PAY_HillCipher obj = new PAY_HillCipher();

        // given key in the question (3x3 matrix)
        double[][] keyMatrix = { { 17, 17, 5 }, { 21, 18, 21 }, { 2, 2, 19 } };

        // given plaintext in the question
        String plainText = "PAY";
        System.out.println("Plaintext: " + plainText);

        // ------------------
        // ENCRYPTION PHASE
        // ------------------

        // convert plaintext to (3x1) matrix for MM
        double[][] plainTextMatrix = { { 15 }, { 0 }, { 24 } };

        // perform MM on keyMatrix and plainTextMatrix
        double[][] cipherTextMatrix = obj.matrixMultiply(keyMatrix, plainTextMatrix);

        // convert cipherTextMatrix to plain cipher text
        String cipherText = obj.matrixToString(cipherTextMatrix);
        System.out.println("Cipher Text: " + cipherText);

        // ------------------
        // DECRYPTION PHASE
        // ------------------

        // inverse the keyMatrix for decryption
        double[][] inverseKeyMatrix = obj.inverseMatrix(keyMatrix);

        // perform matrix multiplication on inverseKeyMatrix and cipherTextMatrix
        double[][] decryptedTextMatrix = obj.matrixMultiply(inverseKeyMatrix, cipherTextMatrix);

        // convert decryptedTextMatrix to plain text
        String decryptedText = obj.matrixToString(decryptedTextMatrix);
        System.out.println("Decrypted Text: " + decryptedText);

        // ------------------
        // TESTING PHASE
        // ------------------

        if (decryptedText.equals(plainText)) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test failed!");
        }

    }
}
