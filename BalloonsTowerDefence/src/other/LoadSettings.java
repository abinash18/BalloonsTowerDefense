/**
 * Abinash Singh
 * Balloons Tower Defense Custom XML API 
 */
package other;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.opengl.Texture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadSettings {

	private static final String SETTINGS_FILE_NAME = "src/xml/settings.xml";
	private static ArrayList<Element> texturePathElements = new ArrayList<Element>();
	private static ArrayList<Element> towerElements = new ArrayList<Element>();
	private static ArrayList<Element> settings = new ArrayList<Element>();
	private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	// private static ArrayList<Texture> spriteTexture = new
	// ArrayList<Texture>();
	private static Document document;
	public static boolean isTexturesDone = false, isTexturePathsDone = false, isTowersDone = false,
			isGameSettingsDone = false, isDone = false, isFileLoaded = false;
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	// public LoadSettings() {
	// texturePathElements = new ArrayList<Element>();
	// towerElements = new ArrayList<Element>();
	// settings = new ArrayList<Element>();
	// }

	public static void LoadFileXML() {

		try {
			// Load File
			File settingsFile = new File(SETTINGS_FILE_NAME);

			// Get Document Builder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Build Document
			document = builder.parse(settingsFile);

			// Normalize the XML Structure
			document.getDocumentElement().normalize();
			isFileLoaded = true;
			// LoadTexturePathElementsIntoArray();
			// LoadSettingsIntoArray();
			// LoadTowerElementsIntoArray();

			// the root node
			// Element root = document.getDocumentElement();
			// System.out.println(root.getNodeName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void LoadTexturePathElementsIntoArray() {
		// Get all nodes for the settings
		// Node main = document.getElementById("main");
		// Node textures = document.getElementById("textures");
		NodeList nList = document.getElementsByTagName("TexturePath");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			System.out.println(""); // Just a separator
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// Print each employee's detail
				Element eElement = (Element) node;
				texturePathElements.add(eElement);
				System.out.println(eElement.getAttribute("name"));
			}
		}
		isTexturePathsDone = true;
	}

	public static void LoadSpritesIntoArray() {
		// Get all nodes for the settings
		// Node main = document.getElementById("main");
		// Node textures = document.getElementById("textures");
		// NodeList nList = document.getElementsByTagName("TexturePath");

		// for (Element texPath : texturePathElements) {
		// System.out.println(texPath.getAttribute("name") + " " +
		// texPath.getAttribute("name"));
		// sprites.add(new Sprite(texPath.getAttribute("name"),
		// texPath.getAttribute("path")));
		// }

		sprites = ImageTools.LoadAllSpriteTexturesFromSpriteSheet("In_Game");

		/*
		 * This method i got from a stack overflow question
		 * https://stackoverflow.com/questions/22610526/how-to-append-elements-
		 * at-the-end-of-arraylist-in-java
		 */
		int endOfList = sprites.size();
		sprites.addAll(endOfList, ImageTools.LoadAllSpriteTexturesFromSpriteSheet("In_Game_HUD"));
		System.out.println(endOfList);
		
		isTexturesDone = true;
	}

	public static void LoadTowerElementsIntoArray() {
		NodeList towers = document.getElementsByTagName("Tower");

		for (int i = 0; i < towers.getLength(); i++) {
			Node node = towers.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				towerElements.add(eElement);
			}
		}
	}

	public static void LoadSettingsIntoArray() {
		// Node gameSettings = document.getElementById("GameSettings");
		NodeList settingsNodeList = document.getElementsByTagName("Setting");

		for (int i = 0; i < settingsNodeList.getLength(); i++) {
			Node node = settingsNodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				settings.add(eElement);
			}
		}
	}

	public static Element getGameSetting(String settingTagName) {
		Element settingElement = null;
		for (Element sett : settings) {
			if (sett.getTagName().equals(settingTagName)) {
				settingElement = sett;
			}
		}
		return settingElement;
	}

	public static Element getTowerInfo(String towerTagName) {
		Element settingElement = null;
		for (Element tower : settings) {
			if (tower.getTagName().equals(towerTagName)) {
				settingElement = tower;
			}
		}
		return settingElement;
	}

	public static Element getTextureElement(String textureName) {
		Element textureElement = null;
		for (Element tex : settings) {
			if (tex.getAttribute("name").equals(textureName)) {
				textureElement = tex;
			}
		}
		return textureElement;
	}

	public static Element getSpriteSheet(String name) {
		Element spritesheet = null;
		NodeList nlist = document.getElementsByTagName("ExternalSpriteSheet");

		for (int i = 0; i < nlist.getLength(); i++) {
			Node node = nlist.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				System.out.println(eElement.getAttribute("name"));
				if (eElement.getAttribute("name").equals(name)) {
					spritesheet = eElement;
				}
			}
		}

		return spritesheet;
	}

	public static NodeList getSpriteSheetParsed(String path) {
		NodeList nlist = null;
		Document doc = null;
		File file = new File(path);

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			doc = builder.parse(file);

			nlist = doc.getElementsByTagName("Cell");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nlist;
	}

	public static Element getSpriteFromExternalXML(String path, String name) {
		Element e = null;
		Document doc = null;
		File file = new File(path);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			doc = builder.parse(file);

			NodeList nList = doc.getElementsByTagName("Cell");

			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					System.out.println(eElement.getAttribute("name"));
					if (eElement.getAttribute("name").equals(name)) {
						e = eElement;
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return e;

	}

	public static NodeList getSpritesFromExternalXML(String path) {
		NodeList nList = null;
		Document doc = null;
		File file = new File(path);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			doc = builder.parse(file);

			nList = doc.getElementsByTagName("Cell");

			// for (int i = 0; i < nList.getLength(); i++) {
			// Node node = nList.item(i);
			// if (node.getNodeType() == Node.ELEMENT_NODE) {
			// Element eElement = (Element) node;
			// System.out.println(eElement.getAttribute("name"));
			// if (eElement.getAttribute("name").equals(name)) {
			// e = eElement;
			// }
			// }
			// }

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return nList;

	}

	public static ArrayList<Element> getTexturePathElements() {
		return texturePathElements;
	}

	public static ArrayList<Element> getTowerElements() {
		return towerElements;
	}

	public static ArrayList<Element> getSettings() {
		return settings;
	}

	public static boolean isTexturesDone() {
		return isTexturesDone;
	}

	public boolean isTowersDone() {
		return isTowersDone;
	}

	public boolean isGameSettingsDone() {
		return isGameSettingsDone;
	}

	public boolean isDone() {
		return isDone;
	}

	public static boolean isFileLoaded() {
		return isFileLoaded;
	}

}
