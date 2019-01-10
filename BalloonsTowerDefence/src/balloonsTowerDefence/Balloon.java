package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.LoadTexture;
import static other.Timer.Delta;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;


public class Balloon implements Entity {

	private static final int MONEY_FOR_KILL = 10;
	private int width, height, moneyReward, currentTurnPoint;
	private float speed, x, y, health, startingHealth, hiddenHealth;
	private Texture instanceTexture;
	// private Texture healthBg, healthFg, healthBorder; //heathBar
	private Floor startingFloorObject;
	private boolean first, alive;
	private FloorGrid grid;
	private boolean calledChangeMoney = false;
	private ArrayList<TurningPoint> turnigPoints;
	private int[] directions; // this contains the x and y momentum

	public Balloon(int floorX, int floorY, FloorGrid grid) {
		this.instanceTexture = LoadTexture("chocolateCookie");
		this.grid = grid;
		this.startingFloorObject = grid.getFloor(floorX, floorY);
		this.width = GRID_SQUARE_SIZE;
		this.height = GRID_SQUARE_SIZE;
		this.health = 50;
		this.startingHealth = health;
		this.hiddenHealth = health;
		this.x = startingFloorObject.getxPos();
		this.y = startingFloorObject.getyPos();
		this.speed = 200;
		this.first = true;
		this.alive = true;
		this.turnigPoints = new ArrayList<TurningPoint>();
		this.directions = new int[2];
		// X direction
		this.directions[0] = 0;
		// Y direction
		this.directions[1] = 1;
		this.directions = findDirection(startingFloorObject);
		this.currentTurnPoint = 0;

		fillTurningPointArrayList();

	}
	
	public Balloon(int floorX, int floorY, FloorGrid grid, BalloonType type) {
		this.instanceTexture = LoadTexture(type.textureName);
		this.grid = grid;
		this.startingFloorObject = grid.getFloor(floorX, floorY);
		this.width = GRID_SQUARE_SIZE;
		this.height = GRID_SQUARE_SIZE;
		this.health = type.health;
		this.startingHealth = health;
		this.hiddenHealth = health;
		this.x = startingFloorObject.getxPos();
		this.y = startingFloorObject.getyPos();
		this.speed = type.speed;
		this.first = true;
		this.alive = true;
		this.turnigPoints = new ArrayList<TurningPoint>();
		this.directions = new int[2];
		// X direction
		this.directions[0] = 0;
		// Y direction
		this.directions[1] = 1;
		this.directions = findDirection(startingFloorObject);
		this.currentTurnPoint = 0;

		fillTurningPointArrayList();

	}

	public Balloon(Texture instanceTexture, Floor startingFloorObject, FloorGrid grid, int width, int height,
			float speed, float health) {

		this.instanceTexture = instanceTexture;
		this.grid = grid;
		this.startingFloorObject = startingFloorObject;
		this.width = width;
		this.height = height;
		this.health = health;
		this.startingHealth = health;
		this.hiddenHealth = health;
		this.x = startingFloorObject.getxPos();
		this.y = startingFloorObject.getyPos();
		this.speed = speed;
		this.first = true;
		this.alive = true;
		this.turnigPoints = new ArrayList<TurningPoint>();
		this.directions = new int[2];
		// X direction
		this.directions[0] = 0;
		// Y direction
		this.directions[1] = 1;
		this.directions = findDirection(startingFloorObject);
		this.currentTurnPoint = 0;

		fillTurningPointArrayList();

	}

	public void tick() {
		// Checks if this is the first tick of this instance of balloon if it is do
		// nothing

		if (first) {
			first = false;
		} else {
			if (isTurningPointReached()) {
				// Checks if there are more turn points ahead
				if (currentTurnPoint + 1 == turnigPoints.size()) {
					endReached();
				} else {
					currentTurnPoint++;
				}
			} else {
				// If the balloon is not at a direction then keep moving in the same direction
				x += Delta() * turnigPoints.get(currentTurnPoint).getxDirection() * speed;
				y += Delta() * turnigPoints.get(currentTurnPoint).getyDirection() * speed;
			}
//			if (pathKeepsGoing()) {
//				x += Delta() * directions[0];
//				y += Delta() * directions[1];
//			}	
		}
	}

	private boolean isTurningPointReached() {
		// Set by default to false
		boolean reached = false;

		Floor floor = turnigPoints.get(currentTurnPoint).getFloor();
		// Check if position reached tile within variance of 3
		// If the x of the balloon is 3 pixels ahead of the turnPoint then
		if (x > floor.getxPos() - 3
				// If the x is between the turn points top left within 3 pixels either way then
				&& x < floor.getxPos() + 3
				// If the y of the balloon is 3 pixels ahead of the turn point then
				&& y > floor.getyPos() - 3
				// If the y of the turn point is between 3 pixels either way of the turn point
				&& y < floor.getyPos() + 3) {
			// Then the turning point has been reached
			reached = true;
			// And the balloon is properly aligned to the grid
			x = floor.getxPos();
			y = floor.getyPos();
		}
		// Return the boolean if the end is reached or not
		return reached;
	}

