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
            boolean login = false;
            while(!login){
                try{
                    System.out.print("Login (Username Password): ");
                    line = in.readUTF();
                    for(int i = 0; i < users.length; i++){
                        if(users.ElementAt(i).getLogin().equals(line)){
                            login = true;
                    }else
                        System.out.println("Wrong login, try again");
                    }
                }
                catch(IOException i){
                    System.out.println(i);
                }
            //Runtime input for the website
            while(!line.equals("quit")){
                try{
                    System.out.println("Enter a number for your option.\nMenu: " + sendMenu(0));
                    line = in.readUTF();
                    switch(line){
                        case "1":
                            break;
                        case "2":
                            break;
                        case "3":
                            break;
                        case "4":
                            break;
                        default:
                            System.out.println("Error, Unkown command");
                    }
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
