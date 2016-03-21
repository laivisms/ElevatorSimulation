/**
 * The Enter class, subclass of Event, part of the elevator simulation. Simulates someone entering an elevator from a floor.
 * Returns another enter if there are more people who wish to enter the elevator and the elevator is not full, and a closeDoor 
 * otherwise.
 * 
 * @author Laivi Malamut-Salvaggio
 *
 */
public class Enter extends Event{

	public Enter(int tim, Elevator elev, Person perso, int floo) {
		super(tim, elev, perso, floo);
		priority = 10;
	}
	
	public Event act(){
		logMessage = "Time " + time + ": " + person.getName() + " entered elevator " + elevator.getName() + " on floor " + (floorNumber +1) + ".";
		elevator.enter(person);
		if(elevator.isAscending() && floor.hasUp() && !elevator.isFull()){
			return new Enter(time + 3, elevator, floor.exitUp(), floorNumber);
		}
		else if(!elevator.isAscending() && floor.hasDown() && !elevator.isFull()){
			return new Enter(time+3, elevator, floor.exitDown(), floorNumber);
		}
		else{
			return new closeDoor(time++, elevator, null, floorNumber);
		}
	}


}
