package presenter;


import android.content.Context;

import org.w3c.dom.Entity;

import java.util.List;

import base.BasePresenter;
import bean.Examination;
import contract.ExamContract;
import model.ExamModel;


public class ExamPresenter extends BasePresenter<ExamContract.ExamView> implements ExamContract.ExamPresenter,ExamContract.onGetExaminationListListener,ExamContract.onSaveCollectionListener {

    private ExamContract.ExamView examView;

    private ExamContract.ExamModel examModel;

    public ExamPresenter(ExamContract.ExamView examView)
    {
        this.examView=examView;
        examModel=new ExamModel();
    }


    @Override
    public void getExaminationList(String sortType) {
         examModel.getExaminationList(sortType,this);
    }

    @Override
    public void saveErrorExamination(Examination examination, Context context) {
        examModel.saveErrorExamination(examination,context);
    }

    @Override
    public void saveCollection(Examination examination, Context context) {
        examModel.saveCollection(examination,context,this);
    }

    @Override
    public void onGetExaminationListSuccess(List<Examination> list) {
        examView.showExamination(list);
    }

    @Override
    public void onsaveCollectionSuccess(boolean isSaveBefore) {
        examView.showSaveCollectionSuccessOrFailure(isSaveBefore);
    }


}
