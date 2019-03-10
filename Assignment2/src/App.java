/**
 * Sample Project for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 * update by Guang Yang, 961587
 */

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
    /** screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;
    //this flag is used to decide which stage will be update/render
	private static boolean flag = true;
	private static final String INPUT_FILE0 = "assets/file/0.lvl";
	private static final String INPUT_FILE1 = "assets/file/1.lvl";

    private static World world0;
    private static World world1;
    public App() {
        super("Shadow Leap");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        try {
			world0 = new World(INPUT_FILE0, true);
			world1 = new World(INPUT_FILE1, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        if(flag) {
        	world0.update(input, delta);
        }
        else {
        	world1.update(input, delta);
        }
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
    	if(flag) {
    		world0.render(g);
    	}
    	else {
    		world1.render(g);
    	} 
    }
    
    /**set the flag to control different stage
     * @param flag if flag is true, update and render stage0; if false, update and render stage1
     */
    public static void setFlag(boolean flag) {
    	App.flag = flag;
    }
    
    /**get the flag
     * @return flag if flag is true, update and render stage0; if false, update and render stage1
     */
	public static boolean getFlag() {
		return flag;	
	    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}