	private void endReached() {
		// If the balloon has reached the end of the path then subtract a life from the
		// player
		Player.changeLifeAmount(-1);
		// and eliminate the balloon
		kill();

	}

	public void damage(int amount) {
		health -= amount;
		if (health <= 0) {
			kill();
			if (!calledChangeMoney) {
				Player.changeMoneyAmount(MONEY_FOR_KILL);
				calledChangeMoney = true;
			}
		}
	}

	private int[] findDirection(Floor startingFloor) {
		int[] dir = new int[2];

		Floor up = grid.getFloor(startingFloor.getXPlace(), startingFloor.getYPlace() - 1);
		Floor right = grid.getFloor(startingFloor.getXPlace() + 1, startingFloor.getYPlace());
		Floor down = grid.getFloor(startingFloor.getXPlace(), startingFloor.getYPlace() + 1);
		Floor left = grid.getFloor(startingFloor.getXPlace() - 1, startingFloor.getYPlace());

		if (startingFloor.getFloorType() == up.getFloorType() && directions[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
		} else if (startingFloor.getFloorType() == right.getFloorType() && directions[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
		} else if (startingFloor.getFloorType() == down.getFloorType() && directions[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
		} else if (startingFloor.getFloorType() == left.getFloorType() && directions[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2; // arbitrary value that can be identified to indicate no direction
			dir[1] = 2;
			// System.out.println("Cant move");
		}

		return dir;
	}

	private void fillTurningPointArrayList() {
		// this call dose 2 things at once i did this by accident and it worked
		// it adds a turning point to the array list and finds the turn as well as
		// setting the directions array
		// by calling findDirection
		turnigPoints.add(findTurningPoint(startingFloorObject, directions = findDirection(startingFloorObject)));

		int counter = 0;
		boolean cont = true;

		while (cont) {
			int[] currentDirection = findDirection(turnigPoints.get(counter).getFloor());
			// Check id a next direction exists end after 20 tries
			if (currentDirection[0] == 2 || counter == 20) { // Arbitrary number to stop it from crashing the game
				cont = false;
			} else {
				turnigPoints.add(findTurningPoint(turnigPoints.get(counter).getFloor(),
						directions = findDirection(turnigPoints.get(counter).getFloor())));
			}
			counter++;
		}

	}

	private TurningPoint findTurningPoint(Floor startingFloor, int[] dir) {
		Floor next = null;
		TurningPoint tp = null;

		// Boolean to decide if next checkpoint is found
		boolean found = false;

		// Integer to increment each loop
		int counter = 1;

		while (!found) {

			if (startingFloor.getXPlace() + dir[0] * counter == grid.getSquaresWide()
					|| startingFloor.getYPlace() + dir[1] * counter == grid.getSquaresHigh()
					|| startingFloor.getFloorType() != grid.getFloor(startingFloor.getXPlace() + dir[0] * counter,
							startingFloor.getYPlace() + dir[1] * counter).getFloorType()) {
				found = true;

				// move counter back to get the tile 1 before
				counter -= 1;
				next = grid.getFloor(startingFloor.getXPlace() + dir[0] * counter,
						startingFloor.getYPlace() + dir[1] * counter);
			}

			counter++;
		}
		tp = new TurningPoint(next, dir[0], dir[1]);
		return tp;
	}

	public void draw() {
		DrawQuadWithTexture(instanceTexture, x, y, width, height);
	}

	private void kill() {
		alive = false;
	}

	public void reduceHiddenHealth(float amount) {
		hiddenHealth -= amount;
	}

	public float getHiddenHealth() {
		return hiddenHealth;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMoneyReward() {
		return moneyReward;
	}

	public void setMoneyReward(int moneyReward) {
		this.moneyReward = moneyReward;
	}

	public int getCurrentTurnPoint() {
		return currentTurnPoint;
	}

	public void setCurrentTurnPoint(int currentTurnPoint) {
		this.currentTurnPoint = currentTurnPoint;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getStartingHealth() {
		return startingHealth;
	}

	public void setStartingHealth(float startingHealth) {
		this.startingHealth = startingHealth;
	}

	public Texture getInstanceTexture() {
		return instanceTexture;
	}

	public void setInstanceTexture(Texture instanceTexture) {
		this.instanceTexture = instanceTexture;
	}

	public Floor getStartTile() {
		return startingFloorObject;
	}

	public void setStartTile(Floor startTile) {
		this.startingFloorObject = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public FloorGrid getGrid() {
		return grid;
	}

	public void setGrid(FloorGrid grid) {
		this.grid = grid;
	}

	public int[] getDirections() {
		return directions;
	}

	public void setDirections(int[] directions) {
		this.directions = directions;
	}

}
