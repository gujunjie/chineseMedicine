package view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.chinesemedicine.R;
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
import base.BaseActivity;
import bean.Examination;
import bean.MessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.ExamContract;
import customview.MyTitleBarWhite;
import presenter.ExamPresenter;

public class ExamActivity extends BaseActivity<ExamContract.ExamView, ExamPresenter> implements ExamContract.ExamView {

    @BindView(R.id.exam_titleBar)
    MyTitleBarWhite examTitleBar;
    @BindView(R.id.vp_exam)
    ViewPager vpExam;
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

    private ExamPresenter presenter;

    private String sortType;

    private int correntNumber = 0;

    private int errorNumber = 0;

    private int currentPosition = 0;

    private List<Examination> examinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);

        initUI();

    }


    public void initUI() {
        examTitleBar.getActivityForFinish(this);
        sortType = getIntent().getStringExtra("sortType");
        examTitleBar.setTitle(sortType);
        getExaminationList(sortType);
    }

    public void getExaminationList(String sortType) {
        presenter.getExaminationList(sortType);//获取试题
    }

    @Override
    public void showExamination(List<Examination> list) {
         examinationList=list;
        tvAllNumber.setText("/" + list.size());

        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ExamFragment fragment = new ExamFragment();
            fragment.setData(list.get(i));
            fragmentList.add(fragment);
        }

        //优先将试题的数据传送到fragment类中用于初始化碎片的数据以及事件侦听，避免空指针异常

        vpExam.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(), fragmentList));

        vpExam.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        initAllItemFlowLayout(list.size());//数字流式布局


    }


    @Override
    public ExamPresenter createPresenter() {
        presenter = new ExamPresenter(this);
        return presenter;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCorrectOrErrorNumber(MessageEvent event) {
        if (event.getIsCorrect()) {
            correntNumber++;
            tvCorrectNumber.setText(correntNumber + "");
        } else {
            errorNumber++;
            tvErrorNumber.setText(errorNumber + "");
            presenter.saveErrorExamination(examinationList.get(currentPosition),this);//记录错题

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
                TextView tag = (TextView) LayoutInflater.from(ExamActivity.this).inflate(R.layout.allitemtag_layout, tflAllItem, false);
                tag.setText(o.toString());
                return tag;
            }
        });

        tflAllItem.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                int tag = list.get(position);
                vpExam.setCurrentItem(tag - 1, true);
                slidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                return true;
            }
        });


    }//初始化上拉栏的数字流式布局


}