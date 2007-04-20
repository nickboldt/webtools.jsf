package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for arithmetic multiply
 * 
 * @author cbateman
 *
 */
public class ArithmeticMultiplyTestCase extends SingleJSPTestCase 
{
    public ArithmeticMultiplyTestCase() 
    {
        super("/testdata/jsps/arithmeticMultiply.jsp.data", "/WEB-INF/arithmeticMultiply.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception 
    {
        super.setUp();
    }

    public void testSanity()
    {
        super.testSanity();
        
        assertEquals("myBean.integerProperty * 3", getELText(_structuredDocument,852));
        assertEquals("myBean.stringProperty * 3", getELText(_structuredDocument,912));
        assertEquals("myBean.integerProperty * myBean.integerProperty", getELText(_structuredDocument,971));
        assertEquals("myBean.bigIntegerProperty * 4", getELText(_structuredDocument,1052));
        assertEquals("myBean.bigIntegerProperty * 5.5", getELText(_structuredDocument,1115));
        assertEquals("myBean.bigDoubleProperty * 5.5", getELText(_structuredDocument,1180));
        assertEquals("myBean.doubleProperty * 5", getELText(_structuredDocument,1244));
        
        assertEquals("5 * 3", getELText(_structuredDocument,1336));
        assertEquals("5.5 * 4", getELText(_structuredDocument,1372));
        assertEquals("5.5 * null", getELText(_structuredDocument,1410));
        assertEquals("'5' * '4'", getELText(_structuredDocument,1454));
        assertEquals("null * null", getELText(_structuredDocument,1497));
        assertEquals("5.5 * 3.5", getELText(_structuredDocument,1543));

        assertEquals("5 * true", getELText(_structuredDocument,1609));
        assertEquals("myBean.integerProperty * myBean.booleanProperty", getELText(_structuredDocument,1648));
        assertEquals("myBean.stringArrayProperty * myBean.booleanProperty", getELText(_structuredDocument,1726));
        assertEquals("myBean.integerProperty * true ", getELText(_structuredDocument,1811));
        assertEquals("'a' * 'b'", getELText(_structuredDocument,1875));
        assertEquals("myBean.bigIntegerProperty * true", getELText(_structuredDocument,1918));
    }
    
    public void testNoErrorExprs()
    {
        assertNoError(852,Signature.SIG_LONG);
        assertNoError(912,Signature.SIG_LONG);
        assertNoError(971,Signature.SIG_LONG);
        assertNoError(1052, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1115, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1180, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1244, Signature.SIG_DOUBLE);
    }
    
    public void testWarningExprs()
    {
        List list = assertSemanticWarning(1336, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
        
        list = assertSemanticWarning(1372, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1410, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1454, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1497, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);
        
        list = assertSemanticWarning(1543, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
    }
    
    public void testErrorExprs()
    {
        List list = assertSemanticError(1609, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1648, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1726, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
        
        list = assertSemanticError(1811, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1875, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS_ID);
        
        list = assertSemanticError(1918, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
