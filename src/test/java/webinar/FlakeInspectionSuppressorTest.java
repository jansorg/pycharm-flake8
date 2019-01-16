package webinar;

import com.google.common.collect.Sets;
import com.intellij.codeInspection.InspectionEP;
import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.codeInspection.LocalInspectionEP;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixture4TestCase;
import org.junit.Test;

import java.util.Set;

public class FlakeInspectionSuppressorTest extends LightPlatformCodeInsightFixture4TestCase {
    private Set<String> excluded = Sets.newHashSet("PyInterpreterInspection", "PyMandatoryEncodingInspection", "PyMissingOrEmptyDocstringInspection");

    @Test
    public void testSuppressErrors() {
        InspectionProfileEntry[] inspections = LocalInspectionEP.LOCAL_INSPECTION.extensions()
                .map(InspectionEP::instantiateTool)
                .filter(e -> e.getShortName().startsWith("Py"))
                .filter(e -> !excluded.contains(e.getShortName()))
                .toArray(InspectionProfileEntry[]::new);

        myFixture.enableInspections(inspections);

        //language=Python
        assertNoErrors("def foo():\n    x = 1 # noqa");
        //language=Python
        assertNoErrors("# flake8: noqa\ndef foo():\n    x = 1");
    }

    private void assertNoErrors(String code) {
        myFixture.configureByText("file.py", code);
        myFixture.checkHighlighting();
    }
}