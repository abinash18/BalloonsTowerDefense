package balloonsTowerDefence;

import static other.DrawInFrame.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import other.StateManager;

import static other.StateManager.*;
import userInterface.*;
import userInterface.UserInterface.Menu;

public class MainMenu {

	private Texture bg;
	private UserInterface menuUI;
	private Menu mainMenu;

	public MainMenu() {
		bg = LoadTexture("mainMenu_BackGround");
		
		menuUI = new UserInterface();
		menuUI.createMenu("MainGameActions", GRID_SQUARE_SIZE / 2, GRID_SQUARE_SIZE / 4, 500, 500, 2, 2);
		
//		mainMenu = menuUI.getMenu("MainGameActions");
//		mainMenu.addMenuButton("play", "button_play");
//		
//		mainMenu.addMenuButton("LevelEdit", "button_LevelEdit");
//		mainMenu.addMenuButton("LeaderBoard", "button_LeaderBoard");
//		mainMenu.addMenuButton("Options", "button_Options");
//		mainMenu.addMenuButton("Exit", "button_Exit");
//		mainMenu.addMenuButton("Instructions", "button_Instructions");
		
		menuUI.addButton("play", "button_play", (int) (WIDTH / 3.5f), (int) (HEIGHT * 0.45f));
		menuUI.addButton("LevelEdit", "button_LevelEdit", (int) (WIDTH / 2.9f), (int) (HEIGHT * 0.53f));
		menuUI.addButton("LeaderBoard", "button_LeaderBoard", (int) (WIDTH / 3.5f), (int) (HEIGHT * 0.59f));
		menuUI.addButton("Options", "button_Options", (int) (WIDTH / 2.9f), (int) (HEIGHT * 0.66f));
		menuUI.addButton("Exit", "button_Exit", (int) (WIDTH / 1.1), (int) (HEIGHT * 0.88f));
		menuUI.addButton("Instructions", "button_Instructions", WIDTH / 180 - 20, (int) (HEIGHT * 0.88f));
	}

	public void checkClick() {
		if (Mouse.isButtonDown(0)) {

			if (menuUI.isButtonClicked("play")) {
				StateManager.setState(GameState.GAME);
			}
			if (menuUI.isButtonClicked("LevelEdit")) {
				StateManager.setState(GameState.LEVEL_EDITOR);
			}
			if (menuUI.isButtonClicked("Exit")) {
				System.exit(0);
			}
		}
	}

	public void tick() {
		DrawQuadWithTexture(bg, 0, 0, 2048, 1024);
		menuUI.drawOnScreen();
		checkClick();
	}
	
}
