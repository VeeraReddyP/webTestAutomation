package init;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {


    private static final LoggerFactory logger = new LoggerFactory(TestListener.class);

    private static String fileSeparator = System.getProperty("file.separator");
    private static String screenShotPath = System.getProperty("user.dir") + fileSeparator + "screenShots";

    public void onStart(ITestContext context) {
        logger.info("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        logger.info(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        logger.info(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        logger.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");

        try {
            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
            ExtentTestManager.getTest().fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(getScreenShot()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public static String getScreenShot() throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File source = ((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.FILE);
        String destination = screenShotPath+fileSeparator+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

}
