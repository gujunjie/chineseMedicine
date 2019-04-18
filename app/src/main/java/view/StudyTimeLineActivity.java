package view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.StudyTimeLineDao;

import java.util.Collections;
import java.util.List;

import adapter.StudyTimeLineRecyclerViewAdapter;
import bean.StudyTimeLine;
import bean.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import customview.MyTitleBar;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import util.DataBaseUtil;

public class StudyTimeLineActivity extends AppCompatActivity {

    @BindView(R.id.studyTimeLine_titleBar)
    MyTitleBar studyTimeLineTitleBar;
    @BindView(R.id.rv_studyTimeLine)
    RecyclerView rvStudyTimeLine;
    @BindView(R.id.tv_allRecordNumber)
    TextView tvAllRecordNumber;
    @BindView(R.id.tv_showNothing)
    TextView tvShowNothing;

    private int recordNumber=0;



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
                StudyTimeLineDao dao = MyApplication.getDaoSession().getStudyTimeLineDao();
                User user = DataBaseUtil.getUser(StudyTimeLineActivity.this);
                List<StudyTimeLine> timeLineList = dao.queryBuilder().where(StudyTimeLineDao.Properties.UserId.eq(user.getId())).list();
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

                        recordNumber=value.size();

                        tvAllRecordNumber.setText("全部记录(" + recordNumber +")");

                        if(recordNumber!=0)
                        {
                            tvShowNothing.setVisibility(View.GONE);
                            List<StudyTimeLine> list = value;
                            Collections.reverse(list);
                            StudyTimeLineRecyclerViewAdapter adapter = new StudyTimeLineRecyclerViewAdapter(StudyTimeLineActivity.this, list);
                            LinearLayoutManager manager = new LinearLayoutManager(StudyTimeLineActivity.this);
                            rvStudyTimeLine.setLayoutManager(manager);
                            rvStudyTimeLine.setAdapter(adapter);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    @OnClick(R.id.iv_deleteAllRecord)
    public void onViewClicked() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(StudyTimeLineActivity.this);
        dialog.setMessage("确认删除全部学习记录吗？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(recordNumber!=0)
                {
                    User user = DataBaseUtil.getUser(StudyTimeLineActivity.this);
                    StudyTimeLineDao dao = MyApplication.getDaoSession().getStudyTimeLineDao();
                    List<StudyTimeLine> list = dao.queryBuilder().where(StudyTimeLineDao.Properties.UserId.eq(user.getId())).list();

                    for (StudyTimeLine s : list) {
                        dao.delete(s);
                    }
                    rvStudyTimeLine.setVisibility(View.GONE);
                    tvAllRecordNumber.setText("全部记录(0)");
                    recordNumber=0;//记录条数重置为0
                    tvShowNothing.setVisibility(View.VISIBLE);//删除所有记录并设置rv不可见
                }else
                {
                    Snackbar.make(tvAllRecordNumber,"您已经清空了所有记录！",Snackbar.LENGTH_LONG)
                            .setAction("知道了", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();
                }


            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();


    }
}
