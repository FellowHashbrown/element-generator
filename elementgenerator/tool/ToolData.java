package elementgenerator.tool;

import json.object.*;

/**
 * A ToolData class to hold information about each tool of an element
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ToolData {
	
	//Static Fields
	
	/**
	 * The Default Tool object for the Pickaxe
	 */
	public static final Tool DEFAULT_PICKAXE = new Tool(Tool.PICKAXE);
	
	/**
	 * The Default Tool object for the Axe
	 */
	public static final Tool DEFAULT_AXE = new Tool(Tool.AXE);
	
	/**
	 * The Default Tool object for the Hoe
	 */
	public static final Tool DEFAULT_HOE = new Tool(Tool.HOE);
	
	/**
	 * The Default Tool object for the Shovel
	 */
	public static final Tool DEFAULT_SHOVEL = new Tool(Tool.SHOVEL);
	
	/**
	 * The Default Tool object for the Sword
	 */
	public static final Tool DEFAULT_SWORD = new Tool(Tool.SWORD);
	
	//Instance Fields
	private Tool pickaxe;
	private Tool axe;
	private Tool hoe;
	private Tool shovel;
	private Tool sword;
	
	/**
	 * Creates a new ToolData object
	 * @param pickaxe the pickaxe tool
	 * @param axe the axe tool
	 * @param hoe the hoe tool
	 * @param shovel the shovel tool
	 * @param sword the sword tool
	 */
	public ToolData(Tool pickaxe, Tool axe, Tool hoe, Tool shovel, Tool sword) {
		this.pickaxe = pickaxe;
		this.axe = axe;
		this.hoe = hoe;
		this.shovel = shovel;
		this.sword = sword;
	}
	
	/**
	 * Creates a new ToolData object
	 */
	public ToolData() {
		this(DEFAULT_PICKAXE, DEFAULT_AXE, DEFAULT_HOE, DEFAULT_SHOVEL, DEFAULT_SWORD);
	}
	
	/**
	 * Sets the Pickaxe Tool object
	 * @param pickaxe Tool object for the pickaxe
	 */
	public void setPickaxe(Tool pickaxe) {
		this.pickaxe = pickaxe;
	}
	
	/**
	 * Sets the axe tool object
	 * @param axe Tool object for the axe
	 */
	public void setAxe(Tool axe) {
		this.axe = axe;
	}
	
	/**
	 * Sets the hoe tool object
	 * @param hoe Tool object for the hoe
	 */
	public void setHoe(Tool hoe) {
		this.hoe = hoe;
	}
	
	/**
	 * Sets the shovel tool object
	 * @param shovel Tool object for the shovel
	 */
	public void setShovel(Tool shovel) {
		this.shovel = shovel;
	}
	
	/**
	 * Sets the sword tool object
	 * @param sword Tool object for the sword
	 */
	public void setSword(Tool sword) {
		this.sword = sword;
	}
	
	/**
	 * Returns the Pickaxe Tool object
	 * @return Tool - the Pickaxe object
	 */
	public Tool getPickaxe() {
		return pickaxe;
	}
	
	/**
	 * Returns the Axe Tool object
	 * @return Tool - the Axe object
	 */
	public Tool getAxe() {
		return axe;
	}
	
	/**
	 * Returns the Hoe tool object
	 * @return Tool - the Hoe object
	 */
	public Tool getHoe() {
		return hoe;
	}
	
	/**
	 * Returns the Shovel tool object
	 * @return Tool - the Shovel object
	 */
	public Tool getShovel() {
		return shovel;
	}
	
	/**
	 * Returns the Sword tool object
	 * @return Tool - the Sword object
	 */
	public Tool getSword() {
		return sword;
	}
	
	/**
	 * Returns the Stringified version of the ToolData
	 * @return String
	 */
	public String toString() {
		
		return (
				getIndent(4) + "Pickaxe\n" + pickaxe.toString() + "\n" +
				getIndent(4) + "Axe\n" + axe.toString() + "\n" +
				getIndent(4) + "Hoe\n" + hoe.toString() + "\n" +
				getIndent(4) + "Shovel\n" + shovel.toString() + "\n" +
				getIndent(4) + "Sword\n" + sword.toString()
		);
		
	}
	
	/**
	 * Returns the JSON version of the ToolData
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("pickaxe", getPickaxe().toJSON()),
				new JSONPair("axe", getAxe().toJSON()),
				new JSONPair("hoe", getHoe().toJSON()),
				new JSONPair("shovel", getShovel().toJSON()),
				new JSONPair("sword", getSword().toJSON())
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
