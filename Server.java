import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Server {
    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private UserList users;
    private EventsList events;
    private User user;

    //See https://stackoverflow.com/questions/72753113/is-it-ok-to-put-the-main-loop-of-a-program-in-the-constructor-of-a-class
    /**
     * Constructs server
     * @param port Connection port to use
     */
    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            users = UserList.loadUserList();
            events = EventsList.loadEventList(users);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public void Run(){
        System.out.println("Server started");
        System.out.println("Waiting for a client");
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
            //TODO: view events function
            //TODO: seat selecting from string
            //TODO:View purchased tickets function

            handleClientInteraction();
            socket.close();
            in.close();
        }
            catch(IOException i){
            System.out.println(i.getMessage());
        }
    }

    private void startApplication(){
        users = UserList.loadUserList();
        events = EventsList.loadEventList(users);
    }

    private void handleClientInteraction(){
        String line = "";
        while(!line.equals("quit")){
            try{
                line = in.readUTF();
                System.out.println("Client: " + line);
                String response = "Echo - " + line;
                out.writeUTF(response);
            }
            catch(IOException i){
                System.out.println(i.getMessage());
            }
        }
    }

    public boolean userLogin(){
        boolean success = false;
        while(!success){
            try{
                out.writeUTF("Enter username: ");
                String username = in.readUTF();
                out.writeUTF("Enter password: ");
                String password = in.readUTF();
                success = users.login(username, password);
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
    return success;
    }

    /**
     * Creates an account by interacting with client for input
     * @return success of operation
     */
    public boolean createAccount(){
        boolean success = false;
        try {
            out.writeUTF("Creating an account...");
            out.writeUTF("Username: ");
            String username = in.readUTF();

            String password = "";
            boolean passwordConfirmed = false;
            while(!passwordConfirmed){
                try {
                    out.writeUTF("Password: ");
                    password = in.readUTF();
                    out.writeUTF("Confirm password: ");
                    String password2 = in.readUTF();
                    if (password.equals(password2)){
                        passwordConfirmed = true;
                    }
                }
                catch (IOException e){
                    System.err.println(e.getMessage());
                }
            }
            out.writeUTF("Email address: ");
            String email = in.readUTF();
            out.writeUTF("Full name: ");
            String name = in.readUTF();

            user = new User(username, password, name, email, users);
            success = users.addUser(user);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
        return success;
    }

    public void mainMenu(){
        //"
        try {
            out.writeUTF("Main menu:\n1.View Events\n2.Buy a Ticket\n3.View Purchased Tickets\n4.Add Points\n5.Account Options\n6.Quit");
            int userInput = in.readInt();
            while(userInput != 6)
            {
                switch (userInput){
                    case 1:
                            out.writeUTF("Upcoming Events: ");
                            events.viewEvents();
                            break;
                    case 2:
                            out.writeUTF("Buy a Ticket:");
                            //TODO: Ticket buying
                            break;
                    case 3:
                            out.writeUTF("Your purchased tickets: ");
                            out.writeUTF(user.viewPurchasedTickets());
                            break;
                    case 4:
                            out.writeUTF("Your current points balance: " + user.getPoints());
                            out.writeUTF("Enter points to add: ");
                            int pointsToAdd = in.readInt();
                            user.addPoints(pointsToAdd);
                            //no limits right now. capitalism
                            break;
                    case 5:
                            out.writeUTF("Account Options");
                            accountMenu();
                            break;
                    default:
                            out.writeUTF("Invalid input. Please try again.");
                            break;
                }
                userInput = in.readInt();
            }
            out.writeUTF("Thank you for using Ticket Seller!");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void purchaseTicketMenu(){
        try {
            out.writeUTF("Upcoming events: \n" + events.viewEvents());
            out.writeUTF("Which event will you purchase tickets for?");

            int userInput = in.readInt();
            Event event = null;
            try {
                //Array indexing accounted for in getEvent()
                event = events.getEvent(userInput);
            }catch(ArrayIndexOutOfBoundsException e) {
                out.writeUTF("Event not found! Please try again."); // maybe wrong
            }
            if (event!= null){
                event.seats.printMap();
                out.writeUTF("Which seat would you like to purchase a ticket for?");
                out.writeUTF("Please enter its seat number: ");
                String seatNumber = in.readUTF();
                Seat seat = event.seats.getSeatFromHumanNumber(seatNumber);
                if (seat != null) {
                    out.writeUTF("Ticket for seat " + seat.getSeatNumber() + " costs " + seat.getPrice() + " points. ");
                    out.writeUTF("Purchase ticket? (Y/N): ");
                    String userYNChoice = "";
                    userYNChoice = in.readUTF();
                    while (!(userYNChoice.equalsIgnoreCase("Y") || userYNChoice.equalsIgnoreCase("N"))){
                        out.writeUTF("Please enter either 'Y' or 'N'!");
                        out.writeUTF("Purchase ticket? (Y/N): ");
                        userYNChoice = in.readUTF();
                    }
                    if(userYNChoice.equalsIgnoreCase("Y")){
                        // TODO: implement user.purchaseTicket
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Helper function - runs login menu, calls necessary methods
     */
    public void loginMenu(){
        try{
            out.writeUTF("Login menu:\n1.Create an Account\n2.Log In\n3.Quit");
            // TODO: login menu
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Helper function - runs account submenu
     */
    public void accountMenu(){
        try{
            out.writeUTF("Account menu:\n1.Change Email\n2.Change Password\n3.Change Name");

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        Server server = new Server(5000);
        server.Run();
    }
}
