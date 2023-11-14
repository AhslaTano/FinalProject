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
    }

    public User(String username, String password, String name, String emailAddress, UserList userList){
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
    public void spendPoints(int debit){
        points -= debit;
    }
    public void addPoints(int credit) { points += credit; }
    public boolean isLoggedIn(){ return loggedIn; }

    public String viewPurchasedTickets(){
        String returnString = "";
        for(Ticket ticket : purchased){
            returnString += ticket;
        }
        return returnString;
    }

    public String viewProfile(){
        return username + ": \n" + name + "\n" + emailAddress + "\nPoints available: " + points;
    }

    @Override
    public String toString(){
        return username + "," + password + "," + emailAddress + "," + name + "," + points + "," + purchased;
    }

    public boolean login(String password){
        loggedIn = password.equals(this.password);
        return loggedIn;
    }
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
