package elementgenerator.gui.customcomponents;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * A class to make JScrollPane creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class ScrollPane {
	
	// Instance Fields
	private JScrollPane scrollpane;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new ScrollPane object
	 * @param anchor how to anchor the ScrollPane on a panel
	 * @param fill how to fill the ScrollPane on a panel
	 * @param gridx the x location of the ScrollPane on a panel
	 * @param gridy the y location of the ScrollPane on a panel
	 * @param gridw the width of the ScrollPane on a panel
	 * @param gridh the height of the ScrollPane on a panel
	 */
	public ScrollPane(int speed, int verticalAttr, int horizontalAttr,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh) {
		
		// Setup ScrollPane
		scrollpane = new JScrollPane();
		if(verticalAttr != 0) {
			scrollpane.setVerticalScrollBarPolicy(verticalAttr);
			scrollpane.getVerticalScrollBar().setUnitIncrement(speed);
		}
		else
			scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		if(horizontalAttr != 0) {
			scrollpane.setHorizontalScrollBarPolicy(horizontalAttr);
			scrollpane.getHorizontalScrollBar().setUnitIncrement(speed);
		}
		else
			scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Setup contraints
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
	 * Returns the JScrollPane object
	 * @return JScrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollpane;
	}
	
	/**
	 * Returns the GridBagConstraints object
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}

}
