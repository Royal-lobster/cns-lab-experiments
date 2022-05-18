package Weak4;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class DES {
    private Cipher des;
    private SecretKey key;
    private final int KEY_SIZE = 56;

    DES() throws Exception {
        des = Cipher.getInstance("DES");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
    }

    public String encrypt(String p) throws Exception {

        // Convert String to byte array
        byte[] pInBytes = p.getBytes();

        // Initialize encryption cipher
        des.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt data
        byte[] encryptedBytes = des.doFinal(pInBytes);

        // Convert encrypted bytes to base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String c) throws Exception {

        // Decode base64 encoded cipher text
        byte[] cInBytes = Base64.getDecoder().decode(c);

        // Initialize decryption cipher (with Initialization Vector)
        des.init(Cipher.DECRYPT_MODE, key);

        // Decrypt data
        byte[] decryptedBytes = des.doFinal(cInBytes);

        // Convert decrypted bytes to string
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            DES obj = new DES();
            String c = obj.encrypt("Hello This is Secret Message");
            String d = obj.decrypt(c);

            System.out.println("Encrypted Data : " + c);
            System.out.println("Decrypted Data : " + d);
        } catch (Exception ignored) {
        }
    }
}