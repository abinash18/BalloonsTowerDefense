package balloonsTowerDefence;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.w3c.dom.Element;
import org.w3c.dom.css.Counter;

import static other.DrawInFrame.*;

import java.lang.reflect.WildcardType;
import java.util.Map;

import other.DrawInFrame;
import other.LevelToolKit;
import other.LoadSettings;
import other.StateManager;
import other.StateManager.GameState;
import userInterface.Button;
import userInterface.Label;
import userInterface.UserInterface;

public class MapSelectScreen {

	FloorGrid[] grid = new FloorGrid[LoadSettings.maps.size()];
	private int gm = 0, map = 0;
	private boolean doneLoading = false;
	Texture bg = getTexture("red_tint"), button = getTexture("button_notClicked");
	UserInterface UI;
	Button noormal, apoco, next;
	Label nex, nor, apoc, loading;

	public MapSelectScreen() {
		UI = new UserInterface();
		UI.addButton("Normal", button, GRID_SQUARE_SIZE, 50, GRID_SQUARE_SIZE * 2, button.getImageHeight());
		UI.addButton("Apocolips", button, GRID_SQUARE_SIZE * 4, 50, GRID_SQUARE_SIZE * 2, button.getImageHeight());
		UI.addButton("Next", button, WIDTH - (WIDTH / 4) * 2, 100, GRID_SQUARE_SIZE * 2, button.getImageHeight());

		noormal = UI.getButton("Normal");
		apoco = UI.getButton("Apocolips");
		next = UI.getButton("Next");

		UI.addLabel("ins", "oztype", 15, "Choose map and then click game mode to start", 0, 0, true);
		UI.addLabel("nor", "oztype", 15, "Normal", 0, 0, false);
		UI.addLabel("apoc", "oztype", 15, "Apocolips", 0, 0, false);
		UI.addLabel("next", "oztype", 15, "Next", 0, 0, false);
		UI.addLabel("Loading", "oztype", 25, "Loading", DrawInFrame.WIDTH / 2, DrawInFrame.HEIGHT / 2, true, true);

		nex = UI.getLabel("next");
		nor = UI.getLabel("nor");
		apoc = UI.getLabel("apoc");
		loading = UI.getLabel("Loading");
		loading.setHidden(false);

		int counter = 0;
		for (Element e : LoadSettings.maps) {

			System.out.println("Loading: " + counter);
			grid[counter] = LevelToolKit.LoadMap(e.getAttribute("name"));
			counter++;
			loading.setText(loading.getText() + ".");
			tick();
		}
		doneLoading = true;
		// new Thread(new MapPreviewLoader(loading, grid, doneLoading)).start();

	}

	public void tick() {

		nor.setX(noormal.getX() + 25);
		nor.setY(noormal.getY());
		apoc.setX(apoco.getX() + 25);
		apoc.setY(apoco.getY());
		nex.setX(next.getX() + 25);
		nex.setY(next.getY());

		// if (doneLoading) {
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (UI.isButtonClicked("Next")) {
					moveIndex();
				}
				if (UI.isButtonClicked("Normal")) {
					StateManager.changeMap(grid[map]);
					StateManager.setState(GameState.GAME);
				}
				if (UI.isButtonClicked("Apocolips")) {
					StateManager.changeMap(grid[map]);
					StateManager.setGameMode(2);
					StateManager.setState(GameState.GAME);
				}
			}

		}
		// }
		draw();
	}

	private void draw() {
		DrawQuadWithTexture(bg, 0, 0, WIDTH, HEIGHT);
		// for (int i = 0; i < grid.length; i++) {
		if (doneLoading) {
			grid[map].drawGridOnScreenMapPreview(25, 100, 100);
		}
		// }
		UI.drawOnScreen();

	}

	private void moveIndex() {
		map++;
		if (map > grid.length - 1) {
			map = 0;
		}
	}

}

class MapPreviewLoader implements Runnable {

	private Label loading;
	private FloorGrid[] grid;
	private boolean doneLoading;

	public MapPreviewLoader(Label lo, FloorGrid[] grid, boolean doneLoading) {
		this.loading = lo;
		this.grid = grid;
		this.doneLoading = doneLoading;
	}

	@Override
	public void run() {
		InitializeGL();
		// loading.setHidden(false);
		int counter = 0;
		for (Element e : LoadSettings.maps) {
			System.out.println("Loading: " + counter);
			grid[counter] = LevelToolKit.LoadMap(e.getAttribute("name"));
			counter++;
			// loading.setText(loading.getText() + ".");
		}
		doneLoading = true;
	}

}
