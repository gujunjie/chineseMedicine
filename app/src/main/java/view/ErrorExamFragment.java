package view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;

import org.greenrobot.eventbus.EventBus;

import bean.ErrorExamination;
import bean.Examination;
import bean.MessageEvent;
import customview.SectionView;

public class ErrorExamFragment extends Fragment {

    private TextView tv_title;

    private SectionView sv_a;
    private SectionView sv_b;
    private SectionView sv_c;
    private SectionView sv_d;
    private SectionView sv_e;


    private LinearLayout ll_answer;

    private TextView tv_correctSection;
    private TextView tv_answerText;


    private ErrorExamination errorExamination;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.examfragment_layout,container,false);
        initFindViewById(view);

        initUI();
        setOnclickListener(errorExamination);


        return view;
    }

    public void initFindViewById(View view)
    {
        tv_title=(TextView)view.findViewById(R.id.tv_title);
        tv_correctSection=(TextView)view.findViewById(R.id.tv_correctSection);
        tv_answerText=(TextView)view.findViewById(R.id.tv_answerText);
        sv_a=(SectionView)view.findViewById(R.id.sv_a);
        sv_b=(SectionView)view.findViewById(R.id.sv_b);
        sv_c=(SectionView)view.findViewById(R.id.sv_c);
        sv_d=(SectionView)view.findViewById(R.id.sv_d);
        sv_e=(SectionView)view.findViewById(R.id.sv_e);
        ll_answer=(LinearLayout) view.findViewById(R.id.ll_answer);

    }

    public void initUI()
    {
        tv_title.setText(errorExamination.getTitle());
        sv_a.setSectionText(errorExamination.getSectionA());
        sv_b.setSectionText(errorExamination.getSectionB());
        sv_c.setSectionText(errorExamination.getSectionC());
        sv_d.setSectionText(errorExamination.getSectionD());
        sv_e.setSectionText(errorExamination.getSectionE());

    }

    public void setData(ErrorExamination errorExamination)
    {
        this.errorExamination=errorExamination;
    }


    public void setOnclickListener(final ErrorExamination errorExamination)
    {
        sv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compareToAnswer("A",errorExamination);
            }
        });
        sv_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compareToAnswer("B",errorExamination);
            }
        });
        sv_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compareToAnswer("C",errorExamination);
            }
        });
        sv_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compareToAnswer("D",errorExamination);
            }
        });
        sv_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compareToAnswer("E",errorExamination);
            }
        });
    }//给选项加事件侦听

    public void compareToAnswer(String choice,ErrorExamination errorExamination)
    {
        if(choice.equals(errorExamination.getCorrectSection()))
        {
            //答对就显示正确
            switch (choice)
            {
                case "A":
                    sv_a.setSectionIcon(R.drawable.correct);
                    sv_a.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "B":
                    sv_b.setSectionIcon(R.drawable.correct);
                    sv_b.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "C":
                    sv_c.setSectionIcon(R.drawable.correct);
                    sv_c.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "D":
                    sv_d.setSectionIcon(R.drawable.correct);
                    sv_d.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "E":
                    sv_e.setSectionIcon(R.drawable.correct);
                    sv_e.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
            }
            EventBus.getDefault().post(new MessageEvent(true));//用户答对，发送给activity更新UI



        }else {

            //答错就显示正确答案，并且提示错误，以及显示解析
            switch (errorExamination.getCorrectSection())
            {
                case "A":
                    sv_a.setSectionIcon(R.drawable.correct);
                    sv_a.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "B":
                    sv_b.setSectionIcon(R.drawable.correct);
                    sv_b.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "C":
                    sv_c.setSectionIcon(R.drawable.correct);
                    sv_c.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "D":
                    sv_d.setSectionIcon(R.drawable.correct);
                    sv_d.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
                case "E":
                    sv_e.setSectionIcon(R.drawable.correct);
                    sv_e.setSectionTextColor(getResources().getColor(R.color.colorCorrect));
                    break;
            }
            switch (choice)
            {
                case "A":
                    sv_a.setSectionIcon(R.drawable.error);
                    sv_a.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
                case "B":
                    sv_b.setSectionIcon(R.drawable.error);
                    sv_b.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
                case "C":
                    sv_c.setSectionIcon(R.drawable.error);
                    sv_c.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
                case "D":
                    sv_d.setSectionIcon(R.drawable.error);
                    sv_d.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
                case "E":
                    sv_e.setSectionIcon(R.drawable.error);
                    sv_e.setSectionTextColor(getResources().getColor(R.color.colorError));
                    break;
            }
            EventBus.getDefault().post(new MessageEvent(false));//用户答错，发送给activity更新UI

            ll_answer.setVisibility(View.VISIBLE);
            tv_correctSection.setText("答案 "+errorExamination.getCorrectSection());
            if(errorExamination.getAnswer()==null)
            {

            }else
            {
                tv_answerText.setText(errorExamination.getAnswer());
            }//显示正确答案与解析


        }

        sv_a.setOnClickListener(null);
        sv_b.setOnClickListener(null);
        sv_c.setOnClickListener(null);
        sv_d.setOnClickListener(null);
        sv_e.setOnClickListener(null);//解除选项的事件侦听
    }//对比答案

}
