package Utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Capabilities {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    static serverManager serverManager = new serverManager();

    public static AndroidDriver getAndroidDriver(String deviceId) throws MalformedURLException {

        //Change the url if you are going to run on cloud services as Perfecto Mobile/Experitest
        String host = "http://127.0.0.1";
        ThreadLocalDriver.setTLADB(new ADB(deviceId));
        ThreadLocalDriver.getTlAdb().unlockDevice();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", deviceId);
        capabilities.setCapability("udid", deviceId);
        capabilities.setCapability("app", "/path/to/your/app");
        /* Appium is looking for specific activity, if it cant find activity it will get exception.
           If your app switching between activities, give it 2 appWaitActivity with the activity you know.
           in order to know current activity in device use:

            adb shell dumpsys window windows | grep -E "mCurrentFocus|mFocusedApp"
            pause
        */
        capabilities.setCapability("appWaitActivity", "your.app.activity");
        capabilities.setCapability("appWaitActivity", "your.app.second.activity");
        capabilities.setCapability("appPackage", "your.app.packageName");
        //This capabilities "ignoreUnimportantViews" seem to make Appium run faster.
        capabilities.setCapability("ignoreUnimportantViews", true);
        capabilities.setCapability("newCommandTimeout", 100000);
        //Start Android device on the same port the appium starts. Appium can hold a lot of drivers at once.
        AndroidDriver webdriver = new AndroidDriver(new URL(host + ":" + "4581" + "/wd/hub"), capabilities);
        return webdriver;
    }


    //Create iOS driver for native app
    public static IOSDriver getiOSDriver(String deviceId) throws MalformedURLException {
        String host = "http://127.0.0.1";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "/your.iOS.app");
        capabilities.setCapability("udid", "834f7ef0d1f8a40753e86e16024f79d07a748bb1");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("deviceName", "834f7ef0d1f8a40753e86e16024f79d07a748bb1");
//        capabilities.setCapability("platformVersion", "10.3.3");
        capabilities.setCapability("useNewWDA", true);
        capabilities.setCapability("noReset",true);
        IOSDriver webdriver = new IOSDriver(new URL(host + ":" + "4581" + "/wd/hub"), capabilities);
        return webdriver;
    }

    //Create iOS driver for web app
    public static IOSDriver getIOSsafari(String deviceId) throws MalformedURLException {
        String host = "http://127.0.0.1";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("udid", "834f7ef0d1f8a40753e86e16024f79d07a748bb1");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("deviceName", "834f7ef0d1f8a40753e86e16024f79d07a748bb1");
//        capabilities.setCapability("platformVersion", "12.1");
        capabilities.setCapability("useNewWDA", true);
        capabilities.setCapability("startIWDP", true);
        capabilities.setCapability("browserName", "Safari");
        IOSDriver webdriver = new IOSDriver(new URL(host + ":" + "4581" + "/wd/hub"), capabilities);
        return webdriver;
    }




    public static void startService(String bpport) {
        serverManager.service(bpport);
    }

}

