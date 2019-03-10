/* Written by Guang Yang studentID:961587
 * This class is used to update the movement of the turtle
 * 
 */
import org.newdawn.slick.SlickException;
/**
 *  To initialize the turtle
 * @author yangguang
 *
 */
public class Turtle extends MovingObjects{
	/** the constructor of turtle 
	 * @param imageSrc this is the image path of the turtle
	 * @param x this is the x position of the turtle
	 * @param y this is the y position of the turtle
	 * @param moveRight this is the direction of the turtle
	 * @param speed this is the speed of the turtle
	 * @throws SlickException 
	 */
	public Turtle(String imageSrc, float x, float y, Boolean moveRight, double speed) throws SlickException {
		super(imageSrc, x, y, moveRight, speed);
		// TODO Auto-generated constructor stub
	}

}


