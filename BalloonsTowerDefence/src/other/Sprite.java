package other;

import org.newdawn.slick.opengl.Texture;

public class Sprite {

	private String name;
	private Texture tex;

	public Sprite(String name, String path) {
		this.name = name;
		this.tex = DrawInFrame.LoadTexture(path);
	}
	
	public Sprite(String name, Texture tex) {
		this.name = name;
		this.tex = tex;
	}

	public String getName() {
		return name;
	}

	public Texture getTex() {
		return tex;
	}

}
