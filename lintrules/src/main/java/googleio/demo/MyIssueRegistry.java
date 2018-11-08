package googleio.demo;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

public class MyIssueRegistry extends IssueRegistry {
    public MyIssueRegistry() {
    }

    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                MyDetector.ISSUE,
                NewThreadDetector.ISSUE,
                MagicNumberDetector.ISSUE,
                WrongConstantNameDetector.ISSUE_CONSTANT
        );
    }

    @Override
    public int getApi() {
        return com.android.tools.lint.detector.api.ApiKt.CURRENT_API;
    }

}
