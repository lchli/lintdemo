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

import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UExpression;
import org.jetbrains.uast.ULiteralExpression;

import java.util.ArrayList;
import java.util.List;

public class MagicNumberDetector extends Detector implements SourceCodeScanner {

    public static final Issue ISSUE = Issue.create(
            "MagicNumber",
            "MagicNumber",
            "为了可维护性，请不要使用魔法数字。应该定义成常量或变量来描述其含义。",
            Category.CORRECTNESS,
            6,
            Severity.WARNING,
            new Implementation(
                    MagicNumberDetector.class,
                    Scope.JAVA_FILE_SCOPE));


    @org.jetbrains.annotations.Nullable
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        List<Class<? extends UElement>> list = new ArrayList<>();
        list.add(UCallExpression.class);
        return list;
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new UElementHandler() {

            @Override
            public void visitCallExpression(UCallExpression call) {
                List<UExpression> args = call.getValueArguments();
                for (UExpression arg : args) {
                    if (arg instanceof ULiteralExpression) {
                        Object val = ((ULiteralExpression) arg).getValue();
                        if (val instanceof Number) {

                            context.report(ISSUE, arg, context.getLocation(arg),
                                    "魔法数字");
                        }
                    }
                }
            }


        };
    }


}