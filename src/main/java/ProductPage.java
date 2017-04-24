import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class ProductPage extends BasePage {

    private By productNameLocator = By.className("h1");
    private By productPriceLocator = By.cssSelector(".current-price>span");
    private By productQuantitiesLocator = By.cssSelector(".product-quantities>span");
    private By productDetailsButtonLocator = By.cssSelector("a[href=\"#product-details\"]");
    private By labelLocator = By.cssSelector(".label");
    private By addToCartButtonLocator = By.className("add-to-cart");

    public ProductPage(WebDriver driver) {
        super(driver);
        waitForPageLoad();
    }

    @Step("Получаем имя товара")
    public String getProductName() {
        return driver.findElement(productNameLocator).getText().toLowerCase();
    }

    @Step("Получаем количество товара")
    public String getProductQuantity() {
        driver.findElement(productDetailsButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(labelLocator)));
        return driver.findElement(productQuantitiesLocator).getText().split(" ")[0];
    }

    @Step("Получаем стоимость товара")
    public String getProductPrice() {
        return driver.findElement(productPriceLocator).getAttribute("content");
    }

    @Step("Переходим в корзину")
    public CartPage moveToCartPage() {
        driver.get(driver.findElement(By.xpath("//a[contains(text(), 'перейти')]")).getAttribute("href"));
        return new CartPage(driver);
    }

    @Step("Добавляем товар в корзину")
    public void addProductToCart() {
        driver.findElement(addToCartButtonLocator).click();
    }

    @Step("Сравниваем количество товара до и после оформления заказа")
    public boolean compareQuantity(String oldQuantity, String newQuantity) {
        return Integer.parseInt(oldQuantity) - Integer.parseInt(newQuantity) == 1;
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("add-to-cart"))));
    }
}
