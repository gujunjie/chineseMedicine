package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abc.chinesemedicine.greendao.StudyTimeLineDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.StudyTimeLineRecyclerViewAdapter;
import bean.StudyTimeLine;
import bean.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import util.DataBaseUtil;

public class StudyTimeLineActivity extends AppCompatActivity {

    @BindView(R.id.studyTimeLine_titleBar)
    MyTitleBar studyTimeLineTitleBar;
    @BindView(R.id.rv_studyTimeLine)
    RecyclerView rvStudyTimeLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_time_line);
        ButterKnife.bind(this);

        initUI();


    }


    public void initUI() {
        studyTimeLineTitleBar.getActivityForFinish(this);


        Observable.create(new ObservableOnSubscribe<List<StudyTimeLine>>() {
            @Override
            public void subscribe(ObservableEmitter<List<StudyTimeLine>> e) {
                StudyTimeLineDao dao=MyApplication.getDaoSession().getStudyTimeLineDao();
                User user= DataBaseUtil.getUser(StudyTimeLineActivity.this);
                List<StudyTimeLine> timeLineList=dao.queryBuilder().where(StudyTimeLineDao.Properties.UserId.eq(user.getId())).list();
                e.onNext(timeLineList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StudyTimeLine>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<StudyTimeLine> value) {
                        List<StudyTimeLine> list=value;
                        Collections.reverse(list);
                        StudyTimeLineRecyclerViewAdapter adapter=new StudyTimeLineRecyclerViewAdapter(StudyTimeLineActivity.this,list);
                        LinearLayoutManager manager=new LinearLayoutManager(StudyTimeLineActivity.this);

                        rvStudyTimeLine.setLayoutManager(manager);
                        rvStudyTimeLine.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }


}
