package balloonsTowerDefence;

import org.newdawn.slick.opengl.Texture;

public class BalloonRed extends Balloon {

	public BalloonRed(int floorX, int floorY, FloorGrid grid, BalloonType type) {
		super(floorX, floorY, grid, type);
	}

	public BalloonRed(Texture instanceTexture, Floor startingFloorObject, FloorGrid grid, int width, int height,
			float speed, float health) {
		super(instanceTexture, startingFloorObject, grid, width, height, speed, health);
	}

}
