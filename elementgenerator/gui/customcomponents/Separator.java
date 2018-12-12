package elementgenerator.gui.customcomponents;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSeparator;

public class Separator {
	
	// Instance Fields
	private JSeparator separator;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new Separator object
	 * @param orientation the orientation of the Separator
	 * @param anchor how to anchor the Separator on the panel
	 * @param fill how to fill the Separator on the panel
	 * @param gridx the x location of the Separator on the panel
	 * @param gridy the y location of the Separator on the panel
	 * @param gridw the width of the Separator on the panel
	 * @param gridh the height of the Separator on the panel
	 */
	public Separator(int orientation, int anchor, int fill, int gridx, int gridy, int gridw, int gridh) {
		
		// Setup separator
		separator = new JSeparator();
		separator.setOrientation(orientation);
		
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
	 * Returns the JSeparator object
	 * @return JSeparator
	 */
	public JSeparator getSeparator() {
		return separator;
	}
	
	/**
	 * Returns the constraints for the panel
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}

}
