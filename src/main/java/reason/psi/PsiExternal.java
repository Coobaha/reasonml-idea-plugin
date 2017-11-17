package reason.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import reason.icons.Icons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PsiExternal extends ASTWrapperPsiElement {

    public PsiExternal(ASTNode node) {
        super(node);
    }

    @NotNull
    public PsiValueName getValueName() {
        return findNotNullChildByClass(PsiValueName.class);
    }

    @Override
    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
//                ReasonMLExternalAlias externalAlias = external.getExternalAlias();
                String externalName = getValueName().getText();
//                if (externalAlias.getTextLength() == 2) {
//                    return externalName;
//                }

//                String externalAliasText = externalAlias.getText();
//                String externalAliasName = externalAliasText.substring(1, externalAliasText.length() - 1);
//                return externalName + (externalAliasName.equals(externalName) ? "" : " ⇐ " + externalAliasName);
                return externalName;
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return Icons.EXTERNAL;
            }
        };
    }

}
