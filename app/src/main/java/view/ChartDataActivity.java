package view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abc.chinesemedicine.MyYAxisValueFormatter;
import com.example.abc.chinesemedicine.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import bean.ExamProgress;
import bean.LearningProgress;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;
import util.DataBaseUtil;

public class ChartDataActivity extends AppCompatActivity {

    @BindView(R.id.chartData_titleBar)
    MyTitleBar chartDataTitleBar;
    @BindView(R.id.learningProgressBarChart)
    BarChart learningProgressBarChart;
    @BindView(R.id.examProgressBarChart1)
    BarChart examProgressBarChart1;
    @BindView(R.id.examProgressBarChart2)
    BarChart examProgressBarChart2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_data);
        ButterKnife.bind(this);

        chartDataTitleBar.getActivityForFinish(this);

        initLearningProgressBarChart();

        initExamProgressBarChart1();

        initExamProgressBarChart2();
    }

    public void initLearningProgressBarChart() {


        //条形图
        learningProgressBarChart.setDrawBarShadow(false);
        learningProgressBarChart.setDrawValueAboveBar(true);
        learningProgressBarChart.getDescription().setEnabled(false);
        // 扩展现在只能分别在x轴和y轴
        learningProgressBarChart.setPinchZoom(false);
        //是否显示表格颜色
        learningProgressBarChart.setDrawGridBackground(false);
        learningProgressBarChart.setTouchEnabled(false);     //能否点击


        //x轴
        XAxis xAxis = learningProgressBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        // 只有1天的时间间隔
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(5);
        xAxis.setAxisMaximum(6f);
        xAxis.setAxisMinimum(0f);
        xAxis.setTextSize(12);


        List<String> s = new ArrayList<>();
        s.add("");
        s.add("中药学");
        s.add("方剂学");
        s.add("中成药");
        s.add("经典医书");
        s.add("针灸学");
        xAxis.setValueFormatter(new MyYAxisValueFormatter(s));


        YAxis leftAxis = learningProgressBarChart.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //这个替换setStartAtZero(true)
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);

        YAxis rightAxis = learningProgressBarChart.getAxisRight();
        rightAxis.setEnabled(false);

        // 设置标示，就是那个一组y的value的
        Legend l = learningProgressBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //样式
        l.setForm(Legend.LegendForm.SQUARE);
        //字体
        l.setFormSize(9f);
        //大小
        l.setTextSize(11f);
        l.setXEntrySpace(6f);


        List<LearningProgress> progressList = DataBaseUtil.getLearningProgressList(this);

        //加载数据
        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        for (int i = 0; i < progressList.size(); i++) {
            yVals1.add(new BarEntry(i + 1, progressList.get(i).getLastLearningPercent()));
        }


        //mBarChart.setFitBars(true);
        learningProgressBarChart.animateY(1000, Easing.Linear);

        BarDataSet set1;
        if (learningProgressBarChart.getData() != null &&
                learningProgressBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) learningProgressBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            learningProgressBarChart.getData().notifyDataChanged();
            learningProgressBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "各科学习进度百分比(%)");
            //设置颜色
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(12f);
            data.setBarWidth(0.8f);
            //设置数据
            learningProgressBarChart.setData(data);
        }

    }

    public void initExamProgressBarChart1() {


        //条形图
        examProgressBarChart1.setDrawBarShadow(false);
        examProgressBarChart1.setDrawValueAboveBar(true);
        examProgressBarChart1.getDescription().setEnabled(false);
        // 扩展现在只能分别在x轴和y轴
        examProgressBarChart1.setPinchZoom(false);
        //是否显示表格颜色
        examProgressBarChart1.setDrawGridBackground(false);

        examProgressBarChart1.setTouchEnabled(false);
        //x轴
        XAxis xAxis = examProgressBarChart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        // 只有1天的时间间隔
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(3);
        xAxis.setAxisMaximum(4f);
        xAxis.setAxisMinimum(0f);
        xAxis.setTextSize(12);


        List<String> s = new ArrayList<>();
        s.add("");
        s.add("中药1");
        s.add("中药2");
        s.add("中药知识技能");

        xAxis.setValueFormatter(new MyYAxisValueFormatter(s));


        YAxis leftAxis = examProgressBarChart1.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //这个替换setStartAtZero(true)
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);

        YAxis rightAxis = examProgressBarChart1.getAxisRight();
        rightAxis.setEnabled(false);

        // 设置标示，就是那个一组y的value的
        Legend l = examProgressBarChart1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //样式
        l.setForm(Legend.LegendForm.SQUARE);
        //字体
        l.setFormSize(9f);
        //大小
        l.setTextSize(11f);
        l.setXEntrySpace(6f);


        List<ExamProgress> progressList = DataBaseUtil.getExamProgressList(this);

        //加载数据
        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            yVals1.add(new BarEntry(i + 1, progressList.get(i).getLastExamPercent()));
        }


        //mBarChart.setFitBars(true);
        examProgressBarChart1.animateY(1000, Easing.Linear);

        BarDataSet set1;
        if (examProgressBarChart1.getData() != null &&
                examProgressBarChart1.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) examProgressBarChart1.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            examProgressBarChart1.getData().notifyDataChanged();
            examProgressBarChart1.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "各科测试进度百分比(%)");
            //设置颜色
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(12f);
            data.setBarWidth(0.8f);
            //设置数据
            examProgressBarChart1.setData(data);
        }

    }

    public void initExamProgressBarChart2() {


        //条形图
        examProgressBarChart2.setDrawBarShadow(false);
        examProgressBarChart2.setDrawValueAboveBar(true);
        examProgressBarChart2.getDescription().setEnabled(false);
        // 扩展现在只能分别在x轴和y轴
        examProgressBarChart2.setPinchZoom(false);
        //是否显示表格颜色
        examProgressBarChart2.setDrawGridBackground(false);
        examProgressBarChart2.setTouchEnabled(false);

        //x轴
        XAxis xAxis = examProgressBarChart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        // 只有1天的时间间隔
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(4);
        xAxis.setAxisMaximum(5f);
        xAxis.setAxisMinimum(0f);
        xAxis.setTextSize(12);


        List<String> s = new ArrayList<>();
        s.add("");
        s.add("药学1");
        s.add("药学2");
        s.add("药学知识技能");
        s.add("管理法规");


        xAxis.setValueFormatter(new MyYAxisValueFormatter(s));


        YAxis leftAxis = examProgressBarChart2.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //这个替换setStartAtZero(true)
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);

        YAxis rightAxis = examProgressBarChart2.getAxisRight();
        rightAxis.setEnabled(false);

        // 设置标示，就是那个一组y的value的
        Legend l = examProgressBarChart2.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //样式
        l.setForm(Legend.LegendForm.SQUARE);
        //字体
        l.setFormSize(9f);
        //大小
        l.setTextSize(11f);
        l.setXEntrySpace(6f);


        List<ExamProgress> progressList = DataBaseUtil.getExamProgressList(this);
        int j=1;
        //加载数据
        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        for (int i = 3; i < 7; i++) {

            yVals1.add(new BarEntry(j, progressList.get(i).getLastExamPercent()));
            j++;
        }


        //mBarChart.setFitBars(true);
        examProgressBarChart2.animateY(1000, Easing.Linear);

        BarDataSet set1;
        if (examProgressBarChart2.getData() != null &&
                examProgressBarChart2.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) examProgressBarChart2.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            examProgressBarChart2.getData().notifyDataChanged();
            examProgressBarChart2.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "各科测试进度百分比(%)");
            //设置颜色
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(12f);
            data.setBarWidth(0.8f);
            //设置数据
            examProgressBarChart2.setData(data);
        }

    }
}



