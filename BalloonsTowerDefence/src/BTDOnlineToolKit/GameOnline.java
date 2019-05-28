/**
 * Abinash Singh 
 * Balloons Tower Defense Game 
 */
package BTDOnlineToolKit;

import packets.GeneralPackets;
import packets.GeneralPackets.*;
import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.LoadTexture;
import static other.DrawInFrame.getTexture;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import balloonsTowerDefence.Balloon;
import balloonsTowerDefence.BalloonRed;
import balloonsTowerDefence.BalloonType;
import balloonsTowerDefence.FloorGrid;
import balloonsTowerDefence.Game;
import balloonsTowerDefence.MonkeyTower;
import balloonsTowerDefence.MonkeyTowerDartMonkey;
import balloonsTowerDefence.MonkeyTowerIceMonkey;
import balloonsTowerDefence.MonkeyTowerNinjaMonkey;
import balloonsTowerDefence.MonkeyTowerSuperMonkey;
import balloonsTowerDefence.Player;
import balloonsTowerDefence.RoundManager;
import handlers.Informal;
import other.StateManager;
import other.Timer;
import userInterface.Button;
import userInterface.Image;
import userInterface.Label;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

/**
 * Main Game class runs the game as a different state of game
 */
public class GameOnline {

	// The map
	private FloorGrid grid;
	// Player object
	private Player player;
	// Manager for the round changing
	private RoundManager roundManager;
	// Interface for the player to interact with the game
	private UserInterface gameUserInterface;
	// the menu objects that the interfaces are organized in
	private Menu pickTowerMenu, playPauseMenu, upgradeMenu;
	// back ground for the menu
	private Texture menuBg;
	// private ArrayList<MonkeyTower> monkeys;
	// the balloons currently in the round
	private Balloon[] balloonsInGame;
	// label objects
	private Label fpsLabel, money, lives, round, timeMultiplier, towerName, upgradeButtonLabel;
	// the tower icon on the tower upgrade menu
	private Image towericon;
	private Button upgradeButton;
	// max type of balloons that can spawn in a round
	public static final int MAX_BALLOON_TYPES = 2;
	// if the upgrade menu is open or not
	public boolean upgradeMenuOpen;
	public int gamemode;

	private static int tcpPort, udpPort;
	private static String ipAddress;
	private static NetworkHandler netHandle;

	private Client client;

	public GameOnline(String userName, String ip, int udp, int tcp) {
		// this.grid = grid;
		this.menuBg = LoadTexture("inGame_TowerPickerMenu");
//		this.balloonsInGame = new Balloon[MAX_BALLOON_TYPES];
//		this.balloonsInGame[0] = new BalloonRed(0, 1, grid, BalloonType.RedBalloon);
//		this.balloonsInGame[1] = new Balloon(0, 1, grid, BalloonType.BlueBalloon);
		this.roundManager = new RoundManager(balloonsInGame, 3, 3);
		this.player = new Player(grid, roundManager);
		this.player.initialize();
		this.upgradeMenuOpen = false;
		// gamemode = gm;
//		if (gm == 2) {
//			roundManager.setTimeBetweenRounds(0);
//			player.setMoney(2000);
//		}

		InitializeUserInterFace();

		client = new Client();
		netHandle = new NetworkHandler(this);
		startClientConnection();
	}

