package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ADB {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */
    //ADB Commands


    private String ID;

    public ADB(String device) {
        ID = device;
    }
    private static String ANDROID_HOME;

    public static String getAndroidHome() {
        if (ANDROID_HOME == null) {
            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if (ANDROID_HOME == null)
                throw new RuntimeException("Failed to find ANDROID_HOME, make sure the environment variable is set");
        }
        return ANDROID_HOME;
    }

    public static String runCommand(String command) {
        String output = null;
        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if (scanner.hasNext()) output = scanner.next();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return output;
    }

    public static String command(String command) {
//        MyLogger.log.debug("Formatting ADB Command: "+command);
        if (command.startsWith("adb")) command = command.replace("adb ", getAndroidHome() + "/platform-tools/adb ");
        else throw new RuntimeException("This method is designed to run ADB commands only!");
//        MyLogger.log.debug("Formatted ADB Command: "+command);
        String output = runCommand(command);
//        MyLogger.log.debug("Output of the ADB Command: "+output);
        if (output == null) return "";
        else return output.trim();
    }

    public static void killServer() {
        command("adb kill-server");
    }

    public void startAppByPackage() {
        command("adb -s " + ID + " shell monkey -p com.your.app 1");
    }

    public static void startServer() {
        command("adb start-server");
    }

    public static ArrayList<String> getConnectedDevices() {
        ArrayList<String> devices = new ArrayList<String>();
        String output = command("adb devices");
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) devices.add(line.replace("device", "").trim());
        }
        return devices;
    }

    public String getForegroundActivity() {
        return command("adb -s " + ID + " shell dumpsys window windows | grep mCurrentFocus");
    }

    public String getAndroidVersionAsString() {
        String output = command("adb -s " + ID + " shell getprop ro.build.version.release");
        if (output.length() == 3) output += ".0";
        return output;
    }

    public int getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
    }

    public ArrayList<String> getInstalledPackages() {
        ArrayList<String> packages = new ArrayList<String>();
        String[] output = command("adb -s " + ID + " shell pm list packages").split("\n");
        for (String packageID : output) {
            packages.add(packageID.replace("package:", "").trim());
        }
        return packages;
    }

    public void moveToLastApp() {
        command("adb -s " + ID + " shell input keyevent KEYCODE_APP_SWITCH");
        if(getDeviceModel().contains("zte")){
            command("adb -s " + ID + " shell input keyevent KEYCODE_APP_SWITCH");
        }else{
            command("adb -s " + ID + " shell input tap 250 520");
        }

    }

    public void openAppsActivity(String packageID, String activityID) {
        command("adb -s " + ID + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
    }

    public void clearAppsData(String packageID) {
        command("adb -s " + ID + " shell pm clear " + packageID);
    }

    public void forceStopApp(String packageID) {
        command("adb -s " + ID + " shell am force-stop " + packageID);
    }

    public void installApp(String apkPath) {
        command("adb -s " + ID + " install " + apkPath);
    }

    public void uninstallApp(String packageID) {
        command("adb -s " + ID + " uninstall " + packageID);
    }

    public void clearLogBuffer() {
        command("adb -s " + ID + " shell -c");
    }

    public String getDeviceTime() {
        String date = command("adb -s " + ID + " shell date");
        return date;
    }

    public void pushFile(String source, String target) {
        command("adb -s " + ID + " push " + source + " " + target);
    }

    public void pullFile(String source, String target) {
        command("adb -s " + ID + " pull " + source + " " + target);
    }

    public void deleteFile(String target) {
        command("adb -s " + ID + " shell rm " + target);
    }

    public String getCurrentPackage() {
        String output = command("adb -s " + ID + " shell dumpsys window windows   | grep -E \"mCurrentFocus|mFocusedApp\"");
        return output;
    }

    public void moveFile(String source, String target) {
        command("adb -s " + ID + " shell mv " + source + " " + target);
    }

    public void takeScreenshot(String target) {
        command("adb -s " + ID + " shell screencap -p \"/mnt/sdcard/output.png\" && adb pull \"/mnt/sdcard/output.png\" \"C:\\Users\\maoz\\Desktop\\ADB Commands\" && adb shell rm \"/mnt/sdcard/output.png");
    }

    public void rebootDevice() {
        command("adb -s " + ID + " reboot");
    }

    public void grantCallPermission() {
        command("adb -s " + ID + " shell pm grant com.your.app android.permission.READ_PHONE_STATE");
    }

    public String getDeviceModel() {
        return command("adb -s " + ID + " shell getprop ro.product.model").toLowerCase();
    }

    public String unlockDevice() {
        return command("adb -s " + ID + " shell am start -n io.appium.unlock/.Unlock");
    }

    public String getDeviceSerialNumber() {
        return command("adb -s " + ID + " shell getprop ro.serialno");
    }

    public String getDeviceCarrier() {
        return command("adb -s " + ID + " shell getprop gsm.operator.alpha");
    }


    public void turnWIFIMethod_2(String enableDisable) {
        command("adb -s " + ID + " shell am broadcast -a io.appium.settings.wifi --es setstatus " + enableDisable);
    }

    public void toggleData(String enableDisable) {
        command("adb -s " + ID + " adb -s ffc64100 shell svc data " + enableDisable);
    }


    public String playAudioFile(String  adName) {
        return command("adb -s " + ID + " shell am start -a android.intent.action.VIEW -d file:///storage/emulated/0/" +  adName + " -t audio/wav");
    }

    public ArrayList<String> getLogcatProcesses() {
        String[] output = command("adb -s " + ID + " shell top -n 1 | grep -i 'logcat'").split("\n");
        ArrayList<String> processes = new ArrayList<>();
        for (String line : output) {
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }

    public void openWifiStatus() {
        command("adb -s " + ID + " shell am start -n com.android.settings/.wifi.WifiStatusTest");
    }

    public void turnWiFiOn() {
        command("adb -s " + ID + "  shell am start -n com.android.settings/.wifi.WifiSettings");
        command("adb -s " + ID + "  shell input keyevent 20");
        command("adb -s " + ID + "  shell input keyevent 23");

    }

    public void turnWifiOff() {
        command("adb -s " + ID + "  shell am start -n com.android.settings/.wifi.WifiSettings");
        command("adb -s " + ID + "  shell input keyevent 23");
    }

    public void pressHomeButton(){
        command("adb -s " + ID + " shell input keyevent  KEYCODE_HOME");

    }

    public void openAppByPackage(){
        command("adb -s " + ID + " shell monkey -p com.your.app -c android.intent.category.LAUNCHER 1");
    }

    public void sendKey(String keyNumber){
        command(("adb -s " + ID + " shell input keyevent" + keyNumber));
    }

    public String getNetworkName(){
        return command("adb -s " + ID + " shell dumpsys netstats | grep -E 'iface=wlan.*networkId'");
    }
}




