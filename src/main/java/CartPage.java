import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class CartPage extends BasePage {

    private By productNameLocator = By.cssSelector("a.label");
    private By productQuantityLocator = By.cssSelector(".js-subtotal");
    private By productPriceLocator = By.cssSelector(".product-price");
    private By orderingButtonLocator = By.cssSelector("a.btn.btn-primary");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем имя товара")
    public String getProductName() {
        return driver.findElement(productNameLocator).getText().toLowerCase();
    }

    @Step("Получаем количество товара")
    public String getProductQuantity() {
        return driver.findElement(productQuantityLocator).getText().split(" ")[0];
    }

    @Step("Получаем стоимость товара")
    public String getProductPrice() {
        return driver.findElement(productPriceLocator).getText().split(" ")[0].replace(",", ".");
    }

    @Step("Переходим к странице оформления")
    public OrderPage moveToOrderPage() {
        driver.findElement(orderingButtonLocator).click();
        return new OrderPage(driver);
    }
}
