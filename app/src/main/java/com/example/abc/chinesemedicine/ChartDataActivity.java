package com.example.abc.chinesemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;

public class ChartDataActivity extends AppCompatActivity {

    @BindView(R.id.chartData_titleBar)
    MyTitleBar chartDataTitleBar;
    @BindView(R.id.mBarChart)
    BarChart mBarChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_data);
        ButterKnife.bind(this);

        initUI();
    }

    public void initUI() {
        chartDataTitleBar.getActivityForFinish(this);


    }

}
