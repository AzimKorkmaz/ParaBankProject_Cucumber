package stepDefinitions;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.GWD;

public class Hooks {
    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) GWD.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName().replaceAll("[^a-zA-Z0-9\\.\\-]", "_"));
        }
        GWD.tearDown();
    }
}