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
package org.eclipse.jst.pagedesigner.converter.jsp;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.converter.HiddenTagConverter;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.converter.TagConverterToDumBlock;
import org.eclipse.jst.pagedesigner.jsp.core.IJSPCoreConstants;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSPConverterFactory implements IConverterFactory {
    private final ILabelProvider  _labelProvider;
    
	/**
	 * 
	 */
	public JSPConverterFactory() {
        _labelProvider = new MyLabelProvider();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#createConverter(org.w3c.dom.Element)
	 */
	public ITagConverter createConverter(Element element, int mode) {
		String tagName = element.getLocalName();

		if (mode == IConverterFactory.MODE_PREVIEW) {
			// we want to generate the included page in preview, so
			// handle differently
			if (IJSPCoreConstants.TAG_INCLUDE.equalsIgnoreCase(tagName)) {
				IncludeTagConverterPreview c = new IncludeTagConverterPreview(
						element, "page");
				c.setMode(mode);
				return c;
			} else if (IJSPCoreConstants.TAG_DIRECTIVE_INCLUDE
					.equalsIgnoreCase(tagName)) {
				IncludeTagConverterPreview c = new IncludeTagConverterPreview(
						element, "file");
				c.setMode(mode);
				return c;
			} else if (IJSPCoreConstants.TAG_ROOT.equalsIgnoreCase(tagName)) {
				TagConverterToDumBlock c = new TagConverterToDumBlock(element);
				c.setNeedBorderDecorator(true);
				c.setMode(mode);
				return c;
			} else {
				return new HiddenTagConverter(element, _labelProvider);
			}
		}
        if (IJSPCoreConstants.TAG_ROOT.equalsIgnoreCase(tagName)) {
        	TagConverterToDumBlock c = new TagConverterToDumBlock(element);
        	c.setNeedBorderDecorator(true);
        	c.setMode(mode);
        	return c;
        }
        return new HiddenTagConverter(element, _labelProvider);
	}
    
    private static class MyLabelProvider extends org.eclipse.jface.viewers.LabelProvider
    {

        public Image getImage(Object element) 
        {
            if (element instanceof ITagConverter)
            {
                final Element hostElement = ((ITagConverter)element).getHostElement();
                final String tagName = hostElement.getLocalName();
                return getJSPSharedImage(tagName);
            }
            
            return null;
        }
    }

	/**
	 * @param tagName
	 * @return
	 */
	private static Image getJSPSharedImage(String tagName) {
		Image image = PDPlugin.getDefault().getImage(
				"palette/JSP/small/JSP_" + tagName.toUpperCase() + ".gif");
		return image;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#getSupportedURI()
	 */
	public String getSupportedURI() {
		return IJMTConstants.URI_JSP;
	}

}
