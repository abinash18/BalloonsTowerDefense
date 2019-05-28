package BTDOnlineToolKit;

import com.esotericsoftware.kryonet.*;

import packets.GeneralPackets.Ping;

public class NetworkHandler extends Listener {

	private Client clientObject;
	private GameOnline go;

	public NetworkHandler(GameOnline go) {
		this.go = go;
		this.clientObject = go.getClient();
	}

	@Override
	public void connected(Connection connection) {
		clientObject.updateReturnTripTime();
		connection.updateReturnTripTime();
	}

	@Override
	public void disconnected(Connection connection) {
		super.disconnected(connection);
	}

	@Override
	public void idle(Connection connection) {
		super.idle(connection);
	}

	@Override
	public void received(Connection con, Object packet) {
		if (packet instanceof Ping) {
			Ping ping = (Ping) packet;
			if (ping.returned) {
				go.getGameUserInterface().getLabel("ping").setText("Ping: " + con.getReturnTripTime() + "ms");
			}
		}
	}

}
