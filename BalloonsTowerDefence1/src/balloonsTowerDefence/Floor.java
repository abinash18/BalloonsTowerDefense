/**
 * Abinash Singh
 * Balloons Tower Defence Floor 
 */
package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;

import static other.DrawInFrame.*;

public class Floor {

	private float xPos, yPos;
	private int width, height;
	private Texture floorTexture;
	private FloorType floor;
	private boolean occupied;

	public Floor(FloorType floorType, float x, float y, int width, int height) {

		this.xPos = x;
		this.yPos = y;
		this.width = width;
		this.height = height;
		this.floorTexture = LoadTexture(floorType.textureName);
		this.floor = floorType;

		if (floorType.builds) {
			this.occupied = false;
		} else {
			this.occupied = true;
		}

	}

	public void DrawFloor() {

		DrawQuadWithTexture(floorTexture, xPos, yPos, width, height);

	}

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
