package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CommonUtils extends PageBase {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */


    public CommonUtils(AppiumDriver driver) {
        super(driver);
    }

    protected final String message_id = "android:id/message";
    protected final String switchWidget_id = "com.android.settings:id/switch_text";
    @FindBy(id = switchWidget_id)
    public WebElement switchWidget;
    @FindBy(id = message_id)
    public WebElement message_d;


    //Check if element is present without threw Exception
    public boolean isDisplay(WebElement element) {
        boolean isExist;
        try {
            element.isDisplayed();
            isExist = true;
        } catch (NoSuchElementException e) {
            isExist = false;
        }
        return isExist;
    }

    //Switch wifi
    public void switchWifi(String on_off) {
        switch (on_off) {
            case "on":
                try {
                    adb.turnWIFIMethod_2("enable");
                    adb.openWifiStatus();
                    String text = driver.findElement(By.id("com.android.settings:id/wifi_state")).getText();
                    Assert.assertEquals(text, "Enabled");
                } catch (NoSuchElementException e) {
                    adb.turnWiFiOn();
//                    switchWidget.click();
                } catch (AssertionError e) {
                    adb.turnWiFiOn();
                    switchWidget.click();
                }
                adb.moveToLastApp();
                break;
            case "off":
                try {
                    adb.turnWIFIMethod_2("disable");
                    if (isDisplay(message_d)) {
                        driver.findElement(By.id("android:id/button1")).click();
                    }
                    adb.openWifiStatus();
                    String text = driver.findElement(By.id("com.android.settings:id/wifi_state")).getText();
                    Assert.assertEquals(text, "Disabled");
                    adb.moveToLastApp();
                } catch (NoSuchElementException e) {
                    adb.turnWiFiOn();
                    switchWidget.click();
                } catch (AssertionError e) {
                    adb.turnWiFiOn();
                    switchWidget.click();
                }
                if (isDisplay(message_d)) {
                    driver.findElement(By.id("android:id/button1")).click();
                }
                break;
        }

    }
}
