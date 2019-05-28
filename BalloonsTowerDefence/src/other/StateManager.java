/**
 * Abinash Singh 
 * Balloons Tower Defense StateManager
 */
package other;

import static other.LevelToolKit.LoadMap;

import javax.swing.JOptionPane;

import BTDOnlineToolKit.GameOnline;
import balloonsTowerDefence.*;
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
		SPLASHSCREEN_LOADING, MAINMENU, GAME, LEVEL_EDITOR, MAP_SELECT_SCREEN, LEADERBOARD, OPTIONS, TESTSCREEN,
		GAME_ONLINE
	}

	// Defines the default state of the game upon launch
	public static GameState gameState = GameState.SPLASHSCREEN_LOADING;
	// Defines all states of the game in different classes\
	// MainMenu
	public static MainMenu mainMenu;
	// The Main Game
	public static Game game;
	// Online Game
	public static GameOnline gameOnline;
	// The Level Editor
	public static LevelEditor levelEditor;

	public static Leaderboard leaderBoard;

	public static TestScreen testScreen;

	public static SplashScreenLoading splashScreenLoading;

	public static MapSelectScreen mapSelectScreen;

	public static FloorGrid map = LoadMap("dfg");

	// Variables for fps figuring out
	public static long nextSecond = System.currentTimeMillis() + 1000;
	public static int framesInLastSecond = 0;
	public static int framesInCurrentSecond = 0, gm = 1;

	public static int portTCP, portUDP;
	public static String ip, username, password;

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
				game = new Game(map, gm);
			}
			// Every time the state manager ticks it also ticks the current game state
			game.tick();
			break;
		case GAME_ONLINE:
			if (gameOnline == null) {
				// Create a new game Instance

//				ip = JOptionPane.showInputDialog("Enter Server Ip");
//				portTCP = Integer.parseInt(JOptionPane.showInputDialog("Enter TCP PORT"));
//				portUDP = Integer.parseInt(JOptionPane.showInputDialog("Enter UDP PORT"));
//				username = JOptionPane.showInputDialog("Enter Your username");
				gameOnline = new GameOnline(username, ip, portUDP, portTCP);
			}
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
			if (leaderBoard == null) {
				leaderBoard = new Leaderboard();
			}
			leaderBoard.tick();
			break;
		case SPLASHSCREEN_LOADING:
			if (splashScreenLoading == null) {
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
		case MAP_SELECT_SCREEN:
			if (mapSelectScreen == null) {
				mapSelectScreen = new MapSelectScreen();
			}
			mapSelectScreen.tick();
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

	public static void changeMap(FloorGrid newMap) {
		map = newMap;
	}

	public static void setGameMode(int gamemode) {
		gm = gamemode;
	}

	public static int getPortTCP() {
		return portTCP;
	}

	public static void setPortTCP(int portTCP) {
		StateManager.portTCP = portTCP;
	}

	public static int getPortUDP() {
		return portUDP;
	}

	public static void setPortUDP(int portUDP) {
		StateManager.portUDP = portUDP;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		StateManager.ip = ip;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		StateManager.username = username;
	}

}
