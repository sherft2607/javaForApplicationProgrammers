import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Example code for lecture 8.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class SimpleClient {

    /**
     * Simple test program to read and write as a client over the network.
     * @param args arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket client = new Socket(args[0], 9898);
        
        OutputStream os = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF(args[1]);
        dos.flush();

        InputStream is = client.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = new String(dis.readUTF());
        System.out.println("Received from " + client.getInetAddress() + ": " + str);

        client.close();
    }

}
