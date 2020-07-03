package com.reason.lang.napkin;

import java.util.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.reason.lang.BaseParsingTestCase;
import com.reason.lang.core.ORUtil;
import com.reason.lang.core.psi.PsiFunctionCallParams;
import com.reason.lang.core.psi.PsiJsObject;
import com.reason.lang.core.psi.PsiLet;
import com.reason.lang.core.psi.PsiLetBinding;
import com.reason.lang.core.psi.PsiLocalOpen;
import com.reason.lang.core.psi.PsiObjectField;

public class JsObjectParsingTest extends BaseParsingTestCase {
    public JsObjectParsingTest() {
        super("", "res", new NsParserDefinition());
    }

    public void testInFunction() {
        PsiLet e = first(letExpressions(parseCode("let x = fn(~props={\"a\": id, \"b\": 0});")));

        PsiLetBinding binding = e.getBinding();
        PsiJsObject object = PsiTreeUtil.findChildOfType(binding, PsiJsObject.class);
        assertNotNull(object);

        Collection<PsiObjectField> fields = object.getFields();
        assertEquals(2, fields.size());
    }

    public void testDeclaringOpen() {
        PsiLet e = first(letExpressions(parseCode("let style = {" +
                "\"marginLeft\": marginLeft, \"marginRight\": marginRight,\"fontSize\": \"inherit\"," +
                "\"fontWeight\": bold ? \"bold\" : \"inherit\"," +
                "\"textTransform\": transform == \"uc\" ? \"uppercase\" : \"unset\",};")));

        PsiLetBinding binding = e.getBinding();
        PsiJsObject object = PsiTreeUtil.findChildOfType(binding, PsiJsObject.class);
        assertNotNull(object);

        Collection<PsiObjectField> fields = object.getFields();
        assertEquals(5, fields.size());
    }

    public void testModuleOpen() {
        PsiLet e = first(letExpressions(parseCode("let computingProperties = createStructuredSelector(" +
                "    ComputingReducers.{ \"lastUpdate\": selectors.getLastUpdate },\n" +
                "  );")));

        PsiLetBinding binding = e.getBinding();
        PsiFunctionCallParams call = PsiTreeUtil.findChildOfType(binding, PsiFunctionCallParams.class);
        PsiLocalOpen open = PsiTreeUtil.findChildOfType(call, PsiLocalOpen.class);
        PsiJsObject jsObject = ORUtil.findImmediateFirstChildOfClass(open, PsiJsObject.class);
        assertNotNull(jsObject);
    }
}
