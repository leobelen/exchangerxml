/*
 * $Id: SelectElementAction.java,v 1.4 2004/10/28 16:17:07 edankert Exp $
 *
 * Copyright (C) 2002, Cladonia Ltd. All rights reserved.
 *
 * This software is the proprietary information of Cladonia Ltd.  
 * Use is subject to license terms.
 */
package com.cladonia.xngreditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import com.cladonia.xml.ExchangerDocument;
import com.cladonia.xml.editor.Editor;
import com.cladonia.xngreditor.ExchangerEditor;
import com.cladonia.xngreditor.XngrImageLoader;

/**
 * An action that can be used to Comment the selected text.
 *
 * @version	$Revision: 1.4 $, $Date: 2004/10/28 16:17:07 $
 * @author Dogsbay
 */
 public class SelectElementAction extends AbstractAction {
 	private static final boolean DEBUG = false;
	
	private Editor editor = null;
	private ExchangerEditor parent = null;

 	/**
	 * The constructor for the action that Comments the selected text.
	 *
	 * @param editor the XML Editor
	 */
 	public SelectElementAction( ExchangerEditor parent) {
		super( "Select Element");
		
		this.parent = parent;
		
		if (DEBUG) System.out.println( "SelectElementAction( "+editor+")");
		
		//putValue( ACCELERATOR_KEY, KeyStroke.getKeyStroke( KeyEvent.VK_E, InputEvent.CTRL_MASK, false));
		putValue( SMALL_ICON, XngrImageLoader.get().getImage( "com/cladonia/xngreditor/icons/SelectElement16.gif"));
		putValue( SHORT_DESCRIPTION, "Select Element");
		
		setEnabled( false);
	}
	
	/**
	 * Sets the current view.
	 *
	 * @param view the current view.
	 */
	public void setView( Object view) {
		if ( view instanceof Editor) {
			editor = (Editor)view;
		} else {
			editor = null;
		}
		
		setDocument( parent.getDocument());
	}

	public void setDocument( ExchangerDocument doc) {
		if ( doc != null) {
			setEnabled( editor != null);
		} else {
			setEnabled( false);
		}
	}
 	
	/**
	 * The implementation of the comment action, called 
	 * after a user action.
	 *
	 * @param event the action event.
	 */
 	public void actionPerformed( ActionEvent event) {
 		if (DEBUG) System.out.println( "SelectElementAction.actionPerformed( "+event+")");
		
		editor.selectElement();
		editor.setFocus();
 	}
}
