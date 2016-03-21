import java.util.Random;

/**
 * The Person class, part of the elevator simulation. used by Controller class, represents a person who is either waiting on a
 * floor for an elevator, in an elevator, or in the process of exiting or entering an elevator. 
 * 
 * @author Laivi Malamut-Salvaggio
 *
 */
public class Person {
	private int startTime;
	private int startFloor;
	private int endFloor;
	private boolean ascending = true;
	private String name = null;
	
	/**
	 * initializes a person to a random start floor and end floor.
	 * 
	 * @param floorAmount The amount of floors the can be on/ end up on
	 */
	public Person(int floorAmount){
		Random rand = new Random();
		while(startFloor == endFloor){
			startFloor = rand.nextInt(floorAmount);
			endFloor = rand.nextInt(floorAmount);
		}
		
		setGoing();
	}
	
	public Person(int start, int end){
		startFloor = start;
		endFloor = end;
		setGoing();
		
	}
	
	private void setGoing(){
		if(endFloor <startFloor){
			ascending = false;
		}
	}
	
	public boolean goingUp(){
		return ascending;
	}
	
	public int getStartFloor(){
		return startFloor;
	}
	
	public int getEndFloor(){
		return endFloor;
	}
	
	public void setName(String nam){
		name = nam;
	}
	
	public void setStartTime(int tim){
		startTime = tim;
	}
	
	public int getStartTime(){
		return startTime;
	}
	
	public String getName(){
		return name;
	}

}
