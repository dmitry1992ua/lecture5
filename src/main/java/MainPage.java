import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class MainPage extends BasePage {

    private By allProductsButtonLocator = By.className("all-product-link");
    private By mobileLogo = By.id("_mobile_logo");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим к странице \"Все товары\"")
    public AllProductsPage moveToAllProductsPage() {
        driver.findElement(allProductsButtonLocator).click();
        return new AllProductsPage(driver);
    }

    @Step("Проверяем версию сайта")
    public boolean isMobileVersion() {
        WebElement element = driver.findElement(mobileLogo);
        if (element.isDisplayed()) return true;
        return false;
    }
}
