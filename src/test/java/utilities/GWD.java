package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class GWD {
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public static ThreadLocal<String> threadBrowserName = new ThreadLocal<>();

    public static WebDriver getDriver() {
        Locale.setDefault(new Locale("en"));
        System.setProperty("user.language", "en");

        if (threadDriver.get() == null) {
            String browser = threadBrowserName.get();

            if (browser == null) {
                browser = "chrome";
            }
            switch (browser.toLowerCase()) {
                case "edge":
                    threadDriver.set(new EdgeDriver());
                    break;
                case "firefox":
                    threadDriver.set(new FirefoxDriver());
                    break;
                case "safari":
                    threadDriver.set(new SafariDriver());
                    break;
                default:
                    threadDriver.set(new ChromeDriver());
                    break;
            }

            threadDriver.get().manage().window().maximize();
            threadDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getIntProperty("pageLoadTimeout")));
        }
        return threadDriver.get();
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigReader.getIntProperty("explicitWait")));
    }

    public static void tearDown() {
        if (threadDriver.get() != null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (threadDriver.get() != null) {
                threadDriver.get().quit();
                threadDriver.remove();
            }
        }
    }
}