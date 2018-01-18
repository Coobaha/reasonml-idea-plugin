package com.reason.ide.insight;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.ProcessingContext;
import com.reason.ide.files.RmlFile;
import com.reason.lang.core.psi.PsiModuleName;
import com.reason.lang.reason.RmlTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.patterns.StandardPatterns.instanceOf;

public class CompletionContributor extends com.intellij.codeInsight.completion.CompletionContributor {

    public CompletionContributor() {
        RmlTypes types = RmlTypes.INSTANCE;
        //MerlinService merlinService = ApplicationManager.getApplication().getComponent(MerlinService.class);
        //boolean useMerlin = merlinService != null && merlinService.hasVersion();
        //if (useMerlin) {
        //    extend(CompletionType.BASIC, psiElement(), new MerlinCompletionProvider());
        //} else {

        extend(CompletionType.BASIC, psiElement().inFile(instanceOf(RmlFile.class)), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiElement position = parameters.getPosition();
                PsiFile file = position.getContainingFile();
                PsiElement parent = position.getParent();
                PsiElement grandPa = parent == null ? null : parent.getParent();
                PsiElement originalPosition = parameters.getOriginalPosition();
                PsiElement originalPrevSibling = originalPosition == null ? null : originalPosition.getPrevSibling();

                if (grandPa instanceof RmlFile) {
                    // We are completing a top level expression

                    PsiElement parentPrevSibling = parent.getPrevSibling();
                    if (originalPosition == null) {
                        // Xxx.<IntellijIdeazzz>
                        // Find the modules before the DOT
                        PsiElement dotPrevSibling = parentPrevSibling.getPrevSibling();
                        if (dotPrevSibling instanceof PsiModuleName) {
                            ModuleDotCompletionProvider.complete(file.getProject(), (PsiModuleName) dotPrevSibling, result);
                            return;
                        }
                    } else {
                        // Xxx.yy<IntellijIdeazzz>
                        // Find the modules before the DOT
                        PsiElement dotPrevSibling = parentPrevSibling.getPrevSibling();
                        if (dotPrevSibling instanceof PsiModuleName) {
                            ModuleDotCompletionProvider.complete(file.getProject(), (PsiModuleName) dotPrevSibling, result);
                            return;
                        }
                    }


                    if (originalPrevSibling != null && originalPrevSibling.getNode().getElementType() == types.DOT) {
                        // Find the modules before the DOT
                        PsiElement dotPrevSibling = originalPrevSibling.getPrevSibling();
                        if (dotPrevSibling instanceof PsiModuleName) {
                            ModuleDotCompletionProvider.complete(file.getProject(), (PsiModuleName) dotPrevSibling, result);
                        }
                    } else if (originalPosition instanceof LeafPsiElement) {
                        if (originalPosition.getNode().getElementType() == types.VALUE_NAME) {
                            // Starts a ModuleName completion
                            ModuleNameCompletion.complete(file.getProject(), (RmlFile) grandPa, originalPosition.getText(), result);
                        }
                    }
                }

            }
        });
    }

}
