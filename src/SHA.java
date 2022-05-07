import java.util.Scanner;

/**
 * SHA 1 hash function algorithm.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class SHA {

    public static void main(String[] args) {

        // Initialize this object
        SHA obj = new SHA();

        // Ask user to enter message
        Scanner input = new Scanner(System.in);
        System.out.print("Enter message to hash: ");
        String m = input.nextLine();

        // Hash the message and print output
        String h = obj.hash(m);
        System.out.println("Hashed message: " + h);
    }

    private String hash(String m) {
        return null;
    }

}