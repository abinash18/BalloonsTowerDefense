package balloonsTowerDefence;

public class TurningPoint {
	
	private Floor floor;
	private int xDirection, yDirection;
	
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
