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

public class MultipleProductSizes extends BaseRun {
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
    public void selectVariousSizesOfProducts() {
        loginPage.clickPasswordLink();
        loginPage.enterAndSubmitPassword();
        Assert.assertEquals(loginPage.getProductTitle(), TestData.expectedProductTitle);
        searchPage.clickSearchIcon();
        searchPage.enterAndSubmitSearchData(TestData.searchData);
        searchPage.clickSelectedProduct(TestData.searchData);
        Assert.assertTrue(productPage.getProductName().equalsIgnoreCase(TestData.searchData));
        if(TestData.productSizes.contains(",")){
            String[] products = TestData.productSizes.split(",");
            for(String s: products){
                productPage.selectProductSize(s);
                productPage.clickAddToCartBtn();
            }
        }else{
            productPage.clickAddToCartBtn();
        }
        productPage.clickViewCart();
        Assert.assertEquals(cartDetails.getTotalProducts(),2);

    }

}
