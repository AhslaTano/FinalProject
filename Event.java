public class Event {
    //TODO: events save file
    private String eventName;
    private String location;
    public SeatMap seats;
    private String date;
    //tickets stored in seatmap in seats

    public Event(){
        eventName = "";
        location = "";
        seats = null;
        date = "";
    }
    public Event(String eventName, String location, String date, int rows, int columns){
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        seats = new SeatMap(columns,rows);
    }
    public Event(String eventName, String location, String date, SeatMap seats){
        this.eventName = eventName;
        this.location = location;
        this.date = date;
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
        return eventName + "," + location + "," + date + "," + seats.toString();
    }

    public String eventDescription(){
        return eventName + " held at " + location + ": " + date;
    }

    /**
     * Purchases ticket
     * @param seatNumber human-readable seat number
     * @param user The user to give the ticket to
     * @return
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
