/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;


/**
 * Change listener used by <code>DialogField</code>
 * 
 * @author mengbo
 */
public interface IDialogFieldChangeListener {

	/**
	 * The dialog field has changed.
	 * @param field
	 */
	void dialogFieldChanged(DialogField field);
}
