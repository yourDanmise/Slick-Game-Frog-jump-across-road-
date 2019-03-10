/* Written by Guang Yang studentID:961587
 * This class is used to draw water on the screen
 */
import org.newdawn.slick.SlickException;
/**
 * this class is used for initialize the water
 * @author yangguang
 *
 */
public class Water extends Sprite{
	/**
	 * this is the constructor of water
	 * @param imageSrc this is the image path of the water
	 * @param x this is the x position of the water
	 * @param y this is the y position of the water
	 * @throws SlickException
	 */
	public Water(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
	}
}
