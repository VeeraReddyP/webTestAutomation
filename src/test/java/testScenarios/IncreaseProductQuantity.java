package testScenarios;

import data.TestData;
import init.DriverUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartDetails;
import pages.LoginPage;
import pages.ProductPage;
import pages.SearchPage;
import utils.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class IncreaseProductQuantity extends BaseRun {

    private static final LoggerFactory logger = new LoggerFactory(IncreaseProductQuantity.class);
    LoginPage loginPage;
    SearchPage searchPage;
    ProductPage productPage;
    CartDetails cartDetails;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(DriverUtils.getDriver());
        searchPage = new SearchPage(loginPage.driver);
        productPage = new ProductPage(loginPage.driver);
        cartDetails = new CartDetails(loginPage.driver);
    }

    @Test
    public void validateProductPrices(){
        loginPage.clickPasswordLink();
        loginPage.enterAndSubmitPassword();
        Assert.assertEquals(loginPage.getProductTitle(), TestData.expectedProductTitle);
        searchPage.clickSearchIcon();
        searchPage.enterAndSubmitSearchData(TestData.searchData);
        searchPage.clickSelectedProduct(TestData.searchData);
        Assert.assertTrue(productPage.getProductName().equalsIgnoreCase(TestData.searchData));
        String actualCost = productPage.getProductCost();
        String[] costOfProduct = actualCost.split("s.");
        float singleProductValue = Float.valueOf(costOfProduct[1].replace(",",""));
        productPage.clickAddToCartBtn();
        productPage.clickViewCart();
        cartDetails.enterProductQuantity(String.valueOf(TestData.noOfProducts));
        Assert.assertEquals(TestData.searchData,cartDetails.getProductTitle());
        String totalCost=cartDetails.getProductTotalPrice();
        String[] totalCostOfProduct = totalCost.split("s.");
        float expectedProductValue = Float.valueOf(totalCostOfProduct[1].replace(",",""));
        Assert.assertEquals(singleProductValue*TestData.noOfProducts,expectedProductValue);
    }
}