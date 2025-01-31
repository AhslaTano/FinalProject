import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for seat map
 */
public class SeatMap {
    public Seat[][] seats;

    //Switched [col][row] indexing for [row][col] because 1. convention 2. made my brain hurt
    public SeatMap(int columns, int rows){
        seats = new Seat[columns][rows];
        for(int r = 0; r < seats.length; r++){
            for(int c = 0; c < seats[r].length; c++){
                seats[r][c] = new Seat(rows-r, r, c);
            }
        }
    }

    /**
     * Prints a formatted map of seats
     */
    public String printMap(){
        int length = seats[0].length * 6;
        String blankSpace =  String.format("%1$" + (length/4) +"s", "");
        String Stage = blankSpace + "[" + blankSpace + "STAGE" + blankSpace + "] \n";
        //System.out.println("\n" + Stage);
        for(int r = 0; r < seats.length; r++){
            for(int c = 0; c < seats[r].length; c++){
                Stage = Stage +"[ " + (seats[r][c].getAvailability()? seats[r][c].getSeatNumber() : "XX") + " ]";
            }
            Stage = Stage + "\n";
        }
        return Stage;
    }

    public String availableSeats(){
        String availableSeats = "";
        for(int r = 0; r < seats.length; r++){
            for(int c = 0; c < seats[0].length; c++){
                if(seats[r][c].getAvailability()){
                    availableSeats = availableSeats + "\t" + seats[r][c];
                }
                availableSeats = availableSeats + "\n";
            }
        }
        return availableSeats;
    }

    @Override
    public String toString(){
        String result = "";
        for(int r = 0; r < seats.length; r++){
            for(int c = 0; c < seats[r].length; c++){
                result += seats[r][c].toString() + "/";
            }
        }
        return result;
    }
    
    public void addSeat(Seat seat){
        int[] location = seat.getLocation();
        seats[location[0]][location[1]] = seat;
    }

    /**
     * Generates seat map from string
     * @param seatmapString String generated by toString()
     * @param users list of users, for tickets
     * @return Reconstructed seat map
     */
    public static SeatMap loadSeatMap(String seatmapString, UserList users){
        Scanner scan = new Scanner(seatmapString);
        ArrayList<String> seatStrings = new ArrayList<>();
        scan.useDelimiter("/");
        while (scan.hasNext()) {
            seatStrings.add(scan.next());
        }
        String[] lastSeat = seatStrings.get(seatStrings.size()-1).split("-");
        int rows = Integer.parseInt(lastSeat[0]) + 1;
        int cols = Integer.parseInt(lastSeat[1])+ 1;
        SeatMap seats = new SeatMap(rows, cols);

        for (String string : seatStrings) {
            String[] seatInfo = string.split("-");

            int row = Integer.parseInt(seatInfo[0]);
            int col = Integer.parseInt(seatInfo[1]);
            int price = Integer.parseInt(seatInfo[2]);

            Seat seat;
            if (seatInfo.length == 3) {
                seat = new Seat(price, row, col);
            }
            else
            {
                User owner = users.getUser(seatInfo[3]);
                seat = new Seat(price, row, col, owner);
            }
            seats.addSeat(seat);
        }
        scan.close();
        return seats;
    }
    

    /**
     * Converts human-readable seat number to coords, returns seat
     * @param seatNumber human-formatted seat number i.e. B6 as opposed to [1][5]
     * @return seat object
     */
    public Seat getSeatFromHumanNumber(String seatNumber){
        seatNumber = seatNumber.toUpperCase(); //just in case
        int row = (int) seatNumber.substring(0, 1).toCharArray()[0] - 65;
        //System.out.println(row);
        int col = Integer.parseInt(seatNumber.substring(1));
        //System.out.println(col);
        return seats[row][col];

    }
}
