import java.util.Scanner;

/**
 * Caesar Cipher algorithm that encrypts plaintext by given key
 * and also decrypts cipher text.
 *
 * @author Srujan Gurram
 * @version 1.0
 * @license MIT
 */

public class CaesarCipher {
    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public int findPos(char a){
        for(int i = 0; i<alphabet.length(); i++)
            if(alphabet.charAt(i) == a)
                return i;
        return -1;
    }

    private String crypt(String m, int k, int method) {
        StringBuilder output = new StringBuilder();
        m = m.toUpperCase();

        // loop over the plain text and add the cipher letter to sb
        for (int i = 0; i < m.length(); i++) {

            // find the pos of current charecter
            int correctPos = findPos(m.charAt(i));

            // find cipher pos of the charecter 
            // (left shift for decrypt and right shift for encrypt)
            int cipherPos;
            if(method == 0)
                cipherPos =  (correctPos + k) % 26;
            else{
                cipherPos =  (correctPos - k) % 26;
                // if cipherPos is negative, add 26 to it
                if(cipherPos < 0)
                    cipherPos += 26;
            }

            // append char of alphabet at cipherPos
            char cipherChar;
            if(correctPos == -1)
                cipherChar = m.charAt(i);
             else
                cipherChar = alphabet.charAt(cipherPos);
            
            output.append(cipherChar);
            
        }
        return output.toString();
    }
    
    public String encrypt(String p, int k){
        return crypt(p, k, 0);
    }

    public String decrypt(String c, int k) {
        return crypt(c, k, 1);
    }

    public static void main(String[] args) {
        
        // Initalize this object
        CaesarCipher obj = new CaesarCipher();

        // ------------------
        // ENCRYPTION PHASE
        // ------------------

        // Ask user to enter plain text
        Scanner input = new Scanner(System.in);
        System.out.print("Enter plain text to encrypt: ");
        String p = input.nextLine();

        // Ask user to enter key
        System.out.print("Enter key to encryption: ");
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
        System.out.print("Enter key for decryption: ");
        k = input.nextInt();
        input.nextLine();

        // Decrypt the cipher text with key and display the plain text
        p = obj.decrypt(c, k);
        System.out.println("Plain text " + p);

        // Close input
        input.close();
    }
}