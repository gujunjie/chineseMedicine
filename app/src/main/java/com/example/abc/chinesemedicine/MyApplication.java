package com.example.abc.chinesemedicine;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.example.abc.chinesemedicine.greendao.DaoMaster;
import com.example.abc.chinesemedicine.greendao.DaoSession;
import com.facebook.stetho.Stetho;
import com.tencent.smtt.sdk.QbSdk;


public class MyApplication extends Application {

    public static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        setDatabase();
        Stetho.initializeWithDefaults(this);
        QbSdk.initX5Environment(this,null);
        QbSdk.setDownloadWithoutWifi(true);
    }


    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        // 此处sport-db表示数据库名称 可以任意填写
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "greendao.db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public static DaoSession getDaoSession() {
        return mDaoSession;
    }


}
