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

    public Seat(int price, int row, int column){
        this.price = price;
        this.column = column;
        this.row = row;
        this.ticket = new Ticket(null, this);
        this.available = ticket.getAvailability();
    }
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

    public void setTicketOwner(User owner){
        ticket.setOwner(owner);
        this.setOwner(owner);
    }

    @Override
    public String toString(){
        return row + "-" + column + "-" + price + "-" + (owner == null? " " : owner.getUsername());
    }
}
