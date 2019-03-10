/* Written by Guang Yang studentID:961587
 * This class is used to update the movement of the moving objects
 */
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * This is the father class of vehicles/turtle/log
 * @author yangguang
 *
 */
public abstract class MovingObjects extends Sprite{
	private double speed;
	private Boolean moveRight;
	/**
	 * This is the constructor of MovingObjects, determine the direction and speed of objects
	 * @param imageSrc image of moving object
	 * @param x x location of moving object
	 * @param y y location of moving object
	 * @param moveRight
	 * @param speed
	 * @throws SlickException
	 */
	// overload the constructor to decide which moveRight object will go
		public MovingObjects(String imageSrc, float x, float y, Boolean moveRight, double speed) throws SlickException {
			super(imageSrc, x, y);
			this.moveRight = moveRight;
			this.speed = speed;
		}
	/**
	 * initialize the origin place of object
	 * @return x position of objects
	 */
	private final float getInitialX() {
		return getmoveRight() ? -this.getWidth() / 2
						 : App.SCREEN_WIDTH + this.getWidth() / 2;
	}
	/**
	 * getter of speed
	 * @return speed
	 */
	public float getSpeed() {
		return (float) speed;
	}
	/**
	 * getter of moving direction
	 * @return
	 */
	public Boolean getmoveRight() {
		return this.moveRight;
	}
	/**
	 * This is the setter of moving direction
	 * @param moveRight determine the direction of moving objects
	 */
	public void setmoveRight(Boolean moveRight) {
		this.moveRight = moveRight;
	}
	@Override
	public void update(Input input, int delta) {
		// determine the moveRight and let the objects come back to the screen when it goes out.
		move((float)speed * delta * (moveRight ? 1 : -1), 0);
		
		// check if the vehicle has moved off the screen
		if (getX() > App.SCREEN_WIDTH + this.getWidth() / 2 || getX() < -this.getWidth() / 2
				 || getY() > App.SCREEN_HEIGHT + this.getWidth() / 2 || getY() < -this.getWidth() / 2) {
					setX(getInitialX());
				}
	}
}

