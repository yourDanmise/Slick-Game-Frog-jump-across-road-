/* Written by Guang Yang studentID:961587
 * This class is the parent class of all the other specific class
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * This is an abstract parent class of all the other objects,
 * and his children can inheritance the normal render and update method from this class
 * @author yangguang
 *
 */
public abstract class Sprite {
	private Image sprite;
	private float x    ;
	private float y;
	/**
	 * This is the constructor of sprite
	 * @param imageSrc This is the image of the sprite
	 * @param x This is the X location of the sprite
	 * @param y This is the Y location of the sprite
	 * @throws SlickException
	 */
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		// Decide which picture will be shown on the screen
		sprite =new Image(imageSrc);
		//initialize the place of Sprite
		this.x = x;
		this.y = y;
	}
	/**
	 * getter of the x
	 * @return x position
	 */
	public float getX() {
		return this.x;
	}
	/**
	 * getter of the y
	 * @return y position
	 */
	public float getY() {
		return this.y;
	}
	/**
	 * setter of the x
	 * @param x the new x position of sprite
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * setter of the y
	 * @param y the new y position of sprite
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * Set both x and y
	 * @param dx Move distance of x
	 * @param dy Move distance of y
	 */
	public final void move(float dx, float dy) {
		setX(x + dx);
		setY(y + dy);
	}
	//I just put the player's update method there.
	//Other class method will be inherited and overridden
	/**
	 * This is write for update the sprite
	 * @param input input of the keyboard 
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(Input input, int delta) {

	}
	/**
	 * This is the getter of the width of the image
	 * @return float width
	 */
	public float getWidth() {
		return sprite.getWidth();
	}
	/**
	 * Normal render method
	 */
	public void render() {
		// This should be pretty simple.
		if(!sprite.equals(null)) {
		sprite.drawCentered(x,y);
		}
	}
	

	
}
