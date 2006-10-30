/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfcore;

import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.HiddenTagConverter;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.converter.TagConverterToSpan;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;


/**
 * @author mengbo
 * @version 1.5
 */
public class JSFCoreConverterFactory implements IConverterFactory
{

    /**
     * 
     */
    public JSFCoreConverterFactory()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#createConverter(org.w3c.dom.Element)
     */
    public ITagConverter createConverter(Element element, int mode)
    {
        String tagName = element.getLocalName();
        ITagConverter converter;
        if (IJSFConstants.TAG_VIEW.equalsIgnoreCase(tagName) || IJSFConstants.TAG_SUBVIEW.equalsIgnoreCase(tagName))
        {
            converter = new ViewTagConverter(element);
            ((ViewTagConverter) converter).setNeedBorderDecorator(true);
        }
        else if (IJSFConstants.TAG_FACET.equalsIgnoreCase(tagName)
                || IJSFConstants.TAG_VERBATIM.equalsIgnoreCase(tagName))
        {
            AbstractTagConverter toSpan = new TagConverterToSpan(element);
            toSpan.setMinWidth(10);
            toSpan.setMinHeight(10);
            toSpan.setNeedBorderDecorator(true);
            converter = toSpan;
        }
        else if (IJSFConstants.TAG_LOADBUNDLE.equalsIgnoreCase(tagName))
        {
            converter = new LoadBundleTagConverter(element, getJSFCoreSharedImage(tagName));
        }
        else
        {
            converter = new HiddenTagConverter(element, getJSFCoreSharedImage(tagName));
        }
        converter.setMode(mode);
        return converter;
    }

    /**
     * @param tagName
     * @return
     */
    private Image getJSFCoreSharedImage(String tagName)
    {
        Image image = JSFUIPlugin.getDefault().getImage("palette/JSFCORE/small/JSF_" + tagName.toUpperCase() + ".gif");
        return image;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#getSupportedURI()
     */
    public String getSupportedURI()
    {
        return IJMTConstants.URI_JSF_CORE;
    }
}
