/**
 * This file is used for testing functions.
 */
public class Program {
    public static void main(String[] args){

        UserList users = UserList.loadUserList();
        // User user = new User("Nick", "passw0rd", "Nick Miller", "millere99@yahoo.com", users);
        // System.out.println(user.toString());
        // user = new User("Zach", "str0ngpassw0rd", "Zach Julian", "zach@julian.com", users);
        // System.out.println(user.toString());
        // user = new User("midas", "g0ld", "King Midas", "midas@greece.com", users);
        // System.out.println(user.toString());
        //System.out.println(users.toString());
        EventsList events = EventsList.loadEventList(users);
        System.out.println(events.viewEvents());
        //EventsList events = new EventsList();
        Event event = new Event("Test", "Testville", "10/10", "7:00", 20, 10);

        events.addEvent(event);

        event = new Event("Test", "Testville", "10/10", "8:00", 20, 10);

        events.addEvent(event);

        //events.getEvent(0).purchaseTicket("h2", users.getUser("midas"));
        events.saveEventsList();

        users.getUser("midas").viewPurchasedTickets();
        events.getEvent(0).seats.printMap();
        Seat seat = event.seats.getSeatFromHumanNumber("");
        System.out.println(seat.getAvailability());
    }
}
