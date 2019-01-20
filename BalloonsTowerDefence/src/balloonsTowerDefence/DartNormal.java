package balloonsTowerDefence;

import static other.DrawInFrame.DrawQuadWithRotatedTexture;

public class DartNormal extends Dart {

	public DartNormal(DartType dartType, Balloon target, float x, float y) {
		super(dartType, target, x, y);
	}

	@Override
	public void damage() {
		// super.setAlive(false);
		super.damage();
	}

	private float calculateAngle() {
		// Ineededtusewikipedia to get this i
		// did not learn this in math and needed
		// to use a couplle youtube tutorials to
		// find out how to do the math in java
		// aswell thank fully one had the exact
		// way of doing this
		double angleTemp = Math.atan2(super.getTarget().getY() - super.getY(), super.getTarget().getX() - super.getX());

		return (float) Math.toDegrees(angleTemp) + 90;
	}

	@Override
	public void draw() {
		// float angle = calculateAngle();
		DrawQuadWithRotatedTexture(super.getTexture(), super.getX(), super.getY(), super.getTexture().getImageWidth(),
				super.getTexture().getImageHeight(), super.angle);
	}

}
