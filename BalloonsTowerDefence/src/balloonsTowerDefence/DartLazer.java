package balloonsTowerDefence;

import static other.DrawInFrame.*;

public class DartLazer extends Dart {

	public DartLazer(DartType dartType, Balloon target, float x, float y, int width, int height) {
		super(dartType, target, x, y, width, height);
	}

	private float calculateAngle() {

		double angleTemp = Math.atan2(super.getTarget().getY() - super.getY(), super.getTarget().getX() - super.getX()); // I
																															// needed
																															// to
																															// use
																															// wikipedia
																															// to
																															// get
																															// this
																															// i
		// did not learn this in math and needed
		// to use a couplle youtube tutorials to
		// find out how to do the math in java
		// aswell thank fully one had the exact
		// way of doing this
		return (float) Math.toDegrees(angleTemp) + 90;
	}

	@Override
	public void draw() {
		float angle = calculateAngle();
		DrawQuadWithRotatedTexture(super.getTexture(), super.getX(), super.getY(), super.getWidth(), super.getHeight(),
				angle);
	}

}
