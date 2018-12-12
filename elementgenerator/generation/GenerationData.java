package elementgenerator.generation;

import json.object.*;

/**
 * A GenerationData class
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class GenerationData {
	
	//Static Fields
	
	/**
	 * The Default nether generation value
	 */
	public static final Generation DEFAULT_NETHER = new Generation();
	
	/**
	 * The Default surface generation value
	 */
	public static final Generation DEFAULT_SURFACE = new Generation(true);
	
	/**
	 * The Default end generation value
	 */
	public static final Generation DEFAULT_END = new Generation();
	
	//Instance Fields
	private Generation nether;
	private Generation surface;
	private Generation end;
	
	/**
	 * Creates a new GenerationData object
	 * @param nether the nether generation object
	 * @param surface the surface generation object
	 * @param end
	 */
	public GenerationData(Generation nether, Generation surface, Generation end) {
		this.nether = nether;
		this.surface = surface;
		this.end = end;
	}
	
	/**
	 * Creates a new GenerationData object
	 */
	public GenerationData() {
		this(DEFAULT_NETHER, DEFAULT_SURFACE, DEFAULT_END);
	}
	
	/**
	 * Sets the Nether generation object
	 * @param nether the nether generation object
	 */
	public void setNether(Generation nether) {
		this.nether = nether;
	}
	
	/**
	 * Sets the Surface generation object
	 * @param surface the surface generation object
	 */
	public void setSurface(Generation surface) {
		this.surface = surface;
	}
	
	/**
	 * Sets the End generation object
	 * @param end the end generation object
	 */
	public void setEnd(Generation end) {
		this.end = end;
	}
	
	/**
	 * Returns the Nether generation object
	 * @return Generation - the Nether generation object
	 */
	public Generation getNether() {
		return nether;
	}
	
	/**
	 * Returns the Surface generation object
	 * @return Generation - the Surface generation object
	 */
	public Generation getSurface() {
		return surface;
	}
	
	/**
	 * Returns the End generation object
	 * @return Generation - the End generation object
	 */
	public Generation getEnd() {
		return end;
	}
	
	/**
	 * Returns the Stringified version of the GenerationData
	 * @return String
	 */
	public String toString() {
		
		return (
				"--Nether--\n" + nether.toString() + "\n" +
				"--Surface--\n" + surface.toString() + "\n" +
				"--End--\n" + end.toString() + "\n"
		);
		
	}
	
	/**
	 * Returns the JSON version of the GenerationData
	 * @return JSONObject
	 */
	public JSONObject toJSON() {
		
		return new JSONObject(
				new JSONPair("nether", nether.toJSON()),
				new JSONPair("surface", surface.toJSON()),
				new JSONPair("end", end.toJSON())
		);
		
	}

}
