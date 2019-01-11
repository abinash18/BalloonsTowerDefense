package balloonsTowerDefence;

public class BalloonBlue extends Balloon {

	public BalloonBlue(int floorX, int floorY, FloorGrid grid, BalloonType type) {
		super(floorX, floorY, grid, type);
	}

	@Override
	public void kill() {
		super.changeType();
	}

}
