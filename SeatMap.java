import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for seat map
 */
public class SeatMap {
    public Seat[][] seats;

    public SeatMap(){
        seats = new Seat[10][10];
        for(int c = 0; c < seats.length; c++){
            for(int r = 0; r < seats[0].length; r++){
                seats[c][r] = new Seat(10-r, c, r);
            }
        }
    }

    public SeatMap(int columns, int rows){
        seats = new Seat[columns][rows];
        for(int c = 0; c < seats.length; c++){
            for(int r = 0; r < seats[c].length; r++){
                seats[c][r] = new Seat(10-r, c, r);
            }
        }
    }
    //TODO: implement pretty print (row letter, 1-indexed numbers, ascii)
    public void printMap(){
        for (int c = 0; c < seats.length; c++) {
            for (int r = 0; r < seats[c].length; r++) {
                System.out.print("Column " + c + " Row " + r + ": " + seats[c][r]);
            }
        }
    }

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

    @Override
    public String toString(){
        String result = "";
        for (int c = 0; c < seats.length; c++) {
            for (int r = 0; r < seats[c].length; r++) {
                result += seats[c][r].toString() + "/";
            }
        }
        return result;
    }
    
    public void addSeat(Seat seat){
        int[] location = seat.getLocation();
        seats[location[0]][location[1]] = seat;
    }

    public static SeatMap loadSeatMap(String seatmapString, UserList users){
        Scanner scan = new Scanner(seatmapString);
        ArrayList<String> seatStrings = new ArrayList<>();
        scan.useDelimiter("/");
        while (scan.hasNext()) {
            seatStrings.add(scan.next());
        }
        String[] lastSeat = seatStrings.get(seatStrings.size()-1).split(":");
        int rows = Integer.valueOf(lastSeat[0]);
        int cols = Integer.valueOf(lastSeat[1]);
        SeatMap seats = new SeatMap(cols, rows);        
        for (String string : seatStrings) {
            Seat seat;
            String[] seatString = string.split(":");
            for (String str : seatString) {
                System.out.println("Debug:" + str);
            }
            int row = Integer.valueOf(seatString[0]);
            int col = Integer.valueOf(seatString[1]);
            int price = Integer.valueOf(seatString[2]);
            
            if (!seatString[3].equals("")) {
                seat = new Seat(price, col, row);
            }
            else
            {
                seat = new Seat(price, col, row, users.getUser(seatString[3]));
            }
            seats.addSeat(seat);
        }
        scan.close();
        return seats;
    }
    
    public Seat getSeatFromLocation(int column, int row){
        return seats[column][row];
    }

    /**
     * Converts human-readable seat number to coords, returns seat
     * @param seatNumber human-formatted seat number i.e. B6 as opposed to [1][5]
     * @return seat object
     */
    public Seat getSeatFromHumanNumber(String seatNumber){
        seatNumber = seatNumber.toUpperCase(); //just in case
        int row = Integer.valueOf(seatNumber.substring(0,1).toCharArray()[0]) - 65;
        int col = Integer.valueOf(seatNumber.substring(1));
        return seats[row][col];

    }
}
