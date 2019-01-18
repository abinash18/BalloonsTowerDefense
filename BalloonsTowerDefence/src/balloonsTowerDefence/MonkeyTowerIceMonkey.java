package balloonsTowerDefence;

import java.util.concurrent.CopyOnWriteArrayList;

public class MonkeyTowerIceMonkey extends MonkeyTower {

	public MonkeyTowerIceMonkey(Floor startingFloor, CopyOnWriteArrayList<Balloon> balloons) {
		super(MonkeyTowerType.IceMonkey, startingFloor, balloons);
	}

	@Override
	public void shoot(Balloon target) {
		//super.darts.add(new DartNormal(super.type.dartType, super.Target, super.getX(), super.getY(), 32, 32));
		super.acquireTarget();
		
		super.getTarget().setSpeed(4);
		
	}

}
