package Weak5;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;

public class AES {
    private Cipher aes = Cipher.getInstance("AES/GCM/NoPadding");
    private SecretKey key;
    private final int KEY_SIZE = 128;
    private final int DATA_LENGTH = 128;

    AES() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
    }

    public String encrypt(String p) throws Exception {

        // Convert String to byte array
        byte[] pInBytes = p.getBytes();

        // Initialize encryption cipher
        aes.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt data
        byte[] encryptedBytes = aes.doFinal(pInBytes);

        // Convert encrypted bytes to base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String c) throws Exception {

        // Decode base64 encoded cipher text
        byte[] cInBytes = Base64.getDecoder().decode(c);

        // Initialize decryption cipher (with Initialization Vector)
        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, aes.getIV());
        aes.init(Cipher.DECRYPT_MODE, key, spec);

        // Decrypt data
        byte[] decryptedBytes = aes.doFinal(cInBytes);

        // Convert decrypted bytes to string
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            AES obj = new AES();
            String c = obj.encrypt("Hello This is Secret Message");
            String d = obj.decrypt(c);

            System.out.println("Encrypted Data : " + c);
            System.out.println("Decrypted Data : " + d);
        } catch (Exception ignored) {
        }
    }
}