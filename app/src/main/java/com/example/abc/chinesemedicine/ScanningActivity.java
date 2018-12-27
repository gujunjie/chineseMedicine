package com.example.abc.chinesemedicine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.gyf.barlibrary.ImmersionBar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import bean.AccessToken;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contract.TokenClient;
import customview.MyTitleBar;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import util.ScanningUtil;

public class ScanningActivity extends AppCompatActivity {

    @BindView(R.id.titlebar)
    MyTitleBar titlebar;
    @BindView(R.id.iv_loading)
    ImageView ivLoading;
    @BindView(R.id.iv_plant)
    ImageView ivPlant;
    @BindView(R.id.barChart)
    HorizontalBarChart barChart;
    @BindView(R.id.tv_showNotPlant)
    TextView tvShowNotPlant;

    private Uri imageUri;//上传图片Uri

    private String accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        ButterKnife.bind(this);
        getAccessToken(this);//获取token
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.colorAccent).init();

        titlebar.getActivityForFinish(this);

        Glide.with(this).load(R.mipmap.sacnning_background).centerCrop().into(ivPlant);



    }

    @OnClick(R.id.iv_plant)
    public void onViewClicked() {
        CropImage.startPickImageActivity(ScanningActivity.this);//开启选择图片
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                Glide.with(ScanningActivity.this).load(imageUri)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE).into(ivPlant);//禁用磁盘与内存缓存
                ivLoading.setVisibility(View.VISIBLE);
                Glide.with(this).load(R.mipmap.loading).into(ivLoading);
                barChart.setVisibility(View.GONE);
                tvShowNotPlant.setVisibility(View.GONE);
                ScanningUtil.scanningPlant(this,accessToken, imageUri, barChart, ivLoading,tvShowNotPlant);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }//裁剪完回调

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = CropImage.getPickImageResultUri(this, data);
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(ScanningActivity.this);
        }//选择完回调


    }



    public void getAccessToken(Context context)
    {
        File cachFile=new File(context.getExternalCacheDir().toString(),"tokenCache");

        int cacheSize=10*1024*1024;

        Cache cache=new Cache(cachFile,cacheSize);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor(context))
                .addNetworkInterceptor(new CacheInterceptor(context))
                .cache(cache)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://aip.baidubce.com/oauth/2.0/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TokenClient tokenClient=retrofit.create(TokenClient.class);
        tokenClient.getAccessToken()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccessToken>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AccessToken value) {
                        accessToken=value.getAccess_token();
                        //Log.d("text", accessToken);
                        //Toast.makeText(ScanningActivity.this,"accesstoken="+accessToken,Toast.LENGTH_SHORT).show();
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

