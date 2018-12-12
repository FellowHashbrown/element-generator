package elementgenerator.block;

import json.object.*;

/**
 * A BlockData class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class BlockData {
	
	//Static Fields
	
	/**
	 * The Default hardness value
	 */
	public static final double DEFAULT_HARDNESS = 1.5;
	
	/**
	 * The Default resistance value
	 */
	public static final double DEFAULT_RESISTANCE = 10.0;
	
	/**
	 * The Default light level value
	 */
	public static final double DEFAULT_LIGHT_LEVEL = 0.0;
	
	/**
	 * The Default light opacity value
	 */
	public static final int DEFAULT_LIGHT_OPACITY = 16;
	
	/**
	 * The Default harvest level value
	 */
	public static final int DEFAULT_HARVEST_LEVEL = 0;
	
	//Instance Fields
	private double hardness;
	private double resistance;
	private double lightLevel;
	private int lightOpacity;
	private int harvestLevel;
	
	/**
	 * Creates a new BlockData object
	 * @param hardness how long it takes to break the block
	 * @param resistance how resistant the block is to explosions
	 * @param lightLevel how much light is emitted from the block
	 * @param lightOpacity how much light can travel through the block
	 * @param harvestLevel the type of tool it takes to harvest the block
	 */
	public BlockData(double hardness, double resistance, double lightLevel, int lightOpacity, int harvestLevel) {
		this.hardness = hardness;
		this.resistance = resistance;
		this.lightLevel = lightLevel;
		this.lightOpacity = lightOpacity;
		this.harvestLevel = harvestLevel;
	}
	
	/**
	 * Creates a new BlockData object
	 * @param hardness how long it takes to break the block
	 * @param resistance how resistant the block is to explosions
	 * @param lightLevel how much light is emitted from the block
	 * @param lightOpacity how much light can travel through the block
	 */
	public BlockData(double hardness, double resistance, double lightLevel, int lightOpacity) {
		this(hardness, resistance, lightLevel, lightOpacity, DEFAULT_HARVEST_LEVEL);
	}
	
	/**
	 * Creates a new BlockData object
	 * @param hardness how long it takes to break the block
	 * @param resistance how resistant the block is to explosions
	 * @param lightLevel how much light is emitted from the block
	 */
	public BlockData(double hardness, double resistance, double lightLevel) {
		this(hardness, resistance, lightLevel, DEFAULT_LIGHT_OPACITY);
	}
	
	/**
	 * Creates a new BlockData object
	 * @param hardness how long it takes to break the block
	 * @param resistance how resistant the block is to explosions
	 */
	public BlockData(double hardness, double resistance) {
		this(hardness, resistance, DEFAULT_LIGHT_LEVEL);
	}
	
	/**
	 * Creates a new BlockData object
	 * @param hardness how long it takes to break the block
	 */
	public BlockData(double hardness) {
		this(hardness, DEFAULT_RESISTANCE);
	}
	
	/**
	 * Creates a new BlockData object
	 */
	public BlockData() {
		this(DEFAULT_HARDNESS);
	}
	
	/**
	 * Sets the hardness of the block
	 * @param hardness how long it takes to break the block
	 */
	public void setHardness(double hardness) {
		this.hardness = hardness;
	}
	
	/**
	 * Sets the resistance of the block
	 * @param resistance how resistant the block is to explosions
	 */
	public void setResistance(double resistance) {
		this.resistance = resistance;
	}
	
	/**
	 * Sets the light level of the block
	 * @param lightLevel how much light is emitted from the block
	 */
	public void setLightLevel(double lightLevel) {
		this.lightLevel = lightLevel;
	}
	
	/**
	 * Sets the light opacity of the block
	 * @param lightOpacity how much light can travel through the block
	 */
	public void setLightOpacity(int lightOpacity) {
		this.lightOpacity = lightOpacity;
	}
	
	/**
	 * Sets the harvest level of the block
	 * @param harvestLevel the type of tool it takes to harvest the block
	 */
	public void setHarvestLevel(int harvestLevel) {
		this.harvestLevel = harvestLevel;
	}
	
	/**
	 * Returns the hardness of the block
	 * @return double - the hardness of the block
	 */
	public double getHardness() {
		return hardness;
	}
	
	/**
	 * Returns the resistance of the block
	 * @return double - the resistance of the block
	 */
	public double getResistance() {
		return resistance;
	}
	
	/**
	 * Returns the light level of the block
	 * @return double - the light level of the block
	 */
	public double getLightLevel() {
		return lightLevel;
	}
	
	/**
	 * Returns the light opacity of the block
	 * @return int - the light opacity of the block
	 */
	public int getLightOpacity() {
		return lightOpacity;
	}
	
	/**
	 * Returns the harvest level of the block
	 * @return int - the harvest level of the block
	 */
	public int getHarvestLevel() {
		return harvestLevel;
	}
	
	/**
	 * Returns the Stringified version of the BlockData
	 * @return String
	 */
	public String toString() {
		
		return (
				getIndent(4) + "Hardness: " + getHardness() + "\n" +
				getIndent(4) + "Resistance: " + getResistance() + "\n" +
				getIndent(4) + "Light Level: " + getLightLevel() + "\n" +
				getIndent(4) + "Light Opacity: " + getLightOpacity() + "\n" +
				getIndent(4) + "Harvest Level: " + getHarvestLevel() + "\n"
		);
		
	}
	
	/**
	 * Returns the JSON version of the BlockData
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("hardness", getHardness()),
				new JSONPair("resistance", getResistance()),
				new JSONPair("lightLevel", getLightLevel()),
				new JSONPair("lightOpacity", getLightOpacity()),
				new JSONPair("harvestLevel", getHarvestLevel())
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
