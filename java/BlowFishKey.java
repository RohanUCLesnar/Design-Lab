
//Using Java Cryptography, encrypt the text 'Hello world' using BlowFish.
//Create your own key using Java keytool


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Scanner;
import java.util.Base64;

public class BlowFishKey {

    public static void main(String[] args) throws Exception {
// create a key generator based upon the Blowfish cipher
        KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
        keygenerator.init(128);
// create a key
        SecretKey secretkey = keygenerator.generateKey();
// create a cipher based upon Blowfish Cipher
        Cipher cipher = Cipher.getInstance("Blowfish");
// initialise cipher to with secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);
// get the text to encrypt
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your message:");
        String inputText = sc.nextLine(); // encrypt message
        byte[] encrypted = cipher.doFinal(inputText.getBytes());
// re-initialise the cipher to be in decrypt mode
        cipher.init(Cipher.DECRYPT_MODE, secretkey);
// decrypt message
        byte[] decrypted = cipher.doFinal(encrypted);
// and display the results
        System.out.println("\nEncrypted text: " + Base64.getEncoder().encodeToString(encrypted) + "\n"+ "\nDecrypted text: " + new String(decrypted));
                
        
    }
}
