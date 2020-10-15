package com.reason.lang.core.stub.type;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import com.reason.ide.search.index.IndexKeys;
import com.reason.lang.core.psi.PsiVal;
import com.reason.lang.core.psi.impl.PsiValImpl;
import com.reason.lang.core.stub.PsiValStub;
import com.reason.lang.core.type.ORTypesUtil;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PsiValStubElementType extends ORStubElementType<PsiValStub, PsiVal> {

  public PsiValStubElementType(@Nullable Language language) {
    super("C_VAL_DECLARATION", language);
  }

  @NotNull
  public PsiValImpl createPsi(@NotNull ASTNode node) {
    return new PsiValImpl(ORTypesUtil.getInstance(getLanguage()), node);
  }

  @NotNull
  public PsiValImpl createPsi(@NotNull PsiValStub stub) {
    return new PsiValImpl(ORTypesUtil.getInstance(getLanguage()), stub, this);
  }

  @NotNull
  public PsiValStub createStub(@NotNull PsiVal psi, StubElement parentStub) {
    return new PsiValStub(parentStub, this, psi.getName(), psi.getPath(), psi.isFunction());
  }

  public void serialize(@NotNull PsiValStub stub, @NotNull StubOutputStream dataStream)
      throws IOException {
    dataStream.writeName(stub.getName());
    dataStream.writeUTFFast(stub.getPath());
    dataStream.writeBoolean(stub.isFunction());
  }

  @NotNull
  public PsiValStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub)
      throws IOException {
    StringRef name = dataStream.readName();
    String path = dataStream.readUTFFast();
    boolean isFunction = dataStream.readBoolean();
    return new PsiValStub(parentStub, this, name, path, isFunction);
  }

  public void indexStub(@NotNull PsiValStub stub, @NotNull IndexSink sink) {
    String name = stub.getName();
    if (name != null) {
      sink.occurrence(IndexKeys.VALS, name);
    }

    String fqn = stub.getQualifiedName();
    sink.occurrence(IndexKeys.VALS_FQN, fqn.hashCode());
  }

  @NotNull
  public String getExternalId() {
    return getLanguage().getID() + "." + super.toString();
  }
}
