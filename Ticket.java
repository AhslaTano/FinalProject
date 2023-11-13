public class Ticket {
    private User owner;
    private Seat seat;

    public Ticket(){
        owner = null;
        seat = null;
    }
    public Ticket(User owner, Seat seat){
        this.owner = owner;
        this.seat = seat;
    }

    public User getOwner(){
        return owner;
    }
    public void setOwner(User owner){
        this.owner = owner;
        seat.setOwner(owner);
    }

    public boolean getAvailability(){
        return(owner == null);
    }

}
