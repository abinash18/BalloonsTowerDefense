package balloonsTowerDefence;

import java.util.concurrent.CopyOnWriteArrayList;

public class MonkeyTowerSuperMonkey extends MonkeyTower {

	public MonkeyTowerSuperMonkey(Floor startingFloor, CopyOnWriteArrayList<Balloon> balloons) {
		super(MonkeyTowerType.SuperMonkey, startingFloor, balloons);
	}

	@Override
	public void shoot(Balloon target) {
		super.setAngle(super.getAngle());
		super.darts.add(new DartNormal(super.type.dartType, super.Target, super.getX() - super.getWidth() / 2,
				super.getY() - super.getHeight() / 2, super.type.dartType.texture.getImageWidth(),
				super.type.dartType.texture.getImageHeight()));
		super.acquireTarget();
	}

}
