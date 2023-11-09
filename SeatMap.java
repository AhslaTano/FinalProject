/**
 * Utilities for seat map
 */
public class SeatMap {
    public Seat[][] seats;

    public SeatMap(){
        seats = new Seat[10][10];
        for(int c = 0; c < seats.length; c++){
            for(int r = 0; r < seats[0].length; r++){
                seats[c][r] = new Seat("Row " + r + "Seat " + c, 10-r, c, r);
            }
        }
    }

    public SeatMap(int columns, int rows){
        seats = new Seat[columns][rows];
        for(int c = 0; c < seats.length; c++){
            for(int r = 0; r < seats[c].length; r++){
                seats[c][r] = new Seat( "Row " + r + "Seat " + c, 10-r, c, r);
            }
        }
    }
    //TODO: implement pretty print (row letter, 1-indexed numbers, ascii)
    public void printMap(){
        for (int c = 0; c < seats.length; c++) {
            for (int r = 0; r < seats[c].length; r++) {
                System.out.println("Column " + c + " Row " + r + ": " + seats[c][r]);
            }
        }
    }

    //TODO: available seats getter
    public String availableSeats(){
        String availableSeats = "";
        for(int c = 0; c < seats.length; c++){
            for(int r = 0; r < seats[0].length; r++){
                if(seats[c][r].getAvailability()){
                    availableSeats = availableSeats + "\t" + seats[c][r];
                }
                availableSeats = availableSeats + "\n";
            }
        }
        return availableSeats;
    }
}
