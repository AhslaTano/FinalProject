import java.util.ArrayList;

public class Event {
    //TODO: events save file
    private String eventName;
    private String location;
    public SeatMap seats;
    private String date;
    private Ticket[][] tickets;

    public Event(){
        eventName = "";
        location = "";
        seats = null;
        date = "";
        tickets = null;
    }
    public Event(String eventName, String location, String date, int rows, int columns){
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        seats = new SeatMap(columns,rows);
        tickets = new Ticket[columns][rows];
        // for(int c = 0; c < seats.length; c++){
        //     for(int r = 0; r < seats[0].length; r++){
        //         seats[c][r] = new Seat(true, "Row " + r + "Seat " + c, 10-r, c, r);
        //         tickets[c][r] = new Ticket();
        //     }
        // }
    }
    public String getEventName(){
        return eventName;
    }
    public String getLocation(){
        return location;
    }
    public String getDate(){
        return date;
    }

    public String toString(){
        return eventName + " held at " + location + ": " + date;
    }
}
