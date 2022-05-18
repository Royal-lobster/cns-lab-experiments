package Week2;

/**
 * Hill Cipher algorithm that encrypts plaintext "PAY" and given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class PAY_HillCipher {

    public static void main(String[] args) {
        // Initialize this object
        PAY_HillCipher obj = new PAY_HillCipher();

        // Test variables
        double[][] p = { { 15 }, { 0 }, { 24 } };
        double[][] keyMatrix = { { 17, 17, 5 }, { 21, 18, 21 }, { 2, 2, 19 } };

        // Encrypt the plaintext
        double[][] c = obj.encrypt(p, keyMatrix);
        System.out.println("Encrypted text: " + obj.matrixToText(c));

        // Decrypt the cipher text
        p = obj.decrypt(c, keyMatrix);
        System.out.println("Decrypted text: " + obj.matrixToText(p));
    }

    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private double[][] encrypt(double[][] p, double[][] k) {
        return multiply(k, p);
    }

    private double[][] decrypt(double[][] c, double[][] k) {

        // inverse key matrix
        double[][] ik = inverse(k);

        // multiply cipher text matrix with key matrix
        return multiply(ik, c);
    }

    double[][] multiply(double[][] a, double[][] b) {
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

    String matrixToText(double[][] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                sb.append(alphabet.charAt((int) a[i][j] % 26));
            }
        }
        return sb.toString();
    }

    double[][] inverse(double[][] a) {
        double det = 0;
        double inv[][] = new double[3][3];

        // Find determinant of matrix a[][]
        for (int i = 0; i < 3; i++)
            det += a[0][i] * (a[1][(i + 1) % 3] * a[2][(i + 2) % 3]
                    - a[1][(i + 2) % 3] * a[2][(i + 1) % 3]);

        // Find inverse using adjoint method
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                inv[i][j] = ((a[(j + 1) % 3][(i + 1) % 3] *
                        a[(j + 2) % 3][(i + 2) % 3]) -
                        (a[(j + 1) % 3][(i + 2) % 3] *
                                a[(j + 2) % 3][(i + 1) % 3]))
                        / det;
        }
        return inv;
    }

}
