package packets;

public class GeneralPackets {

	public static class Ping {
		public int ping;
		public boolean returned;
	}

	public static class Message {
		public String m;
	}

	public static class ConnectRequest {
		public String password, userName;
	}

}
