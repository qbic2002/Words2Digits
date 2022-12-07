package com.example.myplugin;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

public class DigitAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof PsiIdentifier)){
            return;
        }

        TextRange textRange = TextRange.from(element.getTextOffset(), element.getTextLength());
        if (element.getText().contains("one") || element.getText().contains("two") || element.getText().contains("three") || element.getText().contains("four") || element.getText().contains("five") || element.getText().contains("six") || element.getText().contains("seven") || element.getText().contains("eight") || element.getText().contains("nine") || element.getText().contains("zero")) {
            TextAttributes textAttributes = DefaultLanguageHighlighterColors.NUMBER.getDefaultAttributes();
            textAttributes.setErrorStripeColor(JBColor.blue);
            textAttributes.setEffectType(EffectType.WAVE_UNDERSCORE);
            textAttributes.setEffectColor(JBColor.blue);

            TextAttributesKey tempUnknownNumber = TextAttributesKey.createTempTextAttributesKey("UNKNOWN NUMBER", textAttributes);
            TextAttributesKey unknownNumber = TextAttributesKey.createTextAttributesKey("UNKNOWN NUMBER", tempUnknownNumber);
            holder.newAnnotation(HighlightSeverity.ERROR, "Digit-like string")
                    .range(textRange).textAttributes(unknownNumber)
                    .withFix(new FixDigit(textRange, element.getText()))
                    .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL).create();
        }
    }
}
