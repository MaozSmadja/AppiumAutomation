package Utils.listener;

import Utils.BaseClass;
import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.Attachment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Listener extends BaseClass implements ITestListener {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    //Listeners in each case of test results
    private String testResult;
    private Logger log = LogManager.getLogger(getClass().getName());


    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    private static String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    private String getTestResult() {
        return testResult;
    }

    private void setTestResult(String testResult) {
        this.testResult = testResult;
    }


    @Override
    public void onTestStart(ITestResult result) {
        ExtendTestManager.startTest(result.getMethod().getMethodName(), "");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtendTestManager.getTest().log(LogStatus.PASS, "Test passed");
        setTestResult("PASSED");
        setTestFailedMsg("-");
    }


    @Override
    public void onTestFailure(ITestResult result) {
        setTestResult("FAILED");
        setTestFailedMsg(result.getThrowable().getMessage());
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseClass) testClass).getDriver();
        if (driver != null) {
            saveScreenshotPNG(driver);
        }
        log.info(result.getMethod().getMethodName() + " failed and screenshot taken!");
        assert ((TakesScreenshot) driver) != null;
        String screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        ExtendTestManager.getTest().log(LogStatus.FAIL, "Test Failed", ExtendTestManager.getTest().addBase64ScreenShot(screenshot));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtendTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        setTestResult("SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        result.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext context) {
        setTestCaseName(context.getName());
        setTestStartTime(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtendTestManager.endTest();
        ExtendManager.getReporter().flush();
        setTestFinishTime(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        setTestDurationTime(getTestFinishTime() - getTestStartTime());

        if (Objects.equals(getTestResult(), "PASSED")) {
            log.info(getTestResult());
        } else if (Objects.equals(getTestResult(), "FAILED")) {
            log.error(getTestResult());
        } else {
            log.warn(getTestResult());
        }
    }

}