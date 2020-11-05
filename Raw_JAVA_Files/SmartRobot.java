package hw4;
/**
 * Class to represent smart robots.  Smart robots will not collide with
 * rubble or other robots (however, other robots can collide with smart
 * robots.
 * @author Felipe Varela
 */
public class SmartRobot extends Robot {

  /**
   * Stringifies a smart robot
   *
   * @return The stringified robot
   */
  @Override
  public String toString()
  {
    return "S";
  }

  /**
   * Constructs a new smart robot at position (x, y)
   *
   * @param x The x position of the robot
   * @param y The y position of the robot
   */
  public SmartRobot(int x, int y)
  {
    super(x, y);
  }

  /**
   * TODO: Smart robots move toward the PC is at least one dimension, both if
   * possible, but only if there exists a move which doesn't result in a
   * collision.  A smart robot will never collide with obstrutions or other
   * robots.  It can get stuck behind an obstruction (or, optionally, you can
   * implement some more intelligent pathfinding around obsructions, but that
   * is harder to code, and make an already very hard game harder, as well).
   *
   * @param t The tableau
   * @return The new position
   */
  public Pair moveTo(Tableau t) {
	  //Instance variables (makes code cleaner)
	  PlayerCharacter pc = t.getPC();
	  int toX = this.getX();
	  int toY = this.getY();
	  
	  //Checking if it is above, below or equal to the player on the y axis
	  if(toY< pc.getY()) { //The robot is bellow the player
		  toY++; //Move the robot up
	  }
	  else if(toY > pc.getY()) { //The robot is above the player
		  toY--; //Move the robot down
	  }
	  
	  //Checking if it is left, right or equal to the player on the x axis
	  if(toX < pc.getX()) { // The robot is to the left of the player
		  toX++;
	  }
	  else if(toX > pc.getX()) { //The robot is to the right of the player
		  toX--;
	  }
	  
	  //Check if it is a valid movement
	  if(t.getCell(toX, toY) != null) {
		  return new Pair(this.getX(), this.getY()); //Do not move
	  }
	  else {
		  return new Pair(toX, toY); //Move
	  }
	  
  } 
}
