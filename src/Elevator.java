/**
 * The Elevator class for the elevator simulation. Used by the Controller class. Keeps track of which floor it is on, whether it is currently ascending or
 * descending, and how many Person objects it currently contains.
 * 
 * @author Laivi Malamut-Salvaggio
 *
 */
public class Elevator {
	
	private static final int MAX_CAPACITY = 10;
	private int topFloor;
	private Person[] people = new Person[10];
	private int currentHold;
	private int floor;
	private boolean ascending; // true is going up, false is going down
	private String name;
	
	public Elevator(int startFloor, int floorAmount, boolean direction, String name){
		floor = startFloor;
		topFloor = floorAmount-1;
		ascending = direction;
		this.name = name;
	}
	/**
	 * The goUp function. Adds one to floor iff it is not on the top floor already. If after moving up it is on the top floor,
	 * ascending is set to false.
	 * 
	 * @return True if the elevator successfully moved up, false otherwise.
	 */
	public boolean goUp(){
		boolean result = false;
		if(floor < topFloor){
			floor++;
			ascending = true;
			result = true;
			}
		if(floor == topFloor){
			ascending = false;
		}
		return result;
	}
	
	/**
	 * The goDown function. Subtracts one from floor iff it is not on the bottom floor already. If after moving down it is on
	 * the bottom floor, sets ascending to true.
	 * 
	 * @return True if the elevator successfully moved down, false otherwise.
	 */
	public boolean goDown(){
		boolean result = false;
		if(floor>0){
			floor--;
			ascending = false;
			result = true;
			if(floor == 0){
				ascending = true;
			}
		}
		return result;
	}
	
	/**
	 * The enter function. Inputs the specified Person object into the array of people currently on this elevator iff the elevator
	 * is not already at max capacity. 
	 * 
	 * @param person The Person object to add to the array.
	 * @return True if the person was successfully added, false otherwise.
	 */
	public boolean enter(Person person){
		boolean result = false;
		if(currentHold < MAX_CAPACITY){
		    people[currentHold] = person;
			currentHold++;
			result = true;
		}
		return result;
	}
	
	/**
	 * The exit function. Takes the person in the elevator which wants to get off at the specified floor out of the elevator
	 * and returns it.
	 * @param floo The floor which the elevator is letting people off at
	 * @return The person in the elevator meant to alight on floor floo.
	 */
	public Person exit(int floo){
		Person result = null;
		boolean found = false;
		for (int i = 0; i<currentHold && !found; i++){
			if(people[i].getEndFloor() == floo){
				result = people[i];
				currentHold--;
				people[i] = people[currentHold];
				people[currentHold] = null;
				found = true;
			}
		}
		return result;
	}
	
	public int getFloor(){
		return floor;
	}
	
	public int getHold(){
		return currentHold;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isAscending(){
		return ascending;
	}
	/*
	 * checks if the elevator contains someone who wishes to alight onto the specified floor.
	 */
	public boolean isAlighting(int floo){
		if(currentHold>0){
			for (int i = 0; i<currentHold; i++){
				if(people[i].getEndFloor() == floo){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isFull(){
		return currentHold == MAX_CAPACITY;
	}

}
