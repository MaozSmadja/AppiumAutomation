package Pages.iOS;

import Utils.BaseClass;
import Utils.PageBase;
import enums.grantPermissions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class PermissionsHandling extends PageBase {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    //Handling iOS Permissions

    public PermissionsHandling(AppiumDriver driver) {
        super(driver);
    }

    @iOSFindBy(id = "Allow")
    WebElement allowBtn;

    @iOSFindBy(id = "Don't Allow")
    WebElement dontAlllow;

    @iOSFindBy(id = "Always Allow")
    WebElement alwaysAllow;

    @iOSFindBy(id = "Only While Using the App")
    public WebElement whenUsingApp;

    @iOSFindBy(id = "OK")
    public WebElement okBtn;

    public void notificationPermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case allow:
                allowBtn.click();
                break;
            case deny:
                dontAlllow.click();
                break;

        }
    }

    public void locationPermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case allow:
                allowBtn.click();
                break;
            case deny:
                dontAlllow.click();
                break;

        }
    }

    public void locationAlwaysPermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case alwaysAllow:
                alwaysAllow.click();
                break;
            case whenUsingApp:
                whenUsingApp.click();
                break;

        }
    }

    public void microphonePermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case allow:
                okBtn.click();
                break;
            case deny:
                dontAlllow.click();
                break;


        }
    }
}
