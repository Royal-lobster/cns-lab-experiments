package Week5;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class RC4 {
    private Cipher rc4;
    private SecretKey key;
    private final int KEY_SIZE = 128;

    RC4() throws Exception {
        rc4 = Cipher.getInstance("ARCFOUR");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("ARCFOUR");
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
    }

    public String encrypt(String p) throws Exception {

        // Convert String to byte array
        byte[] pInBytes = p.getBytes();

        // Initialize encryption cipher
        rc4.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt data
        byte[] encryptedBytes = rc4.doFinal(pInBytes);

        // Convert encrypted bytes to base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String c) throws Exception {

        // Decode base64 encoded cipher text
        byte[] cInBytes = Base64.getDecoder().decode(c);

        // Initialize decryption cipher (with Initialization Vector)
        rc4.init(Cipher.DECRYPT_MODE, key);

        // Decrypt data
        byte[] decryptedBytes = rc4.doFinal(cInBytes);

        // Convert decrypted bytes to string
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            RC4 obj = new RC4();
            String c = obj.encrypt("LOONATHEWORLD");
            String d = obj.decrypt(c);

            System.out.println("Encrypted Data : " + c);
            System.out.println("Decrypted Data : " + d);
        } catch (Exception ignored) {
        }
    }
}