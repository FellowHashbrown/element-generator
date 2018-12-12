package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;

/**
 * A class for JTextArea creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class TextArea {
	
	// Instance Fields
	private JTextArea textarea;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new TextArea object
	 * @param text the text on the TextArea
	 * @param toolTipText the tool tip of the TextArea
	 * @param anchor how to anchor the TextArea on the panel
	 * @param fill how to fill the TextArea on the panel
	 * @param gridx the x location of the TextArea on the panel
	 * @param gridy the y location of the TextArea on the panel
	 * @param gridw the width of the TextArea on the panel
	 * @param gridh the height of the TextArea on the panel
	 * @param font the Font for the TextArea
	 */
	public TextArea(String text, String toolTipText, 
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font, DocumentListener documentListener) {
		
		// Setup TextArea
		textarea = new JTextArea(text);
		textarea.setFont(font);
		textarea.setToolTipText(toolTipText);
		textarea.setColumns(10);
		textarea.getDocument().addDocumentListener(documentListener);
		
		// Setup constraints
		constraints = new GridBagConstraints();
		if(anchor != 0)
			constraints.anchor = anchor;
		if(fill != 0)
			constraints.fill = fill;
		constraints.gridwidth = gridw;
		constraints.gridheight = gridh;
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		
	}
	
	/**
	 * Returns the TextArea object
	 * @return JTextArea
	 */
	public JTextArea getTextArea() {
		return textarea;
	}
	
	/**
	 * Returns the constraints used for the panel
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}
	
	/**
	 * Adds a new document listener to the JTextArea object
	 * @param documentListener the new DocumentListener object 
	 */
	public void addDocumentListener(DocumentListener documentListener) {
		textarea.getDocument().addDocumentListener(documentListener);
	}

}
