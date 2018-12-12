package elementgenerator.gui.customcomponents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

/**
 * A class to make JPanel creation much simpler
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Panel {
	
	// Instance Fields
	private JPanel panel;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	/**
	 * Creates a new Panel object
	 * @param rows the amount of rows for the layout of the panel
	 * @param columns the amount of columns for the layout of the panel
	 * @param anchor how to anchor the panel on another panel or frame
	 * @param fill how to fill the panel on another panel or frame
	 * @param gridx the x location of the panel on another panel or frame
	 * @param gridy the y location of the panel on another panel or frame
	 * @param gridw the width of the panel on another panel or frame
	 * @param gridh the height of the panel on another panel or frame
	 */
	public Panel(int rows, int columns,
			int anchor, int fill, int gridx, int gridy, int gridw, int gridh) {
		
		// Setup panel
		panel = new JPanel();
		
		// Setup Constraints
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
		
		// Setup Layout
		layout = new GridBagLayout();
		
		int[] columnWidths = new int[columns];
		double[] columnWeights=  new double[columns];
		int[] rowHeights = new int[rows];
		double[] rowWeights = new double[rows];
		
		for(int col = 0; col < columns; col++) {
			columnWidths[col] = 0;
			columnWeights[col] = 0.0;
		}
		
		for(int row = 0; row < rows; row++) {
			rowHeights[row] = 0;
			rowWeights[row] = 0.0;
		}
		
		layout.columnWidths = columnWidths;
		layout.columnWeights = columnWeights;
		layout.rowHeights = rowHeights;
		layout.rowWeights = rowWeights;
		
		// Set Layout
		panel.setLayout(layout);
		
	}
	
	/**
	 * Sets the weights for the specified columns and rows
	 * @param columns an array of columns to set to grow
	 * @param rows an array of rows to set to grow
	 */
	public void setGrow(int[] columns, int[] rows) {
		
		for(int i = 0; i < columns.length; i++)
			layout.columnWeights[columns[i]] = 1.0;
		for(int i = 0; i < rows.length; i++)
			layout.rowWeights[rows[i]] = 1.0;
		
		panel.setLayout(layout);
	}
	
	/**
	 * Returns the JPanel object
	 * @return JPanel
	 */
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * Returns the GridBagLayout object
	 * @return GridBagLayout
	 */
	public GridBagLayout getLayout() {
		return layout;
	}
	
	/**
	 * Returns the GridBagConstraints object
	 * @return GridBagConstraints
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}

}
