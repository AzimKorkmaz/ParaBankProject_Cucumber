package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.*;
import org.testng.annotations.*;
import utilities.GWD;

@CucumberOptions(features = {"src/test/java/featureFiles"},
        glue = {"stepDefinitions"},
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class ParallelAllRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    @Parameters("browserType")
    public void beforeClass(String browserType) {
        GWD.threadBrowserName.set(browserType);
    }
}