package pageObjects;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePageObject {


    /**
     * Created by MaozSmadja on 02/03/2018.
     */
    //Example of using FindBy for android/ios/web
    @AndroidFindBy(id = "splash_startButton")
    @iOSFindBy(id = "Start")
    @FindBy (id = "Start")
    public WebElement startBtn;

    @AndroidFindBy(id = "android:id/button1")
    public WebElement pressOk;


}