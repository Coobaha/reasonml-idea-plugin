package com.reason.lang.core.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiQualifiedNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.reason.lang.core.ORUtil;
import com.reason.lang.core.psi.PsiLowerSymbol;
import com.reason.lang.core.psi.PsiParameter;
import com.reason.lang.core.psi.PsiParameters;
import com.reason.lang.core.psi.PsiSignature;
import com.reason.lang.core.psi.reference.PsiLowerSymbolReference;
import com.reason.lang.core.signature.ORSignature;
import com.reason.lang.core.type.ORTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PsiParameterImpl extends PsiToken<ORTypes> implements PsiParameter {

    public PsiParameterImpl(@NotNull ORTypes types, @NotNull ASTNode node) {
        super(types, node);
    }

    @Nullable
    public PsiElement getNameIdentifier() {
        PsiElement firstChild = getFirstChild();
        if (firstChild != null && firstChild.getNode().getElementType() == m_types.TILDE) {
            return firstChild.getNextSibling();
        }
        return firstChild;
    }

    @Override
    public String getName() {
        PsiElement identifier = getNameIdentifier();
        return identifier == null ? "" : identifier.getText();
    }

    @NotNull
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        return this;
    }

    @Override
    @Nullable
    public PsiSignature getPsiSignature() {
        return PsiTreeUtil.findChildOfType(this, PsiSignature.class);
    }

    @NotNull
    @Override
    public ORSignature getORSignature() {
        PsiSignature signature = getPsiSignature();
        return signature == null ? ORSignature.EMPTY : signature.asHMSignature();
    }

    @Override
    public boolean hasDefaultValue() {
        return ORUtil.nextSiblingWithTokenType(getFirstChild(), m_types.EQ) != null;
    }

    @Nullable
    @Override
    public PsiReference getReference() {
        PsiElement nameIdentifier = getNameIdentifier();
        return nameIdentifier instanceof PsiLowerSymbol ? new PsiLowerSymbolReference((PsiLowerSymbol) nameIdentifier, m_types) : null;
    }

    PsiReference[] EMPTY_REFS = new PsiReference[0];

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        PsiElement nameIdentifier = getNameIdentifier();
        return nameIdentifier == null ? EMPTY_REFS : ReferenceProvidersRegistry.getReferencesFromProviders(nameIdentifier);
    }

    @Nullable
    @Override
    public String toString() {
        return "Parameter " + getName();
    }

    @NotNull
    @Override
    public String getQualifiedName() {
        PsiElement parent = getParent();
        if (parent instanceof PsiParameters) {
            // it's a function definition
            PsiQualifiedNamedElement qualifiedParent = PsiTreeUtil.getParentOfType(this, PsiQualifiedNamedElement.class);
            if (qualifiedParent != null) {
                return qualifiedParent.getQualifiedName() + "[" + getName() + "]";
            }
        }
        return getName();
    }
}
