package com.example.myplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.jetbrains.annotations.NotNull;

public class DigitAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            return;
        }

        SelectionModel selectionModel = editor.getSelectionModel();
        if (!selectionModel.hasSelection()) {
            return;
        }
        String selectedText = selectionModel.getSelectedText();
        if (selectedText == null) {
            return;
        }
//        String newText = selectedText.toLowerCase();
        String newText = selectedText.replace("one", "1");
        newText = newText.replace("two", "2");
        newText = newText.replace("three", "3");
        newText = newText.replace("four", "4");
        newText = newText.replace("five", "5");
        newText = newText.replace("six", "6");
        newText = newText.replace("seven", "7");
        newText = newText.replace("eight", "8");
        newText = newText.replace("nine", "9");
        newText = newText.replace("zero", "0");
        if (newText.equals(selectedText)) {
            return;
        }
        String finalNewText = newText;

        int selectionStart = selectionModel.getSelectionStart();
        int selectionEnd = selectionModel.getSelectionEnd();


        WriteCommandAction.runWriteCommandAction(getEventProject(e), () -> editor.getDocument().replaceString(selectionStart, selectionEnd, finalNewText));
    }

    @Override
    public boolean isDumbAware() {
        return true;
    }
}
