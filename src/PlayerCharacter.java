//package default package;

import java.util.Scanner;

/**
 * TODO: The class implements the player character (PC).
 * 
 * @author Felipe Varela
 */
public class PlayerCharacter extends Character {
	/**
	 * Player is alive or not
	 */
	private boolean alive;

	/**
	 * TODO: Constructs a new PC at position (x, y) in the tableau
	 *
	 * @param x The PCs initial x position.
	 * @param y The PCs initial y position.
	 */
	public PlayerCharacter(int x, int y) {
		super(x, y); // Super class is Character
		alive = true; // Player is alive at first
	}

	/**
	 * TODO: Returns true if and only if the PC is still alive.
	 *
	 * @return Whether or not the PC lives
	 */
	public boolean isAlive() {

		return alive;
	}

	/**
	 * TODO: Marks the PC as no longer being alive
	 */
	public void die() {
		alive = false;
	}

	/**
	 * Stringifies the PC
	 *
	 * @return The stringified PC
	 */
	@Override
	public String toString() {
		return "@";
	}

	/**
	 * This method should never be called.
	 *
	 * @param c Never called
	 * @param t Never called
	 * @return Never called
	 */
	public Cell collideWith(Cell c, Tableau t) {
		return this;
	}

	/**
	 * Handles player I/O. Returns the new (possibly unchanged) position of the PC.
	 *
	 * @param t The tableau
	 * @return The new position of the PC
	 */
	public Pair moveTo(Tableau t) {
		Scanner sc = new Scanner(System.in);
		boolean goodMove = false;
		int toX, toY;
		do {
			toX = xPos;
			toY = yPos;

			System.out.print("; your move: ");

			switch (sc.next().charAt(0)) {
			case 'h':
				toX--;
				break;
			case 'j':
				toY++;
				break;
			case 'k':
				toY--;
				break;
			case 'l':
				toX++;
				break;
			case 'y':
				toX--;
				toY--;
				break;
			case 'u':
				toX++;
				toY--;
				break;
			case 'b':
				toX--;
				toY++;
				break;
			case 'n':
				toX++;
				toY++;
				break;
			case '.':
				goodMove = true;
				break;
			case 'z':
				if (t.hasZap()) {
					doZap(t);
					goodMove = true;
				}
				break;
			case 'w':
				goodMove = t.startWait();
				break;
			case 'q':
				System.exit(0);
				break;
			default:
			}

			if (toX >= 0 && toX < t.getX() && toY >= 0 && toY < t.getY() && t.getCell(toX, toY) == null) {
				goodMove = true;
			}

			if (!goodMove) {
				System.out.print(t + " Invalid move");
			}
		} while (!goodMove);

		return new Pair(toX, toY);
	}

