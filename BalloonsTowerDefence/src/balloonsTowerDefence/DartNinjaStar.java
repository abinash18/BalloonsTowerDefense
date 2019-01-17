package balloonsTowerDefence;

public class DartNinjaStar extends Dart {

	public DartNinjaStar(DartType dartType, Balloon target, float x, float y, int width, int height) {
		super(dartType, target, x, y, width, height);
	}

//	@Override
//	public void draw() {
//
//		DrawQuadWithRotatedTexture(super.getTexture(), super.getX(), super.getY(), super.getWidth(), super.getHeight(),
//				(float) super.getDartType().rotationRate * Delta() * 100);
//
//	}

}
