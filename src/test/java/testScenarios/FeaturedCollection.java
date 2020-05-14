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

public class FeaturedCollection extends BaseRun {

    private static final LoggerFactory logger = new LoggerFactory(FeaturedCollection.class);
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
    public void selectProductFromFeatured() {

        loginPage.clickPasswordLink();
        loginPage.enterAndSubmitPassword();
        Assert.assertEquals(loginPage.getProductTitle(), TestData.expectedProductTitle);
        searchPage.clickCatalog();
        searchPage.selectCatalogValue(TestData.categoryName);
        searchPage.clickCatalogInList();
        String actualCost = productPage.getProductCost();
        productPage.clickAddToCartBtn();
        productPage.clickViewCart();
        String totalCost=cartDetails.getProductTotalPrice();
        Assert.assertEquals(actualCost,totalCost);


    }
}
