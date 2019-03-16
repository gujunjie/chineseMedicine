package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.ExamRecyclerViewAdapter;
import bean.ExamSyllabus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import customview.MyTitleBarWhite;

public class ExamListActivity extends AppCompatActivity {

    @BindView(R.id.rv_exam)
    RecyclerView rvExam;
    @BindView(R.id.examList_titleBar)
    MyTitleBarWhite examListTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);
        ButterKnife.bind(this);

        examListTitleBar.getActivityForFinish(this);
        initUI();
    }


    public void initUI() {
        List<ExamSyllabus> list = new ArrayList<>();
        ExamSyllabus examSyllabus1 = new ExamSyllabus();
        examSyllabus1.setTitle("中药学专业知识(一)");
        examSyllabus1.setSubTitle("中药学是研究中药的基本理论和临床应用的学科，是中医药各专业的基础学科之一。内容包括中药、中药学的概念，中药的起源和发展；");
        examSyllabus1.setExamIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1552055016&di=2c7b680cffa6e3ecb84c6d85f93b5e2c&src=http://bpic.ooopic.com/16/55/21/16552143-470fb7379ff8294e7d5f97f4b4a96cde-0.jpg");
        ExamSyllabus examSyllabus2 = new ExamSyllabus();
        examSyllabus2.setTitle("中药学专业知识(二)");
        examSyllabus2.setSubTitle("中药学是研究中药的基本理论和临床应用的学科，是中医药各专业的基础学科之一。内容包括中药、中药学的概念，中药的起源和发展；");
        examSyllabus2.setExamIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1552055876&di=c4b6bf5b49884a990c7a906d50274769&src=http://bpic.ooopic.com/16/57/92/16579278-713c0dfb7a5b91547b2068b932eaf2c9-0.jpg");
        ExamSyllabus examSyllabus3 = new ExamSyllabus();
        examSyllabus3.setTitle("中药学综合知识与技能");
        examSyllabus3.setSubTitle("中药学是研究中药的基本理论和临床应用的学科，是中医药各专业的基础学科之一。内容包括中药、中药学的概念，中药的起源和发展；");
        examSyllabus3.setExamIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552660712&di=07431c1801a15eca7773235c8e182944&imgtype=jpg&er=1&src=http%3A%2F%2Fbpic.ooopic.com%2F16%2F45%2F66%2F16456673-9282f3922b55348647dc8e9015f05eaf-0.jpg");
        ExamSyllabus examSyllabus4 =new ExamSyllabus();
        examSyllabus4.setTitle("药学专业知识(一)");
        examSyllabus4.setSubTitle("药学专业（Pharmacy）是培养具备药学学科基本理论、基本知识和实验技能，能在药品生产、检验、流通、使用和研究与开发领域从事鉴定、药物设计、一般药物制剂及临床合理用药等方面工作的高级科学技术人才的学科。");
        examSyllabus4.setExamIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1552055978&di=e61fe890ba217c88826a7e0b8f9c2454&src=http://homesitetask.zbjimg.com/homesite/task/a2cc7cd98d1001e97fc80a06b20e7bec55e797c6.jpg/origine/feed4f31-794f-4d82-92fa-c3ae7d8a50f8");
        ExamSyllabus examSyllabus5 =new ExamSyllabus();
        examSyllabus5.setTitle("药学专业知识(二)");
        examSyllabus5.setSubTitle("药学专业（Pharmacy）是培养具备药学学科基本理论、基本知识和实验技能，能在药品生产、检验、流通、使用和研究与开发领域从事鉴定、药物设计、一般药物制剂及临床合理用药等方面工作的高级科学技术人才的学科。");
        examSyllabus5.setExamIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1552056036&di=ef914f9ce47bc2463001c063128ff181&src=http://imgsrc.baidu.com/imgad/pic/item/730e0cf3d7ca7bcbbe59760bb4096b63f624a8f0.jpg");
        ExamSyllabus examSyllabus6 =new ExamSyllabus();
        examSyllabus6.setTitle("药学综合知识与技能");
        examSyllabus6.setSubTitle("药学专业（Pharmacy）是培养具备药学学科基本理论、基本知识和实验技能，能在药品生产、检验、流通、使用和研究与开发领域从事鉴定、药物设计、一般药物制剂及临床合理用药等方面工作的高级科学技术人才的学科。");
        examSyllabus6.setExamIcon("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=656020695,2085191856&fm=27&gp=0.jpg");

        ExamSyllabus examSyllabus7 =new ExamSyllabus();
        examSyllabus7.setTitle("药事管理与法规");
        examSyllabus7.setSubTitle("药学专业（Pharmacy）是培养具备药学学科基本理论、基本知识和实验技能，能在药品生产、检验、流通、使用和研究与开发领域从事鉴定、药物设计、一般药物制剂及临床合理用药等方面工作的高级科学技术人才的学科。");
        examSyllabus7.setExamIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1552056036&di=7b1a12ccbf03177113d8e4b0cad5d19e&src=http://pic.9ht.com/up/2017-7/15009783652403333.jpg");

        list.add(examSyllabus1);
        list.add(examSyllabus2);
        list.add(examSyllabus3);
        list.add(examSyllabus4);
        list.add(examSyllabus5);
        list.add(examSyllabus6);
        list.add(examSyllabus7);

        ExamRecyclerViewAdapter adapter = new ExamRecyclerViewAdapter(list, this);
        rvExam.setAdapter(adapter);
        rvExam.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
