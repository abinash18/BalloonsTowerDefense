package balloonsTowerDefence;

import static other.DrawInFrame.*;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import other.StateManager;
import other.Timer;
import userInterface.Label;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

public class Game {

	private FloorGrid grid;
	private Player player;
	private RoundManager roundManager;
	private UserInterface gameUserInterface;
	private Menu pickTowerMenu, playPauseMenu, upgradeMenu;
	private Texture menuBg;
	private ArrayList<MonkeyTower> monkeys;
	private Balloon[] balloonsInGame;
	private Label fpsLabel, money, lives, round, timeMultiplier;
	public static final int MAX_BALLOON_TYPES = 2;
	public boolean upgradeMenuOpen;

	public Game(int[][] mapMatrix) {
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

	public Game(FloorGrid grid) {
		this.grid = grid;
		this.menuBg = LoadTexture("inGame_TowerPickerMenu");
		this.balloonsInGame = new Balloon[MAX_BALLOON_TYPES];
		this.balloonsInGame[0] = new BalloonRed(0, 1, grid, BalloonType.RedBalloon);
		this.balloonsInGame[1] = new Balloon(0, 1, grid, BalloonType.BlueBalloon);
		this.roundManager = new RoundManager(balloonsInGame, 3, 3);
		this.player = new Player(grid, roundManager);
		this.player.initialize();
		this.upgradeMenuOpen = false;
		InitializeUserInterFace();
	}

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
		upgradeMenu.addImage("TowerIcon", 25, 5, null);
		upgradeMenu.addMenuButton("Upgrade", "button_notClicked", 25, 25, 100, 50);
		
		gameUserInterface.addLabel("fps", "oztype", 25, "", 0, 0, true);
		gameUserInterface.addLabel("money", "oztype", 25, "", pickTowerMenu.getX(), HEIGHT - HEIGHT / 2, true);
		gameUserInterface.addLabel("lives", "oztype", 25, "", pickTowerMenu.getX(), HEIGHT - HEIGHT / 4, true);
		gameUserInterface.addLabel("round", "oztype", 25, "", pickTowerMenu.getX(), HEIGHT - HEIGHT / 6, true);
		gameUserInterface.addLabel("timeMultiplier", "oztype", 25, "", pickTowerMenu.getX(), HEIGHT - HEIGHT / 8, true);

		fpsLabel = gameUserInterface.getLabel("fps");
		money = gameUserInterface.getLabel("money");
		lives = gameUserInterface.getLabel("lives");
		round = gameUserInterface.getLabel("round");
		timeMultiplier = gameUserInterface.getLabel("timeMultiplier");

	}

	public void setOpenUpgradeMenu(boolean o) {
		upgradeMenu.setOpen(o);
	}

	private void updateUpgradeMenu(MonkeyTower mt) {
		float mouseY = HEIGHT - Mouse.getY() - 1;
		upgradeMenuOpen = true;
		upgradeMenu.setX((int)mt.getX() + GRID_SQUARE_SIZE / 2);
		upgradeMenu.setY((int)mouseY + GRID_SQUARE_SIZE / 2);
		upgradeMenu.getLabel("TowerName").setX(upgradeMenu.getX() + 5);
		upgradeMenu.getLabel("TowerName").setY(upgradeMenu.getY() + 5);
		upgradeMenu.getImage("TowerIcon").setTex(mt.getType().icon);
		upgradeMenu.getImage("TowerIcon").setWidth(64);
		upgradeMenu.getImage("TowerIcon").setHeight(64);
		upgradeMenu.getButton("Upgrade").setX(upgradeMenu.getX() + 64);
		upgradeMenu.getButton("Upgrade").setY(upgradeMenu.getY() + 64);
		upgradeMenu.getLabel("Upgrade").setX(upgradeMenu.getX() + 64);
		upgradeMenu.getLabel("Upgrade").setY(upgradeMenu.getY() + 64);
		upgradeMenu.setOpen(true);
	}
	
	private void tickUI() {
		DrawQuadWithTexture(menuBg, 1280, 0, 255, 1025);

		fpsLabel.setText(StateManager.framesInLastSecond + "fps");
		
		money.setText("Money: " + Player.Money);
		lives.setText("Lives: " + Player.LivesLeft);
		round.setText("Round: " + roundManager.getCurrentRoundNumber());
		timeMultiplier.setText("Speed: x" + Timer.timeMultiplier);

		gameUserInterface.drawOnScreen();

		// Handles Mouse input
		MonkeyTower mt = null;
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				float mouseY = HEIGHT - Mouse.getY() - 1;
				if (upgradeMenuOpen) {
					upgradeMenuOpen = false;
					upgradeMenu.setOpen(false);
				} else {

					mt = player.getTowerUnderMouse();

					if (mt != null) {
						updateUpgradeMenu(mt);
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
						player.pickTower(
								new MonkeyTowerIceMonkey(grid.getFloor(0, 0), null));
					}
//					player.pickTower(new MonkeyTowerIceMonkey(MonkeyTowerType.DartMonkey, grid.getFloor(0, 0),
//							roundManager.getCurrentRound().getBalloonsList()));
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
				if (playPauseMenu.isButtonClicked("Play")) {
					// if (Timer.isGamePaused()) {
					if (roundManager.getCurrentRound() == null || roundManager.getCurrentRound().isRoundCompleted()) {
						roundManager.beginRound();
					} else {
						Timer.setGameFastforward();
					}

					// }
				}

			}
		}

	}

	public void tick() {
		grid.drawGridOnScreen();
		roundManager.tick();
		player.tick();
		tickUI();
		monkeys = player.getListOfMonkeyTowers();
		// DrawInFrame.DrawCicle(500, 500, 50);

	}

}
