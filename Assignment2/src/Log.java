/* Written by Guang Yang studentID:961587
 * This class is used to update the movement of the log
 */
import org.newdawn.slick.SlickException;
/**
 *  To initialize the log
 * @author yangguang
 *
 */
public class Log extends MovingObjects{
	/** the constructor of log 
	 * @param imageSrc this is the image path of the log
	 * @param x this is the x position of the log
	 * @param y this is the y position of the log
	 * @param moveRight this is the direction of the log
	 * @param speed this is the speed of the log
	 * @throws SlickException 
	 */
	public Log(String imageSrc, float x, float y, Boolean moveRight, double speed) throws SlickException {
		super(imageSrc, x, y, moveRight, speed);
		// TODO Auto-generated constructor stub
	}

}


