/**
 * Abinash Singh
 * Balloons Tower Defense FloorGrid
 */
package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;

/**
 * Handles the drawing of the floors on the screen
 */
public class FloorGrid {

	// this array represents the map that is being drawn on the screen
	public Floor[][] mapMatrix;
	private int squaresWide, squaresHigh;

	/**
	 * constructor Fills the mapMatrix with the newMapMatrix parameter by reading
	 * each number and corresponding it to the type of floor it represents also
	 * creates a floor object that represents it and fills the mapMatrix with it
	 */
	public FloorGrid(int[][] newMapMatrix) {

		this.squaresWide = newMapMatrix[0].length;
		this.squaresHigh = newMapMatrix.length;
		mapMatrix = new Floor[squaresWide][squaresHigh];

		for (int y = 0; y < mapMatrix.length; y++) {
			for (int x = 0; x < mapMatrix[y].length; x++) {
				switch (newMapMatrix[x][y]) { // the x is first becouse in the int array the floor grid
												// is mirrored so intead of having the origin on the top left the origin
												// would be fliped and different
				case 0: {
					mapMatrix[y][x] = new Floor(FloorType.Grass, y * GRID_SQUARE_SIZE, x * GRID_SQUARE_SIZE,
							GRID_SQUARE_SIZE, GRID_SQUARE_SIZE);
					break;
				}
				case 1: {
					mapMatrix[y][x] = new Floor(FloorType.Path, y * GRID_SQUARE_SIZE, x * GRID_SQUARE_SIZE,
							GRID_SQUARE_SIZE, GRID_SQUARE_SIZE);
					break;
				}
				case 2: {
					mapMatrix[y][x] = new Floor(FloorType.Water, y * GRID_SQUARE_SIZE, x * GRID_SQUARE_SIZE,
							GRID_SQUARE_SIZE, GRID_SQUARE_SIZE);
					break;
				}
				}
			}
		}
	}

	/**
	 * Sets the floor where specified pre: none post: sets the floor where specified
	 * by the x and y coord and the floortype
	 */
	public void setFloor(int xCoord, int yCoord, FloorType type) {
		mapMatrix[xCoord][yCoord] = new Floor(type, xCoord * GRID_SQUARE_SIZE, yCoord * GRID_SQUARE_SIZE,
				GRID_SQUARE_SIZE, GRID_SQUARE_SIZE);
	}

	/**
	 * constructor Fills the entire matrix with grass floors
	 */
	public FloorGrid() {
		this.squaresWide = 20;
		this.squaresHigh = 15;
		mapMatrix = new Floor[squaresWide][squaresHigh];
		for (int y = 0; y < mapMatrix.length; y++) {
			for (int x = 0; x < mapMatrix[y].length; x++) {
				mapMatrix[y][x] = new Floor(FloorType.Grass, y * GRID_SQUARE_SIZE, x * GRID_SQUARE_SIZE,
						GRID_SQUARE_SIZE, GRID_SQUARE_SIZE);
			}
		}
	}

	/**
	 * Retrurns the floor object represented by the xCoord and the yCoord pre: none
	 * post: A floor object is returned represented by the x and y coords
	 */
	public Floor getFloor(int xCoord, int yCoord) {

		if (xCoord < squaresWide && yCoord < squaresHigh && xCoord > -1 && yCoord > -1) {
			return (mapMatrix[xCoord][yCoord]);
		} else {
			return new Floor(FloorType.Null, 0, 0, 0, 0);
		}

	}

	/**
	 * Draws the grid matrix on the screen by looping throug the mapMatrix and
	 * temporary mapping each floor to a temp floor object
	 */
	public void drawGridOnScreen() {

		for (int y = 0; y < mapMatrix.length; y++) {
			for (int x = 0; x < mapMatrix[y].length; x++) {

				Floor temp = mapMatrix[y][x];
				DrawQuadWithTexture(temp.getFloorTexture(), temp.getxPos(), temp.getyPos(), temp.getWidth(),
						temp.getHeight());

			}
		}

	}

	/**
	 * Draws the mapMatrix on the screen with the specified floor size
	 */
	public void drawGridOnScreenMapPreview(int floorSize, int xOffset, int yOffset) {

		for (int y = 0; y < mapMatrix.length; y++) {
			for (int x = 0; x < mapMatrix[y].length; x++) {

				Floor temp = mapMatrix[y][x];
				DrawQuadWithTexture(temp.getFloorTexture(), (temp.getxPos() / GRID_SQUARE_SIZE) * floorSize + xOffset,
						(temp.getyPos() / GRID_SQUARE_SIZE) * floorSize + yOffset, floorSize, floorSize);

			}
		}

	}

	/**
	 * Generated Getters and Setters
	 */

	public int getSquaresWide() {
		return squaresWide;
	}

	public void setSquaresWide(int squaresWide) {
		this.squaresWide = squaresWide;
	}

	public int getSquaresHigh() {
		return squaresHigh;
	}

	public void setSquaresHigh(int squaresHigh) {
		this.squaresHigh = squaresHigh;
	}

}
