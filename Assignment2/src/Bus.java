/* Written by Guang Yang studentID:961587
 * This class is used to update the movement of the bus and racecar
 */
import org.newdawn.slick.SlickException;
/**
 *  To initialize the bus and racecar
 * @author yangguang
 *
 */
public class Bus extends MovingObjects{
	/** the constructor of bus 
	 * @param imageSrc this is the image path of the bus or racecar
	 * @param x this is the x position of the bus or racecar
	 * @param y this is the y position of the bus or racecar
	 * @param moveRight this is the direction of the bus or racecar
	 * @param speed this is the speed of the bus or racecar
	 * @throws SlickException 
	 */
	public Bus(String imageSrc, float x, float y, Boolean moveRight, double speed) throws SlickException {
		super(imageSrc, x, y, moveRight, speed);
	}
}

