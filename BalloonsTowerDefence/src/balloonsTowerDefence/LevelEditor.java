/**
 * Abinash Singh
 * Balloons Tower Defense Level Editor 
 */
package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.LoadTexture;
import static other.LevelToolKit.LoadMap;
import static other.LevelToolKit.saveMap;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

/**
 * The level editor screen
 */
public class LevelEditor {

	private FloorGrid grid;
	private int index;
	private FloorType[] types;
	private UserInterface editorUserInterFace;
	private Menu pickTileMenu;
	private Texture menuBg;

	/**
	 * constructor Loads the saved map file and displays it for editing
	 */
	public LevelEditor() {
		this.grid = LoadMap("dfg");
		this.index = 0;
		this.types = new FloorType[3];
		this.types[0] = FloorType.Grass;
		this.types[1] = FloorType.Path;
		this.types[2] = FloorType.Water;
		this.menuBg = LoadTexture("inGame_TowerPickerMenu");

		setUpUI();

	}

	/**
	 * Initializes all the buttons and menus in the game
	 */
	private void setUpUI() {
		editorUserInterFace = new UserInterface();
		editorUserInterFace.createMenu("PickTileMenu", 1280, 0, 192, 960, 2, 0);

		pickTileMenu = editorUserInterFace.getMenu("PickTileMenu");
		pickTileMenu.addMenuButton("Grass", "grass");
		pickTileMenu.addMenuButton("Path", "path");
		pickTileMenu.addMenuButton("Water", "water");
		pickTileMenu.setOpen(true);
	}

	/**
	 * Updates the screen and draws the objects as well as checking for updates in
	 * the grid and checkinf for clicked buttons
	 */
	public void tick() {
		draw();

		// handle mouse input
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (pickTileMenu.isButtonClicked("Grass")) {
					index = 0;
				} else if (pickTileMenu.isButtonClicked("Path")) {
					index = 1;
				} else if (pickTileMenu.isButtonClicked("Water")) {
					index = 2;
				} else {
					if (((int) Math.floor(Mouse.getX() / GRID_SQUARE_SIZE) < grid.getSquaresWide())) {
						setFloor();
					}
				}
			}
		}

		// Handle keyboard input
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				moveIndex();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
				saveMap("dfg", grid);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_R && Keyboard.getEventKeyState()) {
				grid = new FloorGrid();
			}

		}

	}

	/**
	 * Draws the screen
	 */
	private void draw() {
		DrawQuadWithTexture(menuBg, 1280, 0, 252, 1025);
		editorUserInterFace.drawOnScreen();
		grid.drawGridOnScreen();
	}

	private void moveIndex() {
		index++;
		if (index > types.length - 1) {
			index = 0;
		}
	}

	/**
	 * Sets the floor to which the user clicked on
	 */
	public void setFloor() {
		grid.setFloor((int) Math.floor(Mouse.getX() / GRID_SQUARE_SIZE),
				(int) Math.floor((HEIGHT - Mouse.getY() - 1) / GRID_SQUARE_SIZE), types[index]);
	}

}
