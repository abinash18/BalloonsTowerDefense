package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.LoadTexture;
import static other.DrawInFrame.WIDTH;
import static other.Timer.Delta;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Balloon implements Entity {

	// The amount of money rewarded for each kill this is just a constant but it can
	// be changed in each underlying class
	private static final int MONEY_FOR_KILL = 10;
	// incremnt variables
	private int width, height, moneyReward, currentTurnPoint;
	private float speed, x, y, health, startingHealth, hiddenHealth;
	// the texture for this instace of the balloon
	private Texture instanceTexture;
	// this type contains all default values for the specific type of balloon
	public BalloonType type;
	// private Texture healthBg, healthFg, healthBorder; //heathBar
	// where the balloon spawns
	private Floor startingFloorObject;
	// these just ensure that the balloon is rendered and updated properly
	private boolean first,
			// alive indicates if this balloon is poped and if it should be removed from the
			// balloon list in the current round
			alive;
	// balloon needs a copy of the current grid to traverse the path
	private FloorGrid grid;
	// there was a bug that every object that had this as a target would send money
	// to the player every time it hit this just ensures it is only called once
	private boolean calledChangeMoney = false;
	// the list of places the balloon needs to turn
	private ArrayList<TurningPoint> turnigPoints;
	/*
	 * directions[0] contains the factor by which the balloon should move in the x
	 * axis directions[1] contains the factor by which the balloon should move in
	 * the y axis i.e. directions[0] = 0; directions[1] = 1; this.x += speed *
	 * Delta() * directions[0]; this would produce a 0 x axis movement this.y +=
	 * speed * Delta() * directions[1]; this would produce a delta * speed y axis
	 * movement
	 */
	private int[] directions; // this contains the x and y momentum

	/**
	 * constructor a balloon object is created with the following as parameters int
	 * floorX, int floorY, FloorGrid grid, BalloonType type
	 * 
	 * the balloon is spawned on the floorX and floorY coordinates and the default
	 * values provided by the type specified
	 */
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
		this.type = type;
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

	/**
	 * constructor a balloon object is created with the following as parameters
	 * Texture instanceTexture, Floor startingFloorObject, FloorGrid grid, int
	 * width, int height, float speed, float health
	 * 
	 * the balloon is created with a texture and spawned on the startingFloorObject
	 * with a width and height and speed and health
	 */
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
		this.x = startingFloorObject.getxPos(); // starting x
		this.y = startingFloorObject.getyPos(); // starting y
		this.speed = speed;
		this.first = true;
		this.alive = true;
		this.turnigPoints = new ArrayList<TurningPoint>();
		this.directions = new int[2];
		// X direction
		this.directions[0] = 0;
		// Y direction
		this.directions[1] = 1;
		// returns the first set of directions to follow when spawned
		this.directions = findDirection(startingFloorObject);
		this.currentTurnPoint = 0; // sets the current turning pointy to zero because it is starting off

		// fills the array list with all the turning point objects
		fillTurningPointArrayList();

	}

	/*
	 * tick is called every loop of the main game loop to update the current factors
	 * of the balloons position and checks if the balloon is alive and gets the next
	 * turning point and moves the balloon depending on the speed and the directions
	 * array
	 */
	public void tick() {

		// there was a bug if the player is doing tasks in the back ground it skips a
		// few steps and moves the balloon off the path
		if (x > WIDTH || y > HEIGHT) {
			alive = false;
		}

		// Checks if this is the first tick of this instance of balloon if it is do
		// nothing because it glitches and moves it a checkpoint ahead of it self
		if (first) {
			first = false;
		} else {
			// if the check point is reached then move the increment one ahead
			if (isTurningPointReached()) {
				// Checks if there are more turn points ahead
				if (currentTurnPoint + 1 == turnigPoints.size()) {
					// if there are no more then the endreached is called
					endReached();
				} else {
					// else increments the counter
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

	/**
	 * returns true if the turning point has been reached
	 */
	private boolean isTurningPointReached() {
		// Set by default to false
		boolean reached = false;
		int range = 7;
		// returns the current floor under the current turning point
		Floor floor = turnigPoints.get(currentTurnPoint).getFloor();
		// Check if position reached tile within variance of 3
		// If the x of the balloon is 3 pixels ahead of the turnPoint then
		if (x > floor.getxPos() - range
				// If the x is between the turn points top left within 3 pixels either way then
				&& x < floor.getxPos() + range
				// If the y of the balloon is 3 pixels ahead of the turn point then
				&& y > floor.getyPos() - range
				// If the y of the turn point is between 3 pixels either way of the turn point
				&& y < floor.getyPos() + range) {
			// Then the turning point has been reached
			reached = true;
			// And the balloon is properly aligned to the grid
			x = floor.getxPos();
			y = floor.getyPos();
		}
		// Return the boolean if the end is reached or not
		return reached;
	}

	/**
	 * This pops the balloon if it makes it through the entire way without beeing
	 * poped and subtracts a life from the player
	 */
	private void endReached() {
		// If the balloon has reached the end of the path then subtract a life from the
		// player
		Player.changeLifeAmount(-1);
		// and pop the balloon
		kill();

	}

	/**
	 * damages the balloon object and requires a damage parameter to subtract from
	 * the health if the health is 0 or less then added the specified amount of
	 * money to the player object
	 */
	public void damage(int amount) {
		// subtracts the amount of damage from the heath variable
		health -= amount;
		// if the health is less than 0 then pop the balloon
		if (health <= 0) {
			// pop!
			kill();
			// only if the calledChangeMoney is false change the money amount on the player
			if (!calledChangeMoney) {
				Player.changeMoneyAmount(MONEY_FOR_KILL);
				calledChangeMoney = true; // set the calledChangeMoney to true for the next dart
			}
		}
	}

	/**
	 * findDirection returns a int array which contains two values represent if the
	 * balloon should move in either axis
	 * 
	 * post: returns a direction array that contains values either 1, 2, 0, -1
	 * representing movement if the value is 1 then the balloon moves by
	 * mulltiplieing the speed and delta by 1 otherwise it returns 2 if the end is
	 * reached and 0 if the movement is not required in either the x or y axis also
	 * it is -1 one if it is going backwards or against the axis in this case if it
	 * is -1 on the 1st index then it is going up on the y axis
	 * 
	 */
	private int[] findDirection(Floor startingFloor) {
		// temporary array for the values
		int[] dir = new int[2];

		// floor above
		Floor up = grid.getFloor(startingFloor.getXPlace(), startingFloor.getYPlace() - 1);
		// floor to the right
		Floor right = grid.getFloor(startingFloor.getXPlace() + 1, startingFloor.getYPlace());
		// floor to the down
		Floor down = grid.getFloor(startingFloor.getXPlace(), startingFloor.getYPlace() + 1);
		// floor to the left
		Floor left = grid.getFloor(startingFloor.getXPlace() - 1, startingFloor.getYPlace());

		// if the floor above is the same we are on and the y axis movement is not
		// already 1
		if (startingFloor.getFloorType() == up.getFloorType() && directions[1] != 1) {
			// then set the y axis movement to -1
			dir[0] = 0;
			dir[1] = -1;
			// if the floor to the right is the same we are on and the x axis movement is
			// not already -1
		} else if (startingFloor.getFloorType() == right.getFloorType() && directions[0] != -1) {
			// then set the x axis movement to 1
			dir[0] = 1;
			dir[1] = 0;
			// if the floor bellow is the same we are on and the y axis movement is not
			// already -1
		} else if (startingFloor.getFloorType() == down.getFloorType() && directions[1] != -1) {
			// then set the y axis movement to 1
			dir[0] = 0;
			dir[1] = 1;
			// if the floor to the left is the same we are on and the x axis movement is not
			// already -1
		} else if (startingFloor.getFloorType() == left.getFloorType() && directions[0] != 1) {
			// then set the x axis movement to -1
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2; // arbitrary value that can be identified to indicate no direction
			dir[1] = 2;
			// System.out.println("Cant move");
		}

		// return the filled array
		return dir;
	}

	/**
	 * Fills the array list of turning point objects containing all directional
	 * object for maneuvering the balloon object it loops through util it finds the
	 * end of the path using a basic guess and check algorithm path finding
	 * algorithm,
	 * 
	 * post: the turning point list is filled with all the turning points discovered
	 * in the map
	 * 
	 */
	private void fillTurningPointArrayList() {
		// this call dose 2 things at once i did this by accident and it worked
		// it adds a turning point to the array list and finds the turn as well as
		// setting the directions array
		// by calling findDirection
		turnigPoints.add(findTurningPoint(startingFloorObject, directions = findDirection(startingFloorObject)));

		// Simple counter representing what the check point loop is on
		int counter = 0;
		boolean cont = true; // boolean to represent when to stop looking

		// Loops util the cont var is false
		while (cont) {
			int[] currentDirection = findDirection(turnigPoints.get(counter).getFloor());
			// Check id a next direction exists end after 20 tries
			if (currentDirection[0] == 2 || counter == 20) { // Arbitrary number to stop it from crashing the game
				// if reached then stop the loop
				cont = false;
			} else {
				// else add the current current turning point to array by calling the find
				// turning point method
				turnigPoints.add(findTurningPoint(turnigPoints.get(counter).getFloor(),
						directions = findDirection(turnigPoints.get(counter).getFloor())));
			}
			counter++;
		}

	}

	/**
	 * Returns an object representing the current turning point by looping through
	 * all possibilites of the checkpoint's location up, down, left, right from the
	 * current location starting floor is the object currently on
	 * 
	 * post: an turning point object is returned
	 * 
	 */
	private TurningPoint findTurningPoint(Floor startingFloor, int[] dir) {
		Floor next = null;
		TurningPoint tp = null;

		// Boolean to decide if next checkpoint is found
		boolean found = false;

		// Integer to increment each loop
		int counter = 1;

		/*
		 * In summary this algorithm is more complicated to explain then to make it
		 * basically checks if there is a turning point in the direction the balloon is
		 * going by looking at the dir array passed as a parameter and then sets found
		 * to true if the turning point is found. for example if the x axis movement is
		 * 0 and the y axis is 1 then this algorithm checks the floor down 1 times the
		 * counter so the first one is just 1 down and the next loop the counter is 2
		 * and so it checks the 2nd floor down and if it is a turning point then found
		 * is set to true and the loop is broken i found some tutorial talking about
		 * this and provided some pseudo code but i couldn't find it again so I am sorry
		 * i can't reference it here
		 */

		// Loops through until found is true
		while (!found) {

			// If current floors x plus the current dir on the x axis times counter equals
			// the size of the x axis
			// this checks if the algorithm has searched all of the x axis
			if (startingFloor.getXPlace() + dir[0] * counter == grid.getSquaresWide()
					// If current floors y plus the current dir on the y axis times counter equals
					// the size of the y axis
					// this checks if the algorithm has searched all of the y axis
					|| startingFloor.getYPlace() + dir[1] * counter == grid.getSquaresHigh()
					// if the current floor is not the same as the floor found by timesing the
					// current floors x pos by the x direction times the counter this returns the
					// floor until the loop reaches the end of the maps length
					|| startingFloor.getFloorType() != grid.getFloor(startingFloor.getXPlace() + dir[0] * counter,
							// if the current floor is not the same as the floor found by timesing the
							// current floors y pos by the y direction times the counter this returns the
							// floor until the loop reaches the end of the maps height
							startingFloor.getYPlace() + dir[1] * counter).getFloorType()) {
				found = true;

				// move counter back to get the tile 1 before
				counter -= 1;
				next = grid.getFloor(startingFloor.getXPlace() + dir[0] * counter,
						startingFloor.getYPlace() + dir[1] * counter);
			}

			counter++;
		}
		tp = new TurningPoint(next, dir[0], dir[1]); // the turning point is returned
		return tp;
	}

//	//
//	private boolean changeType(BalloonType typeNew) {
//		if (typeNew.nextBalloonType != null) {
//			type = typeNew.nextBalloonType;
//			instanceTexture = LoadTexture(typeNew.textureName);
//			health = typeNew.health;
//			startingHealth = health;
//			hiddenHealth = health;
//			speed = typeNew.speed;
//			return false;
//		} else {
//			return true;
//		}
//	}

	/**
	 * Draws the object on the screen represented by the texture on the screen using
	 * a static method from DrawInFrame
	 */
	public void draw() {
		DrawQuadWithTexture(instanceTexture, x, y, width, height);
	}

	/**
	 * sets alive to false so that the balloon is considered dead and is removed
	 * from the round
	 */
	public void kill() {
		// if (changeType(BalloonType.RedBalloon)) {
		alive = false;
		// }
	}

	/**
	 * reduces the health of the balloon and requires a amount to do by so.
	 */
	public void reduceHiddenHealth(float amount) {
		hiddenHealth -= amount;
	}

	/**
	 * Generated getters and setters
	 */

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
