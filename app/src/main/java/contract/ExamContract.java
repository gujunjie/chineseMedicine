package contract;

import android.content.Context;

import java.util.List;

import bean.Examination;

public interface ExamContract {


    interface ExamView
    {
         void showExamination(List<Examination> list);//显示题目
    }

    interface ExamModel
    {
        void getExaminationList(String sortType,onGetExaminationListListener listener);
        void saveErrorExamination(Examination examination,Context context);
    }

    interface ExamPresenter
    {
        void getExaminationList(String sortType);
        void saveErrorExamination(Examination examination, Context context);
    }

    interface onGetExaminationListListener
    {
         void onGetExaminationListSuccess(List<Examination> list);
    }

}
