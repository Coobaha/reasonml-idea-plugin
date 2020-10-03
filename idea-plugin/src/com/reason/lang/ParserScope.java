package com.reason.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.reason.lang.core.type.ORCompositeType;
import com.reason.lang.core.type.ORTokenElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParserScope {

  @NotNull private final PsiBuilder m_builder;
  private final int m_offset;

  private @Nullable ParserScopeEnum m_resolution;
  private ORCompositeType m_compositeElementType;
  private ORTokenElementType m_scopeTokenElementType;
  private boolean m_isComplete = false;
  private boolean m_isDummy = false; // Always drop
  private boolean m_isStart = false;
  @Nullable public PsiBuilder.Marker m_mark;

  private ParserScope(
      @NotNull PsiBuilder builder,
      ORCompositeType compositeElementType,
      ORTokenElementType scopeTokenElementType) {
    m_builder = builder;
    m_mark = builder.mark();
    m_offset = builder.getCurrentOffset();
    m_compositeElementType = compositeElementType;
    m_scopeTokenElementType = scopeTokenElementType;
  }

  @NotNull
  public static ParserScope mark(
      @NotNull PsiBuilder builder, @NotNull ORCompositeType compositeElementType) {
    return new ParserScope(builder, compositeElementType, null);
  }

  @NotNull
  public static ParserScope markScope(
      @NotNull PsiBuilder builder,
      @NotNull ORCompositeType compositeElementType,
      @NotNull ORTokenElementType scopeTokenElementType) {
    return new ParserScope(builder, compositeElementType, scopeTokenElementType);
  }

  @NotNull
  static ParserScope markRoot(@NotNull PsiBuilder builder) {
    return new ParserScope(builder, null, null).resolution(ParserScopeEnum.file);
  }

  public boolean isEmpty() {
    return m_builder.getCurrentOffset() - m_offset == 0;
  }

  public void end() {
    if (m_isDummy) {
      drop();
    } else if (m_isComplete) {
      done();
    } else {
      drop();
    }
  }

  private void done() {
    if (m_mark != null) {
      if (m_compositeElementType instanceof IElementType) {
        m_mark.done((IElementType) m_compositeElementType);
      } else {
        m_mark.drop();
      }
      m_mark = null;
    }
  }

  void drop() {
    if (m_mark != null) {
      m_mark.drop();
      m_mark = null;
    }
  }

  public void complete() {
    m_isComplete = true;
  }

  public void dummy() {
    m_isDummy = true;
  }

  public boolean isResolution(ParserScopeEnum resolution) {
    return m_resolution == resolution;
  }

  @NotNull
  public ParserScope resolution(@Nullable ParserScopeEnum resolution) {
    m_resolution = resolution;
    return this;
  }

  boolean isCompositeEqualTo(ORCompositeType compositeType) {
    return m_compositeElementType == compositeType;
  }

  boolean isCompositeIn(ORCompositeType @NotNull ... compositeType) {
    for (ORCompositeType composite : compositeType) {
      if (m_compositeElementType == composite) return true;
    }
    return false;
  }

  boolean isScopeTokenEqualTo(ORTokenElementType tokenElementType) {
    return m_scopeTokenElementType == tokenElementType;
  }

  void setScopeTokenType(@NotNull ORTokenElementType tokenElementType) {
    m_scopeTokenElementType = tokenElementType;
  }

  public void updateCompositeElementType(ORCompositeType compositeType) {
    m_compositeElementType = compositeType;
  }

  public boolean isStart() {
    return m_isStart;
  }

  public void setIsStart(boolean isStart) {
    m_isStart = isStart;
  }

  public @Nullable ParserScopeEnum getResolution() {
    return m_resolution;
  }

  public boolean hasScope() {
    return m_scopeTokenElementType != null;
  }

  ORTokenElementType getScopeTokenElementType() {
    return m_scopeTokenElementType;
  }

  public void rollbackTo() {
    if (m_mark != null) {
      m_mark.rollbackTo();
    }
  }

  public boolean isCompositeType(ORCompositeType elementType) {
    return m_compositeElementType == elementType;
  }

  public ORCompositeType getCompositeType() {
    return m_compositeElementType;
  }

  public ORTokenElementType getScopeType() {
    return m_scopeTokenElementType;
  }
}
