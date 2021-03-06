/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.io.Serializable;

/**
 * An abstraction of a tag element that maps to a JSF artifact (i.e. component, validator)
 *
 * @author cbateman
 *
 */
public interface IJSFTagElement extends ITagElement
{
    /**
     * An enumeration of different kinds of tags.
     * 
     * @author cbateman
     *
     */
    static class TagType implements Serializable
    {
        /**
         * serializable id
         */
        private static final long serialVersionUID = -2845327764902349963L;
        private final static int TYPE_COMPONENT = 0;
        private final static int TYPE_CONVERTER = 1;
        private final static int TYPE_VALIDATOR = 2;
        private final static int TYPE_HANDLER = 3;
        //private final static int TYPE_UNKNOWN = 4;

        private final static String[]  strValues =
            {"component", "converter", "validator", "handler"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

        private final int _intValue;
        
        public TagType(int intValue)
        {
            _intValue = intValue;
        }

        public String toString()
        {
            return strValues[_intValue];
        }

        protected final int intValue()
        {
            return _intValue;
        }
       
        public final static TagType COMPONENT = 
            new TagType(TYPE_COMPONENT);
        public final static TagType CONVERTER = 
            new TagType(TYPE_CONVERTER);
        public final static TagType VALIDATOR = 
            new TagType(TYPE_VALIDATOR);
        public final static TagType HANDLER = 
            new TagType(TYPE_HANDLER);
    }
    
    /**
     * @return the type of tag as distinguished by how it may affect the
     * view at runtime.
     */
    TagType getType();
}