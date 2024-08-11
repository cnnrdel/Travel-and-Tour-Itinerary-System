package client_keys;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class EncryptionUtil {
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "1234567890123456".getBytes(); // 16-byte key for AES

    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    public static void writeEncryptedDataToFile(String filePath, String data) throws Exception {
        String encryptedData = encrypt(data);
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(encryptedData.getBytes());
        }
    }

    public static String readEncryptedDataFromFile(String filePath) throws Exception {
        byte[] encryptedData;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            encryptedData = fis.readAllBytes();
        }
        return decrypt(new String(encryptedData));
    }
}
