package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.LoadTexture;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import other.StateManager;
import other.Timer;
import userInterface.UserInterface;
import userInterface.UserInterface.Menu;

public class Game {

	private FloorGrid grid;
	private Player player;
	private RoundManager roundManager;
	private UserInterface gameUserInterface;
	private Menu pickTowerMenu;
	private Texture menuBg;
	private Balloon[] balloonsInGame;
	public static final int MAX_BALLOON_TYPES = 2;

	public Game(int[][] mapMatrix) {
		this.grid = new FloorGrid(mapMatrix);
		this.menuBg = LoadTexture("inGame_TowerPickerMenu");
		this.balloonsInGame = new Balloon[MAX_BALLOON_TYPES];
		this.balloonsInGame[0] = new BalloonRed(10, 11, grid, BalloonType.RedBalloon);
		this.roundManager = new RoundManager(balloonsInGame, 3, 3);
		this.player = new Player(grid, roundManager);
		this.player.initialize();

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

		InitializeUserInterFace();
	}

	private void InitializeUserInterFace() {
		gameUserInterface = new UserInterface();

		gameUserInterface.createMenu("PickTower", 1280, 0, 192, 960, 2, 0);

		pickTowerMenu = gameUserInterface.getMenu("PickTower");

		pickTowerMenu.addMenuButton("DartMonkey", "cannonBlueFull");
		pickTowerMenu.addMenuButton("IceMonkey", "cannonBlueFull");

	}

	private void tickUI() {
		DrawQuadWithTexture(menuBg, 1280, 0, 255, 1025);

		gameUserInterface.drawOnScreen();

		gameUserInterface.drawString(pickTowerMenu.getX(), pickTowerMenu.getY(), "Lives: " + Player.LivesLeft);
		gameUserInterface.drawString(pickTowerMenu.getX(), pickTowerMenu.getY() + 100, "Money: " + Player.Money);
		gameUserInterface.drawString(pickTowerMenu.getX(), pickTowerMenu.getY() + 150,
				"Round: " + roundManager.getCurrentRoundNumber());
		gameUserInterface.drawString(0, 20, StateManager.framesInLastSecond + "fps");
		gameUserInterface.drawString(pickTowerMenu.getX(), pickTowerMenu.getY() + 250,
				"Speed: x" + Timer.timeMultiplier);
		gameUserInterface.drawString(pickTowerMenu.getX() - 500, pickTowerMenu.getY() + 275,
				"balloons this round: " + roundManager.getBalloonsThisRound());

		// Handles Mouse input
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (pickTowerMenu.isButtonClicked("DartMonkey")) {
					player.pickTower(new MonkeyTowerDartMonkey(MonkeyTowerType.DartMonkey, grid.getFloor(0, 0),
							roundManager.getCurrentRound().getBalloonsList()));
				}
				if (pickTowerMenu.isButtonClicked("IceMonkey")) {
					player.pickTower(new MonkeyTowerIceMonkey(MonkeyTowerType.DartMonkey, grid.getFloor(0, 0),
							roundManager.getCurrentRound().getBalloonsList()));
				}
			}
		}

	}

	public void tick() {
		grid.drawGridOnScreen();
		roundManager.tick();
		player.tick();
		tickUI();
	}

}
