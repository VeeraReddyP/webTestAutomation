package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.LoggerFactory;

public class LoginPage {

    private static final LoggerFactory logger = new LoggerFactory(LoginPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;


    By passwordLink = By.xpath("//div[@class='password-login']/a");
    By typePwd = By.id("Password");
    By submitPwd = By.xpath("//button[@type='submit' and contains(text(),'Enter')]");
    By textDisplay = By.xpath("//p[text()='Design your own shirts']");


    public LoginPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public void clickPasswordLink(){
        elementActions.waitForElemenWithFluent(passwordLink, TestData.timeOut, TestData.pollTime);
        elementActions.click(passwordLink);
    }

    public void enterAndSubmitPassword(){
        elementActions.waitForElemenWithFluent(typePwd, TestData.timeOut, TestData.pollTime);
        elementActions.enterData(typePwd,TestData.password);
        elementActions.click(submitPwd);
    }

    public String getProductTitle() {
        elementActions.waitForElemenWithFluent(textDisplay, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(textDisplay);
    }
}
