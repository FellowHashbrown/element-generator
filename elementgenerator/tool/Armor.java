package elementgenerator.tool;

import json.object.*;

/**
 * An Armor class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Armor {
	
	//Static Fields
	
	/**
	 * The Helmet int identifier
	 */
	public static final int HELMET = 0;
	
	/**
	 * The Chestplate int identifier
	 */
	public static final int CHESTPLATE = 1;
	
	/**
	 * The Leggings int identifier
	 */
	public static final int LEGGINGS = 2;
	
	/**
	 * The Boots int identifier
	 */
	public static final int BOOTS = 3;
	
	/**
	 * The Default armor piece
	 */
	public static final int DEFAULT_ARMOR_PIECE = -1;
	
	/**
	 * The Default armor health for armor piece
	 */
	public static final int DEFAULT_HEALTH = 5;
	
	/**
	 * The Default effect for the armor piece
	 */
	public static final String DEFAULT_EFFECT = "NONE";
	
	/**
	 * The Default level for the armor piece
	 */
	public static final int DEFAULT_LEVEL = 0;
	
	/**
	 * The Default duration for the armor piece
	 */
	public static final int DEFAULT_DURATION = 0;
	
	//Instance Fields
	private int armorPiece;
	private int health;
	private String effect;
	private int level;
	private int duration;
	
	/**
	 * Creates a new Armor piece object
	 * @param armorPiece the piece of armor the Armor object is {@code (HELMET, CHESTPLATE, LEGGINGS, BOOTS)}
	 * @param health the amount of health the armor piece adds
	 * @param effect the effect the armor piece has
	 * @param level the level of the effect
	 * @param duration the duration of the effect
	 */
	public Armor(int armorPiece, int health, String effect, int level, int duration) {
		this.armorPiece = armorPiece;
		this.health = health;
		this.effect = effect;
		this.level = level;
		this.duration = duration;
	}
	
	/**
	 * Creates a new Armor piece object
	 * @param armorPiece the piece of armor the Armor object is {@code (HELMET, CHESTPLATE, LEGGINGS, BOOTS)}
	 */
	public Armor(int armorPiece) {
		this(armorPiece, DEFAULT_HEALTH, DEFAULT_EFFECT, DEFAULT_LEVEL, DEFAULT_DURATION);
	}
	
	/**
	 * Creates a new Armor piece object
	 */
	public Armor() {
		this(DEFAULT_ARMOR_PIECE);
	}
	
	/**
	 * Sets the type of Armor
	 * @param armorPiece type of armor the Armor object is {@code (HELMET, CHESTPLATE, LEGGINGS, BOOTS)}
	 */
	public void setArmorPiece(int armorPiece) {
		this.armorPiece = armorPiece;
	}
	
	/**
	 * Sets the amount of health the armor adds
	 * @param health amount of health the armor adds
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Sets the effect the armor piece adds
	 * @param effect the effect the armor piece adds
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	/**
	 * Sets the level of the effect
	 * @param level level of the effect
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Sets the duration of the effect
	 * @param duration duration of the effect
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Returns the int ID of the armor piece
	 * @return int - the armor piece in int form
	 */
	public int getArmorPieceId() {
		return armorPiece;
	}
	
	/**
	 * Returns the name of the armor piece
	 * @return String - the armor piece
	 */
	public String getArmorPiece() {
		switch(armorPiece) {
			case HELMET: return "Helmet";
			case CHESTPLATE: return "Chestplate";
			case LEGGINGS: return "Leggings";
			case BOOTS: return "Boots";
			default: return "Helmet";
		}
	}
	
	/**
	 * Returns the amount of health the armor adds
	 * @return int - the amount of health the armor adds
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns the name of the effect the armor adds
	 * @return String - the effect the armor adds
	 */
	public String getEffect() {
		return effect;
	}
	
	/**
	 * Returns the level of the effect
	 * @return int - level of the effect
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Returns the duration of the effect
	 * @return int - duration of the effect
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Returns the Stringified version of the Armor
	 * @return String
	 */
	public String toString() {
		
		return (
				"Armor Piece: " + getArmorPieceId() + "\n" +
				"Health: " + getHealth() + "\n" +
				"Effect: " + getEffect() + "\n" +
				"Level: " + getLevel() + "\n" +
				"Duration: " + getDuration() + "\n"
		);
		
	}
	
	/**
	 * Returns the JSON version of the Armor
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("health", getHealth()),
				new JSONPair("effect", getEffect()),
				new JSONPair("level", getLevel()),
				new JSONPair("duration", getDuration())
		);
		
	}
	
}
