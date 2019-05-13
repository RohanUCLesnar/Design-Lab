
import java.util.Scanner;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.util.Base64;

public class BlowFish {

    public static void main(String[] args) throws Exception {
// TODO code application logic here
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        Cipher cipherOut = Cipher.getInstance("Blowfish");
        cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);
        byte iv[] = cipherOut.getIV();
        if (iv != null) {
            System.out.println("Initialization Vector of the Cipher: " + Base64.getEncoder().encodeToString(iv));
        }
        Scanner sc= new Scanner(System.in);
        System.out.println("Input your message:");
        String inputText = sc.nextLine(); // encrypt message
        byte[] encrypted = cipherOut.doFinal(inputText.getBytes());
        cipherOut.init(Cipher.DECRYPT_MODE, secretKey);
// decrypt message
        byte[] decrypted = cipherOut.doFinal(encrypted);
// and display the results
        System.out.println("\nEncrypted text: " + Base64.getEncoder().encodeToString(encrypted) + "\n"+ "\nDecrypted text: " + new String(decrypted));
                
        
        
    }
}
