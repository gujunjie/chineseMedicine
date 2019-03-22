package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;

public class UserDataActivity extends AppCompatActivity {

    @BindView(R.id.userData_titleBar)
    MyTitleBar userDataTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        ButterKnife.bind(this);

        userDataTitleBar.getActivityForFinish(this);
    }
}
