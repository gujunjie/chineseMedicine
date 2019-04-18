package customview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.CacheInterceptor;
import view.MoreActivity;
import com.example.abc.chinesemedicine.R;

import java.io.File;

import adapter.TuiJianRecyclerViewAdapter;
import bean.TuiJian;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.TuiJianClient;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TuiJianView extends RelativeLayout {


    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rv_tuijian)
    RecyclerView rvTuijian;

    public TuiJianView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tuijianview_layout, this);
        ButterKnife.bind(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TuiJianView);
        String sortText = typedArray.getString(R.styleable.TuiJianView_sortText);
        setsortText(sortText);
        typedArray.recycle();
    }

    public void setsortText(String sortText)
    {
        tvSort.setText(sortText);
    }

    public void setRecyclerViewData(final Context context, String sortType, final int itemCount)
    {

        //新建缓存文件
        File cachFile=new File(context.getExternalCacheDir().toString(),"okCache");

        //10M大小
        int cacheSize=10*1024*1024;//m kb b

        //新建httpclient
        Cache cache=new Cache(cachFile,cacheSize);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor(context))
                .addNetworkInterceptor(new CacheInterceptor(context))
                .cache(cache)
                .build();

        //新建retrofit对象，配置基本参数
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://gitee.com/gujunjie/jsonServer/raw/master/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TuiJianClient tuiJianClient=retrofit.create(TuiJianClient.class);

        switch (sortType)
        {
            case "health":
                //养生类推文，获取json数据并解析json实体类
                tuiJianClient.gethealthJson().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TuiJian>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TuiJian value) {

                                //将解析好的json类对象数据链接到适配器显示出来
                                TuiJianRecyclerViewAdapter adapter=new TuiJianRecyclerViewAdapter(context,value.getTuiJian(),itemCount);
                                LinearLayoutManager manager=new LinearLayoutManager(context);
                                //recyclerview嵌套scrollview滑动冲突问题，加入下面两行代码解决
                                //保证滑动时自身大小不变
                                rvTuijian.setHasFixedSize(true);
                                //移除自身滑动特性
                                rvTuijian.setNestedScrollingEnabled(false);
                                rvTuijian.setLayoutManager(manager);
                                rvTuijian.setAdapter(adapter);

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;

            case "medicineDiet":
                //药膳类推文，获取json数据并解析json实体类
                tuiJianClient.getmedicineDietJson().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TuiJian>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TuiJian value) {
                                TuiJianRecyclerViewAdapter adapter=new TuiJianRecyclerViewAdapter(context,value.getTuiJian(),itemCount);
                                LinearLayoutManager manager=new LinearLayoutManager(context);
                                rvTuijian.setHasFixedSize(true);
                                rvTuijian.setNestedScrollingEnabled(false);
                                rvTuijian.setLayoutManager(manager);
                                rvTuijian.setAdapter(adapter);

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case "prescription":
                //药方类推文，获取json数据并解析json实体类
                tuiJianClient.getprescriptionJson().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TuiJian>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TuiJian value) {
                                TuiJianRecyclerViewAdapter adapter=new TuiJianRecyclerViewAdapter(context,value.getTuiJian(),itemCount);
                                LinearLayoutManager manager=new LinearLayoutManager(context);
                                rvTuijian.setHasFixedSize(true);
                                rvTuijian.setNestedScrollingEnabled(false);
                                rvTuijian.setLayoutManager(manager);
                                rvTuijian.setAdapter(adapter);

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }

    public void setTvMoreOnClickListener(final Context context, final int sortType)
    {
        tvMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MoreActivity.class);
                intent.putExtra("sortType",sortType);
                context.startActivity(intent);
            }
        });
    }








}
