/* Written by Guang Yang studentID:961587
 * This class is used to draw extraLife on the screen and make it move like the assignment described
 */
import org.newdawn.slick.SlickException;

/**
 * This class is used to control the movement of extra life
 * @author yangguang
 *
 */
public class ExtraLife extends Sprite{
	private static int moveTimeCounter = 0;
	private float width;
	private boolean  extraLifemoveRight = true;
	private static final int TWO_SECOND = 2000;
	//this variable is write for count the distance between extra life and the log.
	private float relativeDistance = 0;
	/**
	 * This is the constructor of the extra life
	 * @param imageSrc image path of extra life
	 * @param x x location of extra life
	 * @param y y location of extra life
	 * @param log the log which extra life is located in
	 * @throws SlickException
	 */
	public  ExtraLife(String imageSrc, float x, float y, Log log) throws SlickException {
		super(imageSrc, x, y);
		width = log.getWidth();
	}
	/**
	 * This method is used to control the movement of the frog
	 * 1.Move with the log
	 * 2.When log arrive the border, it reappears in the other side. Extra life would reappear on the log on the other side and move in same way.
	 * 3.Every 2 second move 48 pixel
	 * @param log This is the log the extra life located in
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void contactLog(Log log, int delta) {
		
		this.setX(log.getX() + relativeDistance);
		this.setY(log.getY());
			//moveTimeCounter is write for count 2s 
			moveTimeCounter +=delta;
			//System.out.println(moveTimeCounter);
			//move extra life 
			if(moveTimeCounter > TWO_SECOND) {			
				move(log);
				moveTimeCounter = 0;
			}
	}
	/**
	 * This is the setter of move time counter
	 * @param time The time we want to set
	 */
	public static void setTime(int time) {
		moveTimeCounter = time;
	}

	/**
	 * this method is used for move the extra life 48 pixels,
	 * when it arrive the border of the log, change the opposite direction. 
	 * @param log The log where the extra life choose to generate 
	 */
	public void move(Log log) {
		//make sure extra life won't go out of the log
		if((this.getX()>log.getX() + width/2 - World.TILE_SIZE/2)) {
			extraLifemoveRight = false;
		}
		else if(this.getX()<log.getX() - width/2 + World.TILE_SIZE/2) {
			extraLifemoveRight = true;
		}
		//move the extra life to right direction
		if(extraLifemoveRight) {
			relativeDistance += World.TILE_SIZE;
		}
		else {
			relativeDistance -= World.TILE_SIZE;
		}
	
		
	}
	

}
