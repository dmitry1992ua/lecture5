import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by dmitriy.galich on 20.04.2017.
 */
public class OrderingTest extends BaseTest{

    private String productName;
    private String productQuantity;
    private String productPrice;
    private String productUrl;
    private static final String CONFIRM_MESSAGE = "ВАШ ЗАКАЗ ПОДТВЕРЖДЁН";

    @DataProvider
    public Object[][] userData() {
        return new Object[][]{
                { "Dmitry", "Test", "Street", "69105", "Zaporizhzhya" }
        };
    }

    @Title("Проверка версии сайта")
    @Test
    public void checkSiteVersion() {
        MainPage mainPage = new MainPage(driver);
        if (isMobileVersion) {
            Assert.assertTrue(mainPage.isMobileVersion());
        }
        else {
            Assert.assertFalse(mainPage.isMobileVersion());
        }
    }

    @Title("Проверка добавления продукта в корзину")
    @Test(dependsOnMethods = "checkSiteVersion")
    public void addProductToCart() {
        MainPage mainPage = new MainPage(driver);
        AllProductsPage allProductsPage = mainPage.moveToAllProductsPage();
        ProductPage productPage = allProductsPage.onenProductPage();
        productName = productPage.getProductName();
        productQuantity = productPage.getProductQuantity();
        productPrice = productPage.getProductPrice();
        productUrl = driver.getCurrentUrl();
        productPage.addProductToCart();
        CartPage cartPage = productPage.moveToCartPage();
        Assert.assertTrue(cartPage.getProductName().equals(productName)
                && cartPage.getProductQuantity().equals("1")
                && cartPage.getProductPrice().equals(productPrice));
    }

    @Title("Ввод пользовательских данных")
    @Test(dependsOnMethods = "addProductToCart", dataProvider = "userData")
    public void enterUserData(String firstName, String lastName, String street, String postcode, String city) {
        OrderPage orderPage = new CartPage(driver).moveToOrderPage();
        orderPage.enterUserName(firstName, lastName);
        orderPage.enterAddressData(street, postcode, city);
        orderPage.clickContinueButton();
        orderPage.clickOnCheckBox();
        orderPage.clickAccept();
    }

    @Title("Проверка оформления заказа")
    @Test(dependsOnMethods = "enterUserData")
    public void confirmationOrder() {
        ConfirmationOrderPage co = new ConfirmationOrderPage(driver);
        Assert.assertTrue(co.getConfirmMessage().equals(CONFIRM_MESSAGE)
                && co.getProductPrice().equals(productPrice)
                && co.getProductName().equals(productName)
                && co.getProductQuantity().equals("1"));
    }

    @Title("Проверка изменения доступного количества продука после оформления заказа")
    @Test(dependsOnMethods = "confirmationOrder")
    public void checkProductQuantity() {
        driver.get(productUrl);
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.compareQuantity(productQuantity, productPage.getProductQuantity()));
    }

}
