Nick Miller, Zach Julian 

Progress Report: 

    Dates and times of your meetings 

    Thursday, October 19, 2:45 PM 

    What has been completed 

    Plans for program, considerations of possible methods, some object structures 

    Each group member's contributions to the work so far 

    Nick: 

    Report writing 

    Plans for implementing menu, menu options, storing seats, logins. Details within this document.  

    Research for possible UI options, possible security vulnerabilities 

    Zach: 

    Details on implementing functionalities 

    Research on server-client possibilities 

    Creation of base objects 
      

    What menu options will you have? 

    Display Upcoming Events 

    Display Seat Map 

    Purchase Ticket 

    Create Account  

    Log In 

    Check Reservations 

    Quit 

 

    What will happen with each menu option? (What functions will you have? What parameters will they take and what will they return (if anything))  

 

    Display Upcoming Events 

    Will list all upcoming events in the system. There will be a simple listEvents() method that prints things out in a numbered list, no parameters or returns.  

    Then the menu will take user input to either select an event or back out to the main menu. SelectEvent(int numberOnList) will return the event object at the given number on the list, displaying its title, date, and time, and providing the following options: 

    Display Available Seats 

    This will call event.displaySeats(), which will print an ‘ASCII art’ map of seats similar to the following: 

    Each taken seat will be marked with an X. Otherwise they will be left blank. 

[                         STAGE                         ] 

   1      2      3      4      5      6      7      8      9     10      11     12 

A [ X ]  [ X ]  [   ]  [   ]  [   ]  [ X ]  [ X ]  [ X ]  [ X ]  [   ]  [ X ]  [ X ] 

B [   ]  [   ]  [   ]  [ X ]  [ X ]  [   ]  [   ]  [ X ]  [   ]  [ X ]  [   ]  [ X ] 

C [   ]  [   ]  [   ]  [   ]  [ X ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ] 

D [   ]  [   ]  [   ]  [ X ]  [   ]  [   ]  [ X ]  [ X ]  [   ]  [   ]  [   ]  [   ] 

E [   ]  [   ]  [   ]  [   ]  [   ]  [ X ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ] 

[ X ] = TAKEN  [   ]  = AVAILABLE 

    Purchase Ticket 

    This option will essentially move the program flow to menu item 3 below, but with an event already selected. 

    Display Seat Map - This menu option will simply print a map of the seats in the theater. 

    Purchase Ticket 

    Select Event 

    Check Available Seats 

    Display available seat numbers by looping through rows and/or displaying the map. 

    Select Seats 

    User inputs seat rows/numbers to select. Accepts m 

    Confirm purchase 

    Create Account  

    This menu option will collect the following user information and send it to the User constructor. 

    Enter First Name 

    Enter Last Name 

    Enter Phone Number 

    Enter Email 

    Enter Password 

    Once a user is successfully created it will be logged in. 

    Log In 

    Enter Username 

    Enter Password //returns to main menu 

    Check reservations 

    This option will communicate with the server to display a list of tickets purchased by the user. 

    Quit 

 

    What will the client code do? Are there any files that the client will handle? (Note that a user (customer)  will run the client program and quit it when they are done. There should be a quit option in your menu. Until the user chooses to quit, the client program should keep running) 

    The client code will loop through the menu as described above. There will be no need to store files clientside. 

 

    What will your server code do? What files will the server handle? (Note that the server program will always run. Even if it gets stopped (say by an admin), the data about customers should stay in the system  so you need to store data in files) 

    The server code will handle: 

    The list of events. Each event will be an object including: 

    String title {get, set}  

    String description {get, set} 

    String dateTime {get, set} 

    Int price 

    Seatmap seats 

    Method Ticket reserveSeat(string seatCoords, string username) 

    Method Seat locateSeat(string seatCoords) 

    Given a seat location, returns the seat object there 

    Method void displaySeats() 

    Prints seat map 

    The ticket class  

    This will represent a reservation and be stored in the User objects. 

    Event event 

    User user 

    Or string name (combining firstName/lastName) if storing user doesn’t work here 

    Seat seat 

    The master copy of the seat map 

    SeatingRow[] seatMap 

    This will be an array of seatsRow objects, each containing an arrayList of seat objects. 

    A seat’s position will be represented by its location in the array seatMap[I].get(j), where I corresponds to the row and j corresponds to the seat number.  

    For readability, seat locations would be translated into alphanumerical strings. For example, the 7th seat in the 3rd row would be accessed by seatmap[2][6] and would be displayed as ‘Seat C7.’ 

    The seatsRow class will only need to contain: 

    ArrayList<Seat> row 

    A Seat object will contain: 

    User management class 

    ArrayList<User> users 

    Method User registerUser(string firstName, string lastName, string phoneNumber, string email, string username, string password) 

    Method void loginUser(string username, string password) 

    User object: 

    String firstName {get, set} 

    String lastName {get, set} 

    String phoneNumber {get, set} 

    String email {get, set} 

    String username {get, set} 

    String password {get, set} 

    ArrayList<Ticket> tickets 

    Int points {get, set} 

    When a user is created, it will be assigned some number of points. 

Vulnerabilities: 

    Passwords stored in plaintext 

    Vulnerable to bad input that can disrupt storage files 

 

Screenshots of your work so far 

 

Plan for completion of the project 

We’ll start with basic functionality – things like implementing objects and checking interactions server side. Then we will move to client-server interactions before ending with polishing steps. 

What is left  

Implementation, testing 

Timeline for completion 

Due Friday, November 17.  

    We will spend the time until the start of next week (Monday, Nov. 6) on the basic aspects of the programs as described above. 

    We will spend the next week (Monday, Nov 13) on implementing more complex functionality. 

    The time until the due date will be spent on testing and polishing steps. 

Any other comments you have about the project 

    I just hope that I can find enough time to complete it. 