package view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.TextView;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;

import org.greenrobot.eventbus.EventBus;

import bean.MessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import customview.MyTitleBar;
import util.CacheDataManager;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.setting_titleBar)
    MyTitleBar settingTitleBar;
    @BindView(R.id.tv_cacheSize)
    TextView tvCacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        settingTitleBar.getActivityForFinish(this);



        initUI();
    }

    public void initUI() {
        updateCacheSize();
    }


    @OnClick({R.id.ll_userData,R.id.ll_checkUpdate, R.id.ll_deleteCache, R.id.ll_help, R.id.ll_about, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_userData:
                Intent intent=new Intent(this,UserDataActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_checkUpdate:
                Snackbar.make(settingTitleBar, "当前已为最新版本", Snackbar.LENGTH_LONG)
                        .setAction("好的", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .show();
                break;
            case R.id.ll_deleteCache:
                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingActivity.this);
                dialog.setMessage("确认清除全部缓存数据吗？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CacheDataManager.clearAllCache(SettingActivity.this);
                        updateCacheSize();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

                break;
            case R.id.ll_help:
                MyApplication.nightMode();
                break;
            case R.id.ll_about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_logout:
                break;
        }
    }


    public void updateCacheSize() {
        try {
            tvCacheSize.setText(CacheDataManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new MessageEvent());
    }
}
