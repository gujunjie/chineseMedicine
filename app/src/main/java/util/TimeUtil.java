package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String getSystemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
}
