package util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {

    public static String getLoginAccount(Context context)
    {
        SharedPreferences p=context.getSharedPreferences("loginData",Context.MODE_PRIVATE);

        String account=p.getString("loginAccount","defaultUser");

        return account;
    }
}
