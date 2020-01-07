package com.reason.hints;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.reason.Platform;
import com.reason.bs.BsProcess;

import static com.reason.Platform.getOsPrefix;

public class InsightManagerImpl implements InsightManager {

    @NotNull
    AtomicBoolean isDownloading = new AtomicBoolean(false);

    @NotNull
    private final Project m_project;

    private InsightManagerImpl(@NotNull Project project) {
        m_project = project;
    }

    @Override
    public void downloadRincewindIfNeeded(@NotNull VirtualFile sourceFile) {
        File rincewind = getRincewindFile(sourceFile);
        if (rincewind == null || !rincewind.exists()) {
            ProgressManager.getInstance().run(new RincewindDownloader(m_project, sourceFile));
        }
    }

    @Nullable
    @Override
    public File getRincewindFile(@NotNull VirtualFile sourceFile) {
        String filename = getRincewindFilename(sourceFile);
        if (filename == null) {
            return null;
        }

        File pluginLocation = Platform.getPluginLocation();
        return new File(pluginLocation == null ? System.getProperty("java.io.tmpdir") : pluginLocation.getPath(), filename);
    }

    @Override
    public void queryTypes(@NotNull VirtualFile sourceFile, @NotNull Path cmtPath, @NotNull ProcessTerminated runAfter) {
        File rincewindFile = getRincewindFile(sourceFile);
        if (rincewindFile != null) {
            RincewindProcess.getInstance(m_project).types(sourceFile, rincewindFile.getPath(), cmtPath.toString(), runAfter);
        }
    }

    @NotNull
    @Override
    public List<String> dumpInferredTypes(@NotNull VirtualFile cmtFile) {
        RincewindProcess rincewindProcess = RincewindProcess.getInstance(m_project);

        File rincewindFile = getRincewindFile(cmtFile);
        return rincewindFile == null ? Collections.emptyList() : rincewindProcess.dumpTypes( rincewindFile.getPath(), cmtFile);
    }

    @NotNull
    @Override
    public List<String> dumpMeta(@NotNull VirtualFile cmtFile) {
        RincewindProcess rincewindProcess = RincewindProcess.getInstance(m_project);

        File rincewindFile = getRincewindFile(cmtFile);
        return rincewindFile == null ? Collections.emptyList() : rincewindProcess.dumpMeta( rincewindFile.getPath(), cmtFile);
    }

    @NotNull
    @Override
    public String dumpTree(@NotNull VirtualFile cmtFile) {
        Path cmtPath = FileSystems.getDefault().getPath(cmtFile.getPath());
        File rincewindFile = getRincewindFile(cmtFile);
        RincewindProcess rincewindProcess = RincewindProcess.getInstance(m_project);

        return rincewindFile == null ? "<unknown/>" : rincewindProcess.dumpTree(cmtFile, rincewindFile.getPath(), cmtPath.toString());
    }

    @Nullable
    @Override
    public String getRincewindFilename(@NotNull VirtualFile sourceFile) {
        String ocamlVersion = ServiceManager.getService(m_project, BsProcess.class).getOCamlVersion(sourceFile);
        String rincewindVersion = getRincewindVersion(ocamlVersion);

        if (ocamlVersion != null && rincewindVersion != null) {
            return "rincewind_" + getOsPrefix() + ocamlVersion + "-" + rincewindVersion + ".exe";
        }

        return null;
    }

    @Nullable
    private String getRincewindVersion(@Nullable String ocamlVersion) {
        if (ocamlVersion == null) {
            return null;
        }

        if ("4.02".equals(ocamlVersion)) {
            return "0.4";
        }

        return "0.6-snapshot";
    }
}
