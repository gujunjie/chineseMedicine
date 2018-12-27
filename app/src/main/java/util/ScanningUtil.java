package util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abc.chinesemedicine.AuthService;
import com.example.abc.chinesemedicine.CacheInterceptor;
import com.example.abc.chinesemedicine.MyValueFormatter;
import com.example.abc.chinesemedicine.MyYAxisValueFormatter;
import com.example.abc.chinesemedicine.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import bean.AccessToken;
import bean.ScanningResult;
import contract.TokenClient;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScanningUtil {



    public static String plant(String filePath,String accessToken) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
        try {
            // 本地文件路径

            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
           // String accessToken = "24.2456228ad3169a26c11d6f5ab68f6213.2592000.1547740533.282335-15016276";

            String result = HttpUtil.post(url, accessToken, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public static void scanningPlant(final Context context, final String accessToken, final Uri uri, final HorizontalBarChart barChart, final ImageView ivLoading, final TextView notPlant)
    {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String imagePath=getRealFilePath(context,uri);

                String jsonResult=plant(imagePath,accessToken);
                e.onNext(jsonResult);

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Gson gson=new Gson();

                        ScanningResult result= gson.fromJson(value,ScanningResult.class);

                        List<ScanningResult.ResultBean> resultBeanList=result.getResult();

                        //设置相关属性

                        barChart.setDrawBarShadow(false);
                        barChart.setDrawValueAboveBar(true);
                        barChart.getDescription().setEnabled(false);
                        barChart.setPinchZoom(false);
                        barChart.setDrawGridBackground(false);
                        barChart.setTouchEnabled(false);     //能否点击
                        barChart.setDragEnabled(false);   //能否拖拽
                        barChart.setScaleEnabled(false);  //能否缩放

                        // x轴
                        XAxis xl = barChart.getXAxis();
                        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xl.setTextSize(14);
                        xl.setDrawAxisLine(true);
                        xl.setDrawGridLines(false);

                        List<String>  s=new ArrayList<>();
                        for(int i=0;i<resultBeanList.size();i++)
                        {
                            s.add(resultBeanList.get(resultBeanList.size()-i-1).getName());
                        }


                        xl.setValueFormatter(new MyYAxisValueFormatter(s));


                        //y轴
                        YAxis yl = barChart.getAxisLeft();
                        yl.setEnabled(false);
                        yl.setAxisMinimum(0f);

                        //y轴
                        YAxis yr = barChart.getAxisRight();
                        yr.setEnabled(false);

                        barChart.setVisibility(View.VISIBLE);
                        ivLoading.setVisibility(View.GONE);
                        if(resultBeanList.size()>1)
                        {
                            xl.setLabelCount(5);
                            //设置数据
                            setData(context,barChart,resultBeanList);

                        }else
                        {
                            barChart.setVisibility(View.GONE);
                            notPlant.setVisibility(View.VISIBLE);

                        }

                        barChart.setFitBars(true);
                        barChart.animateY(1500,Easing.Linear);
                        Legend l = barChart.getLegend();
                        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                        l.setDrawInside(false);
                        l.setFormSize(8f);
                        l.setXEntrySpace(4f);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public static void setData(Context context, HorizontalBarChart barChart, List<ScanningResult.ResultBean> resultBeanList) {


        List<BarEntry> list=new ArrayList<>();


        for(int i=0;i<resultBeanList.size();i++)
        {
            list.add(new BarEntry(resultBeanList.size()-i-1,(int)(resultBeanList.get(i).getScore()*100)));
        }



        BarDataSet dataSet=new BarDataSet(list,"智能分析结果仅供参考,数值越大可信度越高");

        int[] colors={R.color.colorGreen, R.color.colorYellow,R.color.colorOrange,R.color.colorBlue,R.color.colorRed};

        dataSet.setColors(colors,context);
        dataSet.setValueTextSize(14);
        dataSet.setValueFormatter(new MyValueFormatter());


        BarData data = new BarData(dataSet);
        data.setValueTextSize(10f);
        barChart.setData(data);
    }



}
