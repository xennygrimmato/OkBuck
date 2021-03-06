package com.github.okbuilds.okbuck.composer

import com.github.okbuilds.core.model.AndroidAppTarget
import com.github.okbuilds.core.model.AndroidLibTarget
import com.github.okbuilds.core.model.JavaTarget
import com.github.okbuilds.okbuck.rule.ProjectConfigRule

import static com.github.okbuilds.okbuck.composer.AndroidBuckRuleComposer.bin

class ProjectConfigComposer extends JavaBuckRuleComposer {

    private ProjectConfigComposer() {
        // no instance
    }

    static ProjectConfigRule composeAndroidApp(AndroidAppTarget androidAppTarget) {
        return compose(bin(androidAppTarget) as String, null, androidAppTarget as JavaTarget)
    }

    static ProjectConfigRule composeAndroidLibrary(AndroidLibTarget androidLibTarget) {
        String testTargetName = null;
        if (androidLibTarget.robolectric) {
            testTargetName = test(androidLibTarget) as String
        }
        return compose(src(androidLibTarget) as String, testTargetName, androidLibTarget as JavaTarget)
    }

    static ProjectConfigRule composeJavaLibrary(JavaTarget javaTarget) {
        return compose(src(javaTarget) as String, test(javaTarget) as String, javaTarget)
    }

    private static ProjectConfigRule compose(String targetName, String testTargetName, JavaTarget target) {
        Set<String> mainSources = new LinkedHashSet<>()
        Set<String> testSources = new LinkedHashSet<>()
        mainSources.addAll(target.main.sources)

        if (testTargetName) {
            testSources.addAll(target.test.sources)
        }

        return new ProjectConfigRule(targetName, mainSources, testTargetName, testSources)
    }
}
