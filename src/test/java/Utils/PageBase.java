package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    public AppiumDriver driver;
//    public AppiumDriver driver;
    public WebDriverWait wait;
    public ADB adb;
    public MySql_DB mySql_db;


    //Init driver for each class
    public PageBase(AppiumDriver driver) {
        this.driver = driver;
         wait = new WebDriverWait(driver, 120);
         adb = ThreadLocalDriver.getTlAdb();
         mySql_db = new MySql_DB();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
