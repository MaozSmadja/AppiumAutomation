package Utils;

public class ThreadLocalDriver {


    /**
     * Created by MaozSmadja on 02/03/2018.
     */

    private static ThreadLocal<ADB> tladb = new ThreadLocal<>();

    public synchronized static void setTLADB(ADB adb) {
        tladb.set(adb);
    }


    public synchronized static ADB getTlAdb() {

        return tladb.get();
    }



}

