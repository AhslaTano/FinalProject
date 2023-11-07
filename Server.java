import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;


public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;
    private List<Users> users = new List<Users>();

    public Server(int port){
        try{
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client");
            socket = server.accept();

            System.out.println("Client found");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String line = "";
            while(!line.equals("quit")){
                try{
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch(IOException i){
                    System.out.println(i);
                }
            }
            socket.close();
            in.close();
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
    public static void main(String[] args){
        Server server = new Server(1000);    
    }
}
