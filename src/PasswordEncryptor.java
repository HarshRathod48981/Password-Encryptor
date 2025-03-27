import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

    public static String hashPassword(String password) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            return bytesToHex(hashedBytes);
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: SHA-256 algorithm not found.");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b); // Convert byte to hex
            if (hex.length() == 1) hexString.append('0'); // Ensure two-digit format
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your password : ");
        String password = scanner.nextLine();

        String hashedPassword = hashPassword(password);
        System.out.println("encrypted Password: " +hashedPassword);

        scanner.close();
    }
}