package view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.CacheInterceptor;
import com.example.abc.chinesemedicine.GlideImageLoader;
import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.UserDao;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import bean.LearningProgress;
import bean.TuiJian;
import bean.User;
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
import util.DataBaseUtil;
import util.SharePreferenceUtil;

public class QuestionFragment extends Fragment {



    private Banner banner;

    private PieChart pieChart;

    private LinearLayout ll_myNote;

    private LinearLayout ll_exam;

    private LinearLayout ll_errorExam;

    private LinearLayout ll_searchExam;

    private LinearLayout ll_collection;

    private LinearLayout ll_chartData;

    private TextView tv_lastestLearningData;

    private String lastLearningSubject;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.questionfragment_layout,container,false);


        initFindViewById(view);//获取控件实例

        initBanner(getActivity());//初始化轮播图

        initButton();//初始化按钮

        return view;
    }

    public void initFindViewById(View view)
    {
        banner=(Banner) view.findViewById(R.id.question_banner);
        pieChart=(PieChart)view.findViewById(R.id.pieChart);
        ll_myNote=(LinearLayout) view.findViewById(R.id.ll_myNote);
        ll_exam=(LinearLayout) view.findViewById(R.id.ll_exam);
        ll_errorExam=(LinearLayout) view.findViewById(R.id.ll_errorExam);
        ll_searchExam=(LinearLayout) view.findViewById(R.id.ll_searchExam);
        ll_collection=(LinearLayout) view.findViewById(R.id.ll_collection);
        ll_chartData=(LinearLayout) view.findViewById(R.id.ll_chartData);
        tv_lastestLearningData=(TextView) view.findViewById(R.id.tv_lastestLearningData);

    }

    public void initLastestLearningData()
    {
        String account= SharePreferenceUtil.getLoginAccount(getActivity());

        UserDao dao= MyApplication.getDaoSession().getUserDao();

        List<User> list=dao.queryBuilder().where(UserDao.Properties.Name.eq(account)).list();

        User user=list.get(0);

        lastLearningSubject=user.getLastestLearningSubject();
        String item=user.getLastestLearningItem();

        if(lastLearningSubject==null)
        {
            //如果没有最后一次学习数据就什么都不显示
        }else {
            tv_lastestLearningData.setText(lastLearningSubject+" - - "+item);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        initLastestLearningData();//初始化最后一次学习数据

        initPieChart();//初始化学习进度饼图
    }

    public void initBanner(final Context context)
    {

        //缓存文件
        File cachFile=new File(context.getExternalCacheDir().toString(),"okCache");

        //文件大小
        int cacheSize=10*1024*1024;


        Cache cache=new Cache(cachFile,cacheSize);

        //客户端基本配置
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor(context))
                .addNetworkInterceptor(new CacheInterceptor(context))
                .cache(cache)
                .build();

        //网络请求基本参数
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://gitee.com/gujunjie/jsonServer/raw/master/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TuiJianClient tuiJianClient=retrofit.create(TuiJianClient.class);

        tuiJianClient.getEncyclopediaJson().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TuiJian>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TuiJian value) {
                         List<TuiJian.TuiJianBean> list=value.getTuiJian();
                        //图片
                        List<String> imageList=new ArrayList<>();
                        //标题
                        List<String> titleList=new ArrayList<>();
                        //HTML
                        final List<String> htmlList=new ArrayList<>();
                        for(int i=0;i<list.size();i++)
                        {
                            imageList.add(list.get(i).getImageUrl());
                            titleList.add(list.get(i).getTitle());
                            htmlList.add(list.get(i).getHtml());
                        }
                        //轮播图基本参数设置
                        banner.setImageLoader(new GlideImageLoader())
                                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                                .setIndicatorGravity(BannerConfig.RIGHT)
                                .setDelayTime(2500)
                                .setImages(imageList)
                                .setBannerTitles(titleList)
                                .start();
                        //点击事件
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                String html=htmlList.get(position);
                                Intent intent=new Intent(context, TuiJianWebViewActivity.class);
                                intent.putExtra("html",html);
                                context.startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });






    }


    public void initPieChart()
    {
        pieChart.setUsePercentValues(false);
      pieChart.getDescription().setEnabled(false);

      float learnedPercent=0;
      float unLearnedPercent=100;


      if(lastLearningSubject==null)
      {
          pieChart.setCenterText(new SpannableString("0%"));
      }else
      {
          List<LearningProgress> list=DataBaseUtil.getLearningProgressList(getActivity());

          switch (lastLearningSubject)
          {
              case "中药学":
                learnedPercent=list.get(0).getLastLearningPercent();
                  break;
              case "方剂学":
                  learnedPercent=list.get(1).getLastLearningPercent();
                  break;
              case "中成药":
                  learnedPercent=list.get(2).getLastLearningPercent();
                  break;
              case "经典医书":
                  learnedPercent=list.get(3).getLastLearningPercent();
                  break;
              case "针灸学":
                  learnedPercent=list.get(4).getLastLearningPercent();
                  break;
          }

          unLearnedPercent=100-learnedPercent;

          DecimalFormat df=new DecimalFormat("0");
          pieChart.setCenterText(new SpannableString(df.format(learnedPercent)+"%"));
      }




       //设置中间文字
        pieChart.setDrawCenterText(true);
      // pieChart.setCenterText(new SpannableString("70%"));
       pieChart.setCenterTextSize(20);
       pieChart.setCenterTextColor(R.color.colorAccent);

       //饼图中间的圆
       pieChart.setDrawHoleEnabled(true);
       pieChart.setHoleColor(Color.WHITE);


       //设置中间透明圆
       pieChart.setTransparentCircleColor(Color.WHITE);
       pieChart.setTransparentCircleAlpha(110);


       //设置中间圆以及透明圆半径
       pieChart.setHoleRadius(75f);
       pieChart.setTransparentCircleRadius(80f);


        //饼图数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(learnedPercent));
        entries.add(new PieEntry(unLearnedPercent));

        //不设置图例
        pieChart.getLegend().setEnabled(false);


        //设置数据
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);//每个区域之间的间隔
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(false);

        pieChart.setData(data);
        //刷新
        pieChart.invalidate();





    }

    public void initButton()
    {
        ll_myNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MyNoteListActivity.class);
                startActivity(intent);
            }
        });

        ll_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ExamListActivity.class);
                startActivity(intent);
            }
        });

        ll_errorExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ErrorExaminationListActivity.class);
                startActivity(intent);
            }
        });

        ll_searchExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ll_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CollectionListActivity.class);
                startActivity(intent);
            }
        });

        ll_chartData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ChartDataActivity.class);
                startActivity(intent);
            }
        });
    }




}
