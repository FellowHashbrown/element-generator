package elementgenerator.element;

import java.awt.Color;

import json.object.*;

/**
 * A ColorData class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ColorData {

	//Static Fields
	
	/**
	 * The Default value for the darkest color
	 */
	public static final Color DEFAULT_DARKEST_COLOR = new Color(255, 0, 0);
	
	/**
	 * The Default value for the lightest color
	 */
	public static final Color DEFAULT_LIGHTEST_COLOR = new Color(255, 0, 255);
	
	//Instance Fields
	private Color darkestColor;
	private Color lightestColor;
	
	/**
	 * Creates a new ColorData object
	 * @param darkestColor the darkest color of the element
	 * @param lightestColor the lightest color of the element
	 */
	public ColorData(Color darkestColor, Color lightestColor) {
		this.darkestColor = darkestColor;
		this.lightestColor = lightestColor;
	}
	
	/**
	 * Creates a new ColorData object
	 */
	public ColorData() {
		this(DEFAULT_DARKEST_COLOR, DEFAULT_LIGHTEST_COLOR);
	}
	
	/**
	 * Sets the darkest color of the element
	 * @param darkestColor the darkest color of the element
	 */
	public void setDarkestColor(Color darkestColor) {
		this.darkestColor = darkestColor;
	}
	
	/**
	 * Sets the lightest color of the element
	 * @param lightestColor the lightest color of the element
	 */
	public void setLightestColor(Color lightestColor) {
		this.lightestColor = lightestColor;
	}
	
	/**
	 * Returns the darkest color of the element
	 * @return Color - the darkest color of the element
	 */
	public Color getDarkestColor() {
		return darkestColor;
	}
	
	/**
	 * Returns the lightest color of the element
	 * @return Color - the lightest color of the element
	 */
	public Color getLightestColor() {
		return lightestColor;
	}
	
	/**
	 * Returns the Stringified version of the Color data
	 */
	public String toString() {
		
		return (
				getIndent(4) + "Darkest Color: " + darkestColor.toString() + "\n" +
				getIndent(4) + "Lightest Color: " + lightestColor.toString() + "\n"
		);
		
	}
	
	/**
	 * Returns the JSON version of the Color data
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("darkestColor", new JSONObject(
						new JSONPair("red", darkestColor.getRed()),
						new JSONPair("green", darkestColor.getGreen()),
						new JSONPair("blue", darkestColor.getBlue())
				)),
				new JSONPair("lightestColor", new JSONObject(
						new JSONPair("red", lightestColor.getRed()),
						new JSONPair("green", lightestColor.getGreen()),
						new JSONPair("blue", lightestColor.getBlue())
				))
		);
		
	}
	
	/**
	 * Returns a String used for indenting; Standard Indent size is 4 spaces (' ')
	 * @param indentLevel the level of indent to add
	 * @return String - the indented string
	 */
	private String getIndent(int indentLevel) {
		
		String indentString = "";
		for(int i = 0; i < indentLevel; i++)
			indentString += "    ";
		return indentString;
		
	}

}
