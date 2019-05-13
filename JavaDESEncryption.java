import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
 
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
 /* 
        AFTER RUNNING THE PROGRAM CHECK THE CONTENTS OF THE ENCRYPTED FILE AND DECRYPTED FILE 
        NAMELY encrypted.txt AND decrypted.txt TO SEE THE CHANGED CONTENTS ALSO THE CONTENTS OF 
        input.txt AND decrypted.txt ARE SUPPOSED TO BE SAME 
 */
public class JavaDESEncryption {
 
    private static Cipher encrypt;
    private static Cipher decrypt;
 
    private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };
 
    public static void main(String[] args) {
 
        String clearFile = "input.txt";
        String encryptedFile = "encrypted.txt";
        String decryptedFile = "decrypted.txt";
 
        try {
 
            SecretKey secret_key = KeyGenerator.getInstance("DES")
                    .generateKey();
            AlgorithmParameterSpec alogrithm_specs = new IvParameterSpec(
                    initialization_vector);
 
            // set encryption mode ...
            encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
            encrypt.init(Cipher.ENCRYPT_MODE, secret_key, alogrithm_specs);
 
            // set decryption mode
            decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decrypt.init(Cipher.DECRYPT_MODE, secret_key, alogrithm_specs);
 
            // encrypt file
            encrypt(new FileInputStream(clearFile), new FileOutputStream(
                    encryptedFile));
 
            // decrypt file
            decrypt(new FileInputStream(encryptedFile), new FileOutputStream(
                    decryptedFile));
            System.out.println("End of Encryption/Decryption procedure!");
 
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | InvalidAlgorithmParameterException
                | IOException e) {
            e.printStackTrace();
        }
 
    }
 
    private static void encrypt(InputStream input, OutputStream output)
            throws IOException {
 
        output = new CipherOutputStream(output, encrypt);
        writeBytes(input, output);
    }
 
    private static void decrypt(InputStream input, OutputStream output)
            throws IOException {
 
        input = new CipherInputStream(input, decrypt);
        writeBytes(input, output);
    }
 
    private static void writeBytes(InputStream input, OutputStream output)
            throws IOException {
        byte[] writeBuffer = new byte[512];
        int readBytes = 0;
 
        while ((readBytes = input.read(writeBuffer)) >= 0) {
            output.write(writeBuffer, 0, readBytes);
        }
 
        output.close();
        input.close();
    }
 
}