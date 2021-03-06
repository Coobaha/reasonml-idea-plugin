package com.reason.ide.go;

import com.intellij.navigation.ChooseByNameContributorEx;
import com.intellij.navigation.GotoClassContributor;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Processor;
import com.intellij.util.indexing.FindSymbolParameters;
import com.intellij.util.indexing.IdFilter;
import com.reason.ide.files.FileBase;
import com.reason.ide.search.PsiFinder;
import com.reason.ide.search.index.ModuleIndex;
import com.reason.lang.core.ORFileType;
import com.reason.lang.core.ORUtil;
import com.reason.lang.core.psi.PsiInnerModule;
import com.reason.lang.core.psi.PsiModule;
import com.reason.lang.core.psi.PsiQualifiedElement;
import icons.ORIcons;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Implements the goto class
public class ORModuleContributor implements GotoClassContributor, ChooseByNameContributorEx {

  @Override
  public void processNames(
      @NotNull Processor<? super String> processor,
      @NotNull GlobalSearchScope scope,
      @Nullable IdFilter filter) {
    Project project = scope.getProject();
    if (project != null) {
      ModuleIndex.getInstance().processAllKeys(project, processor);
    }
  }

  @Override
  public void processElementsWithName(
      @NotNull String name,
      @NotNull Processor<? super NavigationItem> processor,
      @NotNull FindSymbolParameters parameters) {
    Project project = parameters.getProject();
    GlobalSearchScope scope = parameters.getSearchScope();

    for (PsiModule psiModule :
        PsiFinder.getInstance(project).findModulesbyName(name, ORFileType.both, null, scope)) {

      NavigationItem element = psiModule;
      if (psiModule instanceof PsiInnerModule) {
        Icon icon = psiModule.isInterface() ? ORIcons.INNER_MODULE_INTF : ORIcons.INNER_MODULE;

        element =
            new ModuleDelegatePresentation(
                psiModule,
                new ItemPresentation() {
                  @Override
                  public @Nullable String getPresentableText() {
                    return psiModule.getName();
                  }

                  @Override
                  public String getLocationString() {
                    return ORUtil.getQualifiedPath(psiModule);
                  }

                  @Override
                  public Icon getIcon(boolean unused) {
                    return icon;
                  }
                });
      }

      processor.process(element);
    }
  }

  @Nullable
  @Override
  public String getQualifiedName(NavigationItem item) {
    if (item instanceof FileBase) {
      return ((FileBase) item).getModuleName();
    } else if (item instanceof PsiQualifiedElement) {
      return ((PsiQualifiedElement) item).getQualifiedName();
    }
    return null;
  }

  @Override
  public @Nullable String getQualifiedNameSeparator() {
    return null;
  }
}
