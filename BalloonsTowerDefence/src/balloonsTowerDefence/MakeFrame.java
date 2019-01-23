/**
 * Abinash Singh
 * Balloons Tower Defence MakeFrame 
 */
package balloonsTowerDefence;

import static other.DrawInFrame.InitializeGL;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;

import other.StateManager;
import other.Timer;

/**
 * Creates the game frame and initializes graphics
 */
public class MakeFrame {

	private static int fps = 200;

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
			// try {
			Timer.Tick();
			StateManager.tick();
			Display.update();
			// Sync the frame rate to 60
			Display.sync(fps);
			// } catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
			// JOptionPane.ERROR_MESSAGE);
			// }

		}

	}

	public static int getFps() {
		return fps;
	}

	public static void setFps(int fps) {
		MakeFrame.fps = fps;
	}

}
