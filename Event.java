public class Event {
    private String eventName;
    private String location;
    public SeatMap seats;
    private String date;
    private String time;
    //tickets stored in seatmap in seats

    public Event(){
        eventName = "";
        location = "";
        seats = null;
        date = "";
        time = "";
    }
    public Event(String eventName, String location, String date, String time, int rows, int columns){
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        seats = new SeatMap(columns,rows);
    }
    public Event(String eventName, String location, String date, String time, SeatMap seats){
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.seats = seats;
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
    
    @Override
    public String toString(){
        return eventName + "," + location + "," + date + "," + time + "," + seats.toString();
    }

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
                user.addPurchasedTicket(seat.getTicket());
                result = true;
            }
        }
        return result;
    }

}
