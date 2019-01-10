package userInterface;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.HEIGHT;
import static other.DrawInFrame.WIDTH;

import org.newdawn.slick.opengl.Texture;

public class ProgressBar {

	private Texture barTexture, borderTexture, bgTexture;
	private int x, y;
	private float width, height, barProgress;
	private boolean isDone;
	private String name;
	
	public ProgressBar(String name, Texture barTexture, Texture borderTexture, Texture bgTexture, int x, int y, float width,
			float height) {
		this.barTexture = barTexture;
		this.borderTexture = borderTexture;
		this.bgTexture = bgTexture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.barProgress = 0;
		this.name = name;
	}
	
	// (totalImages - imagesToLoad.size()) / (float)totalImages)
	
	public void tick(){
		if(!isDone){
			draw();
		}
	}
	
	private void draw(){
		DrawQuadWithTexture(bgTexture, 0, 0, WIDTH, HEIGHT);
		DrawQuadWithTexture(borderTexture, 50, HEIGHT - HEIGHT / 8, 1350, 25);
		DrawQuadWithTexture(barTexture, 50, HEIGHT - HEIGHT / 8 + 4, barProgress * 90, 16);
	}
	
	public void done(){
		isDone = true;
	}
	
	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Texture getBarTexture() {
		return barTexture;
	}

	public void setBarTexture(Texture barTexture) {
		this.barTexture = barTexture;
	}

	public Texture getBorderTexture() {
		return borderTexture;
	}

	public void setBorderTexture(Texture borderTexture) {
		this.borderTexture = borderTexture;
	}

	public Texture getBgTexture() {
		return bgTexture;
	}

	public void setBgTexture(Texture bgTexture) {
		this.bgTexture = bgTexture;
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getBarProgress() {
		return barProgress;
	}

	public void setBarProgress(float barProgress) {
		this.barProgress = barProgress;
	}

}
