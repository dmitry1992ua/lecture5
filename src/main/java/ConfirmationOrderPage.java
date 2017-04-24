import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class ConfirmationOrderPage extends BasePage {

    private By confirmMessageLocator = By.cssSelector(".h1.card-title");
    private By productQuantityLocator = By.cssSelector(".col-xs-2");
    private By nameAndAttribLocator = By.cssSelector(".details>span");
    private By priceLocator = By.cssSelector(".text-xs-right");

    public ConfirmationOrderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем сообщение об успешном оформлении")
    public String getConfirmMessage() {
        return driver.findElement(confirmMessageLocator).getText().substring(1);
    }

    @Step("Получаем имя товара")
    public String getProductName() {
        return driver.findElement(nameAndAttribLocator).getText().split(" -")[0].toLowerCase();
    }

    @Step("Получаем стоимость товара")
    public String getProductPrice() {
        return driver.findElement(priceLocator).getText().split(" ")[0].replace(",", ".");
    }

    @Step("Получаем количество товара")
    public String getProductQuantity() {
        return driver.findElement(productQuantityLocator).getText();
    }
}
