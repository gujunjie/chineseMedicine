package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import bean.Examination;
import bean.MessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import customview.MyTitleBar;
import customview.SectionView;

public class ExamSearchResultActivity extends AppCompatActivity implements View.OnClickListener {


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

        svA.setOnClickListener(this);
        svB.setOnClickListener(this);
        svC.setOnClickListener(this);
        svD.setOnClickListener(this);
        svE.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.sv_a:
                compareToAnswer("A",examination);
                break;
            case R.id.sv_b:
                compareToAnswer("B",examination);
                break;
            case R.id.sv_c:
                compareToAnswer("C",examination);
                break;
            case R.id.sv_d:
                compareToAnswer("D",examination);
                break;
            case R.id.sv_e:
                compareToAnswer("E",examination);
                break;

        }

    }

    public void compareToAnswer(String choice,Examination examination)
    {
        if(choice.equals(examination.getCorrectSection()))
        {
            //答对就显示正确
            switch (choice)
            {
                case "A":
                    svA.setSectionIcon(R.drawable.correct);
                    svA.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "B":
                    svB.setSectionIcon(R.drawable.correct);
                    svB.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "C":
                    svC.setSectionIcon(R.drawable.correct);
                    svC.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "D":
                    svD.setSectionIcon(R.drawable.correct);
                    svD.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "E":
                    svE.setSectionIcon(R.drawable.correct);
                    svE.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
            }

        }else {

            //答错就显示正确答案，并且提示错误，以及显示解析
            switch (examination.getCorrectSection())
            {
                case "A":
                    svA.setSectionIcon(R.drawable.correct);
                    svA.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "B":
                    svB.setSectionIcon(R.drawable.correct);
                    svB.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "C":
                    svC.setSectionIcon(R.drawable.correct);
                    svC.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "D":
                    svD.setSectionIcon(R.drawable.correct);
                    svD.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "E":
                    svE.setSectionIcon(R.drawable.correct);
                    svE.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
            }
            switch (choice)
            {
                case "A":
                    svA.setSectionIcon(R.drawable.error);
                    svA.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
                case "B":
                    svB.setSectionIcon(R.drawable.error);
                    svB.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
                case "C":
                    svC.setSectionIcon(R.drawable.error);
                    svC.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
                case "D":
                    svD.setSectionIcon(R.drawable.error);
                    svD.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
                case "E":
                    svE.setSectionIcon(R.drawable.error);
                    svE.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
            }

            llAnswer.setVisibility(View.VISIBLE);
            tvCorrectSection.setText("答案 "+examination.getCorrectSection());
            if(examination.getAnswer()==null)
            {

            }else
            {
                tvAnswerText.setText(examination.getAnswer());
            }//显示正确答案与解析


        }

        svA.setOnClickListener(null);
        svB.setOnClickListener(null);
        svC.setOnClickListener(null);
        svD.setOnClickListener(null);
        svE.setOnClickListener(null);//解除选项的事件侦听
    }//对比答案
}
