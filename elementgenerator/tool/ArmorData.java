package elementgenerator.tool;

import json.object.*;

/**
 * An ArmorData class to hold the information about the Armor 
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ArmorData {
	
	//Static Fields
	
	/**
	 * The Default Max Damage of the armor
	 */
	public static final int DEFAULT_MAX_DAMAGE = 64;
	
	/**
	 * The Default Enchantability of the Armor
	 */
	public static final int DEFAULT_ENCHANTABILITY = 10;

	/**
	 * The Default toughness of the Armor
	 */
	public static final double DEFAULT_TOUGHNESS = 0.0;

	/**
	 * The Default Helmet object
	 */
	public static final Armor DEFAULT_HELMET = new Armor(Armor.HELMET);

	/**
	 * The Default Chestplate object
	 */
	public static final Armor DEFAULT_CHESTPLATE = new Armor(Armor.CHESTPLATE);

	/**
	 * The Default Leggings object
	 */
	public static final Armor DEFAULT_LEGGINGS = new Armor(Armor.LEGGINGS);

	/**
	 * The Default Boots object
	 */
	public static final Armor DEFAULT_BOOTS = new Armor(Armor.BOOTS);
	
	//Instance Fields
	private int maxDamage;
	private int enchantability;
	private double toughness;
	private Armor helmet;
	private Armor chestplate;
	private Armor leggings;
	private Armor boots;
	
	/**
	 * Creates a new ArmorData object
	 * @param maxDamage maximum amount of damage the armor can take
	 * @param enchantability how far the armor can be enchanted
	 * @param toughness how tough the armor is {@code (0.0 - leather, 2.0 - diamond)}
	 * @param helmet the helmet object
	 * @param chestplate the chestplate object
	 * @param leggings the leggings object
	 * @param boots the boots object
	 */
	public ArmorData(int maxDamage, int enchantability, double toughness, Armor helmet, Armor chestplate, Armor leggings, Armor boots) {
		this.maxDamage = maxDamage;
		this.enchantability = enchantability;
		this.toughness = toughness;
		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
	}
	
	/**
	 * Creates a new ArmorData object
	 * @param maxDamage maximum amount of damage the armor can take
	 * @param enchantability how far the armor can be enchanted
	 * @param toughness how tough the armor is {@code (0.0 - leather, 2.0 - diamond)}
	 */
	public ArmorData(int maxDamage, int enchantability, double toughness) {
		this(maxDamage, enchantability, toughness, DEFAULT_HELMET, DEFAULT_CHESTPLATE, DEFAULT_LEGGINGS, DEFAULT_BOOTS);
	}
	
	/**
	 * Creates a new ArmorData object
	 * @param helmet the helmet object
	 * @param chestplate the chestplate object
	 * @param leggings the leggings object
	 * @param boots the boots object
	 */
	public ArmorData(Armor helmet, Armor chestplate, Armor leggings, Armor boots) {
		this(DEFAULT_MAX_DAMAGE, DEFAULT_ENCHANTABILITY, DEFAULT_TOUGHNESS, helmet, chestplate, leggings, boots);
	}
	
	/**
	 * Creates a new ArmorData object
	 */
	public ArmorData() {
		this(DEFAULT_MAX_DAMAGE, DEFAULT_ENCHANTABILITY, DEFAULT_TOUGHNESS, DEFAULT_HELMET, DEFAULT_CHESTPLATE, DEFAULT_LEGGINGS, DEFAULT_BOOTS);
	}
	
	/**
	 * Sets how much damage the armor can take before it breaks
	 * @param maxDamage how much damage the armor can take before the armor breaks
	 */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
	
	/**
	 * Sets how far the armor can be enchanted
	 * @param enchantability how far the armor can be enchanted
	 */
	public void setEnchantability(int enchantability) {
		this.enchantability = enchantability;
	}
	
	/**
	 * Sets how tough the armor is
	 * @param toughness the toughness of the armor {@code (0.0 - leather, 2.0 - diamond)}
	 */
	public void setToughness(double toughness) {
		this.toughness = toughness;
	}
	
	/**
	 * Sets the helmet object
	 * @param helmet the helmet object
	 */
	public void setHelmet(Armor helmet) {
		this.helmet = helmet;
	}
	
	/**
	 * Sets the chestplate object
	 * @param chestplate the chestplate object
	 */
	public void setChestplate(Armor chestplate) {
		this.chestplate = chestplate;
	}
	
	/**
	 * Sets the leggings object
	 * @param leggings the leggings object
	 */
	public void setLeggings(Armor leggings) {
		this.leggings = leggings;
	}
	
	/**
	 * Sets the boots object
	 * @param boots the boots object
	 */
	public void setBoots(Armor boots) {
		this.boots = boots;
	}
	
	/**
	 * Returns the maximum amount of damage the armor takes until the armor breaks
	 * @return int - the maximum amount of damage the armor can take
	 */
	public int getMaxDamage() {
		return maxDamage;
	}
	
	/**
	 * Returns how far the armor can be enchanted
	 * @return int - how far the armor can be enchanted
	 */
	public int getEnchantability() {
		return enchantability;
	}
	
	/**
	 * Returns toughness of the armor
	 * @return double - the toughness of the armor {@code (0.0 - leather, 2.0 - diamond)}
	 */
	public double getToughness() {
		return toughness;
	}
	
	/**
	 * Returns the Helmet armor piece object
	 * @return Armor - the Helmet armor piece
	 */
	public Armor getHelmet() {
		return helmet;
	}
	
	/**
	 * Returns the Chestplate armor piece object
	 * @return Armor - the Chestplate armor piece
	 */
	public Armor getChestplate() {
		return chestplate;
	}
	
	/**
	 * Returns the Leggings armor piece object
	 * @return Armor - the Leggings armor piece
	 */
	public Armor getLeggings() {
		return leggings;
	}
	
	/**
	 * Returns the Boots armor piece object
	 * @return Armor - the Boots armor piece
	 */
	public Armor getBoots() {
		return boots;
	}
	
	/**
	 * Returns the Stringified version
	 * @return String
	 */
	public String toString() {
		
		return (
				getIndent(1) + "Max Damage: " + getMaxDamage() + "\n" +
				getIndent(1) + "Enchantability: " + getEnchantability() + "\n" +
				getIndent(1) + "Toughness: " + getToughness() + "\n" +
				getIndent(1) + "Helmet: \n" + getHelmet().toString() + "\n" +
				getIndent(1) + "Chestplate: \n" + getChestplate().toString() + "\n" +
				getIndent(1) + "Leggings: \n" + getLeggings().toString() + "\n" +
				getIndent(1) + "Boots: \n" + getBoots().toString() + "\n"
		);
		
	}
	
	/**
	 * Returns the JSON version of the ArmorData
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("maxDamage", getMaxDamage()),
				new JSONPair("enchantability", getEnchantability()),
				new JSONPair("toughness", getToughness()),
				new JSONPair("helmet", getHelmet().toJSON()),
				new JSONPair("chestplate", getChestplate().toJSON()),
				new JSONPair("leggings", getLeggings().toJSON()),
				new JSONPair("boots", getBoots().toJSON())
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
