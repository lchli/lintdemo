package googleio.demo;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;

import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UField;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WrongConstantNameDetector extends Detector implements SourceCodeScanner {

    public static final Issue ISSUE_CONSTANT = Issue.create(
            "WrongConstantName",
            "WrongConstantName",
            "常量应该采用全大写格式命名。",
            Category.CORRECTNESS,
            6,
            Severity.WARNING,
            new Implementation(
                    WrongConstantNameDetector.class,
                    Scope.JAVA_FILE_SCOPE));

    private static final String CONSTANT_PATTERN = "[0-9A-Z_]+";


    @org.jetbrains.annotations.Nullable
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        List<Class<? extends UElement>> list = new ArrayList<>();
        list.add(UField.class);
        return list;
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new UElementHandler() {

            @Override
            public void visitField(UField node) {
                if (node.isFinal() && node.isStatic()) {
                    String name = node.getName();
                    if (!Pattern.matches(CONSTANT_PATTERN, name)) {
                        context.report(ISSUE_CONSTANT, node, context.getLocation(node),
                                "常量命名不规范");
                    }

                }
            }
        };
    }


}