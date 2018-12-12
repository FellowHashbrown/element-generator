package elementgenerator.mod;

import json.object.JSONObject;
import json.object.JSONPair;

/**
 * A Class to hold the information for the different versions of Minecraft Forge
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class MinecraftVersion {
	
	// Static Fields
	public static final String DEFAULT_VERSION = "1.11.2";
	public static final String DEFAULT_BUILD = "13.20.1.2386";
	public static final String DEFAULT_JAVA = "1.6";
	public static final String DEFAULT_SNAPSHOT = "snapshot_20161220";
	public static final String DEFAULT_FORGE_SNAPSHOT = "2.2";
	
	// Instance Fields
	private String version;
	private String build;
	private String java;
	private String snapshot;
	private String forgeSnapshot;
	
	/**
	 * Creates a new MinecraftVersion object
	 * @param version the version of Minecraft Forge
	 */
	public MinecraftVersion(String version, String build, String java, String snapshot, String forgeSnapshot) {
		this.version = version;
		this.build = build;
		this.java = java;
		this.snapshot = snapshot;
		this.forgeSnapshot = forgeSnapshot;
	}
	
	/**
	 * Creates a new MinecraftVersion object
	 */
	public MinecraftVersion() {
		this(DEFAULT_VERSION, DEFAULT_BUILD, DEFAULT_JAVA, DEFAULT_SNAPSHOT, DEFAULT_FORGE_SNAPSHOT);
	}
	
	/**
	 * Returns the version of minecraft forge with the build number
	 * @param withBuild whether or not to include the build number
	 * @return String
	 */
	public String getVersion(boolean withBuild) {
		if(withBuild)
			return version + "-" + getBuild();
		return version;
	}
	
	/**
	 * Returns the version of minecraft forge
	 * @return String
	 */
	public String getVersion() {
		return getVersion(false);
	}
	
	/**
	 * Returns the build of minecraft forge
	 * @return String
	 */
	public String getBuild() {
		return build;
	}
	
	/**
	 * Returns the java version for minecraft forge
	 * @return String
	 */
	public String getJava() {
		return java;
	}
	
	/**
	 * Returns the snapshot of minecraft forge
	 * @return String
	 */
	public String getSnapshot() {
		return snapshot;
	}
	
	/**
	 * Returns the forge snapshot of minecraft forge
	 * @return String
	 */
	public String getForgeSnapshot() {
		return forgeSnapshot;
	}
	
	/**
	 * Returns the JSON version of the MinecraftVersion object
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("version", getVersion()),
				new JSONPair("build", getBuild()),
				new JSONPair("java", getJava()),
				new JSONPair("snapshot", getSnapshot()),
				new JSONPair("forgeSnapshot", getForgeSnapshot())
		);
		
	}

}
