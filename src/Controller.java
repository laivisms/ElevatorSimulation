import java.util.Random;
import java.util.PriorityQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *       Controller Class for the Elevator Simulation program. Keeps track of the Floor, Elevator, and Event objects within the
 *       simulation. People are kept track of by the Floor and Elevator objects, depending on where the person is. First initializes
 *       the amount of floors within the simulation (this can be changed by editing floor amount, and the program will use the specified
 *       amount of floors). Then initializes the three Elevators, and the People (amount of people can be changed as well). People are
 *       stored in Press objects, which are then stored in myQueue, to occur at a random time. Elevator commands are then initialized (three Move Events) and stored
 *       in myQueue to occur at time 0. Elements in myQueue are sorted according to time, ascending. In each iteration of the main loop,
 *       while there is an element on the top of myQueue with time == current iteration, that event is executed as follows: it is first
 *       popped from the list, then given the Floor object on which that Event will be occurring. The Event's Act function is called, 
 *       which may modify the floor and elevator which it is using, then will return an Event object. If the object is not null, it is
 *       added into myQueue. If the user gave the argument of "logging", the log messages are printed. If it is an Event which caused
 *       a person to leave an elevator, the person's wait time is added to waitTimes. After 7200 "seconds" (iterations), the loop
 *       is ended and the average wait time is computed and printed.
 *       
 *        
 * @author Laivi Malamut-Salvaggio
 * @version 1.0
 *
 */

public class Controller {
	private static Floor[] floors;
	private static Elevator[] elevators = new Elevator[3];
	private static PriorityQueue<Event> myQueue = new PriorityQueue<Event>();
	private static String[] firstName = new String[100];
	private static String[] surname = new String[100];
	private static boolean useNames = true;
	private static boolean Print = false;
	private static final int FLOOR_AMOUNT = 18;
	private static final int PEOPLE_AMOUNT = 200;
	private static int waitTimes;
	private static int peopleArrived;

	public static void main(String[] args){
		
		if(args.length == 1){
			if(args[0].toLowerCase() == "logging"){
				Print = true;
			}
		}

		floors = new Floor[FLOOR_AMOUNT]; // create floors (adjustable)
		for(int i = 0; i<FLOOR_AMOUNT; i++){
			floors[i] = new Floor(PEOPLE_AMOUNT);
		}

		elevators[0] = new Elevator(0,FLOOR_AMOUNT, true, "One"); // create elevators
		elevators[1] = new Elevator(8, FLOOR_AMOUNT, false, "Two");
		elevators[2] = new Elevator(17, FLOOR_AMOUNT, false, "Three");

		addNames(); // retrieve all of the first and last names from txt files, store in array 
		addPeople(); // create the specified amount of people, give them unique names if can find the name files
		addElevatorCommands(); // add the initial Move commands for the elevators

		for(int i = 0; i<7200; i++){
			while(myQueue.peek() != null && myQueue.peek().getTime() == i){
				Event current = myQueue.poll();
				current.setFloor(floors[current.getFloorNumber()]); // give the event the floor its working with
				Event toAdd = current.act(); // have the event act out its function, returns the resulting event
				if (toAdd != null){ // Press returns null, dont want to add it to queue
					myQueue.add(toAdd);
				}
				if(Print && (current.getClass() != new Move(0,null,null,0).getClass())){ // Dont print out Move's log message
					System.out.println(current.getLogMessage());
				}
				if(current.getWaitTime() > 0){
					waitTimes += current.getWaitTime();
					peopleArrived++;
				}

			}
		}
		
		printTime();

	}
	/**
	 *  The addPeople function. Created the field variable specified amount of people and stores each of them in a new Press event,
	 *  which is then added to myQueue. Each person gets a random name from the firstName and surname arrays if they were able
	 *  to retrieve the names from files in the addNames() function. Otherwise, each person is named "Person x", with x<PEOPLE_AMOUNT.
	 *  
	 */
	private static void addPeople(){
		Random rand = new Random();
		for(int i = 0; i<PEOPLE_AMOUNT; i++){
			Person tempPerson = new Person(FLOOR_AMOUNT);
			if(useNames){ // if we found the txt files containing the unique names, use them
				String name = firstName[rand.nextInt(100)] + " " + surname[rand.nextInt(100)];
				tempPerson.setName(name);
			}
			else{
				tempPerson.setName("Person " + i);
			}
			Press tempPress = new Press(rand.nextInt(7000), null, tempPerson, tempPerson.getStartFloor());
			myQueue.add(tempPress);
		}
	}

	/**
	 * The addNames function. Retrieves the list of first names and surnames from txt files and stores them in arrays for
	 * later use.
	 * 
	 */
	private static void addNames(){
		FileReader first = null;
		FileReader last = null;
		try{
			first = new FileReader("firstnames.txt");
			last = new FileReader("surnames.txt");
		}
		catch(FileNotFoundException e){
			useNames = false;
		}

		if(useNames){
			Scanner First = new Scanner(first);
			Scanner Last = new Scanner(last);
			int counter = 0;
			while(First.hasNext() && Last.hasNext()){
				firstName[counter] = First.nextLine();
				surname[counter] = Last.nextLine();
				counter++;
			}
			First.close();
			Last.close();
		}
	}
/**
 * The addElevatorCommands function. Initializes the initial Move events for each elevator and stores them in myQueue.
 */
	private static void addElevatorCommands(){
		myQueue.add(new Move(0, elevators[0], null, 1));
		myQueue.add(new Move(0, elevators[1], null, 7));
		myQueue.add(new Move(0, elevators[2], null, 16));
	}
/**
 *  The printTime function. Computes and prints the average wait time for a person in the elevator simulation.
 */
	private static void printTime(){
		System.out.println("Average wait time: " + (waitTimes/peopleArrived) + " seconds.");
	}
}
