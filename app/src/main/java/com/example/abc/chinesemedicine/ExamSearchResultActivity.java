package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import bean.Examination;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;
import customview.SectionView;

public class ExamSearchResultActivity extends AppCompatActivity {


    @BindView(R.id.examSearchResult_titleBar)
    MyTitleBar examSearchResultTitleBar;

    @BindView(R.id.sv_a)
    SectionView svA;
    @BindView(R.id.sv_b)
    SectionView svB;
    @BindView(R.id.sv_c)
    SectionView svC;
    @BindView(R.id.sv_d)
    SectionView svD;
    @BindView(R.id.sv_e)
    SectionView svE;
    @BindView(R.id.tv_correctSection)
    TextView tvCorrectSection;
    @BindView(R.id.tv_answerText)
    TextView tvAnswerText;
    @BindView(R.id.ll_answer)
    LinearLayout llAnswer;
    @BindView(R.id.exam_title)
    TextView examTitle;
    private Examination examination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_search_result);
        ButterKnife.bind(this);

        initUI();


    }


    public void initUI() {

        examination = getIntent().getParcelableExtra("exam");



        examSearchResultTitleBar.getActivityForFinish(this);
        examSearchResultTitleBar.setTitle(examination.getSortType());

        examTitle.setText(examination.getTitle());
        svA.setSectionText(examination.getSectionA());
        svB.setSectionText(examination.getSectionB());
        svC.setSectionText(examination.getSectionC());
        svD.setSectionText(examination.getSectionD());
        svE.setSectionText(examination.getSectionE());

    }
}
