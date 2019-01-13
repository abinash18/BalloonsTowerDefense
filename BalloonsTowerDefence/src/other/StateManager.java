/**
 * Abinash Singh 
 * Balloons Tower Defense StateManager
 */
package other;

import static other.LevelToolKit.LoadMap;

import balloonsTowerDefence.FloorGrid;
import balloonsTowerDefence.Game;
import balloonsTowerDefence.Leaderboard;
import balloonsTowerDefence.LevelEditor;
import balloonsTowerDefence.MainMenu;
import balloonsTowerDefence.SplashScreenLoading;
import balloonsTowerDefence.TestScreen;

/**
 * StateManager Handles the game state changes to different states if requested
 */
public class StateManager {

	// Public enum defined in this class because it dose not need Extra Variables
	public static enum GameState {
		SPLASHSCREEN_LOADING, MAINMENU, GAME, LEVEL_EDITOR, LEADERBOARD, OPTIONS, TESTSCREEN
	}

	// Defines the default state of the game upon launch
	public static GameState gameState = GameState.SPLASHSCREEN_LOADING;
	// Defines all states of the game in different classes\
	// MainMenu
	public static MainMenu mainMenu;
	// The Main Game
	public static Game game;
	// The Level Editor
	public static LevelEditor levelEditor;
	
	public static Leaderboard leaderBoard;

	public static TestScreen testScreen;

	public static SplashScreenLoading splashScreenLoading;
	
	public static FloorGrid map = LoadMap("dfg");

	// Variables for fps figuring out
	public static long nextSecond = System.currentTimeMillis() + 1000;
	public static int framesInLastSecond = 0;
	public static int framesInCurrentSecond = 0;

	/**
	 * Executes every time the game ticks
	 */
	public static void tick() {

		// Switches on the different states of the game that it could be in
		switch (gameState) {
		case MAINMENU:
			// If there is not already a Main menu instance that exists
			if (mainMenu == null) {
				// Create a new Menu Instance
				mainMenu = new MainMenu();
			}
			mainMenu.tick();
			break;
		case GAME:
			// If there is not already a game instance that exists
			if (game == null) {
				// Create a new game Instance
				game = new Game(map);
			}
			// Every time the state manager ticks it also ticks the current game state
			game.tick();
			break;
		case LEVEL_EDITOR:
			// If there is not already a Editor instance that exists
			if (levelEditor == null) {
				// Create a new Editor Instance
				levelEditor = new LevelEditor();
			}
			levelEditor.tick();
			break;
		case LEADERBOARD:
			if (leaderBoard == null){
				leaderBoard = new Leaderboard();
			}
			leaderBoard.tick();
			break;
		case SPLASHSCREEN_LOADING:
			if (splashScreenLoading == null){
				splashScreenLoading = new SplashScreenLoading();
			}
			splashScreenLoading.tick();
			break;
		case OPTIONS:
			break;
		case TESTSCREEN:
			if (testScreen == null) {
				testScreen = new TestScreen();
			}
			testScreen.tick();
			break;
		default:
			break;
		}

		long currentTime = System.currentTimeMillis();
		if (currentTime > nextSecond) {
			nextSecond += 1000;
			framesInLastSecond = framesInCurrentSecond;
			framesInCurrentSecond = 0;

			// System.out.println(framesInLastSecond + "fps");
		}

		framesInCurrentSecond++;

	}

	/**
	 * Sets the state of the gameState variable. pre: none. post: The Game State
	 * variable has been changed to the enum defined in the parameter state
	 */
	public static void setState(GameState state) {
		gameState = state;
	}
}
