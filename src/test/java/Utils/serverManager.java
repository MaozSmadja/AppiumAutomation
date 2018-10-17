package Utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;


public class serverManager {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    public static AppiumDriverLocalService serviceBuilder;

    //Configure appium server
    public static void service(String bpport) {
        serviceBuilder = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                //Port should be the same as driver port
                .usingPort(4581)
                //BootStrap port for each device
                .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, String.valueOf(bpport)));
        serviceBuilder.start();

    }


    public static void stopService() {
        serviceBuilder.stop();
    }


}
