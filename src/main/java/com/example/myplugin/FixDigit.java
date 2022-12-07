package com.example.myplugin;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.command.WriteCommandAction.runWriteCommandAction;

public class FixDigit extends BaseIntentionAction {
    private final TextRange textRange;
    private final String text;

    public FixDigit(TextRange textRange, String text) {
        this.textRange = textRange;
        this.text = text;
    }


    @NotNull
    @Override
    public String getText() {
        return "Replace with number";
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "Fix number";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {

        String newText = text.replace("one", "1");
        newText = newText.replace("two", "2");
        newText = newText.replace("three", "3");
        newText = newText.replace("four", "4");
        newText = newText.replace("five", "5");
        newText = newText.replace("six", "6");
        newText = newText.replace("seven", "7");
        newText = newText.replace("eight", "8");
        newText = newText.replace("nine", "9");
        newText = newText.replace("zero", "0");
        String finalNewText = newText;
        runWriteCommandAction(project, () -> editor.getDocument().replaceString(textRange.getStartOffset(), textRange.getEndOffset(), finalNewText));
    }
}
