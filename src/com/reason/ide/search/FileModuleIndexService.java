package com.reason.ide.search;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.indexing.FileBasedIndex;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class FileModuleIndexService {
    private final FileModuleIndex m_index;
    private final NamespaceIndex m_nsIndex;

    public FileModuleIndexService(@NotNull FileModuleIndex index, @NotNull NamespaceIndex nsIndex) {
        m_index = index;
        m_nsIndex = nsIndex;
    }

    public static FileModuleIndexService getService() {
        return ServiceManager.getService(FileModuleIndexService.class);
    }

    public Collection<String> getNamespaces(@NotNull Project project) {
        return FileBasedIndex.getInstance().getAllKeys(m_nsIndex.getName(), project);
    }

    public Collection<VirtualFile> getFilesWithName(@Nullable String moduleName, @NotNull GlobalSearchScope scope) {
        if (moduleName == null) {
            return Collections.emptyList();
        }

        return FileBasedIndex.getInstance().getContainingFiles(m_index.getName(), moduleName, scope);
    }

    public Collection<VirtualFile> getInterfaceFilesWithName(@Nullable String moduleName, @NotNull GlobalSearchScope scope) {
        if (moduleName == null) {
            return Collections.emptyList();
        }

        Set<VirtualFile> interfaceFiles = new THashSet<>();

        FileBasedIndex.getInstance().processValues(m_index.getName(), moduleName, null, (file, value) -> {
            if (value.isInterface()) {
                interfaceFiles.add(file);
            }
            return true;
        }, scope);

        return interfaceFiles;
    }

    public Collection<VirtualFile> getImplementationFilesWithName(@Nullable String moduleName, @NotNull GlobalSearchScope scope) {
        if (moduleName == null) {
            return Collections.emptyList();
        }

        Set<VirtualFile> files = new THashSet<>();

        FileBasedIndex.ValueProcessor<FileModuleData> valueProcessor = (file, value) -> {
            if (!value.isInterface()) {
                files.add(file);
            }
            return true;
        };
        FileBasedIndex.getInstance().processValues(m_index.getName(), moduleName, null, valueProcessor, scope);

        return files;
    }

    public Collection<VirtualFile> getFilesWithoutNamespace(@NotNull Project project) {
        Collection<VirtualFile> result = new ArrayList<>();

        FileBasedIndex fileIndex = FileBasedIndex.getInstance();
        GlobalSearchScope scope = GlobalSearchScope.allScope(project);

        for (String key : fileIndex.getAllKeys(m_index.getName(), project)) {
            final int valuesSize = fileIndex.getValues(m_index.getName(), key, scope).size();
            if (valuesSize > 2) {
                System.out.println("ERROR, size of " + key + " is " + valuesSize);
            } else {
                fileIndex.processValues(m_index.getName(), key, null, (file, value) -> {
                    if (valuesSize == 1 || value.isInterface()) {
                        if (value.getNamespace().isEmpty() && !value.isComponent()) {
                            result.add(file);
                        }
                    }
                    return true;
                }, scope);
            }
        }

        return result;
    }
}
