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

public class SearchAndAdd  extends BaseRun{

    private static final LoggerFactory logger = new LoggerFactory(SearchAndAdd.class);
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

    @Test()
    public void searchAndAddProductToCart() {
        loginPage.clickPasswordLink();
        loginPage.enterAndSubmitPassword();
        Assert.assertEquals(loginPage.getProductTitle(), TestData.expectedProductTitle);
        searchPage.clickSearchIcon();
        searchPage.enterAndSubmitSearchData(TestData.searchData);
        searchPage.clickSelectedProduct(TestData.searchData);
        Assert.assertTrue(productPage.getProductName().equalsIgnoreCase(TestData.searchData));
        String actualCost = productPage.getProductCost();
        productPage.clickAddToCartBtn();
        productPage.clickViewCart();
        String totalCost=cartDetails.getProductTotalPrice();
        Assert.assertEquals(actualCost,totalCost);
    }
}
