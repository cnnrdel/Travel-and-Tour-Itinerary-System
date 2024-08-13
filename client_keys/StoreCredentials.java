package client_keys;

public class StoreCredentials {
    public static void main(String[] args) {
        String clientId ="";
        String clientSecret ="";

        try {
            EncryptionUtil.writeEncryptedDataToFile("src/client_id2.txt", clientId);
            EncryptionUtil.writeEncryptedDataToFile("src/client_secret2.txt", clientSecret);
            System.out.println("Credentials stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
