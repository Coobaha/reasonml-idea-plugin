package com.reason.ide.reference;

import com.intellij.psi.PsiElement;
import com.reason.ide.ORBasePlatformTestCase;
import com.reason.lang.core.psi.PsiParameter;

public class ResolveParameterElementRMLTest extends ORBasePlatformTestCase {

  public void test_Rml_parenLess() {
    configureCode("A.re", "let add10 = x => x<caret> + 10;");

    PsiElement e = myFixture.getElementAtCaret();
    assertInstanceOf(e.getParent(), PsiParameter.class);
    assertEquals("A.add10[x]", ((PsiParameter) e.getParent()).getQualifiedName());
  }

  public void test_Ocl_parenLess() {
    configureCode("A.ml", "let add10 x = x<caret> + 10");

    PsiElement e = myFixture.getElementAtCaret();
    assertInstanceOf(e.getParent(), PsiParameter.class);
    assertEquals("A.add10[x]", ((PsiParameter) e.getParent()).getQualifiedName());
  }
}