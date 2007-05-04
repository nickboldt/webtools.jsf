/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.project.facet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * JSF Facet Uninstall Delegate for WTP faceted projects.
 *	
 *	<li> removes JSF servlet, servlet-mappings and context-params
 *	<li> leaves JSF configuration files on disk
 *  <li> removes JSF classpath containers
 *  
 * @author Gerry Kessler - Oracle
 * @since M1
 */
public class JSFFacetUninstallDelegate implements IDelegate {

	public void execute(IProject project, IProjectFacetVersion fv,
			Object config, IProgressMonitor monitor) throws CoreException {
		{

			if (monitor != null) {
				monitor.beginTask("", 1); //$NON-NLS-1$
			}

			try {

				// Remove JSF Libraries
				removeJSFLibaries(project, monitor);

				// remove servlet stuff from web.xml
				uninstallJSFReferencesFromWebApp(project, monitor);

				if (monitor != null) {
					monitor.worked(1);
				}
			} finally {
				if (monitor != null) {
					monitor.done();
				}
			}
		}
	}

	private void removeJSFLibaries(IProject project, IProgressMonitor monitor) {
		 final IJavaProject jproj = JavaCore.create(project);
		 List keptEntries = new ArrayList();
		 try {
			IClasspathEntry[] entries = jproj.getRawClasspath();
			  keptEntries = new ArrayList();
			 for (int i=0;i<entries.length;i++){
				 IClasspathEntry entry = entries[i];
				 if (entry.getEntryKind() == IClasspathEntry.CPE_CONTAINER && 
						 ! entry.getPath().segment(0)
						 	.equals(JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID))
					 keptEntries.add(entry);
			 }
		} catch (JavaModelException e) {
			JSFCorePlugin.log(e, "Cannot get classpath entries to remove JSF Libraries for: "+project.getName());
		}
		 
		 if (keptEntries.size() > 0){
			 try {
				jproj.setRawClasspath((IClasspathEntry[])keptEntries.toArray(new IClasspathEntry[0]), monitor);
			} catch (JavaModelException e) {
				JSFCorePlugin.log(e, "Exception occured while removing JSF Libraries during JSF Facet uninstall");
			}
		 }
	
		
	}

	private void uninstallJSFReferencesFromWebApp(IProject project,
			IProgressMonitor monitor) {
		WebArtifactEdit artifactEdit = JSFUtils
				.getWebArtifactEditForWrite(project);
		WebApp webApp = artifactEdit.getWebApp();

		try {
			Servlet servlet = JSFUtils.findJSFServlet(webApp);
			if (servlet == null)
				return;

			// remove faces url mappings
			removeJSFURLMappings(webApp, servlet);
			// remove context params
			removeJSFContextParams(webApp, servlet);
			// remove servlet
			removeJSFServlet(webApp, servlet);

		} finally {
			if (artifactEdit != null) {
				artifactEdit.saveIfNecessary(monitor);
				artifactEdit.dispose();
			}
		}

	}

	private void removeJSFURLMappings(WebApp webApp, Servlet servlet) {
		while (webApp.getServletMapping(servlet) != null) {
			webApp.getServletMappings().remove(
					webApp.getServletMapping(servlet));
		}
	}

	private void removeJSFContextParams(WebApp webApp, Servlet servlet) {
		if (webApp.getVersionID() == J2EEVersionConstants.WEB_2_3_ID){
			Iterator it = webApp.getContexts().iterator();
			while (it.hasNext()) {
				ContextParam cp = (ContextParam) it.next();
				if (cp.getParamName().equals(JSFUtils.JSF_CONFIG_CONTEXT_PARAM)) {
					webApp.getContexts().remove(cp);
					break;
				}
			}
		}
		else {//could check for 2_4 version here.   
			//hoping that the API gets fixed so I don't need to do any of this version checking
			Iterator it = webApp.getContextParams().iterator();
			while (it.hasNext()) {
				ParamValue cp = (ParamValue) it.next();
				if (cp.getName().equals(JSFUtils.JSF_CONFIG_CONTEXT_PARAM)) {
					webApp.getContextParams().remove(cp);
					break;
				}
			}
		}
	}

	private void removeJSFServlet(WebApp webApp, Servlet servlet) {
		webApp.getServlets().remove(servlet);
	}

}