import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class OrderPage extends BasePage {

    private By nameFieldLocator = By.cssSelector("input[name=\"firstname\"]");
    private By lastNameFieldLocator = By.cssSelector("input[name=\"lastname\"]");
    private By emailFieldLocator = By.cssSelector("input[name=\"email\"]");
    private By addressFieldLocator = By.cssSelector("input[name=\"address1\"]");
    private By postcodeFieldLocator = By.cssSelector("input[name=\"postcode\"]");
    private By cityFieldLocator = By.cssSelector("input[name=\"city\"]");
    private By firstPaymentOptionContainerLocarot = By.id("payment-option-1");
    private By checkBoxLocator = By.cssSelector(".js-terms");
    private By firstContinueButtonLocator = By.cssSelector(".continue");
    private By secondContinueButtonLocator = By.cssSelector("input[name=submitAddress]+button");
    private By thirdContinueButtonLocator = By.cssSelector("div.form-fields~button");
    private By acceptOrderButtonLocator = By.cssSelector(".btn-primary.center-block");


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вводим персональные данные пользователя")
    public void enterUserName(String firstName, String lastName) {
        driver.findElement(nameFieldLocator).click();
        driver.findElement(nameFieldLocator).sendKeys(firstName);
        driver.findElement(lastNameFieldLocator).sendKeys(lastName);
        driver.findElement(emailFieldLocator).sendKeys(generateUserEmail());
        driver.findElement(firstContinueButtonLocator).click();
    }

    public void clickContinueButton() {
        driver.findElement(thirdContinueButtonLocator).click();
    }

    @Step("Вводим адрес пользователя")
    public void enterAddressData(String street, String postcode, String city) {
        driver.findElement(addressFieldLocator).sendKeys(street);
        driver.findElement(postcodeFieldLocator).sendKeys(postcode);
        driver.findElement(cityFieldLocator).sendKeys(city);
        driver.findElement(secondContinueButtonLocator).click();
    }

    @Step("Выбираем первый вариант оплаты и активируем чекбокс")
    public void clickOnCheckBox() {
        WebElement checkBox = driver.findElement(checkBoxLocator);
        wait.until(ExpectedConditions.visibilityOf(checkBox));
        driver.findElement(firstPaymentOptionContainerLocarot).click();
        checkBox.click();
    }

    @Step("Подтверждаем оформление заказа")
    public void clickAccept() {
        driver.findElement(acceptOrderButtonLocator).click();
    }

    @Step("Генерируем адрес электронной почты")
    private String generateUserEmail() {
        return System.currentTimeMillis() + "@testmail.com";
    }
}
