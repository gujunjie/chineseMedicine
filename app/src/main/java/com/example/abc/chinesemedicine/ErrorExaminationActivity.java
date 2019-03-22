package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.ErrorExamRecyclerViewAdapter;
import bean.ErrorExamSyllabus;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;

public class ErrorExaminationActivity extends AppCompatActivity {

    @BindView(R.id.tv_errorNumber)
    TextView tvErrorNumber;
    @BindView(R.id.rv_errorExamination)
    RecyclerView rvErrorExamination;
    @BindView(R.id.errorExam_titleBar)
    MyTitleBar errorExamTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_examination);
        ButterKnife.bind(this);

        errorExamTitleBar.getActivityForFinish(this);

        initUI();
    }

    public void initUI() {

        List<ErrorExamSyllabus> list = new ArrayList<>();
        ErrorExamSyllabus syllabus1 = new ErrorExamSyllabus();
        syllabus1.setSortType("中药学专业知识(一)");
        syllabus1.setIcon(R.drawable.number1);
        syllabus1.setErrorNumber(0);


        ErrorExamSyllabus syllabus2 = new ErrorExamSyllabus();
        syllabus2.setSortType("中药学专业知识(二)");
        syllabus2.setIcon(R.drawable.number2);
        syllabus2.setErrorNumber(0);

        ErrorExamSyllabus syllabus3 = new ErrorExamSyllabus();
        syllabus3.setSortType("中药学综合知识与技能");
        syllabus3.setIcon(R.drawable.number3);
        syllabus3.setErrorNumber(0);

        ErrorExamSyllabus syllabus4 = new ErrorExamSyllabus();
        syllabus4.setSortType("药学专业知识(一)");
        syllabus4.setIcon(R.drawable.number4);
        syllabus4.setErrorNumber(0);

        ErrorExamSyllabus syllabus5 = new ErrorExamSyllabus();
        syllabus5.setSortType("药学专业知识(二)");
        syllabus5.setIcon(R.drawable.number5);
        syllabus5.setErrorNumber(0);

        ErrorExamSyllabus syllabus6 = new ErrorExamSyllabus();
        syllabus6.setSortType("药学综合知识与技能");
        syllabus6.setIcon(R.drawable.number6);
        syllabus6.setErrorNumber(0);

        ErrorExamSyllabus syllabus7 = new ErrorExamSyllabus();
        syllabus7.setSortType("药事管理与法规");
        syllabus7.setIcon(R.drawable.number7);
        syllabus7.setErrorNumber(0);

        list.add(syllabus1);
        list.add(syllabus2);
        list.add(syllabus3);
        list.add(syllabus4);
        list.add(syllabus5);
        list.add(syllabus6);
        list.add(syllabus7);

        ErrorExamRecyclerViewAdapter adapter = new ErrorExamRecyclerViewAdapter(list, this);

        rvErrorExamination.setLayoutManager(new LinearLayoutManager(this));
        rvErrorExamination.setAdapter(adapter);


    }
}
