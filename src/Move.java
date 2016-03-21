
/**
 * The Move class, subclass of Event, of the elevator simulation. Simulates an elevator moving from one floor to another. 
 * Returns an openDoor Event of anybody wishes to leave the elevator or enter the non-full elevator on the current floor, 
 * or another move if neither of the former are true. 
 * 
 * @author Laivi Malamut-Salvaggio
 *
 */
public class Move extends Event{

	public Move(int tim, Elevator elev, Person perso, int floo) {
		super(tim, elev, perso, floo);
		priority = 90;
	}
/*
 * First moves the elevator up or down depending on which direction it is currently going, then returns an openDoor Event
 * by using an if-else statement to check whether someone is alighting, else someone wishes to board and elevator is 
 * not full, in that order. In none of the former are true, returns a Move Event.
 */
	@Override
	public Event act() {
		int prevFloor = elevator.getFloor();
		Event result = null;
		boolean openedDoors = false;
		if(elevator.isAscending()){
			elevator.goUp();
		}
		else{
			elevator.goDown();
		}
		if(elevator.isAlighting(floorNumber)){
			result = new openDoor(time + 1, elevator, null, floorNumber);
			openedDoors = true;
		}
		else if(elevator.isAscending()){
			if(floor.hasUp() && !elevator.isFull()){
				result = new openDoor(time + 1, elevator, floor.exitUp(), floorNumber);
				openedDoors = true;
			}
		}
		else{
			if(floor.hasDown() && !elevator.isFull()){
				result = new openDoor(time + 1, elevator, floor.exitDown(), floorNumber);
				openedDoors = true;
			}
		}
		if(elevator.isAscending() && !openedDoors){
			result = new Move(time + 2, elevator, null, floorNumber + 1);
		}
		if(!elevator.isAscending() && !openedDoors){
			result = new Move(time + 2, elevator, null, floorNumber - 1);
		}
		logMessage = "Time " + time + ": elevator " + elevator.getName() + " arrived at floor " + (elevator.getFloor() + 1) + " from floor " + (prevFloor + 1) + ".";
		return result;
	}

}
