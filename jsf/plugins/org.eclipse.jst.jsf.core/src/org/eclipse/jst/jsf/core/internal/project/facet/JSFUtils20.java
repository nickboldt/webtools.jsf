package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;

/**
 * JSF Utils instance for JSF 2.0.
 * 
 * @author cbateman
 * 
 */
/* package: use JSFUtilFactory */class JSFUtils20 extends JSFUtils12
{
    private static final String DEFAULT_DEFAULT_MAPPING_SUFFIX = "xhtml"; //$NON-NLS-1$

    /**
     * @param modelProvider
     */
    protected JSFUtils20(final IModelProvider modelProvider)
    {
        super(JSFVersion.V2_0, modelProvider);
    }

    /**
     * @param jsfVersion
     * @param modelProvider 
     */
    protected JSFUtils20(final JSFVersion jsfVersion, final IModelProvider modelProvider)
    {
        super(jsfVersion, modelProvider);
        if (jsfVersion.compareTo(JSFVersion.V2_0) < 0)
        {
            throw new IllegalArgumentException(
                    "JSFVersion must be at least 2.0"); //$NON-NLS-1$
        }
    }

    @Override
    protected String getDefaultDefaultSuffix()
    {
        return DEFAULT_DEFAULT_MAPPING_SUFFIX;
    }
}
