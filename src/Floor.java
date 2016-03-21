/**
 * The Floor class for the elevator simulation. Used by the Controller class. Contains an array of people who wish to go onto an
 * ascending elevator and an array of people who wish to go onto a descending elevator, as well as counters for each of them.
 * people can exit from a floor, and are only added by the Press class.
 * @author SL
 *
 */
public class Floor {
	
	private Person[] upPeople;
	private Person[] downPeople;
	private int upCounter;
	private int downCounter;
	/**
	 * constructor for the Floor class. Takes an int which specifies how many people are in the entire simulation and initializes
	 * both arrays to that size, in case of the unlikely situtation that all people end up on one floor going one way.
	 * @param maxPeople
	 */
	public Floor(int maxPeople){
		upPeople = new Person[maxPeople];
		downPeople = new Person[maxPeople];
	}
	
	/*
	 * Takes the person who wishes to go up who has waited the longest, returns them, and moves all the people down one so that
	 * order is maintained an the person to press the button most recently will get off first the next time. 
	 */
	public Person exitUp(){
		Person result = null;
		if(upCounter > 0){
			upCounter--;
			result = upPeople[0];
			upPeople[0] = null;
			for(int i = 1; i<=upCounter; i++){
				upPeople[i-1] = upPeople[i];
				upPeople[i] = null;
			}
		}
		return result;
	}
	
	public Person exitDown(){
		Person result = null;
		if(downCounter > 0){
			downCounter--;
			result = downPeople[0];
			downPeople[0] = null;
			for(int i = 1; i<=downCounter; i++){
				downPeople[i-1] = downPeople[i];
				downPeople[i] = null;
			}
		}
		return result;
	}
	
	public void addUp(Person person){
		upPeople[upCounter] = person;
		upCounter++;
	}
	
	public void addDown(Person person){
		downPeople[downCounter] = person;
		downCounter++;
	}
	
	public int getPeople(){
		return upCounter + downCounter;
	}
	
	public boolean hasUp(){
		return upCounter > 0;
	}
	
	public boolean hasDown(){
		return downCounter>0;
	}
}
