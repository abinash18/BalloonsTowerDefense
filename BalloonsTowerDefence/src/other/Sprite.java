package other;

public class Sprite {

	private final String name;
	private final int x, y, w, h;
	
	public Sprite(String name, int x, int y, int w, int h) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
}