	/**
	 * TODO: Gets a valid direction from the player and zaps a ray in that
	 * direction. Rays destroy all robots and rubble (but not rock) in a straight
	 * line in a single direction from the PC to the edge of the tableau
	 *
	 * @param The tableau
	 */
	void doZap(Tableau t) {
		// Handles valid direction input from player
		Scanner sc = new Scanner(System.in);
		boolean goodMove = false;
		int toX, toY;
		do {
			toX = xPos;
			toY = yPos;

			System.out.print("; your move: ");
			switch (sc.next().charAt(0)) {
			case 'h':
				toX--;
				break;
			case 'j':
				toY++;
				break;
			case 'k':
				toY--;
				break;
			case 'l':
				toX++;
				break;
			case 'y':
				toX--;
				toY--;
				break;
			case 'u':
				toX++;
				toY--;
				break;
			case 'b':
				toX--;
				toY++;
				break;
			case 'n':
				toX++;
				toY++;
				break;
			/*
			 * Do not need this section of doZap. Not possible to zap, wait or stay in place
			 * while inside doZap. will keep it in here in case it is needed later in the
			 * specchecker case '.': goodMove = true; break;
			 * 
			 * case 'z': if (t.hasZap()) { doZap(t); goodMove = true; } break;
			 * 
			 * case 'w': goodMove = t.startWait(); break;
			 */
			case 'q':
				System.exit(0);
				break;
			default:
			}

			if (toX >= 0 && toX < t.getX() && toY >= 0 && toY < t.getY() && t.getCell(toX, toY) == null) {
				goodMove = true;
			}

			if (!goodMove) {
				System.out.print(t + " Invalid move");
			}
		} while (!goodMove);

		// Handles the zap from player up until the edge of the map given a direction.
		while ((toX >= 0 && toY >= 0) && (toX < t.getX() && toY < t.getY())) {
			if (toX < xPos && toY == yPos) { // Zap left
				// If the cell can be zapped, if the content of the cell is not null
				if (t.getCell(toX, toY) != null && t.getCell(toX, toY).getZapped() == true) {
					t.nullifyCell(toX, toY);
				}
				toX--;
			} else if (toX == xPos && toY > yPos) { // Zap down
				// If the cell can be zapped, if the content of the cell is not null
				if (t.getCell(toX, toY) != null && t.getCell(toX, toY).getZapped() == true) {
					t.nullifyCell(toX, toY);
				}
				toY++;
			} else if (toX == xPos && toY < yPos) { // Zap up
				// If the cell can be zapped, if the content of the cell is not null
				if (t.getCell(toX, toY) != null && t.getCell(toX, toY).getZapped() == true) {
					t.nullifyCell(toX, toY);
				}
				toY--;
			} else if (toX > xPos && toY == yPos) { // Zap right
				// If the cell can be zapped, if the content of the cell is not null
				if (t.getCell(toX, toY) != null && t.getCell(toX, toY).getZapped() == true) {
					t.nullifyCell(toX, toY);
				}
				toX++;
			} else if (toX < xPos && toY < yPos) { // Zap up-left
				// If the cell can be zapped, if the content of the cell is not null
				if (t.getCell(toX, toY) != null && t.getCell(toX, toY).getZapped() == true) {
					t.nullifyCell(toX, toY);
				}
				toX--;
				toY--;
			} else if (toX > xPos && toY < yPos) { // Zap up-right
				// If the cell can be zapped, if the content of the cell is not null
				if (t.getCell(toX, toY) != null && t.getCell(toX, toY).getZapped() == true) {
					t.nullifyCell(toX, toY);
				}
				toX++;
				toY--;
			} else if (toX < xPos && toY > yPos) { // Zap down-left
				// If the cell can be zapped, if the content of the cell is not null
				if (t.getCell(toX, toY) != null && t.getCell(toX, toY).getZapped() == true) {
					t.nullifyCell(toX, toY);
				}
				toX--;
				toY++;
			} else if (toX > xPos && toY > yPos) { // Zap down-right
				// If the cell can be zapped, if the content of the cell is not null
				if (t.getCell(toX, toY) != null && t.getCell(toX, toY).getZapped() == true) {
					t.nullifyCell(toX, toY);
				}
				toX++;
				toY++;
			}

		}
		// Consumes the zap charge
		t.useZap();
	}

	/**
	 * TODO Handles the situation where a Cell is zapped (by a ray or an exploding
	 * robot). Zapping vaporizes (no rubble) everything except PermanentRock (which
	 * isn't effected. Returns true if and only if the value of the cell should be
	 * changed to null. The PC is killed by a zap.
	 *
	 * @return Whether or not the cell should be nullified
	 */
	@Override
	public boolean getZapped() {
		die();
		return true; // Yes, the player is "vaporized"
	}

	/**
	 * Handles the situation where a Cell is hit by a (non-exploding) robot. Getting
	 * hit will leave rock or rubble if cell was rock or rubble, will leave rubble
	 * if cell was a robot. Will cause an explosion if cell is exploding robot.
	 * Returns the value that should be placed in cell after hit, and marks the PC
	 * as dead.
	 *
	 * @param t  The tableau
	 * @param by The thing doing the hitting
	 * @return New value for cell
	 */
	@Override
	public Cell getHit(Tableau t, Robot by) {
		alive = false;

		return by;
	}

	/**
	 * Signals whether or not a cell can be removed (from Tableau's robot list).
	 * Robots return true; everything else false. The PC should be marked dead.
	 *
	 * @return false
	 */
	@Override
	public boolean removable() {
		alive = false;

		return false;
	}
}
