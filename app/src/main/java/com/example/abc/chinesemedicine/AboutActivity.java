package com.example.abc.chinesemedicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import customview.MyTitleBar;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.about_titleBar)
    MyTitleBar aboutTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        aboutTitleBar.getActivityForFinish(this);


    }

    @OnClick({R.id.tv_checkUpdate, R.id.tv_reward, R.id.tv_agreement, R.id.tv_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_checkUpdate:
                Toast.makeText(this,"当前已为最新版本",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_reward:
                Intent intent3=new Intent(this, AboutDetailsActivity.class);
                intent3.putExtra("sortType","reward");
                startActivity(intent3);
                break;
            case R.id.tv_agreement:
                Intent intent1=new Intent(this, AboutDetailsActivity.class);
                intent1.putExtra("sortType","agreement");
                startActivity(intent1);
                break;
            case R.id.tv_contact:
                Intent intent2=new Intent(this, AboutDetailsActivity.class);
                intent2.putExtra("sortType","contact");
                startActivity(intent2);
                break;
        }
    }
}
