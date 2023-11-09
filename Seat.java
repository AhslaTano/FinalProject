public class Seat {
    private boolean available;
    private String location;
    private int price;
    private int column;
    private int row;
    private Ticket ticket;

    public Seat(){
        available = false;
        location = "";
        price = 0;
    }

    public Seat(String location, int price, int column, int row){
        this.location = location;
        this.price = price;
        this.column = column;
        this.row = row;
        this.ticket = new Ticket(null, this);
        this.available = ticket.getAvailability();
    }

    public int getPrice(){
        return price;
    }
    public boolean getAvailability(){
        return available;
    }
    public String getLocation(){
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

    @Override
    public String toString(){
        return "Seat " + getSeatNumber();
    }
}
