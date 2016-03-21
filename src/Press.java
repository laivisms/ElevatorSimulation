/**
 * The Press class, subclass of Event, part of the elevator simulation. Simulates a person pressing either up or down on 
 * a certain floor in the elevator system. Always returns null.
 * @author Laivi Malamut-Salvaggio
 *
 */
public class Press extends Event {

	public Press(int tim, Elevator elev, Person perso, int floo) {
		super(tim, elev, perso, floo);
		priority = 100;
	}
	/*
	 * Add the person to either the up or down list on their floor, depending on where they are going. Does not need to 
	 * return an event because a Move Event or an Exit event will be able to detect the person on the floor and will stop 
	 * the elevator for them.
	 */
	public Event act() {
		String direction = "up";
		if(person.goingUp()){
			floor.addUp(person);
		}
		else{
			floor.addDown(person);
			direction = "down";
		}

		person.setStartTime(time);
		logMessage = "Time " + time + ": " + person.getName() + " pressed " + direction + " on floor " + (floorNumber +1) + ".";
		return null;
	}
	
	

}
