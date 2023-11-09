public class Program {
    public static void main(String[] args){
        SeatMap seatmap = new SeatMap(20,10);
        seatmap.printMap();

        UserList users = new UserList();
        User user = new User("Nick", "passw0rd", "Nick Miller", "millere99@yahoo.com", users);
        user = new User("Zach", "str0ngpassw0rd", "Zach Julian", "zach@julian.com", users);
        user = new User("midas", "g0ld", "King Midas", "midas@greece.com", users);

        Event event = new Event("Test", "Testville", "10/10", 10, 20);
        event.seats.printMap();

        try {
            users.saveUsersList();
        } catch (Exception e) {
            // TODO: handle exception
        }


    }
}
