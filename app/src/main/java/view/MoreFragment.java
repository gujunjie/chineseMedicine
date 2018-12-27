package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc.chinesemedicine.CacheInterceptor;
import com.example.abc.chinesemedicine.R;

import java.io.File;

import adapter.MoreRecyclerViewAdapter;
import adapter.TuiJianRecyclerViewAdapter;
import bean.TuiJian;
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

public class MoreFragment extends Fragment {

    private RecyclerView rv_more;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.morefragement_layout,container,false);
        rv_more=(RecyclerView)view.findViewById(R.id.rv_more);

        getTuiJianData(getActivity(),getArguments().getString("sortType"));

        return view;
    }

    public void getTuiJianData(Context context,String sortType)
    {
        File cachFile=new File(context.getExternalCacheDir().toString(),"okCache");

        int cacheSize=10*1024*1024;

        Cache cache=new Cache(cachFile,cacheSize);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor(context))
                .addNetworkInterceptor(new CacheInterceptor(context))
                .cache(cache)
                .build();

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
                tuiJianClient.gethealthJson().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TuiJian>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TuiJian value) {
                                MoreRecyclerViewAdapter adapter=new MoreRecyclerViewAdapter(getActivity(),value.getTuiJian());
                                LinearLayoutManager manager=new LinearLayoutManager(getActivity());
                                rv_more.setLayoutManager(manager);
                                rv_more.setAdapter(adapter);

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
                tuiJianClient.getmedicineDietJson().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TuiJian>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TuiJian value) {
                                MoreRecyclerViewAdapter adapter=new MoreRecyclerViewAdapter(getActivity(),value.getTuiJian());
                                LinearLayoutManager manager=new LinearLayoutManager(getActivity());
                                rv_more.setLayoutManager(manager);
                                rv_more.setAdapter(adapter);

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
                tuiJianClient.getprescriptionJson().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TuiJian>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TuiJian value) {
                                MoreRecyclerViewAdapter adapter=new MoreRecyclerViewAdapter(getActivity(),value.getTuiJian());
                                LinearLayoutManager manager=new LinearLayoutManager(getActivity());
                                rv_more.setLayoutManager(manager);
                                rv_more.setAdapter(adapter);

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
}
