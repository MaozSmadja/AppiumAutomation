package tests.Android;

import Utils.BaseClass;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static enums.grantPermissions.*;


@Epic("Regression Tests")
@Feature("Login Tests")
public class PermissionTest_Android extends BaseClass {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */


    //Example using test description

    @BeforeMethod
    public void reset() {
        launchApp();
        welcomePage.startAndOk();
    }

    @AfterMethod
    public void resetAfter() {
        driver.resetApp();
    }


    @Test(description = "Deny call permission forever, reopen, and assert that cant proceed")

    public void denyCallForever() {
        permissionsPage.mediaPermission(allow);
        permissionsPage.callPermission(denyForever);
    }

    @Test(description = "Deny usage permission, and assert cant proceed ")
    public void denyUsage() {
        permissionsPage.mediaPermission(allow);
        permissionsPage.callPermission(allow);
        permissionsPage.audioPermission(allow);
        permissionsPage.locationPermission(allow);
        permissionsPage.usagePermission(deny);
    }

    @Test(
            description = "Deny microphone permission, assert cant proceed message")
    public void denyMicPermission() {
        permissionsPage.mediaPermission(allow);
        permissionsPage.callPermission(allow);
        permissionsPage.audioPermission(deny);
    }

    @Test(
            description = "Deny microphone permission forever, assert cant proceed message and close app")
    public void denyMicForever() {
        permissionsPage.mediaPermission(allow);
        permissionsPage.callPermission(allow);
        permissionsPage.audioPermission(denyForever);
    }


}

