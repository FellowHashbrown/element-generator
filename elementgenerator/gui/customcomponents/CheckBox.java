package elementgenerator.gui.customcomponents;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

/**
 * A Class to make JCheckBox creation much easier to look at
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class CheckBox {
	
	// Instance Fields
	private JCheckBox checkbox;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new CheckBox object
	 * @param text the text for the CheckBox
	 * @param toolTip the tool tip for the CheckBox
	 * @param anchor how to anchor the CheckBox to the panel
	 * @param fill how to fill the CheckBox to the panel
	 * @param gridx the x location to set the CheckBox at
	 * @param gridy the y location to set the CheckBox at
	 * @param gridw the width the CheckBox takes up
	 * @param gridh the height the CheckBox takes up
	 * @param font the Font used for the CheckBox
	 * @param actionListener the ActionListener to be used with the CheckBox
	 */
	public CheckBox(String text, String toolTip,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh,
			Font font, ActionListener actionListener) {
		
		// Setup CheckBox
		checkbox = new JCheckBox(text);
		checkbox.setFont(font);
		checkbox.setToolTipText(toolTip);
		checkbox.addActionListener(actionListener);
		
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
	 * Returns the JCheckBox used for the Panel
	 * @return JCheckBox
	 */
	public JCheckBox getCheckBox() {
		return checkbox;
	}
	
	/**
	 * Returns the GridBagConstraints used for the panel
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}
	
	/**
	 * Adds an action listener to the checkbox
	 * @param actionListener the action listener to add
	 */
	public void addActionListener(ActionListener actionListener) {
		checkbox.addActionListener(actionListener);
	}

}
