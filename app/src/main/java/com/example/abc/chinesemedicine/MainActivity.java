package com.example.abc.chinesemedicine;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentPageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import util.DataBaseUtil;
import view.HomeFragment;
import view.QuestionFragment;
import view.StudyFragment;
import view.UserFragment;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tl_main)
    CommonTabLayout tlMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    private List<Fragment> list;//viewpager适配器要用到的fragment集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        DataBaseUtil.checkCopyDbFile(MainActivity.this);//检查复制数据库

        handlePermission();


    }

    //初始化UI，处理底部导航栏与4个fragment
    public void initUI() {
        ImmersionBar.with(this).init();
        list = new ArrayList<>();

        list.add(new HomeFragment());
        list.add(new StudyFragment());
        list.add(new QuestionFragment());
        list.add(new UserFragment());


        MyFragmentPageAdapter adapter = new MyFragmentPageAdapter(getSupportFragmentManager(), list);
        vpMain.setOffscreenPageLimit(3);
        vpMain.setAdapter(adapter);

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 tlMain.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });//viewpager滑动事件

        ArrayList<CustomTabEntity> customTabEntityArrayList = new ArrayList<>();

        final String title[] = {"主页", "学习", "题库", "个人"};

        final int iconSelect[] = {R.mipmap.home_pressed, R.mipmap.study_pressed, R.mipmap.question_pressed, R.mipmap.user_pressed};

        final int iconUnSelect[] = {R.mipmap.home, R.mipmap.study, R.mipmap.question, R.mipmap.user};

        for (int i = 0; i < 4; i++) {
            final int j = i;
            customTabEntityArrayList.add(new CustomTabEntity() {


                @Override
                public String getTabTitle() {
                    return title[j];
                }

                @Override
                public int getTabSelectedIcon() {
                    return iconSelect[j];
                }

                @Override
                public int getTabUnselectedIcon() {
                    return iconUnSelect[j];
                }
            });
        }


        tlMain.setTabData(customTabEntityArrayList);//给底部tab栏注入资源文件

        tlMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpMain.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });//底部tab栏点击事件
    }

    //处理运行时权限
    public void handlePermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        } else {
            initUI();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.dispose();
        ImmersionBar.with(this).destroy();
    }

    //处理权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initUI();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "请给予权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }
}
