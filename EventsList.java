import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EventsList {
    private ArrayList<Event> events;

    public EventsList(){
        events = new ArrayList<>();
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void saveEventsList(){
        try {
            File file = new File("events.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        
            for (Event event : events) {
                writer.append(event.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Event list file not found!");
        }
    }

    @Override
    public String toString(){
        String result = "";
        for (Event event : events) {
            result += event.toString();
        }
        return result;
    }
    
    public static EventsList loadEventList(UserList users){
        EventsList events = new EventsList();
        try {
             File file = new File("events.txt");
            if(file.length() != 0){
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNextLine()){
                    String eventLine = fileScan.nextLine();
                    String[] eventStrings = eventLine.split(",");
                    events.addEvent(new Event(eventStrings[0], eventStrings[1], eventStrings[2], SeatMap.loadSeatMap(eventStrings[3], users)));
                }
            fileScan.close();
            }  
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
       
        return events;
    }
}
