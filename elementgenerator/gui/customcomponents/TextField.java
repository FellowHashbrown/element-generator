package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

/**
 * A class for JTextField creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class TextField {
	
	// Instance Fields
	private JTextField textfield;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new TextField object
	 * @param text the text on the TextField
	 * @param toolTipText the tool tip of the TextField
	 * @param anchor how to anchor the TextField on the panel
	 * @param fill how to fill the TextField on the panel
	 * @param gridx the x location of the TextField on the panel
	 * @param gridy the y location of the TextField on the panel
	 * @param gridw the width of the TextField on the panel
	 * @param gridh the height of the TextField on the panel
	 * @param font the Font for the TextField
	 */
	public TextField(String text, String toolTipText, 
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font, DocumentListener documentListener) {
		
		// Setup TextField
		textfield = new JTextField(text);
		textfield.setFont(font);
		textfield.setToolTipText(toolTipText);
		textfield.setColumns(10);
		textfield.getDocument().addDocumentListener(documentListener);
		
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
	 * Returns the TextField object
	 * @return JTextField
	 */
	public JTextField getTextField() {
		return textfield;
	}
	
	/**
	 * Returns the constraints used for the panel
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}
	
	/**
	 * Adds a new document listener to the JTextField object
	 * @param documentListener a new DocumentListener object
	 */
	public void addDocumentListener(DocumentListener documentListener) {
		textfield.getDocument().addDocumentListener(documentListener);
	}

}
