package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A class to make JLabel creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Label {
	
	// Instance Fields
	private JLabel label;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new Label object with constraints
	 * @param text the text for the label
	 * @param toolTipText the tool tip text for the label
	 * @param icon the icon shown on the label (or null)
	 * @param anchor how to anchor the Label on the panel
	 * @param fill how to fill the Label on the panel
	 * @param gridx the x location of the label on the panel
	 * @param gridy the y location of the label on the panel
	 * @param gridw the width of the label on the panel
	 * @param gridh the height of the label on the panel
	 * @param font the Font to be used for the label
	 */
	public Label(String text, String toolTipText, ImageIcon icon,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font) {
		
		// Setup Label
		label = new JLabel(text);
		label.setFont(font);
		label.setToolTipText(toolTipText);
		label.setIcon(icon);
		
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
	 * Returns the JLabel object 
	 * @return JLabel
	 */
	public JLabel getLabel() {
		return label;
	}
	
	/**
	 * Returns the constraints for the label
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}

}
