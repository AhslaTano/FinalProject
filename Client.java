import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private Socket socket = null;
    private DataInputStream serverInput;
    private DataInputStream userInput;
    private DataOutputStream out;

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
            System.out.println("Looking for server");
            socket = new Socket(address, port);
            System.out.println("Connected");
            
            serverInput = new DataInputStream(socket.getInputStream());//updated from system.in to communicate with server
            userInput = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u){
            System.out.println(u);
            return;
        }
        catch(IOException i){
            System.out.println(i);
            return;
        }

        String serverLine = "";
        String inputLine = "";
        while(!serverLine.equals("quit")){
            try{
                //loops reading the lines from the server until user input is ready
                while(serverInput.available() > 0){
                    serverLine = serverInput.readUTF();
                    System.out.println("Server: " + serverLine);
                }
                //user input
                inputLine = userInput.readLine();
                out.writeUTF(inputLine);

            }
            catch(IOException i){
                System.out.println(i);
            }

        }
        try{
            serverInput.close();
            userInput.close();
            out.close();
            socket.close();
        }
        catch(IOException i){
            System.out.println(i);
        }
    }


    public static void main(String[] args){
        Client client = new Client("11.21.8.67", 5000);   
    }
}
