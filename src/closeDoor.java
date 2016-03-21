/**
 * The closeDoor class, subclass of Event, part of the elevator simulation. Simulates an elevator closing its doors. 
 * Always returns a Move Event.
 * @author Laivi Malamut-Salvaggio
 *
 */
public class closeDoor extends Event {

	public closeDoor(int tim, Elevator elev, Person perso, int floo) {
		super(tim, elev, perso, floo);
		priority = 0;
	}

	public Event act() {
		logMessage = "Time " + time + ": Elevator " + elevator.getName() + " closed its doors on floor " + (floorNumber + 1) + ".";
		if(elevator.isAscending()){
			return new Move(time + 2, elevator, null, floorNumber + 1);
		}
		else{
			return new Move(time + 2, elevator, null, floorNumber - 1);
		}
	}

}
