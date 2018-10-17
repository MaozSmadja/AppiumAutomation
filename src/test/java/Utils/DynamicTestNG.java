package Utils;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTestNG {


//Create and run XML through runner


//    public void runTestNGTest(List<Map<String, String>> allMaps) {
//        TestNG myTestNG = new TestNG();
//
//        // Create an instance of XML Suite and assign a name for it.
//        XmlSuite mySuite = new XmlSuite();
//        mySuite.setName("MySuite");
//        mySuite.setParallel(XmlSuite.ParallelMode.TESTS);
//        mySuite.addListener("Utils.listener.Listener");
//        List<XmlClass> myClasses = new ArrayList<>();
//        myClasses.add(new XmlClass("detectAdTest"));
//        List<XmlTest> myTests = new ArrayList<>();
//        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
//        myTestNG.setXmlSuites(mySuites);
//
//        XmlTest ZTE = new XmlTest(mySuite);
//        ZTE.setName("Test On ZTE");
//        ZTE.setParameters(allMaps.get(0));
//        ZTE.setXmlClasses(myClasses);
//        myTests.add(ZTE);
//
//        XmlTest Vivo = new XmlTest(mySuite);
//        Vivo.setName("Test On Vivo");
//        Vivo.setParameters(allMaps.get(1));
//        Vivo.setXmlClasses(myClasses);
//        myTests.add(Vivo);
//
//        // add the list of iOS to your Suite.
//        mySuite.setTests(myTests);
//
//        // Add the suite to the list of suites.
//        mySuites.add(mySuite);
//
//        // Set the list of Suites to the testNG object you created earlier.
//        mySuite.setFileName("resources/Runner/myTemp.xml");
//
//        // Create physical XML file based on the virtual XML content
//        for (XmlSuite suite : mySuites) {
//            createXmlFile(suite);
//        }
//        System.out.println("File created successfully.");
//        myTestNG.run();
//
//    }
//
//    // This method will create an Xml file based on the XmlSuite data
//    public void createXmlFile(XmlSuite mSuite) {
//        FileWriter writer;
//        try {
//            writer = new FileWriter(new File("test/resources/Runner/myTemp.xml"));
//            writer.write(mSuite.toXml());
//            writer.flush();
//            writer.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Main Method
//    public static void main(String args[]) {
//        DynamicTestNG dt = new DynamicTestNG();
//
//        // This Map can hold your testng Parameters.
//        List<Map<String, String>> allMaps = new ArrayList<>();
//
//        Map<String, String> testngParams1 = new HashMap<>();
//        testngParams1.put("deviceID", "ffc64100");
//        testngParams1.put("port", "4573");
//        testngParams1.put("bpport", "5766");
//        testngParams1.put("panelistID", "300");
//
//        Map<String, String> testngParams = new HashMap<>();
//        testngParams.put("deviceID", "df89e0b3");
//        testngParams.put("port", "4571");
//        testngParams.put("bpport", "5764");
//        testngParams.put("panelistID", "301");
//        allMaps.add(testngParams1);
//        allMaps.add(testngParams);
//
//        dt.runTestNGTest(allMaps);
//    }

}

