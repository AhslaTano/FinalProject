public class Event {
    private String eventName;
    private String location;
    public SeatMap seats;
    private String date;
    private String time;
    //tickets stored in seatmap in seats
    private int eventID;

    /**
     * Constructs a basic Event object with no values
     */
    public Event(){
        eventName = "";
        location = "";
        seats = null;
        date = "";
        time = "";
    }
    /**
     * construncts an event with values from parameters
     * @param eventName Name of the event
     * @param location Where the event is held
     * @param date Day of the event
     * @param time Time the event starts
     * @param rows Number of rows in the seating graph. This helps create the seatMap
     * @param columns Number of columns in the seating graph. This helps create the seatMap
     */
    public Event(String eventName, String location, String date, String time, int rows, int columns){
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        seats = new SeatMap(columns,rows);
        generateEventID();
    }
    /**
     * construncts an event with values from parameters
     * @param eventName Name of the event
     * @param location Where the event is held
     * @param date Day of the event
     * @param time Time the event starts
     * @param seats A created seatMap from the SeatMap class
     */
    public Event(int eventID, String eventName, String location, String date, String time, SeatMap seats){
        this.eventID = eventID;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.seats = seats;
    }
    /**
     * @return the name of the current event
     */
    public String getEventName(){
        return eventName;
    }
    /**
     * @return the location of the current event
     */
    public String getLocation(){
        return location;
    }
    /**
     * @return The date of the current event
     */
    public String getDate(){
        return date;
    }
    /**
     * @return Returns unique Event Id number. For server
     */
    public int getEventID() { return eventID; }
    /**
     * @return A string representation of the values of the event
     */
    @Override
    public String toString(){
        return eventID + "," + eventName + "," + location + "," + date + "," + time + "," + seats.toString();
    }
    /**
     * A more user friendly version of toString.
     * @return A description of the event for users
     */
    public String eventDescription(){
        return eventName + " held at " + location + " on " + date + " at " + time;
    }

    /**
     * Purchases ticket
     * @param seatNumber human-readable seat number
     * @param user The user to give the ticket to
     * @return Boolean indicating success
     */
    public boolean purchaseTicket(String seatNumber, User user){
        boolean result = false;        
        Seat seat = seats.getSeatFromHumanNumber(seatNumber);
        if (user != null && seat.getAvailability()) {
            if (user.getPoints() >= seat.getPrice()) {
                user.spendPoints(seat.getPrice());
                seat.setTicketOwner(user);
                result = true;
            }
        }
        return result;
    }
    /**
     * Creates unique eventID using hash codes
     */
    private void generateEventID(){
       eventID = this.toString().hashCode();
    }

}
