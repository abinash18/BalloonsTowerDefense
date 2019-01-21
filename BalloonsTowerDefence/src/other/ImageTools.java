package other;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * image tools is used to extract smaller sprite from bigger sprite sheets using
 * XML data sheets
 */
public class ImageTools {

	// private static BufferedImage spriteSheet;

	/**
	 * Loads an induvidual texture from a sprite sheet specified post: a texture is
	 * returned
	 */
	public static Texture LoadSpriteTextureFromSpriteSheet(String spriteName, String spriteSheetName) {
		BufferedImage spriteSheet = null, subImage;
		String spriteSheetPath, spriteSheetPathFile;
		Texture tex = null;
		Element temp = null, tempSprite;

		temp = LoadSettings.getSpriteSheet(spriteSheetName);
		spriteSheetPath = temp.getAttribute("XMLPath");
		System.out.println(temp.getAttribute("XMLPath"));
		spriteSheetPathFile = temp.getAttribute("ImagePath");
		System.out.println(temp.getAttribute("ImagePath"));

		try {
			spriteSheet = ImageIO.read(ResourceLoader.getResourceAsStream(spriteSheetPathFile));
			tempSprite = LoadSettings.getSpriteFromExternalXML(spriteSheetPath, spriteName);

			subImage = spriteSheet.getSubimage(Integer.parseInt(tempSprite.getAttribute("x")),
					Integer.parseInt(tempSprite.getAttribute("y")), Integer.parseInt(tempSprite.getAttribute("w")),
					Integer.parseInt(tempSprite.getAttribute("h")));
			// System.out.println(tempSprite.getAttribute("h"));

			tex = BufferedImageUtil.getTexture("", subImage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tex;

	}

	/**
	 * Loads all sprites from a specified sprite sheet using the xml data sheet
	 * post: a texture corresponding to the name specified is returned
	 */
	public static ArrayList<Sprite> LoadAllSpriteTexturesFromSpriteSheet(String spriteSheetName) {
		BufferedImage spriteSheet = null, subImage;
		String spriteSheetPath, spriteSheetPathFile;
		Texture tex = null;
		Element temp = null, tempSprite;
		NodeList tempSprites;
		ArrayList<Sprite> tempList = new ArrayList<Sprite>();
		int counter = 0;

		temp = LoadSettings.getSpriteSheet(spriteSheetName);
		spriteSheetPath = temp.getAttribute("XMLPath");
		System.out.println(temp.getAttribute("XMLPath"));
		spriteSheetPathFile = temp.getAttribute("ImagePath");
		System.out.println(temp.getAttribute("ImagePath"));

		tempSprites = LoadSettings.getSpriteSheetParsed(spriteSheetPath);

		// NodeList nList = tempSprites.getElementsByTagName("Cell");

		try {
			spriteSheet = ImageIO.read(ResourceLoader.getResourceAsStream(spriteSheetPathFile));

			// for (int i = 0; i < nList.getLength(); i++) {
			//
			// }

			// tempSprites =
			// LoadSettings.getSpritesFromExternalXML(spriteSheetPath);

			for (int i = 0; i < tempSprites.getLength(); i++) {
				Node node = tempSprites.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					tempSprite = (Element) node;
					System.out.println(tempSprite.getAttribute("name"));
					subImage = spriteSheet.getSubimage(Integer.parseInt(tempSprite.getAttribute("x")),
							Integer.parseInt(tempSprite.getAttribute("y")),
							Integer.parseInt(tempSprite.getAttribute("w")),
							Integer.parseInt(tempSprite.getAttribute("h")));
					tex = BufferedImageUtil.getTexture("", subImage);
					tempList.add(new Sprite(tempSprite.getAttribute("name"), tex));
					System.out.println(counter);
					counter++;
				}
			}

			// System.out.println(tempSprite.getAttribute("h"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tempList;

	}

	/**
	 * Open gl texture laoding
	 */
	public static int glLoadTextureLinear(String location) {

		int texture = glGenTextures();

		InputStream in = null;
		try {
			in = new FileInputStream(location);

			PNGDecoder decoder = new PNGDecoder(in);
			ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.RGBA);
			buffer.flip();
			in.close();
			glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexImage2D(GL_TEXTURE_RECTANGLE_ARB, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA,
					GL_UNSIGNED_BYTE, buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, texture);
		return texture;

	}

}
