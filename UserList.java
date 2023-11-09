import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserList {
    
    private ArrayList<User> users = new ArrayList<User>();

    public UserList(){

    }
    public void addUser(User user){
        users.add(user);
    }

    public void saveUsersList() throws IOException{
        File file = new File("users.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        
        for (User user : users) {
            writer.append(user.userString());
        }

    }
}