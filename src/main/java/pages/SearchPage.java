package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.LoggerFactory;

public class SearchPage {

    private static final LoggerFactory logger = new LoggerFactory(SearchPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;


    By searchIcon = By.xpath("//div[@class='site-header__icons-wrapper']/button[1]");
    By typeSearchItems = By.name("q");
    By submitSearch = By.xpath("//form[@class='search-form search-bar__form']/button");
    By catalog = By.xpath("//ul[@id='SiteNav']/descendant::span[text()='Catalog']");
    public By selectProduct(String searchItem){
        return By.xpath("//span[@class='visually-hidden' and text()='"+searchItem+"']/parent::a");
    }
    By catalogVal = By.id("SortBy");
    By cataProduct = By.xpath("//span[@class='visually-hidden' and text()='Another Round Neck Shirt']/parent::a");


    public SearchPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public void clickSearchIcon(){
        elementActions.waitForElemenToBeClickWithFluent(searchIcon, TestData.timeOut, TestData.pollTime);
        elementActions.click(searchIcon);
    }

    public void enterAndSubmitSearchData(String productName){
        elementActions.waitForElemenWithFluent(typeSearchItems, TestData.timeOut, TestData.pollTime);
        elementActions.enterData(typeSearchItems,productName);
        elementActions.waitForElemenWithFluent(submitSearch, TestData.timeOut, TestData.pollTime);
        elementActions.click(submitSearch);
    }

    public void clickSelectedProduct(String productName){
        elementActions.waitForElemenToBeClickWithFluent(selectProduct(productName), TestData.timeOut, TestData.pollTime);
        elementActions.click(selectProduct(productName));
    }

    public void clickCatalog(){
        elementActions.waitForElemenToBeClickWithFluent(catalog, TestData.timeOut, TestData.pollTime);
        elementActions.click(catalog);
    }

    public void selectCatalogValue(String categoryName){
        elementActions.waitForElemenToBeClickWithFluent(catalogVal, TestData.timeOut, TestData.pollTime);
        elementActions.selectDropdownByText(catalogVal,categoryName);
    }

    public void clickCatalogInList(){
        elementActions.waitForElemenToBeClickWithFluent(cataProduct, TestData.timeOut, TestData.pollTime);
        elementActions.click(cataProduct);
    }

}
