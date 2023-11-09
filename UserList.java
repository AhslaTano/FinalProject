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
    public void addUser(User user){
        users.add(user);
    }
     //TODO: fix so it returns as soon as a match comes up
    public User getUser(String username){
        User userReturn = null;
        for (User user : users) {
            if(username.equals(user.getUsername())){
                userReturn = user;
            }
        }
        return userReturn;
    }

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
            System.out.println(e.getStackTrace());
        }
       
        return users;
    }
}