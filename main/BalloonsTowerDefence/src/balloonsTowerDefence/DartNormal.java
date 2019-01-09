package balloonsTowerDefence;

public class DartNormal extends Dart {

	public DartNormal(DartType dartType, Balloon target, float x, float y, int width, int height) {
		super(dartType, target, x, y, width, height);
	}

	@Override
	public void damage() {
		// super.setAlive(false);
		super.damage();
	}

}
