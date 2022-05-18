package Week6;

import javax.crypto.Cipher;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSA {
    private Cipher rsa;
    PrivateKey privateKey;
    PublicKey publicKey;

    private final int KEY_SIZE = 512;

    RSA() throws Exception {
        rsa = Cipher.getInstance("RSA");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair key = keyPairGenerator.generateKeyPair();
        privateKey = key.getPrivate();
        publicKey = key.getPublic();
    }

    public String encrypt(String p) throws Exception {

        // Convert String to byte array
        byte[] pInBytes = p.getBytes();

        // Encrypt data
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = rsa.doFinal(pInBytes);

        // Convert encrypted bytes to base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String c) throws Exception {

        // Decode base64 encoded cipher text
        byte[] cInBytes = Base64.getDecoder().decode(c);

        // Decrypt data
        rsa.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = rsa.doFinal(cInBytes);

        // Convert decrypted bytes to string
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            RSA obj = new RSA();
            String c = obj.encrypt("LOONATHEWORLD");
            String d = obj.decrypt(c);

            System.out.println("Encrypted Data : " + c);
            System.out.println("Decrypted Data : " + d);
        } catch (Exception ignored) {
        }
    }
}