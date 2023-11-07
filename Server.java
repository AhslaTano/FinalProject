import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;


public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;
    //TODO: implement user save file
    private ArrayList<User> users = new ArrayList<User>();

    public Server(int port){
        try{
            //TODO: remove main loop from constructor
            server = new ServerSocket(port);
            System.out.println("Server started");
            
            System.out.println("Waiting for a client");
            socket = server.accept();

            System.out.println("Client found");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            //TODO: implement login function

            //TODO: implement main menu
            out.writeUTF("Welcome!");
            out.writeUTF(sendMenu(0));
            //TODO: view events function
            //TODO: Buy a ticket function
            //TODO: seat selecting from string
            //TODO:View purchased tickets function
            
            String line = "";
            while(!line.equals("quit")){
                try{
                    line = in.readUTF();
                    System.out.println("Client: " + line);
                    String response = "Echo - " + line;
                    out.writeUTF(response);
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

    /**
     * Returns a menu string
     * @param menuNumber Menu from array
     */
    public static String sendMenu(int menuNumber) {
        String[] menus = new String[1];
        menus[0] = "Main menu:\n1.View Events\n2.Buy a Ticket\n3.View Purchased Tickets\n4.Quit";
        return menus[menuNumber];
    }

    public static void main(String[] args){
        Server server = new Server(10000);    
    }
}
