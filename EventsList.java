import java.io.*;
import java.util.*;

public class EventsList {
    private ArrayList<Event> events;
    public EventsList(){
        events = new ArrayList<>();
    }

    public void addEvent(Event event){
        boolean eventExists = false;
        for (Event e : events) {
            if (e.getEventID() == event.getEventID()) {
                eventExists = true;
                break;
            }
        }
        if (!eventExists) {
            events.add(event);
        }
    }
    public Event getEvent(int index) {return events.get(index);}

    /**
     * Writes events to a .txt file
     */
    public void saveEventsList(){
        try {
            File file = new File("events.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

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

    /**
     * Creates eventsList from a file
     * @param users A UserList, corresponds to users in event tickets
     * @return Events list object
     */
    public static EventsList loadEventList(UserList users){
        EventsList events = new EventsList();
        try {
             File file = new File("events.txt");
            if(file.length() != 0){
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNextLine()){
                    String eventLine = fileScan.nextLine();
                    String[] eventStrings = eventLine.split(",");
                    events.addEvent(new Event(Integer.valueOf(eventStrings[0]), eventStrings[1], eventStrings[2], eventStrings[3], eventStrings[4], SeatMap.loadSeatMap(eventStrings[5], users)));
                }
            fileScan.close();
            }  
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
       
        return events;
    }

    public String viewEvents(){
        String result = "";
        if (events.isEmpty()) {
            result = "There are no events coming up.";
        } else {
            for (Event event : events) {
                result += (events.indexOf(event) + 1)+ ". " +  event.eventDescription();
            }
        }
        return result;
    }


}