	private void startClientConnection() {

		try {
			client.start();
			client.connect(5000, "127.0.0.1", 80, 8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
		client.addListener(netHandle);
		Log.set(Log.LEVEL_INFO);
		Kryo kryo = client.getKryo();
		kryo.register(packets.GeneralPackets.Ping.class);
		kryo.register(packets.GeneralPackets.Message.class);
		kryo.register(packets.GeneralPackets.ConnectRequest.class);
		ConnectRequest request = new ConnectRequest();
		request.password = StateManager.password;
		request.userName = StateManager.username;
		client.sendTCP(request);
	}

	/**
	 * constructor Creates a game object with the specified mapMatrix
	 */
	public GameOnline(int[][] mapMatrix) {
		this.grid = new FloorGrid(mapMatrix);
		this.menuBg = LoadTexture("inGame_TowerPickerMenu");
		this.balloonsInGame = new Balloon[MAX_BALLOON_TYPES];
		this.balloonsInGame[0] = new BalloonRed(10, 11, grid, BalloonType.RedBalloon);
		this.roundManager = new RoundManager(balloonsInGame, 3, 3);
		this.player = new Player(grid, roundManager);
		this.player.initialize();
		this.upgradeMenuOpen = false;
		InitializeUserInterFace();
	}

	/**
	 * constructor Creates a game object with a predefined and calculated floor grid
	 */
	public GameOnline(FloorGrid grid, int gm) {

		this.grid = grid;
		this.menuBg = LoadTexture("inGame_TowerPickerMenu");
		this.balloonsInGame = new Balloon[MAX_BALLOON_TYPES];
		this.balloonsInGame[0] = new BalloonRed(0, 1, grid, BalloonType.RedBalloon);
		this.balloonsInGame[1] = new Balloon(0, 1, grid, BalloonType.BlueBalloon);
		this.roundManager = new RoundManager(balloonsInGame, 3, 3);
		this.player = new Player(grid, roundManager);
		this.player.initialize();
		this.upgradeMenuOpen = false;
		gamemode = gm;
		if (gm == 2) {
			roundManager.setTimeBetweenRounds(0);
			player.setMoney(2000);
		}

		InitializeUserInterFace();
	}

	/**
	 * Initializes the user interface objects on the screen pre: none post: a user
	 * interface is initialized and labels buttons and menus are initialized to
	 * default values and are set to objects representing them
	 */
	private void InitializeUserInterFace() {
		gameUserInterface = new UserInterface();

		gameUserInterface.createMenu("PickTower", 1280, 25, 192, 960, 2, 0);
		gameUserInterface.createMenu("PlayPauseMenu", 1216, 0, 64, 64, 2, 0);
		gameUserInterface.createMenu("UpgradeMenu", 0, 0, GRID_SQUARE_SIZE * 3, GRID_SQUARE_SIZE * 3,
				getTexture("black-translucent"));

		playPauseMenu = gameUserInterface.getMenu("PlayPauseMenu");
		upgradeMenu = gameUserInterface.getMenu("UpgradeMenu");
		pickTowerMenu = gameUserInterface.getMenu("PickTower");

		pickTowerMenu.addMenuButton("DartMonkey", "DartMonkey");
		pickTowerMenu.addMenuButton("IceMonkey", "ice_tower_monkey");
		pickTowerMenu.addMenuButton("NinjaMonkey", "NinjaMonkey");
		pickTowerMenu.addMenuButton("SuperMonkey", "supermonkey_icon");
		pickTowerMenu.setOpen(true);

		playPauseMenu.addMenuButton("Play", "playpausefastforward", 90, 90);
		playPauseMenu.setOpen(true);

		upgradeMenu.addLabel("TowerName", "oztype", 15, "Test", upgradeMenu.getX() + 5, upgradeMenu.getY() + 5, false);
		upgradeMenu.addLabel("Upgrade", "oztype", 15, "Test", upgradeMenu.getX() + 25, upgradeMenu.getY() + 25, false);
		upgradeMenu.addImage("TowerIcon", 100, 5, null);
		upgradeMenu.addMenuButton("Upgrade", "button_notClicked", 25, 25, 100, 50);

		towerName = upgradeMenu.getLabel("TowerName");
		upgradeButtonLabel = upgradeMenu.getLabel("Upgrade");
		towericon = upgradeMenu.getImage("TowerIcon");
		upgradeButton = upgradeMenu.getButton("Upgrade");

		gameUserInterface.addLabel("fps", "oztype", 25, "", 0, 0, true);
		gameUserInterface.addLabel("money", "oztype", 25, "", pickTowerMenu.getX(), HEIGHT - HEIGHT / 3, true);
		gameUserInterface.addLabel("lives", "oztype", 25, "", pickTowerMenu.getX(), HEIGHT - HEIGHT / 4, true);
		gameUserInterface.addLabel("round", "oztype", 25, "", pickTowerMenu.getX(), HEIGHT - HEIGHT / 6, true);
		gameUserInterface.addLabel("ping", "oztype", 25, "", 0, 50, true);
		gameUserInterface.addLabel("timeMultiplier", "oztype", 25, "", pickTowerMenu.getX(), HEIGHT - HEIGHT / 8, true);

		fpsLabel = gameUserInterface.getLabel("fps");
		money = gameUserInterface.getLabel("money");
		lives = gameUserInterface.getLabel("lives");
		round = gameUserInterface.getLabel("round");
		timeMultiplier = gameUserInterface.getLabel("timeMultiplier");

	}

	/**
	 * sets the upgrade menu open or closed depending on what the current state of
	 * it is
	 */
	public void setOpenUpgradeMenu(boolean o) {
		upgradeMenu.setOpen(o);
	}

	/**
	 * Updates the labels and buttons on the upgrade menu
	 */
	private void updateUpgradeMenu(MonkeyTower mt) {
		float mouseY = HEIGHT - Mouse.getY() - 1;
		upgradeMenuOpen = true;
		upgradeMenu.setX((int) mt.getX() + GRID_SQUARE_SIZE / 2);
		upgradeMenu.setY((int) mouseY + GRID_SQUARE_SIZE / 2);
		towerName.setxOffset(15);
		towerName.setyOffset(5);
		towerName.setText(mt.type.name());
		towericon.setTex(mt.getType().getIcon());
		towericon.setxOffset(GRID_SQUARE_SIZE * 2);
		towericon.setWidth(64);
		towericon.setHeight(64);
		upgradeButton.setX(upgradeMenu.getX() + GRID_SQUARE_SIZE * 3 - 100);
		upgradeButton.setY(upgradeMenu.getY() + GRID_SQUARE_SIZE * 3 - 50);
		upgradeButtonLabel.setxOffset(GRID_SQUARE_SIZE * 2);
		upgradeButtonLabel.setyOffset(GRID_SQUARE_SIZE * 2);
		upgradeMenu.setOpen(true);
	}

	/**
	 * Updates the game ui and labels and buttons that are not in a menu aswell as
	 * menus
	 */
	private void tickUI() {
		DrawQuadWithTexture(menuBg, 1280, 0, 255, 1025);

		fpsLabel.setText(StateManager.framesInLastSecond + "fps");

		money.setText("Money: " + Player.Money);
		lives.setText("Lives: " + Player.LivesLeft);
		round.setText("Round: " + roundManager.getCurrentRoundNumber());
		timeMultiplier.setText("Speed: x" + Timer.timeMultiplier);

		gameUserInterface.drawOnScreen();

		// handles game end
		if (Player.LivesLeft <= 0) {
			Informal.message("Sorry You Loose!");
			// StateManager.setState(GameState.MAINMENU);
		}

		// Handles Mouse input
		MonkeyTower mt = null;
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				float mouseY = HEIGHT - Mouse.getY() - 1;
				if (upgradeMenuOpen && !upgradeMenu.checkHover()) {
					upgradeMenuOpen = false;
					upgradeMenu.setOpen(false);
				} else {

					mt = player.getTowerUnderMouse();

					if (mt != null) {
						updateUpgradeMenu(mt);
					}

					if (upgradeMenu.isButtonClicked("Upgrade")) {
						System.out.println(mt);
						// mt.upgrade();
					}

				}
				if (pickTowerMenu.isButtonClicked("DartMonkey")) {
					if (roundManager.getCurrentRound() != null) {
						player.pickTower(new MonkeyTowerDartMonkey(grid.getFloor(0, 0),
								roundManager.getCurrentRound().getBalloonsList()));
					} else {
						player.pickTower(new MonkeyTowerDartMonkey(grid.getFloor(0, 0), null));
					}

				}

				if (pickTowerMenu.isButtonClicked("IceMonkey")) {
					if (roundManager.getCurrentRound() != null) {
						player.pickTower(new MonkeyTowerIceMonkey(grid.getFloor(0, 0),
								roundManager.getCurrentRound().getBalloonsList()));
					} else {
						player.pickTower(new MonkeyTowerIceMonkey(grid.getFloor(0, 0), null));
					}
					// player.pickTower(new
					// MonkeyTowerIceMonkey(MonkeyTowerType.DartMonkey,
					// grid.getFloor(0, 0),
					// roundManager.getCurrentRound().getBalloonsList()));
				}
				if (pickTowerMenu.isButtonClicked("NinjaMonkey")) {
					if (roundManager.getCurrentRound() != null) {
						player.pickTower(new MonkeyTowerNinjaMonkey(grid.getFloor(0, 0),
								roundManager.getCurrentRound().getBalloonsList()));
					} else {
						player.pickTower(new MonkeyTowerNinjaMonkey(grid.getFloor(0, 0), null));
					}

				}

				if (pickTowerMenu.isButtonClicked("SuperMonkey")) {
					if (roundManager.getCurrentRound() != null) {
						player.pickTower(new MonkeyTowerSuperMonkey(grid.getFloor(0, 0),
								roundManager.getCurrentRound().getBalloonsList()));
					} else {
						player.pickTower(new MonkeyTowerSuperMonkey(grid.getFloor(0, 0), null));
					}

				}

				// Buttons for play puase and fast forward
				// if (gm == 1) {
				if (playPauseMenu.isButtonClicked("Play")) {
					// if (Timer.isGamePaused()) {
					if (roundManager.getCurrentRound() == null || roundManager.getCurrentRound().isRoundCompleted()) {
						roundManager.beginRound();
					} else {
						Timer.setGameFastforward();
					}

					// }
				}
				// }

			}
		}

	}

