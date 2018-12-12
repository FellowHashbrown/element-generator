/**
 * 
 */
package elementgenerator.gui.customcomponents;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class FileChooser {

	// Instance Fields
	private JFileChooser chooser;
	
	/**
	 * Creates a new FileChooser object
	 * @param dialogTitle the title in the file chooser dialog
	 * @param startDirectory where to start the file chooser at
	 * @param filter a FileNameExtensionFilter for acceptable file extensions
	 * @param allFileFilter whether or not to include the all file filter
	 */
	public FileChooser(String dialogTitle, String startDirectory,
			FileNameExtensionFilter filter, boolean allFileFilter) {
		
		// Setup Chooser
		chooser = new JFileChooser();
		chooser.setDialogTitle(dialogTitle);
		chooser.setAcceptAllFileFilterUsed(allFileFilter);
		chooser.setFileFilter(filter);
		
		// Test if directory exists
		File startDir = new File(startDirectory);
		while(! startDir.exists())
			startDir = startDir.getParentFile();
		chooser.setCurrentDirectory(startDir);
		
	}
	
	/**
	 * Creates a new FileChooser object
	 * @param dialogTitle the title in the file chooser dialog
	 * @param startDirectory where to start the file chooser at
	 * @param filter a FileNameExtensionFilter for acceptable file extensions
	 */
	public FileChooser(String dialogTitle, String startDirectory,
			FileNameExtensionFilter filter) {
		this(dialogTitle, startDirectory, filter, false);
	}
	
	/**
	 * Returns the JFileChooser object
	 * @return JFileChooser
	 */
	public JFileChooser getFileChooser() {
		return chooser;
	}
	
}
