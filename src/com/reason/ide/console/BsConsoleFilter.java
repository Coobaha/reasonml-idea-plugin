package com.reason.ide.console;

import static java.lang.Integer.parseInt;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.OpenFileHyperlinkInfo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.regex.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class BsConsoleFilter implements Filter {
    private static final Pattern LINE_PATTERN = Pattern.compile("^\\s*(.+.(?:rei|re|mli|ml)).([0-9]+):([0-9]+)[-0-9:\\s]*$");

    private final @NotNull Project m_project;

    BsConsoleFilter(@NotNull Project project) {
        m_project = project;
    }

    @Override
    public @Nullable Result applyFilter(@NotNull String line, int entireLength) {
        int startPoint = entireLength - line.length();
        Matcher matcher = LINE_PATTERN.matcher(line);
        if (matcher.find()) {
            String filePath = matcher.group(1);
            VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath);
            if (virtualFile != null) {
                return new Result(
                        startPoint + matcher.start(1),
                        entireLength,
                        new OpenFileHyperlinkInfo(m_project, virtualFile, parseInt(matcher.group(2)) - 1, parseInt(matcher.group(3)) - 1));
            }
        }
        return null;
    }
}
