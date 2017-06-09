import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
/**
 * Created by daniellindeman on 9/10/15.
 */
public class Cipher {

    /**
     * Constructs a cipher object with a given key.
     *
     * @param aKey the encryption key
     */
    public Cipher(int aKey) {
        key = aKey;
    }

    /**
     * Encrypts the contents of a stream.
     *
     * @param in  the input stream
     * @param out the output stream
     */
    public void encryptStream(InputStream in, OutputStream out)
            throws IOException {
        boolean done = false;
        while (!done) {
            int next = in.read();
            if (next == -1) done = true;
            else {
                char b = (char) next;
                char c = encrypt(b);
                out.write(c);
            }
        }
    }

    /**
     Encrypts a char.
     @param c the char to encrypt
     @return the encrypted char
     */
    public char encrypt(char c)
    {
        if ( key < 0 )
            key = key + 26;

        if ( Character.isUpperCase(c) )
            return (char)((c - 'A' + key) % 26 + 'A');

        if ( Character.isLowerCase(c) )
            return (char)((c - 'a' + key) % 26 + 'a');

        return c;
    }
    private int key;
}
