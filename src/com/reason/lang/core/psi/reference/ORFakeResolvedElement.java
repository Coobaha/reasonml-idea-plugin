package com.reason.lang.core.psi.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.FakePsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ORFakeResolvedElement extends FakePsiElement {

  private final @NotNull PsiElement m_sourceElement;

  public ORFakeResolvedElement(@NotNull PsiElement element) {
    m_sourceElement = element;
  }

  @Override
  public @NotNull PsiElement getOriginalElement() {
    return m_sourceElement;
  }

  @Nullable
  @Override
  public PsiElement getParent() {
    return m_sourceElement.getParent();
  }

  @Nullable
  @Override
  public String getText() {
    return m_sourceElement.getText();
  }

  @NotNull
  @Override
  public TextRange getTextRangeInParent() {
    return TextRange.EMPTY_RANGE;
  }
}
