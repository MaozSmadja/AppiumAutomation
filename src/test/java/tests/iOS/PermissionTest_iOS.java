package tests.iOS;

import Utils.BaseClass;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;

import static enums.grantPermissions.allow;
import static enums.grantPermissions.alwaysAllow;

public class PermissionTest_iOS extends BaseClass {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

        @BeforeMethod
    public void beforeMethod() {
        permissionsHandling.notificationPermission(allow);
        welcomePage.startButton();
    }

        @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.executeScript("mobile:removeApp", ImmutableMap.of("bundleId", "com.your.app"));
    }

    @Test
    public void insertValidCredentials() throws InterruptedException {
        permissionsHandling.locationPermission(allow);
        Thread.sleep(1000);
        try {
            permissionsHandling.locationAlwaysPermission(alwaysAllow);
        } catch (NoSuchElementException e) {
            permissionsHandling.microphonePermission(allow);
        }
        try {
            permissionsHandling.microphonePermission(allow);
        } catch (NoSuchElementException e) {
            System.out.println("No more permissions needed");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("element"))).click();
    }

}
