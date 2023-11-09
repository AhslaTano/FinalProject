public class Seat {
    private boolean available;
    private int price;
    private int column;
    private int row;//location given by row/col
    private Ticket ticket;
    private User owner;

    public Seat(){
        available = false;
        price = 0;
    }

    public Seat(int price, int column, int row){
        this.price = price;
        this.column = column;
        this.row = row;
        this.ticket = new Ticket(null, this);
        this.available = ticket.getAvailability();
    }
    public Seat(int price, int column, int row, User owner){
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
        int[] location = {row, column};
        return location;
    }
    
    //Provides human-formatted seat number
    // letter corresponds to row, number to column
    public String getSeatNumber(){
        //Get row letter by ASCII
        //TODO: add robusticity by accounting for row > 26
        String rowNum = String.valueOf((char)('A' + row));
        return rowNum + String.valueOf(column + 1);
    }

    public void setTicketOwner(User owner){
        ticket.setOwner(owner);
    }

    @Override
    public String toString(){
        return row + ":" + column + ":" + price + ":" + (owner == null? "" : owner.getUsername());
    }
}
