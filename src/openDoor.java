/**
 * The openDoor class, subclass of Event, part of the elevator simulation. Simulates an elevator opening its doors to let 
 * people off or on. Returns an Exit Event if people want to exit or else an Enter Event if people want to enter and 
 * nobody wants to exit.
 * @author Laivi Malamut-Salvaggio
 *
 */
public class openDoor extends Event{

	public openDoor(int tim, Elevator elev, Person perso, int floo) {
		super(tim, elev, perso, floo);
		priority = 40;
	}
	
	public Event act(){
		logMessage = "Time " + time + ": elevator " + elevator.getName() + " opened its doors on floor " + (floorNumber +1) + ".";
		if(elevator.isAlighting(floorNumber)){
			return new Exit(time + 2, elevator, null, floorNumber);
		}
		else{
			return new Enter(time + 3, elevator, person, floorNumber);
		}
	}

}
