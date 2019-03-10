/* Written by Guang Yang studentID:961587
 * This class is used for making frog act correctly.
 */

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * This is the class which controls the movement and behavior of player
 * @author yangguang
 *
 */
public class Player extends Sprite{	
	//this moveFlag is write for decide which side the player contact solid objects
	private int moveFlag = -1;
	private static final int MOVE = 48;
	private static final int TAIL_WIDTH = 48;
	private static final int TAIL_HEIGHT = 48;
	private static final int CONTACT_BOTTOM = 1;
	private static final int CONTACT_TOP = 2;
	private static final int CONTACT_RIGHT = 3;
	private static final int CONTACT_LEFT = 4;
	/**
	 * The constructor of players
	 * @param imageSrc Image path of player
	 * @param x x position of player
	 * @param y y position of player
	 * @throws SlickException
	 */
	public Player(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		int dx = 0,
			dy = 0;
		if (input.isKeyPressed(Input.KEY_UP)) {
			dy -= MOVE;
			moveFlag = CONTACT_BOTTOM;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			dy += MOVE;
			moveFlag = CONTACT_TOP;
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dx -= MOVE;
			moveFlag = CONTACT_RIGHT;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dx += MOVE;
			moveFlag = CONTACT_LEFT;
		}
		move(dx, dy);	
		//these lines is write to make sure player can't go out of screen
		if (this.getY() > App.SCREEN_HEIGHT-TAIL_WIDTH) {
			this.setY(App.SCREEN_HEIGHT-TAIL_WIDTH); 
		}
		if (this.getX() > App.SCREEN_WIDTH-TAIL_WIDTH/2) {
			this.setX(App.SCREEN_WIDTH-TAIL_WIDTH/2); 
		}
		if (this.getY() < TAIL_WIDTH) {
			this.setY(TAIL_WIDTH); 
		}
		if (this.getX() < TAIL_WIDTH/2) {
			this.setX(TAIL_WIDTH/2); 
		}
	}
	/**
	 * This method is write for making the player can't move through bulldozer.
	 * when player left intersect with bulldozer, player move with bulldozer
	 * @param other the bulldozer player contacts.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void contactBulldozer(Bulldozer other, int delta) {
		int dx = 0, dy = 0;
		BoundingBox playerBox = new BoundingBox(super.getX(), super.getY(), TAIL_WIDTH, TAIL_HEIGHT);
		BoundingBox BulldozerBox = new BoundingBox(other.getX(), other.getY(), TAIL_WIDTH, TAIL_HEIGHT);
		if(playerBox.LeftIntersects(BulldozerBox)) {
			this.setX((float) (this.getX() + delta * other.getSpeed()) );
		}
		else if(playerBox.intersects(BulldozerBox) && moveFlag == CONTACT_BOTTOM) {
			dy += MOVE;
		}
		else if(playerBox.intersects(BulldozerBox) && moveFlag == CONTACT_TOP) {
			dy -= MOVE;
		}
		else if(playerBox.intersects(BulldozerBox) && moveFlag == CONTACT_RIGHT) {
			dx += MOVE;
		}
		else if(playerBox.intersects(BulldozerBox) && moveFlag == CONTACT_LEFT) {
			dx -= MOVE;
		}
		move(dx, dy);
	}
	/**
	 * This method is write for making the player can't move through tree.
	 * @param other The tree which player contacts
	 */
	public void contactTree(Sprite other) {
		BoundingBox playerBox = new BoundingBox(super.getX(), super.getY(), TAIL_WIDTH, TAIL_HEIGHT);
		BoundingBox TreeBox = new BoundingBox(other.getX(), other.getY(), TAIL_WIDTH, TAIL_HEIGHT);
		if(playerBox.intersects(TreeBox)) {
			this.setY(this.getY() + TAIL_WIDTH);
		}
	}
	/**
	 * This method is write for making the player moves with the log.
	 * @param log The log which player contacts
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void contactLog(Log log, int delta) {
		BoundingBox playerBox = new BoundingBox(super.getX(), super.getY(), TAIL_WIDTH, TAIL_HEIGHT);
		BoundingBox LogBox = new BoundingBox(log.getX(), log.getY(), log.getWidth(), TAIL_HEIGHT);
			if(playerBox.xIntersects(LogBox)) {
				move(log.getSpeed() * delta * (log.getmoveRight() ? 1 : -1), 0);
			}
	}
	/**
	 * This method is write for making the player moves with the turtle.
	 * @param turtle The turtle which player contacts.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void contactTurtle(Turtle turtle, int delta) {
		BoundingBox playerBox = new BoundingBox(super.getX(), super.getY(), TAIL_WIDTH, TAIL_HEIGHT);
		BoundingBox LogBox = new BoundingBox(turtle.getX(), turtle.getY(), turtle.getWidth(), TAIL_HEIGHT);
		if(playerBox.xIntersects(LogBox)) {
			move(turtle.getSpeed() * delta * (turtle.getmoveRight() ? 1 : -1), 0);
		}
	}

	
}

