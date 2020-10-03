package com.reason.ide.repl;

import static com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileDescriptor;
import static com.intellij.openapi.roots.ui.configuration.JdkComboBox.getSdkFilter;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ui.configuration.JdkComboBox;
import com.intellij.openapi.roots.ui.configuration.projectRoot.ProjectSdksModel;
import com.intellij.openapi.util.Condition;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReplRunSettingsEditor extends SettingsEditor<ReplRunConfiguration> {
  private final Project m_project;

  private JPanel c_rootPanel;
  private @Nullable JdkComboBox c_sdk;
  private com.intellij.openapi.ui.TextFieldWithBrowseButton c_cygwinPath;
  private JCheckBox c_cygwin;

  ReplRunSettingsEditor(Project project) {
    m_project = project;
  }

  @Override
  protected void resetEditorFrom(@NotNull ReplRunConfiguration runConfiguration) {
    if (c_sdk != null) {
      c_sdk.setSelectedJdk(runConfiguration.getSdk());
    }
    c_cygwin.setSelected(runConfiguration.getCygwinSelected());
    c_cygwinPath.setText(runConfiguration.getCygwinPath());
  }

  @Override
  protected void applyEditorTo(@NotNull ReplRunConfiguration runConfiguration) {
    if (c_sdk != null) {
      runConfiguration.setSdk(c_sdk.getSelectedJdk());
    }
    runConfiguration.setCygwinSelected(c_cygwin.isSelected());
    runConfiguration.setCygwinPath(c_cygwinPath.getText());
  }

  @NotNull
  @Override
  protected JComponent createEditor() {
    c_cygwinPath.addBrowseFolderListener(
        "Choose cygwin bash.exe", null, m_project, createSingleFileDescriptor("exe"));
    return c_rootPanel;
  }

  private void createUIComponents() {
    ProjectSdksModel model = new ProjectSdksModel();
    Condition<SdkTypeId> filter = sdkTypeId -> "OCaml SDK".equals(sdkTypeId.getName());

    model.reset(m_project);
    c_sdk = new JdkComboBox(m_project, model, filter, getSdkFilter(filter), filter, null);
  }
}
