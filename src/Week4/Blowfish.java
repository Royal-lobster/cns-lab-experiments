package Week4;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Blowfish {
    private Cipher blowfish;
    private SecretKey key;
    private final int KEY_SIZE = 56;

    Blowfish() throws Exception {
        blowfish = Cipher.getInstance("Blowfish");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
    }

    public String encrypt(String p) throws Exception {

        // Convert String to byte array
        byte[] pInBytes = p.getBytes();

        // Initialize encryption cipher
        blowfish.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt data
        byte[] encryptedBytes = blowfish.doFinal(pInBytes);

        // Convert encrypted bytes to base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String c) throws Exception {

        // Decode base64 encoded cipher text
        byte[] cInBytes = Base64.getDecoder().decode(c);

        // Initialize decryption cipher (with Initialization Vector)
        blowfish.init(Cipher.DECRYPT_MODE, key);

        // Decrypt data
        byte[] decryptedBytes = blowfish.doFinal(cInBytes);

        // Convert decrypted bytes to string
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            Blowfish obj = new Blowfish();
            String c = obj.encrypt("LOONATHEWORLD");
            String d = obj.decrypt(c);

            System.out.println("Encrypted Data : " + c);
            System.out.println("Decrypted Data : " + d);
        } catch (Exception ignored) {
        }
    }
}