import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    
    private ArrayList<User> users;

    public UserList(){
        users = new ArrayList<User>();
    }

    /**
     * Adds user if username not taken
     * @param user The new user
     * @return Bool indicating success 
     */
    public boolean addUser(User user){
        boolean usernameAvailable = true;
        boolean result = false;
        for (User u : users) {
            if (u.getUsername().toUpperCase().equals(user.getUsername().toUpperCase())) {
                usernameAvailable = false;
                break;
            }
        }
        if (usernameAvailable) {
            users.add(user);
            result = true;
        }
        return result;
    }
    
    /**
     * Finds user based on username
     * @param username String unique to each user
     * @return User corresponding to username, null if no user
     */
    public User getUser(String username){
        User userReturn = null;
        for (User user : users) {
            if(username.equals(user.getUsername())){
                userReturn = user;
                return userReturn;
            }
        }
        return userReturn;
    }

    /**
     * Writes users to a file
     */
    public void saveUsersList(){
        try {
            File file = new File("users.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        
            for (User user : users) {
                writer.append(user.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: User list file not found!");
        }
    }

    @Override
    public String toString(){
        String result = "";
        for (User user : users) {
            result += user.toString();
        }
        return result;
    }
    
    /**
     * Loads users from save file
     * Note: vulnerability is here
     * @return UserList with saved users
     */
    public static UserList loadUserList(){
        UserList users = new UserList();
        try {
             File file = new File("users.txt");
            if(file.length() != 0){
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNextLine()){
                    String userLine = fileScan.nextLine();
                    //Uh Oh...............
                    String[] userStrings = userLine.split(",");
                    users.addUser(new User(userStrings[0], userStrings[1], userStrings[3], userStrings[2], users));
                }
            fileScan.close();
            }  
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
       
        return users;
    }

    /**
     * Log in
     * @param username String, unique username
     * @param password String, password to try
     * @return Success of operation
     */
    public boolean login(String username, String password){
        boolean result = false;
        User user = getUser(username);
        user.login(password);
        if (user.isLoggedIn()){
            result = true;
        }
        return result;
    }


}