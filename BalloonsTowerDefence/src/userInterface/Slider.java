package userInterface;

import static other.DrawInFrame.DrawQuadWithTexture;
import static other.DrawInFrame.HEIGHT;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import userInterface.UserInterface.Menu;

public class Slider {

	private int sliderBarX, sliderBarY, sliderPointerX, sliderPointerY, width, height, sliderBarWidth, min, max, step,
			currentValue, x, y, pointerHeight, pointerWidth, pointerPos = 0, pointerValue;
	private Texture sliderBarTex, sliderPointerTex;
	private String sliderName;
	private boolean selected;
	// private Button sliderPointer;

	public Slider(Texture barTexture, Texture pointerTexture, String sliderName, int minVal, int maxVal, int step,
			int x, int y) {
		// this.sliderBarTex = LoadTexture("slider_bar");
		// this.sliderPointerTex = LoadTexture("slider_pointer");
		this.sliderBarTex = barTexture;
		this.sliderPointerTex = pointerTexture;
		this.sliderName = sliderName;
		this.min = minVal;
		this.max = maxVal;
		this.step = step;
		this.sliderBarX = 100;
		this.sliderBarY = 100;
		this.sliderPointerX = pointerPos + sliderBarX;
		this.sliderPointerY = 60;
		this.sliderBarWidth = 1000;
		this.pointerHeight = 90;
		this.pointerWidth = 32;
		this.selected = false;
		this.pointerValue = 0;
		calculatePos();

		
	}

	public void tick() {
		draw();
		calculatePos();
		if (checkHover() && Mouse.isButtonDown(0)) {
			calculatePointerValue();
			if (pointerValue < max && pointerValue > min){
				pointerPos = Mouse.getX();
			}
		}

		// UI.createMenu("test", 10, 10, sliderBarWidth, sliderPointerY, 1, 0);
		//
		// sliderMenu = UI.getMenu("test");
		// sliderMenu.addMenuSlider("", barTexture, pointerTexture, minVal,
		// maxVal, step, pointerPos);

	}

	private void calculatePointerValue() {

		pointerValue = pointerPos / max;

	}

	private void calculatePos() {

		pointerPos =
		// This calculates the x pos of the middle of the pointer
		((pointerWidth / 2) + sliderPointerX)
				// This finds the positive value of
				- sliderBarX;

	}

	public boolean checkHover() {

		float mouseY = HEIGHT - Mouse.getY() - 1;

		if (Mouse.getX() > sliderPointerX && Mouse.getX() < sliderPointerX + pointerWidth && mouseY > sliderPointerY
				&& mouseY < sliderPointerY + pointerHeight) {

			return true;

		}
		return false;
	}

	private void draw() {
		DrawQuadWithTexture(sliderBarTex, sliderBarX, sliderBarY, sliderBarWidth, sliderBarTex.getImageHeight());
		DrawQuadWithTexture(sliderPointerTex, sliderPointerX, sliderPointerY, pointerWidth, pointerHeight);
	}

	public int getPointerValue() {
		return pointerValue;
	}

	public void setPointerValue(int pointerValue) {
		this.pointerValue = pointerValue;
	}

	public int getPointerPos() {
		return pointerPos;
	}

	public void setPointerPos(int pointerPos) {
		this.pointerPos = pointerPos;
	}

	public String getSliderName() {
		return sliderName;
	}

	public void setSliderName(String sliderName) {
		this.sliderName = sliderName;
	}

	public int getSliderPointerX() {
		return sliderPointerX;
	}

	public void setSliderPointerX(int sliderPointerX) {
		this.sliderPointerX = sliderPointerX;
	}

	public int getSliderPointerY() {
		return sliderPointerY;
	}

	public void setSliderPointerY(int sliderPointerY) {
		this.sliderPointerY = sliderPointerY;
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

	public int getSliderBarWidth() {
		return sliderBarWidth;
	}

	public void setSliderBarWidth(int sliderBarWidth) {
		this.sliderBarWidth = sliderBarWidth;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
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

	public Texture getSliderBarTex() {
		return sliderBarTex;
	}

	public void setSliderBarTex(Texture sliderBarTex) {
		this.sliderBarTex = sliderBarTex;
	}

	public Texture getSliderPointerTex() {
		return sliderPointerTex;
	}

	public void setSliderPointerTex(Texture sliderPointerTex) {
		this.sliderPointerTex = sliderPointerTex;
	}

	public int getSliderBarX() {
		return sliderBarX;
	}

	public void setSliderBarX(int sliderBarX) {
		this.sliderBarX = sliderBarX;
	}

	public int getSliderBarY() {
		return sliderBarY;
	}

	public void setSliderBarY(int sliderBarY) {
		this.sliderBarY = sliderBarY;
	}

	public int getPointerHeight() {
		return pointerHeight;
	}

	public void setPointerHeight(int pointerHeight) {
		this.pointerHeight = pointerHeight;
	}

	public int getPointerWidth() {
		return pointerWidth;
	}

	public void setPointerWidth(int pointerWidth) {
		this.pointerWidth = pointerWidth;
	}

}
