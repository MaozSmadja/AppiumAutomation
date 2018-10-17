package Utils;

import Pages.Android.*;
import Pages.iOS.PermissionsHandling;
import Utils.listener.Listener;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseClass {


    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    public ADB adb;
    public PermissionsPage permissionsPage;
    public AppiumDriver driver;
    public WebDriverWait wait;
    public static serverManager serverManager = new serverManager();
    public MySql_DB mySql_db;
    public WelcomePage welcomePage;
    public Listener listener;
    public String panelistID_Parameter;
    public CommonUtils commonUtils;
    public PermissionsHandling permissionsHandling;
    private String testCaseName;
    private long testStartTime;
    private long testFinishTime;
    private long testDurationTime;
    private String testFailedMsg;


    public WebDriver getDriver() {
        return driver;
    }

    //Set log level
    @BeforeSuite
    public void beforesuit() {
        Log.log.setLevel(Level.DEBUG);
    }

    /*Start Appium server programmatically
      Each device has it's own bpPort(BootStrapPort) to communicate with the Appium server.
      All parameters are taken from the SuiteRun.xml file.

      */
    @Parameters({"bpport"})
    @BeforeTest
    public void startService(String bpport) {
        Capabilities.startService(bpport);
    }

    //Start Appium driver according to the platform name, take capabilities that relevant to the server.
    @Parameters({"udid", "panelistID", "platformName"})
    @BeforeClass
    public void beforeClass(String deviceID, String panelistID, String platformName) throws MalformedURLException {
        switch (platformName) {
            case "iOS":
                driver = Capabilities.getiOSDriver(deviceID);
                break;
            case "Android":
                driver = Capabilities.getAndroidDriver(deviceID);
                break;
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        /*In order to send adb commands to each device, we should create different thread for each device.
          The adb thread is created with the capabilities of android inside Capabilities class.
          ThreadLocalDriver.getTlAdb()= takes the current thread running and place it in adb.
        */
        adb = ThreadLocalDriver.getTlAdb();
        mySql_db = new MySql_DB();
        listener = new Listener();
        //Set up connection to the DB
        mySql_db.setUp();
        panelistID_Parameter = panelistID;
        wait = new WebDriverWait(driver, 180);
        welcomePage = new WelcomePage(driver);
        permissionsPage = new PermissionsPage(driver);
        commonUtils = new CommonUtils(driver);
        permissionsHandling = new PermissionsHandling(driver);
    }


    //Always Run after class, so if test fail while creating the driver, it will close the Appium server. else it stays running until the timeOut ends.
    @Parameters("platformName")
    @AfterClass(alwaysRun = true)
    public void afterClass(String platformName) {
        System.out.println("execute after class");
        try {
            if (platformName == "Android") {
                driver.removeApp("appName");
            } else {
                driver.executeScript("mobile:removeApp", ImmutableMap.of("bundleId", "com.yourApp"));
            }
            driver.quit();
        } catch (NullPointerException e) {
            System.out.println("The driver never started");
        }


    }

    public void launchApp() {
        if (!adb.getForegroundActivity().contains("mobile")) {
            driver.launchApp();
        }
    }

    //Closing the Appium server programmatically
    @AfterSuite(alwaysRun = true)
    public void after() {

        serverManager.stopService();
        System.out.println("Closed server successfully");


    }


    protected long getTestStartTime() {
        return testStartTime;
    }

    protected void setTestStartTime(long testStartTime) {
        this.testStartTime = testStartTime;
    }

    protected long getTestFinishTime() {
        return testFinishTime;
    }

    protected void setTestFinishTime(long testFinishTime) {
        this.testFinishTime = testFinishTime;
    }

    protected long getTestDurationTime() {
        return testDurationTime;
    }

    protected void setTestDurationTime(long testDurationTime) {
        this.testDurationTime = testDurationTime;
    }

    protected String getTestCaseName() {
        return testCaseName;
    }

    protected void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestFailedMsg() {
        return testFailedMsg;
    }

    public void setTestFailedMsg(String testFailedMsg) {
        this.testFailedMsg = testFailedMsg;
    }
}


