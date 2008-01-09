package org.eclipse.jst.jsf.validation.el.tests.jsp;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

/**
 * Test cases for assignability test on variable and properties
 * 
 * @author cbateman
 */
public class AssignabilityTestCase extends SingleJSPTestCase
{
    public AssignabilityTestCase()
    {
        super("/testdata/jsps/assignability.jsp.data", "/assignability.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("myBean.integerProperty + 3", getELText(_structuredDocument,880));
        assertEquals("myBean.writableStringProperty", getELText(_structuredDocument,938));
        assertEquals("myBean.stringProperty", getELText(_structuredDocument,997));
        assertEquals("bundle.bundleProp2", getELText(_structuredDocument,1047));
        assertEquals("requestScope.myBeanSubClass", getELText(_structuredDocument,1097));
        assertEquals("myBean.validate", getELText(_structuredDocument,1156));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(880, Signature.SIG_LONG, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(938, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        assertNoError(997, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1047, TypeConstants.TYPE_STRING, IAssignable.ASSIGNMENT_TYPE_RHS);
        assertNoError(1097, "Lbeans.MyBeanSubClass;", IAssignable.ASSIGNMENT_TYPE_RHS|IAssignable.ASSIGNMENT_TYPE_LHS);
        // TODO: this will fail when the jsf-impl/api jars are injected since then we will
        // have fully resolved (L) signatures instead of (Q) unresolved ones.
        assertNoError(1156, "(QFacesContext;QUIComponent;Ljava.lang.Object;)V", IAssignable.ASSIGNMENT_TYPE_NONE);
    }

    @Override
    public void testWarningExprs()
    {
        // no warnings
    }

    @Override
    public void testErrorExprs()
    {
        // no errors
    }
}
