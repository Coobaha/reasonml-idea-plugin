package com.reason.lang.napkin;

import com.reason.lang.core.psi.impl.PsiTry;

@SuppressWarnings("ConstantConditions")
public class TryWithTest extends NsParsingTestCase {
  public void test_tryStructure() {
    PsiTry e = (PsiTry) firstElement(parseCode("try x catch { | Not_found => () }"));

    assertEquals("try", e.getFirstChild().getText());
    assertNotNull(e.getBody());
    assertEquals("x", e.getBody().getText());
    assertSize(1, e.getHandlers());
    assertEquals("Not_found => ()", e.getHandlers().iterator().next().getText());
  }
  /*
      public void testTryLet() {
          PsiFile psiFileModule = parseCode("let e = try let t = 6 with Not_found -> ()");
          PsiElement[] children = psiFileModule.getChildren();
          assertEquals(1, children.length);
      }

      public void testTry() {
          PsiFile file = parseCode("try f() with e -> let e = CErrors.push e");
          PsiTry try_ = (PsiTry) firstElement(file);

          assertEquals("e -> let e = CErrors.push e", try_.getHandlers().iterator().next().getText());
      }
  */
}
