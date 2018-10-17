package Pages.Android;

import Utils.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import pageObjects.WelcomePageObject;

public class WelcomePage extends PageBase {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    public WelcomePageObject welcomePO = new WelcomePageObject();

    public WelcomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), welcomePO);
    }


    //Example of using Allure steps
    @Step("Press start and ok on alert message")
    public void startAndOk() {
        welcomePO.startBtn.click();
        welcomePO.pressOk.click();
    }

    @Step("Press start for ios Devices")
    public void startButton() {
        welcomePO.startBtn.click();
    }
}
