package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.LoggerFactory;

public class ProductPage {

    private static final LoggerFactory logger = new LoggerFactory(ProductPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;


    By productName = By.xpath("//h1[@class='product-single__title']");
    By productCost = By.xpath("//span[@class='price-item price-item--regular']");
    By addToCart = By.xpath("//button[@name='add']");
    By viewCart = By.xpath("//a[contains(text(),'View cart')]");
    By totalPrice = By.xpath("//span[@class='cart-subtotal__price']");
    By productSize = By.id("SingleOptionSelector-1");


    public ProductPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public String getProductName() {
        elementActions.waitForElemenWithFluent(productName, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(productName);
    }

    public String getProductCost() {
        elementActions.waitForElemenWithFluent(productCost, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(productCost);
    }

    public void clickAddToCartBtn(){
        elementActions.waitForElemenWithFluent(addToCart, TestData.timeOut, TestData.pollTime);
        elementActions.click(addToCart);
        elementActions.waitFor();
    }

    public void clickViewCart(){
        elementActions.waitForElemenWithFluent(viewCart, TestData.timeOut, TestData.pollTime);
        elementActions.click(viewCart);
    }


    public void selectProductSize(String sizeValue){
        elementActions.waitForElemenToBeClickWithFluent(productSize, TestData.timeOut, TestData.pollTime);
        elementActions.selectDropdownByText(productSize,sizeValue);
    }

}
