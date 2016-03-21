
/**
 * The Exit Class, subclass of Event, for the simulation class. Simulates a person exiting an elevator once he has
 * reached his destination.
 * 
 * @author Laivi Malamut-Salvaggio
 *
 */
public class Exit extends Event {

	
	public Exit(int tim, Elevator elev, Person perso, int floo) {
		super(tim, elev, perso, floo);
		priority = 80;
	}
/*
 * Takes the person who wishes to alight out of the elevator, computes for how long he has waited to arrive at his floor,
 *  and then sets log message. Returns another Exit if more people want to get off, an Enter if people want to get on,
 *  or a closeDoor if neither of the former are true.
 */
	@Override
	public Event act() {
		person = elevator.exit(floorNumber);
		waitTime = time - person.getStartTime();
		logMessage = "Time " + time + ": " + person.getName() + " exited elevator " + elevator.getName() + " on floor " + (floorNumber + 1) + ".";
		if(elevator.isAlighting(floorNumber)){
			return new Exit(time + 2, elevator, null, floorNumber);
		}
		else if(floor.hasUp() && elevator.isAscending()){
			return new Enter(time + 3, elevator, floor.exitUp(), floorNumber);
		}
		else if(floor.hasDown() && !elevator.isAscending()){
			return new Enter(time + 3, elevator, floor.exitDown(), floorNumber);
		}
		else{
			return new closeDoor(time + 1, elevator, null, floorNumber);
		}
	}

}
