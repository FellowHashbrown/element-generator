package elementgenerator.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import elementgenerator.gui.ElementGenerator;

/**
 * A class that holds useful static methods for the Element Generator 
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ElementUtils {
	
	/**
	 * An enclosed Colour class needed because of the alpha transparency not loading correctly
	 * @author Jonah Pierce (Fellow Hashbrown)
	 *
	 */
	private static class Colour {
		
		// Instance Fields
		int r;
		int g;
		int b;
		int a;
		
		/**
		 * Creates a new Colour object
		 * @param r the red value of the color (0-255)
		 * @param g the green value of the color (0-255)
		 * @param b the blue value of the color (0-255)
		 * @param a the alpha value of the color (0-255)
		 */
		public Colour(int r, int g, int b, int a) {
			this.r = r;
			this.g = g;
			this.b = b;
			this.a = a; 
		}
		
		/**
		 * Creates a new Colour object
		 * @param argb an int version of the color
		 */
		public Colour(int argb) {
			this(
					(argb >> 16) & 0xFF,
					(argb >> 8)  & 0xFF,
					(argb >> 0)  & 0xFF,
					(argb >> 24) & 0xFF
			);
		}
		
		/**
		 * Returns the int version of the color
		 * <p>
		 * Basically the same thing as Color.getRGB() but with alpha included properly
		 * @return int
		 */
		public int toInt() {
			return (
					(a << 24) & 0xFF000000 |
					(r << 16) & 0x00FF0000 |
					(g << 8)  & 0x0000FF00 |
					(b << 0)  & 0x000000FF
			);
		}
	}
	
	// Static Fields
	
	/**
	 * The default amount of colors to create a gradient into
	 */
	public static final int DEFAULT_COLOR_AMOUNT = 6;
	
	/**
	 * Copies a file from one directory to another
	 * @param oldFileName the original file path
	 * @param newFilePath the new file path
	 */
	public static void copyFile(String oldFileName, String newFilePath) {
		
		// Get file name
		File oldFile = new File(oldFileName);
		String fileName = oldFile.getName();
		replaceLines(oldFileName, newFilePath + "/" + fileName, new String[] {});
	}
	
	/**
	 * Loads a template file and replaces Strings based on the template
	 * @param oldFileName the file to load the template from
	 * @param newFileName the file to save to
	 * @param replacementLines the array of Strings to replace (order is [old, new, old, new, ... ])
	 */
	public static void replaceLines(String oldFileName, String newFileName, String[] replacementLines) {
		
		// Try to open old file
		BufferedReader reader = null;
		try {
			// Load file
			reader = new BufferedReader(
					new InputStreamReader(
							ElementGenerator.class.getResourceAsStream(oldFileName)
					)
			);
			// Get file lines
			Object[] oldLines = reader.lines().toArray();
			String[] newLines = new String[oldLines.length];
			
			// Replace lines
			for(int i = 0; i < oldLines.length; i++) {
				
				// Old Line
				String line = String.valueOf(oldLines[i]);
				
				// Iterate through replacementLines
				for(int j = 0; j < replacementLines.length; j += 2) // Increase by 2 because of format
					line = line.replace(replacementLines[j], replacementLines[j + 1]);
				
				// Set in lines
				newLines[i] = line;
				
			}
			
			// Save to new file
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName));
			for(String line: newLines)
				writer.write(line + "\n");
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads a template image and replaces the colors based on the template
	 * @param oldFileName the file to load the template from
	 * @param colorReplacements the array of colors to replace (order is [darkestColor, ... , lightestColor])
	 * @return BufferedImage
	 */
	public static BufferedImage replaceColors(String oldFileName, Color[] replacementColors) {
		
		// Attempt to load original file
		BufferedImage image = null;
		try {
			image = ImageIO.read(
						ElementGenerator.class.getResourceAsStream(oldFileName)
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Only replace colors if image exists
		if(image != null) {
			
			// Go through the width
			for(int x = 0; x < image.getWidth(); x++) {
				
				// Go through the height
				for(int y = 0; y < image.getHeight(); y++) {
					
					// Get current pixel at x, y
					Colour current = new Colour(image.getRGB(x, y));
					Color pixel = new Color(image.getRGB(x, y));
					
					// Look through replacementColors to find match
					for(int color = 0; color < replacementColors.length; color += 2 ) { // Increase by two because of format [old, new, old, new, ... ]
						if(pixel.equals(replacementColors[color]))
							current = new Colour(replacementColors[color + 1].getRGB());
					}
					
					// Set to current
					// - NOTE: If a replacement color was found, current will equal that color
					// -       If not, current will be the previously specified color
					image.setRGB(x, y, current.toInt());
				}
			}
		}
		
		return image;
		
	}
	
	/**
	 * Loads a template image and replaces the colors based on the template and then saves it to a new file
	 * @param oldFileName the file to load the template from
	 * @param newFileName the path to save the generated image to
	 * @param colorReplacements the array of colors to replace (order is [darkestColor, ... , lightestColor])
	 */
	public static void replaceColors(String oldFileName, String newFileName, Color[] colorReplacements) {
		
		// Load template from overloaded method
		BufferedImage image = replaceColors(oldFileName, colorReplacements);
		
		// Only save image if image is not null
		if(image != null) {
			
			// Try to write to file
			try {
				ImageIO.write(image, "png", new File(newFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Generates an even gradient of colors and returns an array of them in order of darkest to lightest
	 * @param darkestColor the color to start the gradient at
	 * @param lightestColor the color to end the gradient at
	 * @param amountColors the amount of colors to generate (Default is 6)
	 * @return Color[]
	 */
	public static Color[] getGradient(Color darkestColor, Color lightestColor, int amountColors) {
		// Get the change in rgba values
		int initialRed = darkestColor.getRed();
		int initialGreen = darkestColor.getGreen();
		int initialBlue = darkestColor.getBlue();
		int initialAlpha = darkestColor.getAlpha();
		
		int red = (lightestColor.getRed() - darkestColor.getRed()) / (amountColors - 1);
		int green = (lightestColor.getGreen() - darkestColor.getGreen()) / (amountColors - 1);
		int blue = (lightestColor.getBlue() - darkestColor.getBlue()) / (amountColors - 1);
		int alpha = (lightestColor.getAlpha() - lightestColor.getAlpha()) / (amountColors - 1);
		
		// Generate the array of the gradients
		Color[] gradient = new Color[amountColors];
		for(int color = 0; color < amountColors; color++) {
			gradient[color] = new Color(
					initialRed + (color * red),
					initialGreen + (color * green),
					initialBlue + (color * blue),
					initialAlpha + (color * alpha)
			);
		}
		
		return gradient;
		
	}
	
	/**
	 * Generates an even gradient of colors and returns an array of them in order of darkest to lightest
	 * @param darkestColor the color to start the gradient at
	 * @param lightestColor the color to end the gradient at
	 * @return Color[]
	 */
	public static Color[] getGradient(Color darkestColor, Color lightestColor) {
		return getGradient(darkestColor, lightestColor, DEFAULT_COLOR_AMOUNT);
	}

}
