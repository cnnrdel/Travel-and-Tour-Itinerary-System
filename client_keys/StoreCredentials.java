package client_keys;

public class StoreCredentials {
    public static void main(String[] args) {
        String clientId = "3510aec5";
        String clientSecret = "1e7f9a64a44f11db885af0c42dedc968";

        try {
            EncryptionUtil.writeEncryptedDataToFile("src/client_id2.txt", clientId);
            EncryptionUtil.writeEncryptedDataToFile("src/client_secret2.txt", clientSecret);
            System.out.println("Credentials stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
