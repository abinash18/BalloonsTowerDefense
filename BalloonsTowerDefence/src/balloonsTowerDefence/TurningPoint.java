/**
 * Abinash Singh 
 * Balloons Tower Defense TurningPoint 
 */
package balloonsTowerDefence;

/**
 * This class stores the position for the turn point for the balloons 
 */
public class TurningPoint {
	
	private Floor floor;
	private int xDirection, yDirection;
	
	/**
	 * constructors 
	 * Creates a object representing the turning point
	 */
	public TurningPoint(Floor floor, int xDirection, int yDirection) {
		this.floor = floor;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	public Floor getFloor() {
		return floor;
	}

	public int getxDirection() {
		return xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}
}
