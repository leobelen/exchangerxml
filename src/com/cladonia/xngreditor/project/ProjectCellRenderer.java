/*
 * $Id: ProjectCellRenderer.java,v 1.1 2004/03/25 18:54:53 edankert Exp $
 *
 * Copyright (C) 2002, Cladonia Ltd. All rights reserved.
 *
 * This software is the proprietary information of Cladonia Ltd.  
 * Use is subject to license terms.
 */
package com.cladonia.xngreditor.project;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.TreeCellRenderer;

/**
 * The cell renderer for a Base Project Node.
 *
 * @version	$Revision: 1.1 $, $Date: 2004/03/25 18:54:53 $
 * @author Dogsbay
 */
public class ProjectCellRenderer extends JPanel implements TreeCellRenderer {
	private static final Border SELECTED_BORDER = new LineBorder( UIManager.getColor( "controlShadow"), 1);
	private static final Border UNSELECTED_BORDER = new EmptyBorder( 1, 1, 1, 1);
	private static final boolean DEBUG = false;
	private boolean selected = false;
	private JLabel textLabel = null;
	private JLabel iconLabel = null;
	
	/**
	 * The constructor for the renderer, sets the font type etc...
	 */
	public ProjectCellRenderer() {
		super( new FlowLayout( FlowLayout.LEFT, 0, 0));
		
		setOpaque( false);
		
		iconLabel = new JLabel();
		iconLabel.setOpaque( false);

		textLabel = new JLabel();
		textLabel.setOpaque( false);
		textLabel.setFont( getFont().deriveFont( Font.PLAIN));
		textLabel.setBorder( UNSELECTED_BORDER);
		textLabel.setForeground( UIManager.getColor("Tree.textForeground"));
		
		add( iconLabel);
		add( textLabel);
	}
	
	/**
	  * Configures the renderer based on the passed in components.
	  * The value is set from messaging the tree with
	  * <code>convertValueToText</code>, which ultimately invokes
	  * <code>toString</code> on <code>value</code>.
	  * The foreground color is set based on the selection and the icon
	  * is set based on on leaf and expanded.
	  */
	public Component getTreeCellRendererComponent(JTree tree, Object value,
						  boolean selected,
						  boolean expanded,
						  boolean leaf, int row,
						  boolean hasFocus) {
		this.selected = selected;
		
		if ( value instanceof BaseNode) {
			BaseNode node = (BaseNode)value;

			textLabel.setText( node.getName());

			if ( selected) {
			    textLabel.setBorder( SELECTED_BORDER);
				
				if ( expanded) {
					iconLabel.setIcon( node.getExpandedSelectedIcon());
				} else {
					iconLabel.setIcon( node.getSelectedIcon());
				}
			} else  {
			    textLabel.setBorder( UNSELECTED_BORDER);

				if ( expanded) {
					iconLabel.setIcon( node.getExpandedIcon());
				} else {
					iconLabel.setIcon( node.getIcon());
				}
			}
			
			// There needs to be a way to specify disabled icons.
			if ( !tree.isEnabled()) {
			    setEnabled( false);
			} else {
			    setEnabled( true);
			}
			
			setToolTipText( node.getDescription());
		    setComponentOrientation( tree.getComponentOrientation());
		} else {
			String stringValue = tree.convertValueToText( value, selected, expanded, leaf, row, hasFocus);
		
			textLabel.setText(stringValue);

			if ( selected) {
			    textLabel.setForeground( UIManager.getColor("Tree.selectionForeground"));
			} else  {
			    textLabel.setForeground( UIManager.getColor("Tree.textForeground"));
			}

		    setComponentOrientation(tree.getComponentOrientation());
		}

		return this;
	}

//	private int getLabelStart() {
//		Icon currentI = getIcon();
//		if(currentI != null && getText() != null) {
//		    return currentI.getIconWidth() + Math.max(0, getIconTextGap() - 1);
//		}
//		return 0;
//	}
	
	/**
	 * Paints this cell.
	 *
	 * @param g the graphics object.
	 */
//	public void paint(Graphics g) {
//
//		if ( selected) {
//			int imageOffset = getLabelStart();
//
//		    Color bsColor = UIManager.getColor("Tree.selectionBorderColor");
//
//		    if (bsColor != null) {
//				g.setColor(bsColor);
//				if(getComponentOrientation().isLeftToRight()) {
//				    g.drawRect(imageOffset, 0, getWidth() - 1 - imageOffset,
//					       getHeight() - 1);
//				} else {
//				    g.drawRect(0, 0, getWidth() - 1 - imageOffset,
//					       getHeight() - 1);
//				}
//		    }
//		}
//		super.paint(g);
//	}
	
} 
