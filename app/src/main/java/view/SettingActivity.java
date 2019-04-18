package view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abc.chinesemedicine.R;

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
