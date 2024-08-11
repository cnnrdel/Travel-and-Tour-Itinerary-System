package client_keys;

public class StoreCredentials {
    public static void main(String[] args) {
        String clientId = "jTzpVWGWrF8o3ikzD37No3PNzYciwQFX";
        String clientSecret = "PyqprP1sgNl4njL6";

        try {
            EncryptionUtil.writeEncryptedDataToFile("src/client_id.txt", clientId);
            EncryptionUtil.writeEncryptedDataToFile("src/client_secret.txt", clientSecret);
            System.out.println("Credentials stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
