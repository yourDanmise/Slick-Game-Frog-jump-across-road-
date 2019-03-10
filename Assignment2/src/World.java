/* Written by Guang Yang studentID:961587
 * This class is used to initialize the whole world in the Game
 * include constructor, update and render method
 */

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;
/**
 * this class is the most important class in this program
 * 1. in constructor, it can read the line in the input file
 * 2. in constructor, it can create new objects in the right place with right picture/direction/speed
 * 3. in update, it mainly control the acts when player the objects collides with the objects collides
 * 4. in render, it just normally render all the arraylist and objects
 * @author yangguang
 */
public class World {
	/**
	 * TILE_SIZE is a public attribute which can represent 48 pixels one tile
	 */
	public static final int TILE_SIZE = 48;
	/**
	 * SPRITE_WIDTH is a public attribute which can represent 48 pixels one tile
	 */
	public static final float SPRITE_WIDTH = 48;
	/**
	 *  SPRITE_HEIGHT is a public attribute which can represent 48 pixels one tile
	 */
	public static final float SPRITE_HEIGHT = 48;
	private static Player player;
	private static final String COMMA = ",";
	//this string array is used for storing the data read from the file
	private static String[] splitText;
	//Storing every objects read from the file
	private ArrayList<Sprite> object = new ArrayList<Sprite>();
	//Storing the number of lives
	private static ArrayList<Life> life = new ArrayList<Life>();
	//If frog arrive the hole, this arraylist will show image in the hole
	private ArrayList<Hole> frogInHole = new ArrayList<Hole>();
	//Recording the number of the hole which is been reached
	private ArrayList<Integer> contactHoleRecord = new ArrayList<Integer>();
	private ArrayList<ExtraLife> extraLife = new ArrayList<ExtraLife>();
	public static final int RANDOM_RANGE_TIME = 10;
	public static final int RANDOM_BASE_TIME = 25;
	private int randomTime=(int)(RANDOM_RANGE_TIME*Math.random()+RANDOM_BASE_TIME);
	private int bornLog;
	private static int deltaSum = 0;
	private static int disapearTimeCounter = 0;
	private static int turtleAppearTime = 0;
	private static int turtleDisappearTime = 0;
	private static boolean turtleFlag = true;
	private static final float ORIGIN_PLACE_X = 512;
	private static final float ORIGIN_PLACE_Y = 720;
	
	private static final String WATER = "water";
	private static final String GRASS = "grass";
	private static final String TREE = "tree";
	private static final String BUS = "bus";
	private static final String BULLDOZER = "bulldozer";
	private static final String LOG = "log";
	private static final String LONGLOG = "longLog";
	private static final String  BIKE= "bike";
	private static final String RACECAR = "racecar";
	private static final String TURTLE = "turtle";
	
	private static final String WATER_PATH = "assets/water.png";
	private static final String GRASS_PATH = "assets/grass.png";
	private static final String TREE_PATH = "assets/tree.png";
	private static final String BUS_PATH = "assets/bus.png";
	private static final String BULLDOZER_PATH = "assets/bulldozer.png";
	private static final String LOG_PATH = "assets/log.png";
	private static final String LONGLOG_PATH = "assets/longlog.png";
	private static final String BIKE_PATH = "assets/bike.png";
	private static final String RACECAR_PATH = "assets/racecar.png";
	private static final String TURTLE_PATH = "assets/turtles.png";
	private static final String PLAYER_PATH = "assets/frog.png";
	private static final String LIFE_PATH = "assets/lives.png";
	
	private static final Double BUS_SPEED = 0.15;
	private static final Double BULLDOZER_SPEED = 0.05;
	private static final Double LOG_SPEED = 0.1;
	private static final Double LONGLOG_SPEED = 0.07;
	private static final Double  BIKE_SPEED = 0.2;
	private static final Double RACECAR_SPEED = 0.5;
	private static final Double TURTLE_SPEED = 0.085;
	
