package balloonsTowerDefence;

import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.HEIGHT;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import other.Timer;

public class Player {

	public static int Money, LivesLeft;

	private FloorGrid grid;
	private RoundManager roundManager;
	private ArrayList<MonkeyTower> ListOfMonkeyTowers;
	private boolean MouseDownLeft, MouseDownRight, holdingTower;
	private FloorType[] typesOfFloors;
	private MonkeyTower temporaryTower;

	public Player(FloorGrid grid, RoundManager roundManager) {
		this.grid = grid;
		this.typesOfFloors = new FloorType[3];
		this.typesOfFloors[0] = FloorType.Grass;
		this.typesOfFloors[1] = FloorType.Path;
		this.typesOfFloors[2] = FloorType.Water;
		this.roundManager = roundManager;
		this.ListOfMonkeyTowers = new ArrayList<MonkeyTower>();
		this.MouseDownLeft = false;
		this.MouseDownRight = false;
		this.holdingTower = false;
		this.temporaryTower = null;
		// The Player is created when the game is luanced which means they will have
		// lives and cash at the
		// start but the game hasnt started so we need to put these at 0
		Money = 0;
		LivesLeft = 0;
	}

	public void tick() {
		
		if (holdingTower) {
			temporaryTower.setX(getFloorUnderMouse().getxPos());
			temporaryTower.setY(getFloorUnderMouse().getyPos());
//			tempTower.setX(Mouse.getX());
//			tempTower.setY(Mouse.getY());
			temporaryTower.draw();
		}
		
		// System.out.println(Money + " " + LivesLeft);
		for (MonkeyTower MonkeyTower : ListOfMonkeyTowers) {
			MonkeyTower.tick();
			MonkeyTower.draw();
			MonkeyTower.reUpdateTargetList(roundManager.getCurrentRound().getBalloonsList());
		}

		// handle mouse input
		if (Mouse.isButtonDown(0) && !MouseDownLeft) { // param takes mouse button 0 for leftclick and 1 for right
														// click
//			Floor floor = grid.getFloor(Mouse.getX() / GRID_SQUARE_SIZE, (HEIGHT - Mouse.getY() - 1) / GRID_SQUARE_SIZE);
//			if (floor.getFloorType().builds && changeMoneyAmount(-20)) {
//				ListOfMonkeyTowers.add(new MonkeyTowerDartMonkey(MonkeyTowerType.DartMonkey, floor,
//						roundManager.getCurrentRound().getBalloonsList()));
//			} else {
//				System.out.println("not buildable" + changeMoneyAmount(-20) );
//			}
			
			placeTower();
			
		}

		if (Mouse.isButtonDown(1) && !MouseDownRight) { // param takes mouse button 0 for leftclick and 1 for

		}

		MouseDownRight = Mouse.isButtonDown(1); // these are here becouse the game ticks multiple times
		// a second and you click fast enough to only click once every tick, this makes
		// sure that it only clicks once
		MouseDownLeft = Mouse.isButtonDown(0);

		// handel keyboard input
		while (Keyboard.next()) { // This loop runs as long the key is held down
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Timer.setTimeMultiplier(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Timer.setTimeMultiplier(-0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()) {

			}
			if (Keyboard.getEventKey() == Keyboard.KEY_D && Keyboard.getEventKeyState()) {

			}

		}

	}

	private Floor getFloorUnderMouse() {
		return grid.getFloor(Mouse.getX() / GRID_SQUARE_SIZE, (HEIGHT - Mouse.getY() - 1) / GRID_SQUARE_SIZE);
	}
	
	private void placeTower() {
		Floor currentTile = getFloorUnderMouse();
		if (holdingTower) {
			Floor f = getFloorUnderMouse();
			if (f.getFloorType().builds && !currentTile.isOccupied() && changeMoneyAmount(-temporaryTower.getType().cost)) {
//				towerList
//						.add(new TowerCannonBlue(TowerType.CannonBlue, t, waveManager.getCurrentWave().getEnemyList()));
				ListOfMonkeyTowers.add(temporaryTower);
				currentTile.setOccupied(true);
				holdingTower = false;
				temporaryTower = null;
			} else {
				System.out.println("not buildable");
			}
		}
	}
	
	public void initialize() {
		Money = 200;
		LivesLeft = 100;
	}

	public void pickTower(MonkeyTower t) {
		temporaryTower = t;
		holdingTower = true;
	}
	
	public static boolean changeMoneyAmount(int amount) {
		if (Money + amount >= 0) { // checks the amount if it is greater o r equal to 0 then adds it
			Money += amount;
			return true; // then returns true this is a check if
			// the player has enough money to buy the MonkeyTower or upgrade
		}
		return false; // If not then false is returned and no MonkeyTower is placed
	}

	public static void changeLifeAmount(int amount) {
		LivesLeft += amount; // This need no check if the the lives are less than 0 or 0
		// than it ends the game in the next frame or tick
	}

	public static int getMoney() {
		return Money;
	}

	public static void setMoney(int money) {
		Money = money;
	}

	public static int getLivesLeft() {
		return LivesLeft;
	}

	public static void setLivesLeft(int livesLeft) {
		LivesLeft = livesLeft;
	}

	public FloorGrid getGrid() {
		return grid;
	}

	public void setGrid(FloorGrid grid) {
		this.grid = grid;
	}

	public RoundManager getRoundManager() {
		return roundManager;
	}

	public void setRoundManager(RoundManager roundManager) {
		this.roundManager = roundManager;
	}

	public ArrayList<MonkeyTower> getListOfMonkeyTowers() {
		return ListOfMonkeyTowers;
	}

	public void setListOfMonkeyTowers(ArrayList<MonkeyTower> listOfMonkeyTowers) {
		ListOfMonkeyTowers = listOfMonkeyTowers;
	}

	public boolean isHoldingTower() {
		return holdingTower;
	}

	public void setHoldingTower(boolean holdingTower) {
		this.holdingTower = holdingTower;
	}

	public MonkeyTower getTemporaryTower() {
		return temporaryTower;
	}

	public void setTemporaryTower(MonkeyTower temporaryTower) {
		this.temporaryTower = temporaryTower;
	}

}
