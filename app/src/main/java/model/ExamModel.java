package model;

import android.content.Context;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.greendao.ExaminationDao;

import java.util.List;

import bean.Examination;
import contract.ExamContract;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExamModel implements ExamContract.ExamModel {

    private boolean isSaveBefore;//之前是否存过相同的题目


    @Override
    public void getExaminationList(final String sortType, final ExamContract.onGetExaminationListListener listener) {
        Observable.create(new ObservableOnSubscribe<List<Examination>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Examination>> observableEmitter) throws Exception {

                ExaminationDao dao=MyApplication.getDaoSession().getExaminationDao();

                List<Examination> list=dao.queryBuilder().where(ExaminationDao.Properties.SortType.eq(sortType)).list();

                observableEmitter.onNext(list);

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Examination>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(List<Examination> examinations) {
                          listener.onGetExaminationListSuccess(examinations);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void saveErrorExamination(Examination examination, Context context) {



    }


}
