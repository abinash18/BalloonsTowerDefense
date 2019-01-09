package balloonsTowerDefence;

import java.util.concurrent.CopyOnWriteArrayList;

public class MonkeyTowerDartMonkey extends MonkeyTower {

	public MonkeyTowerDartMonkey(MonkeyTowerType dartmonkey, Floor startingFloor, CopyOnWriteArrayList<Balloon> balloonsList) {
		super(dartmonkey, startingFloor, balloonsList);
	}

	@Override
	public void shoot(Balloon target) {
		super.darts
				.add(new DartNormal(super.type.dartType, super.Target, super.getX(), super.getY(), 32, 32));
	}
}
