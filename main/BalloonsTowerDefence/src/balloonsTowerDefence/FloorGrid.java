/**
 * Abinash Singh
 * Balloons Tower Defense FloorGrid
 */
package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;

public class FloorGrid {

	public Floor[][] mapMatrix;
	private int squaresWide, squaresHigh;

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

	public void setFloor(int xCoord, int yCoord, FloorType type) {
		mapMatrix[xCoord][yCoord] = new Floor(type, xCoord * GRID_SQUARE_SIZE, yCoord * GRID_SQUARE_SIZE,
				GRID_SQUARE_SIZE, GRID_SQUARE_SIZE);
	}

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

	public Floor getFloor(int xCoord, int yCoord) {

		if (xCoord < squaresWide && yCoord < squaresHigh && xCoord > -1 && yCoord > -1) {
			return (mapMatrix[xCoord][yCoord]);
		} else {
			return new Floor(FloorType.Null, 0, 0, 0, 0);
		}

	}

	public void drawGridOnScreen() {

		for (int y = 0; y < mapMatrix.length; y++) {
			for (int x = 0; x < mapMatrix[y].length; x++) {

				Floor temp = mapMatrix[y][x];
				DrawQuadWithTexture(temp.getFloorTexture(), temp.getxPos(), temp.getyPos(), temp.getWidth(),
						temp.getHeight());

			}
		}

	}

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
