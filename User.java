import java.util.*;
public class User {
    private String username;
    private String password;
    private int points;
    private List<Ticket> purchased;
    private String name;
    private String emailAddress;
    private UserList userList;

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
        userList.addUser(this);
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

    public void viewPurchasedTickets(){
        for(Ticket ticket : purchased){
            System.out.println(ticket.toString());
        }
    }

    public void spendPoints(Seat ticket){
        points -= ticket.getPrice();
    }
    // public boolean purchaseTicket(Seat seat){
    //     boolean result = false;
    //     if (seat.getAvailability()) {
    //         if (owner.) {
                
    //         }
    //         setOwner(owner);
    //     }
    // }

    public String viewProfile(){
        return username + ": \n" + name + "\n" + emailAddress + "\nPoints available: " + points;
    }

    public String userString(){
        return username + "," + password + "," + emailAddress + "," + name + "," + points + "," + purchased;
    }
}
