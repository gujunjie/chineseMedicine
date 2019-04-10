package model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.greendao.CollectionDao;
import com.example.abc.chinesemedicine.greendao.ErrorExaminationDao;
import com.example.abc.chinesemedicine.greendao.ExaminationDao;

import java.util.List;

import bean.Collection;
import bean.ErrorExamination;
import bean.Examination;
import bean.User;
import contract.ExamContract;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import util.DataBaseUtil;
import util.SharePreferenceUtil;

public class ExamModel implements ExamContract.ExamModel {

    private boolean isSaveBefore=false;//之前是否存过相同的题目


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


        User user= DataBaseUtil.getUser(context);

        ErrorExaminationDao dao=MyApplication.getDaoSession().getErrorExaminationDao();

        List<ErrorExamination> errorExaminationList=dao.queryBuilder().where(ErrorExaminationDao.Properties.UserId.eq(user.getId())).list();



        if(errorExaminationList.size()==0)
        {
            saveErrorExamInDataBase(examination,user,dao);
            //Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();
        }else
        {
            for(int i=0;i<errorExaminationList.size();i++)
            {
                if(errorExaminationList.get(i).getExamId().longValue()==examination.getId().longValue())
                {
                    isSaveBefore=true;
                    //Toast.makeText(context,"重复保存",Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if(!isSaveBefore)
            {
                saveErrorExamInDataBase(examination,user,dao);
                //Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();
            }

        }
    }



    public void saveErrorExamInDataBase(Examination examination,User user,ErrorExaminationDao dao)
    {
        ErrorExamination errorExamination=new ErrorExamination();
        errorExamination.setTitle(examination.getTitle());
        errorExamination.setSortType(examination.getSortType());
        errorExamination.setSectionA(examination.getSectionA());
        errorExamination.setSectionB(examination.getSectionB());
        errorExamination.setSectionC(examination.getSectionC());
        errorExamination.setSectionD(examination.getSectionD());
        errorExamination.setSectionE(examination.getSectionE());
        errorExamination.setCorrectSection(examination.getCorrectSection());
        if(examination.getAnswer()!=null) {
            errorExamination.setAnswer(examination.getAnswer());
        }
        errorExamination.setExamId(examination.getId());
        errorExamination.setUserId(user.getId());

        dao.insert(errorExamination);
    }


    @Override
    public void saveCollection(final Examination examination, final Context context, final ExamContract.onSaveCollectionListener listener) {


        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {

                boolean isSave=false;
                Long id=examination.getId();

                String sortType=examination.getSortType();

                User user = DataBaseUtil.getUser(context);

                CollectionDao collectionDao = MyApplication.getDaoSession().getCollectionDao();
                List<Collection> collectionList = collectionDao.queryBuilder().where(CollectionDao.Properties.UserId.eq(user.getId())).list();

                if(collectionList.size()==0)
                {
                   saveCollectionInDataBase(collectionDao,user,id,sortType);
                   isSave=false;
                   e.onNext(isSave);
                }else
                {
                    for(int i=0;i<collectionList.size();i++)
                    {
                        if(collectionList.get(i).getSortType().equals(sortType)&&collectionList.get(i).getOriginId().longValue()==id.longValue())
                        {
                            isSave=true;
                            e.onNext(isSave);
                            break;
                        }
                    }
                    if(!isSave)
                    {
                        saveCollectionInDataBase(collectionDao,user,id,sortType);
                        e.onNext(false);
                    }


                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        listener.onsaveCollectionSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void saveCollectionInDataBase(CollectionDao collectionDao, User user, Long id,String sortType) {
        Collection collection = new Collection();
        collection.setLearningOrExam("exam");
        collection.setSortType(sortType);
        collection.setOriginId(id);
        collection.setUserId(user.getId());
        collectionDao.insert(collection);
    }




}
