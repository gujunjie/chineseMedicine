package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.abc.chinesemedicine.R;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.OnClick;
import customview.MyTitleBar;

public class SortActivity extends AppCompatActivity {

    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater mInflater = LayoutInflater.from(this);


        String sortType = getIntent().getStringExtra("sortType");
        switch (sortType) {
            case "pingyin":
                contentView = mInflater.inflate(R.layout.activity_pingyinsort, null);
                setContentView(contentView);
                MyTitleBar pingyinTitlebar = contentView.findViewById(R.id.pingyin_titlebar);
                pingyinTitlebar.getActivityForFinish(this);
                break;
            case "chinesePatentDrugFirstCategory":
                contentView = mInflater.inflate(R.layout.activity_chinesepatentdrugfirstcategory, null);
                setContentView(contentView);
                MyTitleBar drugfirstcategoryTitlebar = contentView.findViewById(R.id.drugfirstcategory_titlebar);
                drugfirstcategoryTitlebar.getActivityForFinish(this);
                break;
            case "acuPoint":
                contentView = mInflater.inflate(R.layout.activity_acupoint, null);
                setContentView(contentView);
                MyTitleBar acuPointTitlebar = contentView.findViewById(R.id.acuPoint_titBar);
                acuPointTitlebar.getActivityForFinish(this);
                break;
            case "prescription":
                contentView = mInflater.inflate(R.layout.activity_prescription, null);
                setContentView(contentView);
                MyTitleBar prescriptionTitleBar = contentView.findViewById(R.id.prescription_titleBar);
                prescriptionTitleBar.getActivityForFinish(this);
                break;
            case "medicalBook":
                contentView = mInflater.inflate(R.layout.activity_medicalbook, null);
                setContentView(contentView);
                MyTitleBar medicalBookTitleBar = contentView.findViewById(R.id.medicalBook_titleBar);
                medicalBookTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_huangdi":
                contentView = mInflater.inflate(R.layout.activity_huangdi, null);
                setContentView(contentView);
                MyTitleBar huangdiTitleBar = contentView.findViewById(R.id.huangdi_titleBar);
                huangdiTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_bencao":
                contentView = mInflater.inflate(R.layout.activity_bencao, null);
                setContentView(contentView);
                MyTitleBar bencaoTitleBar = contentView.findViewById(R.id.bencao_titleBar);
                bencaoTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_nanjing":
                contentView = mInflater.inflate(R.layout.activity_nanjing, null);
                setContentView(contentView);
                MyTitleBar nanjingTitleBar = contentView.findViewById(R.id.nanjing_titleBar);
                nanjingTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_jinkui":
                contentView = mInflater.inflate(R.layout.activity_jinkui, null);
                setContentView(contentView);
                MyTitleBar jinkuiTitleBar = contentView.findViewById(R.id.jinkui_titleBar);
                jinkuiTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_qianjing":
                contentView = mInflater.inflate(R.layout.activity_qianjing, null);
                setContentView(contentView);
                MyTitleBar qianjingTitleBar = contentView.findViewById(R.id.qianjing_titleBar);
                qianjingTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_shennong":
                contentView = mInflater.inflate(R.layout.activity_shennong, null);
                setContentView(contentView);
                MyTitleBar shennongTitleBar = contentView.findViewById(R.id.shennong_titleBar);
                shennongTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_tangtou":
                contentView = mInflater.inflate(R.layout.activity_tangtou, null);
                setContentView(contentView);
                MyTitleBar tangtouTitleBar = contentView.findViewById(R.id.tangtou_titleBar);
                tangtouTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_shanghan":
                contentView = mInflater.inflate(R.layout.activity_shanghan, null);
                setContentView(contentView);
                MyTitleBar shanghanTitleBar = contentView.findViewById(R.id.shanghan_titleBar);
                shanghanTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_pinghu":
                contentView = mInflater.inflate(R.layout.activity_pinghu, null);
                setContentView(contentView);
                MyTitleBar pinghuTitleBar = contentView.findViewById(R.id.pinghu_titleBar);
                pinghuTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_wenbing":
                contentView = mInflater.inflate(R.layout.activity_wenbing, null);
                setContentView(contentView);
                MyTitleBar wenbingTitleBar = contentView.findViewById(R.id.wenbing_titleBar);
                wenbingTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_yixue":
                contentView = mInflater.inflate(R.layout.activity_yixue, null);
                setContentView(contentView);
                MyTitleBar yixueTitleBar = contentView.findViewById(R.id.yixue_titleBar);
                yixueTitleBar.getActivityForFinish(this);
                break;
            case "medicalBookFirstCategory_yizong":
                contentView = mInflater.inflate(R.layout.activity_yizong, null);
                setContentView(contentView);
                MyTitleBar yizongTitleBar = contentView.findViewById(R.id.yizong_titleBar);
                yizongTitleBar.getActivityForFinish(this);
                break;
        }


        ImmersionBar.with(this).statusBarColor(R.color.colorAccent).fitsSystemWindows(true).init();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }



}
