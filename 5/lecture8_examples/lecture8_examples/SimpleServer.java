import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Example code for lecture 8.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class SimpleServer {

    /**
     * Simple test program to read and write as a server over the network.
     * @param args arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9898);
        System.out.println("Server is up and listening...");
        System.out.println();

        Socket client = ss.accept();
        System.out.println("Connection from " + client.getInetAddress());

        // InputStream to read bytes
        InputStream is = client.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = new String(dis.readUTF());
        System.out.println("   Received: " + str);

        // OutputStream to write bytes
        OutputStream os = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("Thank you: " + client.getInetAddress());

        System.out.println();
        System.out.println("Good bye!!! Server is down.");

        // don't forget to close resources
        dos.close();
        dis.close();
        client.close();
        ss.close();
    }

}
