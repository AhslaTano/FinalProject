import java.util.*;
public class User {
    private String username;
    private String password;
    private int points;
    private List<Ticket> purchased;
    private String name;
    private String emailAddress;
    private boolean loggedIn;

    public User(){
        username = "";
        password = "";
        points = 0;
        purchased = null;
        name = "";
        emailAddress = "";
        loggedIn = false;
    }
    /**
     * Constructs a user object with credentials for them to interact with the site
     * @param username The username used to login
     * @param password The password used to login
     * @param name The full name of the User
     * @param emailAddress The email address of the user
     * Also sets point value to 30 and creates a List of Ticket objects to document the tickets they purchase
     */
    public User(String username, String password, String name, String emailAddress){
        this.username = username;
        this.password =  password;
        points = 30;
        purchased = new ArrayList<Ticket>();
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public String getUsername(){
        return username;
    }
    public void changeUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    public void changePassword(String password){
        this.password = password;
    }

    public int getPoints(){
        return points;
    }
    /**
     * Deducts points from the user when a ticket is purchased
     * @param debit The amount deducted, should be the ticket cost
     */
    public void spendPoints(int debit){
        points -= debit;
    }
    public void addPoints(int credit) { points += credit; }
    public boolean isLoggedIn(){ return loggedIn; }

    /**
     * @return A string of the ticket objects the user has purchased
     */
    public String viewPurchasedTickets(){
        String returnString = "";
        for(Ticket ticket : purchased){
            returnString += ticket + "\n";
        }
        return returnString;
    }
    /**
     * @return A string of the user's username, real name, email and points
     */
    public String viewProfile(){
        return username + ": \n" + name + "\n" + emailAddress + "\nPoints available: " + points;
    }

    @Override
    public String toString(){
        return username + "," + password + "," + emailAddress + "," + name + "," + points + "," + purchased;
    }
    /**
     * Called by the server for when the user enters their password. Checks if the password is correct
     * @param password The password entered by the user
     * @return True if the password matches the password connected to their user object or "Account"
     */
    public boolean login(String password){
        loggedIn = password.equals(this.password);
        return loggedIn;
    }
    /**
     * A method called when a ticket sale occurs
     * @param seat the seat that the user bought
     * @return a Boolean, true
     */
    public boolean purchaseTicket(Seat seat){
        if(points < seat.getPrice()){
            return false;
        }
        spendPoints(seat.getPrice());
        seat.setTicketOwner(this);
        purchased.add(seat.getTicket());
        return true;
    }
}
