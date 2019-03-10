/* Written by Guang Yang studentID:961587
 * This class is used to update the movement of the bulldozer
 */
import org.newdawn.slick.SlickException;
/** To initialize the bulldozer
 * @author yangguang
 */
public class Bulldozer extends MovingObjects{
	/** the constructor of bulldozer 
	 * @param imageSrc this is the image path of the bulldozer
	 * @param x this is the x position of the bulldozer
	 * @param y this is the y position of the bulldozer
	 * @param moveRight this is the direction of the bulldozer
	 * @param speed this is the speed of the bulldozer
	 * @throws SlickException 
	 */
	public Bulldozer(String imageSrc, float x, float y, Boolean moveRight, double speed) throws SlickException {
		super(imageSrc, x, y, moveRight, speed);
	}
	
}
