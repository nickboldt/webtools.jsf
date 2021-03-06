/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context;

/**
 * Encapsulates runtime context in a JFace IDocument model
 * 
 * Clients may NOT implement this interface directly but may sub-class
 * Extend AbstractDocumentContext instead.

 * @author cbateman
 *
 */
public interface IDocumentContext extends IModelContext 
{
	// TODO: might make sense to have a getDocument() accessor here
	// currently empty
}
