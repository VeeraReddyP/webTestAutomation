package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.LoggerFactory;

public class CartDetails {

    private static final LoggerFactory logger = new LoggerFactory(CartDetails.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;

    By totalPrice = By.xpath("//span[@class='cart-subtotal__price']");
    By quantityText = By.name("updates[]");
    By productTitle =By.xpath("//a[@class='cart__product-title']");


    public CartDetails(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public String getProductTotalPrice() {
        elementActions.waitForElemenWithFluent(totalPrice, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(totalPrice);
    }

    public void enterProductQuantity(String noOfProducts){
        elementActions.waitForElemenWithFluent(quantityText, TestData.timeOut, TestData.pollTime);
        elementActions.clearField(quantityText);
        elementActions.enterData(quantityText,noOfProducts);
        elementActions.click(totalPrice);
        elementActions.waitFor();
    }

    public String getProductTitle() {
        elementActions.waitForElemenWithFluent(productTitle, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(productTitle);
    }

    public int getTotalProducts(){
        return driver.findElements(productTitle).size();
    }
}
