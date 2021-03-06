package com.reason.lang.core.stub.type;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.io.StringRef;
import com.reason.ide.search.index.IndexKeys;
import com.reason.lang.core.psi.PsiRecordField;
import com.reason.lang.core.psi.impl.PsiRecordFieldImpl;
import com.reason.lang.core.stub.PsiRecordFieldStub;
import com.reason.lang.core.type.ORTypesUtil;
import java.io.*;
import org.jetbrains.annotations.NotNull;

public class PsiRecordFieldStubElementType
    extends ORStubElementType<PsiRecordFieldStub, PsiRecordField> {

  public PsiRecordFieldStubElementType(@NotNull Language language) {
    super("C_RECORD_FIELD", language);
  }

  @NotNull
  public PsiRecordFieldImpl createPsi(@NotNull final PsiRecordFieldStub stub) {
    return new PsiRecordFieldImpl(ORTypesUtil.getInstance(getLanguage()), stub, this);
  }

  @NotNull
  public PsiRecordFieldImpl createPsi(@NotNull final ASTNode node) {
    return new PsiRecordFieldImpl(ORTypesUtil.getInstance(getLanguage()), node);
  }

  @NotNull
  public PsiRecordFieldStub createStub(
      @NotNull final PsiRecordField psi, final StubElement parentStub) {
    return new PsiRecordFieldStub(parentStub, this, psi.getName(), psi.getPath());
  }

  public void serialize(
      @NotNull final PsiRecordFieldStub stub, @NotNull final StubOutputStream dataStream)
      throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeUTFFast(stub.getPath());
  }

  @NotNull
  public PsiRecordFieldStub deserialize(
      @NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
    StringRef name = dataStream.readName();
    String path = dataStream.readUTFFast();
    return new PsiRecordFieldStub(parentStub, this, name, path);
  }

  public void indexStub(@NotNull final PsiRecordFieldStub stub, @NotNull final IndexSink sink) {
    String name = stub.getName();
    if (name != null) {
      sink.occurrence(IndexKeys.RECORD_FIELDS, name);
    }
  }

  @NotNull
  public String getExternalId() {
    return getLanguage().getID() + "." + super.toString();
  }
}
