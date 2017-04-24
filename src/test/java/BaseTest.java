import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class BaseTest {

    protected WebDriver driver;
    protected boolean isMobileVersion;
    private static final String STORE_URL = "http://prestashop-automation.qatestlab.com.ua";

    @BeforeClass()
    public void setUp() {
        driver = WebDriverFactory.initWebDriver("chrome");
        isMobileVersion = WebDriverFactory.getSiteType();
        driver.get(STORE_URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
