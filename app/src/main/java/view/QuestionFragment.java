package view;

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

import com.example.abc.chinesemedicine.ErrorExaminationActivity;
import com.example.abc.chinesemedicine.ExamListActivity;
import com.example.abc.chinesemedicine.GlideImageLoader;
import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.MyNoteListActivity;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.UserDao;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import bean.LearningProgress;
import bean.User;
import util.DataBaseUtil;
import util.SharePreferenceUtil;

public class QuestionFragment extends Fragment {



    private Banner banner;

    private PieChart pieChart;

    private LinearLayout ll_myNote;

    private LinearLayout ll_exam;

    private LinearLayout ll_errorExam;

    private TextView tv_lastestLearningData;

    private String lastLearningSubject;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.questionfragment_layout,container,false);


        initFindViewById(view);//获取控件实例

        initBanner();//初始化轮播图

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

    public void initBanner()
    {
        List<String> list=new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548858163&di=a565eee8a44c26f1601bd04b3acbea8a&imgtype=jpg&er=1&src=http%3A%2F%2Fphoto.orsoon.com%2F180323%2F180323_34%2F1qJhbgW5Lu_small.jpg");
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2790209824,4284414563&fm=200&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548264246865&di=4bb859d9f4de9d2a9448f21f37600fef&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Ffaedab64034f78f04938817c73310a55b3191c09.jpg");
        banner.setImageLoader(new GlideImageLoader()).setIndicatorGravity(BannerConfig.RIGHT).setDelayTime(2000).setImages(list).start();

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
                Intent intent=new Intent(getActivity(), ErrorExaminationActivity.class);
                startActivity(intent);
            }
        });
    }




}
