
/* Written by Guang Yang studentID:961587
 * This class is used to update the movement of the bike
 */
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Bike extends MovingObjects{
	private static final int RIGHT_BORDER = 1000;
	private static final int LEFT_BORDER = 24;
// overload the constructor to decide which moveRight bus will go
	/** the constructor of bike 
	 * @param imageSrc this is the image path of the bike
	 * @param x this is the x position of the bike
	 * @param y this is the y position of the bike
	 * @param moveRight this is the direction of the bike
	 * @param speed this is the speed of the bike
	 * @throws SlickException 
	 */
	public Bike(String imageSrc, float x, float y, Boolean moveRight, double speed)
			throws SlickException {
		super(imageSrc, x, y, moveRight, speed);
	}
	@Override
	public void update(Input input, int delta) {
		// determine the moveRight and let the bus come back to the screen when it goes out.
		if(!super.getmoveRight()) {
			this.setX(this.getX() - delta * super.getSpeed());
			}
		if(super.getmoveRight()) {
			this.setX(this.getX() + delta * super.getSpeed());
			}
		// if bike arrive the border then change the direction
		if (this.getX() > RIGHT_BORDER) {
			super.setmoveRight(false);
		}
		if (this.getX() < LEFT_BORDER) {
			super.setmoveRight(true);
		}
	}
}
