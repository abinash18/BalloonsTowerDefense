/**
 * Abinash Singh
 * Balloons Tower Defense DrawInFrame 
 */
package other;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * DrawInFrame Is used to draw textures in the graphical pane
 */
public class DrawInFrame {

	// These are defined here because every class that needs these will already have
	// imported this class
	public static final int WIDTH = 1472, HEIGHT = 960; // base res of game
	public static final int GRID_SQUARE_SIZE = 64; // the pixel size of each floor texture

	
	/**
	 * Initializes the Graphics and textures
	 */
	public static void InitializeGL() {
		// The Title of the screen is set
		Display.setTitle("Balloons Tower Defence");
		// Magic
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		// glMatrixMode sets the current matrix mode
		glMatrixMode(GL_PROJECTION);
		// glLoadIdentity replaces the current matrix with the identity matrix.
		glLoadIdentity();
		// multiply the current matrix with an orthographic matrix
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		// glMatrixMode sets the current matrix mode
		glMatrixMode(GL_MODELVIEW);
		// Enables the texture rendering to be 2d
		glEnable(GL_TEXTURE_2D);
		// allows png's to be transparent
		glEnable(GL_BLEND);
		// Sets the blending parameters
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

	}

	/**
	 * Draws a square in the frame other wise known as a quad pre: none post: A Quad
	 * has been drawn on screen
	 */
	public static void DrawQuadWithTexture(Texture texture, float x, float y, float width, float height) {

		// binds the texture to the matrix
		texture.bind();
		// translates the texture to one float value of the x and y provided
		// this is the top left of the quad and point of origin for the rest of the
		// process
		glTranslatef(x, y, 0);
		// begins drawing the square
		glBegin(GL_QUADS);
		// the origin is the starting point of texture rendering
		glTexCoord2f(0, 0);
		// the vertex id set
		glVertex2f(0, 0);
		// the top right is set
		glTexCoord2f(1, 0);
		// the top right value is the width of the square
		glVertex2f(width, 0);
		// the bottom right of the square is now being drawn
		glTexCoord2f(1, 1);
		// the bottom right is the width and the height
		glVertex2f(width, height);
		// the bottom left is now being drawn
		glTexCoord2f(0, 1);
		// the bottom right is the height value of the square
		glVertex2f(0, height);
		// the drawing process has ended
		glEnd();
		// Stops the screen from tearing
		glLoadIdentity();

	}

	/**
	 * Draws a square in the frame other wise known as a quad pre: none post: A Quad
	 * has been drawn on screen
	 */
	public static void DrawQuadWithColor(float red, float green, float blue, float x, float y, float width, float height) {
		
		glEnable(GL_TEXTURE_2D);
		// translates the texture to one float value of the x and y provided
		// this is the top left of the quad and point of origin for the rest of the
		// process
		glTranslatef(x, y, 0);
		//Sets the color of the quad being drawn
		glColor4f(1f, 1f, 1f, 1f);
		// begins drawing the square
		glBegin(GL_QUADS);
		// the origin is the starting point of texture rendering
		glTexCoord2f(0, 0);
		// the vertex id set
		glVertex2f(0, 0);
		// the top right is set
		glTexCoord2f(1, 0);
		// the top right value is the width of the square
		glVertex2f(width, 0);
		// the bottom right of the square is now being drawn
		glTexCoord2f(1, 1);
		// the bottom right is the width and the height
		glVertex2f(width, height);
		// the bottom left is now being drawn
		glTexCoord2f(0, 1);
		// the bottom right is the height value of the square
		glVertex2f(0, height);
		// the drawing process has ended
		glEnd();
		glDisable(GL_TEXTURE_2D);
		// Stops the screen from tearing
		glLoadIdentity();

	}
	
	/**
	 * Draws a square in the frame other wise known as a quad which has been rotated
	 * pre: none post: A Rotated Quad has been drawn on screen
	 */
	public static void DrawQuadWithRotatedTexture(Texture texture, float x, float y, float width, float height,
			float angle) {

		// binds the texture to the matrix
		texture.bind();
		// This determines the center of the texture
		// which is all ways the max width and height divided by 2
		glTranslatef(x + width / 2, y + height / 2, 0);
		// the texture is then rotated to the angle given
		glRotated(angle, 0, 0, 1);
		// the top left is then again determined
		glTranslatef(-width / 2, -height / 2, 0);
		// begins drawing the square
		glBegin(GL_QUADS);
		// the origin is the starting point of texture rendering
		glTexCoord2f(0, 0);
		// the vertex id set
		glVertex2f(0, 0);
		// the top right is set
		glTexCoord2f(1, 0);
		// the top right value is the width of the square
		glVertex2f(width, 0);
		// the bottom right of the square is now being drawn
		glTexCoord2f(1, 1);
		// the bottom right is the width and the height
		glVertex2f(width, height);
		// the bottom left is now being drawn
		glTexCoord2f(0, 1);
		// the bottom right is the height value of the square
		glVertex2f(0, height);
		// the drawing process has ended
		glEnd();
		// Stops the screen from tearing
		glLoadIdentity();

	}

	/**
	 * Checks if the object has collided with the other objects x and y positions
	 * Provided for both objects including height and width pre: none post: true is
	 * returned if the two objects have collided otherwise false is returned
	 */
	public static boolean isCollided(float xPos1, float yPos1, float width1, float height1, float xPos2, float yPos2,
			float width2, float height2) {

		// If the XPos1 plus width1 (object 1's top right corner)
		// is greater than the xPos2 (Coord of object 2's top left corner)
		if (xPos1 + width1 > xPos2
				// And xPos1 is less than (Object 1's top left corner)
				// xPos2 + width2 (top left of object 2)
				&& xPos1 < xPos2 + width2
				// And if yPos1 + height1 (bottom left of object 1)
				// is greater than yPos2 (top left of object 2)
				&& yPos1 + height1 > yPos2
				// And if yPos1 (top left of object 1)
				// is less than yPos2 + height2 (object 2's bottom left corner)
				&& yPos1 < yPos2 + height2) {
			// Than return true
			return true;
		}
		// Otherwise return false
		return false;
	}

	/**
	 * Loads a texture from file pre: none post: the texture file is loaded provided
	 * by the file name
	 */
	public static Texture LoadTexture(String name) {

		// Declares a null texture VAR
		Texture tempTexture = null;
		// Loads a resource using slick as a inputStream
		InputStream in = ResourceLoader.getResourceAsStream("resources/" + name + ".png");

		// Magic
		try {
			tempTexture = TextureLoader.getTexture("png", in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Returns the temporary texture Variable
		return (tempTexture);

	}

}
