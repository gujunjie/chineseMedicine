package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.setting_titleBar)
    MyTitleBar settingTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        settingTitleBar.getActivityForFinish(this);
    }
}
