package userInterface;

import static other.DrawInFrame.*;

import org.newdawn.slick.opengl.Texture;

public class Image {

	private int x, y, width, height, xOffset, yOffset;
	private Texture tex;
	private String name;

	public Image(String name, Texture tex, int x, int y, int width, int height, int xOffset, int yOffset) {
		super();
		this.name = name;
		this.tex = tex;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public Image(String name, Texture tex, int x, int y, int width, int height) {
		super();
		this.name = name;
		this.tex = tex;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xOffset = 0;
		this.yOffset = 0;
	}

	public void tick() {
		draw();
	}

	private void draw() {
		if (tex != null) {
			DrawQuadWithTexture(tex, x + xOffset, y + yOffset, width, height);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
