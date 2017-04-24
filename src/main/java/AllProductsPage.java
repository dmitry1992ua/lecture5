import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class AllProductsPage extends BasePage {

    private By productPreviewLocator = By.className("product-thumbnail");

    public AllProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу товара")
    public ProductPage onenProductPage() {
        driver.findElement(productPreviewLocator).click();
        return new ProductPage(driver);
    }
}
