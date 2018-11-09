package googleio.demo;

import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UExpression;
import org.jetbrains.uast.ULiteralExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemPrintDetector extends Detector implements SourceCodeScanner {

    public static final Issue ISSUE = Issue.create(
            "SystemPrintln",
            "",
            "避免使用System println,请使用封装好的log工具类。",
            Category.CORRECTNESS,
            6,
            Severity.WARNING,
            new Implementation(
                    SystemPrintDetector.class,
                    Scope.JAVA_FILE_SCOPE));


    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("println", "print");

    }

    @Override
    public void visitMethod(JavaContext context, UCallExpression node, PsiMethod method) {
        JavaEvaluator evaluator = context.getEvaluator();
        if (!evaluator.isMemberInClass(method, "java.io.PrintStream")) {
            return;
        }

        if (node.asSourceString().contains("System.out.") || node.asSourceString().contains("System.err.")) {
            context.report(ISSUE, node, context.getLocation(node),
                    "避免使用System println");
        }


    }


}