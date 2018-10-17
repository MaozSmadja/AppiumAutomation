package Pages.Android;

import Utils.PageBase;
import enums.grantPermissions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.PermissionPageObject;

import static enums.grantPermissions.allow;

public class PermissionsPage extends PageBase {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    public PermissionPageObject permissionPO = new PermissionPageObject();


    public PermissionsPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), permissionPO);
    }


    @Step("Get usage text title")
    private String getTextTitle() {
        String textTitle = permissionPO.titleText.getText().toLowerCase();
        return textTitle;
    }

    @Step("Get device model")
    public String getDevice() {
        String devicemodel = adb.getDeviceModel().toLowerCase();
        return devicemodel;
    }

    @Step("Get permission message request")
    public String getPermissionMSG() {
        String text = permissionPO.permissionMSG.getText().toLowerCase();
        return text;
    }

    @Step("Switch on usage permission")
    public void switchOnOff() {
        if (getDevice().contains("vivo")) {
            permissionPO.switchOnOffVivo.click();
        } else if (getDevice().contains("zte")) {
            permissionPO.switchOnOffZTE.click();
        } else if (getDevice().contains("nexus")) {
            permissionPO.switchOnOffnexus.click();
        } else {
            permissionPO.switchOnOffZTE.click();
        }
    }

    @Step("Check if element is present")
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

    @Step("Check if app did finish with permissions")
    public void didFinishPermissions() {
        while (!isDisplay(permissionPO.okBotton)) {
            if (isDisplay(permissionPO.afterDenyMSG)) {
                permissionPO.okBotton.click();
                driver.navigate().back();
            } else {
                driver.navigate().back();
            }
        }
        System.out.println("No more permissions required");
    }


    @Step("Get app text message")
    private String appMessage() {
        String denyTxtMsg = permissionPO.afterDenyMSG.getText();
        return denyTxtMsg;
    }

    @Step("Handle Media Permission")
    public void mediaPermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case allow:
                permissionPO.allowBTN.click();
                break;
            case deny:
                permissionPO.denyBTN.click();
                break;
        }
    }

    @Step("Handle call Permission")
    public void callPermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case allow:
                permissionPO.allowBTN.click();
                break;

            case deny:
                permissionPO.denyBTN.click();
                permissionPO.okBotton.click();
                Assert.assertTrue(getPermissionMSG().contains("calls") || getPermissionMSG().contains("phone"));
                break;

            case denyForever:
                permissionPO.denyBTN.click();
                permissionPO.okBotton.click();
                permissionPO.dontAskAgainCheckBox.click();
                permissionPO.denyBTN.click();
                permissionPO.okBotton.click();
                System.out.println("package is " + adb.getCurrentPackage());
                Assert.assertTrue(!adb.getCurrentPackage().contains("app"), "app remains in foreground");
                break;

        }
    }

    @Step("Handle audio Permission")
    public void audioPermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case allow:
                permissionPO.allowBTN.click();
                break;
            case deny:
                permissionPO.denyBTN.click();
                permissionPO.okBotton.click();
                Assert.assertTrue(getPermissionMSG().contains("microphone"));
                break;
            case denyForever:
                permissionPO.denyBTN.click();
                permissionPO.okBotton.click();
                permissionPO.dontAskAgainCheckBox.click();
                permissionPO.denyBTN.click();
                permissionPO.okBotton.click();
                System.out.println("package is " + adb.getCurrentPackage());
                Assert.assertTrue(!adb.getCurrentPackage().contains("app"), "app remains in foreground");
                break;
        }
    }

    @Step("Handle Location Permission")
    public void locationPermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case allow:
                permissionPO.allowBTN.click();
                break;
            case deny:
                permissionPO.denyBTN.click();
                break;

        }
    }

    @Step("Handle usage Permission")
    public void usagePermission(grantPermissions grantOrNot) {
        switch (grantOrNot) {
            case allow:
                switchOnOff();
                driver.navigate().back();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.TextView")));
                driver.navigate().back();
                break;
            case deny:
                driver.navigate().back();
                permissionPO.okBotton.click();
                Assert.assertTrue(getTextTitle().contains("usage"));
                break;

        }
    }

    @Step("Auto grant all Android permissions")
    public void grantAllPermissions() {
        mediaPermission(allow);
        callPermission(allow);
        audioPermission(allow);
        locationPermission(allow);
        try {
            usagePermission(allow);
        } catch (AssertionError | NoSuchElementException e) {
            driver.navigate().back();
        }
        didFinishPermissions();


    }
}
