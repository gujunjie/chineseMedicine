package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugSecondCategoryDao;

import java.util.List;

import adapter.SecondCategoryRecyclerViewAdapter;
import bean.ChinesePatentDrugSecondCategory;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SecondCategoryActivity extends AppCompatActivity {

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.rv_secondCategory)
    RecyclerView rvSecondCategory;

    private String firstCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_category);
        ButterKnife.bind(this);

        firstCategory=getIntent().getStringExtra("firstCategory");
        titleBar.setTitle(firstCategory);
        titleBar.getActivityForFinish(this);

        initSecondCategory();

    }

    public void initSecondCategory()
    {

        Observable.create(new ObservableOnSubscribe<List<ChinesePatentDrugSecondCategory>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ChinesePatentDrugSecondCategory>> e) {
                ChinesePatentDrugSecondCategoryDao dao=MyApplication.getDaoSession().getChinesePatentDrugSecondCategoryDao();

                List<ChinesePatentDrugSecondCategory> list=dao.queryBuilder().where(ChinesePatentDrugSecondCategoryDao.Properties.FirstCategoryName.eq(firstCategory)).list();
                e.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ChinesePatentDrugSecondCategory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ChinesePatentDrugSecondCategory> value) {
                        SecondCategoryRecyclerViewAdapter adapter=new SecondCategoryRecyclerViewAdapter(SecondCategoryActivity.this,value);
                        LinearLayoutManager manager=new LinearLayoutManager(SecondCategoryActivity.this);
                       rvSecondCategory.setAdapter(adapter);
                       rvSecondCategory.setLayoutManager(manager);
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
