package testScenarios;

import data.TestData;
import init.DriverUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;
import utils.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogData extends BaseRun {
    private static final LoggerFactory logger = new LoggerFactory(CatalogData.class);
    LoginPage loginPage;
    SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(DriverUtils.getDriver());
        searchPage = new SearchPage(loginPage.driver);
    }

    @Test
    public void sortedCollectionData() {
        loginPage.clickPasswordLink();
        loginPage.enterAndSubmitPassword();
        Assert.assertEquals(loginPage.getProductTitle(), TestData.expectedProductTitle);
        searchPage.clickCatalog();
        List<WebElement> productNames = searchPage.listOfProductNames();
        ArrayList<String> allProductNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            allProductNames.add(productName.getText());
        }

        // to print only round neck t-shirt names
        List<String> roundNeckShirts = allProductNames.stream().filter(s -> s.startsWith("R")).
                collect(Collectors.toList());

        System.out.println("T-shirt names start with Letter R:::::: " + roundNeckShirts);

        // sort based on product name length
        Collections.sort(allProductNames, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }

        });
        System.out.println("After sorted based on product name length :::::: " + allProductNames);
    }
}
