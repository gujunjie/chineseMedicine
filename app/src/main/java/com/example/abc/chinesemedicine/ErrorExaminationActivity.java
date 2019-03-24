package com.example.abc.chinesemedicine;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.abc.chinesemedicine.greendao.ErrorExaminationDao;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentPageAdapter;
import bean.ErrorExamination;
import bean.Examination;
import bean.MessageEvent;
import bean.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBarWhite;
import util.DataBaseUtil;
import view.ErrorExamFragment;
import view.ExamActivity;
import view.ExamFragment;

public class ErrorExaminationActivity extends AppCompatActivity {

    @BindView(R.id.errorExam_titleBar)
    MyTitleBarWhite errorExamTitleBar;
    @BindView(R.id.vp_errorExam)
    ViewPager vpErrorExam;
    @BindView(R.id.tv_correctNumber)
    TextView tvCorrectNumber;
    @BindView(R.id.tv_errorNumber)
    TextView tvErrorNumber;
    @BindView(R.id.tv_allNumber)
    TextView tvAllNumber;
    @BindView(R.id.tv_currentNumber)
    TextView tvCurrentNumber;
    @BindView(R.id.tfl_allItem)
    TagFlowLayout tflAllItem;
    @BindView(R.id.slidingUpPanel)
    SlidingUpPanelLayout slidingUpPanel;

    private String sortType;

    private int correntNumber = 0;

    private int errorNumber = 0;

    private int currentPosition = 0;

    private List<ErrorExamination> errorExaminationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_examination);
        ButterKnife.bind(this);

        initUI();


    }

    public void initUI() {
        errorExamTitleBar.getActivityForFinish(this);
        sortType = getIntent().getStringExtra("sortType");
        errorExamTitleBar.setTitle(sortType);

        User user= DataBaseUtil.getUser(this);

        ErrorExaminationDao dao=MyApplication.getDaoSession().getErrorExaminationDao();

        errorExaminationList=dao.queryBuilder().where(ErrorExaminationDao.Properties.UserId.eq(user.getId())).where(ErrorExaminationDao.Properties.SortType.eq(sortType)).list();

        tvAllNumber.setText("/" + errorExaminationList.size());

        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < errorExaminationList.size(); i++) {
            ErrorExamFragment fragment = new ErrorExamFragment();
            fragment.setData(errorExaminationList.get(i));
            fragmentList.add(fragment);
        }

        //优先将试题的数据传送到fragment类中用于初始化碎片的数据以及事件侦听，避免空指针异常

        vpErrorExam.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(), fragmentList));

        vpErrorExam.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                tvCurrentNumber.setText(position + 1 + "");

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initAllItemFlowLayout(errorExaminationList.size());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCorrectOrErrorNumber(MessageEvent event) {
        if (event.getIsCorrect()) {
            correntNumber++;
            tvCorrectNumber.setText(correntNumber + "");
            updateRightTimes();//跟新做对次数
            if(currentPosition==errorExaminationList.size()-1)
            {

            }else
            {


                //用户答对且当前不为最后一题，延时一秒自动跳转下一题
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        vpErrorExam.setCurrentItem(currentPosition+1,true);
                    }
                },500);



            }

        } else {
            errorNumber++;
            tvErrorNumber.setText(errorNumber + "");

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("unchecked")
    public void initAllItemFlowLayout(int itemNumber) {

        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < itemNumber; i++) {
            list.add(i + 1);
        }


        tflAllItem.setAdapter(new TagAdapter(list) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tag = (TextView) LayoutInflater.from(ErrorExaminationActivity.this).inflate(R.layout.allitemtag_layout, tflAllItem, false);
                tag.setText(o.toString());
                return tag;
            }
        });

        tflAllItem.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                int tag = list.get(position);
                vpErrorExam.setCurrentItem(tag - 1, true);
                slidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                return true;
            }
        });


    }//初始化上拉栏的数字流式布局

    public void updateRightTimes()
    {

        ErrorExaminationDao errorExaminationDao=MyApplication.getDaoSession().getErrorExaminationDao();

        ErrorExamination errorExamination=errorExaminationList.get(currentPosition);

        int rightTimes=errorExamination.getRightTimes();

        rightTimes++;//当前错题做对的次数

        User user=DataBaseUtil.getUser(this);

        int rightTimesForRemoveSetting=user.getRightTimesForRemove();//用户设置的错对删除次数


        if(rightTimesForRemoveSetting!=-1)
        {
            if(rightTimes>=rightTimesForRemoveSetting)//删除
            {
                errorExaminationDao.delete(errorExamination);
            }else
            {
                errorExamination.setRightTimes(rightTimes);
                errorExaminationDao.update(errorExamination);
            }//更新本题做对次数
        }



    }

}
