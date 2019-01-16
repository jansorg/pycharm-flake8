package webinar;

import com.intellij.codeInspection.InspectionSuppressor;
import com.intellij.codeInspection.SuppressQuickFix;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlakeInspectionSuppressor implements InspectionSuppressor {
    @Override
    public boolean isSuppressedFor(@NotNull PsiElement element, @NotNull String toolId) {
        if (!toolId.startsWith("Py")) {
            return false;
        }

        return isSuppressedByEndOfLine(element) || isSuppressedByLineComment(element);
    }

    private boolean isSuppressedByLineComment(PsiElement element) {
        PsiFile file = element.getContainingFile();

        boolean[] found = {false};
        file.acceptChildren(new PsiRecursiveElementVisitor() {
            @Override
            public void visitComment(PsiComment comment) {
                if (comment.getText().startsWith("# flake8: noqa")) {
                    found[0] = true;
                }
            }
        });

        return found[0];
    }

    private boolean isSuppressedByEndOfLine(PsiElement element) {
        for (PsiElement leaf = PsiTreeUtil.nextLeaf(element); leaf != null; leaf = PsiTreeUtil.nextLeaf(leaf)){
            if (leaf instanceof PsiWhiteSpace && leaf.textContains('\n')) {
                return false;
            }

            if (leaf instanceof PsiComment && leaf.getText().startsWith("# noqa")) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    @Override
    public SuppressQuickFix[] getSuppressActions(@Nullable PsiElement element, @NotNull String toolId) {
        return new SuppressQuickFix[0];
    }
}
