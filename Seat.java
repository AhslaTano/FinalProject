/**
 * Class for Seats. Manages seat and associated ticket.
 */
public class Seat {
    private boolean available;
    private int price;
    private int column;
    private int row;
    private Ticket ticket;
    private User owner;

    public Seat(){
        available = false;
        price = 0;
    }
    /**
     * Constructs an instance of an unpurchased seat at an Event
     * @param price The number of points to buy the seat
     * @param row The row number it is located at in the SeatMap
     * @param column The column number it is located at in the SeatMap
     */
    public Seat(int price, int row, int column){
        this.price = price;
        this.column = column;
        this.row = row;
        this.ticket = new Ticket(null, this);
        this.available = ticket.getAvailability();
    }
    /**
     * Constructs an instance of a purchased seat
     * @param price The number of points to buy the seat
     * @param row The row number it is located at in the SeatMap
     * @param column The column number it is located at in the SeatMap
     * @param owner The User object that bought the seat
     */
    public Seat(int price, int row, int column, User owner){
        this.price = price;
        this.column = column;
        this.row = row;
        this.owner = owner;
        ticket = new Ticket(owner, this);
        available = ticket.getAvailability();
    }

    public int getPrice(){
        return price;
    }
    public boolean getAvailability(){
        return available;
    }
    public Ticket getTicket(){
        return ticket;
    }
    public void setOwner(User owner){
        this.owner = owner;
    }

    public int[] getLocation(){
        return new int[]{row, column};
    }
    
    //Provides human-formatted seat number
    // letter corresponds to row, number to column
    public String getSeatNumber(){
        //Get row letter by ASCII
        //TODO: add robustness by accounting for row > 26
        String rowNum = String.valueOf((char)('A' + row));
        return rowNum + String.valueOf(column + 1);
    }
    /**
     * Gives an unowned ticket an owner. Called by the server when a ticket is purchased
     * @param owner The user object that bought the ticket
     */
    public void setTicketOwner(User owner){
        ticket.setOwner(owner);
        this.setOwner(owner);
    }

    @Override
    /**
     * @return A string of the ticket's values
     */
    public String toString(){
        return row + "-" + column + "-" + price + "-" + (owner == null? " " : owner.getUsername());
    }
}
