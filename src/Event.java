/**
 * The Event class for the elevator simulation. Is the super class for any event which occurs in the controller class.
 * 
 * @author Laivi Malamut-Salvaggio
 *
 */

public abstract class Event implements Comparable<Event> {
	protected Elevator elevator;
	protected Person person;
	protected Floor floor;
	protected int floorNumber;
	protected int time;
	protected int priority;
	protected int waitTime;
	protected String logMessage;
	
	public Event(int tim, Elevator elev, Person perso, int floo){
		person = perso;
		time = tim;
		elevator = elev;
		floorNumber = floo;
	}
	/*
	 * Compares based on which object has the smaller time or, if the time is the same, has the larger priority.
	 * 
	 */
	public int compareTo(Event even){
		int result = time - even.getTime();
		if (result == 0){
			result = even.getPriority() - priority;
		}
		return result;
	}
	
	public Elevator getEleveator(){
		return elevator;
	}
	
	public int getFloorNumber(){
		return floorNumber;
	}
	
	public void setFloor(Floor floo){
		floor = floo;
	}
	
	public int getTime(){
		return time;
	}
	
	public int getWaitTime(){
		return waitTime;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public String getLogMessage(){
		return logMessage;
	}
	
	public Person getPerson(){
		return person;
	}
	 
	/*
	 * The main function for every Event object.
	 */
	public abstract Event act();

}
