/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.tests.updated;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataModelManagerContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;
import org.junit.Test;

public class MetaDataQueryContextFactoryTests extends TestCase{

	@Test
	public void testGetInstance() {
		MetaDataQueryContextFactory factory = MetaDataQueryContextFactory.getInstance();
		assertNotNull(factory);
		MetaDataQueryContextFactory factory2 = MetaDataQueryContextFactory.getInstance();
		assertNotNull(factory2);
		assertSame(factory, factory2);
	}

	@Test
	public void testCreateTaglibDomainContext() {
		MetaDataQueryContextFactory factory = MetaDataQueryContextFactory.getInstance();
		assertNotNull(factory);
		
		IProject project = new MockWorkspaceContext().createProject("test");
		IMetaDataDomainContext context = factory.createTaglibDomainModelContext(project);
		assertNotNull(context);
		assertEquals("TagLibraryDomain", context.getDomainId());
		assertNotNull(context.getAdapter((IProject.class)));
		assertTrue(context instanceof IMetaDataModelManagerContext);
		assertSame(((IMetaDataModelManagerContext)context).getProject(), project);
		
//	tests deprecated null project taglibdomain... even though not supposed to pass null project	
		context = factory.createTaglibDomainModelContext(null);
		assertNotNull(context);
		assertEquals("TagLibraryDomain", context.getDomainId());
		assertNull(context.getAdapter((IProject.class)));
		assertTrue(context instanceof IMetaDataModelManagerContext);
	}

}
