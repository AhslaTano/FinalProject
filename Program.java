public class Program {
    public static void main(String[] args){

        UserList users = UserList.loadUserList();
        // User user = new User("Nick", "passw0rd", "Nick Miller", "millere99@yahoo.com", users);
        // System.out.println(user.toString());
        // user = new User("Zach", "str0ngpassw0rd", "Zach Julian", "zach@julian.com", users);
        // System.out.println(user.toString());
        // user = new User("midas", "g0ld", "King Midas", "midas@greece.com", users);
        // System.out.println(user.toString());
        System.out.println(users.toString());
        EventsList events = new EventsList();
        Event event = new Event("Test", "Testville", "10/10", 10, 20);
        events.addEvent(event);
        //System.out.println(event.toString());
        //EventsList events = EventsList.loadEventList(users);
        
        event.purchaseTicket("A5", users.getUser("midas"));
        events.saveEventsList();

        users.getUser("midas").viewPurchasedTickets();
    }
}