	/**
	 * Event dispatcher for updating the game
	 */
	public void tick() {
		grid.drawGridOnScreen();
		roundManager.tick();
		player.tick();
		tickUI();
		// monkeys = player.getListOfMonkeyTowers();
		// DrawInFrame.DrawCicle(500, 500, 50);

	}

	public FloorGrid getGrid() {
		return grid;
	}

	public void setGrid(FloorGrid grid) {
		this.grid = grid;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public RoundManager getRoundManager() {
		return roundManager;
	}

	public void setRoundManager(RoundManager roundManager) {
		this.roundManager = roundManager;
	}

	public UserInterface getGameUserInterface() {
		return gameUserInterface;
	}

	public void setGameUserInterface(UserInterface gameUserInterface) {
		this.gameUserInterface = gameUserInterface;
	}

	public Menu getPickTowerMenu() {
		return pickTowerMenu;
	}

	public void setPickTowerMenu(Menu pickTowerMenu) {
		this.pickTowerMenu = pickTowerMenu;
	}

	public Menu getPlayPauseMenu() {
		return playPauseMenu;
	}

	public void setPlayPauseMenu(Menu playPauseMenu) {
		this.playPauseMenu = playPauseMenu;
	}

	public Menu getUpgradeMenu() {
		return upgradeMenu;
	}

	public void setUpgradeMenu(Menu upgradeMenu) {
		this.upgradeMenu = upgradeMenu;
	}

	public Texture getMenuBg() {
		return menuBg;
	}

	public void setMenuBg(Texture menuBg) {
		this.menuBg = menuBg;
	}

	public Balloon[] getBalloonsInGame() {
		return balloonsInGame;
	}

	public void setBalloonsInGame(Balloon[] balloonsInGame) {
		this.balloonsInGame = balloonsInGame;
	}

	public Label getFpsLabel() {
		return fpsLabel;
	}

	public void setFpsLabel(Label fpsLabel) {
		this.fpsLabel = fpsLabel;
	}

	public Label getMoney() {
		return money;
	}

	public void setMoney(Label money) {
		this.money = money;
	}

	public Label getLives() {
		return lives;
	}

	public void setLives(Label lives) {
		this.lives = lives;
	}

	public Label getRound() {
		return round;
	}

	public void setRound(Label round) {
		this.round = round;
	}

	public Label getTimeMultiplier() {
		return timeMultiplier;
	}

	public void setTimeMultiplier(Label timeMultiplier) {
		this.timeMultiplier = timeMultiplier;
	}

	public Label getTowerName() {
		return towerName;
	}

	public void setTowerName(Label towerName) {
		this.towerName = towerName;
	}

	public Label getUpgradeButtonLabel() {
		return upgradeButtonLabel;
	}

	public void setUpgradeButtonLabel(Label upgradeButtonLabel) {
		this.upgradeButtonLabel = upgradeButtonLabel;
	}

	public Image getTowericon() {
		return towericon;
	}

	public void setTowericon(Image towericon) {
		this.towericon = towericon;
	}

	public Button getUpgradeButton() {
		return upgradeButton;
	}

	public void setUpgradeButton(Button upgradeButton) {
		this.upgradeButton = upgradeButton;
	}

	public boolean isUpgradeMenuOpen() {
		return upgradeMenuOpen;
	}

	public void setUpgradeMenuOpen(boolean upgradeMenuOpen) {
		this.upgradeMenuOpen = upgradeMenuOpen;
	}

	public int getGamemode() {
		return gamemode;
	}

	public void setGamemode(int gamemode) {
		this.gamemode = gamemode;
	}

	public static int getTcpPort() {
		return tcpPort;
	}

	public static void setTcpPort(int tcpPort) {
		GameOnline.tcpPort = tcpPort;
	}

	public static int getUdpPort() {
		return udpPort;
	}

	public static void setUdpPort(int udpPort) {
		GameOnline.udpPort = udpPort;
	}

	public static String getIpAddress() {
		return ipAddress;
	}

	public static void setIpAddress(String ipAddress) {
		GameOnline.ipAddress = ipAddress;
	}

	public static NetworkHandler getNetHandle() {
		return netHandle;
	}

	public static void setNetHandle(NetworkHandler netHandle) {
		GameOnline.netHandle = netHandle;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public static int getMaxBalloonTypes() {
		return MAX_BALLOON_TYPES;
	}

}
