public class Ticket {
    private User owner;
    private Seat seat;

    public Ticket(){
        owner = null;
        seat = null;
    }
    /**
     * Creates a Ticket object for the users to view as they purchsed the seat
     * @param owner The User who owns the ticket
     * @param seat The seat the owner bought
     */
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
    /**
     * @return Whenter the ticket has been bought or not
     */
    public boolean getAvailability(){
        return(owner == null);
    }

}