	private static final int EXTRA_LIFE_DISAPPEAR_SECOND = 14;
	private static final int INIT_LIFE_NUMBER = 3;
	private static final int INIT_LIFE_PLACE = 24;
	private static final int LIFE_OFFSET = 32;
	private static final int LIFE_Y = 744;
	private static final int THOUSAND = 1000;
	private static final int RIGHT_BORDER = 990;
	private static final int TURTLE_DISAPPEAR_MILLISECOND = 2000;
	private static final int TURTLE_APPEAR_MILLISECOND = 7000;
	private static final int HOLE_NUMBER = 5;
	private static final int NONE = -1;
	private static final int HOLE_HEIGHT = 48;
	private static final int HOLE_WIDTH = 144;
	private static final int HOLE_Y = 48;
	private static final int HOLE_OFFSET = 192;
	private static final int INIT_HOLE_PLACE = 120;
	
	
	/**
	 * this is the constructor of world, which can read files and initialize every 
	 * object in the screen.
	 * @param inputFile This is the input file path
	 * @param flag this variable is used for initialize the life, so that stage2 can inheritance the life of stage1 
	 * @throws SlickException
	 * @throws IOException
	 */
	public World(String inputFile, boolean flag) throws SlickException, IOException {
		  try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		            String text;
		            //read the file and create instance in ArrayList<Sprite> object
		            while ((text = br.readLine()) != null) {
		            	splitText = text.split(COMMA);
		            	if(splitText[0].equals(WATER)) {
		            		object.add(new Water(WATER_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2])));
		            	}
		            	else if(splitText[0].equals(GRASS)) {
		            		object.add(new Grass(GRASS_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2])));
		            	}
		            	else if(splitText[0].equals(TREE)) {
		            		object.add(new Tree(TREE_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2])));
		            	}
		            	else if(splitText[0].equals(BUS)) {
		            		object.add(new Bus(BUS_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2]), Boolean.parseBoolean(splitText[3]), BUS_SPEED));
		            	}
		            	else if(splitText[0].equals(BULLDOZER)) {
		            		object.add(new Bulldozer(BULLDOZER_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2]), Boolean.parseBoolean(splitText[3]), BULLDOZER_SPEED));
		            	}
		            	//Combine the Log class and LongLog class as one class will be more simple
		            	else if(splitText[0].equals(LOG)) {
		            		object.add(new Log(LOG_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2]), Boolean.parseBoolean(splitText[3]), LOG_SPEED));
		            	}
		            	else if(splitText[0].equals(LONGLOG)) {
		            		object.add(new Log(LONGLOG_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2]), Boolean.parseBoolean(splitText[3]), LONGLOG_SPEED));
		            	}
		            	else if(splitText[0].equals(BIKE)) {
		            		object.add(new Bike(BIKE_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2]), Boolean.parseBoolean(splitText[3]), BIKE_SPEED));
		            	}
		            	else if(splitText[0].equals(RACECAR)) {
		            		object.add(new Bus(RACECAR_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2]), Boolean.parseBoolean(splitText[3]), RACECAR_SPEED));
		            	}
		            	else if(splitText[0].equals(TURTLE)) {
		            		object.add(new Turtle(TURTLE_PATH, Float.parseFloat(splitText[1]),
		            				Float.parseFloat(splitText[2]), Boolean.parseBoolean(splitText[3]), TURTLE_SPEED));
		            	} 	
		            }
		      }	            
		  
		player = new Player(PLAYER_PATH, ORIGIN_PLACE_X, ORIGIN_PLACE_Y);
		//add life in arraylist, make sure stage2 can inheritance the life of stage1 
			if(flag) {
			for(int i = 0; i < INIT_LIFE_NUMBER; i++) {
				life.add(new Life(LIFE_PATH, INIT_LIFE_PLACE+i*LIFE_OFFSET, LIFE_Y));
			}
		}
	//System.out.println(randomTime);
	}	
	/**
	 * This is the most long and important method in this program,
	 * Making player behaves correctly when they contacts with all the objects
	 * @param input Update the location of player by the keyboard input  
	 * @param delta Time passed since last frame (milliseconds).
	 * @throws SlickException
	 */
	public void update(Input input, int delta) throws SlickException {
		//deltaSum is write for count random time 
		deltaSum += delta;
		//disapearTimeCounter is write for count 14s 
		disapearTimeCounter += delta;
		//count turtle appear time
		turtleAppearTime += delta;
		//count when turtle have enough time under water and want to go up
		turtleDisappearTime += delta;
		//delay random time to create the extra life
		if(deltaSum > THOUSAND * randomTime) {
			//random select a sprite
			int randomSpriteNumber =(int)(Math.random()*(object.size()-1));
			//If this sprite is log, then init extralife there
			if(object.get(randomSpriteNumber) instanceof Log ) {
				bornLog = randomSpriteNumber;
			extraLife.add(new ExtraLife("assets/ExtraLife.png", 
					object.get(randomSpriteNumber).getX(), object.get(randomSpriteNumber).getY(),
					(Log)object.get(randomSpriteNumber)));
			deltaSum = 0;
			}
			//Else searching in the object to find next log
			else {
				randomSpriteNumber++;
				if(randomSpriteNumber > object.size()-1) {
					randomSpriteNumber = 0;
				}
			}	
		}
	    //after 14s the extraLife disappear
		if(disapearTimeCounter > randomTime * THOUSAND +
				EXTRA_LIFE_DISAPPEAR_SECOND * THOUSAND) {
			extraLife.remove(extraLife.size()-1);
			ExtraLife.setTime(0);
			deltaSum = 0;
			disapearTimeCounter = 0;
			randomTime=(int)(RANDOM_RANGE_TIME*Math.random()+RANDOM_BASE_TIME);
//			System.out.println(randomTime);
//			System.out.println("r");
		}

		if(!extraLife.isEmpty()) {
			//if player contact extra life, life+, extra life disappear
			if(contactSprite(extraLife.get(0))) {
				extraLife.remove(extraLife.size()-1);
				ExtraLife.setTime(0);
				life.add(new Life("assets/lives.png", INIT_LIFE_PLACE+life.size()*LIFE_OFFSET, LIFE_Y));
				deltaSum = 0;
				disapearTimeCounter = 0;
				randomTime=(int)(RANDOM_RANGE_TIME*Math.random()+RANDOM_BASE_TIME);
			//	System.out.println(randomTime);
			}
		}
		//detect if game is over
		if(life.isEmpty()) {
			System.exit(0);
		}
		
		//update player's move
		player.update(input, delta);
		
		//update every instance, and thier method when contact with player
		for(Sprite s: object) {
			//If player is hit by normal vehicles, players will lose life
			if(s instanceof Bus || s instanceof Bike) {
			  if(contactSprite(s)) {
				life.remove(life.size()-1);
				player.setX(ORIGIN_PLACE_X);
				player.setY(ORIGIN_PLACE_Y);
			  }
			}
			if(s instanceof Bulldozer) {
				//if the player was pushed to the right border by the bulldozer, life-1
				 if(LfetContactSprite(s) && player.getX() > RIGHT_BORDER) {
						life.remove(life.size()-1);
						player.setX(ORIGIN_PLACE_X);
						player.setY(ORIGIN_PLACE_Y);
					  }
				 //This is write in the player class which can make player move with the speed of bulldozer
				player.contactBulldozer((Bulldozer)s, delta);
				
			}
			if(s instanceof Tree) {
				//This is write in the player class which can make player can't move through trees
				player.contactTree(s);
			}
			
			if(s instanceof Log) {			
				if(contactSprite(s)) {
					//Making the player has same speed with log
					player.contactLog((Log)s, delta);
				}
			}
			
			//turtleFlag is wrote to clarify whether the turtle is on water.
			//If it's true, turtle is on the water, else, turtle is sinking
			if(s instanceof Turtle) {	
				if(turtleAppearTime > TURTLE_APPEAR_MILLISECOND) {
					turtleFlag = false;
				}
				if(turtleDisappearTime > TURTLE_APPEAR_MILLISECOND + TURTLE_DISAPPEAR_MILLISECOND) {
					turtleAppearTime = 0;
					turtleFlag = true;
					turtleDisappearTime = 0;
				}
				if(contactSprite(s) && (turtleFlag == true)) {
					player.contactTurtle((Turtle)s, delta);
				}
			}
			//update all the sprites
			s.update(input, delta);
		}
		//make extraLife move in right mode.
		if(!extraLife.isEmpty()) {
			extraLife.get(0).contactLog((Log)object.get(bornLog), delta);
			}
		//This is written to determine whether the player have already reached the hole
		//if this hole is reached, player will lose life
		//else, add the number of this hole to the contactHoleRecord
		if(contactHole() != NONE) {
			 if(contactHoleRecord.contains(contactHole())) {
				 life.remove(life.size()-1);
			 }
			 else {
			frogInHole.add(new Hole(PLAYER_PATH, INIT_HOLE_PLACE + contactHole()*HOLE_OFFSET, HOLE_HEIGHT));
			contactHoleRecord.add(contactHole());
			}			
			player.setX(ORIGIN_PLACE_X);
			player.setY(ORIGIN_PLACE_Y);	
		}
		//This is wrote to determine whether all the hole is reached
		//If all 5 holes are reached and now is in stage0, change the world to stage1
		//If all 5 holes are reached and now is in stage1, exit the game
		if(contactHoleRecord.size() == HOLE_NUMBER ) {
			if(App.getFlag()) {
				App.setFlag(false);
			    deltaSum = 0;
				disapearTimeCounter = 0;
				turtleAppearTime = 0;
				turtleDisappearTime = 0;
				player.setX(ORIGIN_PLACE_X);
				player.setY(ORIGIN_PLACE_Y);	
			}
			else {
				System.exit(0);		
			}
		}
		checkOnLogOrOnTurtle();
	}
	/**
	 * this method is wrote to determine whether the player contact with bulldozer's left side,
	 * if contacts, return true, else return false.
	 * @param other The sprite to determine whether the player contact with it's left side
	 * @return Boolean
	 */
	private boolean LfetContactSprite(Sprite other) {
		BoundingBox playerBox = new BoundingBox(player.getX(), player.getY(), SPRITE_WIDTH, SPRITE_HEIGHT);
		BoundingBox otherBox = new BoundingBox(other.getX(), other.getY(), other.getWidth(), SPRITE_HEIGHT);
		if(playerBox.LeftIntersects(otherBox)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * this method is wrote to determine whether the player contact with other sprite,
	 * if contacts, return true, else return false.
	 * @param other The sprite to determine whether the player contact with
	 * @return Boolean 
	 */
	public boolean contactSprite(Sprite other) {
		BoundingBox playerBox = new BoundingBox(player.getX(), player.getY(), SPRITE_WIDTH, SPRITE_HEIGHT);
		BoundingBox otherBox = new BoundingBox(other.getX(), other.getY(), other.getWidth(), SPRITE_HEIGHT);
		if(playerBox.intersects(otherBox)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * create a hole box to make contact
	 * @return i i is an integer to record which hole the player is contacting,
	 * if none of the hole is contacted by the player, return -1.
	 */
	public int contactHole() {	
		BoundingBox playerBox = new BoundingBox(player.getX(), player.getY(), SPRITE_WIDTH, SPRITE_HEIGHT);
		BoundingBox[] HoleBox = new BoundingBox[HOLE_NUMBER];
		for(int i=0; i<HOLE_NUMBER; i++) {
			HoleBox[i] = new BoundingBox(INIT_HOLE_PLACE+i*HOLE_OFFSET, HOLE_Y, HOLE_WIDTH, HOLE_HEIGHT);
			if(playerBox.intersects(HoleBox[i])) {
				return i;
			}
		}
		return NONE;
	}
	/**
	 * this method is wrote for determine whether player is only on the water
	 * or player is also on the log or turtle.
	 * if player is only on the water, it will lose life.
	 */
	public void checkOnLogOrOnTurtle(){
		for(Sprite s: object) {
			if(contactSprite(s)) {
				if(s instanceof Water) {
					for(Sprite s1: object) {
						if(contactSprite(s1)) {
							if(s1 instanceof Log ||
									(s1 instanceof Turtle && (turtleFlag == true))) {
								return;
							}	
						}
					}
					life.remove(life.size()-1);
					player.setX(ORIGIN_PLACE_X);
					player.setY(ORIGIN_PLACE_Y);
				}
			}
		}
	}
	/**
	 * render the world,  so it reflects the current game state.
	 * @param g The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) {
		for(Sprite s: object) {
			//When turtle flag = false, turtle sinks, program doesn't render turtle 
			if(s instanceof Turtle) {
				if(!turtleFlag) {
				}
				else {
					s.render();
				}
			}
			else {
				s.render();
			}
		}		
		player.render();
		for(Life l: life) {
			l.render();
		}
		for(Hole h: frogInHole){
			h.render();
		}
		for(ExtraLife e: extraLife) {
			e.render();
		}
	}
	
}
