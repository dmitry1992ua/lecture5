import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class WebDriverFactory {

    private static boolean isMobileTesting = false;

    public static WebDriver initWebDriver(String browserName) {
        WebDriver driver;
        DesiredCapabilities capabilities = null;
        URL url = null;
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        switch (browserName) {
            case "chrome" :
                capabilities = new DesiredCapabilities().chrome();
                break;
            case "firefox" :
                capabilities = new DesiredCapabilities().firefox();
                break;
            case "ie" :
                capabilities = new DesiredCapabilities().internetExplorer();
                break;
            case "android" :
                capabilities = new DesiredCapabilities().android();
                isMobileTesting = true;
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
        driver = new RemoteWebDriver(url, capabilities);
        if (!isMobileTesting) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static boolean getSiteType() {
        boolean isMobile = isMobileTesting;
        isMobileTesting = false;
        return isMobile;
    }
}
