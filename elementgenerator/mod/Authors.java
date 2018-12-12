package elementgenerator.mod;

import json.object.JSONArray;
import json.object.JSONElement;
import json.object.JSONPrimitive;

/**
 * A class to hold author names
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Authors {
	
	// Instance Fields
	private String[] authors;
	
	/**
	 * Creates a new Authors object
	 * @param authors an array of authors
	 */
	public Authors(String... authors) {
		this.authors = authors;
	}
	
	/**
	 * Creates a new Authors object
	 * @param authors a JSONArray to get authors from
	 */
	public Authors(JSONArray authors) {
		this();
		for(JSONElement author: authors.getArray())
			addAuthor(((JSONPrimitive)author).getAsString(false));
	}
	
	/**
	 * Creates a new Authors object
	 */
	public Authors() {
		this(new String[0]);
	}
	
	/**
	 * Sets an array of author names
	 * @param authors the array of authors
	 */
	public void setAuthors(String... authors) {
		this.authors = authors;
	}
	
	/**
	 * Adds an author to the Authors object
	 * @param author
	 */
	public void addAuthor(String author) {
		String[] newArray = new String[authors.length + 1];
		for(int i = 0; i < authors.length; i++)
			newArray[i] = authors[i];
		newArray[newArray.length - 1] = author;
		authors = newArray.clone();
	}
	
	/**
	 * Adds an array of authors to the Authors object
	 * @param authors the array of authors to add
	 */
	public void addAuthors(String... authors) {
		for(String author: authors)
			addAuthor(author);
	}
	
	/**
	 * Returns the array of authors
	 * @return String[] 
	 */
	public String[] getAuthors() {
		return authors;
	}
	
	/**
	 * Returns the array of authors in one String
	 * @return String
	 */
	public String getAuthorAsString() {
		String authors = "[ ";
		for(int i = 0; i < this.authors.length; i++) {
			authors += "\"" + this.authors[i] + "\"";
			if(i < this.authors.length - 1)
				authors += ", ";
		}
		authors += " ]";
		return authors;
	}
	
	/**
	 * Returns the array of authors in one String
	 * @param newLines whether or not to have each author on a new line
	 * @return String
	 */
	public String getAuthorAsString(boolean newLines) {
		if(newLines) {
			String authors = "";
			for(int i = 0; i < this.authors.length; i++) {
				authors += this.authors[i];
				if(i < this.authors.length - 1)
					authors += "\n";
			}
			return authors;
		}
		return getAuthorAsString();
	}
	
	/**
	 * Returns a JSONArray of the authors
	 * @return JSONArray
	 */
	public JSONArray toJSON() {
		JSONArray array = new JSONArray();
		for(String author: authors)
			array.addItem(new JSONPrimitive(author));
		return array;
	}

}
