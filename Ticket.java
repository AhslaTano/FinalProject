public class Ticket {
    private Seat purchasedSeat;
    private Event event;
    private User owner; //Ticket keeps track of its owner

    public Ticket(){
        event = null;
        purchasedSeat = null;
        owner = null;
    }
    public Ticket(Seat purchasedSeat, Event event, User owner){
        this.purchasedSeat = purchasedSeat;
        this.event = event;
        this.owner = owner;
    }
    public String getSeatLocation(){
        return purchasedSeat.getLocation();
    }
    public Event getEvent(){
        return event;
    }
}
