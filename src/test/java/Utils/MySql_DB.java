package Utils;

import org.assertj.db.type.Request;
import org.assertj.db.type.Source;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

//import static org.assertj.core.api.Assertions.*;


public class MySql_DB extends BaseClass {

    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    private static Source source;
    final Date currentTime = new Date();

    //Make changes to the DB
    public void writeToDB(String query) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");
        //connect to your DB with UserName and Password
        Connection con = DriverManager.getConnection("jdbc:mysql://Your_DB_IP:3306/ad_db", "username", "password");
        System.out.println("connected to MySQL");
        Statement smt = con.createStatement();
        int rows = smt.executeUpdate(query);
        System.out.println("Rows imapacted : " + rows);
    }

    public void deleteIMEIofUserID(String panelistID) throws SQLException, ClassNotFoundException {
        writeToDB("UPDATE `ad_db`.`users` SET `IMEI`=NULL WHERE `userID`= '1' ");
    }

    //Set up source for AssertJ
    public Source setUp() {
        source = new Source("jdbc:mysql://Your_DB_IP:3306/ad_db", "username", "password");
        return source;
    }

    public void makeRequest(String imei, String currentTime) {
        //Make request and verify its result.
        Request request = new Request(source,
                "SELECT  userID , Data\n" +
                "FROM users_db.users\n" +
                "where userID =70\n" +
                "and imei= " + imei +
                "and data like 'Geo:10.130%,11.8928%WiFi:test%'\n" +
                "order by userid desc\n" +
                "limit  1\n");
        //Print the result to console
        output(request).toConsole();
        assertThat(request).row()
                .value("userID").isEqualTo("70")
                .value("Data").isNotNull();
    }

    public String getCurrentTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000000000");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String f = df.format(currentTime);
        return f;
    }


}


