package elementgenerator.tool;

import json.object.*;

/**
 * A Tool class to hold information about the tools of an element
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Tool {

	//Static Fields
	
	/**
	 * Used to identify a tool as a pickaxe
	 */
	public static final int PICKAXE = 0;
	
	/**
	 * Used to identify a tool as an axe
	 */
	public static final int AXE = 1;
	
	/**
	 * Used to identify a tool as a hoe
	 */
	public static final int HOE = 2;
	
	/**
	 * Used to identify a tool as a shovel
	 */
	public static final int SHOVEL = 3;
	
	/**
	 * Used to identify a tool as a sword
	 */
	public static final int SWORD = 4;
	
	/**
	 * Default tool type for a tool
	 */
	public static final int DEFAULT_TOOL_TYPE = PICKAXE;
	
	/**
	 * Default harvest level for a tool
	 */
	public static final int DEFAULT_HARVEST_LEVEL = 0;
	
	/**
	 * Default efficiency for a tool
	 */
	public static final int DEFAULT_EFFICIENCY = 1;
	
	/**
	 * Default enchantability for a tool
	 */
	public static final int DEFAULT_ENCHANTABILITY = 10;
	
	/**
	 * Default attack damage for a tool
	 */
	public static final int DEFAULT_ATTACK_DAMAGE = 1;
	
	/**
	 * Default uses for a tool
	 */
	public static final int DEFAULT_USES = 64;
	
	//Instance Fields
	private int toolType;
	private int harvestLevel;
	private int efficiency;
	private int enchantability;
	private int attackDamage;
	private int uses;
	
	/**
	 * Creates a new Tool object
	 * @param toolType type of tool {@code (PICKAXE, AXE, HOE, SHOVEL, SWORD)}
	 * @param harvestLevel harvest level of the tool {@code (0 - wood, 1 - stone, 2 - iron/gold, 3 - diamond)}
	 * @param efficiency how efficient the tool is when breaking a block
	 * @param enchantability how far the tool can be enchanted
	 * @param attackDamage how much damage is dealt to an entity when attacking
	 * @param uses how many uses the tool has until it breaks
	 */
	public Tool(int toolType, int harvestLevel, int efficiency, int enchantability, int attackDamage, int uses) {
		this.toolType = toolType;
		this.harvestLevel = harvestLevel;
		this.efficiency = efficiency;
		this.enchantability = enchantability;
		this.attackDamage = attackDamage;
		this.uses = uses;
	}
	
	/**
	 * Creates a new Tool object
	 * @param toolType type of tool {@code (PICKAXE, AXE, HOE, SHOVEL, SWORD)}
	 */
	public Tool(int toolType) {
		this(toolType, DEFAULT_HARVEST_LEVEL, DEFAULT_EFFICIENCY, DEFAULT_ENCHANTABILITY, DEFAULT_ATTACK_DAMAGE, DEFAULT_USES);
	}
	
	/**
	 * Creates a new Tool object
	 */
	public Tool() {
		this(DEFAULT_TOOL_TYPE);
	}
	
	/**
	 * Sets the type of tool
	 * @param toolType type of tool {@code (PICKAXE, AXE, HOE, SHOVEL, SWORD)}
	 */
	public void setToolType(int toolType) {
		this.toolType = toolType;
	}
	
	/**
	 * Sets the harvest level of the tool
	 * @param harvestLevel harvest level of the tool {@code (0 - wood, 1 - stone, 2 - iron/gold, 3 - diamond)}
	 */
	public void setHarvestLevel(int harvestLevel) {
		this.harvestLevel = harvestLevel;
	}
	
	/**
	 * Sets the efficiency of the tool
	 * @param efficiency how efficient the tool is at breaking a block
	 */
	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}
	
	/**
	 * Sets the enchantability of the tool
	 * @param enchantability how far the tool can be enchanted
	 */
	public void setEnchantability(int enchantability) {
		this.enchantability = enchantability;
	}
	
	/**
	 * Sets the attack damage of the tool
	 * @param attackDamage how much damage is dealt to an entity when attacking
	 */
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	
	/**
	 * Sets how many uses the tool has
	 * @param uses the amount of times the tool can be used until it breaks
	 */
	public void setUses(int uses) {
		this.uses = uses;
	}
	
	/**
	 * Returns the type of tool in int form
	 * @return int - type of tool
	 */
	public int getToolId() {
		return toolType;
	}
	
	/**
	 * Returns the type of tool in String form
	 * @return String - type of tool
	 */
	public String getTool() {
		switch(toolType) {
			case PICKAXE: 	return "Pickaxe";
			case AXE: 		return "Axe";
			case HOE: 		return "Hoe";
			case SHOVEL:	return "Shovel";
			case SWORD: 	return "Sword";
			default: 		return "Pickaxe";
		}
	}
	
	/**
	 * Returns the harvest level of the tool
	 * @return int - harvest level
	 */
	public int getHarvestLevel() {
		return harvestLevel;
	}
	
	/**
	 * Returns the efficiency of the tool
	 * @return int - efficiency
	 */
	public int getEfficiency() {
		return efficiency;
	}
	
	/**
	 * Returns the enchantability of the tool
	 * @return int - enchantability
	 */
	public int getEnchantability() {
		return enchantability;
	}
	
	/**
	 * Returns the attack damage of the tool
	 * @return int - attack damage
	 */
	public int getAttackDamage() {
		return attackDamage;
	}
	
	/**
	 * Returns the uses of the tool
	 * @return int - uses
	 */
	public int getUses() {
		return uses;
	}
	
	/**
	 * Returns the Stringified version of the tool
	 * @return String
	 */
	public String toString() {
		
		return (
				getIndent(5) + "Type: " + getTool() + "\n" +
				getIndent(5) + "Harvest Level: " + getHarvestLevel() + "\n" +
				getIndent(5) + "Efficiency: " + getEfficiency() + "\n" +
				getIndent(5) + "Enchantability: " + getEnchantability() + "\n" +
				getIndent(5) + "Attack Damage: " + getAttackDamage() + "\n" +
				getIndent(5) + "Uses: " + getUses() + "\n"
		);
		
	}
	
	/**
	 * Returns the JSON version of the tool
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("harvestLevel", getHarvestLevel()),
				new JSONPair("efficiency", getEfficiency()),
				new JSONPair("enchantability", getEnchantability()),
				new JSONPair("attackDamage", getAttackDamage()),
				new JSONPair("uses", getUses())
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
