package org.eclipse.jst.jsf.test.util.mock;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class MockResource implements IResource
{
    private final int _type;
    private final IPath _path;
    public final static List<Integer> VALID_TYPES;

    static
    {
        final List<Integer> list = new ArrayList<Integer>();
        list.add(IResource.FILE);
        list.add(IResource.FOLDER);
        list.add(IResource.PROJECT);
        list.add(IResource.ROOT);
        VALID_TYPES = Collections.unmodifiableList(list);
    }

    public MockResource(final int type, final IPath path)
    {
        _path = path;
        _type = type;
        Assert.assertTrue(VALID_TYPES.contains(type));
    }

    public int getType()
    {
        return _type;
    }

    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Class adapter)
    {
        throw new UnsupportedOperationException();
    }

    public boolean contains(final ISchedulingRule rule)
    {
        throw new UnsupportedOperationException();

    }

    public boolean isConflicting(final ISchedulingRule rule)
    {
        throw new UnsupportedOperationException();

    }

    public void accept(final IResourceProxyVisitor visitor, final int memberFlags)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void accept(final IResourceVisitor visitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void accept(final IResourceVisitor visitor, final int depth,
            final boolean includePhantoms) throws CoreException
            {
        throw new UnsupportedOperationException();
            }

    public void accept(final IResourceVisitor visitor, final int depth, final int memberFlags)
    throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void clearHistory(final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void copy(final IPath destination, final boolean force, final IProgressMonitor monitor)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void copy(final IPath destination, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
            {
        throw new UnsupportedOperationException();

            }

    public void copy(final IProjectDescription description, final boolean force,
            final IProgressMonitor monitor) throws CoreException
            {
        throw new UnsupportedOperationException();

            }

    public void copy(final IProjectDescription description, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
            {
        throw new UnsupportedOperationException();

            }

    public IMarker createMarker(final String type) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IResourceProxy createProxy()
    {
        throw new UnsupportedOperationException();
    }

    public void delete(final boolean force, final IProgressMonitor monitor)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void delete(final int updateFlags, final IProgressMonitor monitor)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void deleteMarkers(final String type, final boolean includeSubtypes, final int depth)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public boolean exists()
    {
        throw new UnsupportedOperationException();
    }

    public IMarker findMarker(final long id) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IMarker[] findMarkers(final String type, final boolean includeSubtypes, final int depth)
    throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public int findMaxProblemSeverity(final String type, final boolean includeSubtypes,
            final int depth) throws CoreException
            {
        throw new UnsupportedOperationException();
            }

    public String getFileExtension()
    {
        String name = getName();
        int index = name.lastIndexOf('.');
        if (index == -1)
            return null;
        if (index == (name.length() - 1))
            return ""; //$NON-NLS-1$
        return name.substring(index + 1);
    }

    public IPath getFullPath()
    {
        throw new UnsupportedOperationException();
    }

    public long getLocalTimeStamp()
    {
        throw new UnsupportedOperationException();
    }

    public IPath getLocation()
    {
        throw new UnsupportedOperationException();
    }

    public URI getLocationURI()
    {
        throw new UnsupportedOperationException();
    }

    public IMarker getMarker(final long id)
    {
        throw new UnsupportedOperationException();
    }

    public long getModificationStamp()
    {
        throw new UnsupportedOperationException();
    }

    public String getName()
    {
        return _path.lastSegment();
    }

    public IContainer getParent()
    {
        throw new UnsupportedOperationException();
    }

    public Map<?,?> getPersistentProperties() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public String getPersistentProperty(final QualifiedName key) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IProject getProject()
    {
        throw new UnsupportedOperationException();
    }

    public IPath getProjectRelativePath()
    {
        throw new UnsupportedOperationException();
    }

    public IPath getRawLocation()
    {
        throw new UnsupportedOperationException();
    }

    public URI getRawLocationURI()
    {
        throw new UnsupportedOperationException();
    }

    public ResourceAttributes getResourceAttributes()
    {
        throw new UnsupportedOperationException();
    }

    public Map<?,?> getSessionProperties() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public Object getSessionProperty(final QualifiedName key) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IWorkspace getWorkspace()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isAccessible()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isDerived()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isDerived(final int options)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isHidden()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isHidden(final int options)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isLinked()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isVirtual()
    {
        throw new UnsupportedOperationException();
    }

    public boolean hasFilters()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isLinked(final int options)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isLocal(final int depth)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isPhantom()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isReadOnly()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isSynchronized(final int depth)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isTeamPrivateMember()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isTeamPrivateMember(final int options)
    {
        throw new UnsupportedOperationException();
    }

    public void move(final IPath destination, final boolean force, final IProgressMonitor monitor)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void move(final IPath destination, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
            {
        throw new UnsupportedOperationException();

            }

    public void move(final IProjectDescription description, final boolean force,
            final boolean keepHistory, final IProgressMonitor monitor) throws CoreException
            {
        throw new UnsupportedOperationException();

            }

    public void move(final IProjectDescription description, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
            {
        throw new UnsupportedOperationException();

            }

    public void refreshLocal(final int depth, final IProgressMonitor monitor)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void revertModificationStamp(final long value) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setDerived(final boolean isDerived) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setDerived(final boolean isDerived, final IProgressMonitor monitor)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setHidden(final boolean isHidden) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setLocal(final boolean flag, final int depth, final IProgressMonitor monitor)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public long setLocalTimeStamp(final long value) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void setPersistentProperty(final QualifiedName key, final String value)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setReadOnly(final boolean readOnly)
    {
        throw new UnsupportedOperationException();

    }

    public void setResourceAttributes(final ResourceAttributes attributes)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setSessionProperty(final QualifiedName key, final Object value)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setTeamPrivateMember(final boolean isTeamPrivate)
    throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void touch(final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public boolean isFiltered()
    {
        throw new UnsupportedOperationException();
    }
}
