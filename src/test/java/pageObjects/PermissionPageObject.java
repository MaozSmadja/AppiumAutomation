package pageObjects;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class PermissionPageObject {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */
    private final String permissionMSG_id="com.android.packageinstaller:id/permission_message";
    private final String switchOnOffZTE_id="com.android.settings:id/switchWidget";
    private final String switchOnOffVivo_id="android:id/checkbox";
    private final String switchOnOffnexus_id="com.android.packageinstaller:id/permission_message";
    private final String alertTitle_id="android:id/alertTitle";
    private final String afterDenyMSG_id="android:id/message";
    private final String denyBTN_id="com.android.packageinstaller:id/permission_deny_button";
    private final String allowBTN_id="com.android.packageinstaller:id/permission_allow_button";
    private final String titleText_class="android.widget.TextView";
    private final String dontAskAgainCheckBox_id="com.android.packageinstaller:id/do_not_ask_checkbox";
    private final String okBotton_id="android:id/button1";

    @AndroidFindBy(id = permissionMSG_id )
    public WebElement permissionMSG;

    @AndroidFindBy(id = switchOnOffZTE_id)
    public WebElement switchOnOffZTE;

    @AndroidFindBy(id = switchOnOffVivo_id)
    public WebElement switchOnOffVivo;

    @AndroidFindBy(id = switchOnOffnexus_id)
    public WebElement switchOnOffnexus;

    @AndroidFindBy(id = alertTitle_id)
    public WebElement alertTitle;

    @AndroidFindBy(id = afterDenyMSG_id)
    public WebElement afterDenyMSG;

    @AndroidFindBy(id = denyBTN_id)
    public WebElement denyBTN;

    @AndroidFindBy(id = allowBTN_id)
    public WebElement allowBTN;

    @AndroidFindBy(className = titleText_class)
    public WebElement titleText;

    @AndroidFindBy(id = dontAskAgainCheckBox_id)
    public WebElement dontAskAgainCheckBox;

    @AndroidFindBy(id = okBotton_id)
    public WebElement okBotton;


}







