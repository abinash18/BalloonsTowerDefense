/**
 * Abinash Singh
 * Balloons Tower Defence Floor 
 */
package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.GRID_SQUARE_SIZE;
import static other.DrawInFrame.LoadTexture;

import org.newdawn.slick.opengl.Texture;

/**
 * Floor object class represents every floor drown on the screen by floor grid
 */
public class Floor {

	private float xPos, yPos;
	private int width, height;
	private Texture floorTexture;
	// The type of floor that is being drawn
	private FloorType floor;
	// if the floor has a tower placed on it or not
	private boolean occupied;

	public Floor(FloorType floorType, float x, float y, int width, int height) {

		this.xPos = x;
		this.yPos = y;
		this.width = width;
		this.height = height;
		this.floorTexture = LoadTexture(floorType.textureName);
		this.floor = floorType;

		// if the type of floor can be built on
		if (floorType.builds) {
			// set the bulfding to true
			this.occupied = false;
		} else {
			// other wise set to false
			this.occupied = true;
		}

	}

	/**
	 * Draws the floor object on the screen which is represented by the floor
	 * texture and its attributes
	 */
	public void DrawFloor() {

		DrawQuadWithTexture(floorTexture, xPos, yPos, width, height);

	}

	/**
	 * Generated Getters And Setters 
	 */
	
	public int getXPlace() {
		return (int) xPos / GRID_SQUARE_SIZE;
	}

	public int getYPlace() {
		return (int) yPos / GRID_SQUARE_SIZE;
	}

	public FloorType getFloorType() {
		return floor;
	}

	public void setFloorType(FloorType floor) {
		this.floor = floor;
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
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

	public Texture getFloorTexture() {
		return floorTexture;
	}

	public void setFloorTexture(Texture floorTexture) {
		this.floorTexture = floorTexture;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

}
