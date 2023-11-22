import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private Scanner userInput;

    /*
     try{
            socket = serverSocket.accept();
            System.out.println("Client found");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            startApplication();

            while(!user.isLoggedIn()) {
                loginMenu();
            }
            mainMenu();

            handleClientInteraction();
            socket.close();
            in.close();
        }
            catch(IOException i){
            System.out.println(i.getMessage());
        }
    }

     */

    public Client(String address, int port){
        try{
            // TODO: remove main loop from constructor
            socket = new Socket(address, port);
            System.out.println("Connected");
            input = new DataInputStream(socket.getInputStream());//updated from system.in to communicate with server
            out = new DataOutputStream(socket.getOutputStream());
            userInput = new Scanner(System.in);
        }
        catch(UnknownHostException u){
            System.out.println(u);
            return;
        }
        catch(IOException i){
            System.out.println(i);
            return;
        }

        String clientLine = "";
        String serverLine = "";
        while(!clientLine.equals("quit")){
            try{
                //then print output
                serverLine = input.readUTF();
                System.out.println("Server: " + serverLine);
                //first send input
                clientLine = userInput.nextLine();
                out.writeUTF(clientLine);

            }
            catch(IOException i){
                System.out.println(i);
            }

        }
        try{
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i){
            System.out.println(i);
        }
    }


    public static void main(String[] args){
        Client client = new Client("11.22.67.253", 5000);   
    }
}
