/**
 * Abinash Singh
 * Balloons Tower Defence MakeFrame 
 */
package balloonsTowerDefence;

import static other.DrawInFrame.InitializeGL;

import org.lwjgl.opengl.Display;

import other.StateManager;
import other.Timer;

public class MakeFrame {

	public static final int MAX_FRAMERATE = 200;
	
	/**
	 * Constructor pre: none post: The main game loop updates every thing in it
	 * every frame
	 */
	public MakeFrame() {
		// initializes Graphics rendering
		InitializeGL();
		// A grid is defined
		// FloorGrid floor = new FloorGrid(StartGame.testMap);
		// A loop is started to keep the display alive
		while (!Display.isCloseRequested()) {
			Timer.Tick();
			StateManager.tick();
			Display.update();
			// Sync the frame rate to 60
			Display.sync(MAX_FRAMERATE);
		}

	}

}
