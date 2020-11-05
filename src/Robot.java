//package default package;
/**
 * Class to represet robots (non-player characters)
 * @author Felipe Varela
 */
public class Robot extends Character {
	protected int index;

	/**
	 * Stringifies a robot
	 *
	 * @return The stringified robot
	 */
	@Override
	public String toString() {
		return "R";
	}

	/**
	 * Creates a new robot at position (x, y)
	 *
	 * @param x The x position of the robot
	 * @param y The y position of the robot
	 */
	public Robot(int x, int y) {
		super(x, y);
	}

	/**
	 * Returns the index of the robot in the tableau
	 *
	 * @return The index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Updates the index of the robot in the tableau
	 *
	 * @param i The new index
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * TODO: Handles collisions of the robot with another cell. This is the default
	 * collideWith method. If something is in the cell, the robot is removed and the
	 * cell is hit. The content of the cell after the collision is returned.
	 *
	 * @param c The cell to collide with
	 * @param t The tableau
	 * @return The value to be placed in the cell after the collision
	 */
	public Cell collideWith(Cell c, Tableau t) {
		if (c == null) { // The cell is null. The robot will be able to move there
			return this;
		}

		else { // There is something in the cell and the "this" robot will collide with it.
			t.removeRobot(this.getIndex()); // Remove the robot from the Tableau
			Cell outcome = (c.getHit(t, this)); // Make collision happen
			return outcome;
		}
	}

	/**
	 * Handles the situation where a Cell is zapped (by a ray or an exploding
	 * robot). Zapping vaporizes (no rubble) everything except PermanentRock (which
	 * isn't effected. Returns true if and only if the value of the cell should be
	 * changed to null.
	 *
	 * @return Whether or not the cell should be nullified
	 */
	@Override
	public boolean getZapped() {
		return true;
	}

	/**
	 * TODO: Handles the situation where a Cell is hit by a (non-exploding) robot.
	 * Getting hit will leave rock or rubble if cell was rock or rubble, will leave
	 * rubble if cell was a robot. Will cause an explosion if cell is exploding
	 * robot. Returns the value that should be placed in cell after hit.
	 *
	 * @param by The thing doing the hitting
	 * @return New value for cell
	 */
	@Override
	public Cell getHit(Tableau t, Robot by) {
		t.removeRobot(this.getIndex());
		return new Obstruction(xPos, yPos);
	}

	/**
	 * Signals whether or not a cell can be removed (from Tableau's robot list).
	 * Robots return true; everything else false. The PC should be marked dead.
	 *
	 * @return false
	 */
	@Override
	public boolean removable() {
		return true;
	}

	/**
   * TODO: Finds a new position for the moving robot.  Robots move toward the
   * PC.  "Toward the PC" means that the robot moves in the direction of the
   * PC in at least one dimension, and if possible, it moves toward the PC in
   * both dimensions.
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
	  
	  return new Pair(toX, toY);
  }
  
  
}
